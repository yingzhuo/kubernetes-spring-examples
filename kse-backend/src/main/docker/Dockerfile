# ----------------------------------------------------------------------------------------------------------------------
# 预构建
# ----------------------------------------------------------------------------------------------------------------------
ARG BASE_IMG=registry.cn-shanghai.aliyuncs.com/yingzhuo/java:jre-8

FROM $BASE_IMG AS builder

COPY *.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract && \
    rm -rf /home/app/dependencies/BOOT-INF/lib/java-boot-jarmode-layertools-*.jar && \
    rm -rf /home/app/application/META-INF/maven && \
    rm -rf /home/app/application/BOOT-INF/classpath.idx && \
    rm -rf /home/app/application/BOOT-INF/layers.idx

# ----------------------------------------------------------------------------------------------------------------------
# 构建
# ----------------------------------------------------------------------------------------------------------------------
FROM $BASE_IMG

LABEL maintainer="应卓 <yingzhor@gmail.com>" \
      description="本项目用来各种实验" \
      github-url="https://github.com/yingzhuo/kubernetes-spring-examples"

COPY --from=builder /home/app/dependencies/ ./
COPY --from=builder /home/app/spring-boot-loader/ ./
COPY --from=builder /home/app/snapshot-dependencies/ ./
COPY --from=builder /home/app/internal-dependencies/ ./
COPY --from=builder /home/app/application/ ./
COPY --chown=root:root ./docker-entrypoint.sh /home/app/sbin/entrypoint

RUN chmod +x /home/app/sbin/entrypoint

ENV SPRING_PROFILES_ACTIVE=k8s

ENTRYPOINT ["entrypoint"]

EXPOSE 8080