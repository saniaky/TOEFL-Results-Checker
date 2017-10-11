package notification;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
public class Notification {

    private final TrayNotification trayNotification;
    private final TelegramNotification telegramNotification;

    public Notification() throws RuntimeException {
        trayNotification = new TrayNotification();
        telegramNotification = new TelegramNotification();
    }

    public void sendMessage(String message, NotificationType type) {
        trayNotification.sendNotification(message, type);
        //telegramNotification.sendNotification(message, type);
    }

}
