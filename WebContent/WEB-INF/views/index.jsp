<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>

<!DOCTYPE html>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<html lang="en">
<head>
<title>QuerySoft-AG Office Haryana</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jsSHA/2.0.2/sha.js"></script>
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/util.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/staticResources/styleSheets/main.css">
<!--===============================================================================================-->
</head>
<body
	style="background-image: url(${pageContext.request.contextPath}/staticResources/images/indexBG.jpg);">
	<a href="${pageContext.request.contextPath}/gpfView"><button
			class="btn btn-primary"
			style="font-family: cambria math; float: right; margin-top: 10px; margin-right: 10px; cursor: pointer;">GPF
			Records</button></a>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w"
					action="${pageContext.request.contextPath}/login"
					class="login-form" method="post" id="loginForm">
					<div
						style="float: left; text-align: center; height: 62px; width: 100%;">
						<img width="100%"
							src="${pageContext.request.contextPath}/staticResources/images/indexLogo.jpg">
					</div>
					<span style="text-align: center;"
						class="login100-form-title p-b-32"> <br> <br> <b>Login</b>
					</span> <span class="incorrect" style="color: red;">${messages.wrongInputError}</span><br>
					<span class="txt1 p-b-11"> Username </span>
					<div class="wrap-input100 validate-input m-b-36"
						data-validate="Username is required">
						<input class="input100" type="text" name="userId" id="userId"
							onkeyup="userIdFunction();" autocomplete="off"> <span
							class="focus-input100"></span> <span id="userIdPatternError"
							style="color: red; display: none;">Please enter only
							alphabet and numeric value.</span>
					</div>
					<%
						if (request.getAttribute("isLoggedIn") == null) {
					%>
					<span class="txt1 p-b-11"> Password </span>
					<div class="wrap-input100 validate-input m-b-12"
						data-validate="Password is required">
						<span class="btn-show-pass"> <i class="fa fa-eye"></i>
						</span> <input class="input100" type="password" name="password" autocomplete="off"
							id="password"> <span class="focus-input100"></span>
						<%
							} else {
						%>
						<input class="input100" type="password" name="password"
							id="password"> <span class="focus-input100"></span>
						<%
							}
						%>

					</div>
					<label class="incorrect">${messages.loginError}</label>

					<%
						if ((request.getSession().getAttribute("isCaptchaSolved") == null) && (request.getAttribute("isLoggedIn") == null)) {
					%>
					<label for="captchaCode" class="prompt">Retype the
						characters from the picture:</label>

					<!-- Adding BotDetect Captcha to the page -->
					<botDetect:captcha id="loginCaptcha" userInputID="captchaCode"
						codeLength="4" imageWidth="200" codeStyle="ALPHA" />


					<div class="validationDiv">
						<input id="captchaCode" class="input100" type="text"
							style="width: 100%; border: 1px solid black; margin: 5px 0px 5px;"
							name="captchaCode" /> <span class="incorrect"
							style="color: red;">${messages.captchaIncorrect}</span>
					</div>
					<%
						}
					%>
					<%
						if (request.getAttribute("isLoggedIn") == null) {
					%>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" onclick="mySubmit(this)">
							Login</button>
					</div>

					<%
						} else {
					%>
					<a href="${pageContext.request.contextPath}"
						style="color: white; padding: 23px; background: red; border-radius: 50%;">Back
						to login page.</a>
					<%
						}
					%>


				</form>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/select2.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/staticResources/scripts/main.js"></script>
	<script type="text/javascript">
		function mySubmit(obj) {
			var userId = document.getElementById('userId');
			var password = document.getElementById('password');
			var hashObj = new jsSHA("SHA-512", "TEXT", {
				numRounds : 1
			});
			hashObj.update(password.value);
			var hash = hashObj.getHash("HEX");
			password.value = hash;
			document.getElementById('loginForm').submit();
		}

		function userIdFunction() {
			var textPattern = /^[0-9a-zA-Z]+$/;
			var userId = document.forms["loginForm"]["userId"].value;
			if (!userId.match(textPattern)) {
				document.getElementById("userIdPatternError").style.display = 'block';
				return false;
			} else {
				document.getElementById("userIdPatternError").style.display = 'none';
			}
		}
	</script>
</body>
</html>