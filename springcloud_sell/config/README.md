# config-server
spring-cloud config server

- 访问springcloud config-server的ip http://localhost:8080/label/name-profiles.yml
- label:分支 可省略 省略就是用master分支
- name：服务名 
- profiles：环境
- 配置文件不写环境（order.yml) 就是都会加载可以把公有变量放进去
