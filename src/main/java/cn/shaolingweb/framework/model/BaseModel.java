package cn.shaolingweb.framework.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Model 基础类
 */
public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** 支持模糊查询的字段k-v，k-字段，v=1*/
	private Map<String, String> selectType=new HashMap<String, String>();
	/**  排序字段 */
	private String sortColumns;
	/** 主键ID串，用于基于单主键批量删除*/
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Map<String, String> getSelectType() {
		return selectType;
	}

	public void setSelectType(Map<String, String> selectType) {
		this.selectType = selectType;
	}
	/** 
	 * 对象需要模糊查询的字段列表
	 * @param fields
	 */
	public void setSelectType(String... fields) {
		for (String field : fields) {
			selectType.put(field, "1");
		}
	}

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}
}
