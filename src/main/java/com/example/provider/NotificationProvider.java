package com.example.provider;

import com.example.api.NotificationException;
import com.example.message.NotificationMessage;

public interface NotificationProvider {

  void send(NotificationMessage message) throws NotificationException;

}
