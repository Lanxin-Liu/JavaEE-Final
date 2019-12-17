-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: healthykitchen
-- ------------------------------------------------------
-- Server version	5.7.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Collection`
--

DROP TABLE IF EXISTS `Collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Collection` (
  `collection_name` varchar(20) DEFAULT NULL,
  `collection_user_id` int(11) DEFAULT NULL,
  `collection_recipe_id` int(11) DEFAULT NULL,
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`collection_id`),
  KEY `fk_collection_user_id_idx` (`collection_user_id`),
  KEY `fk_collection_recipe_id_idx` (`collection_recipe_id`),
  CONSTRAINT `fk_collection_recipe_id` FOREIGN KEY (`collection_recipe_id`) REFERENCES `Recipe` (`recipe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_collection_user_id` FOREIGN KEY (`collection_user_id`) REFERENCES `User_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Collection`
--

LOCK TABLES `Collection` WRITE;
/*!40000 ALTER TABLE `Collection` DISABLE KEYS */;
INSERT INTO `Collection` VALUES ('DeFault',1,40,10);
/*!40000 ALTER TABLE `Collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_user_id` int(11) DEFAULT NULL,
  `comment_recipe_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_user_id_idx` (`comment_user_id`),
  KEY `fk_comment_recipe_id_idx` (`comment_recipe_id`),
  CONSTRAINT `fk_comment_recipe_id` FOREIGN KEY (`comment_recipe_id`) REFERENCES `Recipe` (`recipe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`comment_user_id`) REFERENCES `User_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DailyPlan`
--

DROP TABLE IF EXISTS `DailyPlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DailyPlan` (
  `DP_id` int(11) NOT NULL AUTO_INCREMENT,
  `DP_date` datetime DEFAULT NULL,
  `DP_calorie` int(11) DEFAULT NULL,
  `DP_user_id` int(11) NOT NULL,
  `DP_recipe_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`DP_id`),
  KEY `fk_DP_user_id_idx` (`DP_user_id`),
  KEY `fk_DP_recipe_id_idx` (`DP_recipe_id`),
  CONSTRAINT `fk_DP_recipe_id` FOREIGN KEY (`DP_recipe_id`) REFERENCES `Recipe` (`recipe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DP_user_id` FOREIGN KEY (`DP_user_id`) REFERENCES `User_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DailyPlan`
--

LOCK TABLES `DailyPlan` WRITE;
/*!40000 ALTER TABLE `DailyPlan` DISABLE KEYS */;
INSERT INTO `DailyPlan` VALUES (13,'2019-12-17 02:45:41',900,1,40);
/*!40000 ALTER TABLE `DailyPlan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Follow`
--

DROP TABLE IF EXISTS `Follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Follow` (
  `followed_user_id` int(11) NOT NULL,
  `following_user_id` int(11) NOT NULL,
  `follow_time` datetime DEFAULT NULL,
  PRIMARY KEY (`followed_user_id`,`following_user_id`),
  KEY `fk_following_user_id_idx` (`following_user_id`),
  CONSTRAINT `fk_followed_user_id` FOREIGN KEY (`followed_user_id`) REFERENCES `User_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_following_user_id` FOREIGN KEY (`following_user_id`) REFERENCES `User_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Follow`
--

LOCK TABLES `Follow` WRITE;
/*!40000 ALTER TABLE `Follow` DISABLE KEYS */;
INSERT INTO `Follow` VALUES (1,5,'2019-12-17 02:46:11');
/*!40000 ALTER TABLE `Follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Love`
--

DROP TABLE IF EXISTS `Love`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Love` (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `like_recipe_id` int(11) NOT NULL,
  `like_user_id` int(11) NOT NULL,
  `like_time` datetime DEFAULT NULL,
  PRIMARY KEY (`like_id`,`like_recipe_id`,`like_user_id`),
  KEY `like_recipe_id_idx` (`like_recipe_id`),
  KEY `like_user_id_idx` (`like_user_id`),
  CONSTRAINT `fk_like_recipe_id` FOREIGN KEY (`like_recipe_id`) REFERENCES `Recipe` (`recipe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_user_id` FOREIGN KEY (`like_user_id`) REFERENCES `User_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Love`
--

LOCK TABLES `Love` WRITE;
/*!40000 ALTER TABLE `Love` DISABLE KEYS */;
INSERT INTO `Love` VALUES (7,40,1,'2019-12-17 02:45:37');
/*!40000 ALTER TABLE `Love` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Material`
--

DROP TABLE IF EXISTS `Material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Material` (
  `material_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `calorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`material_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Material`
--

LOCK TABLES `Material` WRITE;
/*!40000 ALTER TABLE `Material` DISABLE KEYS */;
INSERT INTO `Material` VALUES ('可乐',3),('可可粉',7),('土豆粉',4),('基围虾',2),('多宝鱼',3),('大蒜',2),('奶油',8),('奶酪',3),('姜',3),('富强粉',3),('小米',3),('山药',2),('方便面',4),('有机花菜',1),('松子',16),('核桃',16),('橄榄油',9),('淀粉',4),('炼乳',4),('牛奶',1),('牛肉',2),('猪油',9),('猪肉',4),('玉米',2),('玉米油',4),('生菜',1),('白糖',4),('盐',1),('米醋',1),('米饭',NULL),('糯米粉',3),('红糖',4),('纯净水',1),('羊肉',6),('胡萝卜',1),('芝麻酱',6),('花生油',9),('花生酱',6),('苦瓜',1),('茄子',1),('草鱼',2),('菜籽油',9),('葡萄',1),('葱',1),('蒜',1),('薏米',3),('螃蟹',2),('西红柿',1),('酱油',1),('酵母',1),('酸菜',2),('面粉',3),('香大米',3),('香菜',1),('鲈鱼',2),('鳜鱼',3),('鸡翅',3),('鸡肉',4),('鸡蛋',2),('鸭蛋',3),('鹌鹑蛋',2),('黄油',9),('黄米',3),('黄酒',2),('黑胡椒粉',1);
/*!40000 ALTER TABLE `Material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recipe`
--

DROP TABLE IF EXISTS `Recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recipe` (
  `recipe_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `recipe_time` datetime DEFAULT NULL,
  `recipe_tag` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `recipe_image` varchar(255) DEFAULT NULL,
  `like_num` int(11) DEFAULT NULL,
  `collect_num` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `recipe_user_id` int(11) NOT NULL,
  `recipe_desc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `calorie` int(11) DEFAULT NULL,
  `recipe_username` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`recipe_id`),
  KEY `user_id_idx` (`recipe_user_id`),
  KEY `fk_recipe_tag_id_idx` (`recipe_tag`),
  KEY `fk_recipe_username_idx` (`recipe_username`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recipe`
--

LOCK TABLES `Recipe` WRITE;
/*!40000 ALTER TABLE `Recipe` DISABLE KEYS */;
INSERT INTO `Recipe` VALUES (31,'松软养胃的山药饼','2019-12-17 01:32:29','晚餐','http://localhost:8443/api/file/00.jpg',NULL,NULL,1,7,'山药更是一种高营养低热量的食物，100g的山药只有56千卡的热量，可以减少皮下脂肪的堆积，吃了不容易发胖。',NULL,'admin7'),(32,'加这两样东西，有机花菜更好吃','2019-12-17 01:55:18','晚餐','http://localhost:8443/api/file/tqnhnojpeg',NULL,NULL,3,1,NULL,NULL,NULL),(33,'可可粉制简易巧克力','2019-12-17 02:03:03','小孩','http://localhost:8443/api/file/k8z2oujpeg',NULL,NULL,5,2,NULL,NULL,'admin2'),(34,'哈哈，只是不是你最爱吃的酸菜鱼！','2019-12-17 02:07:23','晚餐','http://localhost:8443/api/file/5ejc5ejpeg',NULL,NULL,2,1,NULL,NULL,NULL),(35,'西红柿炒蛋！单身必备快手菜','2019-12-17 02:08:45','无糖','http://localhost:8443/api/file/x9n8kpjpeg',NULL,NULL,3,3,NULL,NULL,'admin3'),(36,'教你剥葡萄！','2019-12-17 02:15:01','减肥','http://localhost:8443/api/file/i5x8qpjpeg',NULL,NULL,3,1,NULL,NULL,NULL),(37,'三分钟搞定的蚝油生菜','2019-12-17 02:21:39','老人','http://localhost:8443/api/file/kjmktnjpeg',NULL,NULL,2,4,NULL,NULL,'admin4'),(38,'黄金玉米烙','2019-12-17 02:23:44','早餐','http://localhost:8443/api/file/3jrnnhjpeg',NULL,NULL,1,1,NULL,NULL,NULL),(39,'酱油炒饭','2019-12-17 02:28:13','午餐','http://localhost:8443/api/file/eqzm38jpeg',NULL,NULL,0,5,NULL,NULL,'admin5'),(40,'酸汤龙利鱼','2019-12-17 02:29:24','孕妇','http://localhost:8443/api/file/j6ufgxjpeg',1,NULL,3,1,NULL,900,NULL);
/*!40000 ALTER TABLE `Recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecipeContent`
--

DROP TABLE IF EXISTS `RecipeContent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RecipeContent` (
  `step_id` int(11) NOT NULL,
  `step_recipe_id` int(11) NOT NULL,
  `step_desc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `step_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`step_id`,`step_recipe_id`),
  KEY `fk_recipe_id_idx` (`step_recipe_id`),
  CONSTRAINT `fk_step_recipe_id` FOREIGN KEY (`step_recipe_id`) REFERENCES `Recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecipeContent`
--

LOCK TABLES `RecipeContent` WRITE;
/*!40000 ALTER TABLE `RecipeContent` DISABLE KEYS */;
INSERT INTO `RecipeContent` VALUES (1,31,'山药切片，入锅蒸10分钟左右','http://localhost:8443/api/file/0.jpg'),(1,32,'配菜：花菜切去多余的茎，用刀劈成合适的大小，小一点更入味，也好熟。西红柿切小块。葱斜切成圈，姜切丝。','http://localhost:8443/api/file/11.jpg'),(1,33,'隔水加热，碗中倒入150ml纯净水，加入白砂糖40g溶解。','http://localhost:8443/api/file/16.jpg'),(1,36,'准备一盆冰水。','http://localhost:8443/api/file/21.jpg'),(1,38,'打开一罐玉米粒，倒入盘子里，控干水','http://localhost:8443/api/file/50.jpg'),(2,31,'放入容器中捣成山药泥，不要有颗粒状','http://localhost:8443/api/file/1.jpg'),(2,32,'热锅冷油，放入葱姜，翻炒半分钟','http://localhost:8443/api/file/12.jpg'),(2,33,'70g的可可粉一定要分次加入，隔水加热的温度保持住，但不要超过70℃；\n搅拌至无明显粉末即可。','http://localhost:8443/api/file/17.jpg'),(2,36,'锅里放水（水量要足够使葡萄浮上水面）开火烧水。\n！！！水烧开后放入葡萄粒（一次不要放太多,最多20粒左右。）','http://localhost:8443/api/file/22.jpg'),(3,31,'山药泥冷至20度左右加入面粉、酵母、玉米油、炼乳（没有炼乳放白糖也可以），打入鸡蛋','http://localhost:8443/api/file/2.jpg'),(3,32,'加入西红柿，翻炒至出沙，加小半碗水，炖一两分钟，让汤汁浓稠，加入盐、黑胡椒粉','http://localhost:8443/api/file/13.jpg'),(3,33,'黄油提前室温软化，此时加入黄油40g，可以很明显地发现巧克力浆变得更加顺滑。','http://localhost:8443/api/file/18.jpg'),(3,36,'在锅里焯20－30秒（会看到一些普葡萄皮裂开）后迅速将葡萄粒捞出,放进冰水里拔凉（放置5分钟左右）（在热水里煮30秒的大粒葡萄仍然处于生的状态）','http://localhost:8443/api/file/23.jpg'),(4,31,'用筷子搅拌成絮状，之后揉成一个光滑的面团，覆盖保鲜膜发酵至2倍大','http://localhost:8443/api/file/3.jpg'),(4,32,'倒入菜花，转中火，翻炒3分钟左右，菜花茎部变熟发青，即可盛出。喜欢软一点的可以多炒一会儿。','http://localhost:8443/api/file/14.jpg'),(4,33,'将可可浆倒入容器即可，要吃的时候直接用勺挖着吃，太过瘾了哈哈哈','http://localhost:8443/api/file/19.jpg'),(4,36,'5分钟过后,将葡萄从水里捞出,放进一个碗里,将皮剥去。','http://localhost:8443/api/file/24.jpg'),(5,31,'发酵好之后取出和成面团，面和的越软，饼越好吃，但是面团不要太稀，稀了不成形，可以加入适量干粉揉匀','http://localhost:8443/api/file/5.jpg'),(5,32,'黑椒西红柿汁能渗到菜花里去，色泽很棒，酸辣口感非常好，清爽又健康。','http://localhost:8443/api/file/15.jpg'),(6,31,'用圆形模具压出形状，个人认为小一点比较好看','http://localhost:8443/api/file/6.jpg'),(7,31,'使用小火预热平底锅，逐一放入发酵好的饼（饼一定要发酵好，再入锅）刷上一层油，放入饼盖上盖子，轻微晃动锅，使锅底受热均匀，直至烙至表面呈金黄色','http://localhost:8443/api/file/8.jpg'),(8,31,'大约1分钟左右揭盖，锅里的饼就变得胖乎乎的，烙好一面，翻过来再烙另一面(一定要用小火！）','http://localhost:8443/api/file/9.jpg'),(9,31,'做好啦！','http://localhost:8443/api/file/00.jpg');
/*!40000 ALTER TABLE `RecipeContent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recipe_has_Material`
--

DROP TABLE IF EXISTS `Recipe_has_Material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recipe_has_Material` (
  `recipe_id` int(11) NOT NULL,
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `material_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`,`material_name`),
  CONSTRAINT `fk_recipe_id` FOREIGN KEY (`recipe_id`) REFERENCES `Recipe` (`recipe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recipe_has_Material`
--

LOCK TABLES `Recipe_has_Material` WRITE;
/*!40000 ALTER TABLE `Recipe_has_Material` DISABLE KEYS */;
INSERT INTO `Recipe_has_Material` VALUES (31,'山药',300),(31,'玉米油',10),(31,'白糖',20),(31,'酵母',3),(31,'面粉',330),(31,'鸡蛋',100),(32,'姜',10),(32,'有机花菜',500),(32,'油',8),(32,'盐',10),(32,'西红柿',50),(32,'黑胡椒粉',10),(33,'动物黄油',40),(33,'可可粉',70),(33,'白糖',40),(33,'纯净水',150),(34,'草鱼',100),(34,'酸菜',50),(35,'西红柿',50),(35,'鸡蛋',100),(36,'葡萄',100),(37,'生菜',400),(37,'盐',10),(37,'酱油',20),(38,'淀粉',10),(38,'玉米',300),(38,'白糖',20),(38,'花生油',100),(39,'盐',5),(39,'米饭',500),(39,'葱',10),(39,'酱油',10),(40,'鳜鱼',300);
/*!40000 ALTER TABLE `Recipe_has_Material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tag` (
  `tag_id` int(11) DEFAULT NULL,
  `tag_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tag`
--

LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO `Tag` VALUES (5,'减肥'),(2,'午餐'),(6,'孕妇'),(8,'小孩'),(4,'无糖'),(1,'早餐'),(3,'晚餐'),(7,'老人');
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tip`
--

DROP TABLE IF EXISTS `Tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tip` (
  `tip_id` int(11) NOT NULL AUTO_INCREMENT,
  `tip_content` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tip`
--

LOCK TABLES `Tip` WRITE;
/*!40000 ALTER TABLE `Tip` DISABLE KEYS */;
INSERT INTO `Tip` VALUES (1,'绿茶减轻辐射影响'),(2,'西红柿减少皮肤辐射损伤;'),(3,'剩菜时间不宜过长，以不隔餐为宜。最好能五六个小时内吃掉。'),(4,'豆浆中有能除掉保温瓶水垢的物质，温度适宜条件以豆浆为养料，瓶内细菌大量繁殖'),(5,'蜂蜜可促进酒精分解排泄，保护肝脏。'),(6,'生梨润肺化痰好，苹果止泻营养高。'),(7,'黄瓜减肥有成效，抑制癌症猕猴桃。'),(8,'萝卜消食除胀气，芹菜能治血压高。'),(9,'冬瓜消肿有利尿，绿豆解毒疗效高。'),(10,'早点最好是安排在7点以后吃会比较适宜，有早起床习惯的人群不要太早吃早点。'),(11,'糖尿病人要使全天总热量控制在规定范围内，不仅要控制主食，也要限制副食。');
/*!40000 ALTER TABLE `Tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_info`
--

DROP TABLE IF EXISTS `User_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_info` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `followed_num` int(11) DEFAULT NULL,
  `following_num` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_info`
--

LOCK TABLES `User_info` WRITE;
/*!40000 ALTER TABLE `User_info` DISABLE KEYS */;
INSERT INTO `User_info` VALUES (1,'admin1','123456','','今天吃了啥','1',0,'1'),(2,'admin2','123456','','今天吃了啥','1',0,'0'),(3,'admin3','123456','','今天吃了啥','1',0,'0'),(4,'admin4','123456','','今天吃了啥','1',0,'0'),(5,'admin5','123456','','今天吃了啥','1',1,'0'),(6,'admin6','123456','','今天吃了啥','1',0,'0'),(7,'admin7','123456','','今天吃了啥','1',0,'0'),(8,'admin8','123456','','今天吃了啥','1',0,'0'),(9,'admin9','123456','','今天吃了啥','1',0,'0'),(10,'admin10','123456','','今天吃了啥','1',0,'0'),(11,'admin11','123456','','今天吃了啥','1',0,'0'),(12,'admin12','123456','','今天吃了啥','1',0,'0'),(13,'admin13','123456','','今天吃了啥','1',0,'0'),(14,'admin14','123456','','今天吃了啥','1',0,'0'),(15,'admin15','123456','','今天吃了啥','1',0,'0'),(16,'admin16','123456','','今天吃了啥','1',0,'0'),(17,'admin17','123456','','今天吃了啥','1',0,'0'),(18,'admin18','123456','','今天吃了啥','1',0,'0'),(19,'admin19','123456','','今天吃了啥','1',0,'0'),(20,'admin20','123456','','今天吃了啥','1',0,'0'),(21,'admin21','123456','','今天吃了啥','1',0,'0'),(22,'admin22','123456','','今天吃了啥','1',0,'0'),(23,'admin23','123456','','今天吃了啥','1',0,'0'),(24,'admin24','123456','','今天吃了啥','1',0,'0'),(25,'admin25','123456','','今天吃了啥','1',0,'0'),(26,'admin26','123456','','今天吃了啥','1',0,'0'),(27,'admin27','123456','','今天吃了啥','1',0,'0'),(28,'admin28','123456','','今天吃了啥','1',0,'0'),(29,'admin29','123456','','今天吃了啥','1',0,'0'),(30,'admin30','123456','','今天吃了啥','1',0,'0');
/*!40000 ALTER TABLE `User_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-17  3:11:48
