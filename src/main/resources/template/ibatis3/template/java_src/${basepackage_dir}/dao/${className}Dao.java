<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   

package ${basepackage};
import cn.bidlink.framework.dao.ibatis.MyBatisBaseDao;
<#include "/java_imports.include">

/**
 * 自动生成
 * @author   shaoling@ebnew.com
 */
public interface ${className}Dao extends MyBatisBaseDao<${className}, ${table.idColumn.javaType}> {
	
	/**
	 * 根据ID集合批量删除
	 * @param ids	id字符串  如: 1,2,3
	 * @param cls	实体类型
	 */
	public void deleteByIds(String ids, Class<${className}> cls);
}
