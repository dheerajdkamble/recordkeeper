CREATE TABLE "PUBLIC"."RECORD" (
    id integer not null auto_increment,
    name varchar(100),
    created_date date,
    created_by varchar(100),
    modified_date date,
    modified_by varchar(100),
    PRIMARY KEY (id)
);