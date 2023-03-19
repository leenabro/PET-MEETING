<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
    
    	section{
    	width:70%;
    	margin:auto;
    	}
        section>div#board-write-container{
            width: ; margin:0 auto; text-align:center; 
        }
        section>div#board-write-container h2{
            margin:10px 0;
        }
        table#tbl-board{
        	font-family: 'Jua', sans-serif;
            width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both;
        }
        table#tbl-board th{
            width: 125px; border:1px solid; padding: 5px 0; text-align:center; background-color:whitesmoke;
        } 
        table#tbl-board td{
            border:1px solid; padding: 5px 0 5px 10px; text-align:left;
        }
        div#comment-container button#btn-insert{
            width:60px;height:50px; color:black; background-color:pink;position:relative;top:-20px;
        }
        
        table#tbl-comment{
            width:580px; margin:0 auto; border-collapse:collapse; clear:both;
        } 
        table#tbl-comment tr td{
            border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;
        }
        table#tbl-comment tr td:first-of-type{
            padding: 5px 5px 5px 50px;
        }
        table#tbl-comment tr td:last-of-type{
            text-align:right; width: 100px;
        }
        table#tbl-comment button.btn-delete{
            display:none;
        }
        table#tbl-comment tr:hover{
            background:lightgray;
        }
        table#tbl-comment tr:hover button.btn-delete{
            display:inline;
        }
        table#tbl-comment sub.comment-writer {
            color:navy; font-size:14px
        }
        table#tbl-comment sub.comment-date {
            color:tomato; font-size:10px
        }
    </style>
</head>
<body>
    <section id="content">   
        <div id="board-write-container">
            <h2 style="text-align: center; font-size: 2em;">
            	<c:if test="${ param.boardCode eq 'B1' }"><c:out value="펫 다이어리"/></c:if>
            	<c:if test="${ param.boardCode eq 'B2' }"><c:out value="펫미팅 후기"/></c:if>
          	    <c:if test="${ param.boardCode eq 'B3' }"><c:out value="정보 공유"/></c:if>
            	<c:if test="${ param.boardCode eq 'B4' }"><c:out value="문의 / 건의"/></c:if>
         	    <c:if test="${ param.boardCode eq 'B5' }"><c:out value="공지사항"/></c:if>
          	 	<c:if test="${ param.boardCode eq 'B6' }"><c:out value="신고하기"/></c:if>
            </h2>
            <table id="tbl-board" >
                <tr>
                    <th>카테고리</th>
                    <td>${ board.boardName }</td>
                    <th>글번호</th>
                    <td>${ board.boardNumber }</td>
                </tr>

                <tr>
                    <th>제 목</th>
                    <td colspan="3">${ board.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td colspan="3">${ board.createDate }</td>
                </tr>
                
                <tr>
                    <th>작성자</th>
                    <td colspan="3">${ board.memberNickName }</td>
                </tr>
                <tr>
                    <th>대상자</th>
                    <td colspan="3">${ board.subjectId }</td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td colspan="3">${ board.views }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <c:if test="${ empty board.originalFileName }">
	                    <td colspan="3">
	                        <span> - </span>
	                    </td>
                    </c:if>
                    <c:if test="${ not empty board.originalFileName }">
	                    <td colspan="3">
	                        <a href="javascript:" id="fileDown">
	                        	<span> ${ board.originalFileName } </span>
	                        </a>
	                    </td>
                    </c:if>
                </tr>
                <tr>
                    <th>내 용</th>
                    <td colspan="3">${ board.boardContent }</td>
                </tr>
                <tr>
                    <th colspan="4">
                    <c:if test="${ not empty loginMember && loginMember.id == board.memberId }">
                        <button type="button" class="btn btn-outline-secondary" onclick="location.href='${ path }/board/update?boardNo=${ board.boardNo }'">수정</button>
                        <button type="button" class="btn btn-outline-secondary" id="btnDelete">삭제</button>
                        </c:if>
                        <button type="button" class="btn btn-outline-secondary" onclick="location.href='${ path }/board/list?boardCode=${ board.boardCode }&page=1'">목록</button>
                 
                    </th>
                </tr>
            </table>
            <hr>
            
            <br>
            <div id="comment-container">
                <div class="comment-editor">
                    <form action="${ path }/board/reply" method="POST">
	                    
                        <input type="hidden" name="boardNo" value="${ board.boardNo }">
                        <textarea name="content" id="content" cols="55" rows="2"></textarea>
                        <button type="submit" id="btn-insert" class="btn btn-outline-secondary">등록</button>	    			
                    </form>
                </div>
            </div>
            <table id="tbl-comment">
            	<c:forEach var="reply" items="${ board.replies }">
	                <tr class="level1">
	                    <td>
	                        <sub class="comment-writer">${ reply.memberNickName }</sub>
	                        <sub class="comment-date">${ reply.createDate }</sub>
	                        
	                        <br>
	                        <span>${ reply.content }</span>
	                    </td>
	                    <td>
	                    	<c:if test="${ not empty loginMember && loginMember.id == reply.memberId }">
	                        	
	                        	<button type="button" id="btnRDelete" class="btn btn-outline-secondary" rNo="${ reply.replyNo }">삭제</button>
	                    	</c:if>
	    
	                    </td>
	                </tr>
            	</c:forEach>
            </table>
        </div>
    </section>
    <script>
    $(document).ready(() => {
    	$('#btnRDelete').on('click', (event) => {
    		let rNo = $(event.target).attr('rNo');
    		
    		if(confirm('댓글을 삭제하시겠습니까?')){
    			location.href='${ path }/board/replydelete?boardNo=${param.boardNo}&replyNo='+rNo;
    		}		
    	});
    	
    	
    	$('#replyContent').on('focus', () => {
	         if(${ empty loginMember}) {
	            alert('로그인 후 이용해 주세요.')   ;
	            $('#so').focus();
	         }
	      });
    	
    	$('#btnDelete').on('click', (event) => {
			if(confirm('정말로 게시글을 삭제 하시겠습니까?')) {
				location.replace('${ path }/board/delete?boardNo=${ board.boardNo }&boardCode=${board.boardCode}');
			}
    	});
    	
    	$('#fileDown').on('click', () => {
            let oname = encodeURIComponent('${ board.originalFileName }');
            let rname = encodeURIComponent('${ board.renamedFileName }');
            
            location.assign('${ path }/board/fileDown?oname=' + oname + '&rname=' + rname);
    		
    	});
		
	});
    </script>
</body>

<jsp:include page="/views/common/footer.jsp" />