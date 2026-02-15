// MySQLSpec.groovy
// (C) 2016 Masato Kokubo

package org.lightsleep.spec.database

import org.debugtrace.DebugTrace
import org.lightsleep.component.*
import org.lightsleep.database.*
import org.lightsleep.helper.*

import spock.lang.*

// MySQLSpec
@Unroll
class MySQLSpec extends Specification {
    @Shared map = MySQL.instance.typeConverterMap()

    // -> SqlString
    def "MySQL #title -> SqlString"(String title, Object sourceValue, String expectedString) {
        DebugTrace.enter() // for Debugging
        DebugTrace.print('title', title) // for Debugging
        DebugTrace.print('sourceValue', sourceValue) // for Debugging
        DebugTrace.print('expectedString', expectedString) // for Debugging
        when:
        def convertedValue = TypeConverter.convert(map, sourceValue, SqlString).toString()
        DebugTrace.print('convertedValue', convertedValue) // for Debugging

        then:
        expectedString == convertedValue
        DebugTrace.leave() // for Debugging

        where:
        title|sourceValue|expectedString

        // title            |sourceValue          |expectedString
        'Boolean false     '|false                |'0'
        'Boolean true      '|true                 |'1'
        'String \\u0000    '|'\u0000'             |"'\\0'"
        'String \\b        '|'\b'                 |"'\\b'"
        'String \\t        '|'\t'                 |"'\\t'"
        'String \\n        '|'\n'                 |"'\\n'"
        'String \\r        '|'\r'                 |"'\\r'"
        'String \'A\'      '|"'A'"                |"'''A'''"
        'String \\         '|'\\'                 |"'\\\\'"
        'byte[] {0,1,-2,-1}'|[0,1,-2,-1] as byte[]|"X'0001FEFF'"
    }
}
