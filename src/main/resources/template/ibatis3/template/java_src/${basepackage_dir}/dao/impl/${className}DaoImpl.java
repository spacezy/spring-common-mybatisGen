<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.impl;

import cn.bidlink.framework.core.annotation.Dao;
<#include "/java_imports.include">

/**
 * 
 * @description:	${table.remarks}
 * @version   Ver 1.0
 * @author   <a href="mailto:zuiwoxing@gmail.com">SYSTEM</a>
 * @Date	 2012	2012-9-4		下午6:45:14
 *
 */
@Dao
public class ${className}DaoImpl extends MyBatisBaseDaoImpl<${className},${table.idColumn.javaType}> implements  ${className}Dao{
	
	@Override
	public void deleteByIds(String ids, Class<${className}> cls) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		this.getCurSqlSessionTemplate().delete(cls.getName()+".delete_batch_string", map);
	}

}
