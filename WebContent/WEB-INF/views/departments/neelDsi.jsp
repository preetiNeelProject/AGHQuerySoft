<%-- 
    Document   : Bulk Upload
    Created on : 03 Dec, 2017, 10:17:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty msg}">
	<div id="myModal" class="base-modal" style="display: block;">
		<div class="base-modal-content" style="width: 40%;">
			<div class="base-container">
				<br><p class="h4" style="text-align: center; font-family: cambria math;">${msg}</p>
				<button class="confBtn" style="margin-left: 45%;"><p class="h5" style="font-family: cambria; text-align: center;" onclick="document.getElementById('myModal').style.display='none'">OK</p></button>
      		</div>
		</div>
	</div>
</c:if>

<div id="confModal" class="base-modal" style="display: none; margin: 0px; padding: 0px;">
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="margin-top: 10%; margin-left: 35%; float: left; width:30%; height:27%;">
    	<p class="h3" style="font-family: cambria; text-align: center;"><br><b>Confirm these details before proceeding?</b></p><br>
    	<button class="confBtn" style="margin-left: 29%;"><p class="h5" style="font-family: cambria; text-align: center;" onclick="document.getElementById('confModal').style.display='none';document.getElementById('dsiNeel').submit();"><b>OK</b></p></button>
    	<button class="confBtn" style="margin-left: 5%;"><p class="h5" style="font-family: cambria; text-align: center;" onclick="document.getElementById('confModal').style.display='none'"><b>Cancel</b></p></button>
    </div>
</div>

<br>
<div class="container">
  <div class="form-style-8">
  	<a href="${pageContext.request.contextPath}/dsi?obj=neel"><button class="btn btn-primary" style="float: right; margin-right: 10px; cursor: pointer;">Reset</button></a>
    <h2 style="text-align: center;">Apply Digital Signatures(NEEL)</h2>
      <form action="dsi" method="post" id="dsiNeel" enctype="multipart/form-data">
        <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
        <tr>
            <td>Select Folder:<br><br>
                <input type="file" name="folder" webkitdirectory directory required>
            </td>
            <td>Enter PIN:
                <input type="password" name="pin" required>
            </td>
        </tr>
        <tr><td colspan="2"><input type="hidden" name="obj" value="neel"></td></tr>
        <tr>
            <td colspan="2" align="center"><br><input type="button" onclick="document.getElementById('confModal').style.display='block'" value="Apply signatures"></td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        </TABLE>
      </form>
      <c:if test="${not empty err}">
      	<a href="${pageContext.request.contextPath}/errFile" style="text-decoration: none; color: red; text-align: center; align: center;">Click here to see details...</a>
      </c:if>
  </div>
</div>