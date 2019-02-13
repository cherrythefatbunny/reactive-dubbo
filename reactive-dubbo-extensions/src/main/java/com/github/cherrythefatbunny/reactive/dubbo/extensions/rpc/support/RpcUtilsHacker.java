package com.github.cherrythefatbunny.reactive.dubbo.extensions.rpc.support;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.Invocation;
import javassist.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * hack RpcUtils.getReturnTypes,supporting mono and flux
 * @author cherry
 */
public class RpcUtilsHacker {
    private static final Logger logger = LoggerFactory.getLogger(RpcUtilsHacker.class);
    private static final String RPCUTILS_CLASS_NAME = "com.alibaba.dubbo.rpc.support.RpcUtils";
    public static void hack() {
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get(RPCUTILS_CLASS_NAME);
            CtMethod ctMethod = ctClass.getDeclaredMethod("getReturnTypes");
            //rename method `getReturnTypes` to `getReturnTypes0`
            ctClass.removeMethod(ctMethod);
            ctMethod.setName("getReturnTypes0");
            ctClass.addMethod(ctMethod);
            //add new method `getReturnTypes` according to RpcUtilsHacker.getReturnTypes
            CtClass ctClass1 = classPool.get(RpcUtilsHacker.class.getName());
            ctMethod = new CtMethod(ctClass1.getDeclaredMethod("getReturnTypes"),ctClass,null);
            ctClass.addMethod(ctMethod);
            ctClass.toClass();
        } catch (NotFoundException|CannotCompileException e) {
            logger.warn("hack RpcUtils failed",e);
        }
    }
    public static Type[] getReturnTypes0(Invocation invocation) {
        return null;
    }

    /**
     * template method of RpcUtils.getReturnTypes
     * */
    public static Type[] getReturnTypes(Invocation invocation) {
        Type[] types = getReturnTypes0(invocation);
        if(types==null)return null;
        if (types[0] == Mono.class) {
            Class<Mono> returnType = (Class<Mono>) types[0];
            ParameterizedType genericReturnType = (ParameterizedType) types[1];
            return new Type[]{genericReturnType.getActualTypeArguments()[0]};
        }
        if (types[0] == Flux.class) {
            Class<Flux> returnType = (Class<Flux>) types[0];
            ParameterizedType genericReturnType = (ParameterizedType) types[1];
            genericReturnType = ParameterizedTypeImpl.make(List.class,new Type[]{genericReturnType.getActualTypeArguments()[0]},null);
            return new Type[]{List.class, genericReturnType};
        }
        return types;
    }
}