CREATE DATABASE IF NOT EXISTS `easyflow` default character set utf8 collate utf8_unicode_ci;
use `easyflow`;

drop table if exists sys_user;
create table sys_user
(
    id           bigint unsigned auto_increment primary key,
    username     varchar(50) comment '用户名',
    password     varchar(50) comment '密码',
    fullname     varchar(20) comment '姓名',
    phone        varchar(15) comment '手机号',
    email        varchar(50) comment '邮箱',
    gmt_create   datetime          default current_timestamp,
    gmt_modified datetime not null default current_timestamp on update current_timestamp,
    unique key uk_username (username)
) comment '用户表';

drop table if exists flow_definition;
create table flow_definition
(
    id              bigint unsigned auto_increment primary key,
    definition_code varchar(100) not null comment '定义代码',
    definition_name varchar(50) comment '流程定义名称',
    gmt_create      datetime              default current_timestamp,
    gmt_modified    datetime     not null default current_timestamp on update current_timestamp,
    unique key uk_dcode (definition_code)
) comment '流程定义表';

drop table if exists flow_definition_node;
create table flow_definition_node
(
    id               bigint unsigned auto_increment primary key,
    definition_code  varchar(100) not null comment '定义代码',
    parent_code      varchar(100) comment '父节点代码',
    node_code        varchar(100) not null comment '节点代码',
    node_name        varchar(50)  not null comment '节点名称',
    condition_script varchar(100) comment '条件脚本',
    rel_class        varchar(200) not null comment '关联类的全路径',
    priority         int          not null default 0 comment '分支优先级',
    gmt_create       datetime              default current_timestamp,
    gmt_modified     datetime     not null default current_timestamp on update current_timestamp
) comment '流程定义节点表';

drop table if exists flow_instance;
create table flow_instance
(
    id              bigint unsigned auto_increment primary key,
    instance_code   varchar(50)  not null comment '实例代码',
    definition_code varchar(100) not null comment '定义代码',
    username        varchar(50) comment '申请人用户名',
    fullname        varchar(20) comment '申请人姓名',
    module_id       varchar(50) comment '模块id',
    module_name     varchar(50) comment '模块名称',
    title           varchar(100) comment '申请标题',
    status          int          not null default 0 comment '状态：0进行中，1已完成，2已终止等',
    apply_time      datetime     not null default current_timestamp comment '申请时间',
    gmt_create      datetime              default current_timestamp,
    gmt_modified    datetime     not null default current_timestamp on update current_timestamp,
    unique key uk_icode (instance_code)
) comment '流程实例表';

drop table if exists flow_instance_node;
create table flow_instance_node
(
    id            bigint unsigned auto_increment primary key,
    node_id       int comment '节点id',
    parent_id     int comment '父节点id',
    instance_code varchar(50) comment '实例代码',
    task_code     varchar(50) comment '任务编号',
    username      varchar(50) comment '审批人用户名',
    fullname      varchar(20) comment '审批人姓名',
    node_code     varchar(100) comment '节点代码',
    node_name     varchar(50) comment '节点名称',
    action_status int      not null default 0 comment '状态：0待处理，1同意，2不同意等',
    action_time   datetime comment '批阅时间',
    note          varchar(250) comment '批注',
    gmt_create    datetime          default current_timestamp,
    gmt_modified  datetime not null default current_timestamp on update current_timestamp,
    unique key uk_task_id (task_code)
) comment '流程实例节点表';

drop table if exists flow_variable;
create table flow_variable
(
    id            bigint unsigned auto_increment primary key,
    instance_code varchar(50) comment '实例代码',
    keyy          varchar(64) comment '变量键',
    valuee        varchar(200) comment '变量值',
    type          varchar(100) comment '变量类型',
    gmt_create    datetime          default current_timestamp,
    gmt_modified  datetime not null default current_timestamp on update current_timestamp
) comment '流程实例变量';

drop table if exists leave_apply;
create table leave_apply
(
    id            bigint unsigned auto_increment primary key,
    instance_code varchar(50) comment '实例代码',
    type          varchar(10) not null comment '假期类型',
    leave_day     int         not null comment '请假天数',
    start_date    date        not null comment '开始日期',
    end_date      date        not null comment '结束日期',
    reason        varchar(250) comment '请假理由',
    gmt_create    datetime             default current_timestamp,
    gmt_modified  datetime    not null default current_timestamp on update current_timestamp
) comment '请假申请表';

drop table if exists leave_employee;
create table leave_employee
(
    id           bigint unsigned auto_increment primary key,
    username     varchar(50) comment '用户名',
    gender       tinyint comment '性别,女1男2',
    annual_days  int comment '剩余年假天数',
    gmt_create   datetime          default current_timestamp,
    gmt_modified datetime not null default current_timestamp on update current_timestamp
) comment '请假员工信息';

