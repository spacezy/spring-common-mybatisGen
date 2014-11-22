<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.impl;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Repository;
import  cn.shaolingweb.framework.dao.MyBatisBaseDaoImpl;
import ${basepackage}.model.${className};
import ${basepackage}.dao.${className}Dao;
<#include "/java_imports.include">

@Repository
public class ${className}DaoImpl extends MyBatisBaseDaoImpl<${className},${table.idColumn.javaType}> implements  ${className}Dao{
	
}
