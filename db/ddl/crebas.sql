/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/2/12 15:14:06                           */
/*==============================================================*/



/*==============================================================*/
/* Table: bank_define                                           */
/*==============================================================*/
create table bank_define
(
   bank_code            char(3) not null comment '银行编码',
   bank_name            varchar(150) comment '银行名称',
   is_avaliable         char default '0' comment '是否可用(1:是，0：否)',
   primary key (bank_code)
);

alter table bank_define comment '银行信息表';

/*==============================================================*/
/* Table: login_log                                             */
/*==============================================================*/
create table login_log
(
   login_log_id         int not null auto_increment comment '主键',
   user_code            int comment '用户编码',
   login_time           timestamp comment '登录时间',
   primary key (login_log_id)
);

/*==============================================================*/
/* Index: idx_login_log_user_code                               */
/*==============================================================*/
create index idx_login_log_user_code on login_log
(
   user_code
);

/*==============================================================*/
/* Table: product_detail                                        */
/*==============================================================*/
create table product_detail
(
   product_detail_id    int not null auto_increment comment '主键',
   product_code         varchar(20) comment '产品编码',
   original_total_amount decimal(16,2) comment '原始总可投金额',
   current_total_amount decimal(16,2) comment '当前可投金额',
   lock_start_time      timestamp comment '产品锁定开始时间',
   lock_end_time        timestamp comment '产品锁定结束时间',
   product_detail_desc  varchar(300) comment '产品信息描述',
   created_Date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间',
   created_by           varchar(150) comment '创建人',
   updated_by           varchar(150) comment '更新人',
   primary key (product_detail_id)
);

alter table product_detail comment '产品明细(一个标的下具体的债权信息，product_info表记录的是总括信息)';

/*==============================================================*/
/* Table: product_info                                          */
/*==============================================================*/
create table product_info
(
   product_code         varchar(20) not null comment '产品编码',
   product_name         varchar(20) comment '产品名称',
   total_amount         decimal(16,2) comment '产品标的总额',
   product_desc         varchar(300) comment '产品描述',
   bid_start_time       timestamp comment '产品投标开始时间',
   bid_end_time         timestamp comment '产品投标结束时间',
   lock_start_time      timestamp comment '产品锁定开始时间',
   lock_end_time        timestamp comment '产品锁定结束时间',
   predicate_rate_min   decimal(5,4) comment '预期最低年收益率',
   predicate_rate_max   decimal(5,4) comment '预期最高年收益率',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间',
   created_by           varchar(20) comment '创建人',
   updated_by           varchar(20) comment '更新人',
   primary key (product_code)
);

alter table product_info comment '产品信息';

/*==============================================================*/
/* Table: product_rate                                          */
/*==============================================================*/
create table product_rate
(
   product_code         varchar(20) comment '产品编码',
   rate_effective_date  timestamp comment '生效日期',
   rate_invalide_date   timestamp,
   rate                 decimal(5,4) comment '利率',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间'
);

alter table product_rate comment '产品利率';

/*==============================================================*/
/* Index: idx_product_rate_prod_code                            */
/*==============================================================*/
create index idx_product_rate_prod_code on product_rate
(
   product_code
);

/*==============================================================*/
/* Table: product_reward                                        */
/*==============================================================*/
create table product_reward
(
   product_reward_id    int not null auto_increment comment '主键',
   product_code         varchar(20) comment '产品编码',
   reword_code          varchar(20) comment '奖励编码',
   original_amount      decimal(16,2) comment '产品对应奖励原始金额',
   current_amount       decimal(16,2) comment '产品奖励当前可用金额',
   primary key (product_reward_id)
);

alter table product_reward comment '产品投标奖励';

/*==============================================================*/
/* Index: idx_product_reward_prod_code                          */
/*==============================================================*/
create index idx_product_reward_prod_code on product_reward
(
   product_code
);

/*==============================================================*/
/* Index: idx_product_reward_reward_code                        */
/*==============================================================*/
create index idx_product_reward_reward_code on product_reward
(
   reword_code
);

/*==============================================================*/
/* Table: product_rule                                          */
/*==============================================================*/
create table product_rule
(
   product_code         varchar(20) comment '产品编码',
   invest_amount_min    decimal(16,2) comment '最小投资额',
   invest_amount_max    decimal(16,2) comment '最大投资额',
   per_copy_amount      decimal(16,2) comment '每份可投金额(元/份)',
   effective_date       timestamp comment '生效日期',
   invidate_date        timestamp comment '失效日期'
);

alter table product_rule comment '产品规则';

/*==============================================================*/
/* Index: idx_product_rule_prod_code                            */
/*==============================================================*/
create index idx_product_rule_prod_code on product_rule
(
   product_code
);

/*==============================================================*/
/* Table: report_business_statistical                           */
/*==============================================================*/
create table report_business_statistical
(
   statistical_date     timestamp not null comment '统计日期',
   total_deposit_amount decimal(16,2) comment '当天总转入金额',
   total_withdraw_amount decimal(16,2) comment '当天总转出金额',
   total_interest       decimal(16,4) comment '当天总收益',
   primary key (statistical_date)
);

alter table report_business_statistical comment '业务统计报表
';

/*==============================================================*/
/* Table: reward_define                                         */
/*==============================================================*/
create table reward_define
(
   reward_code          varchar(20) not null comment '编码',
   reward_name          varchar(150) comment '名称',
   type                 varchar(4) comment '类型（0001:现金券 0002:加息券）',
   amount               decimal(16,2) comment '奖励的金额',
   reward_desc          varchar(20) comment '描述',
   effective_days       int comment '有效天数',
   effective_date       timestamp comment '有效期开始时间',
   invilate_date        timestamp comment '失效时间（有效期结束时间）',
   primary key (reward_code)
);

alter table reward_define comment '奖品定义表';

/*==============================================================*/
/* Table: third_platform                                        */
/*==============================================================*/
create table third_platform
(
   platform_id          char(2) not null comment '平台id',
   platform_name        varchar(100) comment '平台名称',
   is_available         char default '0' comment '是否可用(1:是 0:否)',
   primary key (platform_id)
);

alter table third_platform comment '第三方平台';

/*==============================================================*/
/* Table: third_trade_log                                       */
/*==============================================================*/
create table third_trade_log
(
   ttl_id               int not null auto_increment comment '主键',
   order_id             varchar(32) comment '订单号',
   platform_id          char(2) comment '平台id（01：支付宝 02：块钱）',
   request_data         text comment '发送给第三方平台的数据',
   response_data        text comment '第三方平台返回的数据',
   response_status      varchar(3) comment '第三方平台返回的状态（1：成功 0：失败）',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '更新时间',
   primary key (ttl_id)
);

alter table third_trade_log comment '第三方平台交易流水记录';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_code            int not null auto_increment comment '用户编码',
   user_name            varchar(150) comment '用户名',
   mobile_number        varchar(15) comment '手机号',
   device_id            varchar(100) comment '设备号',
   login_password       varchar(32) comment '登录密码(md5加密)',
   hand_password        varchar(32) comment '手势密码(md5加密)',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间',
   primary key (user_code)
);

alter table user comment '会员信息表';

/*==============================================================*/
/* Index: idx_user_name                                         */
/*==============================================================*/
create index idx_user_name on user
(
   user_code
);

/*==============================================================*/
/* Index: idx_mobile_number                                     */
/*==============================================================*/
create index idx_mobile_number on user
(
   mobile_number
);

/*==============================================================*/
/* Table: user_account                                          */
/*==============================================================*/
create table user_account
(
   user_code            int comment '用户编码',
   amount               decimal(16,2) comment '用户账号余额',
   total_interest       decimal(16,4) comment '利息总收入',
   investing_amount     decimal(16,2) comment '在投金额',
   lock_amount          decimal(16,2) comment '锁定金额'
);

alter table user_account comment '用户账户信息';

/*==============================================================*/
/* Index: idx_user_account_user_code                            */
/*==============================================================*/
create index idx_user_account_user_code on user_account
(
   user_code
);

/*==============================================================*/
/* Table: user_bind_card                                        */
/*==============================================================*/
create table user_bind_card
(
   user_code            int comment '用户编码',
   bank_card_number     varchar(25) comment '银行卡号',
   bank_code            char(3) comment '银行编号',
   reserve_mobile       varchar(15) comment '预留手机号',
   short_number         varchar(10) comment '短卡号(快钱支付使用)'
);

alter table user_bind_card comment '绑定银行卡信息';

/*==============================================================*/
/* Table: user_detail                                           */
/*==============================================================*/
create table user_detail
(
   user_code            int comment '用户编码',
   certificate_type     char(2) comment '证件类型(01:身份证)',
   certificate_number   varchar(20) comment '证件号码',
   real_name            varchar(150) comment '姓名',
   pay_password         varchar(32) comment '支付密码(md5)',
   is_mobile_authen     char default '0' comment '手机是否认证(1:是0:否)',
   mobile_authen_time   timestamp comment '手机认证时间',
   is_name_authen       char default '0' comment '身份是否认证',
   name_authen_time     timestamp comment '身份认证时间',
   register_time        timestamp comment '注册时间'
);

alter table user_detail comment '认证信息表';

/*==============================================================*/
/* Index: idx_user_detail_user_code                             */
/*==============================================================*/
create index idx_user_detail_user_code on user_detail
(
   user_code
);

/*==============================================================*/
/* Table: user_interest_detail                                  */
/*==============================================================*/
create table user_interest_detail
(
   user_product_id      int comment '主键',
   actual_interest      decimal(16,2) comment '实际收益金额',
   actual_interest_rate decimal(16,2) comment '实际收益率',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间'
);

alter table user_interest_detail comment '用户产品收益记录表(每日结算利息记录)';

/*==============================================================*/
/* Index: idx_user_interest_creat_date                          */
/*==============================================================*/
create index idx_user_interest_creat_date on user_interest_detail
(
   created_date
);

/*==============================================================*/
/* Index: idx_user_interest_user_prd_id                         */
/*==============================================================*/
create index idx_user_interest_user_prd_id on user_interest_detail
(
   user_product_id
);

/*==============================================================*/
/* Table: user_order                                            */
/*==============================================================*/
create table user_order
(
   order_id             varchar(32) not null comment '交易流水号',
   user_code            int comment '用户编码',
   product_code         varchar(20) comment '产品编码',
   amount               decimal(16,2) comment '投资金额',
   order_type           char(2) comment '类型：01：充值 02：提现 03：利息发放',
   status               char(2) comment '订单状态（01：已申请(资金锁定) 02：产品锁定期 03：结算结束）',
   message              varchar(200) comment '订单相关信息描述（成功、失败原因）',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间',
   primary key (order_id)
);

alter table user_order comment '用户订单流水记录';

/*==============================================================*/
/* Index: idx_user_order_user_code                              */
/*==============================================================*/
create index idx_user_order_user_code on user_order
(
   user_code
);

/*==============================================================*/
/* Index: idx_user_order_prod_code                              */
/*==============================================================*/
create index idx_user_order_prod_code on user_order
(
   product_code
);

/*==============================================================*/
/* Index: idx_user_order_updated_date                           */
/*==============================================================*/
create index idx_user_order_updated_date on user_order
(
   updated_date
);

/*==============================================================*/
/* Index: idx_user_order_created_date                           */
/*==============================================================*/
create index idx_user_order_created_date on user_order
(
   created_date
);

/*==============================================================*/
/* Table: user_product                                          */
/*==============================================================*/
create table user_product
(
   user_product_id      int not null auto_increment comment '主键',
   user_code            int comment '用户编码',
   product_code         varchar(20) comment '产品编码',
   current_amount       decimal(16,2) comment '当前投资金额（=前一天投资额+充值-提现+利息）',
   total_interest       decimal(16,2) comment '产品总收益',
   status               decimal(16,4) comment '投标状态（1：产品锁定期 2：结算结束）',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间',
   primary key (user_product_id)
);

alter table user_product comment '用户成功购买的产品列表汇总';

/*==============================================================*/
/* Index: pk_user_product_id                                    */
/*==============================================================*/
create index pk_user_product_id on user_product
(
   user_product_id
);

/*==============================================================*/
/* Index: idx_user_product_user_code                            */
/*==============================================================*/
create index idx_user_product_user_code on user_product
(
   user_code
);

/*==============================================================*/
/* Index: idx_user_product_prod_code                            */
/*==============================================================*/
create index idx_user_product_prod_code on user_product
(
   product_code
);

/*==============================================================*/
/* Table: user_product_detail                                   */
/*==============================================================*/
create table user_product_detail
(
   user_product_detail_id int not null auto_increment comment '主键',
   user_product_id      int comment '用户产品主键',
   product_detail_id    int comment '投资产品明细id',
   invest_amount        decimal(16,2) comment '投资金额',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '更新时间',
   primary key (user_product_detail_id)
);

alter table user_product_detail comment '购买产品明细';

/*==============================================================*/
/* Index: idx_user_prod_detail_user_prod_id                     */
/*==============================================================*/
create index idx_user_prod_detail_user_prod_id on user_product_detail
(
   user_product_id
);

/*==============================================================*/
/* Index: idx_user_prod_detail_prod_detail_id                   */
/*==============================================================*/
create index idx_user_prod_detail_prod_detail_id on user_product_detail
(
   product_detail_id
);

/*==============================================================*/
/* Index: idx_user_prod_detail_creat_date                       */
/*==============================================================*/
create index idx_user_prod_detail_creat_date on user_product_detail
(
   created_date
);

/*==============================================================*/
/* Table: user_reward                                           */
/*==============================================================*/
create table user_reward
(
   user_reward_id       int not null auto_increment comment '主键',
   user_code            int comment '用户编码',
   product_reward_id    int,
   reward_code          varchar(20) comment '奖品编码',
   reward_amount        decimal(16,2) comment '奖品金额',
   status               char comment '奖品状态(1:可用 2:失效)',
   created_Date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间',
   primary key (user_reward_id)
);

/*==============================================================*/
/* Table: user_reward_consume_log                               */
/*==============================================================*/
create table user_reward_consume_log
(
   consume_log_id       int not null auto_increment comment '主键',
   user_reward_id       int comment '消费的奖品id',
   consume_amount       decimal(16,2) comment '缴费金额',
   consume_purpose      varchar(100) comment '消费用途',
   created_date         timestamp comment '创建时间',
   updated_date         timestamp comment '修改时间',
   primary key (consume_log_id)
);

alter table user_reward_consume_log comment '奖励信息消费';

alter table login_log add constraint FK_ref_loginlog_user foreign key (user_code)
      references user (user_code) on delete restrict on update restrict;

alter table product_detail add constraint FK_ref_prod_detail_prod foreign key (product_code)
      references product_info (product_code) on delete restrict on update restrict;

alter table product_rate add constraint FK_ref_prod_rate_prod foreign key (product_code)
      references product_info (product_code) on delete restrict on update restrict;

alter table product_reward add constraint FK_ref_prod_reward_prod foreign key (product_code)
      references product_info (product_code) on delete restrict on update restrict;

alter table product_reward add constraint FK_ref_prod_reward_reward_def foreign key (reword_code)
      references reward_define (reward_code) on delete restrict on update restrict;

alter table product_rule add constraint FK_ref_prod_rule_prod foreign key (product_code)
      references product_info (product_code) on delete restrict on update restrict;

alter table third_trade_log add constraint FK_ref_third_trade_log_user_order foreign key (order_id)
      references user_order (order_id) on delete restrict on update restrict;

alter table third_trade_log add constraint FK_ref_trade_log_plat foreign key (platform_id)
      references third_platform (platform_id) on delete restrict on update restrict;

alter table user_account add constraint FK_ref_user_account_code foreign key (user_code)
      references user (user_code) on delete restrict on update restrict;

alter table user_bind_card add constraint FK_ref_user_bind_card_bank_def foreign key (bank_code)
      references bank_define (bank_code) on delete restrict on update restrict;

alter table user_bind_card add constraint FK_ref_user_bind_card_user foreign key (user_code)
      references user (user_code) on delete restrict on update restrict;

alter table user_detail add constraint FK_ref_user_detail_user foreign key (user_code)
      references user (user_code) on delete restrict on update restrict;

alter table user_interest_detail add constraint FK_ref_user_interest_user_prod foreign key (user_product_id)
      references user_product (user_product_id) on delete restrict on update restrict;

alter table user_order add constraint FK_ref_user_order_prod foreign key (product_code)
      references product_info (product_code) on delete restrict on update restrict;

alter table user_order add constraint FK_ref_user_order_user foreign key (user_code)
      references user (user_code) on delete restrict on update restrict;

alter table user_product add constraint FK_ref_user_prod_prod foreign key (product_code)
      references product_info (product_code) on delete restrict on update restrict;

alter table user_product add constraint FK_ref_user_prod_user foreign key (user_code)
      references user (user_code) on delete restrict on update restrict;

alter table user_product_detail add constraint FK_ref_user_prod_detail_prod_detail foreign key (product_detail_id)
      references product_detail (product_detail_id) on delete restrict on update restrict;

alter table user_product_detail add constraint FK_ref_user_prod_detail_prod_id foreign key (user_product_id)
      references user_product (user_product_id) on delete restrict on update restrict;

alter table user_reward add constraint FK_ref_user_reward_prod_reward foreign key (product_reward_id)
      references product_reward (product_reward_id) on delete restrict on update restrict;

alter table user_reward add constraint FK_ref_user_reward_reward_def foreign key (reward_code)
      references reward_define (reward_code) on delete restrict on update restrict;

alter table user_reward add constraint FK_ref_user_reward_user foreign key (user_code)
      references user (user_code) on delete restrict on update restrict;

alter table user_reward_consume_log add constraint FK_ref_user_reward_consume_user_reward foreign key (user_reward_id)
      references user_reward (user_reward_id) on delete restrict on update restrict;

