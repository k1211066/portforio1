package com.work.pac.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "work_task")
public class Work implements Serializable{

	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 名前
	 */
	@Column(name = "name")
	private String name;
	/**
	 * ステータス
	 */
	@Column(name = "state")
	private String state;
	/**
	 * 期日
	 */
	@Column(name = "deadline")
	private Date deadline;
	/**
	 * 優先度(1～5)
	 */
	@Column(name = "priority")
	private String priority;
	/**
	 * 詳細
	 */
	@Column(name = "detail")
	private String detail;
	/**
	 * 更新日時
	 */
	@Column(name = "update_date")
	private Date updateDate;
	/**
	 * 登録日時
	 */
	@Column(name = "create_date")
	private Date createDate;

}
