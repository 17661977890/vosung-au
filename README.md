# au-parent

# 权限管理平台

* springBoot  springCloud  eureka注册中心 zipkin全链路追踪  feign声明式服务调用  hystrix容错机制 
* 统一出入参配置，统一异常处理，国际化配置，跨域配置，接入elk统一日志服务（可以看我之前写的ELK的docker搭建，并如何接入springBoot项目）
* 接口独立化--- au-api项目
* maven打包到私服（maven私服配置）
* 阿波罗的接入可作为配置中心
* 代码自动生成（mybatis 的代码生成器 生成mapper.xml dao entity）

* 业务部分： 组织、组织关系、部门、部门组、岗位、岗位汇报体系、员工、员工任岗、权限项、权限对象、资源（菜单、按钮）、用户、角色、字典(数据库与redis存)、用户组织角色关系

* 数据库表结构： vsc_au.sql  vsc_au.pdm
