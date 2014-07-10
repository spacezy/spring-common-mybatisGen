
CREATE TABLE `department` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `name` VARCHAR(12) DEFAULT NULL COMMENT '部门名称',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8


CREATE TABLE `userinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长',
  `userName` varchar(12) DEFAULT NULL COMMENT '用户名',
  `favorite` varchar(12) DEFAULT NULL COMMENT '爱好',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `departId` int(11) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`),
  KEY `FK_userinfo_department` (`departId`),
  CONSTRAINT `FK_userinfo_department` FOREIGN KEY (`departId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
