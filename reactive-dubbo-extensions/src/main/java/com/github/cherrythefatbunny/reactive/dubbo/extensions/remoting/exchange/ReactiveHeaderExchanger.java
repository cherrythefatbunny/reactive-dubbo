package com.github.cherrythefatbunny.reactive.dubbo.extensions.remoting.exchange;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.Transporters;
import com.alibaba.dubbo.remoting.exchange.ExchangeClient;
import com.alibaba.dubbo.remoting.exchange.ExchangeHandler;
import com.alibaba.dubbo.remoting.exchange.ExchangeServer;
import com.alibaba.dubbo.remoting.exchange.Exchanger;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeClient;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeServer;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchanger;
import com.alibaba.dubbo.remoting.exchange.support.header.ReactiveHeaderExchangeHandler;
import com.alibaba.dubbo.remoting.transport.DecodeHandler;

/**
 * reactive HeaderExchanger to replace {@link HeaderExchanger}
 * @author cherrythefatbunny
 */
public class ReactiveHeaderExchanger implements Exchanger {

    public static final String NAME = "reactiveheader";

    @Override
    public ExchangeClient connect(URL url, ExchangeHandler handler) throws RemotingException {
        return new HeaderExchangeClient(Transporters.connect(url, new DecodeHandler(new ReactiveHeaderExchangeHandler(handler))), true);
    }

    @Override
    public ExchangeServer bind(URL url, ExchangeHandler handler) throws RemotingException {
        return new HeaderExchangeServer(Transporters.bind(url, new DecodeHandler(new ReactiveHeaderExchangeHandler(handler))));
    }

}