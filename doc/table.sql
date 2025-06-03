create table contest
(
    id              int auto_increment
        primary key,
    type            varchar(32) not null comment '类型',
    status          varchar(16) not null comment '状态',
    create_user_id  bigint      not null,
    create_group_id bigint      null,
    create_time     timestamp   not null,
    update_time     timestamp   not null
)
    comment '比赛';

create index contest_create_user_id_index
    on contest (create_user_id);

create table contest_end
(
    id          int auto_increment
        primary key,
    contest_id  int            not null,
    user_id     bigint         null,
    end_point   decimal(10, 2) not null comment '结算点数',
    elo_change  decimal(10, 2) null comment 'elo变化',
    create_time timestamp      not null,
    update_time timestamp      not null,
    constraint contest_end_contest_id_user_id_uindex
        unique (contest_id, user_id)
)
    comment '比赛结算';

create table contest_record
(
    id             int auto_increment
        primary key,
    contest_id     int         not null,
    direction      varchar(16) not null comment 'direction',
    record_user_id bigint      not null comment '用户id',
    point          int         not null comment '点数',
    create_time    timestamp   not null,
    update_time    timestamp   not null,
    constraint contest_record_contest_id_direction_uindex
        unique (contest_id, direction)
)
    comment '比赛记录';

create table elo
(
    id          int auto_increment
        primary key,
    user_id     bigint                         not null,
    type        varchar(16)                    null,
    elo         decimal(10, 2) default 2000.00 not null,
    update_time timestamp                      not null,
    create_time timestamp                      not null,
    constraint elo_type_user_id_uindex
        unique (type, user_id)
)
    comment 'elo表';
