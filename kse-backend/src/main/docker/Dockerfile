# 阶段 (一)

FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/java:jre-8 as builder
WORKDIR /tmp
COPY ./*.jar layed.jar
RUN java -Djarmode=layertools -jar /tmp/layed.jar extract && \
    rm -rf /tmp/dependencies/BOOT-INF/lib/spring-boot-jarmode-layertools-*.jar

# 阶段 (二)
FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/java:jre-8

LABEL MAINTAINER="应卓 <yingzhor@gmail.com>"

WORKDIR /opt/

COPY --from=builder /tmp/dependencies/ ./
COPY --from=builder /tmp/spring-boot-loader/ ./
COPY --from=builder /tmp/snapshot-dependencies/ ./
COPY --from=builder /tmp/kotlin-dependencies/ ./
COPY --from=builder /tmp/internal-dependencies/ ./
COPY --from=builder /tmp/application/ ./
COPY ./docker-entrypoint.sh ./docker-entrypoint.sh

ENTRYPOINT ["bash", "./docker-entrypoint.sh"]

ENV SPRING_PROFILES_ACTIVE=k8s \
    APP_TIMEZONE=Asia/Shanghai \
    APP_COUNTRY=CN \
    APP_LANG=zh

EXPOSE 8080