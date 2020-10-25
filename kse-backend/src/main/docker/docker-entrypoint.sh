#!/bin/bash

set -e

cd /home/app

exec gosu app:app java \
  -Djava.security.egd=file:/dev/./urandom \
  -Duser.timezone="Asia/Shanghai" \
  -Duser.language="zh" \
  -Duser.country="CN" \
  -Djava.io.tmpdir=/home/app/tmp \
  org.springframework.boot.loader.JarLauncher \
  "$@"