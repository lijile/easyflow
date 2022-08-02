## IDEA

- 关于IDEA的安装与使用请参考：https://github.com/judasn/IntelliJ-IDEA-Tutorial
- 搜索插件仓库，安装插件Lombok

![](https://raw.githubusercontent.com/lijile/easyflow/master/doc/images/Lombok.png)



## Mysql

- 下载并安装mysql **8.0**，下载地址：https://dev.mysql.com/downloads/installer/

- 设置数据库用户名和密码：easyflow_user/easyflow@2021，或在application-*.yml文件中修改对应信息。

- 分别导入doc/sql下的schema.sql和data.sql文件

  

## 启动后端项目

- 直接运行 org.lecoder.easyflow.EasyflowApplication 的main方法即可；
- 接口文档地址：http://localhost:8080/swagger-ui/


## 构建项目
- 构建前端项目
```
# 进入根目录下的easyflow-html
cd easyflow-html
# 安装依赖
npm install
# 执行构建
npm run build
```
- 打包jar
```
# 进入根目录
cd easyflow
# 执行构建
mvn clean package -Dmaven.test.skip=true
# 运行jar包
java -jar target/easyflow-1.0.0.jar
```
- 访问地址：http://localhost:8080/public/index.html