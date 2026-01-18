[English](CHANGELOG.md)

- [<small>バージョン</small> 4.0.1](#バージョン-410) - 2026/1/18
- [<small>バージョン</small> 4.0.1](#バージョン-401) - 2022/7/9
- [<small>バージョン</small> 4.0.0](#バージョン-400) - 2020/10/3
- [<small>バージョン</small> 3.2.0](#バージョン-320) - 2019/9/25
- [<small>バージョン</small> 3.1.1](#バージョン-311) - 2019/7/18
- [<small>バージョン</small> 3.1.0](#バージョン-310) - 2019/7/16
- [<small>バージョン</small> 3.0.1](#バージョン-301) - 2018/12/1
- [<small>バージョン</small> 3.0.0](#バージョン-300) - 2018/10/7
- [<small>バージョン</small> 2.2.1](#バージョン-221) - 2018/2/12
- [<small>バージョン</small> 2.2.0](#バージョン-220) - 2018/2/4
- [<small>バージョン</small> 2.1.1](#バージョン-211) - 2017/12/3
- [<small>バージョン</small> 2.1.0](#バージョン-210) - 2017/11/12
- [<small>バージョン</small> 2.0.0](#バージョン-200) - 2017/9/9


## <small><small>バージョン</small></small> 4.1.0

* リポジトリをMavenからJitPackに変更しました。
* Db2のサポートを廃止しました。
* ドキュメントの形式をAsciiDocからMarkdownに変更しました。

## <small><small>バージョン</small></small> 4.0.1

* エラーメッセージを改善しました。

## <small><small>バージョン</small></small> 4.0.0

**新機能**
* 以下のSQLを生成できるようになりました。
    * `WITH`句付き`SELECT SQL`  
        `WITH W1(\...) AS (`  
        `  SELECT \...`  
        `)`  
        `SELECT ... FROM W1 \...`
    * 再帰`SELECT SQL`  
        `WITH RECURSIVE W1(\...) AS (`  
        `  SELECT \...`  
        `  UNION ALL`  
        `  SELECT \...`  
        `) SELECT \... FROM W1 \...`
    * サブクエリ付き`INSERT SQL`  
        `INSERT INTO \... (\...) SELECT \... FROM \...`
    * サブクエリの結合
        `SELECT \... FROM \... INNER JOIN (SELECT \...) \...`

**追加されたメソッド(コンストラクタ)**
* `org.lightsleep.Sql`<small>クラス</small>
    * `String queryName()`
    * `Sql<E> innerJoin(Sql<?> joinSql, String tableAlias, Condition on)`
    * `Sql<E> innerJoin(Sql<?> joinSql, String tableAlias, String on, Object... arguments)`
    * `Sql<E> leftJoin(Sql<?> joinSql, String tableAlias, Condition on)`
    * `Sql<E> leftJoin(Sql<?> joinSql, String tableAlias, String on, Object... arguments)`
    * `Sql<E> rightJoin(Sql<?> joinSql, String tableAlias, Condition on)`
    * `Sql<E> rightJoin(Sql<?> joinSql, String tableAlias, String on, Object... arguments)`
    * `Sql<E> with(Sql<?>... withSqls)`
    * `List<Sql<?>> getWithSqls()`
    * `boolean isWithSql()`
    * `Sql<E> recursive(Sql<?> recursiveSql)`
    * `Sql<?> getRecursiveSql()`
    * `boolean isRecursiveSql()`
    * `boolean isInInsertFrom()`
    * `int insert()`

* `org.lightsleep.database.Standard`<small>クラス</small>
    * `<E> void appendInsertColumns(StringBuilder buff, Sql<E> sql)`
    * `<E> void appendInsertValues(StringBuilder buff, Sql<E> sql, List<Object> parameters)`
    * `<E> void appendUpdateColumnsAndValues(StringBuilder buff, Sql<E> sql, List<Object> parameters)`

* `org.lightsleep.helper.JoinInfo<JE>`<small>クラス</small>
    * `JoinInfo <small>(JoinType joinType, Sql<JE> joinSql, String tableAlias, Condition on)`
    * `Sql<JE> joinSql()`

* `org.lightsleep.helper.TypeConverter<ST, DT>`<small>クラス</small>
    * `static <ST, DT> TypeConverter<ST, DT> of(Class<ST> sourceType, Class<DT> destinType, Function<? super ST, ? extends DT> function)`
    * `static <ST, MT, DT> TypeConverter<ST, DT> of(Map<String, TypeConverter<?, ?>> typeConverterMap, Class<ST> sourceType, Class<MT> middleType, Class<DT> destinType)`
    * `static <ST, MT, DT> TypeConverter<ST, DT> of(Map<String, TypeConverter<?, ?>> typeConverterMap, Class<ST> sourceType, Class<MT> middleType, Class<DT> destinType, Function<? super MT, ? extends DT> function)`

**削除されたインタフェース**
* `org.lightsleep.entity.Composite`
* `org.lightsleep.entity.PostLoad`
* `org.lightsleep.entity.PreStore`

**削除されたメソッド**
* `org.lightsleep.Sql`<small>クラス</small>
    * `Sql<E> setColumns(Set<String> propertyNames)`
    * `Sql<E> setColumns(Class<?> resultClass)`
    * `Sql<E> doIf(boolean condition, Consumer<Sql<E>> action, Consumer<Sql<E>> elseAction)`

**引数または戻り値が変更されたメソッド**
* `org.lightsleep.database.Database<ST, DT>`<small>インタフェース</small>
    * `<E> String selectSql(Sql<E> sql, List<Object> parameters)`  
  → `<E> CharSequence selectSql(Sql<E> sql, List<Object> parameters)`
    * `<E> String subSelectSql(Sql<E> sql, List<Object> parameters)`  
  → `<E, OE> CharSequence subSelectSql(Sql<E> sql, Sql<OE> outerSql, List<Object> parameters)`
    * `<E> String subSelectSql(Sql<E> sql, Supplier<CharSequence> columnsSupplier, List<Object> parameters)`  
  → `<E, OE> CharSequence subSelectSql(Sql<E> sql, Sql<OE> outerSql, Supplier<CharSequence> columnsSupplier, List<Object> parameters)`
    * `<E> String insertSql(Sql<E> sql, List<Object> parameters)`  
  → `<E> CharSequence insertSql(Sql<E> sql, List<Object> parameters)`
    * `<E> String updateSql(Sql<E> sql, List<Object> parameters)`  
  → `<E> CharSequence updateSql(Sql<E> sql, List<Object> parameters)`
    * `<E> String deleteSql(Sql<E> sql, List<Object> parameters)`  
  → `<E> CharSequence deleteSql(Sql<E> sql, List<Object> parameters)`

## <small><small>バージョン</small></small> 3.2.0

**追加されたインタフェース**
* `org.lightsleep.entity.PostDelete`
* `org.lightsleep.entity.PostInsert`
* `org.lightsleep.entity.PostSelect`
* `org.lightsleep.entity.PostUpdate`
* `org.lightsleep.entity.PreDelete`
* `org.lightsleep.entity.PreUpdate`

**追加されたクラス**
* `org.lightsleep.database.MariaDB`
* `org.lightsleep.database.anchor.mariadb`

**仕様変更**
* `org.lightsleep.entity.#PreInsert##`インタフェースの`preInsert`メソッドの戻り値型を`int`から`[blue]#void`に変更しました。
* `org.lightsleep.entity.#Composite##`インタフェースの`postInsert`, `postUpdate`および`postDelete`メソッドの戻り値型を`int`から`void`に変更しました。
* `org.lightsleep.database.#DB2##`クラスを`[blue]#Db2`に変更しました。

**非推奨になったインタフェース**
* `org.lightsleep.entity.Composite`
* `org.lightsleep.entity.PostLoad`
* `org.lightsleep.entity.PreStore`


## <small><small>バージョン</small></small> 3.1.1

**バグ修正**
* サブクエリ条件からメインテーブルに結合しているテーブルのカラム名が参照できない。


## <small><small>バージョン</small></small> 3.1.0

**新機能**
* `FROM`句にサブクエリを使用した`SELECT SQL`の生成
* `UNION SQL`の生成

**仕様変更**
* `org.lightsleep.##Sql</small>columns(String \...)`メソッドを複数回呼び出した場合の仕様を変更しました。  
**本<small><small>バージョン</small></small>より前:** 引数のカラム配列が累積される。  
**本<small><small>バージョン</small></small>:** 引数のカラム配列に置き換えられる。

**追加されたメソッド**
* `org.lightsleep.Sql`<small>クラス</small>
    * `Sql<E> columns(Collection<String> propertyNames)`
    * `<RE> Sql<E> columns(Class<RE> resultClass)`
    * `Sql<E> from(Sql<?> fromSql)`
    * `Sql<?> getFrom()`
    * `<SE> Sql<E> where(Sql<SE> subSql, String content)`
    * `<SE> Sql<E> and(Sql<SE> subSql, String content)`
    * `<SE> Sql<E> or(Sql<SE> subSql, String content)`
    * `<SE> Sql<E> having(Sql<SE> subSql, String content)`
    * `<UE> Sql<E> union(Sql<UE> unionSql)`
    * `<UE> Sql<E> unionAll(Sql<UE> unionSql)`
    * `List<Sql<?>> getUnionSqls()`
    * `boolean isUnionAll()`

* `org.lightsleep.Condition`<small>インターフェース</small>
    * `static <E, SE> Condition of(Sql<E> outerSql, Sql<SE> subSql, String content)`
    * `default <K> Condition and(K entity)`
    * `default <E, SE> Condition and(Sql<E> outerSql, Sql<SE> subSql, String content)`
    * `default <K> Condition or(K entity)`
    * `default <E, SE> Condition or(Sql<E> outerSql, Sql<SE> subSql, String content)`

* `org.lightsleep.component.SubqueryCondition`<small>クラス</small>
    * `<E> SubqueryCondition(Sql<E> outerSql, Sql<SE> subSql, Expression expression)`

**非推奨になったメソッド**
* `org.lightsleep.Sql`<small>クラス</small>
    * `setColumns<small>(Set<String> propertyNames)`
    * `setColumns<small>(Class<?> resultClass)`

## <small><small>バージョン</small></small> 3.0.1

**変更**
* `SQLServer` データベース･ハンドラを使用した場合、`U+0080` 以上の文字コードを含む文字列リテラルは、`N` プレフィックス付き(例 `N'漢字'`)で生成するようにしました。
* `SQLite` データベース･ハンドラを使用した場合、`byte[]` のリテラルは、`X'hhhhhh'` 形式で生成するようにしました。(配列長が `maxBinaryLiteralLength` を超えない場合)

## <small><small>バージョン</small></small> 3.0.0

**改善**
* 以下のデータ型をサポートしました。エンティティクラスのフィールド型として使用できます。
    * `java.time.</small>LocalDate`
    * `java.time.</small>LocalTime`
    * `java.time.</small>LocalDateTime`
    * `java.time.</small>OffsetDateTime`
    * `java.time.</small>ZonedDateTime`
    * `java.time.</small>Instant`

**追加されたメソッドおよびコンストラクタ**
* `org.lightsleep.Sql`<small>クラス</small>
    * `doNotIf<small>(boolean condition, Consumer<Sql<E>> action)`
    * `doElse<small>(Consumer<Sql<E>> elseAction)`
    * `executeUpdate<small>(String sql)`

* `org.lightsleep.database.Database`<small>インタフェースおよびこれをインプリメントしているクラス</small>
    * `getObject<small>(Connection connection, ResultSet resultSet, String columnLabel)`

* `org.lightsleep.helper.ConvertException`<small>クラス</small>
    * `ConvertException<small>(Class<?> sourceType, Object source, Class<?> destinType, Throwable cause)`

* `org.lightsleep.helper.TypeConverter`<small>クラス</small>
    * `TypeConverter<small>(Class<ST> sourceType, Class<DT> destinType, Function<? super ST, MT> function1, Function<? super MT, ? extends DT> function2)`
    * `TypeConverter<small>(Class<ST> sourceType, Class<DT> destinType,Function<? super ST, ? extends MT1> function1, Function<? super MT1, ? extends MT2> function2, Function<? super MT2, ? extends DT> function3)`
    * `TypeConverter<small>(Class<ST> sourceType, Class<DT> destinType, Function<? super ST, MT1> function1, Function<? super MT1, ? extends MT2> function2, Function<? super MT2, ? extends MT3> function3, Function<? super MT3, ? extends DT> function4)`

**非推奨になったメソッド**
* `org.lightsleep.Sql`<small>クラス</small>
** `doIf<small>(boolean condition, Consumer<Sql<E>> action, Consumer<Sql<E>> elseAction)`

**削除されたメソッドおよびコンストラクタ**
* `org.lightsleep.Sql`<small>クラス</small>
    * `select<small>(ConnectionWrapper connection, Consumer<? super E> consumer)`
    * `select<small>(ConnectionWrapper connection, Consumer<? super E> consumer, Consumer<? super JE1> consumer1)`
    * `select<small>(ConnectionWrapper connection, Consumer<? super  E > consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2)`
    * `select<small>(ConnectionWrapper connection, Consumer<? super E> consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2, Consumer<? super JE3> consumer3)`
    * `select<small>(ConnectionWrapper connection, Consumer<? super E> consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2, Consumer<? super JE3> consumer3, Consumer<? super JE4> consumer4)`
    * `select<small>(ConnectionWrapper connection)`
    * `selectCount<small>(ConnectionWrapper connection)`
    * `insert<small>(ConnectionWrapper connection, E entity)`
    * `insert<small>(ConnectionWrapper connection, Iterable<? extends E> entities)`
    * `update<small>(ConnectionWrapper connection, E entity)`
    * `update<small>(ConnectionWrapper connection, Iterable<? extends E> entities)`
    * `delete<small>(ConnectionWrapper connection)`
    * `delete<small>(ConnectionWrapper connection, E entity)`
    * `delete<small>(ConnectionWrapper connection, Iterable<? extends E> entities)`

* `org.lightsleep.database.DB2##`, `MySQL`, `Oracle`, `PostgreSQL`, `SQLite`, `SQLServer`, `Standard`クラス</small>
    * `instance<small>()`

* `org.lightsleep.helperTypeConverter`<small>クラス</small>
    * `TypeConverter<small>(TypeConverter<ST, MT> typeConverter1, TypeConverter<MT, DT> typeConverter2)`

## <small><small>バージョン</small></small> 2.2.1

**バグ修正**
* [修正済] OracleのJDBCドライバjarがクラスパスにないと動作しない。

## <small><small>バージョン</small></small> 2.2.0

**改善**
* SQLのログに接続先のJDBC URLを含めるオプションを *追加* しました。  
  使用例:::
  `lightsleep.properties`ファイルに以下を追加  
  `connectionLogFormat = [{0}/{1}/{2}]`

* ログのパスワード部分を`"xxxx"`でマスクするようにしました。

`Database`インタフェースおよびその実装クラスに`maskPassword`メソッドを**追加**しました。

## <small><small>バージョン</small></small> 2.1.1

**バグ修正**
* [修正済] コネクション･サプライヤが`Jndi`の場合に常に`Standard`データベース･ハンドラが選択される。

**その他**
* ログメッセージの改善

## <small><small>バージョン</small></small> 2.1.0

<small><small>バージョン</small></small>番号はマイナー･リリースですが、**仕様変更があります**。

`lightsleep.properties`ファイルに、複数のJDBC URLの定義を**可能**にしました。

JDBC URLに対応するデータベース･ハンドラ･クラス**は自動的に判断**するようにしました。これに伴い`lightsleep.properties`ファイルの`Database`プロパティを**無効**にしました。**(仕様変更)**

以下のメソッド/コンストラクタを **追加** しました。
* `org.lightsleep.Sql`<small>クラス</small>
    * `public ConnectionWrapper getConnection()`

* `org.lightsleep.connection.ConnectionSupplier`<small>インタフェース</small>
    * `Database getDatabase()`
    * `DataSource getDataSource()`
    * `String getUrl()`
    * `static ConnectionSupplier of(String supplierName, Properties properties)`
    * `static ConnectionSupplier find(String... urlWords)`

* `org.lightsleep.connection.AbstractConnectionSupplier`<small>抽象クラス</small>
    * `protected AbstractConnectionSupplier(Properties properties, Consumer<Properties> modifier)`
    * `@Override public Database getDatabase()`
    * `@Override public String getUrl()`
    * `@Override public String toString()`

* `org.lightsleep.database.Database`<small>インタフェース</small>
    * `static Database getInstance(String jdbcUrl)`

* `org.lightsleep.helper.Resource`<small>クラス</small>
    * `public static Resource getGlobal()`

`org.lightsleep.Sql`クラスの以下のメソッドを**削除**しました。**(仕様変更)**
* `public static Database getDatabase()`
* `public static void setDatabase(Database database)`
* `public static ConnectionSupplier getConnectionSupplier()`
* `public static void setConnectionSupplier(ConnectionSupplier supplier)`

`org.lightsleep.connection.ConnectionWrapper##`クラスを追加し、各メソッドの引数の型を`java.sql.Connection##`から`[blue]#ConnectionWrapper`に**変更**しました。**(仕様変更)**

`[blue small]#org.lightsleep.connection#`パッケージの各クラスに`Properties properties`を引数とするコンストラクタを**追加**しました。

`[blue small]#org.lightsleep.database.anchor#`パッケージと`db2`, `mysql`, `oracle`, `postgresql`, `sqlite`, `sqlserver`クラスを**追加**しました。これらのクラスは、JDBC URLから対応するデータベース･ハンドラ･クラスを見つける際に使用されます。

`[blue small]#org.lightsleep.database#`パッケージの各クラスの`instance<small>()##`メソッドを **非推奨** にし、`[blue]#instance`静的変数を **追加** しました。

## <small><small>バージョン</small></small> 2.0.0

`org.lightsleep.Sql`クラスの型パラメータとは異なるエンティティ型でSELECT SQLの結果を取得する以下のメソッドを**追加**しました。
* `public <R> Optional<R> selectAs(Class<R> resultClass)`
* `public <R> void selectAs(Class<R> resultClass, Consumer<? super R> consumer)`

`org.lightsleep.Sql##`クラスの`Connection`引数を持つメソッドを**非推奨**にし、`[blue]#Connection`引数がない以下のメソッドを**追加**しました。
* `public void select(Consumer<? super E> consumer)`
* `public <JE1> void select(Consumer<? super E> consumer, Consumer<? super JE1> consumer1)`
* `public <JE1, JE2> void select(Consumer<? super E> consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2)`
* `public <JE1, JE2, JE3> void select(Consumer<? super  E> consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2, Consumer<? super JE3> consumer3)`
* `public <JE1, JE2, JE3, JE4> void select(Consumer<? super E> consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2, Consumer<? super JE3> consumer3, Consumer<? super JE4> consumer4)`
* `public Optional<E> select()`
* `public int selectCount()`
* `public int insert(E entity)`
* `public int insert(Iterable<? extends E> entities)`
* `public int update(E entity)`
* `public int update(Iterable<? extends E> entities)`
* `public int delete()`
* `public int delete(E entity)`
* `public int delete(Iterable<? extends E> entities)`

`org.lightsleep.Sql`クラスに以下のメソッドを**追加**しました。
* `public Sql<E> connection(Connection connection)`
* `public <R> Sql<E> setColumns(Class<R> resultClass)`
* `public Sql<E> doAlways(Consumer<Sql<E>> action)`

`org.lightsleep.Sql##`クラスが`[blue]#Cloneable`インタフェースを**実装**するようにしました。

`org.lightsleep.Sql##`クラスの`[blue]#where`メソッドの引数の仕様を**変更**しました。**(仕様変更)**
``
public Sql<E> where(E entity)  
    ↓
public <K> Sql<E> where(K entity)  
``

`Table`アノテーション･クラスに付与されていた`@Inherited`を**削除**しました。**(仕様変更)**

`Key`, `NonColumn`, `NonInsert`, `NonSelect`, `NonUpdate`アノテーション･クラスに`value`プロパティを**追加**しました。

`NonColumnProperty`, `NonInsertProperty`, `NonSelectProperty`, `NonUpdateProperty`アノテーション･クラスに`property`プロパティを**追加**し、`value`プロパティの仕様を**変更**しました。**(仕様変更)**

`org.lightsleep.component.Expression##`クラスの`toString`において、内容文字列の`{}`と引数の数が不一致の際にスローする例外を`IllegalArgumentException`から`[blue]#MissingArgumentsException`(新規追加)に**変更**にしました。**(仕様変更)**

`org.lightsleep.helper.Accessor##`クラスの`getField`, `getValue`, `setValue`メソッドでスローする例外を`IllegalArgumentException`から`[blue]#MissingPropertyException`(新規追加)に**変更**にしました。**(仕様変更)**

<div style="color:gray"><i>(C) 2015 Masato Kokubo (<small>小久保 雅人</small>)</i></div>
