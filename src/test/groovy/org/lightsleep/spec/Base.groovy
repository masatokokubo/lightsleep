// Base.groovy
// (C) 2016 Masato Kokubo

package org.lightsleep.spec

import java.sql.Connection
import java.sql.Date
import java.util.function.Consumer

import org.debugtrace.DebugTrace
import org.lightsleep.*
import org.lightsleep.component.*
import org.lightsleep.connection.*
import org.lightsleep.database.*
import org.lightsleep.helper.Resource
import org.lightsleep.test.entity.*

import spock.lang.*

// Base
// @since 2.1.0
public class Base extends Specification {
    static def classNames = [
        "org.mariadb.jdbc.Driver",
        "com.mysql.cj.jdbc.Driver",
        "org.postgresql.Driver",
        "org.sqlite.JDBC",
        "com.microsoft.sqlserver.jdbc.SQLServerDriver",
        "org.apache.tomcat.jdbc.pool.DataSource"
    ]
    @Shared List<ConnectionSupplier> connectionSuppliers
    @Shared ConnectionSupplier connectionSupplier
    @Shared boolean notSupportRightJoin        // Dose not support SELECT ... RIGHT OUTER JOIN ...
    @Shared boolean notSupportWithClause       // Dose not support WITH ...
    @Shared boolean notSupportUpdateWithJoin   // Dose not support UPDATE ... JOIN ... 
    @Shared boolean notSupportForUpdate        // Dose not support SELECT ... FOR UPDATE 
    @Shared boolean notSupportForUpdateNoWait  // Dose not support SELECT ... FOR UPDATE NOWAIT
    @Shared boolean notSupportForUpdateNoWaitN // Dose not support SELECT ... FOR UPDATE WAIT N

    def setupSpec() {
        for (def className : classNames) {
            try {
                def clazz = Class.forName(className)
            }
            catch (Exception e) {
                DebugTrace.print('e', e) // TODO: 🌟Debug
            }
        }
        
        def databaseResource = new Resource('Database')
        def databaseKeyword = databaseResource.getString('Database')
        notSupportRightJoin =
            databaseKeyword.contains('sqlite')
        notSupportWithClause =
            databaseKeyword.contains('mysql-') && databaseKeyword.contains('3306') // MySQL 4.7
        notSupportUpdateWithJoin =
            databaseKeyword.contains('ora19-'  ) ||
            databaseKeyword.contains('post-' ) ||
            databaseKeyword.contains('sqlite')
        notSupportForUpdate =
            databaseKeyword.contains('sqlite')
        notSupportForUpdateNoWait = 
            databaseKeyword.contains('maria-') ||
            databaseKeyword.contains('mysql-') ||
            databaseKeyword.contains('post-' ) ||
            databaseKeyword.contains('sqlite')
        notSupportForUpdateNoWaitN = 
            databaseKeyword.contains('maria-') ||
            databaseKeyword.contains('mysql-') ||
            databaseKeyword.contains('post-' ) ||
            databaseKeyword.contains('sqlite') ||
            databaseKeyword.contains('sql-'  )

        connectionSuppliers = [
            Jdbc    .simpleName,
            C3p0    .simpleName,
            Dbcp    .simpleName,
            HikariCP.simpleName,
            TomcatCP.simpleName,
        ].collect {ConnectionSupplier.find(it, databaseKeyword)}

        connectionSupplier = connectionSuppliers[0]
    }

    def deleteAllTables() {
        Transaction.execute(connectionSupplier) {
            new Sql<>(Contact ).connection(it).where(Condition.ALL).delete()
            new Sql<>(Address ).connection(it).where(Condition.ALL).delete()
            new Sql<>(Phone   ).connection(it).where(Condition.ALL).delete()
            new Sql<>(Product ).connection(it).where(Condition.ALL).delete()
            new Sql<>(Sale    ).connection(it).where(Condition.ALL).delete()
            new Sql<>(SaleItem).connection(it).where(Condition.ALL).delete()
        }
    }
}
