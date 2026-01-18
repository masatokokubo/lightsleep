# Lightsleep チュートリアル

[English](Tutorial.md)

テーブルの行を取得してコンソールに出力する簡単なプログラムを作成してみます。

#### 1. テーブルの準備

MariaDB, MySQL, Oracle, PostgreSQL, SQLiteまたはSQL ServerのいずれかのデータベースにContactテーブルを作成し、サンプルデータを挿入します。

以下のSQLのいずれかを実行してテーブルを作成します。

```sql
-- MariaDB, MySQL
CREATE TABLE Contact (
    id         INT         NOT NULL AUTO_INCREMENT,
    firstName  VARCHAR(20) NOT NULL,
    lastName   VARCHAR(20) NOT NULL,
    birthday   DATE            NULL,

    PRIMARY KEY(id)
);
```

```sql
-- Oracle
CREATE TABLE Contact (
    id        NUMBER   (9) GENERATED ALWAYS AS IDENTITY,
    firstName VARCHAR2(20 CHAR) NOT NULL,
    lastName  VARCHAR2(20 CHAR) NOT NULL,
    birthday  DATE                  NULL,

    PRIMARY KEY(id)
);
```

```sql
-- PostgreSQL
CREATE TABLE Contact (
    id        SERIAL      NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName  VARCHAR(20) NOT NULL,
    birthday  DATE            NULL,

    PRIMARY KEY(id)
);
```

```sql
-- SQLite
CREATE TABLE Contact (
    id        INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName TEXT    NOT NULL,
    lastName  TEXT    NOT NULL,
    birthday  TEXT        NULL,
);
```

```sql
-- SQL Server
CREATE TABLE Contact (
    id        INT IDENTITY(1,1) NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName  VARCHAR(20) NOT NULL,
    birthday  DATE            NULL,

    PRIMARY KEY(id)
);
```

以下のSQLを実行してテーブルにデータを挿入します。

```sql
DELETE FROM Contact;
INSERT INTO Contact VALUES ('First' , 'Example', DATE'1991-01-01');
INSERT INTO Contact VALUES ('Second', 'Example', DATE'1992-02-02');
INSERT INTO Contact VALUES ('Third' , 'Example', DATE'1993-03-03');
INSERT INTO Contact VALUES ('Fourth', 'Example', DATE'1994-04-04');
```

#### 2. エンティティ･クラスの作成

Contactテーブルから取得した行を保持するためのエンティティ･クラスを作成します。

```java
// Contact.java
package org.lightsleep.tutorial.entity;

import java.time.LocalDate;

import org.lightsleep.Sql;
import org.lightsleep.connection.ConnectionWrapper;
import org.lightsleep.entity.Key;
import org.lightsleep.entity.NonInsert;
import org.lightsleep.entity.PostInsert;

public class Contact implements PostInsert {
    @Key
    @NonInsert
    public int id;
    public String firstName;
    public String lastName;
    public LocalDate birthday;

    public static Contact of(String firstName, String lastName, int birthYear, int birthMonth, int birthDay) {
        Contact contact = new Contact();
        contact.firstName = firstName;
        contact.lastName = lastName;
        contact.birthday = LocalDate.of(birthYear, birthMonth, birthDay);
        return contact;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void postInsert(ConnectionWrapper conn) {
        new Sql<>(Contact.class)
            .columns("id")
            .where("id=",
                new Sql<>(Contact.class)
                    .columns("id")
                    .expression("id", "MAX({id})")
            )
            .connection(conn)
            .select(entity -> id = entity.id);
    }
}
```

#### 3. プロパティ･ファイルの準備

下記の`lightsleep.properties`ファイルを作成しクラス･パスのいずれかにおいてください。`url`, `user`および`password`の値は、使用するデータベース環境に合わせて変更してください。

```properties
# lightsleep.properties - MariaDB
url      = jdbc:mariadb://<データベース・サーバー>:<ポート番号>/<データベース>
user     = <ユーザー名>
password = <パスワード>
```

```properties
# lightsleep.properties - MySQL
url      = jdbc:mysql://<データベース・サーバー>:<ポート番号>/<データベース>
user     = <ユーザー名>
password = <パスワード>
```

```properties
# lightsleep.properties - Oracle
url      = jdbc:oracle:thin:@<データベース・サーバー>:<ポート番号>:<SID>
user     = <ユーザー名>
password = <パスワード>
```

```properties
# lightsleep.properties - PostgreSQL
url      = jdbc:postgresql://<データベース・サーバー>:<ポート番号>/<データベース>
user     = <ユーザー名>
password = <パスワード>
```

```properties
# lightsleep.properties - SQLite
url = jdbc:sqlite:<データベース・ファイル・パス>
```

```properties
# lightsleep.properties - SQL Server
url      = jdbc:sqlserver://<データベース・サーバー>:<ポート番号>;Database=<データベース>
user     = <ユーザー名>
password = <パスワード>
```

#### 4. データの取得
テーブルから全行を取得するプログラムを作成します。

```java
// Example1.java
package org.lightsleep.tutorial;

import java.util.ArrayList;
import java.util.List;

import org.lightsleep.Sql;
import org.lightsleep.Transaction;
import org.lightsleep.component.Condition;
import org.lightsleep.tutorial.entity.Contact;

public class Example1 {
    public static void main(String[] args) {
        try {
            Transaction.execute(conn -> {
                new Sql<>(Contact.class)
                    .where(Condition.ALL)
                    .connection(conn)
                    .delete();

                new Sql<>(Contact.class)
                    .connection(conn)
                    .insert(Contact.of("Yukari", "Apple", 2001, 1, 1));

                new Sql<>(Contact.class)
                    .connection(conn)
                    .insert(Contact.of("Azusa", "Apple", 2002, 2, 2));

                new Sql<>(Contact.class)
                    .connection(conn)
                    .insert(Contact.of("Chiyuki", "Apple", 2003, 3, 3));
            });

            List<Contact> contacts = new ArrayList<>();
            Transaction.execute(conn -> {
                new Sql<>(Contact.class)
                    .connection(conn)
                    .select(contacts::add);
            });

            for (int index = 0; index < contacts.size(); ++index) {
                Contact contact = contacts.get(index);
                System.out.println(
                    index
                    + ": Name: " + contact.firstName + " " + contact.lastName
                    + ", Birthday: " + contact.birthday
                );
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Example1 を実行すると以下がコンソールに表示されます。

```
0: Name: Yukari Apple, Birthday: 2001-01-01
1: Name: Azusa Apple, Birthday: 2002-02-02
2: Name: Chiyuki Apple, Birthday: 2003-03-03
```

<div style="color:gray"><i>(C) 2015 Masato Kokubo (<small>小久保 雅人</small>)</i></div>
