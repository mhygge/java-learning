/**
 * @program: j8test
 * @description: 测试类
 * @author: limeng
 * @create: 2019-10-12 10:04
 **/
public class TestClass {
    private String MSG = "Original";

    private void privateMethod(String head, int tail) {
        System.out.print(head + tail);
    }

    public String getMSG() {
        return MSG;
    }

    //常量
    //String 会被 JVM 优化
   /* private final String FINAL_VALUE="FINAL";

    public String getFINAL_VALUE() {
        return FINAL_VALUE;
    }*/


  /* private final String FINAL_VALUE;

   //构造方法内为常量赋值

    public TestClass( ) {
        this.FINAL_VALUE = "FINAL";
    }

    public String getFINAL_VALUE() {
        return FINAL_VALUE;
    }*/

    private final String FINAL_VALUE
            = null == null ? "FINAL" : null;

    public String getFINAL_VALUE() {
        return FINAL_VALUE;
    }
}
