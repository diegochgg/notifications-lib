package com.example.api;

import com.example.message.NotificationMessage;

public interface NotificationChannel {

  NotificationResult send(NotificationMessage message) throws NotificationException;

}
