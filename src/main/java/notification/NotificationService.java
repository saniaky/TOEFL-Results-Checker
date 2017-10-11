package notification;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
public interface NotificationService {

    void sendNotification(String text, NotificationType type);

}
