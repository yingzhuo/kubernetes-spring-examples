#!/bin/bash

set -e

cd /home/java

exec gosu java:java java \
  -Djava.security.egd=file:/dev/./urandom \
  -Duser.timezone="Asia/Shanghai" \
  -Duser.language="zh" \
  -Duser.country="CN" \
  -Djava.io.tmpdir=/home/java/tmp \
  org.springframework.boot.loader.JarLauncher \
  "$@"
