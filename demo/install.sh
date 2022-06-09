#! /bin/bash

# 默认或-d 安装debug包
# -r 安装release包

if [ "$#" -lt 1 ] || [ "$1" == "-d" ]; then
  echo "======Debug包 begin 🚀️========"
  ../gradlew assembleDebug --stacktrace
  adb install -r ./build/outputs/apk/debug/demo-debug.apk

elif  [ "$1" == "-r" ]; then
  echo "======Release包 begin 🚀========"
  ../gradlew assembleRelease --stacktrace
  adb install -r ./build/outputs/apk/release/demo-release.apk
fi

adb shell am start -n "com.cyd.demo/com.cyd.demo.MainActivity"