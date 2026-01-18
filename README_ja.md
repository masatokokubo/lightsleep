# Lightsleep

[English](README.md)

Lightsleepは、軽量のO/Rマッピング･ライブラリで、Java 8以降で利用できます。

### <small>特徴</small>

- Java 8で追加された機能(関数型インタフェース、Optionalクラス)を使用したAPI 。
- SQLの構築で使用するメソッド名がSQLの予約語と同じため、直観的に理解しやすい。
- J2EEが不要(Java RuntimeとJDBCドライバー以外に依存するライブラリがない)なため、適用範囲が広い。
- XMLファイル等によるテーブルとJavaクラスとのマッピング用の定義ファイルは不要。
- ライブラリがコンパクトなため学習が容易。
- 各種のDBMSに同時に接続可能。
- 各種のコネクション･プール･ライブラリを同時に使用可能。
- 内部ログを各種のロギング･ライブラリから選択して出力可能。

### <small>対応</small>DBMS

- MariaDB
- MySQL
- Oracle Database
- PostgreSQL
- SQLite
- Microsoft SQL Server
- 標準SQL準拠DBMS

#### build.gradle<small>での依存関係の記述例</small>

```gradle
// build.gradle
repositories {
    ...
    maven { url 'https://jitpack.io' }
    ...
}

dependencies {
    ...
    implementation 'com.github.masatokokubo:lightsleep:4.1.0'
    ...
}
```

### Lightsleep<small>で使用するエンティティ･クラスの定義例</small>

```java
// Contact.java
package org.lightsleep.example.java.entity;
import java.sql.Date;
import java.sql.Timestamp;
import org.lightsleep.entity.*;

public class Contact {
    @Key
    public int       id;
    public String    lastName;
    public String    firstName;
    public LocalDate birthday;

    @Insert("0") @Update("{updateCount}+1")
    public int updateCount;

    @Insert("CURRENT_TIMESTAMP") @NonUpdate
    public LocalDateTime createdTime;

    @Insert("CURRENT_TIMESTAMP") @Update("CURRENT_TIMESTAMP")
    public LocalDateTime updatedTime;
}
```

### Lightsleep<small>の使用例</small>

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .or   ("{lastName}={}", "Orange")
        .orderBy("{lastName}")
        .orderBy("{firstName}")
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' OR lastName='Orange'
  ORDER BY lastName ASC, firstName ASC
```

### <small>ライセンス</small>

[The MIT License (MIT)](LICENSE.txt)

<div style="color:gray"><i>(C) 2015 Masato Kokubo (<small>小久保 雅人</small>)</i></div>

### <small>ドキュメント</small>

[<small>変更履歴</small>](CHANGELOG_ja.md)

[<small>チュートリアル</small>](Tutorial_ja.md)

[<small>ユーザー･ガイド</small>](UserGuide_ja.md)

[API仕様](http://masatokokubo.github.io/Lightsleep-java/javadoc_ja/index.html)

### Qiita<small>記事</small>

[Java RuntimeとJDBCドライバーだけで動作するO/Rマッピング･ライブラリLightsleepの紹介](http://qiita.com/MasatoKokubo/items/ab46696b203d7f67036c)

[Java 8用O/Rマッピング･ライブラリLightsleepの柔軟なデータ型変換の仕組み](http://qiita.com/MasatoKokubo/items/1080d1277e2b51d88f89)
