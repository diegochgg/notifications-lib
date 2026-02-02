package com.example.examples;

import com.example.api.NotificationException;
import com.example.api.NotificationResult;
import com.example.api.NotificationService;
import com.example.config.NotificationServiceBuilder;
import com.example.message.NotificationMessage;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NotificationExample {

  public static void main(String[] args) throws NotificationException {

    System.out.println("=== Email sync ===");
    NotificationService emailService = NotificationServiceBuilder.emailService();

    NotificationMessage emailMessage =
        new NotificationMessage("user@email.com", "Bienvenido", "Gracias por registrarte."
        );

    NotificationResult emailResult = emailService.notify(emailMessage);
    System.out.println("[EMAIL] " + emailResult.getMessage());

    System.out.println("\n=== Launching async email batch ===");
    NotificationService emailServiceAsync = NotificationServiceBuilder.emailService();

    List<NotificationMessage> messages = List.of(
        new NotificationMessage("a@mail.com", "Hola A", "Mensaje A"),
        new NotificationMessage("b@mail.com", "Hola B", "Mensaje B"),
        new NotificationMessage("c@mail.com", "Hola C", "Mensaje C")
    );

    List<CompletableFuture<Void>> asyncFutures =
        messages.stream()
            .map(message ->
                emailServiceAsync.notifyAsync(message)
                    .thenAccept(result ->
                        System.out.println("[ASYNC EMAIL] Sent to " + message.getTo() + ": " + result.getMessage()
                        )
                    )
                    .exceptionally(ex -> {
                      System.err.println("[ASYNC EMAIL] Error sending to " + message.getTo() + ": " +
                                         ex.getCause().getMessage()
                      );
                      return null;
                    })
            )
            .toList();

    System.out.println("\n=== Sms sync ===");
    NotificationService smsService = NotificationServiceBuilder.smsService();

    NotificationMessage smsMessage =
        new NotificationMessage("+51987654321", null, "Su código de verificación es 123456"
        );

    NotificationResult smsResult = smsService.notify(smsMessage);
    System.out.println("[SMS] " + smsResult.getMessage());

    System.out.println("\n=== Push sync ===");
    NotificationService pushService = NotificationServiceBuilder.pushService();

    NotificationMessage pushMessage =
        new NotificationMessage("device-token-abc-123", "Nueva notificación", "Tienes un nuevo mensaje."
        );

    NotificationResult pushResult = pushService.notify(pushMessage);
    System.out.println("[PUSH] " + pushResult.getMessage());
    System.out.println("\n=== Sync examples completed ===");

    System.out.println("\n=== End async ===");
    CompletableFuture.allOf(asyncFutures.toArray(new CompletableFuture[0])).join();
    System.out.println("\n=== Async example completed ===");
  }
}
