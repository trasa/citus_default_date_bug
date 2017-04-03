package com.meancat;


import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
/*
create table uuidsample (id uuid,
                            other_id bigint,
                            name varchar(50) not null,
                            namespace varchar(50) not null,
>>>                         create_date timestamptz not null default now()
                            );
alter table uuidsample add primary key (id, other_id);
alter table uuidsample owner to some_owner_name;
select create_distributed_table('uuidsample', 'other_id');

if you remove the create_date column then everything is fine.
adding it back in without the default is ok too.

*/
public interface SampleDao {
    @SqlUpdate("insert into uuidsample(id, other_id, name, namespace) values (:id, :other_id, :name, :namespace) on conflict do nothing")
    int createUUID(@Bind("id") UUID id, @Bind("other_id") long otherId, @Bind("name") String name, @Bind("namespace") String namespace);

    @SqlQuery("select name from uuidsample where id = :id and other_id = :other_id")
    String getUUID(@Bind("id") UUID id, @Bind("other_id") long otherId);
}
