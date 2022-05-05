# CraftTools

内部用プラグイン

## 使い方

* 埋めたい穴高さに埋めたいブロックを置きます
* その上に載って`/tools fill`を打ちます
* `/tools redo`で巻き戻せます

### 注意
* サイズによって時間がかかります
* ログアウトすると巻き戻しはできません
* 巻き戻しは5回までです

## 開発環境

- IntelliJ IDEA
- Adoptium Temurin JDK 17
- Paper 1.18.2
- Gradle
- Spotless

## 前提プラグイン

## ビルド

ビルドの前に、Gradleでタスク`copyPatchedPaperJar`を実行してください。成功したら`libs`ディレクトリに`paper-1.18.2.jar`というファイルが生成されます。

そのあと、Gradleでタスク`build`を実行してください。成功したら`build/libs/`に

- `Cardinal-x.x.x-SNAPSHOT.jar`
- `Cardinal-x.x.x-SNAPSHOT-all.jar`

が生成されます。Paperに導入するには、依存しているパッケージなどが含まれた`all`の方のjarファイルを使ってください。

ビルドしたときに、`spotless`から始まるタスクでエラーが起きたときは、タスク`spotlessApply`実行してみてください。

## サーバーへの導入

前提条件:

- Java 17
- Paper

1. `plugins`にビルドされたjarファイルを入れてください。
2. サーバーを実行してください。

GLHF ✨

## Commit message style

Angularの[Commit Message Format](https://github.com/angular/angular/blob/master/CONTRIBUTING.md#commit)を採用しています。
1コミットはできるだけ最小限にしてください。

## Branch Model

[Git-flow](https://qiita.com/KosukeSone/items/514dd24828b485c69a05)を採用しています。
MergeするときにはPull Request(PR)を作成してください。
