package com.petmeeting.mvc.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
   private int replyNo;
   
   private int boardNo;
   
   private int memberCode;
   
   private String memberId;
   
   private String memberNickName;
   
   private String content;
   
   private Date createDate;
   
   private Date modifyDate;
   
   private String replyStatus;
   
}