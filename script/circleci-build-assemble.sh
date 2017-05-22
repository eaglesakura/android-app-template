#! /bin/sh -eu

# ビルド
# ビルド対象のコマンドは適宜設定する
./gradlew \
          :app:assembleStudioDebug \
          :app:assembleProductRelease \
          ciCollectAndroidApps
#          :app:assembleGoogleplayDebug :app:assembleGoogleplayRelease
