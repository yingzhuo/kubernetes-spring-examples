#!/bin/bash

set -e

exec java \
  -Djava.security.egd=file:/dev/./urandom \
  -Duser.timezone="${APP_TIMEZONE}" \
  -Duser.language="${APP_LANG}" \
  -Duser.country="${APP_COUNTRY}" \
  org.springframework.boot.loader.JarLauncher \
  "$@"

exit 0
