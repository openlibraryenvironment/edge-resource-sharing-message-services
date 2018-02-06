FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Ian.Ibbotson@k-int.com
VOLUME /tmp
ADD resource-sharing-message-services-1.0.jar resource-sharing-message-services.jar
RUN sh -c 'touch /resource-sharing-message-services.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/resource-sharing-message-services.jar"]
