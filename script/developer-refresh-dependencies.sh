#! /bin/sh

###############################################
# ビルドに必要な依存環境を取得するスクリプト。
# 依存関係の同期を伴うため、非常に時間がかかる
###############################################
echo "sync submodules"
if [ -e ".gitmodules" ]; then
    git submodule update --init
fi

echo "update Android SDK"
sh -c "$(curl -fsSL https://raw.githubusercontent.com/eaglesakura/build-dependencies/master/android-sdk.sh)"

./gradlew --refresh-dependencies clean
