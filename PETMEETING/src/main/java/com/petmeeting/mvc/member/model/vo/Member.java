package com.petmeeting.mvc.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor                    
public class Member {
	private int mCode;
	
	private String id;
	
	private String password;
	
	private String name;
	
	private String nickname;
	
	private String address;
	
	private String phone;
	
	private String email;
	
	private String birth;
	
	private Date signUpDate;
	
	private String gender;
	
	private String status;
	
}