/**
 * @program: j8test
 * @description:
 * @author: limeng
 * @create: 2020-07-06 16:31
 **/
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader:"+this.name + " in " + this.city;
    }
}
