package com.petmeeting.mvc.board.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
   private int boardNo;
   
   private int rowNum;
   
   private String boardCode;
   
   private String boardName;
   
   private int boardNumber;
   
   private String boardTitle;
   
   private int memberCode;
   
   private String memberId;
   
   private String memberNickName;
   
   private String boardContent;
   
   private String subjectId;
   
   private Date createDate;
   
   private Date modifyDate;
   
   private int views;
   
   private String boardStatus;
   
   private List<Reply> replies;
   
   private String originalFileName;
   
   private String renamedFileName;


   
}