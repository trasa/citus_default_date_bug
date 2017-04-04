# citus_default_date_bug
demonstrates bug(?) with Citus Sharded Postgres and a default date column 
See https://github.com/citusdata/citus/issues/1304

Given code like this:

```
Connection conn = h.getConnection();
PreparedStatement stmt = conn.prepareStatement("insert into uuidsample(id, other_id, name, namespace) " +
                                               "values (?, ?, ?, ?) on conflict do nothing");
stmt.setObject(1, UUID.randomUUID());
stmt.setLong(2, 1L);
stmt.setString(3, "jdbc_prep_stmt");
stmt.setString(4, "j");
stmt.executeUpdate();
```

We get an exception:
```
Caused by: org.postgresql.util.PSQLException: ERROR: no value found for parameter 3
```

Appears to be related to bugs with Citus and Prepared Statements.
