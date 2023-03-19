<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판글목록페이지</title>
</head>
	<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
	<style>
		a {
			text-decoration: none;
			color: green;
		}	
	
		section {
			margin:auto;
		   	width:70%;
		   	font-family: 'Jua', sans-serif;
	   	}
        section#board-list-container{
            width:600px; margin:0 auto; text-align:center;
        }
        section#board-list-container h2{
            margin:10px 0;
        }
        table#tbl-board{
            width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both;
        }
        table#tbl-board th, table#tbl-board td{
            border:1px solid; padding: 5px 0; text-align:center;
        } 
        #tbl-board th {
        	background-color: orange;
        }
        
        button#btn-add{
			float:right;
        }
        div#pageBar{
            margin-top:10px; text-align:center;
        }
    </style>
<body>
    <section id="content">
		<div id="board-list-container">
		<h2 style="text-align: center; font-size: 2em;">산책 친구 찾기 게시판</h2>
		
		<c:if test="${ not empty loginMember }">
			<button id="btn-add" onclick="location.href='${ path }/walkBoard/walkwrite'">글쓰기</button><br><br>
		</c:if>
		<table id="tbl-board" class="table table-hover" style="border: 1px solid #dddddd">
			<thead>
				<tr>
					<th style="bakcground-color: #eeeeee; ">글번호</th>
					<th style="bakcground-color: #eeeeee; ">제목</th>
					<th style="bakcground-color: #eeeeee; ">작성자</th>
					<th style="bakcground-color: #eeeeee; ">첨부파일</th>
					<th style="bakcground-color: #eeeeee; ">작성일</th>
					<th style="bakcground-color: #eeeeee; ">조회수</th>
				</tr>
			</thead>
			<c:if test="${ empty list }">
				<tr>
					<td colspan="5">
						조회된 게시글이 없습니다.
				</td>
			</tr>
			</c:if>
			<c:if test="${ not empty list }">
				<c:forEach var="walkboard" items="${ list }">
					<tr>
						<td>${ walkboard.rowNum }</td>
						<td>
							<a href="${ path }/walkboard/walkview?wbNo=${walkboard.wbNo}">
								${ walkboard.wbTitle }
							</a>
						</td>
						<td>${ walkboard.memNickname }</td>
						<td>
							<c:if test="${ empty walkboard.originalFileName }">
								<span> - </span>
							</c:if>
							<c:if test="${ not empty walkboard.originalFileName }">
								<img width="20px" src="${ path }/resources/images/file.png">
							</c:if>
						</td>
						<td>${ walkboard.createDate }</td>
						<td>${ walkboard.wbViews }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div id="pageBar">
			<button onclick="location.href='${ path }/walkboard/walklist?page=1'">&lt;&lt;</button>
			<button onclick="location.href='${ path }/walkboard/walklist?page=${ pageInfo.prevPage }'">&lt;</button>
			                  
			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">
				<c:choose>
					<c:when test="${ status.current == pageInfo.currentPage }">
						<button disabled>${ status.current }</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href='${ path }/walkboard/walklist?page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>

                  <button onclick="location.href='${ path }/walkboard/walklist?page=${ pageInfo.nextPage }'">&gt;</button>
                  <button onclick="location.href='${ path }/walkboard/walklist?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
              </div>
          </div>
      </section>
      
<jsp:include page="/views/common/footer.jsp" />