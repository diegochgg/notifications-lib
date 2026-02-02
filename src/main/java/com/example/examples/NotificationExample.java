package com.example.examples;

import com.example.api.NotificationException;
import com.example.api.NotificationResult;
import com.example.api.NotificationService;
import com.example.config.NotificationServiceBuilder;
import com.example.message.NotificationMessage;

public class NotificationExample {

  public static void main(String[] args) throws NotificationException {

    System.out.println("Notification Examples: Email");
    NotificationService emailService = NotificationServiceBuilder.emailService();

    NotificationMessage emailMessage =
        new NotificationMessage("user@email.com", "Bienvenido", "Gracias por registrarte"
        );

    NotificationResult emailResult = emailService.notify(emailMessage);
    System.out.println("[EMAIL] " + emailResult.getMessage());

    System.out.println("Notification Examples: Sms");
    NotificationService smsService = NotificationServiceBuilder.smsService();

    NotificationMessage smsMessage =
        new NotificationMessage("+51987654321", null, "Su código de verificación es 123456"
        );

    NotificationResult smsResult = smsService.notify(smsMessage);
    System.out.println("[SMS] " + smsResult.getMessage());

    System.out.println("Notification Examples: Push");
    NotificationService pushService = NotificationServiceBuilder.pushService();

    NotificationMessage pushMessage =
        new NotificationMessage("device-token-abc-123", "Nueva notificación", "Tienes un nuevo mensaje"
        );

    NotificationResult pushResult = pushService.notify(pushMessage);
    System.out.println("[PUSH] " + pushResult.getMessage());
  }

}
