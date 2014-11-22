<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   

package ${basepackage}.dao;
import ${basepackage}.model.${className};
import cn.shaolingweb.framework.dao.MyBatisBaseDao;
<#include "/java_imports.include">

/**
 * 自动生成,请勿修改
 * @author   shaoling
 */
public interface ${className}Dao extends MyBatisBaseDao<${className}, ${table.idColumn.javaType}> {
	
}
