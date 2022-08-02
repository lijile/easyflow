# easyflow-html

easyflow 是一个简单、易用、高效的流程审批项目.

## 环境准备

测试时，由于前端和后端地址不一致，为了解决跨域问题，需要修改 config/proxy.js 的代理地址；

此外，需要根据需要修改 src/utils/consts.js 的服务器地址前缀。

```bash
npm install
```

### 项目启动

```bash
npm start
```

### 项目构建

```bash
npm run build
```

### Nginx 配置文件参考

```
location ^~ /easyflow/html {
    root   D:/software/nginx/data/html;
    try_files $uri $uri/ /easyflow/html/index.html;
}
```
