# Notifications Library

Notification library contains the implementation for classic multi-channel notification + multi-provider + async 
design challenge.

## Overview

This library is a simple implementation of a notification system that can send notifications to different channels 
(Email, SMS, Push notifications) using different providers (SendGrid, Firebase, Twilio). The communication to sent
the notifications can be done synchronously and asynchronously.

## Key Components

- com.acme.notifications.NotificationDispatcher: This is the main class that dispatches notifications to different channels using different providers.
- com.acme.notifications.providers.NotificationProvider: This is the interface that defines the contract for the providers.
- com.acme.notifications.providers.AbstractNotificationProvider: This is the abstract class that implements the NotificationProvider interface.
- com.acme.domains.Notification: This is the class that represents a notification.
- com.acme.domains.Channel: This is the enum that represents the different channels.
- com.acme.providers.impl.SendGridProvider: This is the implementation of the NotificationProvider interface for SendGrid.
- com.acme.providers.impl.FirebaseProvider: This is the implementation of the NotificationProvider interface for Firebase.
- com.acme.providers.impl.TwilioProvider: This is the implementation of the NotificationProvider interface for Twilio.
- com.acme.notifications.ExecNotificationDispatcher: This is an example class to show hot to use NotificationDispatcher class.


## How to run the application

### Prerequisites

In order to run the application, you need to have next tools installed in your machine:

- JDK 21
- Maven 3.X
- Docker engine (For example, Docker version 28.3.3, build 980b856).

### Steps to run the application

The steps to execute are next: 

1- Go to project root directory. From command line run next command:
  ```
  cd /path/to/project_roor_directory
  ```

2- Compile and package the application with next command:
  ```
  mvn clean package
  ```

3- Build the image based on the Dockerfile:
  ```
  docker build --no-cache -t notifications-library --force-rm=true .
  ```
Note: The --no-cache option is used to avoid using cached layers, which can help ensure that the image is built 
with the latest dependencies, any other changes made to the code or the Dockerfile.

4- Run the container:
  ```
  docker run -it notifications-library /bin/sh
  ```
Note: This because the image is based on Alpine Linux, which use /bin/sh instead of /bin/bash.

5- And finally, running the application inside the container:
  ```
  java -jar notifications-library-1.0.0.jar com.acme.notifications.ExecNotificationDispatcher
  ```
The application will ask you to provide an option to simulate sending notifications. The possible options are:

- dispatch: To send just one notification using the all providers configured. 
  In this case, the providers configured are SendGridProvider and FarebaseProvider.
- dispatchAll: To dispatch a list of notifications considering the channel notification and providers configured.
  In this case, the providers configured are SendGridProvider, FarebaseProvider and TwilioProvider.

Other util commands:

- To verify the image created:
  ```
  docker images
  ```

- To remove the image created:
  ```
  docker rmi -f <IMAGE_ID>
  ```
