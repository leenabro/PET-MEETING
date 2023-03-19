<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<style>
	div {
		display:block;
	}

	#sendAuthMail, #inputEmail {
		display: inline-flex;
	}

</style>

<body>
	<div>
		<label>가입시 입력한 이메일을 입력해주세요.</label>
	</div>
	<div>
		<input type="text" id="inputEmail">
		<button id="sendAuthMail">인증하기</button>
	</div>		
		<input type="text" id="inputAuthCode" style="display: none">
		<button id="checkAuthCode" style="display: none">확인</button>
</body>
<script type="text/javascript" src="${ path }/resources/js/jquery-3.6.3.js"></script>
<script type="text/javascript">
$(document).ready(() => {
	$('#sendAuthMail').on('click', () => {
		const findIdByEmail = $('#inputEmail').val().trim();
		
		    $.ajax({
		      type: 'POST',
		      url: '${ path }/findCode',
		      dataType: 'json',
		      data: {
		    	  findIdByEmail
		      },
		      success: (obj) => {
		        console.log(obj.ReturnMCode);
		        
		        if (obj.ReturnMCode != null) {
		          alert("등록된 이메일입니다.");
		          if(confirm("비밀번호 변경을 위한 인증코드를 보내시겠습니까?")) {
		        	  const inputEmail = $('#inputEmail').val().trim();
		        	 
						$.ajax({
							type: 'POST',
							url: '${ path }/sendAuthMail',
							dataType: 'json',
							data: {
								inputEmail
							},
									
							success: (obj) => {
								console.log(obj.checkAuthCode);
				
								if(obj.checkAuthCode != null) {
									alert("인증메일을 발송하였습니다.");
									$('#inputAuthCode').show();
									$('#checkAuthCode').show();
									
									$('#checkAuthCode').on('click', () => {
										var inputAuthCode = $('#inputAuthCode').val();
							        	const inputEmail = $('#inputEmail').val().trim();
										
										if(obj.checkAuthCode == inputAuthCode) {
											alert("인증에 성공하셨습니다.");
											$('#inputAuthCode').attr("readonly", true);
											$('#inputAuthCode').attr("value", true);
											
											location.replace("${ path }/views/member/updatePwdByEmail.jsp");	
											
										} else {
											console.log(obj.checkAuthCode);
											console.log(inputAuthCode);
											alert("인증에 실패하였습니다. 입력한 값을 다시 확인해주세요.");
											$('#checkAuthCode').attr("value", "");
										}
									});
									
								} else {
									alert("인증메일 발송에 실패하였습니다. 다시 확인해주세요.");
								}
											
							},
									
							error: (error) => {
								console.log(error);
							}
						});
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
