package com.poornama.api.presentation;

/**
 * Created by dedunu on 11/7/14.
 */
public class Notification {
    private NotificationType notificationType;
    private String message;

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
