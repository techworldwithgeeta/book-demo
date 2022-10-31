FROM java:8
EXPOSE 8080
ADD target/book-store-demo-docker.jar book-store-demo-docker.jar
ENTRYPOINT ["java","-jar","/book-store-demo-docker.jar"]