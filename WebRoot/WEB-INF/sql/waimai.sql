-- --------------------------------------------------------
-- 主机:                           192.168.0.99
-- 服务器版本:                        5.7.18 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.4.0.5173
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 waimai 的数据库结构
CREATE DATABASE IF NOT EXISTS `waimai` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `waimai`;

-- 导出  表 waimai.ad_levels 结构
CREATE TABLE IF NOT EXISTS `ad_levels` (
  `level` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='country:国家\nprovince:省份（直辖市会在province和city显示）\ncity:市（直辖市会在province和city显示）\ndistrict:区县\nbiz_area:商圈（强烈建议利用showbiz参数跳过）\nstreet:街道';

-- 正在导出表  waimai.ad_levels 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `ad_levels` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_levels` ENABLE KEYS */;

-- 导出  表 waimai.ad_regions 结构
CREATE TABLE IF NOT EXISTS `ad_regions` (
  `adcode` mediumint(9) NOT NULL,
  `name` varchar(10) NOT NULL,
  `fk_ad_levels_level` varchar(10) DEFAULT NULL,
  `citycode` smallint(6) DEFAULT NULL COMMENT '城市编码',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '中心经度',
  `latitude` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`adcode`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.ad_regions 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `ad_regions` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_regions` ENABLE KEYS */;

-- 导出  表 waimai.comments 结构
CREATE TABLE IF NOT EXISTS `comments` (
  `fk_orders_orderid` int(11) NOT NULL,
  `fk_products_productid` int(11) NOT NULL,
  `fk_users_userid` int(11) NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `attitude_score` decimal(3,2) NOT NULL,
  `dish_score` decimal(3,2) NOT NULL,
  PRIMARY KEY (`fk_orders_orderid`,`fk_products_productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.comments 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- 导出  表 waimai.cust_addresses 结构
CREATE TABLE IF NOT EXISTS `cust_addresses` (
  `custaddid` int(11) NOT NULL,
  `fk_users_userid` int(11) NOT NULL,
  `fk_pois_poiid` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`custaddid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户地址';

-- 正在导出表  waimai.cust_addresses 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cust_addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `cust_addresses` ENABLE KEYS */;

-- 导出  表 waimai.delivery_rules 结构
CREATE TABLE IF NOT EXISTS `delivery_rules` (
  `delivery_rule` tinyint(4) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`delivery_rule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.delivery_rules 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `delivery_rules` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_rules` ENABLE KEYS */;

-- 导出  表 waimai.files 结构
CREATE TABLE IF NOT EXISTS `files` (
  `fileid` int(11) NOT NULL,
  `path` varchar(1000) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.files 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;

-- 导出  表 waimai.functions 结构
CREATE TABLE IF NOT EXISTS `functions` (
  `functionid` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `fk_views_viewid` int(11) DEFAULT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`functionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能表';

-- 正在导出表  waimai.functions 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `functions` DISABLE KEYS */;
/*!40000 ALTER TABLE `functions` ENABLE KEYS */;

-- 导出  表 waimai.orders 结构
CREATE TABLE IF NOT EXISTS `orders` (
  `orderid` int(11) NOT NULL,
  `fk_order_states_state` tinyint(4) NOT NULL,
  `fk_cust_addresses_custaddid` int(11) NOT NULL,
  `fk_shops_shopid` int(11) NOT NULL,
  `fk_delivery_rules_delivery_rule` tinyint(4) NOT NULL,
  `fk_users_userid` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `custname` varchar(20) NOT NULL,
  `shopname` varchar(25) NOT NULL,
  `total_price` decimal(12,2) NOT NULL,
  `ac_payment` decimal(12,2) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.orders 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 导出  表 waimai.order_items 结构
CREATE TABLE IF NOT EXISTS `order_items` (
  `fk_orders_orderid` int(11) NOT NULL,
  `fk_products_productid` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`fk_orders_orderid`,`fk_products_productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.order_items 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;

-- 导出  表 waimai.order_states 结构
CREATE TABLE IF NOT EXISTS `order_states` (
  `state` tinyint(4) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.order_states 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `order_states` DISABLE KEYS */;
INSERT INTO `order_states` (`state`, `description`) VALUES
	(1, '未收货'),
	(2, '已收货');
/*!40000 ALTER TABLE `order_states` ENABLE KEYS */;

-- 导出  表 waimai.pois 结构
CREATE TABLE IF NOT EXISTS `pois` (
  `poiid` varchar(15) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `fk_ad_regions_adcode` mediumint(9) DEFAULT NULL,
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`poiid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.pois 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `pois` DISABLE KEYS */;
/*!40000 ALTER TABLE `pois` ENABLE KEYS */;

-- 导出  表 waimai.products 结构
CREATE TABLE IF NOT EXISTS `products` (
  `productid` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `fk_shops_shopid` int(11) NOT NULL,
  `fk_files_icon` int(11) NOT NULL,
  `fk_files_picture` int(11) NOT NULL,
  `fk_product_cats_pdcatid` int(11) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `discount` decimal(2,2) DEFAULT NULL,
  `monthly_sale` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `attitude_score` decimal(3,2) DEFAULT NULL,
  `dish_score` decimal(3,2) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.products 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- 导出  表 waimai.product_cats 结构
CREATE TABLE IF NOT EXISTS `product_cats` (
  `pdcatid` int(11) NOT NULL,
  `fk_shops_shopid` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`pdcatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.product_cats 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `product_cats` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_cats` ENABLE KEYS */;

-- 导出  表 waimai.qualifications 结构
CREATE TABLE IF NOT EXISTS `qualifications` (
  `qualificationid` int(11) NOT NULL,
  `issue_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`qualificationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.qualifications 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qualifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `qualifications` ENABLE KEYS */;

-- 导出  表 waimai.qualifications_has_files 结构
CREATE TABLE IF NOT EXISTS `qualifications_has_files` (
  `fk_qualifications_qualificationid` int(11) NOT NULL,
  `fk_files_fileid` int(11) NOT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`fk_qualifications_qualificationid`,`fk_files_fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.qualifications_has_files 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qualifications_has_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `qualifications_has_files` ENABLE KEYS */;

-- 导出  表 waimai.roles 结构
CREATE TABLE IF NOT EXISTS `roles` (
  `roleid` tinyint(4) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  waimai.roles 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- 导出  表 waimai.roles_has_functions 结构
CREATE TABLE IF NOT EXISTS `roles_has_functions` (
  `fk_roles_roleid` tinyint(4) NOT NULL,
  `fk_functions_functionid` int(11) NOT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`fk_roles_roleid`,`fk_functions_functionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.roles_has_functions 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `roles_has_functions` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_has_functions` ENABLE KEYS */;

-- 导出  表 waimai.shops 结构
CREATE TABLE IF NOT EXISTS `shops` (
  `shopid` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `fk_shop_cats_shopcatid` int(11) NOT NULL,
  `fk_users_userid` int(11) NOT NULL,
  `fk_files_icon` int(11) DEFAULT NULL,
  `fk_pois_poiid` varchar(15) NOT NULL,
  `fk_qualifications_qualificationid` int(11) DEFAULT NULL,
  `fk_delivery_rules_delivery_rule` tinyint(4) NOT NULL,
  `fk_shop_states_shop_state` tinyint(4) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `base_price` decimal(12,2) DEFAULT NULL,
  `delivery_price` decimal(12,2) DEFAULT NULL,
  `average_time` int(11) DEFAULT NULL COMMENT '分钟',
  `open_time` time NOT NULL,
  `close_time` time NOT NULL,
  `phone` varchar(20) NOT NULL,
  `announcement` varchar(200) DEFAULT NULL,
  `attitude_score` decimal(3,2) DEFAULT NULL,
  `dish_score` decimal(3,2) DEFAULT NULL,
  `monthly_sale` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.shops 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `shops` DISABLE KEYS */;
/*!40000 ALTER TABLE `shops` ENABLE KEYS */;

-- 导出  表 waimai.shops_has_shop_tags 结构
CREATE TABLE IF NOT EXISTS `shops_has_shop_tags` (
  `fk_shops_shopid` int(11) NOT NULL,
  `fk_shop_tags_tagid` int(11) NOT NULL,
  PRIMARY KEY (`fk_shops_shopid`,`fk_shop_tags_tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.shops_has_shop_tags 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `shops_has_shop_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `shops_has_shop_tags` ENABLE KEYS */;

-- 导出  表 waimai.shop_cats 结构
CREATE TABLE IF NOT EXISTS `shop_cats` (
  `shopcatid` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`shopcatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.shop_cats 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `shop_cats` DISABLE KEYS */;
INSERT INTO `shop_cats` (`shopcatid`, `name`) VALUES
	(1, '餐厅'),
	(2, '超市商店');
/*!40000 ALTER TABLE `shop_cats` ENABLE KEYS */;

-- 导出  表 waimai.shop_states 结构
CREATE TABLE IF NOT EXISTS `shop_states` (
  `shop_state` tinyint(4) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`shop_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.shop_states 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `shop_states` DISABLE KEYS */;
INSERT INTO `shop_states` (`shop_state`, `description`) VALUES
	(1, '营业'),
	(2, '休息');
/*!40000 ALTER TABLE `shop_states` ENABLE KEYS */;

-- 导出  表 waimai.shop_tags 结构
CREATE TABLE IF NOT EXISTS `shop_tags` (
  `tagid` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.shop_tags 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `shop_tags` DISABLE KEYS */;
INSERT INTO `shop_tags` (`tagid`, `name`) VALUES
	(1, '快餐便当'),
	(2, '特色菜系'),
	(3, '小吃夜宵'),
	(4, '甜品饮品'),
	(5, '果蔬生鲜'),
	(6, '零食饮料');
/*!40000 ALTER TABLE `shop_tags` ENABLE KEYS */;

-- 导出  表 waimai.users 结构
CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `password` char(64) NOT NULL COMMENT '使用sha256加密',
  `uq_telephone` varchar(15) DEFAULT NULL,
  `uq_email` varchar(50) DEFAULT NULL,
  `point` int(11) DEFAULT NULL COMMENT '积分',
  `fk_roles_roleid` tinyint(4) NOT NULL COMMENT '角色',
  `fk_files_icon` int(11) DEFAULT NULL COMMENT '图标文件',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `flag` tinyint(4) NOT NULL COMMENT '标志位',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  waimai.users 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- 导出  表 waimai.views 结构
CREATE TABLE IF NOT EXISTS `views` (
  `viewid` int(11) NOT NULL,
  `path` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`viewid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  waimai.views 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `views` DISABLE KEYS */;
/*!40000 ALTER TABLE `views` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
