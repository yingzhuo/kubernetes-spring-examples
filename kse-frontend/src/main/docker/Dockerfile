# ----------------------------------------------------------------------------------------------------------------------
# 预构建
# ----------------------------------------------------------------------------------------------------------------------
FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/springboot:8 as builder
COPY *.jar /opt/app.jar
RUN java -Djarmode=layertools -jar /opt/app.jar extract && \
    rm -rf /opt/dependencies/BOOT-INF/lib/spring-boot-jarmode-layertools-*.jar && \
    rm -rf /opt/application/BOOT-INF/classpath.idx && \
    rm -rf /opt/application/BOOT-INF/layers.idx

# ----------------------------------------------------------------------------------------------------------------------
# 构建
# ----------------------------------------------------------------------------------------------------------------------
FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/springboot:8

COPY --from=builder /opt/dependencies/ ./
COPY --from=builder /opt/spring-boot-loader/ ./
COPY --from=builder /opt/snapshot-dependencies/ ./
COPY --from=builder /opt/internal-dependencies/ ./
COPY --from=builder /opt/application/ ./

ENV APP_PROFILES=k8s

EXPOSE 7777 8443