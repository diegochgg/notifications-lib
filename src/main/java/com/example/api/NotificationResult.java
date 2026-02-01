package com.example.api;

public class NotificationResult {

  private final boolean success;
  private final String message;

  public NotificationResult(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

}
