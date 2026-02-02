# Librería de Notificaciones

Librería Java para el envío de notificaciones multicanal (Email, SMS, Push, etc.) con un diseño extensible, desacoplado y preparado para integraciones reales con proveedores externos.

El objetivo es facilitar el envío de notificaciones sin acoplar la lógica de negocio a proveedores específicos.

## Instalación

La librería puede integrarse en proyectos Java usando Maven o Gradle.

- Maven

```xml
<dependency>
  <groupId>com.example</groupId>
  <artifactId>notifications-lib</artifactId>
  <version>1.0.0</version>
</dependency>
``` 

- Gradle

```groovy
dependencies {
    implementation("com.example:notifications-lib:1.0.0")
}
``` 

## Quick Start

**Sync: Ejemplo de uso para envío de una notificación por email**

```java
NotificationService notificationService = NotificationServiceBuilder.emailService();

NotificationMessage message =
        new NotificationMessage(
                "user@email.com",
                "Bienvenido",
                "Gracias por registrarte"
        );

try {
    NotificationResult result = notificationService.notify(message);
    System.out.println(result.getMessage());
} catch (NotificationException e) {
    System.err.println(e.getMessage());
}
```

**Async: Ejemplo de uso para envíos de notificación por email por lote**
```java
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
    
    CompletableFuture.allOf(asyncFutures.toArray(new CompletableFuture[0])).join();
```

## Configuración

La configuración de canales y proveedores se realiza mediante builders/factories, manteniendo el dominio desacoplado de la infraestructura.

**Configurar Email**
```java
NotificationService emailService = NotificationServiceBuilder.emailService();
```

**Configurar Sms**
```java
NotificationService emailService = NotificationServiceBuilder.smsService();
```

**Configurar Push Notifications**
```java
NotificationService emailService = NotificationServiceBuilder.pushService();
```

**Cambiar de proveedor no requiere modificar el código cliente, solo la configuración interna del builder (NotificationServiceBuilder).**

## Proveedores soportados

Actualmente se incluye una implementación génerica simulada de un proveedor, sin embargo siguiendo la documentación real de proveedores se pueden obtener:

| Canal | Proveedor simulado | Referencia real |
|-------|--------------------|---------|
| Email | MockEmailProvider  | SendGrid, Mailgun |
| Sms   | MockSmsProvider    | Twilio, Nexmo   |
| Push  | MockPushProvider   | Firebase Cloud Messaging     |

## API Reference

**NotificationService** : Responsable de orquestar el envío de notificaciones.

- Métodos principales :

  `NotificationResult notify(NotificationMessage message)`
  **Envía una notificación y puede lanzar NotificationException si el envío falla.**

  `CompletableFuture<NotificationResult> notifyAsync(NotificationMessage message)`
  **Envía una notificación de forma asíncrona. Permite construir flujos de envío en lote desde el código cliente.**

**NotificationChannel** : Representa un canal de notificación (Email, SMS, Push, etc.).

**NotificationProvider** : Abstracción del proveedor externo.

**NotificationMessage** : Objeto de dominio que representa el contenido de la notificación.

**NotificationResult** : Representa el resultado del envío.

## Seguridad

Buenas prácticas recomendadas para integraciones reales:

- Usar variables de entorno o gestores de secretos.
- Evitar logs con información sensible.

## Manejo de errores
El envío de notificaciones puede fallar por problemas de validación, infraestructura o proveedores externos.  
En estos casos, la librería lanza una `NotificationException` para que el cliente decida cómo manejar el error.

## Notificaciones Asíncronas
La librería soporta el envío de notificaciones de forma asíncrona utilizando `CompletableFuture`, 
permitiendo ejecutar envíos no bloqueantes y en paralelo.

**El servicio expone un método asíncrono que retorna un CompletableFuture:**
```java
CompletableFuture<NotificationResult> future = notificationService.notifyAsync(message);
```

## Docker
Este proyecto incluye un Dockerfile para facilitar la ejecución de ejemplos sin necesidad de configurar Java localmente.

```bash
docker build -t notifications-lib .
docker run notifications-lib