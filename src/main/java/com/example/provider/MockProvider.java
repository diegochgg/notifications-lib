package com.example.provider;

import com.example.api.NotificationException;
import com.example.message.NotificationMessage;

public class MockProvider implements NotificationProvider {

  @Override
  public void send(NotificationMessage message) throws NotificationException {
    try {
      System.out.println("Simulating sending to " + message.getTo());
    } catch (Exception e) {
      throw new NotificationException("Provider error", e);
    }
  }

}
