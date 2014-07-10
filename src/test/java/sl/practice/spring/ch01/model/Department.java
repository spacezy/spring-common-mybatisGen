/*
* Department.java 2012-09-03
* Copyright  © 2001-2012 必联网
* 京ICP备09004729号京公网安备110108008196号
*/
package sl.practice.spring.ch01.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @table department   
 *
 */
public class Department {
 
 
	 /**
     * 描述:部门ID     
     * 字段: id  INT(10)  
     */	
	private java.lang.Integer id;
 
	 /**
     * 描述:部门名称     
     * 字段: name  VARCHAR(12)  
     */	
	private java.lang.String name;
 
	 /**
     * 描述:创建时间     
     * 字段: createTime  TIMESTAMP(19)  
     */	
	private java.util.Date createTime;
	//【非数据库字段，查询时使用】
	private java.util.Date createTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date createTimeEnd;
 
	 /**
     * 描述:更新时间     
     * 字段: updateTime  TIMESTAMP(19)  
     */	
	private java.util.Date updateTime;
	//【非数据库字段，查询时使用】
	private java.util.Date updateTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date updateTimeEnd;
 

	public Department(){
	}

	public Department(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	
    
    
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
    
    
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
    public void setCreateTimeBegin(java.util.Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeEnd(java.util.Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}	
    
    
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
    public void setUpdateTimeBegin(java.util.Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}
	
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeEnd(java.util.Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}
	
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}	
    
    
	
	private Set userinfos = new HashSet(0);
	public void setUserinfos(Set<Userinfo> userinfo){
		this.userinfos = userinfo;
	}
	
	public Set<Userinfo> getUserinfos() {
		return userinfos;
	}
 
}

