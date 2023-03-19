<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />
<style>
	#memberInfoTable {
		text-align: center;
	}
	
	#memberInfoTable td:nth-of-type(2) {
		text-align: left;		
		padding-left: 10px;

	}
	
</style>


<section>
	<article id="member_info">
	<hr>
	<form action="${ path }/member/update" method="post">
		<h3 style="text-align: center;">사용자 정보</h3>
	    <table id="memberInfoTable">
		    <tr>
		        <td width="150px">
		            아이디
		        </td>
		        <td>
		            ${ loginMember.id }
		        </td>
		    </tr>
		    <tr>
		        <td>
		            이름
		        </td>
		        <td>
		            ${ loginMember.name }
		        </td>
		    </tr>
		    <tr>
		        <td>
		            닉네임
		        </td>
		        <td>
		            <input type="text" id="nickname" name="nickname" value="${ loginMember.nickname }" required>
		            <input type="button" id="checkDuplicate" value="중복확인">
		        </td>
		    </tr>
		    <tr>
		        <td>
		            핸드폰 번호
		        </td>
		        <td>
		            <input type="tel" id="phone" name="phone" value="${ loginMember.phone }">
		        </td>
		    </tr>
		    <tr>
		        <td>
		            주소
		        </td>
		        <td>
					${ loginMember.address }
		        </td>
		    </tr>
		    <tr>
		        <td>
		            이메일
		        </td>
		        <td>
		            ${ loginMember.email }
		        </td>
		    </tr>
		    <tr>
		        <td>
		            성별
		        </td>
		        <td>
		        	<c:if test="${ loginMember.gender == 'M' }">
		        		남자
		        	</c:if>
		        	<c:if test="${ loginMember.gender == 'F' }">
		        		여자
		        	</c:if>
		        </td>
		    </tr>
		</table>
		<div style="text-align: center; margin-top: 10px">
			<input type="submit" id="memberUpate_btn" value="저장">
			<input type="button" id="pwdUpdate_btn" value="비밀번호 수정">
			
		</div>
	</form>
	</article>
    </section>
    
    <script>
	// 아이디 중복 확인
	$(document).ready(() => {
		$('#checkDuplicate').on('click', () => {
			let nickname = $('#nickname').val().trim();
			
			$.ajax({
				type: 'POST',
				url: '${ path }/member/nickCheck',
				dataType: 'json',
				data: {
					nickname
				},
				success: (obj) => {
					console.log(obj);
					
					if (obj.duplicate) {
						alert("이미 사용중인 닉네임 입니다.")
					} else {
						alert("사용 가능한 닉네임입니다.")
					}
					
				},
				error: (error) => {
					console.log(error);
				}
				
			})
		});
		
		$('#pwdUpdate_btn').on('click', () => {
			let url = '${ path }/member/updatePwd';
			
			let status = 'left=500px, top=200px, width=500px, height=250px';
			
			open(url, 'updatePwd', status);
		});
	});
</script>
    
    
    
    <jsp:include page="/views/common/footer.jsp" /> 