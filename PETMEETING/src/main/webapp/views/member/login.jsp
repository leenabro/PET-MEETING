<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />
<style>
    #container {
         margin-top: 5%; 
         padding-left: 80px;
    }
    
    .btn_login {
        display: inline-block;
        top: 70px;
        width: 320px;
        height: 40px;
        border-radius: 6px;
        border: solid 1px rgba(0, 0, 0, .15);
        background-color: #03c75a;
        box-sizing: border-box;
    }

    
    div #wrap.wrap {
        letter-spacing: -.5px;
    }
    
	div { 
       display: block;
    } 
    
    .login_wrap {
        box-sizing: border-box;
        width: 400px;
        margin: 0 auto;
    }
    
    .find_wrap {
        padding: 20px 0 20px;
        list-style: none;
        display: inline-flex;
	}

    .find_text {
        font-size: 14px;
        line-height: 17px;
        text-decoration: none;
        font: bolder;
        color: black;
        
    }
    
    .input_text {
    	width: 80%;
        border: solid 1px;
        border-radius: 6px;
    }
    
    #login_text {
    margin-top: 30px;
    }

</style>
    <section>
            <h1 id="login_text" style="text-align: center;">Login</h1>
        <div id="container" class="container">
            <div class="content">
                <div class="login_wrap">
                    <form action="${ path }/member/login" method="POST">
                        <div>
                            <input type="text" id="inputId" name="inputId" class="input_text" placeholder="아이디" maxlength="16">
                        </div>
                        <br>
                        <div>
                            <input type="password" id="inputPwd" name="inputPwd" class="input_text" placeholder="비밀번호" maxlength="16">
                        </div>
                        <br>
                        <div class="btn_login_wrap"> 
                            <button type="submit" class="btn_login" id="btn_login">
                                <span class="btn_login_text">로그인</span>
                            </button>
                        </div>
                        <br>
                        <div>
                            <label for="saveid">
                                <input type="checkbox" id="saveid" name="saveid" class="input_save">
                                <span style="font-size: small;">아이디 저장</span>
                            </label>
                        </div>
                    </form>
                    
                    <ul class="find_wrap" id="find_wrap">
                        <li><a onclick="popupfindId();" class="find_text">아이디 찾기</a></li>
                        <li><a onclick="popupfindPwd();" class="find_text" style="padding: 50px;">비밀번호 찾기</a></li>
                        <li><a href="${ path }/member/signup" class="find_text">회원가입</a></li>
                    </div>
                    </ul>
            </div>
        </div>
        <a id="pageMove" href='#indexMove'></a>
    </section>
<script type="text/javascript">
	var popupWidth = 100;
	var popupHeight = 50;
	
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
	
	var popupY= (window.screen.height / 2) - (popupHeight / 2);
	// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
	
// 	window.open('', '', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);


	function popupfindId() { 
		window.open("${ path }/views/member/findId.jsp","pop", "height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ', scrollbars=yes, resizable=yes"); 
		}
	
// 	function popupfindPwd() { 
		
// 		window.open("${ path }/views/member/checkAgreement.jsp"); 
// // 		window.open("${ path }/views/member/checkAgreement.jsp","pop", "height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ', scrollbars=yes, resizable=yes"); 
// 		}

	var loginBtn = document.getElementById("btn_login");
	
	loginBtn.addEventListener("click", login);
	
	// 로그인 버튼 클릭 실행 함수
	function login() {
	  var saveIdCheckbox = document.getElementById("saveid");
	  
	  // 체크박스가 체크되면, input 요소의 값을 저장
	  if (saveIdCheckbox.checked) {
	    var inputIdValue = document.getElementById("inputId").value;
	    // Local Storage에 값 저장
	    localStorage.setItem("savedId", inputIdValue); 
	  }
	}

	// 페이지 로딩 시 저장한 값확인하여 input 요소에 입력
	window.onload = function() {
	  var savedId = localStorage.getItem("savedId");
	  if (savedId !== null) {
	    var inputId = document.getElementById("inputId");
	    inputId.value = savedId;
	    
	    // 체크박스도 체크된 상태로 표시
	    var saveIdCheckbox = document.getElementById("saveid");
	    saveIdCheckbox.checked = true;
	  }
	};
	
	$(document).ready(() => {
		$('#pageMove').get(0).click();
	});
	
	function popupfindPwd() { 
	      window.open("${ path }/views/member/findPwd.jsp","pop", "height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ', scrollbars=yes, resizable=yes"); 
	      }
</script>


<jsp:include page="/views/common/footer.jsp" /> 