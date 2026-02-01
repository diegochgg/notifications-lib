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

**Ejemplo de uso para envío de una notificación por email**

```java
NotificationService notificationService = NotificationServiceBuilder.emailService();

NotificationMessage message =
        new NotificationMessage(
                "user@email.com",
                "Bienvenido",
                "Gracias por registrarte"
        );

NotificationResult result = notificationService.notify(message);

System.out.println(result.getMessage());
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

- Método principal : void notify(NotificationMessage message)

**NotificationChannel** : Representa un canal de notificación (Email, SMS, Push, etc.).

**NotificationProvider** : Abstracción del proveedor externo.

**NotificationMessage** : Objeto de dominio que representa el contenido de la notificación.

**NotificationResult** : Representa el resultado del envío.

## Seguridad

Buenas prácticas recomendadas para integraciones reales:

- Usar variables de entorno o gestores de secretos.
- Evitar logs con información sensible.


