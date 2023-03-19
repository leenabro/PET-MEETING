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
	<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
    <style>
		#boardH1 {
		    font-family: 'Jua', sans-serif;
		    color: rgb(12, 66, 12);
		    text-align: center;
		    padding: 100px;
		}
		
		#boardname{
		    font-family: 'Jua', sans-serif;
		    color: rgb(12, 66, 12);
		    width: 100%;
		    padding:  10px;
		}
		
		.ck-editor__editable {
		    font-family: 'Jua', sans-serif;
		    color: rgb(12, 66, 12);
		    min-height: 500px;
		}
		
		#button{
		    padding:  10px;
		    font-family: 'Jua', sans-serif;
		    color: rgb(12, 66, 12);   
		}
		
		#boardTitle, #writer, #subject{
		    width: 97%;
		}
		
		#upfile1, #upfile2{
		margin-bottom: 5px;
		}
		
		div#board-write-container{
		    width:100%;
		    margin:0 auto;
		    text-align:center;
		}
		div#board-write-container h2{
		    margin:10px 0;
		}
		table#tbl-board{
		    width:70%;
		    margin:0 auto;
		    height: 50%;
		    border:1px solid black;
		    border-collapse:collapse;
		    margin-bottom: 100px;
		}
		table#tbl-board th{
		    border:1px solid;
		    padding:5px 0;
		    text-align:center;
		}
		table#tbl-board td{
		    border:1px solid;
		    padding:5px 0 5px 10px;
		    text-align:left;
		}
    </style>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    
	<!--한글폰트-->
	<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

	<!--영어폰트-->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Jua&family=Playfair+Display:ital,wght@1,500&display=swap" rel="stylesheet">
</head>
<body>
    <h1 id="boardH1">게시글 작성하기</h1>

    <form action="${ path }/walkBoard/walkwrite" method="POST" enctype="multipart/form-data">
        <table id='tbl-board'>
        	<tr>
                <th>제목</th>
                <td><input type="text" name="content_title" id="boardTitle"></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" name="writer" id="writer" value="${ loginMember.nickname }" readonly></td>
            </tr>
            <tr>
            	<th>첨부파일</th>
				<td><input type="file" name="upfile"></td>
            </tr>
            <tr>
                <th>강아지 정보</th>
                <td>
                	이름 : ${ dogName }<br>
					종류 : ${ dogKind }<br>
					크기 : ${ dogSize }<br>
					성별 : ${ dogGender }<br>
					중성화 여부 : ${ dogNeutered }<br>
					백신 접종 여부 : ${ dogVaccine }<br>
                </td>
            </tr>
             <tr>
               <th>주인 성별</th>
               <c:if test="${ loginMember.gender == 'M' }">
               	   <td>남자</td>
               </c:if>
               <c:if test="${ loginMember.gender == 'F' }">
               	   <td>여자</td>
               </c:if>
            </tr>
            <tr>
                <th>내용</th>
                <td> 
                    <div id="editorbox">
                        <textarea name="content" id="editor"  placeholder="내용을 입력하세요." ></textarea>
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2">
                    <div id="button">
                        <input type="reset" value="초기화" class="btn btn-outline-secondary">
                        <input type="submit" value="등록" class="btn btn-outline-secondary"> 
                    </div>
                </th>
            </tr>
        </table>
    </form>

<!--ckeditor-->
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>

<!--ckeditor한국어 연결 옵션-->
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/translations/ko.js"></script>
<script>ClassicEditor.create( document.querySelector( '#editor' ), { language: "ko" }); </script>

</body>
<jsp:include page="/views/common/footer.jsp" />