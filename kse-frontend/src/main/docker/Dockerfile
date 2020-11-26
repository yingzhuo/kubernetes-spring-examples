# ----------------------------------------------------------------------------------------------------------------------
# 预构建
# ----------------------------------------------------------------------------------------------------------------------
ARG BASE_IMG=registry.cn-shanghai.aliyuncs.com/yingzhuo/java:jre-8

FROM $BASE_IMG AS builder

WORKDIR /opt

COPY *.jar /opt/app.jar

RUN java -Djarmode=layertools -jar /opt/app.jar extract && \
    rm -rf /opt/dependencies/BOOT-INF/lib/java-boot-jarmode-layertools-*.jar && \
    rm -rf /opt/application/META-INF/maven && \
    rm -rf /opt/application/BOOT-INF/classpath.idx && \
    rm -rf /opt/application/BOOT-INF/layers.idx

# ----------------------------------------------------------------------------------------------------------------------
# 构建
# ----------------------------------------------------------------------------------------------------------------------
FROM $BASE_IMG

LABEL maintainer="应卓 <yingzhor@gmail.com>"

WORKDIR /opt

COPY --from=builder /opt/dependencies/ ./
COPY --from=builder /opt/spring-boot-loader/ ./
COPY --from=builder /opt/snapshot-dependencies/ ./
COPY --from=builder /opt/internal-dependencies/ ./
COPY --from=builder /opt/application/ ./
COPY --chown=root:root ./docker-entrypoint.sh /bin/entrypoint

HEALTHCHECK --interval=10s --timeout=30s --start-period=3s --retries=3 CMD curl --fail http://localhost:7777/actuator/info || exit 1

RUN chmod +x /bin/entrypoint

ENV SPRING_PROFILES_ACTIVE=k8s

ENTRYPOINT ["entrypoint"]

EXPOSE 7777 8443