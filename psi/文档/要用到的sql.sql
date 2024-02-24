-- 创建数据库
create database psi CHARACTER SET utf8;
use psi;

set foreign_key_checks = 0;

drop table if exists user;
create table `user` (
  `id` bigint(20) not null auto_increment comment '主键',
  `user_name` varchar(50) not null comment '用户名',
  `pwd` varchar(50) not null comment '密码',
  `role_id` int(10) not null  comment '角色id',
  primary key (`id`),
  UNIQUE KEY `uk_user_name` (`user_name`) USING BTREE
) engine=InnoDB  default charset=utf8 comment '用户表';
insert into user(user_name,pwd,role_id) values ('admin','123456',1);

drop table if exists supplier;
create table `supplier` (
  `id` bigint(20) not null auto_increment comment '主键',
  `name` varchar(200) not null comment '供应商名',
  primary key (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) engine=InnoDB  default charset=utf8 comment '供应商表';


drop table if exists goods;
create table `goods` (
  `id` bigint(20) not null auto_increment comment '主键',
  `name` varchar(150) not null comment '商品名',
  `born_place` varchar(50) default null comment '产地',
  `sale_price` numeric(10,2) not null comment '销售价,单位:元',
  `stock` int(10) not null default 0 comment '库存',
  `unit` varchar(30) not null comment '单位',
  `specifications` varchar(150) not null comment '规格',
  primary key (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) engine=InnoDB  default charset=utf8 comment '商品表';


drop table if exists purchase;
create table `purchase` (
  `id` bigint(20) not null auto_increment comment '主键',
  `purchase_no` varchar(50) not null comment '进货批次号',
  `operator_id` bigint(20) not null comment '经办人id',
  status tinyint(1) not null default 0 comment '0:待审核 1:审核通过 2:审核拒绝,3:已退货',
  `purchase_date` timestamp not null default current_timestamp  comment '进货日期',
  primary key (`id`),
  UNIQUE KEY `uk_purchase_no` (`purchase_no`) USING BTREE
) engine=InnoDB  default charset=utf8 comment '进货表';

drop table if exists purchase_detail;
create table `purchase_detail` (
  `id` bigint(20) not null auto_increment comment '主键',
  `purchase_id` bigint(20) not null comment 'purchase表主键',
  `purchase_no` varchar(50) not null comment '进货批次号,业务外键',
  `goods_id` bigint(20) not null comment '商品表的主键',
  `buy_price` numeric(10,2) not null comment '采购价,单位:元',
  `num` int(10) not null comment '采购数量',
  `supplier_id` bigint(20) not null comment '供应商表的主键',
  primary key (`id`)
) engine=InnoDB  default charset=utf8 comment '进货明细表';

drop table if exists return_goods;
create table `return_goods` (
  `id` bigint(20) not null auto_increment comment '主键',
  `purchase_no` varchar(50) not null comment '进货批次号,业务外键',
  `ret_no` varchar(50) not null comment '退货批次号',
  `operator_id` bigint(20) not null comment '经办人id',
  status tinyint(1) not null default 0 comment '0:待审核 1:审核通过 2:审核拒绝',
  `return_date` timestamp not null default current_timestamp  comment '进货日期',
  primary key (`id`),
  UNIQUE KEY `uk_ret_no` (`ret_no`) USING BTREE
) engine=InnoDB  default charset=utf8 comment '退货表';

drop table if exists return_goods_detail;
create table `return_goods_detail` (
  `id` bigint(20) not null auto_increment comment '主键',
  `ret_id` bigint(20) not null comment 'return_goods表主键',
  `ret_no` varchar(50) not null comment '退货批次号',
  `goods_id` bigint(20) not null comment '商品表的主键',
  `return_price` numeric(10,2) not null comment '退货价格,单位:元',
  `num` int(10) not null comment '采购数量',
  `supplier_id` bigint(20) not null comment '供应商表的主键',
  primary key (`id`)
) engine=InnoDB  default charset=utf8 comment '进货明细表';

drop table if exists sale_order;
create table `sale_order` (
  `id` bigint(20) not null auto_increment comment '主键',
  `order_no` varchar(50) not null comment '订单号',
  `total_amount` numeric(10,2) not null comment '商品总价,单位:元',
   status tinyint(1) not null default 0 comment '0:已完成 1:已退款',
  `create_time` timestamp not null default current_timestamp  comment '创建日期',
  primary key (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`) USING BTREE
) engine=InnoDB  default charset=utf8 comment '销售订单表';

drop table if exists sale_order_detail;
create table `sale_order_detail` (
  `id` bigint(20) not null auto_increment comment '主键',
  `order_id` bigint(20) not null comment '订单表主键',
  `goods_id` bigint(20) not null comment '商品表的主键',
  `sale_price` numeric(10,2) not null comment '销售价格,单位:元',
  `num` int(10) not null comment '销售数量',
  primary key (`id`)
) engine=InnoDB  default charset=utf8 comment '销售订单明细表';


drop table if exists refund;
create table `refund` (
  `id` bigint(20) not null auto_increment comment '主键',
  `order_no` varchar(50) not null comment 'sale_order表主键',
  `refund_no` varchar(50) not null comment '退款编号',
  `total_amount` numeric(10,2) not null comment '退款总金额,单位:元',
  `create_time` timestamp not null default current_timestamp  comment '创建日期',
  primary key (`id`),
  UNIQUE KEY `uk_refund_no` (`refund_no`) USING BTREE
) engine=InnoDB  default charset=utf8 comment '退款表';

drop table if exists refund_detail;
create table `refund_detail` (
  `id` bigint(20) not null auto_increment comment '主键',
  `refund_id` bigint(20) not null comment '退款表主键',
  `goods_id` bigint(20) not null comment '商品表的主键',
  `refund_price` numeric(10,2) not null comment '退款价格,单位:元',
  `num` int(10) not null comment '销售数量',
  primary key (`id`)
) engine=InnoDB  default charset=utf8 comment '退款明细表';

set foreign_key_checks = 1;