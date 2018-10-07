set names 'utf8mb4';
create user 'slickdev'@'%'
  identified by 'Slickdev.2018';
create database slickdev
  character set = 'utf8mb4';
grant select on mysql.* to 'slickdev'@'%';

use slickdev;
-- init tables, views, sequences  begin
create table test (
  id         bigint auto_increment primary key,
  name       varchar(255),
  created_at timestamp
);
-- init tables, views, sequences  end

grant all on slickdev.* to 'slickdev'@'%';
