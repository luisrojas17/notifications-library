This repo contains implementation for classic multi-channel notification + multi-provider + async design challenge.


To build the image based on the Dockerfile:

    docker build --no-cache -t notifications-library --force-rm=true .

To verify the image created:

    docker images

Note: if you want to remove the image created:

        docker rmi -f <IMAGE_ID>

To run the container:

    docker run -it notifications-library /bin/bash

    docker run -it notifications-library /bin/sh

To run the application inside the container:

    java -jar notifications-library-1.0.0.jar com.acme.notifications.ExecNotificationDispatcher
