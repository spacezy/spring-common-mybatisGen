<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import cn.shaolingweb.framework.model.BaseModel;
<#if table.needDateTimeFormat>
import org.springframework.format.annotation.DateTimeFormat;
</#if>
<#include "/java_imports.include">

/**
 * 自动生成，请勿修改
 * @table ${table.sqlName}   ${table.remarks}
 * @author  shaoling
 */
public class ${className} extends BaseModel{
	private static final long serialVersionUID = 1L;
	<#list table.columns as column>
	/**描述:${column.columnAlias!}   
        * 字段: ${column.sqlName}  ${column.sqlTypeName}(${column.size})  
       */	
	<#if column.needDateTimeFormat>
	@DateTimeFormat(pattern="${column.format}")
	</#if>
	private ${column.javaType} ${column.columnNameLower};
	<#if column.isDateTimeColumn>
	//【非数据库字段，查询时使用】
	<#if column.needDateTimeFormat>
	@DateTimeFormat(pattern="${column.format}")
	</#if>
	private ${column.javaType} ${column.columnNameLower}Begin;
	//【非数据库字段，查询时使用】
	<#if column.needDateTimeFormat>
	@DateTimeFormat(pattern="${column.format}")
	</#if>
	private ${column.javaType} ${column.columnNameLower}End;
	</#if> 
	</#list>
	<@generateConstructor className/>
	<@generateJavaColumns/>
	<#-- 
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>
	-->
}

<#macro generateJavaColumns>
	<#list table.columns as column>
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	 <#if column.isDateTimeColumn>
    public void set${column.columnName}Begin(${column.javaType} ${column.columnNameLower}Begin) {
		this.${column.columnNameLower}Begin = ${column.columnNameLower}Begin;
	}
	
	public ${column.javaType} get${column.columnName}Begin() {
		return this.${column.columnNameLower}Begin;
	}
	
	public void set${column.columnName}End(${column.javaType} ${column.columnNameLower}End) {
		this.${column.columnNameLower}End = ${column.columnNameLower}End;
	}
	
	public ${column.javaType} get${column.columnName}End() {
		return this.${column.columnNameLower}End;
	}	
	 </#if> 
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
