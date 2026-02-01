package com.example.channel;

import com.example.api.NotificationChannel;
import com.example.api.NotificationException;
import com.example.api.NotificationResult;
import com.example.message.NotificationMessage;
import com.example.provider.NotificationProvider;

public class EmailChannel implements NotificationChannel {

  private final NotificationProvider provider;

  public EmailChannel(NotificationProvider provider) {
    this.provider = provider;
  }

  @Override
  public NotificationResult send(NotificationMessage message) throws NotificationException {
    provider.send(message);
    return new NotificationResult(true, "Email sent");
  }

}
