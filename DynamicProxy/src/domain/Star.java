package domain;

/**
 * @program: j8test
 * @description: 明星实现了ShowService
 * @author: limeng
 * @create: 2019-10-12 13:35
 **/
public class Star implements ShowService {
    private String name;

    @Override
    public void sing(String songName) {
        System.out.println(this.name+" sing a song: "+songName);
    }

    @Override
    public void dance() {
        System.out.println(this.name+"dandce");
    }

    public Star(String name) {
        this.name = name;
    }
    public Star(){

    }

}
