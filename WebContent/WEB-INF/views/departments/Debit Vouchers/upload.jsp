<%-- 
    Document   : Upload File
    Created on : 03 Dec, 2017, 10:17:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="dvForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty msg}">
	<div id="myModal" class="base-modal" style="display: block;">
		<div class="base-modal-content" style="width: 40%;">
			<div class="base-container">
				<br><p class="h4" style="text-align: center; font-family: cambria math;">${msg}</p>
				<button class="confBtn" style="margin-left: 45%; background-color: green; color: #ffffff;"><p class="h6" style="text-align: center;" onclick="document.getElementById('myModal').style.display='none'">OK</p></button>
      		</div>
		</div>
	</div>
</c:if>

<div id="confModal" class="base-modal" style="display: none; margin: 0px; padding: 0px;">
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="margin-top: 10%; margin-left: 35%; float: left; width:30%; height:27%;">
    	<p class="h3" style="font-family: cambria; text-align: center;"><br>Are you sure about uploading of this record ?</p><br>
    	<button class="confBtn" style="margin-left: 29%; background-color: green; color: #ffffff;" onclick="document.getElementById('dvForm').submit();"><p class="h5" style="font-family: cambria; text-align: center;"><b>OK</b></p></button>
    	<button class="confBtn" style="margin-left: 5%; background-color: red; color: #ffffff;" onclick="document.getElementById('confModal').style.display='none'"><p class="h5" style="font-family: cambria; text-align: center;"><b>Cancel</b></p></button>
    </div>
</div>

<br>
<div class="container">   
    <div class="WelTxtSub">Upload Debit Voucher</div>
</div>
<div class="form-style-8" style="margin-bottom: 0px; padding-bottom: 0px; ">
<a href="${pageContext.request.contextPath}/upload?department=Debit Vouchers"><button class="btn btn-primary" style="float: right; margin-right: 10px; cursor: pointer;">Reset</button></a>
    <br><br>
    <dvForm:form action="uploadD" id="dvForm" method="post" modelAttribute="dvForm" enctype="multipart/form-data">
        <table class="table">
            <tr>
                <td>
                	<label style="color:#567080;">Series:</label><br>
                	<dvForm:input style="width: 250px; height: 35px;" path="series" id="series" required="true"/>
                </td>
                <td>
                	<label style="color:#567080;">Account No.:</label><br>
                	<dvForm:input style="width: 250px; height: 35px;" path="accountNo" id="accountNo" required="true"/>
                </td>
                <td>
                	<label style="color:#567080;">Name:</label><br>
                	<dvForm:input style="width: 250px; height: 35px;" path="name" id="name" required="true"/>
                </td>
            	<td>
                	<label style="color:#567080;">VR No.:</label><br>
                	<dvForm:input style="width: 250px; height: 35px;" path="vrNo" id="vrNo" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                	<label style="color:#567080;">Date:</label><br>
                	<dvForm:input  style="width: 250px; height: 35px;" path="date" id="date" required="true"/>
                </td>
            	<td>
                	<label style="color:#567080;">Amount:</label><br>
                	<dvForm:input style="width: 250px; height: 35px;" path="amount" id="amount" required="true"/>
                </td>
                <td>
                	<label style="color:#567080;">Treasury:</label><br>
                	<dvForm:input style="width: 250px; height: 35px;" path="treasury" id="treasury" required="true"/>
                </td>
                <td>
                	<label style="color:#567080;" for="Amount">Choose Pdf File:</label><br>
                	<input type="file" name="file" id="file"/>
                </td>
            </tr>
            <tr><td colspan="4" align="center"><br><input  type="button" onclick="document.getElementById('confModal').style.display='block'" value="Upload"></td></tr>
        </table>
    </dvForm:form>
</div>
<br><br><br>