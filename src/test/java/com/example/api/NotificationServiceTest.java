package com.example.api;

import com.example.message.NotificationMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

  @Mock
  private NotificationChannel channel;

  @InjectMocks
  private NotificationService service;

  @Test
  void shouldSendNotificationSuccessfully() throws NotificationException {
    NotificationMessage message = new NotificationMessage("test@example.com", "title", "body");
    NotificationResult expectedResult = new NotificationResult(true, "sent");

    Mockito.when(channel.send(message)).thenReturn(expectedResult);
    NotificationResult result = service.notify(message);

    Assertions.assertEquals(expectedResult, result);
    Mockito.verify(channel).send(message);
  }

  @Test
  void shouldPropagateNotificationException() throws NotificationException {
    NotificationMessage message = new NotificationMessage("test@example.com", "title", "body");
    Mockito.when(channel.send(message)).thenThrow(new NotificationException("Channel error"));
    Assertions.assertThrows(NotificationException.class, () -> service.notify(message));
    Mockito.verify(channel).send(message);
  }

}
