FROM bellsoft/liberica-openjdk-alpine:21
COPY cadabra-shop-impl/target/cadabra-shop-impl-*.jar cadabra-shop.jar
ENTRYPOINT ["java","-jar","/cadabra-shop.jar"]
