# 新闻发布系统

#### 介绍
第九小组的新闻发布系统

#### 软件架构
软件架构说明
entity目录：实体层，数据库在springboot项目中的对应的实体类。

dao目录：持久化数据层，主要与数据库交互。创建sql语句。

service目录：先创建service接口，再创建serviceImpl实现类，实现类调用dao层的方法进行实现。注意事务处理与并发这一层。

controller目录：主要调用Service层里面的接口控制具体的业务流程。

listen目录：监听器。主要关注自动化监听，提供了自定义事件，自定义监听器，以及如何调用。

exceptiondeal目录：异常处理。广泛的异常处理，可以拦截发出的异常，进行友好显示以及日志提示。
自定义异常处理，只需要在BusinessMsgEnum加上一个枚举型，再到异常处理中拦截即可。有例子。

aop目录：面向切面编程，主要用于日志记录、性能统计、安全控制、事务处理，项目中
的应用场景：可以使用AOP来记录新闻编辑、审核和发布的时间和操作人员，以便进行后续的审计和追踪。
此外，AOP还可以用于实现新闻发布系统中的事务处理，确保在新闻发布过程中的数据一致性和完整性。
另外，AOP还可以用于实现新闻发布系统中的安全控制，例如，可以使用AOP来限制某些用户对敏感信息的访问权限，以确保新闻信息的安全性。
登录权限验证也可以使用aop。

config目录：配置相关的java类。fastJsonConfig对json数据的null和中文乱码进行了处理。
JsonResult是用来配置统一的json返回数据的包装类
MicroServiceUrl提供了一个从配置文件读取数据的例子。

templates目录：存放thymeleaf模板页面。项目不设置前后端分离，后端直接调用页面。

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
