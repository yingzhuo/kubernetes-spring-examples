# ----------------------------------------------------------------------------------------------------------------------
# 预构建
# ----------------------------------------------------------------------------------------------------------------------
FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/springboot:8 AS builder

COPY *.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract && \
    rm -rf /opt/dependencies/BOOT-INF/lib/spring-boot-jarmode-layertools-*.jar && \
    rm -rf /opt/application/BOOT-INF/classpath.idx && \
    rm -rf /opt/application/BOOT-INF/layers.idx

# ----------------------------------------------------------------------------------------------------------------------
# 构建
# ----------------------------------------------------------------------------------------------------------------------
FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/springboot:8

ARG VERSION=unknown

LABEL maintainer="应卓 <yingzhor@gmail.com>" \
      version=${VERSION} \
      description="本项目用来各种实验" \
      github-url="https://github.com/yingzhuo/kubernetes-spring-examples"

COPY --from=builder /opt/dependencies/ ./
COPY --from=builder /opt/spring-boot-loader/ ./
COPY --from=builder /opt/snapshot-dependencies/ ./
COPY --from=builder /opt/internal-dependencies/ ./
COPY --from=builder /opt/application/ ./

ENV APP_PROFILES=k8s \
    APP_VERSION=${VERSION}

EXPOSE 8080