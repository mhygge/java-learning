package JDKPattern;

import domain.ShowService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: j8test
 * @description: 代理请求处理器
 * @author: limeng
 * @create: 2019-10-12 13:46
 **/
public class InvocationhandlerImpl implements InvocationHandler {
    ShowService target;

    public InvocationhandlerImpl(ShowService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        //表演前开始收钱
        getMoney();
        //明星开始唱歌
        Object invoke=method.invoke(target,args);
        //表演结束后开发票
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
