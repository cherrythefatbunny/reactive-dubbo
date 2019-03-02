package com.alibaba.dubbo.remoting.exchange.support.header;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.exchange.ExchangeChannel;
import com.alibaba.dubbo.remoting.exchange.ExchangeHandler;
import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.dubbo.remoting.exchange.Response;
import com.alibaba.dubbo.rpc.RpcResult;
import com.github.cherrythefatbunny.reactive.dubbo.extensions.remoting.exchange.ReactiveHeaderExchanger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.function.Function;


/**
 * reactive HeaderExchangeHandler which {@link ReactiveHeaderExchanger} to work with.
 * Having the same package as {@link HeaderExchangeHandler} which has some unproper method signatures
 * @author cherrythefatbunny
 */
public class ReactiveHeaderExchangeHandler extends HeaderExchangeHandler {
    public static final String NAME = "reactiveheader";
    protected static final Logger LOGGER = LoggerFactory.getLogger(HeaderExchangeHandler.class);

    private static final String CHANNEL_KEY = HeaderExchangeChannel.class.getName() + ".CHANNEL";
    protected final ExchangeHandler handler;

    public ReactiveHeaderExchangeHandler(ExchangeHandler handler) {
        super(handler);
        if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.handler = handler;
    }

    @Override
    public void received(Channel channel, Object message) throws RemotingException {
        channel.setAttribute(KEY_READ_TIMESTAMP, System.currentTimeMillis());
        ExchangeChannel exchangeChannel = HeaderExchangeChannel.getOrAddChannel(channel);
        try {
            if (message instanceof Request) {
                // handle request.
                Request request = (Request) message;
                if (request.isEvent()) {
                    handlerEvent(channel, request);
                } else {
                    if (request.isTwoWay()) {
                        handleRequest0(exchangeChannel,request)
                        .doOnNext(response -> {
                            try {
                                channel.send(response);
                            } catch (RemotingException e) {
                                LOGGER.warn(String.format("send response error[request:%s]",request),e);
                            }
                        }).subscribe();
                    } else {
                        handler.received(exchangeChannel, request.getData());
                    }
                }
            } else if (message instanceof Response) {
                handleResponse(channel, (Response) message);
            } else if (message instanceof String) {
                if (isClientSide(channel)) {
                    Exception e = new Exception("Dubbo client can not supported string message: " + message + " in channel: " + channel + ", url: " + channel.getUrl());
                    logger.error(e.getMessage(), e);
                } else {
                    String echo = handler.telnet(channel, (String) message);
                    if (echo != null && echo.length() > 0) {
                        channel.send(echo);
                    }
                }
            } else {
                handler.received(exchangeChannel, message);
            }
        } finally {
            HeaderExchangeChannel.removeChannelIfDisconnected(channel);
        }
    }
    protected Mono<Response> handleRequest0(ExchangeChannel channel, Request req) {
        Response res = new Response(req.getId(), req.getVersion());
        if (req.isBroken()) {
            Object data = req.getData();

            String msg;
            if (data == null) msg = null;
            else if (data instanceof Throwable) msg = StringUtils.toString((Throwable) data);
            else msg = data.toString();
            res.setErrorMessage("Fail to decode request due to: " + msg);
            res.setStatus(Response.BAD_REQUEST);

            return Mono.just(res);
        }
        // find handler by message class.
        Object msg = req.getData();
        try {
            // handle data.
            Object result = handler.reply(channel, msg);
            if(result!=null&&RpcResult.class.isAssignableFrom(result.getClass())) {
                Object meta = ((RpcResult)result).getValue();
                if (meta instanceof Mono) {
                    return Mono.from((Mono) meta)
                            .onErrorResume(fallback(res))
                            .map(o -> {
                                res.setStatus(Response.OK);
                                ((RpcResult) result).setValue(o);
                                res.setResult(result);
                                return res;
                            });
                } else if (meta instanceof Flux) {
                    return Flux.from((Flux<Object>) meta)
                            .onErrorResume(fallback(res))
                            .collect(ArrayList::new, ArrayList::add)
                            .map(list -> {
                                ((RpcResult) result).setValue(list);
                                res.setResult(result);
                                return res;
                            });
                }
            }
            res.setStatus(Response.OK);
            res.setResult(result);
        } catch (Throwable e) {
            res.setStatus(Response.SERVICE_ERROR);
            res.setErrorMessage(StringUtils.toString(e));
        }
        return Mono.just(res);
    }
    private static boolean isClientSide(Channel channel) {
        InetSocketAddress address = channel.getRemoteAddress();
        URL url = channel.getUrl();
        return url.getPort() == address.getPort() &&
                NetUtils.filterLocalHost(url.getIp())
                        .equals(NetUtils.filterLocalHost(address.getAddress().getHostAddress()));
    }
    protected Function<? super Throwable, ? extends Mono<? extends Response>> fallback(Response res) {
        return throwable -> {
            res.setStatus(Response.SERVICE_ERROR);
            res.setErrorMessage(StringUtils.toString((Throwable) throwable));
            return Mono.just(res);
        };
    }
}