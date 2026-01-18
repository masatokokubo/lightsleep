# Lightsleep <small>4.0.0 User's Guide</small>

[Japanese](UserGuide_ja.md)

### Table of Contents

[1. Packages](#1-packages)  
[2. Create entity classes](#2-create-entity-classes)  
[2-1. Annotations to be used in entity classes](#2-1-annotations-to-be-used-in-entity-classes)  
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
[2-1-12. @KeyProperty, @ColumnProperty, ... and @UpdateProperty](#2-1-12-keyproperty-columnproperty--and-updateproperty)  
[2-2. Interfaces implemented by entity classes](#2-2-interfaces-implemented-by-entity-classes)  
[2-2-1. PreInsert Interface](#2-2-1-preinsert-interface)  
[2-2-2. PreUpdate Interface](#2-2-2-preupdate-interface-since-320)  
[2-2-3. PreDelete Interface](#2-2-3-predelete-interface-since-320)  
[2-2-4. PostInsert Interface](#2-2-4-postinsert-interface-since-320)  
[2-2-5. PostUpdate Interface](#2-2-5-postupdate-interface-since-320)  
[2-2-6. PostDelete Interface](#2-2-6-postdelete-interface-since-320)  
[2-2-7. PostSelect Interface](#2-2-7-postselect-interface-since-320)  
[2-2-8. Composite Interface](#2-2-8-composite-interface-deprecated-since-320)  
[3. Definition of lightsleep.properties](#3-definition-of-lightsleepproperties)  
[3-1. Logging library class](#3-1-logging-library-class)  
[3-2. Database handler class](#3-2-database-handler-class)  
[3-3. Connection supplier class](#3-3-connection-supplier-class)  
[4. Transaction](#4-transaction)  
[5. Execution of SQL](#5-execution-of-sql)  
[5-1. SELECT](#5-1-select)  
[5-1-1. SELECT 1 row with an Expression condition](#5-1-1-select-1-row-with-an-expression-condition)  
[5-1-2. SELECT 1 row with an Entity condition](#5-1-2-select-1-row-with-an-entity-condition)  
[5-1-3. SELECT multiple rows with an Expression condition](#5-1-3-select-multiple-rows-with-an-expression-condition)  
[5-1-4. SELECT with a Subquery condition](#5-1-4-select-with-a-subquery-condition)  
[5-1-5. SELECT with Expression conditions (AND)](#5-1-5-select-with-expression-conditions-and)  
[5-1-6. SELECT with Expression Condition (OR)](#5-1-6-select-with-expression-condition-or)  
[5-1-7. SELECT with Expression conditions A AND B OR C AND D](#5-1-7-select-with-expression-conditions-a-and-b-or-c-and-d)  
[5-1-8. SELECT with selection of columns](#5-1-8-select-with-selection-of-columns)  
[5-1-9. SELECT with GROUP BY and HAVING](#5-1-9-select-with-group-by-and-having)  
[5-1-10. SELECT with ORDER BY, OFFSET and LIMIT](#5-1-10-select-with-order-by-offset-and-limit)  
[5-1-11. SELECT with FOR UPDATE](#5-1-11-select-with-for-update)  
[5-1-12. SELECT with INNER JOIN](#5-1-12-select-with-inner-join)  
[5-1-13. SELECT with LEFT OUTER JOIN](#5-1-13-select-with-left-outer-join)  
[5-1-14. SELECT with RIGHT OUTER JOIN](#5-1-14-select-with-right-outer-join)  
[5-1-15. SELECT COUNT(\*)](#5-1-15-select-count)  
[5-1-16. SELECT FROM Clause Subquery](#5-1-16-select-from-clause-subquery)  
[5-1-17. SELECT UNION, UNION ALL](#5-1-17-select-union-union-all)  
[5-1-18. SELECT WITH clause](#5-1-18-select-with-clause)  
[5-1-19. SELECT RECURSIVE](#5-1-19-select-recursive)  
[5-2. INSERT](#5-2-insert)  
[5-2-1. INSERT 1 row](#5-2-1-insert-1-row)  
[5-2-2. INSERT multiple rows](#5-2-2-insert-multiple-rows)  
[5-3. UPDATE](#5-3-update)  
[5-3-1. UPDATE 1 row](#5-3-1-update-1-row)  
[5-3-2. UPDATE multiple rows](#5-3-2-update-multiple-rows)  
[5-3-3. UPDATE with a Condition and selection of columns](#5-3-3-update-with-a-condition-and-selection-of-columns)  
[5-3-4. UPDATE all rows](#5-3-4-update-all-rows)  
[5-4. DELETE](#5-4-delete)  
[5-4-1. DELETE 1 row](#5-4-1-delete-1-row)  
[5-4-2. DELETE multiple rows](#5-4-2-delete-multiple-rows)  
[5-4-3. DELETE with a Condition](#5-4-3-delete-with-a-condition)  
[5-4-4. DELETE all rows](#5-4-4-delete-all-rows)  
[6. Expression Conversion](#6-expression-conversion)  
[7. Mappings of Java types and column types in datetime types](#7-mappings-of-java-types-and-column-types-in-datetime-types)  
[7.1 MariaDB, MySQL](#72-mariadb-mysql)  
[7.2 Oracle](#73-oracle)  
[7.3 PostgreSQL](#74-postgresql)  
[7.4 SQLite](#75-sqlite)  
[7.5 SQL Server](#76-sql-server)  


[To TOC](#table-of-contents)

### 1. Packages

Has the following packages.

**Packages**

| Package | Contained classes and interfaces |
|---|---|
| `org.lightsleep` | Classes you use primarily |
| `org.lightsleep.component` | Classes you use to create SQL components such as conditions and expressions |
| `org.lightsleep.connection` | Classes that supply connection wrapper classes to this library using various connection pool libraries |
| `org.lightsleep.database` | Classes for generating SQL for various DBMSs |
| `org.lightsleep.database.anchor` | Classes used in mapping words contained in JDBC URLs to classes in the `org.lightsleep.database`package |
| `org.lightsleep.entity` | Annotation classes and interfaces you use when creating entity classes |
| `org.lightsleep.helper` | Helper classes used inside this library |
| `org.lightsleep.logger` | Classes that output logs inside this library using various logging libraries |


[To TOC](#table-of-contents)

### 2. Create entity classes

Create corresponding entity classes for each table in the database.


#### 2-1. Annotations to be used in entity classes

Lihgtsleep automatically associates with tables in methods with an entity class or object as an argument, but you may also need to use annotations for entity classes.

Lightsleep has the following annotations.

**Annotations**

| Annotation Type | Element(s) | Content of Indication | Target |
|---|---|---|---|
| [`@Table`](#entity-table) | `String value` | Related table name | Class |
| [`@Key`](#entity-key) | `boolean value` (default: `true`) | Related to the primary key | Field |
| [`@Column`](#entity-column) | `String value` | Related column name | Field |
| [`@ColumnType`](#entity-columntype) | `Class<?> value` | Related column type | Field |
| [`@NonColumn`](#entity-noncolumn) | `boolean value` (default: `true`) | Not related to any column | Field |
| [`@NonSelect`](#entity-nonselect) | `boolean value` (default: `true`) | Not used in SELECT SQL | Field |
| [`@NonInsert`](#entity-noninsert) | `boolean value` (default: `true`) | Not used in INSERT SQL | Field |
| [`@NonUpdate`](#entity-nonupdate) | `boolean value` (default: `true`) | Not used in UPDATE SQL | Field |
| [`@Select`](#entity-select) | `String value` | Expression used in SELECT SQL | Field |
| [`@Insert`](#entity-insert) | `String value` | Expression used in INSERT SQL | Field |
| [`@Update`](#entity-update) | `String value` | Expression used in UPDATE SQL | Field |
| [`@KeyProperty`](#entity-xxxxxproperty) | `String property`,<br>`boolean value` (default: `true`) | Related to the primary key | Class |
| [`@ColumnProperty`](#entity-xxxxxproperty) | `String property`,<br>`String column` | Related column name | Class |
| [`@ColumnTypeProperty`](#entity-xxxxxproperty) | `String property`,<br>`Class<?> type` | Related column type | Class |
| [`@NonColumnProperty`](#entity-xxxxxproperty) | `String property`,<br>`boolean value` (default: `true`) | Not related to any columns | Class |
| [`@NonSelectProperty`](#entity-xxxxxproperty) | `String property`,<br>`boolean value` (default: `true`) | Not used in SELECT SQL | Class |
| [`@NonInsertProperty`](#entity-xxxxxproperty) | `String property`,<br>`boolean value` (default: `true`) | Not used in INSERT SQL | Class |
| [`@NonUpdateProperty`](#entity-xxxxxproperty) | `String property`,<br>`boolean value` (default: `true`) | Not used in UPDATE SQL | Class |
| [`@SelectProperty`](#entity-xxxxxproperty) | `String property`,<br>`String expression` | Expression used in SELECT SQL | Class |
| [`@InsertProperty`](#entity-xxxxxproperty) | `String property`,<br>`String expression` | Expression used in INSERT SQL | Class |
| [`@UpdateProperty`](#entity-xxxxxproperty) | `String property`,<br>`String expression` | Expression used in UPDATE SQL | Class |


[To TOC](#table-of-contents)  
[To Annotation List](#entity-annotation)

##### 2-1-1. @Table

Specifies the table name related to the class.  
If the table name is the same as the class name, you do not need to specify this annotation.

```java
// Example in Java
@Table("Contact")
public class Person extends PersonBase {

    @Table("super")
     public static class Ex extends Person {
```

If you specify `@Table("super")`, the class name of the superclass is the table name.


##### 2-1-2. @Key

Indicates that the column related to the field is part of the primary key.

```java
// Example in Java
@Key
public int contactId;
@Key
public short featureIndex;
```

##### 2-1-3. @Column

Indicates the name of column related to the field.  
If the column name is the same as the field name, you do not need to specify it.

```java
// Example in Java
@Column("firstName")
public String first;
@Column("lastName")
public String last;
```

##### 2-1-4. @ColumnType

Indicates the type of column related to the field.  
If the field type and column type are the same type, you do not need to specify it.  
Specify if field type (e.g. date type) and column type (e.g. numerical type) are different.

```java
// Example in Java
@ColumnType(Long.class)
public LocalDate birthday;
```

[To TOC](#table-of-contents)  
[To Annotation List](#entity-annotation)

##### 2-1-5. @NonColumn

Indicates that the field not related to any column.

```java
// Example in Java
@NonColumn
public List<Phone> phones;
@NonColumn
public List<Address> addresses;
```


##### 2-1-6. @NonSelect

Indicates that the column related the field is not used in SELECT SQL.

```java
// Example in Java
@NonSelect
public LocalDateTime createdTime;
@NonSelect
public LocalDateTime updatedTime;
```

##### 2-1-7. @NonInsert

Indicates that the column related the field is not used in INSERT SQL.

```java
// Example in Java
@NonInsert
public LocalDateTime createdTime;
@NonInsert
public LocalDateTime updatedTime;
```

##### 2-1-8. @NonUpdate

Indicates that the column related the field is not used in UPDATE SQL.

```java
// Example in Java
@NonUpdate
public LocalDateTime createdTime;
```

[To TOC](#table-of-contents)  
[To Annotation List](#entity-annotation)

##### 2-1-9. @Select

Indicates a column expression instead of the column name in SELECT SQL.

```java
// Example in Java
@Select("{firstName}||' '||{lastName}")
@NonInsert@NonUpdate
public String fullName;
```

##### 2-1-10. @Insert

Indicates an expression instead of the field value in INSERT SQL.  
If this annotation is specified, the value of the field is not used.

```java
// Example in Java
@Insert("CURRENT_TIMESTAMP")
public LocalDateTime createdTime;
@Insert("CURRENT_TIMESTAMP")
public LocalDateTime updatedTime;
```

##### 2-1-11. @Update

Indicates an expression instead of the field value in UPDATE SQL.  
If this annotation is specified, the value of the field is not used.

```java
// Example in Java
@Update("{updateCount}+1")
public int updateCount;
@Update("CURRENT_TIMESTAMP")
public LocalDateTime updatedTime;
```

[To TOC](#table-of-contents)  
[To Annotation List](#entity-annotation)

##### 2-1-12. @KeyProperty, @ColumnProperty, ... and @UpdateProperty

These annotations are used to specify for fields defined in superclass.  
The specified contents also affects subclasses, but specifications in the subclass takes precedence.  
If you specify `value=false`, `column=""`, `type=Void.class` or `expression=""`, specifications in the superclass are canceled.

```java
// Example in Java
@KeyProperty(property="contactId")
@KeyProperty(property="featureIndex")
public class ContactFeature extends ContactFeatureKey {
```

### 2-2. Interfaces implemented by entity classes

[To TOC](#table-of-contents)

#### 2-2-1. PreInsert <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`insert(E)` and `insert(Iterable)` methods of Sql<E> class invoke `preInsert` method of the entity class before executing INSERT SQL.

You can implement primary key numbering using `preInsert` method.

```java
// Example in Java
public abstract class Common implements PreInsert {
    @Key
    public int id;

    @Override
    public void preInsert(ConnectionWrapper conn) {
        id = Numbering.getNewId(conn, getClass());
    }
}
```

[To TOC](#table-of-contents)

#### 2-2-2. PreUpdate <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`update(E)` and `update(Iterable)` methods of Sql<E> class invoke `preUpdate` method of the entity class before executing UPDATE SQL.


[To TOC](#table-of-contents)

#### 2-2-3. PreDelete <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`delete(E)` and `delete(Iterable)` methods of Sql<E> class invoke `preDelete` method of the entity class before executing DELETE SQL.

[To TOC](#table-of-contents)

#### 2-2-4. PostInsert <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`insert(E)` and `insert(Iterable)` method of Sql<E> class invoke `postInsert` method of the entity class after executing INSERT SQL.

If an entity is enclose another entity, by implementing this interface,  
You can perform SQL processing to the enclosed entity in conjunction the entity which encloses.

And you can use `postInsert` method to get the value automatically numbered at the time of insertion.

```java
// Example in Java
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

[To TOC](#table-of-contents)

#### 2-2-5. PostUpdate <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`update(E)` and `update(Iterable)` methods of Sql<E> class invoke `postUpdate` method of the entity class after executing UPDATE SQL.

If an entity is enclose another entity, by implementing this interface,  
You can perform SQL processing to the enclosed entity in conjunction the entity which encloses.

[To TOC](#table-of-contents)

#### 2-2-6. PostDelete <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`delete(E)` and `delete(Iterable)` method of Sql<E> class invoke `postDelete` method of the entity class after executing DELETE SQL.

If an entity is enclose another entity, by implementing this interface,  
You can perform SQL processing to the enclosed entity in conjunction the entity which encloses.

[To TOC](#table-of-contents)

#### 2-2-7. PostSelect <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`select` methods of Sql<E> class invoke `postSelect` method of the entity class after each entity is retrieved by executing SELECT SQL.

If an entity is enclose another entity, by implementing this interface,  
You can perform SQL processing to the enclosed entity in conjunction the entity which encloses.

[To TOC](#table-of-contents)

#### 2-2-8. Composite <span style="font-size:0.85em;">Interface</span>

If an entity class implements this interface,  
`select`, `insert(E)`, `update(E)` and `delete(E)` methods of `Sql<E>` class invoke `postSelect`, `postInsert`, `postUpdate` or `postDelete` method of the entity class after the execution of each SQL.

If an entity is enclose another entity, by implementing this interface,  
You can perform SQL processing to the enclosed entity in conjunction the entity which encloses.

```java
// Example in Java
@Table("super")
public class ContactComposite extends Contact implements Composite {
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
    public void postInsert(ConnectionWrapper conn) {
        phones.forEach(phone -> phone.contactId = id);
        int count = new Sql<>(Phone.class)
            .connection(conn)
            .insert(phones);
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
                .collect(Collectors.toList())) ;

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
            .where("{contactId}={}", id)
            .connection(conn)
            .delete();
    }
```


[To TOC](#table-of-contents)

### 3. Definition of lightsleep.properties

Lightsleep.properties is a properties file referenced by Lightsleep and you can specify the following contents.  
*(The `Database` property up to version 2.0.0 has been removed in version 2.1.0, the database handler is automatically determined from the corresponding JDBC URL.)*

|Property Name           |Content                                                |Default Value
|:-----------------------|:------------------------------------------------------|:------------
|[`Logger`](#logger)     |Logging class                                          |`Std$Out$Info`
|[`ConnectionSupplier`](#connectionsupplier)|Connection Supplier class           |`Jdbc`
|`url`                   |JDBC URL                                               |_None_
|`urls`                  |JDBC URLs                                              |_None_
|`dataSource`            |Data source name when using `Jndi`                     |_None_
|`dataSources`           |Data source names when using `Jndi`                    |_None_
|`maxStringLiteralLength`|Maximum length of string literals when generates SQL   |128
|`maxBinaryLiteralLength`|Maximum length of binary literals when generates SQL   |128
|`maxLogStringLength`    |Maximum length of string values output to log | 200    |
|`maxLogByteArrayLength` |Maximum number of elements of byte arrays output to log|200
|`maxLogArrayLength`     |Maximum number of elements of arrays output to log     |100
|`maxLogMapSize`         |Maximum number of elements of maps output to log       |100
|`connectionLogFormat`   |The log output format of `ConnectionSupplier`<br><br>**String replacements:**<br>**{0}**: To the simple class name of the database handler<br>**{1}**: To the simple class name of the connection supplier<br>**{2}**: To the JDBC URL of the connection|`[{0}/{1}]`

Place the `lightsleep.properties` file in one of the class paths. Or you can specify the file path with the system property `lightsleep.resource`. *(java -Dlightsleep.resource=...)*  
In addition to the above define the properties used by the connection pool library.

Example of lightsleep.properties:

```properties
# lightsleep.properties
Logger      = Log4j2
ConnectionSupplier = Dbcp
url         = jdbc:postgresql://postgresqlserver/example
username    = example
password    = _example_
initialSize = 10
maxTotal    = 100
```

You can specify multiple JDBC URLs in the `urls` property separated by commas.  
If you define a property with more than one line, append a backslash (`\`) to the end of the line other than the last line.  
If you specify `urls`, the specification of `url` will be invalid.

```properties
# lightsleep.properties - Case of specifying multiple JDBC URLs
Logger      = Log4j2
ConnectionSupplier = Dbcp
urls        = jdbc:postgresql://postgresqlserver/example1,\
              jdbc:postgresql://postgresqlserver/example2
user        = example
password    = _example_
initialSize = 10
maxTotal    = 100
```

You can specify a different DBMS URL for each JDBC URL. If the user and password are different for each JDBC URL, specify them in the URL.

```properties
# lightsleep.properties - Case of using multiple DBMS (specifying user and password in URL)
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

To specify a connection supplier for each URL, write it within `[]` at the head of the URL.  
The specification of this form takes precedence over the specification of `ConnectionSupplier` property.  
You can specify the `username` and `jdbcUrl` property with the `user` and `url` property, but specify properties other than those with the property name specific to the connection pool library.

```properties
# lightsleep.properties - Case of specifying a connection supplier for each URL
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

[To TOC](#table-of-contents) [To Properties List](#lightsleep-properties)

#### 3-1. Logging library class

Select the value of the `Logger` property from the following.

|Value          |Logging library etc. |Log level|Definition file used by the logging library
|:--------------|:--------------------|:-------:|:------------------------------------------
|`Jdk`          |Java Runtime         |-        |logging.properties
|`Log4j`        |Log4j                |-        |log4j.properties or log4j.xml
|`Log4j2`       |Log4j 2              |-        |log4j2.xml
|`SLF4J`        |SLF4J                |-        |Depends on target logging library implementation
|`Std$Out$Trace`|Output to System.out |trace    |_nothing_
|`Std$Out$Debug`|_(same as above)_    |debug    |_nothing_
|`Std$Out$Info` |_(same as above)_    |info     |_nothing_
|`Std$Out$Warn` |_(same as above)_    |warn     |_nothing_
|`Std$Out$Error`|_(same as above)_    |error    |_nothing_
|`Std$Out$Fatal`|_(same as above)_    |fatal    |_nothing_
|`Std$Err$Trace`|Output to System.err |trace    |_nothing_
|`Std$Err$Debug`|_(same as above)_    |debug    |_nothing_
|`Std$Err$Info` |_(same as above)_    |info     |_nothing_
|`Std$Err$Warn` |_(same as above)_    |warn     |_nothing_
|`Std$Err$Error`|_(same as above)_    |error    |_nothing_
|`Std$Err$Fatal`|_(same as above)_    |fatal    |_nothing_

If you do not specify it, `Std$Out$Info` is selected.

[To TOC](#table-of-contents) [To Properties List](#lightsleep-properties)

#### 3-2. Database handler class

The database handler class is automatically selected from the contents of the JDBC URL specified in the `url` or `urls` property.

|Word included in JDBC URL|Selected class|Corresponding DBMS
|:-----------|:-----------|:--------------------------------
|`mariadb`   |`MariaDB`   |[MariaDB](https://mariadb.org/)
|`mysql`     |`MySQL`     |[MySQL](https://www.mysql.com/)
|`oracle`    |`Oracle`    |[Oracle Database](https://www.oracle.com/database/index.html)
|`postgresql`|`PostgreSQL`|[PostgreSQL](https://www.postgresql.org/)
|`sqlite`    |`SQLite`    |[SQLite](https://sqlite.org/index.html)
|`sqlserver` |`SQLServer` |[Microsoft SQL Server](https://www.microsoft.com/en-us/sql-server/sql-server-2017)

If the JDBC URL does not contain any of the words above, `Standard` class is selected.


[To TOC](#table-of-contents) [To Properties List](#lightsleep-properties)

#### 3-3. Connection supplier class

Select the value of the `ConnectionSupplier` property from the following.

|Value     |Corresponding connection pool libraries
|:---------|:--------------------------------------
|`C3p0`    |[c3p0](http://www.mchange.com/projects/c3p0/)
|`Dbcp`    |[Apache Commons DBCP](https://commons.apache.org/proper/commons-dbcp/)
|`HikariCP`|[HikariCP](https://github.com/brettwooldridge/HikariCP)
|`TomcatCP`|[Tomcat JDBC Connection Pool](http://tomcat.apache.org/tomcat-9.0-doc/jdbc-pool.html)
|`Jndi`    |Java Naming and Directory Interface (JNDI) ([In the case of Tomcat](http://tomcat.apache.org/tomcat-8.5-doc/jndi-datasource-examples-howto.html))
|`Jdbc`    |`DriverManager#getConnection(String url, Properties info)` Method

Also define the information required by the connection pool library in the lightsleep.properties file.  
Below the ConnectionSupplier (from `url`) in definition examples of lightsleep.properties are the definition contents to be passed to the connection supplier.

```properties
# lightsleep.properties - Jdbc
ConnectionSupplier = Jdbc
url      = jdbc:mariadb://maria-win-x64:3306/example
user     = example
password = _example_
```

```properties
# lightsleep.properties - C3p0
ConnectionSupplier = C3p0
url      = jdbc:mysql://mysql-win-x64:3307/example
user     = example
password = _example_
```

```properties
# c3p0.properties
c3p0.initialPoolSize = 20
c3p0.minPoolSize     = 10
c3p0.maxPoolSize     = 30
```

```properties
# lightsleep.properties - Dbcp
ConnectionSupplier = Dbcp
url         = jdbc:oracle:thin:@ora19-win-x64:1521:example
user        = example
  or
username    = example
password    = _example_
initialSize = 20
maxTotal    = 30
```

```properties
# lightsleep.properties - HikariCP
ConnectionSupplier = HikariCP
url             = jdbc:postgresql://post-win-x64/example
  or
jdbcUrl         = jdbc:postgresql://post-win-x64/example
user            = example
  or
username        = example
password        = _example_
minimumIdle     = 10
maximumPoolSize = 30
```

```properties
# lightsleep.properties - TomcatCP
ConnectionSupplier = TomcatCP
url         = jdbc:sqlserver://sql-win-x64;database=example
user        = example
  or
username    = example
password    = _example_
initialSize = 20
maxActive   = 30
```

```properties
# lightsleep.properties - Jndi
ConnectionSupplier = Jndi
dataSource         = jdbc/example
  or
dataSource         = example
```

[To TOC](#table-of-contents)

### 4. Transaction

Execution of `Transaction.execute` method is equivalent to the execution of a transaction.  
Define contents of the transaction by the argument `transaction` as a lambda expression.  
The lambda expression is equivalent to the contents of `Transaction.executeBody` method and the argument of this method is a `ConnectionWrapper`.

```java
// Example in Java
var contact = new Contact(1, "Akane", "Apple");

Transaction.execute(conn -> {
    // Start of transaction
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(contact);
    ...
    // End of transaction
});
```

If you define multiple JDBC URLs in `lightsleep.properties`, you need to specify which URL to execute the transaction.  
The `ConnectionSupplier.find` method searches for a JDBC URL that contains all of the string array of arguments.  
An exception will be thrown if more than one is found or if it can not be found.

```java
// Example in Java
public static final ConnectionSupplier supplier1 = ConnectionSupplier.find("example1");
    ...

var contact = new Contact(1, "Akane", "Apple");

Transaction.execute(supplier1, conn -> {
    // Start of transaction
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(contact);
   ...
    // End of transaction
});
```

If an exception is thrown during the transaction, `Transaction.rollback` method is called.  
Otherwise, `Transaction.commit` method is called.

[To TOC](#table-of-contents)

### 5. Execution of SQL

Use the various methods of `Sql` class to execute SQLs and define it in the lambda expression argument of `Transaction.execute` method.

#### 5-1. SELECT

#### 5-1-1. SELECT 1 row with an Expression condition

```java
// Example in Java
Transaction.execute(conn -> {
    Optional<Contact> contactOpt = new Sql<>(Contact.class)
        .where("{id}={}", 1)
        .connection(conn)
        .select();
});
```

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE id=1
```

[To TOC](#table-of-contents)

#### 5-1-2. SELECT 1 row with an Entity condition

```java
// Example in Java
var contact = new Contact();
contact.id = 1;
Transaction.execute(conn -> {
    Optional<Contact> contactOpt = new Sql<>(Contact.class)
        .where(contact)
        .connection(conn)
        .select();
});
```

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE id=1
```

[To TOC](#table-of-contents)

#### 5-1-3. SELECT multiple rows with an Expression condition

```java
// Example in Java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .connection(conn)
        .select(contacts::add)
);
```

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple'
```

[To TOC](#table-of-contents)

#### 5-1-4. SELECT with a Subquery condition

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime
  FROM Contact C
  WHERE EXISTS (SELECT * FROM Phone P WHERE P.contactId=C.id)
```

[To TOC](#table-of-contents)

#### 5-1-5. SELECT with Expression conditions (AND)

```java
// Example in Java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .and  ("{firstName}={}", "Akane")
        .connection(conn)
        .select(contacts::add)
);
```

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' AND firstName='Akane'
```

[To TOC](#table-of-contents)

#### 5-1-6. SELECT with Expression Condition (OR)

```java
// Example in Java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .or   ("{lastName}={}", "Orange")
        .connection(conn)
        .select(contacts::add)
);
```

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' OR lastName='Orange'
```

[To TOC](#table-of-contents)

#### 5-1-7. SELECT with Expression conditions A AND B OR C AND D

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' AND firstName='Akane' OR lastName='Orange' AND firstName='Setoka'
```

[To TOC](#table-of-contents)

#### 5-1-8. SELECT with selection of columns

```java
// Example in Java
var contacts = new ArrayList<Contact>();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Apple")
        .columns("lastName", "firstName")
        .connection(conn)
        .select(contacts::add)
);
```

```sql
-- Generated SQL
SELECT firstName, lastName FROM Contact WHERE lastName='Apple'
```

[To TOC](#table-of-contents)

#### 5-1-9. SELECT with GROUP BY and HAVING

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT MIN(C.lastName) C_lastName
  FROM Contact C
  GROUP BY C.lastName
  HAVING COUNT(C.lastName)>=2
```

[To TOC](#table-of-contents)

#### 5-1-10. SELECT with ORDER BY, OFFSET and LIMIT

```java
// Example in Java
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

```sql
-- Generated SQL for MariaDB, MySQL, PostgreSQL and SQLite
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  ORDER BY lastName ASC, firstName ASC, id ASC
  LIMIT 5 OFFSET 10
```

```sql
-- Generated SQL for Oracle and SQLServer (Skip rows during getting)
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  ORDER BY lastName ASC, firstName ASC, id ASC
```

[To TOC](#table-of-contents)

#### 5-1-11. SELECT with FOR UPDATE

```java
// Example in Java
Transaction.execute(conn -> {
    Optional<Contact> contactOpt = new Sql<>(Contact.class)
        .where("{id}={}", 1)
        .forUpdate()
        .connection(conn)
        .select();
});
```

```sql
-- Generated SQL for MariaDB, MySQL, Oracle and PostgreSQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1 FOR UPDATE
```

```sql
-- Generated SQL for SQLite
-- UnsupportedOperationException is thrown on SQLite because FOR UPDATE is not supported.
```

```sql
-- Generated SQL for SQLServer
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WITH (ROWLOCK,UPDLOCK) WHERE id=1
```

[To TOC](#table-of-contents)

#### 5-1-12. SELECT with INNER JOIN

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime, P.contactId P_contactId, P.featureIndex P_featureIndex, P.label P_label, P.content P_content
  FROM Contact C
  INNER JOIN Phone P ON P.contactId=C.id
  WHERE C.id=1
```

[To TOC](#table-of-contents)

#### 5-1-13. SELECT with LEFT OUTER JOIN

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime, P.contactId P_contactId, P.featureIndex P_featureIndex, P.label P_label, P.content P_content
  FROM Contact C
  LEFT OUTER JOIN Phone P ON P.contactId=C.id
  WHERE C.lastName='Apple'
```

[To TOC](#table-of-contents)

#### 5-1-14. SELECT with RIGHT OUTER JOIN

```java
// Example in Java
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

```sql
-- Generated SQL
-- An exception is thrown in SQLite because RIGHT OUTER JOIN is not supported.
SELECT C.id C_id, C.firstName C_firstName, C.lastName C_lastName, C.birthday C_birthday, C.updateCount C_updateCount, C.createdTime C_createdTime, C.updatedTime C_updatedTime, P.contactId P_contactId, P.featureIndex P_featureIndex, P.label P_label, P.content P_content
  FROM Contact C
  RIGHT OUTER JOIN Phone P ON P.contactId=C.id
  WHERE P.label='Main'
```

[To TOC](#table-of-contents)

#### 5-1-15. SELECT COUNT(*)

```java
// Example in Java
int[] count = new int[1];
Transaction.execute(conn ->
    count[0] = new Sql<>(Contact.class)
        .where("lastName={}", "Apple")
        .connection(conn)
        .selectCount()
);
```

```sql
-- Generated SQL
SELECT COUNT(*) FROM Contact WHERE lastName='Apple'
```


[To TOC](#table-of-contents)

#### 5-1-16. SELECT FROM Clause Subquery

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime, fullName
  FROM (
    SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime, firstName||' '||lastName fullName FROM Contact
  ) Contact
  WHERE fullName='Akane Apple' ORDER BY fullName ASC
```


[To TOC](#table-of-contents)

#### 5-1-17. SELECT UNION, UNION ALL

```java
// Example in Java
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

```sql
-- Generated SQL
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

[To TOC](#table-of-contents)

#### 5-1-18. SELECT WITH clause

```java
// Example in Java
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

```sql
-- Generated SQL
WITH W1(id, parentId, name) AS (
  SELECT id, parentId, name FROM Node WHERE name LIKE '%-%'
)
SELECT id, parentId, name FROM W1
```

[To TOC](#table-of-contents)

#### 5-1-19. SELECT RECURSIVE

```java
// Example in Java
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

```sql
-- Generated SQL for MariaDB, MySQL, PostgreSQL and SQLite
WITH RECURSIVE W1(id, parentId, name) AS (
  SELECT id, parentId, name FROM Node WHERE id=1
  UNION ALL
  SELECT node.id node_id, node.parentId node_parentId, node.name node_name
    FROM Node node,W1
    WHERE node.parentId=W1.id
)
SELECT id, parentId, name FROM W1
```

```sql
-- Generated SQL for Oracle and SQL Server
WITH W1(id, parentId, name) AS (
  SELECT id, parentId, name FROM Node WHERE id=1
  UNION ALL
  SELECT node.id node_id, node.parentId node_parentId, node.name node_name
    FROM Node node,W1
    WHERE node.parentId=W1.id
)
SELECT id, parentId, name FROM W1
```

[To TOC](#table-of-contents)

#### 5-2. INSERT

#### 5-2-1. INSERT 1 row

```java
// Example in Java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(new Contact(1, "Akane", "Apple", 2001, 1, 1))
```

```sql
-- Generated SQL for MariaDB, MySQL, Oracle and PostgreSQL
INSERT INTO Contact
  (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (1, 'Akane', 'Apple', DATE'2001-01-01', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

```sql
-- Generated SQL for SQLite
INSERT INTO Contact
  (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (1, 'Akane', 'Apple', '2001-01-01', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

```sql
-- Generated SQL for SQLServer
INSERT INTO Contact
  (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (1, 'Akane', 'Apple', CAST('2001-01-01' AS DATE), 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

[To TOC](#table-of-contents)

#### 5-2-2. INSERT multiple rows

```java
// Example in Java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .connection(conn)
        .insert(Arrays.asList(
            new Contact(2, "Yukari", "Apple", 2001, 1, 2),
            new Contact(3, "Azusa", "Apple", 2001, 1, 3)
        ))
```

```sql
-- Generated SQL for MariaDB, MySQL, Oracle and PostgreSQL
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (2, 'Yukari', 'Apple', DATE'2001-01-02', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (3, 'Azusa', 'Apple', DATE'2001-01-03', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

```sql
-- Generated SQL for SQLite
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (2, 'Yukari', 'Apple', '2001-01-02', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (3, 'Azusa', 'Apple', '2001-01-03', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

```sql
-- Generated SQL for SQLServer
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (2, 'Yukari', 'Apple', CAST('2001-01-02' AS DATE), 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO Contact (id, firstName, lastName, birthday, updateCount, createdTime, updatedTime)
  VALUES
  (3, 'Azusa', 'Apple', CAST('2001-01-03' AS DATE), 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
```

[To TOC](#table-of-contents)

### 5-3. UPDATE

#### 5-3-1. UPDATE 1 row

```java
// Example in Java
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

```sql
-- Generated SQL for MariaDB, MySQL, Oracle, PostgreSQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
UPDATE Contact SET
  firstName='Akiyo', lastName='Apple', birthday=DATE'2001-01-01', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP WHERE id=1
```

```sql
-- Generated SQL for SQLite
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
UPDATE Contact SET
  firstName='Akiyo', lastName='Apple', birthday='2001-01-01', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP WHERE id=1
```

```sql
-- Generated SQL for SQLServer
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
UPDATE Contact SET
  firstName='Akiyo', lastName='Apple', birthday=CAST('2001-01-01' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP WHERE id=1
```

[To TOC](#table-of-contents)

#### 5-3-2. UPDATE multiple rows

```java
// Example in Java
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

```sql
-- Generated SQL for MariaDB, MySQL, Oracle, PostgreSQL
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

```sql
-- Generated SQL for SQLite
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE lastName='Apple'
UPDATE Contact SET
  firstName='Akiyo', lastName='Apfel', birthday='2001-01-01', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=1
UPDATE Contact SET
  firstName='Yukari', lastName='Apfel', birthday='2001-01-02', updateCount=updateCount+1, updatdTime=CURRENT_TIMESTAMP
  WHERE id=2
UPDATE Contact SET
  firstName='Azusa', lastName='Apfel', birthday='2001-01-03', updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=3
```

```sql
-- Generated SQL for SQLServer
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE lastName='Apple'
UPDATE Contact SET
  firstName='Akiyo', lastName='Apfel', birthday=CAST('2001-01-01' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=1
UPDATE Contact SET
  firstName='Yukari', lastName='Apfel', birthday=CAST('2001-01-02' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=2
UPDATE Contact
  SET firstName='Azusa', lastName='Apfel', birthday=CAST('2001-01-03' AS DATE), updateCount=updateCount+1, updatedTime=CURRENT_TIMESTAMP
  WHERE id=3
```

[To TOC](#table-of-contents)

#### 5-3-3. UPDATE with a Condition and selection of columns

```java
// Example in Java
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

```sql
-- Generated SQL
UPDATE Contact SET lastName='Pomme' WHERE lastName='Apfel'
```

[To TOC](#table-of-contents)

#### 5-3-4. UPDATE all rows

```java
// Example in Java
var contact = new Contact();
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where(Condition.ALL)
        .columns("birthday")
        .connection(conn)
        .update(contact)
);
```

```sql
-- Generated SQL
UPDATE Contact SET birthday=NULL
```


[To TOC](#table-of-contents)

#### 5-4. DELETE

#### 5-4-1. DELETE 1 row

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE id=1
DELETE FROM Contact WHERE id=1
```

#### 5-4-2. DELETE multiple rows

```java
// Example in Java
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

```sql
-- Generated SQL
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact WHERE lastName='Pomme'
DELETE FROM Contact WHERE id=2
DELETE FROM Contact WHERE id=3
```

#### 5-4-3. DELETE with a Condition

```java
// Example in Java
Transaction.execute(conn ->
    new Sql<>(Contact.class)
        .where("{lastName}={}", "Orange")
        .connection(conn)
        .delete()
);
```

```sql
-- Generated SQL
DELETE FROM Contact WHERE lastName='Orange'
```

#### 5-4-4. DELETE all rows

```java
// Example in Java
Transaction.execute(conn ->
    new Sql<>(Phone.class)
        .where(Condition.ALL)
        .connection(conn)
        .delete()
);
```

```sql
-- Generated SQL
DELETE FROM Phone
```

[To TOC](#table-of-contents)

### 6. Expression Conversion

When generating SQL, evaluates the following character string as an expression and perform conversion processing.

- The value of `@Select`, `@Insert` and `@Update`
- The value of `expression` of `@SelectProperty`, `@InsertProperty` and `@UpdateProperty` annotations.
- Arguments for the following methods of the `Sql` class
  - `where(String content, Object... arguments)`
  - `where(String content, Sql<SE> subSql)`
  - `where(Sql<SE> subSql, String content)`
  - `and(String content, Object... arguments)`
  - `and(String content, Sql<SE> subSql)`
  - `and(Sql<SE> subSql, String content)`
  - `or(String content, Object... arguments)`
  - `or(String content, Sql<SE> subSql)`
  - `or(Sql<SE> subSql, String content)`
  - `groupBy(String content, Object... arguments)`
  - `having(String content, Object... arguments)`
  - `having(String content, Sql<SE> subSql)`
  - `having(Sql<SE> subSql, String content)`
  - `orderBy(String content, Object... arguments)`
- Arguments for the following methods of the `Condition` interface
  - `of(String content, Object... arguments)`
  - `of(String content, Sql<E> outerSql, Sql<SE> subSql)`
  - `of(Sql<E> outerSql, Sql<SE> subSql, String content)`
  - `and(String content, Object... arguments)`
  - `and(String content, Sql<E> outerSql, Sql<SE> subSql)`
  - `and(Sql<E> outerSql, Sql<SE> subSql, String content)`
  - `or(String content, Object... arguments)`
  - `or(String content, Sql<E> outerSql, Sql<SE> subSql)`
  - `or(Sql<E> outerSql, Sql<SE> subSql, String content)`
- Arguments of the following constructor of the `Expression` class
  - `Expression(String content, Object... arguments)`

Conversion of expressions has the following.

| Format  | Conversion Content
|:--------|:------------------
|`{}`     |An element of `arguments` in appearance
|`{xxx}`  |The column name related to property `xxx`
|`{A.xxx}`|`"A."` + The column name related to property `xxx` (`A` is a table alias)
|`{A_xxx}`|The column alias related to table alias `A` and `xxx` property
|`{#xxx}` |The value of property `xxx` of an entity set on the `Sql` object (or an entity argument of `Sql#insert` or `Sql#update` method)


[To TOC](#table-of-contents)

### 7. Mappings of Java types and column types in datetime types

##### 7.2 MariaDB, MySQL

|                          |`DATE`|`TIME`                 |`DATETIME`             |`TIMESTAMP`
|:-------------------------|:----:|:---------------------:|:---------------------:|:---------:
|`java.util.Date`          | ✓    |                       |                       |
|`java.sql.Date`           | ✓    |                       |                       |
|`java.sql.Time`           |      |✓ (10<sup>-3</sup> sec)|                       |
|`java.sql.Timestamp`      | ✓    |                       |✓ (10<sup>-6</sup> sec)|✓ (10<sup>-6</sup> sec)
|`java.time.LocalDate`     |      |                       |                       |
|`java.time.LocalTime`     |      |✓ (10<sup>-6</sup> sec)|                       |
|`java.time.LocalDateTime` |      |                       |✓ (10<sup>-6</sup> sec)|✓ (10<sup>-6</sup> sec)
|`java.time.OffsetDateTime`|      |                       |                       |
|`java.time.ZonedDateTime` |      |                       |                       |
|`java.time.Instant`       |      |                       |                       |

##### 7.3 Oracle

|                          |`DATE` |`TIMESTAMP(9)`         |`TIMESTAMP(9) WITH TIME ZONE`|`TIMESTAMP(9) WITH LOCAL TIME ZONE`
|:-------------------------|:-----:|:---------------------:|:--------------------:|:---------------------------------------:
|`java.util.Date`          |✓      |                       |                      |
|`java.sql.Date`           |✓      |                       |                      |
|`java.sql.Time`           |✓(sec) |                       |                      |
|`java.sql.Timestamp`      |✓(sec) |✓ (10<sup>-9</sup> sec)|                      |✓ (10<sup>-9</sup> sec)
|`java.time.LocalDate`     |✓(sec) |                       |                      |
|`java.time.LocalTime`     |✓(sec) |                       |                      |
|`java.time.LocalDateTime` |✓(sec) |✓ (10<sup>-9</sup> sec)|                      |✓ (10<sup>-9</sup> sec)
|`java.time.OffsetDateTime`|       |                       |✓ (10<sup>-9</sup> sec)|
|`java.time.ZonedDateTime` |       |                       |✓ (10<sup>-9</sup> sec)|
|`java.time.Instant`       |       |                       |✓ (10<sup>-9</sup> sec)|


##### 7.4 PostgreSQL

|                          |`DATE`|`TIME(6)`              |`TIMESTAMP(6)`         |`TIMESTAMP(6) WITH TIME ZONE`
|:-------------------------|:----:|:---------------------:|:---------------------:|:---------------------------:
|`java.util.Date`          | ✓    |                       |                       |
|`java.sql.Date`           | ✓    |                       |                       |
|`java.sql.Time`           |      |✓ (10<sup>-3</sup> sec)|                       |
|`java.sql.Timestamp`      | ✓    |                       |✓ (10<sup>-6</sup> sec)|
|`java.time.LocalDate`     |      |                       |                       |
|`java.time.LocalTime`     |      |✓ (10<sup>-6</sup> sec)|                       |
|`java.time.LocalDateTime` |      |                       |✓ (10<sup>-6</sup> sec)|
|`java.time.OffsetDateTime`|      |                       |                       |✓ (10<sup>-6</sup> sec)
|`java.time.ZonedDateTime` |      |                       |                       |
|`java.time.Instant`       |      |                       |                       |✓ (10<sup>-6</sup> sec)

##### 7.5 SQLite

|                           | `DATE`, `TIME`, `DATETIME`, `TEXT`
|:--------------------------|:--------------------------------:
| `java.util.Date`          |✓
| `java.sql.Date`           |✓
| `java.sql.Time`           |✓ (10<sup>-3</sup> sec)
| `java.sql.Timestamp`      |✓ (10<sup>-9</sup> sec)
| `java.time.LocalDate`     |✓ (10<sup>-9</sup> sec)
| `java.time.LocalTime`     |✓ (10<sup>-9</sup> sec)
| `java.time.LocalDateTime` |✓ (10<sup>-9</sup> sec)
| `java.time.OffsetDateTime`|✓ (10<sup>-9</sup> sec)
| `java.time.ZonedDateTime` |✓ (10<sup>-9</sup> sec)
| `java.time.Instant`       |✓ (10<sup>-9</sup> sec)

##### 7.6 SQL Server

|                          |`DATE`|`TIME(7)`              |`DATETIME2(7)`         |`DATETIMEOFFSET(7)`
|--------------------------|:----:|:---------------------:|:---------------------:|:---:
|`java.util.Date`          | ✓    |                       |                       |
|`java.sql.Date`           | ✓    |                       |                       |
|`java.sql.Time`           |      |✓ (10<sup>-3</sup> sec)|                       |
|`java.sql.Timestamp`      | ✓    |                       |✓ (10<sup>-7</sup> sec)|
|`java.time.LocalDate`     |      |                       |                       |
|`java.time.LocalTime`     |      |✓ (10<sup>-3</sup> sec)|                       |
|`java.time.LocalDateTime` |      |                       |✓ (10<sup>-7</sup> sec)|
|`java.time.OffsetDateTime`|      |                       |                       |✓ (10<sup>-7</sup> sec)
|`java.time.ZonedDateTime` |      |                       |                       |
|`java.time.Instant`       |      |                       |                       |✓ (10<sup>-7</sup> sec

<span style="color:gray;">_(C) 2015 Masato Kokubo_</span>
