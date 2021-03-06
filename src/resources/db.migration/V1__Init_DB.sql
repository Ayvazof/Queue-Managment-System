create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table office (id varchar(255) not null, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table office_task (office_id varchar(255) not null, task_id varchar(255) not null) engine=InnoDB;
create table ord (id integer not null, create_time_date varchar(255), primary key (id)) engine=InnoDB;
create table task (id varchar(255) not null, name varchar(255), prefix varchar(255), priority integer not null, super_task_id varchar(255), primary key (id)) engine=InnoDB;
create table ticket (id integer not null, create_time_date varchar(255), queue_num integer not null, state integer, state_change_date_time varchar(255), task_id varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB;
create table user_office (user_id varchar(255) not null, office_id varchar(255) not null) engine=InnoDB;
create table user_role (user_id varchar(255) not null, roles varchar(255)) engine=InnoDB;
create table usr (id varchar(255) not null, login varchar(255), name varchar(255), password varchar(255), surname varchar(255), primary key (id)) engine=InnoDB;
alter table office_task add constraint office_task_task_fk foreign key (task_id) references task (id);
alter table office_task add constraint office_task_office_fk foreign key (office_id) references office (id);
alter table task add constraint task_superTask_fk foreign key (super_task_id) references task (id);
alter table ticket add constraint ticket_task_fk foreign key (task_id) references task (id);
alter table ticket add constraint ticket_user_fk foreign key (user_id) references usr (id);
alter table user_office add constraint user_office_office_fk foreign key (office_id) references office (id);
alter table user_office add constraint user_office_user_fk foreign key (user_id) references usr (id);
alter table user_role add constraint user_role_user_fk foreign key (user_id) references usr (id);