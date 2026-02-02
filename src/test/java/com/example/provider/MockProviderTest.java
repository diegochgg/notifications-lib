package com.example.provider;

import com.example.api.NotificationException;
import com.example.message.NotificationMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockProviderTest {

  @Test
  void shouldSendNotificationWithoutError() {
    MockProvider provider = new MockProvider();
    NotificationMessage message = new NotificationMessage("test@example.com", "title", "body");
    Assertions.assertDoesNotThrow(() -> provider.send(message));
  }

  @Test
  void shouldWrapExceptionIntoNotificationException() {
    MockProvider provider = new MockProvider();
    NotificationMessage message = Mockito.mock(NotificationMessage.class);
    Mockito.when(message.getTo()).thenThrow(new RuntimeException("Failed Runtime"));

    NotificationException exception =
        Assertions.assertThrows(NotificationException.class, () -> provider.send(message));

    Assertions.assertEquals("Provider error", exception.getMessage());
    Assertions.assertNotNull(exception.getCause());
    Assertions.assertEquals("Failed Runtime", exception.getCause().getMessage());
  }

}
