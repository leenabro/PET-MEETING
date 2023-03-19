<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />

<style>
	#adminNav {
		list-style-type: none;
   		padding: 0;
   		margin: 0;
	}
	
	#adminNav li {
		height: 70px;
		text-align: center;
	}
	
	#adminNav a {
		 display: block;
  		 text-decoration: none;
  		 color: black;
  		 height: 100%;
   		 line-height: 70px;
	}
	
	#adminNav a:hover {
		background-color: blanchedalmond;
	}
	
	#allStatus td {
		text-align: right;
	}
	
	#recentBoard td > div{
		text-align: center;
		overflow: hidden;
	}
	
	#recentWalkBoard td > div{
		text-align: center;
		overflow: hidden;
	}
	
	#recentBoard td p{
		margin: auto;
	}
	
	#recentWalkBoard td p{
		margin: auto;
	}
	
	#recentBoard th{
		width: 100px;
	}
	
	.rb_title {
		text-align: center;
	}
		
	
</style>


   <section id="adminSection">
        <h2 style="text-align: center; margin: 30px; font-size: 3em;">관리자 페이지</h2>
        <article>
			<ul id="adminNav">
				<li style="height: 10px; width: 100%; background-color: blanchedalmond"></li>
				<li><a href="${ path }/admin/manage">페이지 현황 보기</a></li>
				<li><a href="${ path }/mebmer/">회원 관리</a></li>
				<li><a href="${ path }/walkboard/walklist">산책 추천 게시판</a></li>
				<li><a href="${ path }/board/list?boardCode=B5&page=1">공지 사항 게시판</a></li>
			</ul>
        </article>
        <article>
            <div>
                <table id="allStatus" class="adminTable">
                    <tr>
                        <th style="background-color: blanchedalmond;">현황</th>
                        <td style="background-color: blanchedalmond;"></td>
                    </tr>
                    <tr>
                        <th>전체 회원 수</th>
                        <td style="width: 90%;">${ countMember } 명</td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">게시글</th>
                        <td>${ countBoard } 개</td>
                    </tr>
                    <tr>
                    </tr>
                </table>
            </div>
            <div>
                <table id="recentWalkBoard" class="adminTable">
                    <tr>
                        <th style="background-color: blanchedalmond;">산책 게시판 <br> 최신글</th>
                        <td class="rb_title" style="background-color: blanchedalmond;">제목</td>
                        <td class="rb_title" style="background-color: blanchedalmond;">내용</td>
                        <td class="rb_title" style="background-color: blanchedalmond;">닉네임</td>
                    </tr>
                 	<c:forEach var="walkBoard" items="${ walkList }">
                    <tr>
                        <th>${ walkBoard.rowNum }</th>
                        <td>
                        	<div>${ walkBoard.wbTitle }</div>
                        </td>
                        <td>
                        	<div style="overflow: hidden;">${ walkBoard.wbContent }</div>
                        </td>
                        <td>
                        	<div>${ walkBoard.memNickname }</div>
                        </td>
                    </tr>
                   	</c:forEach>
                </table>
            </div>
        </article>
        <article>
            <div>
                <table id="recentBoard" class="adminTable">
                    <tr>
                        <th class="rb_title" style="background-color: blanchedalmond;">게시판 최신글</th>
                        <td class="rb_title" style="background-color: blanchedalmond;">제목</td>
                        <td class="rb_title" style="background-color: blanchedalmond;">내용</td>
                        <td class="rb_title" style="background-color: blanchedalmond;">닉네임</td>
                    </tr>
                   	<c:forEach var="board" items="${ list }">
                    <tr>
                        <th>${ board.rowNum }</th>
                        <td>
                        	<div>${ board.boardTitle }</div>
                        </td>
                        <td>
                        	<div style="overflow: hidden;">${ board.boardContent }</div>
                        </td>
                        <td>
                        	<div>${ board.memberNickName }</div>
                        </td>
                    </tr>
                   	</c:forEach>
                </table>
            </div>
            <a id="pageMove" href='#indexMove'></a>
        </article>
    </section>

	<script>
		$(document).ready(() => {
			$('#pageMove').get(0).click();
		});
	</script>

<jsp:include page="/views/common/footer.jsp" /> 
	