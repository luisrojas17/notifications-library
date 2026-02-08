FROM amazoncorretto:21-alpine

WORKDIR /app

ENV SENDGRID_API_KEY=98f4621e-2688-4af7-b739-a6b2b1cbc1a1
ENV SENDGRID_ENDPOINT=http://localhost:18080/send/email
ENV SENDGRID_PASS=password_example
ENV SENDGRID_SENDER=sender_example@acme.com
ENV SENDGRID_USER=user_example
ENV FIREBASE_ENDPOINT=http://localhost:18080/push
ENV FIREBASE_PROJECT_ID=98f4621e-2688-4af7-b739-a6b2b1cbc1a2
ENV TWILIO_AUTH_TOKEN=1f4o9r9bo8qml6d7nqlr3nmpo71i9k3q3coifp72v5hp5k5n2kqc
ENV TWILIO_ENDPOINT=http://localhost:18080/send/sms
ENV TWILIO_SID=3qfc0v5plov114n7082vbu68a4

COPY target/notifications-library-1.0.0.jar /app/notifications-library-1.0.0.jar
COPY target/libs/*.jar /app/libs/

CMD ["java", "-jar", "notifications-library-1.0.0.jar", "com.acme.notifications.ExecNotificationDispatcher"]
