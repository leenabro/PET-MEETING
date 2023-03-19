package com.petmeeting.mvc.walkboard.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkBoard {

	private int rowNum;
	
	private int wbNo;
	
	private int memberCode;
	
	private String memId;
	
	private String memNickname;
	
	private String wbTitle;
	
	private String wbContent;
	
	private int wbViews;
	
	private String wbStatus;
	
	private List<WalkBoardReply> replies;
	
	private String dogId;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String originalFileName;
	
	private String renamedFileName;
	
}