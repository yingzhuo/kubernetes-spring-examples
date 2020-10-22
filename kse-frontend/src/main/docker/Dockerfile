# ----------------------------------------------------------------------------------------------------------------------
# 预构建
# ----------------------------------------------------------------------------------------------------------------------
ARG BASE_IMG=registry.cn-shanghai.aliyuncs.com/yingzhuo/java:jre-8

FROM $BASE_IMG AS builder

COPY *.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract && \
    rm -rf /home/java/dependencies/BOOT-INF/lib/java-boot-jarmode-layertools-*.jar && \
    rm -rf /home/java/application/META-INF/maven && \
    rm -rf /home/java/application/BOOT-INF/classpath.idx && \
    rm -rf /home/java/application/BOOT-INF/layers.idx

# ----------------------------------------------------------------------------------------------------------------------
# 构建
# ----------------------------------------------------------------------------------------------------------------------
FROM $BASE_IMG

LABEL maintainer="应卓 <yingzhor@gmail.com>" \
      description="本项目用来各种实验" \
      github-url="https://github.com/yingzhuo/kubernetes-spring-examples"

COPY --from=builder /home/java/dependencies/ ./
COPY --from=builder /home/java/spring-boot-loader/ ./
COPY --from=builder /home/java/snapshot-dependencies/ ./
COPY --from=builder /home/java/internal-dependencies/ ./
COPY --from=builder /home/java/application/ ./
COPY --chown=root:root ./docker-entrypoint.sh /bin/entrypoint

RUN chmod 550 /bin/entrypoint

ENV SPRING_PROFILES_ACTIVE=k8s

ENTRYPOINT ["entrypoint"]

EXPOSE 7777 8443