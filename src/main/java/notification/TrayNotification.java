package notification;

import java.awt.*;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
class TrayNotification implements NotificationService {

    private final TrayIcon trayIcon;

    public TrayNotification() throws RuntimeException {
        if (!SystemTray.isSupported()) {
            throw new RuntimeException("Tray is not supported");
        }
        trayIcon = createTrayIcon();
    }

    @Override
    public void sendNotification(String message, NotificationType type) {
        trayIcon.displayMessage("TOEFL Checker", message, type.getTrayIcon());
    }

    private TrayIcon createTrayIcon() {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");

        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);

        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("desktop system tray is missing!");
        }

        return trayIcon;
    }
}
