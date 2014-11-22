package cn.shaolingweb.framework.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @author  shaoling@shaolingweb.cn
 */
public class FrameworkDateUtil {
	/** 常见格式化样式 ：年月日时分秒 */
	public static String FORMAT="yyyy-MM-dd HH:mm:ss";
	/** 常见格式化样式 ：年月日时分秒.毫秒 */
	public static String FORMAT_SSS=FORMAT+".SSS";
	
	/**
	 * 格式化日期
	 * @param date 若为null，则使用当前时间
	 * @param format，若为null，则采用默认的{@link FrameworkDateUtil.FORMAT}
	 * @return
	 */
	public static String format(Date date,String format) {
		DateFormat sdf=new SimpleDateFormat(format==null?FORMAT:format);
		if (date==null) {
			return sdf.format(new Date());
		}
		return sdf.format(sdf.format(date));
	}
}
