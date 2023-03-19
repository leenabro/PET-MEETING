<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp" />

<style>
    body {
        font-family: '돋움';
    }

    div {
        display: block;
        margin-top: 10px;
    }

    label {
        cursor: pointer;
    }
    
    #container {
    	margin-top: 50px;
    }

    .box {
        display: block;
        position: relative;
        width: 40%;
        height: 51px;
        border: solid 1px #dadada;
        /* border: none; */
        padding: 10px 110px 10px 14px;
        vertical-align: top;
        padding-right: 14px;
        box-sizing: border-box;
    }

    .info {
        display: block;
        position: relative;
        width: 100%;
        height: 29px;
        padding-right: 25px;
        line-height: 29px;
        border: none;
        font-size: 15px;
        box-sizing: border-box;
        z-index: 10;
    }

    .signup_content {
        padding-left: 35%;
    }

    .infotitle {
        font-size: medium;
    }

	.box {
		border: solid 1px;
		border-radius: 5px;
	}

	.selectbtn{
        display: inline-block;
        top: 70px;
        width: 195px;
        height: 40px;
        border-radius: 6px;
        border: solid 1px rgba(0, 0, 0, .15);
        background-color: #03c75a;
        box-sizing: border-box;
	}
	
	.btntext {
		color: white;
	}
	
	.btncontaint {
		text-decoration: none;
	}
	
	#findAddress, #checkDuplicate {
		border: solid 1px rgba(0, 0, 0, .15);
		border-radius: 5px;
		background-color: silver;
		text-decoration: none;
		display: inline-block;
		box-sizing: border-box;
	}
	
	#getAddress, #getId , #getEmail , #getNickname {
		display: inline-flex;
		width: 100%;
	}

	#address {
		font-size: 12px;
	}
	
	.msg {
		color: red;
	}

</style>

<section>
<!-- 	아이디 폼태그 컨테이너 -->
    <div id="container" >
    <h1 style="text-align: center;">회원가입</h1>
        <form id="signup_form" action="${ path }/member/signup" method="POST">
            <div id="content">
                <div class="signup_content">
                    <div class="">
                            <h3 class="infotitle">
                                <label for="getid">아이디</label>
                            </h3>
                        <div id="getId">
                            <span class="box" id="idbox">
                                <input type="text" id="inputId" name="inputId" class="info" maxlength="16" required>
                            </span>
                            	<input type="button" id="checkDuplicateId" value="중복 검사" >
                        </div>
                        <div id="getPwd">
                            <h3 class="infotitle">
                                <label for="getpwd">비밀번호</label>
                            </h3>
                            <span class="box">
                                <input type="password" id="inputPwd" name="inputPwd" class="info" maxlength="16" required>
                            </span>
                            <span style="display: none;" id="pwdMsg" class="msg">
                            비밀번호는 6~16자의 영문 소문자, 숫자, 특수문자만 사용 가능합니다.</span>
                        </div>
                        <div id="getrePwd">
                            <h3 class="infotitle">
                                <label for="getpwd2">비밀번호 확인</label>
                            </h3>
                            <span class="box"> 
                                <input type="password" id="rePwd" name="rePwd" class="info" maxlength="16" required>
                            </span>
                            <span style="display: none;" id="rePwdMsg" class="msg">입력하신 비밀번호가 일치하지 않습니다.</span>
                        </div>
                        <div id="getName">
                            <h3 class="infotitle">
                                <label for="getname">이름</label>
                            </h3>
                            <span class="box">
                                <input type="text" id="inputName" name="inputName" class="info" maxlength="16" required>
                            </span>
                            <span style="display: none;" id="nameMsg" class="msg">이름은 한글과 영문 대소문자만 사용 가능합니다.</span>
                        </div>
                            <h3 class="infotitle">
                                <label for="getnickname">닉네임</label>
                            </h3>
                        <div id="getNickname">
                            <span class="box">
                                <input type="text" id="inputNickname" name="inputNickname" class="info" maxlength="8" required>
                            </span>
                            <input type="button" id="checkDuplicateNick" value="중복검사" >
                        </div>
                        <div id="getPhone">
                            <h3 class="infotitle">
                                <label for="getphone">전화번호</label>
                            </h3>
                            <span class="box">
                                <input type="tel" id="inputPhone" name="inputPhone" class="info" maxlength="12" required>
                            </span>
                            <span style="display: none;" id="phoneMsg" class="msg">휴대폰번호는 숫자만 사용 가능합니다.</span>
                        </div>
                            <h3 class="infotitle">
                                <label for="getaddress">주소</label>
                            </h3>
                        <div id="getAddress">
                            <span class="box">
                                <input type="text" id="address" name="address" class="info" maxlength="50" required readonly>
                            </span>
                        <input id="findAddress" type="button" value="주소 찾기" onclick="popup();" />
                        </div>
                            <h3 class="infotitle">
                                <label for="getEmail">이메일</label>
                            </h3>
                        <div id="getEmail">
                            <span class="box">
                                <input type="email" id="inputEmail" name="inputEmail" class="info" maxlength="40" value="" required>
                            </span>
                            <input type="button" id="checkDuplicateEmail" value="중복검사" >
                            <input type="button" id="sendAuthMail" name="sendAuthMail" value="인증하기" disabled>
                        </div>
                        <div id="checkAuthCode_Box" style="display: none">
                            <h3 class="infotitle">
                                <label for="getemail">인증코드 확인</label>
                            </h3>
                            <span class="box">
                                <input type="text" id="checkAuthCode" name="checkAuthCode" class="info" maxlength="10" value="" required>
                            </span>
                            <input type="button" id="btnCheckAuthCode" value="확인" >
                        </div>
                        <div>
                            <h3 class="infotitle">
                                <label for="getbirth">생년월일</label>
                            </h3>
                            <span class="box">
                                <input type="text" id="inputBirth" name="inputBirth" class="info" maxlength="8" required>
                            </span>
                            <span style="display: none;" id="birthMsg" class="msg">생년월일은 8자리 숫자로 입력해 주세요. ex) 20001212</span>
                        </div>
                        <div>
                            <h3 class="infotitle">
                                <label for="getgender">성별</label>
                            </h3>
                                <label for="male">남</label>
                                <input type="radio" id="male" name="inputGender" value="M" required>
                                <label for="female">녀</label>
                                <input type="radio" id="female" name="inputGender" value="F" required>
                        </div>
                        
                    	</div>
                    	<div class="btncontainer">
			            <button type="submit" id="signup" class="selectbtn">
			            <span class="btntext">가입하기</span>
			            </button>
			            <button type="button" id="cancel" class="selectbtn"
			            onclick="location.href='${ path }/'">
			            <span class="btntext">취소</span>
			            </button>
                    	</div>
                </div>
            </div>
        </form>
    </div>
    <a id="pageMove" href='#indexMove'></a>
</section>

<script type="text/javascript">
	
// 	주소찾기 팝업창 함수
	function popup() { 
		window.open("${ path }/views/member/jusopopup.jsp","pop","width=400,height=400,scrollbars=yes,resizable=yes"); 
		}
// 	사용자에게 주소를 입력받고 돌려받음
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPare2,engAddr,JibunAddr,zipNo) {
		document.getElementById("address").value = roadFullAddr;
		}

	const form = document.getElementById("signup_form");
	
	form.addEventListener("submit", (event) => { 
		  if (!isValid()) {
		    alert("제대로 입력하지 않았습니다.");
		    event.preventDefault();
		  }
	});
	
	
	  // 유효성 검사 함수
	  function isValid() {
// 		  값 가져오기
		  const inputId = document.getElementById("inputId").value;
		  const inputPwd = document.getElementById("inputPwd").value;
		  const rePwd = document.getElementById("rePwd").value;
		  const inputName = document.getElementById("inputName").value;
		  const inputNickname = document.getElementById("inputNickname").value;
		  const inputPhone = document.getElementById("inputPhone").value;
		  const address = document.getElementById("address").value;
		  const inputEmail = document.getElementById("inputEmail").value;
		  const inputBirth = document.getElementById("inputBirth").value;
		    const inputGender = document.querySelectorAll('input[name="inputGender"]:checked');

		  const checkAuthCode = document.getElementById("checkAuthCode").value;
		  
		  const pwdRegExp = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{6,16})/;
		  const nameRegExp = /^[가-힣a-zA-Z]+$/;
		  const phoneRegExp = /^\d+$/;

		  function birthRegExp(inputBirth) {
			  
			  const year = inputBirth.substring(0, 4);
			  const month = inputBirth.substring(4, 6);
			  const day = inputBirth.substring(6, 8);
			  
			  const isValidYear = /^[1-2][0-9]{3}$/.test(year);
			  const isValidMonth = /^(0[1-9]|1[0-2])$/.test(month);
			  const isValidDay = /^(0[1-9]|[1-2][0-9]|3[0-1])$/.test(day);
			  
			  if (!isValidYear || !isValidMonth || !isValidDay) {
			    return false;
			  }
			  
			  // 윤년 검사 (2월 29일)
			  const isLeapYear = (year % 4 === 0 && year % 100 !== 0) || year % 400 === 0;
			  if (month === '02' && day > (isLeapYear ? 29 : 28)) {
			    return false;
			  }
			  
			  // 30일까지 있는 달 (2, 4, 6, 9, 11)
			  if (/^(02|04|06|09|11)$/.test(month) && day > 30) {
			    return false;
			  }
			  
			  return true;
		  }
		  
// 		      공백 및 유효성 검사 확인
			  let check = true;
		     
			  if (!inputId) {
				alert("유효하지 않는 아이디입니다.");
				check = false;
			  } 
			  
			  if (!inputPwd) {
				alert("유효하지 않는 비밀번호입니다.");
				check = false;
			  } 
			  
			  if (!rePwd) {
				alert("유효하지 않는 비밀번호입니다.");
				check = false;
			  } 
			  
			  if (!inputName) {
				alert("유효하지 않는 이름입니다.");
				check = false;
			  } 
			  
			  if (!inputNickname) {
				  alert("유효하지 않는 닉네임입니다.");
				  check = false;
			  } 
			  
			  if (!inputPhone) {
					alert("유효하지 않는 전화번호입니다.");
					check = false;
			  } 
			  
			  if (!address) {
					alert("유효하지 않는 주소입니다.");  
					check = false;
			  } 
			  
			  if (!inputEmail) {
					alert("유효하지 않는 이메일입니다.");  
					check = false;
			  } 
			  
			  if (!checkAuthCode) {
					alert("유효하지 않는 인증코드입니다.");  
					check = false;
			  } 
			  
			  if (!inputBirth) {
					alert("유효하지 않는 생년월일입니다.");  
					check = false;
			  } 
			  
			  if (!inputGender) {
					alert("유효하지 않는 성별입니다.");  
					check = false;
			  } 
				  
		  	  if (!pwdRegExp.test(inputPwd)) {
			    document.getElementById("pwdMsg").style.display = "block";
			    check = false;
					alert("유효하지 않는 비밀번호입니다.");  
			  } else {
			    document.getElementById("pwdMsg").style.display = "none";
			  }

			  if (inputPwd !== rePwd) {
					alert("유효하지 않는 비밀번호입니다.");  
			    document.getElementById("rePwdMsg").style.display = "block";
			    check = false;
			  } else {
			    document.getElementById("rePwdMsg").style.display = "none";
			  }

			  if (!nameRegExp.test(inputName)) {
			    document.getElementById("nameMsg").style.display = "block";
			    check = false;
					alert("유효하지 않는 이름입니다.");  
			  } else {
			    document.getElementById("nameMsg").style.display = "none";
			  }

			  if (!phoneRegExp.test(inputPhone)) {
			    document.getElementById("phoneMsg").style.display = "block";
			    check = false;
					alert("유효하지 않는 전화번호입니다.");  
			  } else {
			    document.getElementById("phoneMsg").style.display = "none";
			  }

			  if (!birthRegExp(inputBirth)) {
			    document.getElementById("birthMsg").style.display = "block";
			    check = false;
					alert("유효하지 않는 생년월일입니다");  
			  } else {
			    document.getElementById("birthMsg").style.display = "none";
			  }

		  return check;
	  }
	  

// 	아이디 중복 확인
	$(document).ready(() => {
		$('#checkDuplicateId').on('click', () => {
			let inputId = $('#inputId').val().trim();
			const idRegExp = /^[a-z0-9_-]{4,16}$/;
			
			if(idRegExp.test(inputId)) {
				$.ajax({
					type: 'POST',
					url: '${ path }/member/idCheck',
					dataType: 'json',
					data: {
						inputId
					},
					success: (obj) => {
						console.log(obj);
						
						if(obj.duplicateId) {
							alert("이미 사용중인 아이디 입니다.");
						} else {
							alert("사용 가능한 아이디 입니다.");
							
						}
					},
					error: (error) => {
						console.log(error);	
					}
				});
				
			} else {
				alert("아이디는 4~16자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
			}
		});
		
// 		닉네임 중복확인
		$('#checkDuplicateNick').on('click', () => {
			let inputNickname = $('#inputNickname').val().trim();
			const nicknameRegExp = /^[가-힣a-zA-Z]+$/;
			
			if(nicknameRegExp.test(inputNickname)) {
				$.ajax({
					type: 'POST',
					url: '${ path }/member/nickCheck',
					dataType: 'json',
					data: {
						inputNickname
					},
					success: (obj) => {
						console.log(obj);
						
						if(obj.duplicateNickname) {
							alert("사용중인 닉네임 입니다.");
							$("#inputNickname").attr("value", "");
						} else {
							alert("사용 가능한 닉네임 입니다.");
						}
					},
					error: (error) => {
						console.log(error);	
						$("#inputNickname").attr("value", "");
					}
				});
			
			} else {
				alert("닉네임은 한글과 영문 대소문자만 사용 가능합니다.");
			}
		});
		
// 		이메일 중복확인
		$('#checkDuplicateEmail').on('click', () => {
			let inputEmail = $('#inputEmail').val().trim();
			const emailRegExp = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;
			
			if(emailRegExp.test(inputEmail)) {
					$.ajax({
						type: 'POST',
						url: '${ path }/member/emailCheck',
						dataType: 'json',
						data: {
							inputEmail
						},
						success: (obj) => {
							console.log(obj);
							
							if(obj.duplicateEmail) {
								alert("사용중인 이메일 입니다.");
								$("#inputEmail").attr("value", "");
							} else {
								alert("사용 가능한 이메일 입니다.");
								if(confirm("이메일을 사용하시겠습니까? (확인 시 수정이 불가합니다.)")) {
									$('#checkDuplicateEmail').attr("disabled", true);
									$("input[name=inputEmail]").attr("readonly", true);
									$("input[name=inputEmail]").attr("value", "true");
									$("input[name=sendAuthMail]").removeAttr("disabled");
								} 
							}
						},
						error: (error) => {
							console.log(error);	
						}
					});	
			} else {
				alert("올바르지 않은 이메일 형식입니다. 다시 확인해주세요.");
			}
		});
			
// 		이메일로 인증코드 보내기
		$('#sendAuthMail').on('click', () => {
			let inputEmail = $('#inputEmail').val().trim();
			console.log(inputEmail);
					
			$.ajax({
				type: 'POST',
				url: '${ path }/sendAuthMail',
				dataType: 'json',
				data: {
					inputEmail
				},
						
				success: (obj) => {
					console.log(obj);

					if(obj.checkAuthCode != null) {
						alert("인증메일을 발송하였습니다.");
						$('#checkAuthCode_Box').show();
						
						$('#btnCheckAuthCode').on('click', () => {
							var inputAuthCode = $('#checkAuthCode').val();
							
							if(obj.checkAuthCode == inputAuthCode) {
								alert("인증에 성공하셨습니다.");
								$('#checkAuthCode').attr("readonly", true);
								$('#checkAuthCode').attr("value", true);
								$('#btnCheckAuthCode').attr("disabled", true);
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
		});
	});
	
	$(document).ready(() => {
		$('#pageMove').get(0).click();
	});
	
	
</script>
<jsp:include page="/views/common/footer.jsp" /> 