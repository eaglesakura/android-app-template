# Android app template.

## このリポジトリについて

[eglibrary-framework](https://github.com/eaglesakura/eglibrary)を使ったアプリ開発のテンプレートです。

アプリ開発におおよそ必要となる構成のテンプレートを作成しておき、すぐに本質的な開発に移れるようにします。

フレームワークを使う場合に読んでおくべきURL

 * [俺的Androidアプリ開発の基本方針 ver 2016.Q3](http://qiita.com/eaglesakura/items/7b5e61186c0c754c03b6)
  * 及び[リンクされている記事](http://qiita.com/eaglesakura/items/7b5e61186c0c754c03b6#%E8%AA%AD%E3%82%93%E3%81%A7%E3%81%8A%E3%81%8F%E3%81%A8%E8%89%AF%E3%81%84%E5%8F%82%E8%80%83%E8%A8%98%E4%BA%8B)

| 想定される開発ブランチ | 内容 | ビルドステータス |
|---|---|---|
| master | 最新ビルド（Nightly Build対象） | [![CircleCI](https://circleci.com/gh/eaglesakura/android-app-template/tree/master.svg?style=svg)](https://circleci.com/gh/eaglesakura/android-app-template/tree/master) |
| develop | 最新の開発版, DEBUG版デプロイ用 | - |
| feature/id/{issue num} | issue対応 | - |
| v${バージョン名} | リリースビルド | - |

## 環境変数設定

| 環境変数名 | 内容 | 備考 |
|---|---|---|
| IMGMAGICK_CONVERT | ImageMagick convertコマンドパス | mipmap出力を行う場合 |
| IMGMAGICK_IDENTIFY | ImageMagick identifyコマンドパス | mipmap出力を行う場合 |
| DEPLOYGATE_USER_NAME | CIからDeploygateへDeployする場合のアカウント名 | CIのみ |
| DEPLOYGATE_API_KEY | CIからDeploygateへDeployする場合のAPIキー | CIのみ |
| SLACK_HOOK_URL | CIからSlack通知を行う場合のURL | CIのみ |

## アプリ開発の開始

このプロジェクトをcloneもしくはコピーし、アプリpackage名を適宜書き換えてください。

サンプルで入っている `ExampleActivity` は参考であるため、削除しても問題ありません。

* 参考手順

```
# アプリをcloneする
git clone git@github.com:eaglesakura/android-app-template.git

# ディレクトリ名を任意に変更する
mv android-app-template/ my-application/
cd my-application/

# Remote Repositoryを変更する
git remote rm origin
git remote add origin git@your-repository.git
```

## ビルド方法

コマンドラインで下記のコマンドを実行することで、アプリをビルドすることができます。

ただし、署名鍵等はダミーファイルがコミットされているため、Google PlayやDeploygateにアップロードされているapkを生成することはできません。

署名ファイルやAPIキー等の重要情報は "app/private"配下に管理されます。ダミーファイルは"app/private.tmp"に保存されていますので、そのファイルを"app/private"配下にコピーする（もしくは"script/developer-install-private.sh"を実行することで所定の位置へファイルを移動できます。

アプリの動作確認は可能です。

### 必要環境

 1. JDK 1.8
 1. Cygwin(Windows環境の場合) / Terminal(Mac/Ubuntu環境の場合)
 1. Android SDK(可能な限り最新版)
 1. 環境変数: ANDROID_HOME

### ビルド手順

<pre>

# Android SDKの内容を同期する
sh -c "$(curl -fsSL https://raw.githubusercontent.com/eaglesakura/build-dependencies/master/android-sdk.sh)"

# リポジトリをcloneしてビルド用ブランチへ移動する
git checkout develop

# スクリプトに実行権限を付与する
chmod 755 ./script/developer-install-private.sh
chmod 755 ./gradlew

# private.tmpをapp/privateにコピーする
./script/developer-install-private.sh

# ビルドを行う
./gradlew --refresh-dependencies assemble
</pre>

## ライセンス

MITライセンスで配布します。このコードを一部、もしくは全部を利用した場合はライセンスにしたがって表記を行ってください。
