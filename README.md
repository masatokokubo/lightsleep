# Lightsleep

[Japanese](README_ja.md)

Lightsleep is a lightweight Object-Relational (O/R) mapping library and is available in Java 8 or later.

### Features

- It has API using features added in Java 8 (functional interface and Optional class).
- It is easy to understand intuitively because method names used in SQL construction are the same as SQL reserved words.
- It is applicable because J2EE is unnecessary: there is no library that depends on Java Runtime and JDBC driver other than.
- It does not need a definition file for mapping tables and Java classes with XML files.
- Learning is easy because the library is compact.
- Connectable to various DBMS simultaneously.
- Various connection pool libraries can be used simultaneously.
- You can select from various logging libraries when outputting internal logs.

### Supported DBMSs

- MariaDB
- MySQL
- Oracle Database
- PostgreSQL
- SQLite
- Microsoft SQL Server
- DBMSs that conforms to the standard SQL

### Description example of dependency in build.gradle

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

### Definition example of entity class used in Lightsleep

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

### Example of using Lightsleep

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

&emsp;Generated SQL
```sql
SELECT id, firstName, lastName, birthday, updateCount, createdTime, updatedTime
  FROM Contact
  WHERE lastName='Apple' OR lastName='Orange'
  ORDER BY lastName ASC, firstName ASC
```

### License

[The MIT License (MIT)](LICENSE.txt)

<div style="color:gray"><i>(C) 2015 Masato Kokubo</i></div>

### Documents

[CHANGELOG](CHANGELOG.md)

[Tutorial](Tutorial.md)

[User's Guide](UserGuide.md)

[API Specification](http://masatokokubo.github.io/Lightsleep-java/javadoc/index.html)

[BLOG @Hatena](http://lightsleep.hatenablog.com/)
