/**
 *  hsqldb.jar快速使用
 *  	http://blog.csdn.net/feijianxia/article/details/1753777
 *  启动数据库服务器和客户端:
 *  	服务器:java -classpath hsqldb.jar org.hsqldb.Server
	客户端:java -classpath hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
	
	设定连接数据库的相关参数
                driverClassName=org.hsqldb.jdbcDriver
                url=jdbc:hsqldb:hsql://localhost
         	hibernate.dialect=org.hibernate.dialect.HSQLDialect
        	 Class.forName("org.hsqldb.jdbcDriver");
           	Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", "sa", "");
 * @author  shaoling@shaolingweb.cn
 */
package cn.shaolingweb.demo.hsqldb;