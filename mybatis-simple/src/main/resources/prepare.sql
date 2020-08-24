# 创建测试数据库
CREATE DATABASE mybatis DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use mybatis;

# 创建测试表
CREATE TABLE `country`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `countryname` varchar(255) NULL,
    `countrycode` varchar(255) NULL,
    PRIMARY KEY (`id`)
);

# 插入测试数据
insert country(`countryname`, `countrycode`)
values ('中国', 'CN'),
       ('美国', 'US'),
       ('俄罗斯', 'RU'),
       ('英国', 'GB'),
       ('法国', 'FR');

# 用户表
create table sys_user
(
    id            bigint not null auto_increment comment '用户 ID',
    user_name     varchar(50) comment '用户名',
    user_password varchar(50) comment '密码',
    user_email    varchar(50) comment '邮箱',
    user_info     text comment '简介',
    head_img      blob comment '头像',
    create_time   datetime comment '创建时间',
    primary key (id)
);
alter table sys_user
    comment '用户表';

# 角色表
create table sys_role
(
    id          bigint not null auto_increment comment '角色 ID',
    role_name   varchar(50) comment '角色名',
    enabled     int comment '有效标志',
    create_by   bigint comment '创建人',
    create_time datetime comment '创建时间',
    primary key (id)
);
alter table sys_role
    comment '角色表';

# 权限表
create table sys_privilege
(
    id             bigint not null auto_increment comment '权限 ID',
    privilege_name varchar(50) comment '权限名称',
    privilege_url  varchar(200) comment '权限 URL',
    primary key (id)
);
alter table sys_privilege
    comment '权限表';

# 用户角色管理表
create table sys_user_role
(
    user_id bigint comment '用户 ID',
    role_id bigint comment '角色 ID'
);
alter table sys_user_role
    comment '用户角色关联表';

# 角色权限管理表
create table sys_role_privilege
(
    role_id      bigint comment '角色 ID',
    privilege_id bigint comment '权限 ID'
);
alter table sys_role_privilege
    comment '角色权限关联表';

# 测试数据
INSERT INTO `sys_user`
VALUES ('1', 'admin', '123456', 'admin@mybatis.tk', '管理员', null, '2016-04-01 17：00：58');
INSERT INTO `sys_user`
VALUES ('1001', 'test', '123456', 'test@mybatis.tk', '测试用户', null, '2016-04-01 17：01：52');
INSERT INTO `sys_role`
VALUES ('1', '管理员', '1', '1', '2016-04-01 17：02：14');
INSERT INTO `sys_role`
VALUES ('2', '普通用户', '1', '1', '2016-04-01 17：02：34');
INSERT INTO `sys_user_role`
VALUES ('1', '1');
INSERT INTO `sys_user_role`
VALUES ('1', '2');
INSERT INTO `sys_user_role`
VALUES ('1001', '2');
INSERT INTO `sys_privilege`
VALUES ('1', '用户管理', '/users');
INSERT INTO `sys_privilege`
VALUES ('1', '用户管理', '/users');
INSERT INTO `sys_privilege`
VALUES ('2', '角色管理', '/roles');
INSERT INTO `sys_privilege`
VALUES ('3', '系统日志', '/logs');
INSERT INTO `sys_privilege`
VALUES ('4', '人员维护', '/persons');
INSERT INTO `sys_privilege`
VALUES ('5', '单位维护', '/companies');
INSERT INTO `sys_role_privilege`
VALUES ('1', '1');
INSERT INTO `sys_role_privilege`
VALUES ('1', '3');
INSERT INTO `sys_role_privilege`
VALUES ('1', '2');
INSERT INTO `sys_role_privilege`
VALUES ('2', '4');
INSERT INTO `sys_role_privilege`
VALUES ('2', '5');

drop procedure if exists `select_user_by_id`;

delimiter ;;
# 根据用户ID查询用户信息
create procedure select_user_by_id(in userId bigint,
                                   out userName varchar(50),
                                   out userPassword varchar(50),
                                   out userEmail varchar(50),
                                   out userInfo text,
                                   out headImg blob,
                                   out createTime timestamp)
begin
    select id, user_name, user_password, user_email, user_info, head_img, create_time
    into userId, userName,
        userPassword,userEmail,userInfo,headImg,createTime
    from sys_user
    where id = userId;
end ;;
delimiter ;

drop procedure if exists select_user_page;
delimiter ;;
create procedure select_user_page(in userName varchar(50),
                                  in _offset bigint,
                                  in _limit bigint,
                                  out total bigint)

begin
    select count(*) from sys_user where user_name like concat('%', userName, '%');
    select * from sys_user where user_name like concat('%', userName, '%') limit _offset ,_limit;
end ;;
delimiter ;

drop procedure if exists insert_user_and_roles;
delimiter ;;
create procedure insert_user_and_roles(out userId bigint,
                                       in userName varchar(50),
                                       in userPassword varchar(50),
                                       in userEmail varchar(50),
                                       in userInfo text,
                                       in headImg blob,
                                       out createTime datetime,
                                       in roleIds varchar(200))
begin
    set createTime = now();
    insert into sys_user(user_name, user_password, user_email, user_info, head_img, create_time)
    values (userName, userPassword, userEmail, userInfo, headImg, createTime);
    select last_insert_id() into userId;
    set roleIds = concat(',', roleIds, ',');
    insert into sys_user_role (user_id, role_id)
    select userId, id
    from sys_user
    where instr(roleIds, concat(',', id, ',')) > 0;
end ;;

delimiter ;

drop procedure if exists delete_user_by_id;
delimiter ;;
create procedure delete_user_by_id(in userId bigint)
begin
    delete from sys_user_role where user_id = userId;
    delete from sys_user where id = userId;
end ;;

delimiter ;