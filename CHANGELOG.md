[Japanese](CHANGELOG_ja.md)

- [version 4.1.0-j17](#version-410-j17) - February 15, 2026
- [version 4.1.0](#version-410) - January   18, 2026
- [version 4.0.1](#version-401) - July       9, 2022
- [version 4.0.0](#version-400) - October    3, 2020
- [version 3.2.0](#version-320) - September 25, 2019
- [version 3.1.1](#version-311) - July      18, 2019
- [version 3.1.0](#version-310) - July      16, 2019
- [version 3.0.1](#version-301) - December   1, 2018
- [version 3.0.0](#version-300) - October    7, 2018
- [version 2.2.1](#version-221) - February  12, 2018
- [version 2.2.0](#version-220) - February   4, 2018
- [version 2.1.1](#version-211) - December   3, 2017
- [version 2.1.0](#version-210) - November  12, 2017
- [version 2.0.0](#version-200) - September  9, 2017

## <small>`version`</small> 4.1.0-j17

* Changed for Java 17.

## <small>`version`</small> 4.1.0

* Changed the repository from Maven to JitPack.
* Removed support for Db2.
* Changed the documentation format from AsciiDoc to Markdown.

## <small>`version`</small> 4.0.1

* Improved error messages.

## <small>`version`</small> 4.0.0

**NEW Features**
    You can now generate the following SQLs.

* `SELECT SQL` with `WITH` clauses`WITH W1(...) AS (``  SELECT ...``)``SELECT ... FROM W1 ...`
* Recursive `SELECT SQL``WITH RECURSIVE W1(...) AS (``  SELECT ...``  UNION ALL``  SELECT ...``) SELECT ... FROM W1 ...`
* `INSERT SQL` with subquery`INSERT INTO ... (...) SELECT ... FROM ...`
* Join subqueries
  `SELECT ... FROM ... INNER JOIN (SELECT ...) ...`

**Added Methods**

* `org.lightsleep.Sql` class

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
* `org.lightsleep.database.Standard` class

  * `<E> void appendInsertColumns(StringBuilder buff, Sql<E> sql)`
  * `<E> void appendInsertValues(StringBuilder buff, Sql<E> sql, List<Object> parameters)`
  * `<E> void appendUpdateColumnsAndValues(StringBuilder buff, Sql<E> sql, List<Object> parameters)`
* `org.lightsleep.helper.JoinInfo<JE>` class

  * `JoinInfo# (JoinType joinType, Sql<JE> joinSql, String tableAlias, Condition on)`
  * `Sql<JE> joinSql()`
* `org.lightsleep.helper.TypeConverter<ST, DT>` class

  * `static <ST, DT> TypeConverter<ST, DT> of(Class<ST> sourceType, Class<DT> destinType, Function<? super ST, ? extends DT> function)`
  * `static <ST, MT, DT> TypeConverter<ST, DT> of(Map<String, TypeConverter<?, ?>> typeConverterMap, Class<ST> sourceType, Class<MT> middleType, Class<DT> destinType)`
  * `static <ST, MT, DT> TypeConverter<ST, DT> of(Map<String, TypeConverter<?, ?>> typeConverterMap, Class<ST> sourceType, Class<MT> middleType, Class<DT> destinType, Function<? super MT, ? extends DT> function)`

**Deleted Interfaces**

* `org.lightsleep.entity.Composite`
* `org.lightsleep.entity.PostLoad`
* `org.lightsleep.entity.PreStore`

**Deleted Methods**

* `org.lightsleep.Sql## class`
  * `Sql<E> setColumns(Set<String> propertyNames)`
  * `Sql<E> setColumns(Class<?> resultClass)`
  * `Sql<E> doIf(boolean condition, Consumer<Sql<E>> action, Consumer<Sql<E>> elseAction)`

**Methods with modified argument or return value**

* `org.lightsleep.database.Database<ST, DT>## #interface`
  * `<E> String selectSql(Sql<E> sql, List<Object> parameters)`→ `<E># [small red]#CharSequence selectSql(Sql<E> sql, List<Object> parameters)`
  * `<E> String subSelectSql(Sql<E> sql, List<Object> parameters)`→ `<E,# [small red]#OE> CharSequence subSelectSql(Sql<E> sql,## [small red]#Sql<OE> outerSql,# List<Object> parameters)`
  * `<E> String subSelectSql(Sql<E> sql, Supplier<CharSequence> columnsSupplier, List<Object> parameters)`→ `<E,# [small red]#OE> CharSequence subSelectSql(Sql<E> sql,## [small red]#Sql<OE> outerSql,# Supplier<CharSequence> columnsSupplier, List<Object> parameters)`
  * `<E> String insertSql(Sql<E> sql, List<Object> parameters)`→ `<E># [small red]#CharSequence insertSql(Sql<E> sql, List<Object> parameters)`
  * `<E> String updateSql(Sql<E> sql, List<Object> parameters)`→ `<E># [small red]#CharSequence updateSql(Sql<E> sql, List<Object> parameters)`
  * `<E> String deleteSql(Sql<E> sql, List<Object> parameters)`
    → `<E># [small red]#CharSequence deleteSql(Sql<E> sql, List<Object> parameters)`

## <small>`version`</small> 3.2.0

**Added Interfaces**

* `org.lightsleep.entity.PostDelete`
* `org.lightsleep.entity.PostInsert`
* `org.lightsleep.entity.PostSelect`
* `org.lightsleep.entity.PostUpdate`
* `org.lightsleep.entity.PreDelete`
* `org.lightsleep.entity.PreUpdate`

**Added Classes**

* `org.lightsleep.database.MariaDB`
* `org.lightsleep.database.anchor.mariadb`

**Specification Changes**

* Changed the return type of `org.lightsleep.entity.#PreInsert` method of `preInsert` interface from `int` to `void`.
* Changed the return type of `postInsert`, `postUpdate` and `postDelete` method of `org.lightsleep.entity.#Composite` interface from `int` to `void`.
* Changed `org.lightsleep.database.#DB2` class to `Db2`.

**Deprecated Interfaces**

* `org.lightsleep.entity.Composite`
* `org.lightsleep.entity.PostLoad`
* `org.lightsleep.entity.PreStore`

## <small>`version`</small> 3.1.1

**Bug Fix**

* Can not refer to column names of the table joined to the main table from the subquery condition.

## <small>`version`</small> 3.1.0

**NEW Features**

* You can now generate `SELECT SQL` using subqueries in the `FROM` clause.
* You can now generate `UNION SQL`

**Specification Change**

* Changed the specification when calling the `org.lightsleep.##Sqlcolumns(String ...)` method multiple times.
  **Prior to this version:** Columns of the argument value are accumulated.
  **This version:** Replaced by the columns of the argument value.

**Added Methods**

* `org.lightsleep.Sql` class

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
* `org.lightsleep.Condition` interface

  * `static <E, SE> Condition of(Sql<E> outerSql, Sql<SE> subSql, String content)`
  * `default <K> Condition and(K entity)`
  * `default <E, SE> Condition and(Sql<E> outerSql, Sql<SE> subSql, String content)`
  * `default <K> Condition or(K entity)`
  * `default <E, SE> Condition or(Sql<E> outerSql, Sql<SE> subSql, String content)`
* `org.lightsleep.component.SubqueryCondition` class

  * `<E> SubqueryCondition(Sql<E> outerSql, Sql<SE> subSql, Expression expression)`

**Deprecated Methods**

* `org.lightsleep.Sql` class
  * `setColumns(Set<String> propertyNames)`
  * `setColumns(Class<?> resultClass)`

## <small>`version`</small> 3.0.1

**Changes**

* When using the `SQLServer` database handler, string literals containing the character code of `U+0080` and above are now generated with `N` prefix (e.g. `N'漢字'`).
* When using the `SQLite` database handler, literals of `byte[]` are now generated in the `X'hhhhhh'` format if the array length does not exceed the `maxBinaryLiteralLength`.

## <small>`version`</small> 3.0.0

**Improvement**

* Supported the following data types. You can use them as a field type for entity classes.
  * `java.time.LocalDate`
  * `java.time.LocalTime`
  * `java.time.LocalDateTime`
  * `java.time.OffsetDateTime`
  * `java.time.ZonedDateTime`
  * `java.time.Instant`

**Added Methods and Constructors**

* `org.lightsleep.Sql` class

  * `doNotIf(boolean condition, Consumer<Sql<E>> action)`
  * `doElse(Consumer<Sql<E>> elseAction)`
  * `executeUpdate(String sql)`
* `org.lightsleep.database.Database` interface and classes implementing it

  * `getObject(Connection connection, ResultSet resultSet, String columnLabel)`
* `org.lightsleep.helper.ConvertException` class

  * `ConvertException(Class<?> sourceType, Object source, Class<?> destinType, Throwable cause)`
* `org.lightsleep.helper.TypeConverter` class

  * `TypeConverter(Class<ST> sourceType, Class<DT> destinType, Function<? super ST, MT> function1, Function<? super MT, ? extends DT> function2)`
  * `TypeConverter(Class<ST> sourceType, Class<DT> destinType,Function<? super ST, ? extends MT1> function1, Function<? super MT1, ? extends MT2> function2, Function<? super MT2, ? extends DT> function3)`
  * `TypeConverter(Class<ST> sourceType, Class<DT> destinType, Function<? super ST, MT1> function1, Function<? super MT1, ? extends MT2> function2, Function<? super MT2, ? extends MT3> function3, Function<? super MT3, ? extends DT> function4)`

**Deprecated Method**

* `org.lightsleep.Sql` class
  * `doIf(boolean condition, Consumer<Sql<E>> action, Consumer<Sql<E>> elseAction)`

**Deleted** Methods and Constructor

* `org.lightsleep.Sql` class

  * `select(ConnectionWrapper connection, Consumer<? super E> consumer)`
  * `select(ConnectionWrapper connection, Consumer<? super E> consumer, Consumer<? super JE1> consumer1)`
  * `select(ConnectionWrapper connection, Consumer<? super  E > consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2)`
  * `select(ConnectionWrapper connection, Consumer<? super E> consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2, Consumer<? super JE3> consumer3)`
  * `select(ConnectionWrapper connection, Consumer<? super E> consumer, Consumer<? super JE1> consumer1, Consumer<? super JE2> consumer2, Consumer<? super JE3> consumer3, Consumer<? super JE4> consumer4)`
  * `select(ConnectionWrapper connection)`
  * `selectCount(ConnectionWrapper connection)`
  * `insert(ConnectionWrapper connection, E entity)`
  * `insert(ConnectionWrapper connection, Iterable<? extends E> entities)`
  * `update(ConnectionWrapper connection, E entity)`
  * `update(ConnectionWrapper connection, Iterable<? extends E> entities)`
  * `delete(ConnectionWrapper connection)`
  * `delete(ConnectionWrapper connection, E entity)`
  * `delete(ConnectionWrapper connection, Iterable<? extends E> entities)`
* `org.lightsleep.database.DB2`, `MySQL`, `Oracle`, `PostgreSQL`, `SQLite`, `SQLServer` and `Standard` class

  * `instance()`
* `org.lightsleep.helperTypeConverter` class

  * `TypeConverter(TypeConverter<ST, MT> typeConverter1, TypeConverter<MT, DT> typeConverter2)`

## <small>`version`</small> 2.2.1

**Bug fix**

* [Fixed] Lightsleep does not work unless the Oracle JDBC driver jar is in the classpath.

## <small>`version`</small> 2.2.0

**Improvements**

* *Added* an option to include JDBC URL of the connection to SQL logs.Example of use:::
  Add the following to `lightsleep.properties` file`connectionLogFormat = [{0}/{1}/{2}]`
* The password parts of the logs are masked with `"xxxx"`.

*Added* `maskPassword` method to the `Database` interface and its implementation classes.

## <small>`version`</small> 2.1.1

**Bug fix**

* [Fixed] `Standard` database handler is always selected when connection supplier is `Jndi`.

**Other**

* Improve log messages

## <small>`version`</small> 2.1.0

Version number is a minor release, but there are **some specification changes**.

**Enabled** the definition of multiple JDBC URLs in the `lightsleep.properties` file.

Database handler classes corresponding to JDBC URLs are now **automatically determined**, and **disabled** the `Database` property in `lightsleep.properties` file. **(Specification change)**

**Added** the following methods and constructor.

* `org.lightsleep.Sql` class

  * `public ConnectionWrapper getConnection()`
* `org.lightsleep.connection.ConnectionSupplier` interface

  * `Database getDatabase()`
  * `DataSource getDataSource()`
  * `String getUrl()`
  * `static ConnectionSupplier of(String supplierName, Properties properties)`
  * `static ConnectionSupplier find(String... urlWords)`
* `org.lightsleep.connection.AbstractConnectionSupplier` abstract class

  * `protected AbstractConnectionSupplier(Properties properties, Consumer<Properties> modifier)`
  * `@Override public Database getDatabase()`
  * `@Override public String getUrl()`
  * `@Override public String toString()`
* `org.lightsleep.database.Database` interface

  * `static Database getInstance(String jdbcUrl)`
* `org.lightsleep.helper.Resource` class

  * `public static Resource getGlobal()`

**Deleted** the following methods of the `org.lightsleep.Sql` *class*. **(Specification change)**

* `public static Database getDatabase()`
* `public static void setDatabase(Database database)`
* `public static ConnectionSupplier getConnectionSupplier()`
* `public static void setConnectionSupplier(ConnectionSupplier supplier)`

**Added** the `org.lightsleep.connection.ConnectionWrapper` class, and **changed** the argument type of each method from `java.sql.Connection` to `ConnectionWrapper`. **(Specification change)**

**Added** a constructor with `Properties properties` argument to each class of the `[blue small]#org.lightsleep.connection` package.

**Added** the `[blue small]#org.lightsleep.database.anchor` package and `db2`, `mysql`, `oracle`, `postgresql`, `sqlite` and `sqlserver` classes. These classes are used to find the corresponding database handler class from the JDBC URL.

**Deprecated** the `instance()` methods and **added** `instance` static variables of each class in the `[blue small]#org.lightsleep.database` package.

## <small>`version`</small> 2.0.0

Added the following method to get the result of SELECT SQL with entity type different from type parameter of `org.lightsleep.Sql` class.

* `public <R> Optional<R> selectAs(Class<R> resultClass)`
* `public <R> void selectAs(Class<R> resultClass, Consumer<? super R> consumer)`

**Deprecated** the method with the `Connection` argument of the `org.lightsleep.Sql` class and **added** the following method with no `Connection` argument.

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

**Added** the following method to the `org.lightsleep.Sql` class.

* `public Sql<E> connection(Connection connection)`
* `public <R> Sql<E> setColumns(Class<R> resultClass)`
* `public Sql<E> doAlways(Consumer<Sql<E>> action)`

The `org.lightsleep.Sql` class now **implements** the `Cloneable` interface.

**Changed** the specification of the argument of the `where` method of the `org.lightsleep.Sql` class **(Specification change)**
``public Sql<E> where(E entity)     ↓ public <K> Sql<E> where(K entity)``

**Deleted** `@Inherited` attached to `Table` annotation class. **(Specification change)**

**Added** `value` property to `Key`, `NonColumn`, `NonInsert`, `NonSelect` and `NonUpdate` annotation classes.

**Added** a `property` property to the `NonColumnProperty`, `NonInsertProperty`, `NonSelectProperty` and `NonUpdateProperty` annotation classes and changed the specification of the `value` property. **(Specification change)**

**Changed** the exception thrown on `toString` of `org.lightsleep.component.Expression` class when number of `{}` in the content string and arguments dose not match from `IllegalArgumentException` to `MissingArgumentsException` *(new class)*. **(Specification change)**

**Changed** the exception thrown on `getField`, `getValue` and `setValue` methods of `org.lightsleep.helper.Accessor` class from `IllegalArgumentException` to `MissingPropertyException` *(new class)*. **(Specification change)**

<div style="color:gray"><i>(C) 2015 Masato Kokubo</i></div>
