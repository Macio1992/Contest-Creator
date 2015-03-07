# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint not null,
  email                     varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  password                  varchar(255),
  account_type              varchar(6),
  account_status            varchar(1),
  constraint ck_account_account_type check (account_type in ('ADMIN','CLIENT')),
  constraint ck_account_account_status check (account_status in ('N','A','D')),
  constraint pk_account primary key (id))
;

create table admin (
  id                        bigint not null,
  account_id                bigint,
  constraint pk_admin primary key (id))
;

create table client (
  id                        bigint not null,
  account_id                bigint,
  constraint pk_client primary key (id))
;

create sequence account_seq;

create sequence admin_seq;

create sequence client_seq;

alter table admin add constraint fk_admin_account_1 foreign key (account_id) references account (id);
create index ix_admin_account_1 on admin (account_id);
alter table client add constraint fk_client_account_2 foreign key (account_id) references account (id);
create index ix_client_account_2 on client (account_id);



# --- !Downs

drop table if exists account cascade;

drop table if exists admin cascade;

drop table if exists client cascade;

drop sequence if exists account_seq;

drop sequence if exists admin_seq;

drop sequence if exists client_seq;

