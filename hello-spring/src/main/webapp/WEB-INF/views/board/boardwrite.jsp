<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 작성</title>
	<jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/boardwrite.js"></script>
    <style type="text/css">
      /*div인데 클래스가 grid인것*/
      div.grid {
        display: grid;
        grid-template-columns: 80px 1fr;
        grid-template-rows: 28px 28px 320px 1fr;
        row-gap: 10px;
      }
      

    </style>
    <!-- 자바스크립트 인터폴레이션. 안전하게 쓰려면 jstl을 사용한다. -->
    <!-- internal 일때만 jstl를 css에서 사용할 수도 있다. body 와 자바스크립트에서도 사용할 수 있다.
    	 에러가 없을 때는 자바스크립트를 보여주지 않는다.
     -->
 <!-- 
    <script type="text/javascript">
    
    /*
    	<c:if test="${not empty errorMessage}">
    	 window.onload = function() {
    		var message = "${errorMessage}";
    		if (message !== "") {
    			alert(message);
    		}
    	}	
    	 
    	</c:if>
    */
    window.onload = function () {
    	var dialog = document.querySelector(".alert-dialog");
    	dialog.showModal();
    }
    </script>
 -->    
  </head>
  <body>
   <jsp:include page="../member/membermenu.jsp"></jsp:include>
  	<c:if test="${not empty errorMessage }">
  		<dialog class="alert-dialog">
  			<h1>${errorMessage}</h1>
  		</dialog>
  	</c:if>
  
    <h1>게시글 작성</h1>
    <form action="/board/write" method="post" enctype="multipart/form-data">
    
      <div class="grid">
        <label for="subject">제목</label>
        <input type="text" id="subject" name="subject" value="${boardVO.subject }"/>

        <label for="file">첨부파일</label>
        <input type="file" name="file" id="file" />

        <label for="content">내용</label>
        <textarea id="content" name="content" style="height: 300px">${boardVO.content }</textarea>

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
