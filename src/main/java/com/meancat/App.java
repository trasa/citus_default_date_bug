package com.meancat;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.DBI;

public class App {
    public static void main(String[] args) throws SQLException {
        String user;
        String pass;
        String dbname;

        if (args.length > 1) {
            user = args[0];
            pass = args[1];
            dbname = args[2];
        } else {
            user = "postgres";
            pass = "postgres";
            dbname = "sample";
        }

        DriverManager.registerDriver(new org.postgresql.Driver());

        // replace db, user and pass with real values
        DBI dbi = new DBI("jdbc:postgresql://localhost:5432/" + dbname, user, pass);

        SampleDao dao = dbi.open(SampleDao.class);


        /*
        Exception in thread "main" org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException: org.postgresql.util.PSQLException: ERROR: no value found for parameter 3 [statement:"insert into uuidsample(id, other_id, name, namespace) values (:id, :other_id, :name, :namespace) on conflict do nothing", located:"insert into uuidsample(id, other_id, name, namespace) values (:id, :other_id, :name, :namespace) on conflict do nothing", rewritten:"insert into uuidsample(id, other_id, name, namespace) values (?, ?, ?, ?) on conflict do nothing", arguments:{ positional:{}, named:{name:'asdf',namespace:'ns',id:ebd9882d-dcff-48a2-8424-3790d463616b,other_id:1}, finder:[]}]
	at org.skife.jdbi.v2.SQLStatement.internalExecute(SQLStatement.java:1338)
	at org.skife.jdbi.v2.Update.execute(Update.java:56)
	at org.skife.jdbi.v2.sqlobject.UpdateHandler$2.value(UpdateHandler.java:68)
	at org.skife.jdbi.v2.sqlobject.UpdateHandler.invoke(UpdateHandler.java:81)
	at org.skife.jdbi.v2.sqlobject.SqlObject.invoke(SqlObject.java:212)
	at org.skife.jdbi.v2.sqlobject.SqlObject$2.intercept(SqlObject.java:109)
	at org.skife.jdbi.v2.sqlobject.CloseInternalDoNotUseThisClass$$EnhancerByCGLIB$$2c61ff7.createUUID(<generated>)
	at com.meancat.App.main(App.java:36)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
Caused by: org.postgresql.util.PSQLException: ERROR: no value found for parameter 3
	at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2284)
	at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2003)
	at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:200)
	at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:424)
	at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:161)
	at org.postgresql.jdbc.PgPreparedStatement.execute(PgPreparedStatement.java:155)
	at org.skife.jdbi.v2.SQLStatement.internalExecute(SQLStatement.java:1327)
	... 12 more

         */

        UUID u = UUID.randomUUID();
        dao.createUUID(u, 1L, "asdf", "ns" );
        System.out.println("created " + u);
        String name = dao.getUUID(u, 1L);
        System.out.println(name);

        dbi.close(dao);
    }
}
