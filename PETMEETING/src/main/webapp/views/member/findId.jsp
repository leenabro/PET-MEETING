<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<style>
	div {
		display:block;
	}

</style>

<body>
	<div>
		<label>가입시 입력한 이메일을 입력해주세요.</label>
	</div>
	<div>
		<input type="text" id="findIdByEmail">
		<button id="findIdBtn">확인하기</button>
	</div>
		<input type="text" id="ViewId" style="display: none" readonly>
</body>
<script type="text/javascript" src="${ path }/resources/js/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(document).ready(() => {
	  $('#findIdBtn').on('click', () => {
	    var findIdByEmail = $('#findIdByEmail').val().trim();
	    
	    $.ajax({
	      type: 'POST',
	      url: '${ path }/findId',
	      dataType: 'json',
	      data: {
	        findIdByEmail
	      },
	      success: (obj) => {
	        console.log(obj.ReturnFindId);
	        
	        if (obj.ReturnFindId != null) {
	          alert("등록된 이메일입니다.");
	          if (confirm("아이디를 확인하시겠습니까?")) {
	            $('#ViewId').removeAttr("style");
	            $('#ViewId').attr("value", obj.ReturnFindId);
	          }
	        } else {
	          alert("등록된 이메일이 없습니다. 다시 확인해 주세요.");
	        }
	      },
	      error: (error) => {
	        console.log(error);  
	        alert("등록된 이메일이 없습니다. 다시 확인해 주세요.");
	      }
	    });
	  });
	});
</script>
</html>