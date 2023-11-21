drop table if exists "member";
create table `member`
(
    `id`     bigint not null comment 'id',
    `mobile` varchar(11) comment 'phone number',
    primary key (`id`),
    unique key `mobile_unique` (`mobile`)

) engine = InnoDB
  default charset = utf8mb4 comment 'member table';


drop table if exists `passenger`;
create table `passenger` (
    `id` bigint not null comment 'id',
    `member_id` bigint not null comment 'member id',
    `name` varchar(20) not null comment 'passenger name',
    `id_card` varchar(18) not null comment 'passenger id card NO',
    `type` char(1) not null comment 'passenger type|enum[PassengerTypeEnum]',
    `create_time` datetime(3) comment 'create time of the record',
    `update_time` datetime(3) comment 'update time of the record',
    primary key (`id`),
    index `member_id_index` (`member_id`)
) engine = InnoDB
  default charset = utf8mb4 comment 'passenger';