#! /bin/sh -eu

# ビルド
# ビルド対象のコマンドは適宜設定する
./gradlew -PpreDexEnable=false -Pcom.android.build.threadPoolSize=1  \
          :app:assemble \
          ciCollectAndroidApps
#          :app:assembleGoogleplayDebug :app:assembleGoogleplayRelease
