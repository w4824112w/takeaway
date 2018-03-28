/*
Navicat MySQL Data Transfer

Source Server         : local库
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : takeaway

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-28 21:16:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_ali_pay_notifys`
-- ----------------------------
DROP TABLE IF EXISTS `tb_ali_pay_notifys`;
CREATE TABLE `tb_ali_pay_notifys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notify_time` datetime DEFAULT NULL,
  `notify_id` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `out_trade_no` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `trade_no` varchar(255) DEFAULT NULL,
  `trade_status` varchar(255) DEFAULT NULL,
  `seller_id` varchar(255) DEFAULT NULL,
  `seller_email` varchar(255) DEFAULT NULL,
  `buyer_id` varchar(255) DEFAULT NULL,
  `buyer_email` varchar(255) DEFAULT NULL,
  `total_fee` decimal(10,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_payment` datetime DEFAULT NULL,
  `is_total_fee_adjust` varchar(255) DEFAULT NULL,
  `use_coupon` varchar(255) DEFAULT NULL,
  `discount` varchar(255) DEFAULT NULL,
  `refund_status` varchar(255) DEFAULT NULL,
  `gmt_refund` datetime DEFAULT NULL,
  `verify_date` datetime DEFAULT NULL,
  `is_verify` varchar(255) DEFAULT NULL,
  `process_status` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_ali_pay_notifys
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_cities`
-- ----------------------------
DROP TABLE IF EXISTS `tb_cities`;
CREATE TABLE `tb_cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_cities
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_coupons`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupons`;
CREATE TABLE `tb_coupons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `activity_description` varchar(255) DEFAULT NULL,
  `exceed` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `effective_time` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `limit_used` decimal(10,2) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupons
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_coupon_exchanges`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_exchanges`;
CREATE TABLE `tb_coupon_exchanges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `coupon_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_exchanges
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_coupon_exchange_records`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_exchange_records`;
CREATE TABLE `tb_coupon_exchange_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exchange_code` varchar(255) DEFAULT NULL,
  `exchange_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `exchange_id` int(11) DEFAULT NULL,
  `gain_time` datetime DEFAULT NULL,
  `send_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_exchange_records
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_coupon_grant_rules`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_grant_rules`;
CREATE TABLE `tb_coupon_grant_rules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL,
  `send_type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_grant_rules
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_coupon_grant_send_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_grant_send_type`;
CREATE TABLE `tb_coupon_grant_send_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_grant_send_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_coupon_merchants`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_merchants`;
CREATE TABLE `tb_coupon_merchants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_merchants
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_coupon_types`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_types`;
CREATE TABLE `tb_coupon_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_types
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_feedbacks`
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedbacks`;
CREATE TABLE `tb_feedbacks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_feedbacks
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_history_orders`
-- ----------------------------
DROP TABLE IF EXISTS `tb_history_orders`;
CREATE TABLE `tb_history_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_type` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_price` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `is_pay` int(11) DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  `pay_sno` varchar(255) DEFAULT NULL,
  `is_received` int(11) DEFAULT NULL,
  `received_date` datetime DEFAULT NULL,
  `is_refund` int(11) DEFAULT NULL,
  `refund_date` datetime DEFAULT NULL,
  `is_reservation` int(11) DEFAULT NULL,
  `reservation_date` datetime DEFAULT NULL,
  `is_reminder` int(11) DEFAULT NULL,
  `reminder_date` datetime DEFAULT NULL,
  `is_distribution` int(11) DEFAULT NULL,
  `merchant_type` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `is_invoice` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_history_orders
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_items`
-- ----------------------------
DROP TABLE IF EXISTS `tb_items`;
CREATE TABLE `tb_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_type` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `puton_date` datetime DEFAULT NULL,
  `putoff_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `delivery_start_time` datetime DEFAULT NULL,
  `delivery_end_time` datetime DEFAULT NULL,
  `origin_price` decimal(10,2) DEFAULT NULL,
  `cost_price` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `sales_volume` int(11) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  `remain` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `is_puton` int(11) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_items
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_item_merchants`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_merchants`;
CREATE TABLE `tb_item_merchants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_merchants
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_item_pictures`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_pictures`;
CREATE TABLE `tb_item_pictures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_pictures
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_item_propertys`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_propertys`;
CREATE TABLE `tb_item_propertys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `is_open` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_propertys
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_item_types`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_types`;
CREATE TABLE `tb_item_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_types
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_managers`
-- ----------------------------
DROP TABLE IF EXISTS `tb_managers`;
CREATE TABLE `tb_managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_managers
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_menus`
-- ----------------------------
DROP TABLE IF EXISTS `tb_menus`;
CREATE TABLE `tb_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `pid_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menus
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_merchants`
-- ----------------------------
DROP TABLE IF EXISTS `tb_merchants`;
CREATE TABLE `tb_merchants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `manager_phone` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `platform_commission` decimal(10,2) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `notice` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `distribution_infor` varchar(255) DEFAULT NULL,
  `starting_price` decimal(10,2) DEFAULT NULL,
  `full_free_distribution` decimal(10,2) DEFAULT NULL,
  `distribution_fee` decimal(10,2) DEFAULT NULL,
  `distribution_scope` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_merchants
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_merchant_pictures`
-- ----------------------------
DROP TABLE IF EXISTS `tb_merchant_pictures`;
CREATE TABLE `tb_merchant_pictures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `description` char(10) DEFAULT NULL,
  `created_at` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_merchant_pictures
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_merchant_prices`
-- ----------------------------
DROP TABLE IF EXISTS `tb_merchant_prices`;
CREATE TABLE `tb_merchant_prices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_merchant_prices
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_merchant_types`
-- ----------------------------
DROP TABLE IF EXISTS `tb_merchant_types`;
CREATE TABLE `tb_merchant_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_merchant_types
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_orders`
-- ----------------------------
DROP TABLE IF EXISTS `tb_orders`;
CREATE TABLE `tb_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_type` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_price` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `is_pay` int(11) DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  `pay_sno` varchar(255) DEFAULT NULL,
  `is_received` int(11) DEFAULT NULL,
  `received_date` datetime DEFAULT NULL,
  `is_refund` int(11) DEFAULT NULL,
  `refund_date` datetime DEFAULT NULL,
  `is_reservation` int(11) DEFAULT NULL,
  `reservation_date` datetime DEFAULT NULL,
  `is_reminder` int(11) DEFAULT NULL,
  `reminder_date` datetime DEFAULT NULL,
  `is_distribution` int(11) DEFAULT NULL,
  `merchant_type` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `is_invoice` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_orders
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_order_cancles`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_cancles`;
CREATE TABLE `tb_order_cancles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `oper_at` datetime DEFAULT NULL,
  `oper_man` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_cancles
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_order_coupon_records`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_coupon_records`;
CREATE TABLE `tb_order_coupon_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `coupon_type` int(11) DEFAULT NULL,
  `coupon_amout` decimal(10,2) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `user_coupon_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_coupon_records
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_order_reserves`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_reserves`;
CREATE TABLE `tb_order_reserves` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_delivery_time` datetime DEFAULT NULL,
  `hope_delivery_time` datetime DEFAULT NULL,
  `limit_delivery_time` datetime DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `oper_at` datetime DEFAULT NULL,
  `oper_man` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_reserves
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_order_types`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_types`;
CREATE TABLE `tb_order_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_types
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_pay_requests`
-- ----------------------------
DROP TABLE IF EXISTS `tb_pay_requests`;
CREATE TABLE `tb_pay_requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_type_id` int(11) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `notify_url` varchar(255) DEFAULT NULL,
  `jump_url` varchar(255) DEFAULT NULL,
  `is_pay` int(11) DEFAULT NULL,
  `is_notify` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `success_time` datetime DEFAULT NULL,
  `notify_time` datetime DEFAULT NULL,
  `notify_times` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pay_requests
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_pay_types`
-- ----------------------------
DROP TABLE IF EXISTS `tb_pay_types`;
CREATE TABLE `tb_pay_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pay_types
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_provinces`
-- ----------------------------
DROP TABLE IF EXISTS `tb_provinces`;
CREATE TABLE `tb_provinces` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_provinces
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_roles`
-- ----------------------------
DROP TABLE IF EXISTS `tb_roles`;
CREATE TABLE `tb_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_roles
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_role_menus`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menus`;
CREATE TABLE `tb_role_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_menus
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_users`
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) DEFAULT NULL,
  `微信token` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `invite_code` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `ori` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_users
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user_coupons`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_coupons`;
CREATE TABLE `tb_user_coupons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `gain_time` datetime DEFAULT NULL,
  `coupon_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_coupons
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_vip`
-- ----------------------------
DROP TABLE IF EXISTS `tb_vip`;
CREATE TABLE `tb_vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_vip
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_wx_pay_notifys`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wx_pay_notifys`;
CREATE TABLE `tb_wx_pay_notifys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time_end` datetime DEFAULT NULL,
  `out_trade_no` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `total_fee` decimal(10,2) DEFAULT NULL,
  `cash_fee` decimal(10,2) DEFAULT NULL,
  `bank_type` varchar(255) DEFAULT NULL,
  `err_code_des` varchar(255) DEFAULT NULL,
  `err_code` varchar(255) DEFAULT NULL,
  `result_code` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `nonce_str` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `is_verify` int(11) DEFAULT NULL,
  `verify_date` datetime DEFAULT NULL,
  `trade_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wx_pay_notifys
-- ----------------------------
