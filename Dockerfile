FROM java:8
MAINTAINER 835256838@qq.com
VOLUME /tmp
ADD spider-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]