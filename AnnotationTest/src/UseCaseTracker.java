import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: j8test
 * @description:
 * @author: limeng
 * @create: 2020-07-28 13:42
 **/
public class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found Use Case" + uc.id() + "\n" + uc.description());
                useCases.remove(Integer.valueOf(uc.id()));
            }
        }
        useCases.forEach(i -> System.out.println("Missing use case " + i));

    }

    public static void main(String[] args) {
        List<Integer> useCases = IntStream.range(47,51)
                .boxed().collect(Collectors.toList());
        trackUseCases(useCases, PasswordUtils.class);

    }
}
