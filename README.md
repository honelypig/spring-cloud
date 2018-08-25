# spring-cloud
这是我最近学了springcloud后，根据所学内容整理的一个eureka集群。
1、bus 消息总线
2、clientandprovider 从config-server获取配置，并提供服务
3、config-server 用来作为从svn获取配置的模块
4、consumer  服务消费者，其中使用了负载均衡、Feign、Hystrix
5、provider  是第二个clientandprovider的复制版本。用来开启同样的多个服务
6、server  eureka的服务中心
7、zuul 网关，用来转发请求

注意事项：
1、启动顺序 6->3->2->4->7->1,主要是6、3、2要先启动
2、使用3时之前，要有svn 仓库，并在application.yml文件中配置svn仓库地址
3、bus消息总线有rabbitmq、kafka等，本案例用rabbitmq，需要先安装erlang，并安装rabbitmq server
更多细节可以参考我的博客https://my.oschina.net/u/3671217
