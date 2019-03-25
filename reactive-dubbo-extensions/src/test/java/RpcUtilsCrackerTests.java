import com.github.cherrythefatbunny.reactive.dubbo.extensions.rpc.support.RpcUtilsCracker;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.protocol.dubbo.DubboInvoker;
import org.apache.dubbo.rpc.support.RpcUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class RpcUtilsCrackerTests {
    static DubboInvoker invoker;
    @BeforeClass
    public static void init() {
        RpcUtilsCracker.crack();
        invoker = new DubboInvoker(RpcUtilsCrackerTests.class
                ,new URL("dubbo","127.0.0.1",20880,DemoProvider.class.getName())
                ,null);
    }
    @Test
    public void getReturnTypesTest1() throws NoSuchMethodException {
        Method methodDemo = DemoProvider.class.getDeclaredMethod("demo1");
        RpcInvocation invocation = new RpcInvocation(methodDemo,null);
        invocation.setInvoker(invoker);
        Type[] types = RpcUtils.getReturnTypes(invocation);
        Assert.assertEquals(2,types.length);
        Assert.assertEquals(methodDemo.getReturnType(),types[0]);
        Assert.assertEquals(methodDemo.getGenericReturnType(),types[1]);
    }
    @Test
    public void getReturnTypesTest2() throws NoSuchMethodException {
        Method methodDemo = DemoProvider.class.getDeclaredMethod("demo2");
        RpcInvocation invocation = new RpcInvocation(methodDemo,null);
        invocation.setInvoker(invoker);
        Type[] types = RpcUtils.getReturnTypes(invocation);
        Assert.assertEquals(1,types.length);
        Assert.assertArrayEquals(((ParameterizedType)methodDemo.getGenericReturnType()).getActualTypeArguments(),types);
    }
    @Test
    public void getReturnTypesTest3() throws NoSuchMethodException {
        Method methodDemo = DemoProvider.class.getDeclaredMethod("demo3");
        RpcInvocation invocation = new RpcInvocation(methodDemo,null);
        invocation.setInvoker(invoker);
        Type[] types = RpcUtils.getReturnTypes(invocation);
        Assert.assertEquals(2,types.length);
        Assert.assertEquals(methodDemo.getReturnType(),types[0]);
        Assert.assertEquals(methodDemo.getGenericReturnType(),types[1]);
    }
    @Test
    public void getReturnTypesTest4() throws NoSuchMethodException {
        Method methodDemo = DemoProvider.class.getDeclaredMethod("demo4");
        RpcInvocation invocation = new RpcInvocation(methodDemo,null);
        invocation.setInvoker(invoker);
        Type[] types = RpcUtils.getReturnTypes(invocation);
        Assert.assertEquals(2,types.length);
        Assert.assertEquals(List.class,types[0]);
        Type expected = ParameterizedTypeImpl
                .make(List.class,new Type[]{((ParameterizedTypeImpl)methodDemo.getGenericReturnType()).getActualTypeArguments()[0]},null);
        Assert.assertEquals(expected,types[1]);
    }
}