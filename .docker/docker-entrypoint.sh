#!/usr/bin/env bash

set -e

if [[ "${SPRING_PROFILES_ACTIVE}" == "" ]]; then
  echo "[ERROR]: 环境变量'SPRING_PROFILES_ACTIVE'没有指定"
  exit 1
fi

if [[ "${APP_TIMEZONE}" == "" ]]; then
  echo "[ERROR]: 环境变量'APP_TIMEZONE'没有指定"
  exit 1
fi

if [[ "${APP_LANG}" == "" ]]; then
  echo "[ERROR]: 环境变量'APP_LANG'没有指定"
  exit 1
fi

if [[ "${APP_COUNTRY}" == "" ]]; then
  echo "[ERROR]: 环境变量'APP_COUNTRY'没有指定"
  exit 1
fi

exec java \
  -Djava.security.egd=file:/dev/./urandom \
  -Duser.timezone="${APP_TIMEZONE}" \
  -Duser.language="${APP_LANG}" \
  -Duser.country="${APP_COUNTRY}" \
  org.springframework.boot.loader.JarLauncher \
  "$@"

exit 0
