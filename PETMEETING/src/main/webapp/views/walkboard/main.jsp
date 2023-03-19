<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<style>
#walkTitle {
    text-align: center;
    font-size: 2em;
}

.join {
    text-align: right;
}

.b_wrapper {
    width: 70%;
    height: 70%;
    margin: 0 auto;
    position: relative;
}

.b_wrapper div {
	cursor: pointer;
}

#walkimg {
    width: 100%;
    height: 100%;
    position: relative;
}

#walk-list {
    width: 35%;
    height: 60%;
    position: absolute;
    top: 17%;
    left: 8%;
}

#walk-board {
    width: 80%;
    height: 10%;
    position: absolute;
    top: 84%;
    left: 13%;
}
</style>

<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
    
	<section>
		<div id="walkTitle">
			<br>
			<h4>이동하고 싶은 게시판을 선택 하세요!</h4>
		</div>
    
		<!-- 게시판 선택 화면 -->
		<div class="b_wrapper">
			<img src="${ path }/resources/image/walkmain.jpg" id="walkimg">
			<div id="walk-list" onclick="location.href='${ path }/views/walkboard/recowalk.jsp'"></div>
			<div id="walk-board" onclick="location.href='${ path }/views/walkboard/walksearch.jsp'"></div>
		</div>
		<a id="pageMove" href='#indexMove'></a>
	</section>
</body>
<script>
		$(document).ready(() => {
			$('#pageMove').get(0).click();
		});
</script>

<jsp:include page="/views/common/footer.jsp" />