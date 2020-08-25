package domain;

/**
 * @program: j8test
 * @description: 经纪人
 * @author: limeng
 * @create: 2019-10-12 13:41
 **/
public class Agent implements ShowService {
    private Star star;

    public Agent(Star star) {
        this.star = star;
    }
    private void getMoney(){
        System.out.println("get money");
    }
    private void writeReceipt(){
        System.out.println("write receipt");
    }

    @Override
    public void sing(String songName) {
        //唱歌前开始收钱
        getMoney();
        //明星开始唱歌
    }

    @Override
    public void dance() {

    }
}
