# simple-spring-boot-security-demo
基于spring boot + oauth2 + mybatis的restful api 服务器

# 启动服务
在根目录执行以下命令：
```
$ gradle build
$ gradle bootRun
```

# 测试服务

1. 获取accessToken，使用post方式获取，客户端配置见：SecurityServerConfiguration.java

```
http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=app&client_secret=123456

```
2. 验证受保护的url
```
2.1 不使用access_token 
url: http://localhost:8080/api/test
method: GET
result: 401

2.2 使用错误的access_token
url: http://localhost:8080/api/test?access_token=xxx
method: GET
result: invalid token

2.3 使用正确的access_token
url: http://localhost:8080/api/test?access_token=xxx
method: GET
result: {"tips":"this is a restful api with authorized", "code":1}

```
2. 验证未受保护url
```
http://localhost:8080/api1/test 
```

# 新增功能
1. 整合mybatis，构建完整的MVC基础框架，包含controller,service,dao层，代码检出即用
2. 通过db管理客户，适合对外提供接口服务的场景

# 建表语句
注意由于包里边写死了表名，所以表名不可修改。详情可参阅源码：JdbcTokenStore.java JdbcClientDetailsService.java等,如果提示无法插入数据，根据报错信息将有问题的字段类型改成varbinary即可
```

CREATE TABLE `oauth_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` varbinary(2048) DEFAULT NULL,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` varbinary(2048) DEFAULT NULL,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(256) DEFAULT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` text,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `oauth_client_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` text,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `oauth_refresh_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` varbinary(2048) DEFAULT NULL,
  `authentication` varbinary(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--- 测试表 ---
CREATE TABLE action_log (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `action_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
```