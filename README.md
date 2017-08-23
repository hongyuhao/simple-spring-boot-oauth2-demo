# simple-spring-boot-security-demo
基于spring boot + oauth2的restful api 服务器

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

# 备注
此项目结构目前相对比较简单，客户端账号暂时通过写死的方式，存储token暂时使用默认的InMemoryTokenService, 后续将上传通过数据库获取授权客户端的方式，敬请关注。如有需要改进的获取有其他意见可与本人联系，邮箱：hyhsoftware@163.com