package com.petmeeting.mvc.walkboard.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkBoardReply {
	
	private int wcNo;
	
	private int wbNo;
	
	private int mCode;
	
	private String wcNickname;
	
	private String wbComment;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String wcStatus;

}