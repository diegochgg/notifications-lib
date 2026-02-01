package com.example.provider;

import com.example.message.NotificationMessage;

public class MockProvider implements NotificationProvider {

  @Override
  public void send(NotificationMessage message) {
    System.out.println("simulating sending to " + message.getTo());
  }

}
