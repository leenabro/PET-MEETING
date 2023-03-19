<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 보기</title>
    <style>
        section>div#board-write-container{
            width:600px; margin:0 auto; text-align:center;
        }
        section>div#board-write-container h2{
            margin:10px 0;
        }
        table#tbl-board{
            width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both;
        }
        table#tbl-board th{
            width: 125px; border:1px solid; padding: 5px 0; text-align:center; background-color: whitesmoke;
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
            <h2 style="text-align: center; font-size: 2em;">게시판</h2>
            <table id="tbl-board" >
                <tr>
                    <th>글번호</th>
                    <td>${ walkboard.wbNo }</td>
                </tr>
                <tr>
                    <th>제 목</th>
                    <td>${ walkboard.wbTitle }</td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td>${ walkboard.createDate }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ walkboard.memNickname }</td>
                </tr>
                <tr>
                	<th>첨부파일</th>
					<td>
						<c:if test="${ empty walkboard.originalFileName }">
							<span> - </span>
						</c:if>
						<c:if test="${ not empty walkboard.originalFileName }">
							<a href="javascript:" id="fileDown">
								<span> ${ walkboard.originalFileName } </span>
							</a>
						</c:if>
					</td>
                </tr>
                <tr>
                    <th>강아지 정보</th>
                    <td>
	                    이름 : ${ dog.name }<br>
						종류 : ${ dog.kind }<br>
						크기 : ${ dog.size }<br>
						성별 : ${ dog.gender }<br>
						중성화 여부 : ${ dog.neutered }<br>
						백신 접종 여부 : ${ dog.vaccine }<br>
                    </td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td>${ walkboard.wbViews }</td>
                </tr>
                <tr>
                    <th>내 용</th>
                    <td>${ walkboard.wbContent }</td>
                </tr>
                <tr>
                    <th colspan="2">
                    <c:if test="${ not empty loginMember && loginMember.MCode == walkboard.memberCode }">
                        <button type="button" class="btn btn-outline-secondary" onclick="location.href='${ path }/walkboard/walkupdate?wbNo=${ walkboard.wbNo }'">수정</button>
                        <button type="button" class="btn btn-outline-secondary" id="btnDelete">삭제</button>
                        </c:if>
                        <button type="button" class="btn btn-outline-secondary" onclick="location.replace('${ path }/walkboard/walklist')">목록</button>
                    </th>
                </tr>
            </table>
            <br>
            <div id="comment-container">
                <div class="comment-editor">
                    <form action="${ path }/walkBoard/reply" method="POST">
                        <input type="hidden" name="walkboardwbNo" value="${ walkboard.wbNo }">
                        <input type="hidden" name="writer" value="${ walkboard.memNickname }">
                        <textarea name="" id="commentContent" cols="55" rows="3"></textarea>
                        <button class="btn btn-outline-secondary" type="submit" id="btn-insert">등록</button>	    			
                    </form>
                </div> 
            </div>
            <table id="tbl-comment">
            	<c:forEach var="reply" items="${ walkboard.replies }">
                	<tr class="level1">
                    	<td>
                        	<sub class="comment-writer">${ reply.wcNickname }</sub>
                        	<sub class="comment-date">${ reply.createDate }</sub>
                        	<br><br>
                        	<span> ${ reply.wbComment }</span>
                    	</td>
                    	<td>
                    		<c:if test="${ not empty loginMember && loginMember.MCode == walkboard.memberCode }">
	                        	<button class="btn btn-outline-secondary" type="submit" id="commentbtn">삭제</button>
                    		</c:if>
                    	</td>
                	</tr>
                	</c:forEach>
            </table>
        </div>
    </section>
<script type="text/javascript">
 	$(document).ready(() => {
 		$('#btnDelete').on('click', () => {
 			if(confirm('정말로 게시글을 삭제할까요?' )) {
 				location.replace('${ path }/walkboard/walkdelete?wbNo=${ walkboard.wbNo }');
 			}
 		});
 		
 		$('#fileDown').on('click', () => {
			let oname = encodeURIComponent('${ walk_board.originalFileName }');
			let rname = encodeURIComponent('${ walk_board.renamedFileName }');
			
			location.assign('${ path }/walk_board/fileDown?oname=${ walk_board.originalFileName }&rname=${ walk_board.renamedFileName }');
		});
 		
 		$('#commentContent').on('focus', () => {
			if (${ empty loginMember }) {
				alert('로그인 후 이용 해주세요.');
				
				$('#so').focus();
			}
		});
 		
 	}); 
 
 </script>   
    
</body>
<jsp:include page="/views/common/footer.jsp" />