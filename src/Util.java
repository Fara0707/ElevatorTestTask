import static java.lang.Math.max;
import static java.lang.Math.min;

public class Util {
    public static int randomBetweenTwo(int a, int b) {
        return (int)((Math.random() * (max(a, b) - min(a,b) + 1)) + min(a, b));
    }
}
