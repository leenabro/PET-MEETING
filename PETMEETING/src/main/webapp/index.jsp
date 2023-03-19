<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/views/common/header.jsp" />

<%-- 부트스트랩 '회전목마'테마 --%>
     <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
         <div class="carousel-inner">
             <div class="carousel-item active">
                     <img src="https://images.unsplash.com/photo-1534361960057-19889db9621e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80" class="d-block w-100" alt="…">
                 </div>
                 <div class="carousel-item">
                     <img src="https://cdn.pixabay.com/photo/2022/05/20/13/29/dogs-7209506_1280.jpg" class="d-block w-100" alt="…">
                 </div>
                 <div class="carousel-item">
                     <img src="https://cdn.pixabay.com/photo/2016/04/05/21/42/dog-1310545_1280.jpg"  class="d-block w-100" alt="…">
                 </div>
             </div>
             <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                 <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                 <span class="visually-hidden">Previous</span>
             </button>
             <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                 <span class="carousel-control-next-icon" aria-hidden="true"></span>
                 <span class="visually-hidden">Next</span>
             </button>
     </div>

 <section id="content">
 	    <div class="card" style="width: 55rem;" id="search">
                    <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbC3VYr%2FbtrZJ2Z3j7e%2FTMsy0kgAAyW2IG1k9Bfgl0%2Fimg.png" class="card-img-top" alt="…">
                    <div class="card-body">
                        <p class="card-text" id="serch">친구찾기</p>
                    </div>
                </div>
                <div class="card" style="width: 55rem;" id="map">
                    <img src="https://cdn.pixabay.com/photo/2022/10/11/03/40/borador-7513186_1280.jpg" class="card-img-top" alt="…">
                    <div class="card-body">
                        <p class="card-text" id="serch">산책지도</p>
                    </div>
                </div>
                <div class="card" style="width: 55rem;" id="petdiary">
                    <img src="https://cdn.pixabay.com/photo/2022/05/30/14/05/paw-7231300_1280.jpg" class="card-img-top" alt="…">
                    <div class="card-body">
                        <p class="card-text" id="serch">펫다이어리</p>
                    </div>
                </div>
 </section>

<jsp:include page="/views/common/footer.jsp" />
 
