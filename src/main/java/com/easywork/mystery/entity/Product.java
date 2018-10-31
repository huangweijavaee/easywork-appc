package com.easywork.mystery.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * product 实体类
 * Sat Oct 13 18:16:23 CST 2018
 * @Mystery
 */ 
@Entity
@Table(name="product")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer proid;
	private String proname;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getProid(){
		return proid;
	}

	public void setProid(Integer proid){
		this.proid=proid;
	}

	public String getProname(){
		return proname;
	}

	public void setProname(String proname){
		this.proname=proname;
	}

}

