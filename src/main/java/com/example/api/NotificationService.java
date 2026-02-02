package com.example.api;

import com.example.message.NotificationMessage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class NotificationService {

  private final NotificationChannel channel;

  public NotificationService(NotificationChannel channel) {
    this.channel = channel;
  }

  public NotificationResult notify(NotificationMessage message) throws NotificationException {
    return channel.send(message);
  }

  public CompletableFuture<NotificationResult> notifyAsync(NotificationMessage message) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        return channel.send(message);
      } catch (NotificationException e) {
        throw new CompletionException(e);
      }
    });
  }

}
