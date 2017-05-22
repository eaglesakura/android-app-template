#! /bin/sh -eu

# ビルド
# ビルド対象のコマンドは適宜設定する
./gradlew \
          :app:assembleDevelopDebug \
          :app:assembleProductRelease \
          :app:assembleDevelopDebugAndroidTest \
          ciCollectAndroidApps
#          :app:assembleGoogleplayDebug :app:assembleGoogleplayRelease
