<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
<style>
@charset "UTF-8";

#title {
    text-align: center;
    font-size: 2em;
    border: 1px solid white;
}

.join {
    text-align: right;
}

.s_wrapper {
    text-align: center;
    border: 1px solid white;
}

#walk {
    font-size: 1.2em;
    padding: 5%;
    border: 1px solid white;
}

#search-box {
    width: 250px;
    height: 25px;
}

#d-option {
    width: 50%;
    height: 50%;
    margin: 0 auto;
    padding: 5%;
    background-color: blanchedalmond;
}

.select {
    width: 100%;
    height: 30px;
}

#condition_btn {
    width: 300px;
    height: 40px;
    font-size: 1.2em;
    margin: 0 auto;
    margin-top: 3%;
    display: block;
}
</style>

    <section>
        <div class="s_wrapper">
            <div id="walk">
                <h2>오늘은 어떤 강아지와 달려볼까요?</h2>
            </div>

           	<div style="text-align: center;">
                <button type="submit" id="search_button">산책 게시판으로 이동</button>
           	</div>
        </div>
        <br>
        <!-- 카테고리 선택 옵션 -->
        <div id="test">
            <form action="${ path }/walkboard/list" method="post">
                <fieldset id="d-option">
                    <legend style="text-align: center; font-size: 1.5em;">&nbsp;같이 산책하고 싶은 친구의 유형을 선택해 주세요!&nbsp;</legend>
                <!-- 강아지 옵션 -->
                <label>크기 분류</label><br><br>
                <select name="size" id="size" class="select">
                    <optgroup label="크기 선택">
                        <option selected>----------</option>
                        <option value="소형견">소형</option>
                        <option value="중형견">중형</option>
                        <option value="대형견">대형</option>
                    </optgroup>
                </select>

                <br><br>

                <label>강아지 성별</label><br><br>
                <select name="dogGender" id="gender" class="select">
                    <optgroup label="성별 선택">
                        <option selected>----------</option>
                        <option value="M">수컷</option>
                        <option value="F">암컷</option>
                    </optgroup>
                </select>
                
                <br><br>

                <label>품종</label><br><br>
                <select name="kind" id="kind" class="select">
                    <optgroup label="품종 선택">
                        <option selected>----------</option>
                        <option value="골든리트리버">골든리트리버</option><option value="닥스훈트">닥스훈트</option><option value="도베르만핀셔">도베르만 핀셔</option><option value="래브라도 리트리버">래브라도 리트리버</option><option value="몰티즈">몰티즈</option><option value="미니어처 슈나우저">미니어처 슈나우저</option><option value="미니어처 푸들">미니어처 푸들</option>
                        <option value="미니어처 핀셔">미니어처 핀셔</option><option value="버니즈마운틴도그">버니즈마운틴도그</option><option value="베들링턴 테리어">베들링턴 테리어</option><option value="보더 콜리">보더 콜리</option><option value="보스턴 테리어">보스턴 테리어</option><option value="비글">비글</option><option value="비숑 프리제">비숑 프리제</option><option value="사모예드">사모예드</option>
                        <option value="살루키">살루키</option><option value="셰블랜드 쉽독">셰블랜드 쉽독</option><option value="스탠더드 푸들">스탠더드 푸들</option><option value="시바 이누">시바 이누</option><option value="시베리안 허스키">시베리안 허스키</option><option value="시츄">시츄</option><option value="아메리칸 코카 스파니엘">아메리칸 코카 스파니엘</option><option value="알레스칸맬러뮤트">알레스칸맬러뮤트</option>
                        <option value="요크셔 테리어">요크셔 테리어</option><option value="웰시 코기">웰시 코기</option><option value="이탈리안 그레이하운드">이탈리안 그레이하운드</option><option value="제페니스 스피츠">제페니스 스피츠</option><option value="진돗개">진돗개</option><option value="치와와">치와와</option><option value="카바리에 킹찰스 스파니">카바리에 킹찰스 스파니</option><option value="코커 스패니얼">코커 스패니얼</option>
                        <option value="토이 푸들">토이 푸들</option><option value="파피용">파피용</option><option value="퍼그">퍼그</option><option value="페키니즈">페키니즈</option><option value="펨브록 웰시 코기">펨브록 웰시 코기</option><option value="포메라니안">포메라니안</option><option value="푸들">푸들</option><option value="풍산개">풍산개</option>
                        <option value="프렌치 불도그">프렌치 불도그</option><option value="믹스견">믹스견</option><option value="기타">기타</option>
                    </optgroup>
                </select>
                
                <br><br>
                
                <label for="vaccine">예방접종 여부 (중복 선택 가능)</label><br><br>
                <input type="checkbox" name="vaccine" id="vc1" value="종합백신">&nbsp;DHPPL 종합 백신
                <input type="checkbox" name="vaccine" id="vc2" value="코로나백신">&nbsp;코로나 장염 백신
                <input type="checkbox" name="vaccine" id="vc3" value="켄넬코프">&nbsp;켄넬 코프 백신
                <input type="checkbox" name="vaccine" id="vc4" value="신종플루">&nbsp;신종 플루 백신
                <input type="checkbox" name="vaccine" id="vc5" value="광견병">&nbsp;광견병 백신
                <input type="checkbox" name="vaccine" id="vn" value="접종안함">&nbsp;미접종
                
                <br><br>
                
                <label>중성화 여부</label><br><br>
                <select name="neuter" id="neuter" class="select">
                    <optgroup label="중성화 여부 선택">
                        <option selected>----------</option>
                        <option value="Y">O</option>
                        <option value="N">X</option>
                    </optgroup>
                </select>

                <br><br>
                
                <!-- 주인 옵션 -->
                
                <label>주인 성별</label><br><br>
                <label>
                    <input type="radio" name="gender" id="gender" value="M" checked>&nbsp;남자
                </label>
                <label>
                    <input type="radio" name="gender" id="gender" value="F">&nbsp;여자
                </label>
                </fieldset>
		        <!-- 게시글 검색 버튼 -->
		        <div class="search-btn">
		            <div>
		                <button type="submit" id="condition_btn">검색 조건으로 친구 찾기</button>
		            </div>
		        </div>
            </form>
        </div>
    </section>
    
    <script>
		$(document).ready(() => {
			$('#search_button').on('click', () => {
				location.replace('${ path }/walkboard/walklist')
			});
			
			
		});
	</script>

<jsp:include page="/views/common/footer.jsp" />