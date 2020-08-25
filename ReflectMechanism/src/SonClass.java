/**
 * @program: j8test
 * @description: 子类
 * @author: limeng
 * @create: 2019-10-12 09:03
 **/
public class SonClass extends FatherClass {
    private String mSonName;
    protected int mSonAge;
    public String mSonBirthday;

    public String getmSonName() {
        return mSonName;
    }

    public void setmSonName(String mSonName) {
        this.mSonName = mSonName;
    }

    public int getmSonAge() {
        return mSonAge;
    }

    public void setmSonAge(int mSonAge) {
        this.mSonAge = mSonAge;
    }

    public void printSonMsg(){
        System.out.println("Son Msg - name : "+mSonName+"; age : "+mSonAge);

    }
}
