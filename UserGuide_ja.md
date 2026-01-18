# Lightsleep <small>4.0.0 ユーザーガイド</small>

[English](UserGuide.md)

### 目次

[1. パッケージ](#1-パッケージ)  
[2. エンティティクラスの作成](#2-エンティティクラスの作成)  
[2-1. エンティティクラスで使用するアノテーション](#2-1-エンティティクラスで使用するアノテーション)  
[2-1-1. @Table](#2-1-1-table)  
[2-1-2. @Key](#2-1-2-key)  
[2-1-3. @Column](#2-1-3-column)  
[2-1-4. @ColumnType](#2-1-4-columntype)  
[2-1-5. @NonColumn](#2-1-5-noncolumn)  
[2-1-6. @NonSelect](#2-1-6-nonselect)  
[2-1-7. @NonInsert](#2-1-7-noninsert)  
[2-1-8. @NonUpdate](#2-1-8-nonupdate)  
[2-1-9. @Select](#2-1-9-select)  
[2-1-10. @Insert](#2-1-10-insert)  
[2-1-11. @Update](#2-1-11-update)  
[2-1-12. @KeyProperty, @ColumnProperty, ... @UpdateProperty](#2-1-12-keyproperty-columnproperty--updateproperty)  
[2-2. エンティティクラスで実装するインターフェース](#2-2-エンティティクラスで実装するインターフェース)  
[2-2-1. PreInsert<small>インターフェース</small>](#2-2-1-preinsertインターフェース)  
[2-2-2. PreUpdate<small>インターフェース</small>](#2-2-2-preupdateインターフェース)  
[2-2-3. PreDelete<small>インターフェース</small>](#2-2-3-predeleteインターフェース)  
[2-2-4. PostInsert<small>インターフェース</small>](#2-2-4-postinsertインターフェース)  
[2-2-5. PostUpdate<small>インターフェース</small>](#2-2-5-postupdateインターフェース)  
[2-2-6. PostDelete<small>インターフェース</small>](#2-2-6-postdeleteインターフェース)  
[2-2-7. PostSelect<small>インターフェース</small>](#2-2-7-postselectインターフェース)  
[3. lightsleep.propertiesファイルの定義](#3-lightsleeppropertiesの定義)  
[3-1. ログライブラリクラス](#3-1-ログライブラリクラスの指定)  
[3-2. データベースハンドラクラス](#3-2-データベースハンドラクラス)  
[3-3. コネクションサプライヤクラス](#3-3-コネクションサプライヤクラス)  
[4. トランザクション](#4-トランザクション)  
[5. SQLの実行](#5-sqlの実行)  
[5-1. SELECT](#5-1-select)  
[5-1-1. SELECT 1行/式条件](#5-1-1-select-1行式条件)  
[5-1-2. SELECT 1行/エンティティ条件](#5-1-2-select-1行エンティティ条件)  
[5-1-3. SELECT 複数行/式条件](#5-1-3-select-複数行式条件)  
[5-1-4. SELECT サブクエリ条件](#5-1-4-select-サブクエリ条件)  
[5-1-5. SELECT 式条件/AND](#5-1-5-select-式条件and)  
[5-1-6. SELECT 式条件/OR](#5-1-6-select-式条件or)  
[5-1-7. SELECT 式条件/SELECT 式条件/A AND B OR C AND D](#5-1-7-select-式条件a-and-b-or-c-and-d)  
[5-1-8. SELECTカラムの選択](#5-1-8-selectカラムの選択)  
[5-1-9. SELECT GROUP BY, HAVING](#5-1-9-select-group-by-having)  
[5-1-10. SELECT ORDER BY, OFFSET, LIMIT](#5-1-10-select-order-by-offset-limit)  
[5-1-11. SELECT FOR UPDATE](#5-1-11-select-for-update)  
[5-1-12. SELECT 内部結合](#5-1-12-select-内部結合)  
[5-1-13. SELECT 左外部結合](#5-1-13-select-左外部結合)  
[5-1-14. SELECT 右外部結合](#5-1-14-select-右外部結合)  
[5-1-15. SELECT COUNT(*)](#5-1-15-select-count)  
[5-1-16. SELECT FROM句サブクエリ](#5-1-16-select-from句サブクエリ)  
[5-1-17. SELECT UNION, UNION ALL](#5-1-17-select-union-union-all)  
[5-1-18. SELECT WITH句](#5-1-18-select-with句)  
[5-1-19. SELECT RECURSIVE](#5-1-19-select-recursive)  
[5-2. INSERT](#5-2-insert)  
[5-2-1. INSERT 1行](#5-2-1-insert-1行)  
[5-2-2. INSERT 複数行](#5-2-2-insert-複数行)  
[5-3. UPDATE](#5-3-update)  
[5-3-1. UPDATE 1行](#5-3-1-update-1行)  
[5-3-2. UPDATE 複数行](#5-3-2-update-複数行)  
[5-3-3. UPDATE 指定条件, カラム選択](#5-3-3-update-指定条件-カラム選択)  
[5-3-4. UPDATE 全行](#5-3-4-update-全行)  
[5-4. DELETE](#5-4-delete)  
[5-4-1. DELETE 1行](#5-4-1-delete-1行)  
[5-4-2. DELETE 複数行](#5-4-2-delete-複数行)  
[5-4-3. DELETE 指定条件](#5-4-3-delete-指定条件)  
[5-4-4. DELETE 全行](#5-4-4-delete-全行)  
[6. 式の変換処理](#6-式の変換処理)  
[7. 日時型おけるJava型とカラム型の対応](#7-日時型おけるjava型とカラム型の対応)  
[7-1. MariaDB, MySQL](#7-1-mariadb-mysql)  
[7-2. Oracle](#7-2-oracle)  
[7-3. PostgreSQL](#7-3-postgresql)  
[7-4. SQLite](#7-4-sqlite)  
[7-5. SQL Server](#7-5-sql-server)  

[目次へ](#目次)

### 1. パッケージ

以下のパッケージがあります。

&emsp;パッケージ一覧
|パッケージ|含まれるクラス/インタフェース
|:-------|:----------------------
|`org.lightsleep`                |主に使用するクラス
|`org.lightsleep.component`      |条件や式などのSQLの構成要素を作成する際に使用するクラス
|`org.lightsleep.connection`     |各種コネクションプールライブラリを利用してコネクションラッパークラスを供給するクラス
|`org.lightsleep.database`       |各種DBMS用のSQLを生成するクラス
|`org.lightsleep.database.anchor`|JDBC URLに含まれる文字列と`org.lightsleep.database`パッケージのクラスとの対応付けで使用されるクラス
|`org.lightsleep.entity`         |エンティティクラスを作成する際に使用するアノテーションクラスおよびインタフェース
|`org.lightsleep.helper`         |ライブラリ内部で使用される補助的なクラス
|`org.lightsleep.logger`         |各種ロギングライブラリを利用してライブラリ内部のログを出力するクラス

[目次へ](#目次)

### 2. エンティティクラスの作成
データベースのテーブル毎に対応するエンティティクラスを作成します。

#### 2-1. エンティティクラスで使用するアノテーション
Lihgtsleepは、エンティティクラスまたはオブジェクトを引数とするメソッドでは自動的にテーブルとの関連付けを行いますが、エンティティクラスにアノテーションの付与が必要な場合があります。

Lightsleepには、以下のアノテーションがあります。

&emsp;アノテーション一覧
<table>
  <tr>
    <th>アノテーション型</th>
    <th>要素</th>
    <th>示す内容</th>
    <th>付与する対象</th>
  </tr>
  <tr>
    <td><<Entity-Table,<code>@Table</code>>></td>
    <td>String value</td>
    <td>関連するテーブル名</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-Key,<code>@Key</code>>></td>
    <td><code>boolean value</code> (省略値: <code>true</code>)</td>
    <td>プライマリキーに対応</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-Column,<code>@Column</code>>></td>
    <td>String value</td>
    <td>関連するカラムの名前</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-ColumnType,<code>@ColumnType</code>>></td>
    <td>Class<?> value</td>
    <td>関連するカラムの型</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-NonColumn,<code>@NonColumn</code>>></td>
    <td><code>boolean value</code> (省略値: <code>true</code>)</td>
    <td>カラムに関連しない</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-NonSelect,<code>@NonSelect</code>>></td>
    <td><code>boolean value</code> (省略値: <code>true</code>)</td>
    <td>SELECT SQLに使用しない</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-NonInsert,<code>@NonInsert</code>>></td>
    <td><code>boolean value</code> (省略値: <code>true</code>)</td>
    <td>INSERT SQLに使用しない</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-NonUpdate,<code>@NonUpdate</code>>></td>
    <td><code>boolean value</code> (省略値: <code>true</code>)</td>
    <td>UPDATE SQLに使用しない</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-Select,<code>@Select</code>>></td>
    <td>String value</td>
    <td>SELECT SQLで使用する式</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-Insert,<code>@Insert</code>>></td>
    <td>String value</td>
    <td>INSERT SQLで使用する式</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-Update,<code>@Update</code>>></td>
    <td>String value</td>
    <td>UPDATE SQLで使用する式</td>
    <td>フィールド</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@KeyProperty</code>>></td>
    <td>
      <code>String property</code>,
      <code>boolean value</code> (省略値: <code>true</code>)
    </td>
    <td>プライマリキーに対応</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@ColumnProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>String column</code>
    </td>
    <td>関連するカラムの名前</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@ColumnTypeProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>Class<?> type</code>
    </td>
    <td>関連するカラムの型</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@NonColumnProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>boolean value</code> (省略値: <code>true</code>)
    </td>
    <td>カラムに関連しない</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@NonSelectProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>boolean value</code> (省略値: <code>true</code>)
    </td>
    <td>SELECT SQLに使用しない</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@NonInsertProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>boolean value</code> (省略値: <code>true</code>)
    </td>
    <td>INSERT SQLに使用しない</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@NonUpdateProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>boolean value</code> (省略値: <code>true</code>)
    </td>
    <td>UPDATE SQLに使用しない</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@SelectProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>String expression</code>
    </td>
    <td>SELECT SQLで使用する式</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@InsertProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>String expression</code>
    </td>
    <td>INSERT SQLで使用する式</td>
    <td>クラス</td>
  </tr>
  <tr>
    <td><<Entity-XxxxxProperty,<code>@UpdateProperty</code>>></td>
    <td>
      <code>String property</code>,<br>
      <code>String expression</code>
    </td>
    <td>UPDATE SQLで使用する式</td>
    <td>クラス</td>
  </tr>
</table>

[目次へ](#目次)  
<<Entity-Annotation,アノテーション一覧へ>>

##### 2-1-1. @Table
クラスに関連するデータベーステーブル名を示します。
テーブル名がクラス名と同じであれば、このアノテーションを指定する必要はありません。

```java
import org.lightsleep.entity.*;

@Table("Contact")
public class Contact1 extends Contact {
   ...
}
```

`@Table("super")`を指定した場合は、スーパークラスのクラス名がテーブル名となります。

```java
@Table("Contact")
public class Person extends PersonBase {

    @Table("super")
     public static class Ex extends Person {
```

##### 2-1-2. @Key
フィールドに関連するカラムがプライマリーキーの一部である事を示します。

```java
@Key
public int contactId;
@Key
public short featureIndex;
```

##### 2-1-3. @Column
フィールドに関連するデータベースカラム名を示します。
カラム名がフィールド名と同じであれば、このアノテーションを指定する必要がありません。

```java
@Column("firstName")
public String first;
@Column("lastName")
public String last;
```

##### 2-1-4. @ColumnType
フィールドに関連するカラムの型を示します。
フィールド型とカラム型が同種類の場合は、指定する必要がありません。
フィールド型が日付型で、カラム型が数値型のように異なる場合に指定します。

```java
@ColumnType(Long.class)
public LocalDate birthday;
```

[目次へ](#目次)  
<<Entity-Annotation,アノテーション一覧へ>>

##### 2-1-5. @NonColumn
フィールドがどのカラムにも関連しない事を示します。

```java
@NonColumn
public List<Phone> phones;
@NonColumn
public List<Address> addresses;
```

##### 2-1-6. @NonSelect
フィールドに関連するカラムがSELECT SQLで使用されない事を示します。

```java
@NonSelect
public LocalDateTime createdTime;
@NonSelect
public LocalDateTime updatedTime;
```

##### 2-1-7. @NonInsert
フィールドに関連するカラムがINSERT SQLで使用されない事を示します。

```java
@NonInsert
public LocalDateTime createdTime;
@NonInsert
public LocalDateTime updatedTime;
```

##### 2-1-8. @NonUpdate
フィールドに関連するカラムがUPDATE SQLで使用されない事を示します。

```java
@NonUpdate
public LocalDateTime createdTime;
```

[目次へ](#目次)  
<<Entity-Annotation,アノテーション一覧へ>>

##### 2-1-9. @Select
SELECT SQLのカラム名の代わりの式を指定します。

```java
@Select("{firstName}||' '||{lastName}")
@NonInsert@NonUpdate
public String fullName;
```

##### 2-1-10. @Insert
INSERT SQLの挿入値の式を示します。
このアノテーションが指定された場合、フィールドの値は使用されません。

```java
@Insert("CURRENT_TIMESTAMP")
public LocalDateTime createdTime;
@Insert("CURRENT_TIMESTAMP")
public LocalDateTime updatedTime;
```

##### 2-1-11. @Update
UPDATE SQLの更新値の式を示します。
このアノテーションが指定された場合、フィールドの値は使用されません。

```java
@Update("{updateCount}+1")
public int updateCount;
@Update("CURRENT_TIMESTAMP")
public LocalDateTime updatedTime;
```

[目次へ](#目次)  
<<Entity-Annotation,アノテーション一覧へ>>

##### 2-1-12. @KeyProperty, @ColumnProperty, ... @UpdateProperty
これらのアノテーションは、スーパークラスで定義されているフィールドに対して指定する場合に使用します。
指定された内容はサブクラスにも影響しますが、サブクラスでの指定が優先されます。
`value=false`, `column=""`, `type=Void.class`, `expression=""` を指定すると、スーパークラスでの指定が打ち消されます。

```java
@KeyProperty(property="contactId")
@KeyProperty(property="featureIndex")
public class ContactFeature extends ContactFeatureKey {
```

### 2-2. エンティティクラスで実装するインターフェース

[目次へ](#目次)

#### 2-2-1. PreInsert<small>インターフェース</small>
エンティティクラスがこのインターフェースを実装している場合、``Sql<E>``クラスの``insert(E)``および``insert(Iterable)``メソッドからINSERT SQLの実行前に``preInsert``メソッドが呼び出されます。

``preInsert``メソッドを使用して、プライマリーキーの採番の実装をする事ができます。

```java
public abstract class Common implements PreInsert {
    @Key
    public int id;

    @Override
    public void preInsert(ConnectionWrapper conn) {
        id = Numbering.getNewId(conn, getClass());
    }
}
```

[目次へ](#目次)

#### 2-2-2. PreUpdate<small>インターフェース</small>
エンティティクラスがこのインターフェースを実装している場合、``Sql<E>``クラスの``update(E)``および``update(Iterable)``メソッドからUPDATE SQLの実行前に``preUpdate``メソッドが呼び出されます。

[目次へ](#目次)

#### 2-2-3. PreDelete<small>インターフェース</small>
エンティティクラスがこのインターフェースを実装している場合、``Sql<E>``クラスの``delete(E)``および``delete(Iterable)``メソッドからDELETE SQLの実行前に``preDelete``メソッドが呼び出されます。

[目次へ](#目次)

#### 2-2-4. PostInsert<small>インターフェース</small>
エンティティクラスがこのインターフェースを実装している場合、``Sql<E>``クラスの``insert(E)``および``insert(Iterable)``メソッドからINSERT SQLの実行後に``postInsert``メソッドが呼び出されます。

エンティティが他のエンティティを内包する場合、このインターフェースを実装する事で、内包するエンティティへの SQL 処理を連動して行う事ができます。

また``postInsert``メソッドを使用して、挿入時に自動採番された値を取得する事ができます。

```java
public abstract class Common implements PostInsert {
    @Key
    @NonInsert
    public int id;

    @Override
    public void postInsert(Connection conn) {
        Class<? extends Common> entityClass = getClass();
        if (PostSelect.class.isAssignableFrom(entityClass))
            entityClass = (Class<? extends Common>)entityClass.getSuperclass();
        new Sql<>(entityClass)
            .columns("id")
            .where("id=",
                new Sql<>(entityClass)
                    .columns("id")
                    .expression("id", "MAX({id})")
            )
            .connection(conn)
            .select(entity -> id = entity.id);
    }
}

public class Contact extends Common {
    public String firstName;
    public String lastName;
    public Date birthday;
}
```

&emsp;INSERT <small>時に生成される</small> SQL
```sql
INSERT INTO Contact (firstName, lastName, birthday) VALUES ('Yukari', 'Apple', DATE'2001-01-10')
SELECT id FROM Contact WHERE id= (SELECT MAX(id) id FROM Contact)
```

[目次へ](#目次)

#### 2-2-5. PostUpdate<small>インターフェース</small>
エンティティクラスがこのインターフェースを実装している場合、``Sql<E>``クラスの``update(E)``および``update(Iterable)``メソッドからUPDATE SQLの実行後に``postUpdate``メソッドが呼び出されます。

エンティティが他のエンティティを内包する場合、このインターフェースを実装する事で、内包するエンティティへの SQL 処理を連動して行う事ができます。

[目次へ](#目次)

#### 2-2-6. PostDelete<small>インターフェース</small>
エンティティクラスがこのインターフェースを実装している場合、``Sql<E>``クラスの``delete(E)``および``delete(Iterable)``メソッドからDELETE SQLの実行後に``postDelete``メソッドが呼び出されます。

エンティティが他のエンティティを内包する場合、このインターフェースを実装する事で、内包するエンティティへの SQL 処理を連動して行う事ができます。

[目次へ](#目次)

#### 2-2-7. PostSelect<small>インターフェース</small>
エンティティクラスがこのインターフェースを実装している場合、SELECT SQLを実行してエンティティを取得した後に``postSelect``メソッドが呼び出されます。

エンティティが他のエンティティを内包する場合、このインターフェースを実装する事で、内包するエンティティへの SQL 処理を連動して行う事ができます。

[目次へ](#目次)

```java
@Table("super")
public class ContactComposite extends Contact implements PostSelect, PostInsert, PostUpdate, PostDelete {
    @NonColumn
    public final var phones = new ArrayList<Phone>();

    @Override
    public void postSelect(ConnectionWrapper conn) {
        if (id != 0) {
            new Sql<>(Phone.class)
                .where("{contactId}={}", id)
                .orderBy("{phoneNumber}")
                .connection(conn)
                .select(phones::add);
        }
    }

    @Override
    public int postInsert(ConnectionWrapper conn) {
        phones.forEach(phone -> phone.contactId = id);
        new Sql<>(Phone.class)
            .insert(phones);
            .connection(conn)
    }

    @Override
    public void postUpdate(ConnectionWrapper conn) {
        List<Integer> phoneIds = phones.stream()
            .map(phone -> phone.id)
            .filter(id -> id != 0)
            .collect(Collectors.toList());

        // Delete phones
        new Sql<>(Phone.class)
            .where("{contactId}={}", id)
            .doIf(phoneIds.size() > 0,
                sql -> sql.and("{id} NOT IN {}", phoneIds)
            )
            .connection(conn)
            .delete();

        // Uptete phones
        new Sql<>(Phone.class)
            .connection(conn)
            .update(phones.stream()
                .filter(phone -> phone.id != 0)
                .collect(Collectors.toList()));

        // Insert phones
        new Sql<>(Phone.class)
            .connection(conn)
            .insert(phones.stream()
                .filter(phone -> phone.id == 0)
                .collect(Collectors.toList()));
    }

    @Override
    public void postDelete(ConnectionWrapper conn) {
        new Sql<>(Phone.class)
            .connection(conn)
            .where("{contactId}={}", id)
            .delete();
    }
```

[目次へ](#目次)

### 3. lightsleep.propertiesの定義

lightsleep.propertiesは、Lightsleepが参照するプロパティファイルで、以下の内容を指定できます。  
*(バージョン2.0.0まで存在した `Database` プロパティは、バージョン2.1.0で削除されました。データベースハンドラは、JDBC URLから自動的に決定されます。)*

|プロパティ名              |指定する内容                             |デフォルト値
|:-----------------------|:--------------------------------------|:----------
|`Logger`                |ログ出力クラス                           |`Std$Out$Info`
|`ConnectionSupplier`    |コネクションサプライヤクラス               |`Jdbc`
|`url`                   |JDBC URL                               |_なし_
|`urls`                  |JDBC URL(複数)                         |_なし_
|`dataSource`            |`Jndi`を使用した場合のデータソース名       |_なし_
|`dataSources`           |`Jndi`を使用した場合のデータソース名(複数)  |_なし_
|`maxStringLiteralLength`|SQLが生成される時の文字列リテラルの最大長   |128
|`maxBinaryLiteralLength`|SQLが生成される時のバイナリ列リテラルの最大長|128
|`maxLogStringLength`    |ログに出力する文字列値の最大長             |200
|`maxLogByteArrayLength` |ログに出力するバイト配列の最大要素数        |200
|`maxLogArrayLength`     |ログに出力する配列の最大要素数             |100
|`maxLogMapSize`         |ログに出力するマップの最大要素数           |100
|`connectionLogFormat`   |`ConnectionSupplier` のログ出力フォーマット<br>**文字列の置換:**<br>**{0}**: データベースハンドラのクラス名<br>**{1}**: ネクションサプライヤのクラス名<br>**{2}**: 接続先JDBC URL|`[{0}/{1}]`

`lightsleep.properties` ファイルは、クラスパスのいずれかに置いてください。あるいはシステムプロパティ `lightsleep.resource` でファイルパスを指定する事もできます。*(java -Dlightsleep.resource=...)*  
上記以外にもコネクションプールライブラリが使用するプロパティを定義します。

lightsleep.propertiesの例:  
```
Logger      = Log4j2
ConnectionSupplier = Dbcp
url         = jdbc:postgresql://postgresqlserver/example
user        = example
password    = _example_
initialSize = 10
maxTotal    = 100
```

`urls`プロパティにカンマ区切りで複数のJDBC URLを指定できます。
1つのプロパティを複数行で定義する場合は、最後の行以外の行末にバックスラッシュ(`\`)を付加します。  
`urls`を指定した場合は、`url`の指定は無効になります。

[source,properties]
.lightsleep.properties - 複数のJDBC URLを指定する場合
```
Logger      = Log4j2
ConnectionSupplier = Dbcp
urls        = jdbc:postgresql://postgresqlserver/example1,\
              jdbc:postgresql://postgresqlserver/example2
user        = example
password    = _example_
initialSize = 10
maxTotal    = 100
```

JDBC URL毎に異なるDBMSのURLを指定できます。JDBC URL毎にユーザー、パスワードが異なる場合は、URL内で指定してください。

[source,properties]
.lightsleep.properties - 複数のDBMSを使用する場合(URL内でユーザーとパスワードを指定)
```
Logger = Log4j2
ConnectionSupplier = Dbcp
urls = \
    jdbc:mariadb://mariadb:3306/example?user=example&password=_example_,\
    jdbc:mysql://mysql:3306/example?user=example&password=_example_,\
    jdbc:oracle:thin:example/_example_@oracle122:1521:example,\
    jdbc:postgresql://postgresql:5432/example?user=example&password=_example_,\
    jdbc:sqlite:C:/sqlite/example,\
    jdbc:sqlserver://sqlserver:1433;database=example;user=example;password=_example_,\

initialSize = 10
maxTotal    = 100
```

URL毎にコネクションサプライヤを指定する場合は、URLの先頭の``[]``内に記述します。  
この形式の指定は、``ConnectionSupplier``プロパティの指定よりも優先されます。  
`username`, ``jdbcUrl``プロパティは、`user`, ``url``プロパティで指定する事ができますが、それら以外はコネクションプールライブラリ固有のプロパティ名で指定してください。

lightsleep.properties - URL毎にコネクションサプライヤを指定する場合
```
Logger = Log4j2
urls = \
    [  C3p0  ]jdbc:mariadb://mariadb:3306/example?user=example&password=_example_,\
    [  Dbcp  ]jdbc:mysql://mysql:3306/example?user=example&password=_example_,\
    [HikariCP]jdbc:oracle:thin:example/_example_@oracle122:1521:example,\
    [TomcatCP]jdbc:postgresql://postgresql:5432/example?user=example&password=_example_,\
    [  Jdbc  ]jdbc:sqlite:C:/sqlite/example,\
    [  C3p0  ]jdbc:sqlserver://sqlserver:1433;database=example;user=example;password=_example_,\

# Dbcp, HikariCP, TomcatCP
initialSize = 10

# Dbcp
maxTotal    = 10

# TomcatCP
maxActive   = 10

# HikariCP
minimumIdle     = 10
maximumPoolSize = 10
```

[目次へ](#目次) [プロパティ一覧へ](#3-lightsleeppropertiesの定義)

#### 3-1. ログライブラリクラスの指定

Loggerプロパティの値は以下から選択してください。

|指定値|ログライブラリなど|ログレベル|ログライブラリが使用する定義ファイル
|:--------------|:---------------|:---:|:----------------------
|`Jdk`          |Java Runtime    |－   |logging.properties
|`Log4j`        |Log4j           |－   |log4j.propertiesまたはlog4j.xml
|`Log4j2`       |Log4j 2         |－   |log4j2.xml
|`SLF4J`        |SLF4J           |－   |対象とするログライブラリ実装に依存
|`Std$Out$Trace`|System.outに出力|trace|_なし_
|`Std$Out$Debug`|_同上_          |debug|_なし_
|`Std$Out$Info` |_同上_          |info |_なし_
|`Std$Out$Warn` |_同上_          |warn |_なし_
|`Std$Out$Error`|_同上_          |error|_なし_
|`Std$Out$Fatal`|_同上_          |fatal|_なし_
|`Std$Err$Trace`|System.errに出力|trace|_なし_
|`Std$Err$Debug`|_同上_          |debug|_なし_
|`Std$Err$Info` |_同上_          |info |_なし_
|`Std$Err$Warn` |_同上_          |warn |_なし_
|`Std$Err$Error`|_同上_          |error|_なし_
|`Std$Err$Fatal`|_同上_          |fatal|_なし_

指定がない場合は、``Std$Out$Info``が選択されます。

[目次へ](#目次) [プロパティ一覧へ](#3-lightsleeppropertiesの定義)

#### 3-2. データベースハンドラクラス

データベースハンドラクラスは、`url`または`urls`プロパティで指定されたJDBC URLの内容から自動的に選択されます。

|JDBC URLに含まれる文字列|選択されるクラス|対応するDBMS
|:-----------|:-----------|:-------------------------------
|`mariadb`   |`MariaDB`   |[MariaDB](https://mariadb.org/)
|`mysql`     |`MySQL`     |[MySQL](https://www.mysql.com/)
|`oracle`    |`Oracle`    |[Oracle Database](https://www.oracle.com/database/index.html)
|`postgresql`|`PostgreSQL`|[PostgreSQL](https://www.postgresql.org/)
|`sqlite`    |`SQLite`    |[SQLite](https://sqlite.org/index.html)
|`sqlserver` |`SQLServer` |[Microsoft SQL Server](https://www.microsoft.com/ja-jp/sql-server/sql-server-2017)

JDBC URLに上記の文字列のいずれもが含まれていない場合、``Standard``クラスが選択されます。

[目次へ](#目次) [プロパティ一覧へ](#3-lightsleeppropertiesの定義)

#### 3-3. コネクションサプライヤクラス

ConnectionSupplierプロパティの値は以下から選択してください。JDBC URLの先頭の``[]``内で指定する事もできます。

|指定値     |対応するコネクションプールライブラリ
|:---------|:------------------------------
|`C3p0`    |[c3p0](http://www.mchange.com/projects/c3p0/)
|`Dbcp`    |[Apache Commons DBCP](https://commons.apache.org/proper/commons-dbcp/)
|`HikariCP`|[HikariCP](https://github.com/brettwooldridge/HikariCP)
|`TomcatCP`|[Tomcat JDBC Connection Pool](http://tomcat.apache.org/tomcat-9.0-doc/jdbc-pool.html)
|`Jndi`    |Java Naming and Directory Interface (JNDI) ([Tomcatの場合](http://tomcat.apache.org/tomcat-8.5-doc/jndi-datasource-examples-howto.html))
|`Jdbc`    |`DriverManager#getConnection(String url, Properties info)` メソッド

`C3p0`, `Dbcp 2`, `HikariCP`, ``TomcatCP``クラスは、それぞれ対応するコネクションプールライブラリを使用してデータベースコネクションを取得します。  
``Jndi``クラスは、JNDI (Java Naming and Directory Interface)を使用して取得したデータソース(`javax.sql.DataSource`)からデータベースコネクションを取得します。  
``Jdbc``クラスは、``java.sql.DriverManager.getConnection``メソッドを使用してデータベースコネクションを取得します。  
コネクションプールライブラリが必要する情報もlightsleep.propertiesファイルに定義してください。
以下のlightsleep.propertiesの定義例のConnectionSupplierより下(url ~)は、コネクションプールライブラリに渡す内容です。

lightsleep.properties - Jdbc
```properties
ConnectionSupplier = Jdbc
url      = jdbc:mariadb://maria-win-x64:3306/example
user     = example
password = _example_
```

lightsleep.properties - C3p0
```properties
ConnectionSupplier = C3p0
url      = jdbc:mysql://mysql-win-x64:3307/example
user     = example
password = _example_
```

c3p0.properties
```properties
c3p0.initialPoolSize = 20
c3p0.minPoolSize     = 10
c3p0.maxPoolSize     = 30
```

lightsleep.properties - Dbcp
```properties
ConnectionSupplier = Dbcp
url         = jdbc:oracle:thin:@ora19-win-x64:1521:example
user        = example
# または
username    = example
password    = _example_
initialSize = 20
maxTotal    = 30
```

lightsleep.properties - HikariCP
```properties
ConnectionSupplier = HikariCP
url             = jdbc:postgresql://post-win-x64/example
# または
jdbcUrl         = jdbc:postgresql://post-win-x64/example
user            = example
# または
username        = example
password        = _example_
minimumIdle     = 10
maximumPoolSize = 30
```

lightsleep.properties - TomcatCP
```properties
ConnectionSupplier = TomcatCP
url         = jdbc:sqlserver://sql-win-x64;database=example
user        = example
# または
username    = example
password    = _example_
initialSize = 20
maxActive   = 30
```

lightsleep.properties - Jndi
```properties
ConnectionSupplier = Jndi
dataSource         = jdbc/example
# または
dataSource         = example
```

[目次へ](#目次)

### 4. トランザクション

``Transaction.execute``メソッドの実行が1つのトランザクションの実行に相当します。
トランザクションの内容を引数``transaction``(ラムダ式) で定義してください。
ラムダ式は、``Transaction.executeBody``メソッドの内容に相当し、このメソッドの引数は、``ConnectionWrapper``です。

```java
var contact = new Contact(1, "Akane", "Apple");

Transaction.execute(conn -> {
    // トランザクション開始
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(contact);
   ...
    // トランザクション終了
});
```

複数のJDBC URLを ``lightsleep.properties``に定義した場合は、どのURLに対してトランザクションの実行を行うかを指定する必要があります。 ``ConnectionSupplier.find``メソッドは、引数の文字列配列のすべてが含まれるJDBC URLを検索します。
複数見つかった場合または見つからない場合は例外がスローされます。

```java
public static final ConnectionSupplier supplier1 = ConnectionSupplier.find("example1");
    ...

var contact = new Contact(1, "Akane", "Apple");

Transaction.execute(supplier1, conn -> {
    // トランザクション開始
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(contact);
   ...
    // トランザクション終了
});
```

トランザクション中に例外がスローされた場合は、``Transaction.rollback``メソッドが実行され、
そうでなければ ``Transaction.commit``メソッドが実行されます。

[目次へ](#目次)

### 5. SQLの実行
SQLの実行は、``Sql``クラスの各種メソッドを使用し、``Transaction.execute``メソッドの引数のラムダ式内に定義します。

#### 5-1. SELECT

#### 5-1-1. SELECT 1行/式条件

```java
Transaction.execute(conn -> {
    var contactOpt = new Sql<>(Contact.class)
        .where("{id}={}", 1)
        .connection(conn)
        .select();
});
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE id=1
```

[目次へ](#目次)

#### 5-1-2. SELECT 1行/エンティティ条件

```java
var contact = new Contact();
contact.id = 1;
Transaction.execute(conn -> {
    var contactOpt = new Sql<>(Contact.class)
        .where(contact)
        .connection(conn)
        .select();
});
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE id=1
```

[目次へ](#目次)

#### 5-1-3. SELECT 複数行/式条件

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple'
```

[目次へ](#目次)

#### 5-1-4. SELECT サブクエリ条件

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class, "C")
        .where("EXISTS",
            new Sql<>(Phone.class, "P")
                .where("{P.contactId}={C.id}")
        )
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime
  FROM Contact C
  WHERE EXISTS (SELECT * FROM Phone P WHERE P.contactId=C.id)
```

[目次へ](#目次)

#### 5-1-5. SELECT 式条件/AND

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .and  ("{firstName}={}", "Akane")
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' AND firstName='Akane'
```

[目次へ](#目次)

#### 5-1-6. SELECT 式条件/OR

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .or   ("{lastName}={}", "Orange")
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' OR lastName='Orange'
```

[目次へ](#目次)

#### 5-1-7. SELECT 式条件/A AND B OR C AND D

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where(Condition
            .of ("{lastName}={}", "Apple")
            .and("{firstName}={}", "Akane")
        )
        .or(Condition
            .of ("{lastName}={}", "Orange")
            .and("{firstName}={}", "Setoka")
        )
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' AND firstName='Akane' OR lastName='Orange' AND firstName='Setoka'
```

[目次へ](#目次)

#### 5-1-8. SELECTカラムの選択

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .columns("lastName", "firstName")
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT firstName, lastName FROM Contact WHERE lastName='Apple'
```

[目次へ](#目次)

#### 5-1-9. SELECT GROUP BY, HAVING

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class, "C")
        .columns("lastName")
        .groupBy("{lastName}")
        .having("COUNT({lastName})>=2")
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT MIN(C.lastName) C_lastName FROM Contact C GROUP BY C.lastName HAVING COUNT(C.lastName)>=2
```

[目次へ](#目次)

#### 5-1-10. SELECT ORDER BY, OFFSET, LIMIT

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .orderBy("{lastName}")
        .orderBy("{firstName}")
        .orderBy("{id}")
        .offset(10).limit(5)
        .connection(conn)
        .select(contacts::add)
);
```

&emsp;<small>生成される</small>SQL - MariaDB, MySQL, PostgreSQL, SQLite
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  ORDER BY lastName ASC, firstName ASC, id ASC
  LIMIT 5 OFFSET 10
```

&emsp;<small>生成される</small>SQL - Oracle, SQLServer (取得時に行をスキップする)
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  ORDER BY lastName ASC, firstName ASC, id ASC
```

[目次へ](#目次)

#### 5-1-11. SELECT FOR UPDATE

```java
Transaction.execute(conn -> {
    var contactOpt = new Sql<>(Contact.class)
        .where("{id}={}", 1)
        .forUpdate()
        .connection(conn)
        .select();
});
```

&emsp;<small>生成される</small>SQL - MariaDB, MySQL, Oracle, PostgreSQL, SQLite
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1 FOR UPDATE
```

&emsp;<small>生成される</small>SQL - SQLite
```sql
-- SQLiteでは、FOR UPDATEをサポートしていないのでUnsupportedOperationExceptionがスローされます。
```

&emsp;<small>生成される</small>SQL - SQLServer
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WITH (ROWLOCK,UPDLOCK) WHERE id=1
```

[目次へ](#目次)

#### 5-1-12. SELECT 内部結合

```java
var contacts = new ArrayList<Contact>();
var phones = new ArrayList<Phone>();
Transaction.execute(conn ->
    new Sql<>(Contact.class, "C")
        .innerJoin(Phone.class, "P", "{P.contactId}={C.id}")
        .where("{C.id}={}", 1)
        .connection(conn)
        .select(contacts::add, phones::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime, P.contactId P_contactId, P.featureIndex P_featureIndex, P.label P_label, P.content P_content
  FROM Contact C
  INNER JOIN Phone P ON P.contactId=C.id
  WHERE C.id=1
```

[目次へ](#目次)

#### 5-1-13. SELECT 左外部結合

```java
var contacts = new ArrayList<Contact>();
var phones = new ArrayList<Phone>();
Transaction.execute(conn ->
    new Sql<>(Contact.class, "C")
        .leftJoin(Phone.class, "P", "{P.contactId}={C.id}")
        .where("{C.lastName}={}", "Apple")
        .connection(conn)
        .select(contacts::add, phones::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime, P.contactId P_contactId, P.featureIndex P_featureIndex, P.label P_label, P.content P_content
  FROM Contact C
  LEFT OUTER JOIN Phone P ON P.contactId=C.id
  WHERE C.lastName='Apple'
```

[目次へ](#目次)

#### 5-1-14. SELECT 右外部結合

```java
var contacts = new ArrayList<Contact>();
var phones = new ArrayList<Phone>();
Transaction.execute(conn ->
    new Sql<>(Contact.class, "C")
        .rightJoin(Phone.class, "P", "{P.contactId}={C.id}")
        .where("{P.label}={}", "Main")
        .connection(conn)
        .select(contacts::add, phones::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
-- SQLiteでは、RIGHT OUTER JOINが未サポートのため、例外がスローされます。
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime, P.contactId P_contactId, P.featureIndex P_featureIndex, P.label P_label, P.content P_content
  FROM Contact C
  RIGHT OUTER JOIN Phone P ON P.contactId=C.id
  WHERE P.label='Main'
```

[目次へ](#目次)

#### 5-1-15. SELECT COUNT(*)

```java
var count = new int[1];
Transaction.execute(conn ->
    count[0] = new Sql<>(Contact.class)
        .where("lastName={}", "Apple")
        .connection(conn)
        .selectCount()
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT COUNT(*) FROM Contact WHERE lastName='Apple'
```

[目次へ](#目次)

#### 5-1-16. SELECT FROM句サブクエリ

```java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn -> {
    Class<? extends Contact.Ex> contactClass = Contact.Ex.targetClass(conn.getDatabase());
    new Sql<>(contactClass)
        .from(new Sql<>(contactClass))
        .where("{fullName}={}", "Akane Apple")
        .orderBy("{fullName}")
        .connection(conn)
        .select(contacts::add);
});
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime, fullName
  FROM (
    SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime, firstName||' '||lastName fullName FROM Contact
  ) Contact
  WHERE fullName='Akane Apple' ORDER BY fullName ASC
```

[目次へ](#目次)

#### 5-1-17. SELECT UNION, UNION ALL

```java
var features = new ArrayList<ContactFeature>();
var targetFirstName = "Setoka";
var targetLastName = "Orange";
Transaction.execute(conn -> {
    new Sql<>(ContactFeature.class, "F")
        .columns(ContactFeature.class)
        .unionAll(new Sql<>(Address.class)
            .innerJoin(Contact.class, "C", "{C.id}={F.contactId}")
            .where("{C.firstName}={}", targetFirstName)
            .and("{C.lastName}={}", targetLastName)
            .and("{F.featureIndex}={}", 1)
        )
        .unionAll(new Sql<>(Email.class)
            .innerJoin(Contact.class, "C", "{C.id}={F.contactId}")
            .where("{C.firstName}={}", targetFirstName)
            .and("{C.lastName}={}", targetLastName)
            .and("{F.featureIndex}={}", 1)
        )
        .unionAll(new Sql<>(Phone.class)
            .innerJoin(Contact.class, "C", "{C.id}={F.contactId}")
            .where("{C.firstName}={}", targetFirstName)
            .and("{C.lastName}={}", targetLastName)
            .and("{F.featureIndex}={}", 1)
        )
        .unionAll(new Sql<>(Url.class)
            .innerJoin(Contact.class, "C", "{C.id}={F.contactId}")
            .where("{C.firstName}={}", targetFirstName)
            .and("{C.lastName}={}", targetLastName)
            .and("{F.featureIndex}={}", 1)
        )
        .orderBy("{F_label}")
        .connection(conn)
        .select(features::add);
});
```

&emsp;<small>生成される</small>SQL
```sql
SELECT F.contactId F_contactId, F.featureIndex F_featureIndex, F.label F_label, F.content F_content
  FROM Address F
  INNER JOIN Contact C ON C.id=F.contactId
  WHERE C.firstName='Setoka' AND C.lastName='Orange' AND F.featureIndex=1
UNION ALL
SELECT F.contactId F_contactId, F.featureIndex F_featureIndex, F.label F_label, F.content F_content
  FROM Email F
  INNER JOIN Contact C ON C.id=F.contactId
  WHERE C.firstName='Setoka' AND C.lastName='Orange' AND F.featureIndex=1
UNION ALL
SELECT F.contactId F_contactId, F.featureIndex F_featureIndex, F.label F_label, F.content F_content
  FROM Phone F
  INNER JOIN Contact C ON C.id=F.contactId
  WHERE C.firstName='Setoka' AND C.lastName='Orange' AND F.featureIndex=1
UNION ALL
SELECT F.contactId F_contactId, F.featureIndex F_featureIndex, F.label F_label, F.content F_content
  FROM Url F
  INNER JOIN Contact C ON C.id=F.contactId
  WHERE C.firstName='Setoka' AND C.lastName='Orange' AND F.featureIndex=1
ORDER BY F_label ASC
```

[目次へ](#目次)

#### 5-1-18. SELECT WITH句

```java
var nodes = new ArrayList<Node>();
var nodeSql = new Sql<>(Node.class)
    .where("{name} LIKE {}", "%-%");
Transaction.execute(conn ->
    new Sql<>(Node.class)
        .with(nodeSql)
        .from(nodeSql)
        .connection(conn)
        .select(nodes::add)
);
```

&emsp;<small>生成される</small>SQL
```sql
WITH W1(id, parentId, name) AS (
  SELECT id, parentId, name FROM Node WHERE name LIKE '%-%'
)
SELECT id, parentId, name FROM W1
```

[目次へ](#目次)

#### 5-1-19. SELECT RECURSIVE

```java
var nodes = new ArrayList<Node>();
var nodeSql = new Sql<>(Node.class).where(rootNode)
    .recursive(new Sql<>(Node.class, "node").where("{node.parentId}={W1.id}"));
Transaction.execute(conn ->
    new Sql<>(Node.class)
        .with(nodeSql)
        .from(nodeSql)
        .connection(conn)
        .select(nodes::add)
);
```

&emsp;<small>生成される</small>SQL - MariaDB, MySQL, PostgreSQL, SQLite
```sql
WITH RECURSIVE W1(id, parentId, name) AS (
  SELECT id, parentId, name FROM Node WHERE id=1
  UNION ALL
  SELECT node.id node_id, node.parentId node_parentId, node.name node_name
    FROM Node node,W1
    WHERE node.parentId=W1.id
)
SELECT id, parentId, name FROM W1
```

&emsp;<small>生成される</small>SQL - Oracle, SQL Server
```sql
WITH W1(id, parentId, name) AS (
  SELECT id, parentId, name FROM Node WHERE id=1
  UNION ALL
  SELECT node.id node_id, node.parentId node_parentId, node.name node_name
    FROM Node node,W1
    WHERE node.parentId=W1.id
)
SELECT id, parentId, name FROM W1
```

[目次へ](#目次)

#### 5-2. INSERT

#### 5-2-1. INSERT 1行

```java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(new Contact(1, "Akane", "Apple", 2001, 1, 1))
```

&emsp;<small>生成される</small>SQL - MariaDB, MySQL, Oracle, PostgreSQL
```sql
INSERT INTO Contact
  (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (1, 'Akane', 'Apple', DATE'2001-01-01', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

&emsp;<small>生成される</small>SQL - SQLite
```sql
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (1, 'Akane', 'Apple', '2001-01-01', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

&emsp;<small>生成される</small>SQL - SQLServer
```sql
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (1, 'Akane', 'Apple', CAST('2001-01-01' AS DATE), 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

[目次へ](#目次)

#### 5-2-2. INSERT 複数行

```java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(Arrays.asList(
            new Contact(2, "Yukari", "Apple", 2001, 1, 2),
            new Contact(3, "Azusa", "Apple", 2001, 1, 3)
        ))
```

&emsp;<small>生成される</small>SQL - MariaDB, MySQL, Oracle, PostgreSQL
```sql
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (2, 'Yukari', 'Apple', DATE'2001-01-02', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (3, 'Azusa', 'Apple', DATE'2001-01-03', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

&emsp;<small>生成される</small>SQL - SQLite
```sql
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (2, 'Yukari', 'Apple', '2001-01-02', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (3, 'Azusa', 'Apple', '2001-01-03', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

&emsp;<small>生成される</small>SQL - SQLServer
```sql
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (2, 'Yukari', 'Apple', CAST('2001-01-02' AS DATE), 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (3, 'Azusa', 'Apple', CAST('2001-01-03' AS DATE), 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

[目次へ](#目次)

#### 5-3. UPDATE

#### 5-3-1. UPDATE 1行

```java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{id}={}", 1)
        .connection(conn)
        .select()
        .ifPresent(contact -> {
            contact.firstName = "Akiyo";
            new Sql<>(Contact.class)
                .connection(conn)
                .update(contact);
        })
);
```

&emsp;<small>生成される</small>SQL - MariaDB, MySQL, Oracle, PostgreSQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
UPDATE Contact SET
  firstName='Akiyo', lastName='Apple', birthday=DATE'2001-01-01', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP WHERE id=1
```

&emsp;<small>生成される</small>SQL - SQLite
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
UPDATE Contact SET
  firstName='Akiyo', lastName='Apple', birthday='2001-01-01', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP WHERE id=1
```

&emsp;<small>生成される</small>SQL - SQLServer
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
UPDATE Contact SET
  firstName='Akiyo', lastName='Apple', birthday=CAST('2001-01-01' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP WHERE id=1
```

[目次へ](#目次)

#### 5-3-2. UPDATE 複数行

```java
Transaction.execute(conn -> {
    var contacts = new ArrayList<Contact>();
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .connection(conn)
        .select(contact -> {
            contact.lastName = "Apfel";
            contacts.add(contact);
        });
    new Sql<>(Contact.class)
        .connection(conn)
        .update(contacts);
});
```

&emsp;<small>生成される</small>SQL - MariaDB, MySQL, Oracle, PostgreSQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE lastName='Apple'
UPDATE Contact SET
  firstName='Akiyo', lastName='Apfel', birthday=DATE'2001-01-01', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=1
UPDATE Contact SET
  firstName='Yukari', lastName='Apfel', birthday=DATE'2001-01-02', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=2
UPDATE Contact SET
  firstName='Azusa', lastName='Apfel', birthday=DATE'2001-01-03', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=3
```

&emsp;<small>生成される</small>SQL - SQLite
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE lastName='Apple'
UPDATE Contact SET
  firstName='Akiyo', lastName='Apfel', birthday='2001-01-01', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=1
UPDATE Contact SET
  firstName='Yukari', lastName='Apfel', birthday='2001-01-02', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=2
UPDATE Contact SET
  firstName='Azusa', lastName='Apfel', birthday='2001-01-03', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=3
```

&emsp;<small>生成される</small>SQL - SQLServer
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE lastName='Apple'
UPDATE Contact SET
  firstName='Akiyo', lastName='Apfel', birthday=CAST('2001-01-01' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=1
UPDATE Contact SET
  firstName='Yukari', lastName='Apfel', birthday=CAST('2001-01-02' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=2
UPDATE Contact SET
  firstName='Azusa', lastName='Apfel', birthday=CAST('2001-01-03' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=3
```

[目次へ](#目次)

#### 5-3-3. UPDATE 指定条件, カラム選択

```java
var contact = new Contact();
contact.lastName = "Pomme";
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apfel")
        .columns("lastName")
        .connection(conn)
        .update(contact)
);
```

&emsp;<small>生成される</small>SQL
```sql
UPDATE Contact SET lastName='Pomme' WHERE lastName='Apfel'
```

[目次へ](#目次)

#### 5-3-4. UPDATE 全行

```java
var contact = new Contact();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where(Condition.ALL)
        .columns("birthday")
        .connection(conn)
        .update(contact)
);
```

&emsp;<small>生成される</small>SQL
```sql
UPDATE Contact SET birthday=NULL
```

[目次へ](#目次)

#### 5-4. DELETE

#### 5-4-1. DELETE 1行

```java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{id}={}", 1)
        .connection(conn)
        .select()
        .ifPresent(contact ->
            new Sql<>(Contact.class)
                .connection(conn)
                .delete(contact))
);
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
DELETE FROM Contact WHERE id=1
```

[目次へ](#目次)

#### 5-4-2. DELETE 複数行

```java
Transaction.execute(conn -> {
    var contacts = new ArrayList<Contact>();
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Pomme")
        .connection(conn)
        .select(contacts::add);
    new Sql<>(Contact.class)
        .connection(conn)
        .delete(contacts);
});
```

&emsp;<small>生成される</small>SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE lastName='Pomme'
DELETE FROM Contact WHERE id=2
DELETE FROM Contact WHERE id=3
```

[目次へ](#目次)

#### 5-4-3. DELETE 指定条件

```java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Orange")
        .connection(conn)
        .delete()
);
```

&emsp;<small>生成される</small>SQL
```sql
DELETE FROM Contact WHERE lastName='Orange'
```

[目次へ](#目次)

#### 5-4-4. DELETE 全行

```java
Transaction.execute(conn ->
    new Sql<>(Phone.class)
        .where(Condition.ALL)
        .connection(conn)
        .delete()
);
```

&emsp;<small>生成される</small>SQL
```sql
DELETE FROM Phone
```

[目次へ](#目次)

### 6. 式の変換処理

SQL を生成する時に、以下の文字列を式として評価し、変換処理を行います。

* `@Select`, `@Insert`, ``@Update``アノテーションの値

* `@SelectProperty`, `@InsertProperty`, ``@UpdateProperty``アノテーションの ``expression``の値

* `Sql` クラスの以下のメソッドの引数
  * `where(String content, Object... arguments)`
  * `where(String content, Sql<SE> subSql)`
  * `where(Sql<SE> subSql, String content)`
  * `and(String content, Object... arguments)`
  * `and(String content, Sql<SE> subSql)`
  * `and(Sql<SE> subSql, String content)`
  * `or(String content, Object... arguments)`
  * `or(String content, Sql<SE> subSql)`
  * `or(Sql<SE> subSql, String content)`
  * `groupBy(String content, Object... arguments)`
  * `having(String content, Object... arguments)`
  * `having(String content, Sql<SE> subSql)`
  * `having(Sql<SE> subSql, String content)`
  * `orderBy(String content, Object... arguments)`

* `Condition` インターフェースの以下のメソッドの引数
  * `of(String content, Object... arguments)`
  * `of(String content, Sql<E> outerSql, Sql<SE> subSql)`
  * `of(Sql<E> outerSql, Sql<SE> subSql, String content)`
  * `and(String content, Object... arguments)`
  * `and(String content, Sql<E> outerSql, Sql<SE> subSql)`
  * `and(Sql<E> outerSql, Sql<SE> subSql, String content)`
  * `or(String content, Object... arguments)`
  * `or(String content, Sql<E> outerSql, Sql<SE> subSql)`
  * `or(Sql<E> outerSql, Sql<SE> subSql, String content)`

* `Expression` クラスの以下のコンストラクタの引数
  * `Expression(String content, Object... arguments)`

式の変換には以下があります。

|書式      |変換内容
|:--------|:------
|`{}`     |出現順に``arguments``の要素
|`{xxx}`  |``xxx``プロパティに関連するカラム名
|`{A.xxx}`|`"A."` + ``xxx``プロパティに関連するカラム名(`A` はテーブル別名)
|`{A_xxx}`|テーブル別名``A``と``xxx``プロパティに関連するカラム別名
|`{#xxx}` |`Sql` オブジェクトに設定されたエンティティ(または``Sql#insert``, ``Sql#update``メソッドのエンティティ引数)の``xxx``プロパティの値

[目次へ](#目次)


### 7. 日時型おけるJava型とカラム型の対応

##### 7-1. MariaDB, MySQL

|                          |`DATE`|`TIME`                |`DATETIME`            |`TIMESTAMP`
|:-------------------------|:----:|:--------------------:|:--------------------:|:---------:
|`java.util.Date`          | ✓    |                      |                      |
|`java.sql.Date`           | ✓    |                      |                      |
|`java.sql.Time`           |      |✓ (10<sup>-3</sup> 秒)|                      |
|`java.sql.Timestamp`      | ✓    |                      |✓ (10<sup>-6</sup> 秒)|✓ (10<sup>-6</sup> 秒)
|`java.time.LocalDate`     |      |                      |                      |
|`java.time.LocalTime`     |      |✓ (10<sup>-6</sup> 秒)|                      |
|`java.time.LocalDateTime` |      |                      |✓ (10<sup>-6</sup> 秒)|✓ (10<sup>-6</sup> 秒)
|`java.time.OffsetDateTime`|      |                      |                      |
|`java.time.ZonedDateTime` |      |                      |                      |
|`java.time.Instant`       |      |                      |                      |

##### 7-2. Oracle

|                          |`DATE`|`TIMESTAMP(9)`        |`TIMESTAMP(9)`<br>`WITH TIME ZONE`|`TIMESTAMP(9) WITH`<br>`LOCAL TIME ZONE`
|:-------------------------|:-----|:---------------------|:---------------------|:--------------------
|`java.util.Date`          |✓     |                      |                      |
|`java.sql.Date`           |✓     |                      |                      |
|`java.sql.Time`           |✓ (秒)|                      |                      |
|`java.sql.Timestamp`      |✓ (秒)|✓ (10<sup>-9</sup> 秒)|                      |✓ (10<sup>-9</sup> 秒)
|`java.time.LocalDate`     |✓ (秒)|                      |                      |
|`java.time.LocalTime`     |✓ (秒)|                      |                      |
|`java.time.LocalDateTime` |✓ (秒)|✓ (10<sup>-9</sup> 秒)|                      |✓ (10<sup>-9</sup> 秒)
|`java.time.OffsetDateTime`|      |                      |✓ (10<sup>-9</sup> 秒)|
|`java.time.ZonedDateTime` |      |                      |✓ (10<sup>-9</sup> 秒)|
|`java.time.Instant`       |      |                      |✓ (10<sup>-9</sup> 秒)|

##### 7-3. PostgreSQL

|                          |`DATE`|`TIME(6)`             |`TIMESTAMP(6)`        |`TIMESTAMP(6) WITH TIME ZONE`
|:-------------------------|:-----|:---------------------|:---------------------|:----------------------------
|`java.util.Date`          |✓     |                      |                      |
|`java.sql.Date`           |✓     |                      |                      |
|`java.sql.Time`           |      |✓ (10<sup>-3</sup> 秒)|                      |
|`java.sql.Timestamp`      |✓     |                      |✓ (10<sup>-6</sup> 秒)|
|`java.time.LocalDate`     |      |                      |                      |
|`java.time.LocalTime`     |      |✓ (10<sup>-6</sup> 秒)|                      |
|`java.time.LocalDateTime` |      |                      |✓ (10<sup>-6</sup> 秒)|
|`java.time.OffsetDateTime`|      |                      |                      |✓ (10<sup>-6</sup> 秒)
|`java.time.ZonedDateTime` |      |                      |                      |
|`java.time.Instant`       |      |                      |                      |✓ (10<sup>-6</sup> 秒)

##### 7-4. SQLite

|                          |`DATE`, `TIME`, `DATETIME`, `TEXT`
|:-------------------------|:---------------------------------
|`java.util.Date`          |✓
|`java.sql.Date`           |✓
|`java.sql.Time`           |✓ (10<sup>-3</sup> 秒)
|`java.sql.Timestamp`      |✓ (10<sup>-9</sup> 秒)
|`java.time.LocalDate`     |✓ (10<sup>-9</sup> 秒)
|`java.time.LocalTime`     |✓ (10<sup>-9</sup> 秒)
|`java.time.LocalDateTime` |✓ (10<sup>-9</sup> 秒)
|`java.time.OffsetDateTime`|✓ (10<sup>-9</sup> 秒)
|`java.time.ZonedDateTime` |✓ (10<sup>-9</sup> 秒)
|`java.time.Instant`       |✓ (10<sup>-9</sup> 秒)

##### 7-5. SQL Server

|                          |`DATE`|`TIME(7)`             |`DATETIME2(7)`        |`DATETIMEOFFSET(7)`
|:-------------------------|:-----|:---------------------|:---------------------|:------------------
|`java.util.Date`          |✓     |                      |                      |
|`java.sql.Date`           |✓     |                      |                      |
|`java.sql.Time`           |      |✓ (10<sup>-3</sup> 秒)|                      |
|`java.sql.Timestamp`      |✓     |                      |✓ (10<sup>-7</sup> 秒)|
|`java.time.LocalDate`     |      |                      |                      |
|`java.time.LocalTime`     |      |✓ (10<sup>-3</sup> 秒)|                      |
|`java.time.LocalDateTime` |      |                      |✓ (10<sup>-7</sup> 秒)|
|`java.time.OffsetDateTime`|      |                      |                      |✓ (10<sup>-7</sup> 秒)
|`java.time.ZonedDateTime` |      |                      |                      |
|`java.time.Instant`       |      |                      |                      |✓ (10<sup>-7</sup> 秒)

[目次へ](#目次)

<div style="color:gray"><i>(C) 2015 Masato Kokubo</i></div>
