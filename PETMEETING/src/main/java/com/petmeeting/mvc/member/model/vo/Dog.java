package com.petmeeting.mvc.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
	private String id;
	
	private String imgReName;
	
	private String imgOriginName;
	
	private int num;
	
	private String name;
	
	private String kind;
	
	private String size;
	
	private String gender;
	
	private String neutered;
	
	private String vaccine;
	
	private int mCode;
}
