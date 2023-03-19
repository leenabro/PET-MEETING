<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />

<style>
	h3{
		font-family: 'Jua', sans-serif;
	}
	
	#memberInfoTable {
		font-family: 'Jua', sans-serif;
	}
	
	#dog_info {
		font-family: 'Jua', sans-serif;
	}
</style>

 <section>
    <article id="member_info">
	<hr>
    <div style="text-align: center;">
   		<h3 style="text-align: center;">사용자 정보</h3>
	    <table id="memberInfoTable" style="text-align: center;">
	        <tr>
	            <td width="150px">
	                아이디
	            </td>
	            <td id="memberId">
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
	                ${ loginMember.nickname }
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
	                핸드폰 번호
	            </td>
	            <td>
	                ${ loginMember.phone }
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
	     <input type="button" id="memberUpdate_btn" class="btn btn-outline-secondary" value="수정" style="margin-top: 10px; font-family: 'Jua', sans-serif;">
    </div>
	<br>
	<hr>
    </article>
    <article id="dog_info">
        <h3 style="text-align: center;">강아지 정보</h3>

        <!-- 탭 메뉴 만들기 -->
        <!-- 비동기로 수정, 저장할 수 있도록 구현 -->
        <form class="dogInfo" action="${ path }/member/dogUpdate" method="post">
        	<div class="container" id="dog_infos">
            	<ul class="tabs">
	        		<c:forEach var="dog" items="${ list }">
        	        	<li class="tab-link current" id="tab_title${ dog.num }" data-tab="tab-${ dog.num }">강아지</li>
    	    		</c:forEach>
            	</ul>
            	<c:forEach var="dog" items="${ list }">
				
	            <div id="tab-${ dog.num }" class="tab-content">
	                <!-- 강아지 정보 삽입 -->
	                <c:if test="${ empty dog.imgReName }">
	                <img src="https://i.pinimg.com/originals/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2.png" alt="사진을 올려주세요.">
	                </c:if>
	                <c:if test="${ not empty dog.imgReName}">
	                <img src="${ path }/resources/upload/dogimg/${ dog.imgReName }" alt="사진을 올려주세요.">
	                </c:if>
                    <div class="form-div">
                        <label for="dogName" class="form-label"><b>이름</b></label> <br>
                        <input type="text" class="form-control" name="dogName1" id="dogName" value="${ dog.name }" readonly>
                    </div>
                    <div class="form-div">
                        <label class="form-label"><b>종류</b></label><br>
                        <p>${ dog.kind }</p>
                    </div>
                    <div class="form-div">
                        <label class="form-label"><b>크기</b></label><br>
                        <p>${ dog.size }</p>
                    </div>
                    <br>
                    <div class="form-div">
                        <label class="form-label"><b>성별</b></label> <br>
                        <c:if test="${ dog.gender == 'M' }">
                        	<p>남자 아이</p>
                        </c:if>
                        <c:if test="${ dog.gender == 'F' }">
                        	<p>여자 아이</p>
                        </c:if>
                    </div>
                    <br>
                    <div>
                   	<label><b>중성화 여부</b></label> <br>

		                <c:if test="${ dog.neutered == 'Y' }">
	                      	<p>중성화 한 친구</p>
	                    </c:if>
	                    <c:if test="${ dog.neutered == 'N' }">
	                      	<p>중성화 하지 않은 친구</p>
	                    </c:if>
                    </div> 
                    <br>
                    <div class="form-div">
                         <label class="form-label"><b>예방 접종 여부(접종한 항목을 체크하세요.)</b></label> <br>
                         <p>${ dog.vaccine }</p>
                    </div>
	        	</div>
	        	</c:forEach>
	        </div>
	        <div style="text-align: center; margin-top: 10px">
	   			<input type="submit" id="dogUpdate_btn" class="btn btn-outline-secondary" value="추가">
	   			<input type="button" id="dogDelete_btn" class="btn btn-outline-secondary" value="삭제">
	        </div>
      	</form>
    </article>
</section>

<script>
	$(document).ready(() => {
		let num = 7;
		
		$('#memberUpdate_btn').on('click', () => {
			location.replace('${ path }/views/member/update.jsp');
		});
		
		// 텝 메뉴 js 파일
	    $('ul.tabs li').click(function(){
	        var tab_id = $(this).attr('data-tab');
	    
	        $('ul.tabs li').removeClass('current');
	        $('.tab-content').removeClass('current');
	    
	        $(this).addClass('current');
	        $("#"+tab_id).addClass('current');
	    })

	    
	    $('#dogDelete_btn').on('click', function() {
	         
	    	if($('#tab-6').index() == -1) {
	    		if($('#tab-5').index() == -1) {
	    			if($('#tab-4').index() == -1) {
	    				if($('#tab-3').index() == -1) {
	    					if($('#tab-2').index() == -1) {
	    						alert('더이상 삭제할 수 없습니다.')
	    					} else {
		    					$('div#tab-2').remove();
		    			 	    $('li#tab_title2').remove();
// 		    			    	location.replace('${ path }/member/dogDelete');
	    					}
	    				} else {
	    					$('div#tab-3').remove();
	    			 	    $('li#tab_title3').remove();
// 	    			    	location.replace('${ path }/member/dogDelete');
	    				}
	    			} else {
    					$('div#tab-4').remove();
    			 	    $('li#tab_title4').remove();
//     			    	location.replace('${ path }/member/dogDelete');
	    			}
	    		} else {
	    			$('div#tab-5').remove();
			 	    $('li#tab_title5').remove();
// 			    	location.replace('${ path }/member/dogDelete');
	    		}
	    	} else {
	    		$('div#tab-6').remove();
		 	    $('li#tab_title6').remove();
// 		    	location.replace('${ path }/member/dogDelete');
	    	}
	    	
	    	
	    });
	
	});

</script>	

<jsp:include page="/views/common/footer.jsp" /> 
	