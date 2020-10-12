# ----------------------------------------------------------------------------------------------------------------------
# 预构建
# ----------------------------------------------------------------------------------------------------------------------
ARG BASE_IMG=registry.cn-shanghai.aliyuncs.com/yingzhuo/springboot:8

FROM $BASE_IMG AS builder

COPY *.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract && \
    rm -rf /home/spring/dependencies/BOOT-INF/lib/spring-boot-jarmode-layertools-*.jar && \
    rm -rf /home/spring/application/BOOT-INF/classpath.idx && \
    rm -rf /home/spring/application/BOOT-INF/layers.idx

# ----------------------------------------------------------------------------------------------------------------------
# 构建
# ----------------------------------------------------------------------------------------------------------------------
FROM $BASE_IMG

LABEL maintainer="应卓 <yingzhor@gmail.com>" \
      description="本项目用来各种实验" \
      github-url="https://github.com/yingzhuo/kubernetes-spring-examples"

COPY --from=builder /home/spring/dependencies/ ./
COPY --from=builder /home/spring/spring-boot-loader/ ./
COPY --from=builder /home/spring/snapshot-dependencies/ ./
COPY --from=builder /home/spring/internal-dependencies/ ./
COPY --from=builder /home/spring/application/ ./

ENV SPRING_PROFILES_ACTIVE=k8s

EXPOSE 8080