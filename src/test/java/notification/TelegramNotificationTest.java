package notification;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexander Kohonovsky
 * @since 11.10.2017
 */
public class TelegramNotificationTest {

    private TelegramNotification telegramNotification;

    @Before
    public void setUp() throws Exception {
        telegramNotification = new TelegramNotification();
    }

    @Test
    public void sendNotification() throws Exception {
        telegramNotification.sendNotification("Hi", null);
    }

}