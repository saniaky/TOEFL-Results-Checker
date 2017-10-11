package notification;

import java.awt.*;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
public enum NotificationType {

    INFO {
        @Override
        TrayIcon.MessageType getTrayIcon() {
            return TrayIcon.MessageType.INFO;
        }
    },

    WARNING {
        @Override
        TrayIcon.MessageType getTrayIcon() {
            return TrayIcon.MessageType.WARNING;
        }
    },

    ERROR {
        @Override
        TrayIcon.MessageType getTrayIcon() {
            return TrayIcon.MessageType.ERROR;
        }
    };

    abstract TrayIcon.MessageType getTrayIcon();
}
