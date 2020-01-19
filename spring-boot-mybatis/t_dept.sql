DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `deptName` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `deptLoca` varchar(32) DEFAULT NULL COMMENT '部门地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;