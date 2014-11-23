/**
 * Druid 是一个JDBC组件，它包括三部分： 
       1 DruidDriver 代理Driver，能够提供基于Filter－Chain模式的插件体系。 
       2 DruidDataSource 高效可管理的数据库连接池。 
       3 SQLParser
  在应用监控中，JDBC层间的监控十分重要，Druid就是为监控而生的。
       在实际测试中，DruidDataSource的性能要比C3P0/DBCP/Jboss DataSource/Proxool/BoneCP都要好
         但性能不是Druid的最重要优势。
    内置提供了一些常用的Filter，StatFilter用于提供监控数据，
     	Log系列Filter用于提供输出Connection、Statement、ResultSet相关的日志，
     		WallFilter用于防御SQL注入攻击。
     	业务系统自行开发各种功能的Filter，如：数据库密码加密的CirceFilter、数据库修改操作变更日志记录的TracerFilter、用于关联监控统计的DragoonFilter、用于错误编码转换的EncodingConvertFilter等等。
     	
 * @author  shaoling@shaolingweb.cn
 */
package cn.shaolingweb.framework.data.dbpool;