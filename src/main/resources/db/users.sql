CREATE TABLE `users` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `first_name` varchar(20) DEFAULT NULL COMMENT '姓',
  `last_name` varchar(20) DEFAULT NULL COMMENT '名',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_accessed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into users(user_name,first_name,last_name,is_active) values('a','b','c',0);
