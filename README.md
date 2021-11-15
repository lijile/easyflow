## 项目介绍
easyflow 是一个简单、易用、高效的流程审批项目。

目前有不少的工作流引擎项目，例如JBPM、Activiti、Flowable等等，但是有个比较普遍存在的问题是，过于复杂，不易上手。
举个栗子，Activiti6.0以act_开头的核心数据表有28个之多，执行一个简单的流程涉及数据库操作太多，优化困难。

easyflow参考了Activiti的优秀设计思路，取其精华，去掉不常用功能，核心数据库仅此5个，一个简单的工作流，只需要执行几行语句即可生成。




### 项目演示
![](https://raw.githubusercontent.com/lijile/easyflow/master/doc/images/task_list.jpg)
![](https://raw.githubusercontent.com/lijile/easyflow/master/doc/images/task_approve.jpg)



## 技术选型

### 后端技术架构

| 技术         | 说明            | 官网 |
| ------------ | --------------- | ---- |
| Spring Boot  | 容器+MVC        |  https://spring.io/projects/spring-boot    |
| Mybatis      | ORM框架         |  http://www.mybatis.org/mybatis-3/zh/index.html   |
| Mybatis Plus | Mybatis的增强版 |   https://mp.baomidou.com/guide   |
| Swagger      | 文档生产工具    |   https://github.com/swagger-api/swagger-ui   |


### 前端技术架构

| 技术       | 说明           | 官网                                         |
| ---------- | -------------- | -------------------------------------------- |
| React      | 前端框架       | https://react.docschina.org/                 |
| Umi        | 阿里系前端框架 | https://umijs.org/zh-CN/docs/getting-started |
| Ant Design | 前端组件       | https://ant.design/components/overview-cn/   |




## 环境搭建

### 开发工具

工具 | 说明 | 官网
----|----|----
IDEA | 开发IDE | https://www.jetbrains.com/idea/download
RedisDesktop | redis客户端连接工具 | https://redisdesktop.com/download
Navicat | 数据库连接工具 | http://www.formysql.com/xiazai.html
MindMaster | 思维导图设计工具 | http://www.edrawsoft.cn/mindmaster
PicPick | 屏幕取色工具 | https://picpick.app/zh/

### 开发环境

工具 | 版本号 | 下载
----|----|----
JDK | 1.8 | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Mysql | 8.0 | https://www.mysql.com/
nginx | 1.10 | http://nginx.org/en/download.html



## 搭建步骤

### Windows版
- 本地安装开发环境中的所有工具并启动，具体参考[deploy-windows.md](doc/deploy/deploy_windows.md)
