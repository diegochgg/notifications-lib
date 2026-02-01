package com.example.api;

import com.example.message.NotificationMessage;

public class NotificationService {

  private final NotificationChannel channel;

  public NotificationService(NotificationChannel channel) {
    this.channel = channel;
  }

  public NotificationResult notify(NotificationMessage message) throws NotificationException {
    return channel.send(message);
  }

}
