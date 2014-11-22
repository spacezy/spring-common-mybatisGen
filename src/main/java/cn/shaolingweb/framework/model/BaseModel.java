package cn.shaolingweb.framework.model;

import java.io.Serializable;

/**
 * @description: Model 基础类
 */
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 排序字段
	 */
	private String sortColumns;

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	 
}
