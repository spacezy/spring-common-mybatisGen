/*
* Copyright  © shaoling
*/package cn.shaolingweb.demo.model;

import java.io.Serializable;


/**
 * 自动生成，请勿修改
 */
public class User implements Serializable{
	private java.lang.Integer id;
	private java.lang.String name;
	public User() {
	}
	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public java.lang.Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * @return the username
	 */
}
