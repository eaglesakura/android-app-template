#! /bin/sh


report_cp() {

mkdir "$CIRCLE_ARTIFACTS/app"
mkdir "$CIRCLE_TEST_REPORTS/junit/"
mkdir "$CIRCLE_TEST_REPORTS/junit/$1"

cp -r ./app/build/reports "$CIRCLE_ARTIFACTS/app"
find . -type f -regex ".*/build/test-results/$1/.*xml" -exec cp {} $CIRCLE_TEST_REPORTS/junit/$1/ \;
}

# テスト実行
./gradlew \
          :app:testTerminalDebugUnitTest


if [ $? -ne 0 ]; then
    echo "UnitTest failed..."
    report_cp "testTerminalDebugUnitTest"
    exit 1
else
    report_cp
fi
