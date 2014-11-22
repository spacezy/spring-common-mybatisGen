package genMain;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cn.org.rapid_framework.generator.GeneratorFacade;
/**
  基于FreeMaker的生成技术
 */
public class GeneratorMain {
//	private static  Logger logger=Logger.getLogger(GeneratorMain.class);
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {
//		DOMConfigurator.configure("log4j.xml");
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印数据库中的表名称
// 		g.generateByAllTable(getTplPath());	//不建议使用：自动搜索数据库中的所有表并生成文件,template为模板的根目录
 		g.generateByTable("jc_user", getTplPath());//名称如果是*,则所有表
//		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot")+"/ibatis3/template/java_src");
//		Runtime.getRuntime().exec("cmd.exe /c start "+".");
 		copyToDemo();
 		System.out.println("................over.................");
	}
	
	/**基于spring-core，在当前类路径下查找资源
	 * @param isDelete 是否删除原有目录
	 * @return
	 */
	private static String getTplPath(){
			Resource res=new ClassPathResource("template");
			File result=null;
			try {
				 result=res.getFile();
				return result.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	private static void copyToDemo() {
		File srcDir=new File("output/ibatis3/template/java_src");
		File destDir=new File("src/test/java");
		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
