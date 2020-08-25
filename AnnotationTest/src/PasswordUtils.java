import java.util.List;

/**
 * @program: j8test
 * @description:
 * @author: limeng
 * @create: 2020-07-28 13:34
 **/
public class PasswordUtils {
    @UseCase(id = 47,description = "Password must contain at least one numeric")
    public boolean validatePassword(String passwd) {
        return (passwd.matches("\\w*\\d\\w*"));
    }
    @UseCase(id = 48)
    public String encryptPassword(String passwd) {
        return new StringBuilder(passwd)
                .reverse().toString();
    }
    @UseCase(id = 49, description = "New passwords can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords,String passwd) {
        return !prevPasswords.contains(passwd);
    }
}
