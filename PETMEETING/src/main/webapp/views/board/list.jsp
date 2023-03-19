<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판글목록페이지</title>
   <style>
   		a {
   			text-decoration: none;
   			color: green;
   		}
   
	   section {
	   margin:auto;
	   width:70%;
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
        table#tbl-board th{
        	background-color:orange;
        }
        button#btn-add{
			float:right;
        }
        div#pageBar{
            margin-top:10px; text-align:center;
        }
    </style>
</head>
<body>
    <%
    	String boardCode = request.getParameter("boardCode");
    	System.out.println("boardCode=" + boardCode);
    %>
    <section id="content">
		<div id="board-list-container">
		<h2 id="boardH2"style="text-align: center; font-size: 2em;">
			<c:if test="${ param.boardCode eq 'B1' }"><c:out value="펫 다이어리"/></c:if>
            <c:if test="${ param.boardCode eq 'B2' }"><c:out value="펫미팅 후기"/></c:if>
            <c:if test="${ param.boardCode eq 'B3' }"><c:out value="정보 공유"/></c:if>
            <c:if test="${ param.boardCode eq 'B4' }"><c:out value="문의 / 건의"/></c:if>
            <c:if test="${ param.boardCode eq 'B5' }"><c:out value="공지사항"/></c:if>
            <c:if test="${ param.boardCode eq 'B6' }"><c:out value="신고하기"/></c:if>
        </h2>
		<c:if test="${ not empty loginMember }">
			<button id="btn-add" onclick="location.href='${ path }/board/write'" class="btn btn-outline-secondary">글쓰기</button><br><br>
		</c:if>
              
		<select name="category" id="category" onchange="location.href=this.value" style="margin-bottom: 5px;">
			<option value="카테고리" selected disabled>카테고리</option>
			<option value="${ path }/board/list?boardCode=B1&page=1">펫 다이어리</option>
			<option value="${ path }/board/list?boardCode=B2&page=1">펫미팅 후기</option>
			<option value="${ path }/board/list?boardCode=B3&page=1">정보 공유</option>
			<option value="${ path }/board/list?boardCode=B4&page=1">문의 / 건의</option>
			<option value="${ path }/board/list?boardCode=B5&page=1">공지사항</option>
			<option value="${ path }/board/list?boardCode=B6&page=1">신고하기</option>
		</select>
		<select name="writingnumber" id="writingnumber" style="float: right; margin-bottom: 5px;">
			<option value="5" id="five">5개씩 보기</option>
			<option value="10" id="ten" selected>10개씩 보기</option>
		</select>
	
	

		<table id="tbl-board" class="table table-hover" >
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>첨부파일</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${ empty list }">
				<tr>
				<td colspan="5">
				조회된 게시글이 없습니다.
				</td>
			</tr>
			</c:if>
			<c:if test="${ not empty list }">
			<c:forEach var="board" items="${ list }">
			<tr>
				<td>${ board.rowNum }</td>
				<td>
					<a href="${ path }/board/view?boardCode=${board.boardCode}&boardNo=${board.boardNo}">
               		   ${ board.boardTitle }
            	   </a>
				</td>
				<td>${ board.memberNickName }</td>
				<td>
					<c:if test="${ empty board.originalFileName }">
								<span> - </span>
							</c:if>
							<c:if test="${ not empty board.originalFileName }">
								<img width="20px" src="${ path }/resources/images/file.png">
							</c:if>
				
				</td>
				<td>${ board.createDate }</td>
				<td>${ board.views }</td>
			</tr>
			</c:forEach>
			</c:if>
			
		</table>

		<div id="pageBar">
			<button onclick="location.href='${ path }/board/list?boardCode=${ param.boardCode }&page=1'">&lt;&lt;</button>
			<button onclick="location.href='${ path }/board/list?boardCode=${ param.boardCode }&page=${ pageInfo.prevPage }'">&lt;</button>
			                  
			<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">
				<c:choose>
					<c:when test="${ status.current == pageInfo.currentPage }">
						<button disabled>${ status.current }</button>
					</c:when>
					<c:otherwise>
						<button onclick="location.href='${ path }/board/list?boardCode=${ param.boardCode }&page=${ status.current }'">${ status.current }</button>
					</c:otherwise>
				</c:choose>
								                  
			</c:forEach>


                  <button onclick="location.href='${ path }/board/list?boardCode=${ param.boardCode }&page=${ pageInfo.nextPage }'">&gt;</button>
                  <button onclick="location.href='${ path }/board/list?boardCode=${ param.boardCode }&page=${ pageInfo.maxPage }'">&gt;&gt;</button>
              </div>
          </div>
      </section>
      <script>
      $(document).ready(() => {
    	  $('#category').val('${ param.boardCode }').attr("selected", true);
    	  
    	  $(function(){
    		  $("#category").val("카테고리").attr("selected","true");
    	  });
    	  
      });
      </script>
<jsp:include page="/views/common/footer.jsp" />