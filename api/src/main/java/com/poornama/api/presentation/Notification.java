package com.poornama.api.presentation;

/**
 * @author dedunu
 */
public class Notification {
    private NotificationType notificationType;
    private String message;
    private int integer;

    /**
     * Return the integer value
     *
     * @return int
     */
    public int getInteger() {
        return integer;
    }

    /**
     * Sets the integer value
     *
     * @param integer int
     */
    public void setInteger(int integer) {
        this.integer = integer;
    }

    /**
     * Return NotificationType
     *
     * @return NotificationType
     */
    public NotificationType getNotificationType() {
        return notificationType;
    }

    /**
     * Sets NotificationType
     *
     * @param notificationType NotificationType
     */
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Return the message
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message
     *
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
