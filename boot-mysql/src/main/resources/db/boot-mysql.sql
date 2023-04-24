create table t_uc_user (
    id                int auto_increment comment 'id' primary key,
    account           varchar(30)             not null comment '账号',
    user_name         varchar(30)             null comment '用户名',
    phone_number      varchar(13)             null comment '手机号',
    phone_number_code varchar(3) default '86' null comment '手机号地区编号',
    email             varchar(50)             null comment '邮箱',
    CREATE_TIME       datetime                null comment '创建时间',
    UPDATE_TIME       datetime                null comment '更新时间',
    DELETED           tinyint(1)              not null comment '逻辑删除'
) comment '用户中心-用户';

