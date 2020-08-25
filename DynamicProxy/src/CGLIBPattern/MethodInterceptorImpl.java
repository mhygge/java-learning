package CGLIBPattern;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: j8test
 * @description: 。。。
 * @author: limeng
 * @create: 2019-10-14 10:01
 **/
public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        getMoney();
        Object invoke=methodProxy.invokeSuper(o,objects);
        writeReceipt();
        return invoke;
    }
    private void getMoney(){
        System.out.println("get money");
    }
    private void writeReceipt(){
        System.out.println("write receipt");
    }
}
