import java.util.Timer;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
public class Main {

    private static final int PERIOD = 1 * 60 * 1000;

    public static void main(String[] args) {
        Timer t = new Timer();
        ToeflCheckTask toeflCheckTask = new ToeflCheckTask();
        t.scheduleAtFixedRate(toeflCheckTask, 0, PERIOD);
    }

}
