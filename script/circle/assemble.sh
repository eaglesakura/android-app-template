#! /bin/sh -eu

# ビルド
# ビルド対象のコマンドは適宜設定する
./gradlew \
          :app:assembleTerminalDebug \
          :app:assembleTerminalRelease \
          :app:assembleTerminalDebugAndroidTest \
          ciCollectAndroidApps
#          :app:assembleGoogleplayDebug :app:assembleGoogleplayRelease
