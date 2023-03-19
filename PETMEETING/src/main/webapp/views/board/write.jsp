<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>boardUpdate</title>
	<!--     <link rel="stylesheet" href="./boardUpdate.css"> -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<!--한글폰트-->
	<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
	
	<!--영어폰트-->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Jua&family=Playfair+Display:ital,wght@1,500&display=swap" rel="stylesheet">
	<style>
		#writeH1 {
		    font-family: 'Jua', sans-serif;
		    color: rgb(12, 66, 12);
		    text-align: center;
		    padding: 30px;
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
		
		#writeTitle, #writer, #subject{
		    width: 99%;
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
		    margin:0 auto;
		    height: 50%;
		    width: 70%;
		    border:1px solid black;
		    border-collapse:collapse;
		    margin-bottom: 100px;
		    table-layout: fixed;
		    font-family: 'Jua', sans-serif;
		}
		table#tbl-board th{
		    border:1px solid;
		    padding:5px 0;
		    text-align:center;
		    width: 150px;
		    background-color: whitesmoke;
		}
		table#tbl-board td{
		    border:1px solid;
		    padding:5px 0 5px 10px;
/* 		    text-align:left; */
		}
	     
	</style>

</head>
<body>

<!--여기부터 게시글 작성하기 내용-->
    <h1 id="writeH1">작성하기</h1>

    <form action="${ path }/board/write" method="POST" enctype="multipart/form-data">
        <table id='tbl-board'>
            <tr>
                <th><label for="category">카테고리</label></th>
                <td>
                    <select name="category" id="category" required>
                    	<option value="" selected disabled>선택</option>
                        <option value="B1" id="B1">펫 다이어리</option>
                        <option value="B2" id="B2">펫 미팅 후기</option>
                        <option value="B3" id="B3">정보 공유</option>
                        <option value="B4" id="B4">문의 / 건의</option>
                        <option value="B6" id="B6">신고하기</option>
                        
                        
                    </select>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" id="writeTitle"></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" name="writer" id="writer" value="${ loginMember.id }" readonly></td>
            </tr>
            <tr>
                <th>대상자ID</th>
                <td><input type="text" name="subject" id="subject" value="" ></td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td>
                    <input type="file" name="upfile" id="upfile"><br>

                </td>
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
                        <input type="submit" value="제출" class="btn btn-outline-secondary"> 
                    </div>
                </th>
            </tr>
        </table>
    </form>
</body>


<!--ckeditor-->
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>
<!--ckeditor한국어 연결 옵션-->
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/translations/ko.js"></script>
<script>ClassicEditor.create( document.querySelector( '#editor' ), { language: "ko" }); </script>

<jsp:include page="/views/common/footer.jsp" />