package com.example.message;

public class NotificationMessage {

  private final String to;
  private final String title;
  private final String body;

  public NotificationMessage(String to, String title, String body) {
    this.to = to;
    this.title = title;
    this.body = body;
  }

  public String getTo() {
    return to;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }

}
