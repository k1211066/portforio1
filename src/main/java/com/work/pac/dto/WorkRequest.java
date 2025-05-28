package com.work.pac.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

	@Data
public class WorkRequest implements Serializable {
	/**
	 * 名前
	 */
	@NotEmpty(message = "名前を入力してください")
	@Size(max = 45, message = "名前は45文字以内で入力してください")
	private String name;
	 /**
	  * ステータス(NotEmptyは使わないと思うが念のため)
	  */
	@NotEmpty(message = "ステータスを入力してください")
	private String state="未完了";
	/**
	 * 期日
	 */
	@NotEmpty(message = "期日を入力してください")
	private Date deadline;
	
	/*
	 * 優先度 (NotEmptyは使わないと思うが念のため)
	 */
	@NotEmpty(message = "優先度を入力してください")
	private String priority;
	
	/**
	 * 詳細
	 */
	@Size(max = 255, message = "詳細は255文字以内で入力してください")
	private String detail;


}
