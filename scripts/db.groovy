@GrabConfig(systemClassLoader=true)
@Grab(group='mysql', module='mysql-connector-java', version='5.1.6')
import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:mysql://localhost/grails_org", "root", "", "com.mysql.jdbc.Driver")

sql.eachRow("SELECT * from pending_release") { row ->
    println "Found pending plugin ${row.plugin_name} ${row.plugin_version}"
}
