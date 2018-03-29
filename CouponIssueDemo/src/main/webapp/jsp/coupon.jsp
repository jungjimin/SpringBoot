<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>쿠폰발행</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 20px;
        padding-bottom: 40px;
      }

      /* Custom container */
      .container-narrow {
        margin: 0 auto;
        max-width: 500px;
      }
      .container-narrow > hr {
        margin: 30px 0;
      }

      /* Main marketing message and sign up button */
      .jumbotron {
        margin: 60px 0;
        text-align: center;
      }
      .jumbotron h1 {
        font-size: 50px;
        line-height: 1;
      }
      .jumbotron .btn {
        font-size: 21px;
        padding: 14px 24px;
      }

 	  .input-nonblock-level {
 	    display: block;
	    width: 70%;
 	    min-height: 50px;
 	    margin: 10px 0;
	    -webkit-box-sizing: border-box;
	    -moz-box-sizing: border-box;
	         box-sizing: border-box;
	  }
      
      /* Supporting marketing content */
      .marketing {
        margin: 60px 0;
      }
      .marketing p + h4 {
        margin-top: 28px;
      }
    </style>
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="/ico/apple-touch-icon-57-precomposed.png">
	<link rel="shortcut icon" href="/ico/favicon.png">
  </head>
  <script type="text/javascript" 	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <script type="text/javascript">

	function goReg() 
	{
		if(document.frm.email.value == ""){
			alert("Email address를 입력하세요");
			return
		}
	   	
		var sendData = {email:document.frm.email.value};
	    
	    $.ajax({
	        type : 'post',
	        url : 'http://localhost:8080/CouponIssue/',
	        data : sendData,
	        dataType : 'json',
	        error: function(xhr, status, error)
	        {
	        	alert("쿠폰발급에 실패했습니다." + error);
	        },
	        success : function(json)
	        {
	        	alert(json.res_msg);
	        }
	    });    	
	}
	
	function goList(page) {
	   	
		
		var sendData = {pageNo:page,rowCount:3};
		
	    $.ajax({
	        type : 'post',
	        url : 'http://localhost:8080/CouponList/',
	        data : sendData,
	        dataType : 'json',
	        error: function(xhr, status, error){
	            alert(error);
	        },
	        success : function(json){
	        	
	        	var list = json.list;
	            var totalRows = list[0].tot_page;
	     	
	            // 다시 그리기 위한 삭제
	        	$("#contents").empty();
	        	
	        	var html = "<table class='table table-striped'>";
	        	html += "<thead>";
	        	html +="<tr>";
	        	html +="<th align='center'>ID</th>";
	        	html +="<th align='center'>EMAIL</th>";
	        	html +="<th align='center'>COUPON</th>";
	        	html +="<th align='center'>DATETIME</th>";
	        	html +="</thead>";
	        	html +="</tr>";
	        	html +="<tbody>";
	        	
	        	$.each(list, function (key,val){
	        		html +="<tr>";
	        		html +="<td>" + val.id + "</td>";
	        		html +="<td>" + val.email + "</td>";
	        		html +="<td>" + val.coupon_no + "</td>";
	        		html +="<td>" + val.regdate + "</td>";
	        		html +="</tr>";
	        	});
	        	html +="</tbody>";
	        	html +="</table>";
	        	$('#contents').html(html).trigger("create");
	        	
	        	//pagination
	        	$("#pagination").empty(); // 다시 그리기 위한 삭제
	        	for(var i=1; i<= totalRows; i++){
	        		if(i < totalRows){
	        			$("#pagination").append("<a href='javascript:goList("+i+")'>" + i + "</a> | ");
	        		}else{
	        			$("#pagination").append("<a href='javascript:goList("+i+")'>" + i + "</a>");
	        		}
	        	}
	        }
	    });    	
	}	

  </script>
<body>
    <div class="container-narrow">

      <div class="masthead">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="https://github.com/jungjimin/SpringBoot">GitHub</a></li>
        </ul>
        <h3 class="muted">Spring Boot</h3>
      </div>

      <hr>

      <div class="jumbotron">
        <h1>쿠폰 발행</h1>
        <p class="lead">아래 발행버튼을 누르면 쿠폰이 발행됩니다.</p>
	    <form class="form-signin" id="frm" name="frm">
	        <input type="text" size="10" class="input-nonblock-level" name="email" id="email"  placeholder="Email address">
	        <a class="btn btn-large btn-success" href="javascript:goReg()">발행</a>
	    </form>
	  </div>

	  <hr />
	  
      <div class="jumbotron">
        <h1>발급된 쿠폰 조회</h1>
        <p class="lead"></p>
        <a class="btn btn-large btn-success" href="javascript:goList(1)">조회</a>
      </div>
      <hr>
      <div id="contents" class="row-fluid marketing"></div>
	  <div id="pagination" class="text-center"></div>
      <hr>
      	  
      <div class="footer">
        <p>Made by Jimin.Jung .2018.03</p>
      </div>

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap-transition.js"></script>
    <script src="/js/bootstrap-alert.js"></script>
    <script src="/js/bootstrap-modal.js"></script>
    <script src="/js/bootstrap-dropdown.js"></script>
    <script src="/js/bootstrap-scrollspy.js"></script>
    <script src="/js/bootstrap-tab.js"></script>
    <script src="/js/bootstrap-tooltip.js"></script>
    <script src="/js/bootstrap-popover.js"></script>
    <script src="/js/bootstrap-button.js"></script>
    <script src="/js/bootstrap-collapse.js"></script>
    <script src="/js/bootstrap-carousel.js"></script>
    <script src="/js/bootstrap-typeahead.js"></script>

</body>
</html>

