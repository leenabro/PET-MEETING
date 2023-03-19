<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/views/common/header.jsp" />
<style>
	#dog_info {
		font-family: 'Jua', sans-serif;
	}
</style>


<section>
	<br>
	<hr>
    <article id="dog_info">
   	   <h3 style="text-align: center;">강아지 정보</h3>

        <!-- 탭 메뉴 만들기 -->
        <!-- 비동기로 수정, 저장할 수 있도록 구현 -->
        <form class="dogInfo" action="${ path }/member/dog/insert" enctype="multipart/form-data" method="post">
        	<div class="container" id="dog_infos">
            	<ul class="tabs">
                	<li class="tab-link current" id="tab_std" data-tab="tab-1">강아지</li>
            	</ul>
	            <div id="tab-1" class="tab-content current">
	                <!-- 강아지 정보 삽입 -->
	                <img src="https://i.pinimg.com/originals/ae/8a/c2/ae8ac2fa217d23aadcc913989fcc34a2.png" alt="사진을 올려주세요.">
	                <div style="text-align: center; margin-top: 10px;">
	                    <input type="file" name="upimg" class="btn btn-outline-secondary"><br>
	                </div>
                    <div class="form-div">
                        <label for="dogName" class="form-label"><b>이름</b></label> <br>
                        <input type="text" class="form-control" name="dogName" id="dogName">
                    </div>
                    <div class="form-div">
                        <label class="form-label"><b>종류</b></label><br>
                        <select name="dogKind">
                            <option>---------------</option>
                            <option value="골든리트리버">골든리트리버</option><option value="닥스훈트">닥스훈트</option><option value="도베르만핀셔">도베르만 핀셔</option><option value="래브라도 리트리버">래브라도 리트리버</option><option value="몰티즈">몰티즈</option><option value="미니어처 슈나우저">미니어처 슈나우저</option><option value="미니어처 푸들">미니어처 푸들</option>
                            <option value="미니어처 핀셔">미니어처 핀셔</option><option value="버니즈마운틴도그">버니즈마운틴도그</option><option value="베들링턴 테리어">베들링턴 테리어</option><option value="보더 콜리">보더 콜리</option><option value="보스턴 테리어">보스턴 테리어</option><option value="비글">비글</option><option value="비숑 프리제">비숑 프리제</option><option value="사모예드">사모예드</option>
                            <option value="살루키">살루키</option><option value="셰블랜드 쉽독">셰블랜드 쉽독</option><option value="스탠더드 푸들">스탠더드 푸들</option><option value="시바 이누">시바 이누</option><option value="시베리안 허스키">시베리안 허스키</option><option value="시츄">시츄</option><option value="아메리칸 코카 스파니엘">아메리칸 코카 스파니엘</option><option value="알레스칸맬러뮤트">알레스칸맬러뮤트</option>
                            <option value="요크셔 테리어">요크셔 테리어</option><option value="웰시 코기">웰시 코기</option><option value="이탈리안 그레이하운드">이탈리안 그레이하운드</option><option value="제페니스 스피츠">제페니스 스피츠</option><option value="진돗개">진돗개</option><option value="치와와">치와와</option><option value="카바리에 킹찰스 스파니">카바리에 킹찰스 스파니</option><option value="코커 스패니얼">코커 스패니얼</option>
                            <option value="토이 푸들">토이 푸들</option><option value="파피용">파피용</option><option value="퍼그">퍼그</option><option value="페키니즈">페키니즈</option><option value="펨브록 웰시 코기">펨브록 웰시 코기</option><option value="포메라니안">포메라니안</option><option value="푸들">푸들</option><option value="풍산개">풍산개</option>
                            <option value="프렌치 불도그">프렌치 불도그</option><option value="믹스견">믹스견</option><option value="기타">기타</option>
                        </select>
                    </div>
                    <div class="form-div">
                        <label class="form-label"><b>크기</b></label><br>
                        <select name="dogSize">
                            <option>---------------</option>
                            <option value="대형견">대형견</option>
                            <option value="중형견">중형견</option>
                            <option value="소형견">소형견</option>
                        </select>
                    </div>
                    <br>
                    <div class="form-div">
                        <label class="form-label"><b>성별</b></label> <br>
                        <label style="margin-right: 40px">
                            <input type="radio" id="male" name="dogGender" value="M" checked> 남자
                        </label>
                        <label>
                            <input type="radio" id="female" name="dogGender" value="F"> 여자
                        </label>
                    </div>
                    <br>
                    <div>
                   	<label><b>중성화 여부</b></label> <br>
	                   	<label style="margin-right: 10px">
	                           <input type="radio" id="neuterdeY" name="neutered" value="Y"> 중성화 O
	                       </label>
	                       <label>
	                           <input type="radio" id="neuterdeN" name="neutered" value="N" checked> 중성화 X
	                    </label>
                    </div> 
                    <br>
                    <div class="form-div">
                         <label class="form-label"><b>예방 접종 여부(접종한 항목을 체크하세요.)</b></label> <br>
                         <table>
                             <tr>
                                 <td>
                                     <label>
                                         <input type="checkbox" name="vaccine" value="종합백신"> 종합백신(DHPPL)
                                     </label> 
                                 </td>
                                 <td>
                                     <label>
                                         <input type="checkbox" name="vaccine" value="코로나백신"> 코로나백신(Corona Virus)
                                     </label>
                                 </td>
                                 <td>
                                     <label>
                                         <input type="checkbox" name="vaccine" value="켄넬코프"> 캔넬코프(Kennel Cough)
                                     </label>
                                 </td>
                             </tr>
                             <tr>
                                 <td>
                                     <label> 
                                         <input type="checkbox" name="vaccine" value="신종플루"> 인플루엔자(신종플루)
                                     </label>
                                 </td>
                                 <td>
                                     <label>
                                         <input type="checkbox" name="vaccine" value="광견병"> 광견병
                                     </label> 
                                 </td>
                                 <td>
                                     <label>
                                         <input type="checkbox" name="vaccine" value="접종안함"> 접종 안함
                                     </label>
                                 </td>
                             </tr>
                         </table>
                    </div>
	        	</div>
	        </div>
	        <div style="text-align: center; margin-top: 10px">
		        <input type="submit" value="저장" class="btn btn-outline-secondary">
	        </div>
      	</form>
        </article>
    </section>
    
    <jsp:include page="/views/common/footer.jsp" /> 