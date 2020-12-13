create table user(
id int primary key auto_increment comment '主键ID',
name varchar(20) not null comment '姓名',
gender varchar(10) not null comment '性别',
address varchar(30) not null comment '地址',
create_time datetime not null default current_timestamp comment '添加时间',
update_time datetime on update current_timestamp comment '更新时间'
)engine=InnoDB default charset=utf8mb4;