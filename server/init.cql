CREATE KEYSPACE cycling WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
use cycling;
CREATE TABLE user(    id timeuuid PRIMARY KEY,    title text,    description text,    published boolean );