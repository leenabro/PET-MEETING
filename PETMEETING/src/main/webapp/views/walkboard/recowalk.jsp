<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<style>
.mainDiv {
    display: inline-block;
    margin: auto;

}

#firstDiv {
    width: 80%;
    margin-right: 20px;
    float: left;
}

#lastDiv {
	margin-top: 10px;
	font-family: 'Jua', sans-serif;
}

#lastDiv input{
	width: 200px;
	height: 200px;
	font-size: 1.5em;
}



#mainimg {
    text-align: center;
}

footer {
    clear:left;
}
/* Recommend Walk Board img CSS */
#container {
    display: relative;
}

#imgs{
    height: 400px; 
    display: flex;
    justify-content: center; 
    align-items: center;
    overflow: scroll;

}

.item{
    height: auto;
    margin-top: 10px;
    justify-content: center; 
    align-items: center;
    display: flex;
    background-color: rgb(255, 249, 239);
}

.active{
    display: inline-block;
}

#prev, #prev:active, #prev:focus{
    top: 45%;
    transform: translateY(-50%);
    width: 50px; height: 20px;
    border: none; background-color: blanchedalmond; outline: none;
    float: left;
    clear: left;
}
#next, #next:active, #next:focus{
    top: 45%;
    transform: translateY(-50%);
    width: 50px; height: 20px;
    border: none; background-color: blanchedalmond; outline: none;
    float: right;
    clear: right;
    margin-right: 20%;

}


.recoPtag {
    clear: both;
}
</style>

<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
    
	<section>
		 <article>
            <div id="firstDiv" class="item active">
                <h2 style="text-align: center; margin-top: 10px;">마포구 하늘공원 메타세콰이어길</h2>
                <br>
                <div id="mainimg">
                    <img src="${ path }/resources/image/recowalk/mainimg.png">
                </div>
                <br>
                <p style="margin-left: 10%;" class="recoPtag">
                    하늘공원의 한강 변 쪽으로 위치한 메타세콰이어길은 총길이 900m의 산책로로 하늘공원의 다른 산책로보다 <br>
                    유동 인구가 적은 편이라 반려견 동반이 훨씬 수월한 길이다. <br>
                    특히 가을에 단풍이 들면 무척 아름다운 절경을 볼 수 있는 가을 단풍길이라고도 불리고 있다. <br>
                    그뿐만 아니라 월드컵공원 반려견 놀이터에서 충분한 시간을 즐기고, 산책과 더불어 하늘공원에 오르는 계단부터 <br>
                    맹꽁이버스까지 댕댕이와 다양한 체험을 같이 할 수 있는 코스이기도 하다. <br>
                </p>

                <div id="container"></div>
                <div id="imgs">
                    <div><img src="${ path }/resources/image/recowalk/subimg1.png" class="slideimg"></div> &nbsp;
                    <div><img src="${ path }/resources/image/recowalk/subimg2.png" class="slideimg"></div> &nbsp;
                    <div><img src="${ path }/resources/image/recowalk/subimg3.png" class="slideimg"></div>
                </div>
                
            </div>
            <div id="firstDiv" class="item">
                <h2 style="text-align: center; margin-top: 10px;">도봉구 초안산 도봉 둘레길</h2>
                <br>
                <div id="mainimg">
                    <img src="${ path }/resources/image/recowalk/mainimg2.png">
                </div>
                <br>
                <p style="margin-left: 10%;" class="recoPtag">
                    도봉구 초안산근린공원에 마련된 도봉구 반려견놀이터를 시작으로 인근에 형성된 초안산 도봉 둘레길, <br>
                    그리고 초안산 나들길을 산책하는 코스로 비교적 낮은 산에 산림욕을 즐기며 초안산 도봉 둘레길 4.2km 구간이나 <br>
                    초안산나들길 4.4km 구간을 반려견 및 반려견주의 기분과 체력에 따라 선택하며 즐길 수 있는 코스이다. <br>
                    특히 봄에는 초안산 인근 우이천 길을 걸으면 흐드러진 벚꽃 속에서 봄을 온몸으로 맞이할 수 있다. <br>
                </p>

                <div id="container"></div>
                <div id="imgs">
                    <div><img src="${ path }/resources/image/recowalk/subimg2-1.png" class="slideimg"></div> &nbsp;
                    <div><img src="${ path }/resources/image/recowalk/subimg2-2.png" class="slideimg"></div> &nbsp;
                    <div><img src="${ path }/resources/image/recowalk/subimg2-3.png" class="slideimg"></div>
                </div>
                
            </div>
            <div id="firstDiv" class="item">
                <h2 style="text-align: center; margin-top: 10px;">동작구 보라매공원 둘레길</h2>
                <br>
                <div id="mainimg">
                    <img src="${ path }/resources/image/recowalk/mainimg3.png">
                </div>
                <br>
                <p style="margin-left: 10%;" class="recoPtag">
                    댕댕이와 함께 걷는 산책코스 중 손에 꼽는 아스팔트로 된 지역이다. <br>
                    물론 보라매공원 내에 반려견 놀이터와 흙길 및 잔디밭이 펼쳐진 구역이 있지만 많이 번잡하지 않으며 유유자적하게 일정 시간 동안 걸을 수 있는 지역이다. <br>
                    그뿐만 아니라 공원 내의 연못길을 따라 한 바퀴 돌며 산책할 수 있는 구역도 조성되어 있으며, 봄과 여름에는 공원 내 중앙잔디광장과 음악분수 등에서 <br>
                    다양한 이벤트도 진행되니 동작구 인근의 반려인이라면 댕댕이와 함께 충분히 가볼 만한 코스이다.
                </p>

                <div id="container"></div>
                <div id="imgs">
                    <div><img src="${ path }/resources/image/recowalk/subimg3-1.png" class="slideimg"></div> &nbsp; 
                    <div><img src="${ path }/resources/image/recowalk/subimg3-2.png" class="slideimg"></div> &nbsp;
                    <div><img src="${ path }/resources/image/recowalk/subimg3-3.png" class="slideimg"></div>
                </div>
                
            </div>
            <div>
                <button class="prev" id="prev">이전</button>
            </div>
            <div>
                <button class="next" id="next">다음</button>
            </div>
            <div id="lastDiv" class="mainDiv">
				<div>
					<input id="map_btn" class="btn btn-outline-secondary" type="button" value="지도로 검색하기" >
				</div>
            </div>
            <a id="pageMove" href='#indexMove'></a>
        </article>
	</section>
	<script>

		$(document).ready(() => {
	        let curPos = 0; // 현재 보이는 요소의 인덱스 번호
	
	        function prev(){
	            if(curPos > 0){
	            $("button").removeAttr("disabled") // 모든 버튼 사용할 수 있게!
	            // toggleClass : 클래스가 있으면 제거, 없으면 생성!
	            $($(".item")[curPos]).toggleClass("active")
	            $(".item").hide();
	            curPos -= 1;
	            $($(".item")[curPos]).toggleClass("active")
	            $(".active").fadeIn(800); // 새로운 액티브 요소만 스르륵 나타난다
	            }           
	            if(curPos == 0){
	            $(".prev")[0].setAttribute("disabled",'true')
	            }
	        }       
	
	        function next(){
	            if(curPos < 2){
	                $("button").removeAttr("disabled")
	                $($(".item")[curPos]).toggleClass("active")
	                $(".item").hide();
	                curPos += 1;
	                $($(".item")[curPos]).toggleClass("active")
	                $(".active").fadeIn(800); 
	                }
	            if(curPos == 2){
	                $(".next")[0].setAttribute("disabled",'true')
	            }
	        }
	
	        function init(){
	        $(".item").hide()
	        $(".active").show()
	        $(".prev").click(prev)
	        $(".next").click(next)
	        }
	
	        init();
	        
	        $('#map_btn').on('click', () =>{
	        	location.replace('${ path }/views/walkboard/mapAPI.jsp');
	        });
	        
	        $('#pageMove').get(0).click();
	        
	    });
	
	</script>

<jsp:include page="/views/common/footer.jsp" />