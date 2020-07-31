# 阶段 (一)
FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/springboot:8 as builder
WORKDIR /tmp
COPY ./*.jar app.jar
RUN java -Djarmode=layertools -jar /tmp/app.jar extract && \
    rm -rf /tmp/dependencies/BOOT-INF/lib/spring-boot-jarmode-layertools-*.jar && \
    rm -rf /tmp/application/BOOT-INF/classpath.idx && \
    rm -rf /tmp/application/BOOT-INF/layers.idx

# 阶段 (二)
FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/springboot:8

COPY --from=builder /tmp/dependencies/ ./
COPY --from=builder /tmp/spring-boot-loader/ ./
COPY --from=builder /tmp/snapshot-dependencies/ ./
COPY --from=builder /tmp/internal-dependencies/ ./
COPY --from=builder /tmp/application/ ./

ENV APP_PROFILES=k8s

EXPOSE 8080