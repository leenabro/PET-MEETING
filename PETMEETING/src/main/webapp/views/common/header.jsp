<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PET MEETING</title>

<%-- CSS only --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<%-- 한글폰트 --%>
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<%-- 영어폰트 --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Playfair+Display:ital,wght@1,500&display=swap" rel="stylesheet">
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="${ path }/resources/css/style.css">

</head>

<body>
<%-- 상단 하얀 부분 --%>
<header>
	<nav  id="nav1" class="navbar bg-light">
           <div class="container-fluid">
            	<div class="login-container">
					  <div>
					  	<a href="${ path }">
                  	  		<img src="https://cdn.pixabay.com/photo/2012/05/07/13/46/foot-48503__340.png" id="logoimg">
						</a>
                	  </div>
					<c:if test="${ empty loginMember && empty admin }">
                  	  	<button type="button" id="si" class="btn btn-outline-secondary">회원가입</button>
                   	 	<button type="button" id="so" class="btn btn-outline-secondary">로그인</button>             
					</c:if>
            	</div>
           		<c:if test="${ not empty loginMember }">
           		<div style="position: absolute; right: 210px">
           			${ loginMember.name }님 안녕하세요.
           		</div>
            	<div class="login-container">
            		
					<table>
						<tr>
							<td>
								<button id="myPage" class="btn btn-outline-secondary" onclick="location.href='${ path }/member/myPage'">내 정보</button>
							</td>
							<td>
								<button id="logout" class="btn btn-outline-secondary" onclick="location.replace('${ path }/logout')">로그아웃</button>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								
							</td>
						</tr>
					</table>
				</div>
				</c:if>
				<c:if test="${ empty loginMember && not empty admin }">
					<div style="position: absolute; right: 210px">
						관리자님 어서오세요.
					</div>
					<div class="login-container">
						<table>
							<tr>
								<td colspan="2">
					
								</td>
							</tr>
							<tr>
								<td>
									<button id="aLogout" class="btn btn-outline-secondary" onclick="location.replace('${ path }/logout')">로그아웃</button>
									<button id="manageMem" class="btn btn-outline-secondary" onclick="location.replace('${ path }/admin/manage')">회원관리</button>
								</td>
							</tr>
						</table>
					</div>
				</c:if>
            	</div>
            	
    </nav>
    
    <div id="title">
            PET MEETING
    </div>   
    
    <div id="addimg"></div> 
	<div class="btn-group-vertical" role="group" aria-label="Vertical button group" id="fixed" >
    	<button type="button" id="warkboard_btn" class="btn btn-primary" style="height: 150px; margin-bottom: 10px; 
         			border-bottom-left-radius: 0.375rem; border-bottom-right-radius: 0.375rem;">산책가기</button>
        <div class="btn-group" role="group">
          		<button type="button" id="board_btn" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" 
	            		style="height: 150px; border-top-left-radius: 0.375rem; border-top-right-radius: 0.375rem;">게시판</button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="${ path }/board/list?boardCode=B1&page=1">펫다이어리</a></li>
                <li><a class="dropdown-item" href="${ path }/board/list?boardCode=B2&page=1">펫미팅 후기</a></li>
                <li><a class="dropdown-item" href="${ path }/board/list?boardCode=B3&page=1">정보 공유</a></li>
                <li><a class="dropdown-item" href="${ path }/board/list?boardCode=B4&page=1">문의하기</a></li>
                <li><a class="dropdown-item" href="${ path }/board/list?boardCode=B5&page=1">공지사항</a></li>
                <li><a class="dropdown-item" href="${ path }/board/list?boardCode=B6&page=1">신고하기</a></li>
               </ul>
           </div>
    </div>
		<div style="border: 1px solid;">
		    <h1 id="indexMove" style="text-align: center; font-family: 'Jua', 'sans-sefif'">PET MEETING</h1>
		</div>
</header>					

		
<!-- <script src="main.js"></script> -->
<%-- JavaScript Bundle with Popper --%>
<script>
	$(document).ready(() => {
		$('#btnMyPage').on('click', () => {
			location.replace("${ path }/views/member/myPage.jsp");	
		});
		
		$('#si').on('click', () => {
			location.replace("${ path }/views/member/signup.jsp");
		});
		
		$('#so').on('click', () => {
			location.replace("${ path }/views/member/login.jsp");
		});
		
		$('#warkboard_btn').on('click', () =>{
			location.replace("${ path }/walkboard/main")
		});
	});

</script>

    
