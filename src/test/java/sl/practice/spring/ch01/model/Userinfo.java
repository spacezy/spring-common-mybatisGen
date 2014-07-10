/*
* Userinfo.java 2012-09-03
* Copyright  © 2001-2012 必联网
* 京ICP备09004729号京公网安备110108008196号
*/
package  sl.practice.spring.ch01.model;


/**
 * 
 * @table userinfo   
 * @version  Ver 1.0
 * @Date	 2012	2012-9-5		上午9:37:37
 *
 */
public class Userinfo {
 
 
	 /**
     * 描述:主键，自动增长     
     * 字段: id  INT(10)  
     */	
	private java.lang.Integer id;
 
	 /**
     * 描述:用户名     
     * 字段: userName  VARCHAR(12)  
     */	
	private java.lang.String userName;
 
	 /**
     * 描述:爱好     
     * 字段: favorite  VARCHAR(12)  
     */	
	private java.lang.String favorite;
 
	 /**
     * 描述:创建时间     
     * 字段: createTime  DATETIME(19)  
     */	
	private java.util.Date createTime;
	//【非数据库字段，查询时使用】
	private java.util.Date createTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date createTimeEnd;
 
	 /**
     * 描述:修改时间     
     * 字段: updateTime  TIMESTAMP(19)  
     */	
	private java.util.Date updateTime;
	//【非数据库字段，查询时使用】
	private java.util.Date updateTimeBegin;
	//【非数据库字段，查询时使用】
	private java.util.Date updateTimeEnd;
 
	 /**
     * 描述:部门ID     
     * 字段: departId  INT(10)  
     */	
	private java.lang.Integer departId;
 

	public Userinfo(){
	}

	public Userinfo(
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
	
    
    
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
    
    
	public void setFavorite(java.lang.String favorite) {
		this.favorite = favorite;
	}
	
	public java.lang.String getFavorite() {
		return this.favorite;
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
    
    
	public void setDepartId(java.lang.Integer departId) {
		this.departId = departId;
	}
	
	public java.lang.Integer getDepartId() {
		return this.departId;
	}
	
    
    
	
	private Department department;
	
	public void setDepartment(Department department){
		this.department = department;
	}
	
	public Department getDepartment() {
		return department;
	}
 
}

