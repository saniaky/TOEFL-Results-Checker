import ets.ETSParser;
import ets.Status;
import notification.Notification;
import notification.NotificationType;

import java.util.TimerTask;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
public class ToeflCheckTask extends TimerTask {

    private Notification notification;
    private ETSParser etsParser;

    public ToeflCheckTask() {
        notification = new Notification();
        etsParser = new ETSParser();
    }

    @Override
    public void run() {
        Status status = etsParser.parse();

        switch (status) {
            case NO_RESULTS:
                break;
            case RESULTS_AVAILABLE:
                notification.sendMessage("Yahooo, results are available.", NotificationType.INFO);
                System.exit(0);
                break;
            case SESSION_TIMEOUT:
                notification.sendMessage("You've been logged out.", NotificationType.ERROR);
                System.exit(0);
            default:
                notification.sendMessage("Oops, something went wrong.", NotificationType.ERROR);
                System.exit(0);
        }
    }


}
