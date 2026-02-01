package com.example.config;

import com.example.api.NotificationService;
import com.example.channel.EmailChannel;
import com.example.channel.PushChannel;
import com.example.channel.SmsChannel;
import com.example.provider.MockProvider;

public class NotificationServiceBuilder {

  public static NotificationService emailService() {
    return new NotificationService(
        new EmailChannel(new MockProvider())
    );
  }

  public static NotificationService smsService() {
    return new NotificationService(
        new SmsChannel(new MockProvider())
    );
  }

  public static NotificationService pushService() {
    return new NotificationService(
        new PushChannel(new MockProvider())
    );
  }

}
