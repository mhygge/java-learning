package CGLIBPattern;

import domain.ShowService;
import domain.Star;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

/**
 * @program: j8test
 * @description: 创建cglib代理
 * @author: limeng
 * @create: 2019-10-14 10:05
 **/
public class CglibProxyDemo {
    @Test
    public void test() {
        Star star=new Star("Eminem");
        MethodInterceptor methodInterceptor=new MethodInterceptorImpl();
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(star.getClass());
        enhancer.setCallback(methodInterceptor);
        ShowService showService= (ShowService) enhancer.create();
        showService.sing("Mockingbird");
    }
}
