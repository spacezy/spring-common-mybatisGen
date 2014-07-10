
package test;

import org.junit.*;

import cn.org.rapid_framework.generator.*;
 
public class T1 {

	@Test
	public void t1() {
		String outRoot=GeneratorProperties.getProperty("outRoot");
		System.out.println(outRoot);
	}
}
