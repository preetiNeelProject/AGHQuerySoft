<%-- 
    Document   : Logs
    Created on : 03 Dec, 2017, 05:32:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>
<script type="text/javascript">
	function checkRadio(radioType)
	{
		if(radioType=='user')
		{
			document.getElementById('fileNoRadio').checked=false;
			document.getElementById('userTable').style.display='block';
			document.getElementById('fileNoTable').style.display='none';
			document.getElementById('logType').value='user';
		}
		else
		{
			document.getElementById('userRadio').checked=false;
			document.getElementById('fileNoTable').style.display='block';
			document.getElementById('userTable').style.display='none';
			document.getElementById('logType').value='voucher';
		}
		document.getElementById('logType').value=radioType;
	}
	$(document).ready(function() 
		{
		   $('#logTable').DataTable({
		       "pagingType":"full_numbers"
		    });
		});
</script>

<c:if test="${not empty msg}">
	<div id="myModal" class="base-modal" style="display: block;">
		<div class="base-modal-content" style="width: 40%;">
			<div class="base-container">
				<br><p class="h4" style="text-align: center; font-family: cambria math;">${msg}</p>
				<button class="confBtn" style="margin-left: 45%; background-color: green; color: #ffffff;"><p class="h5" style="font-family: cambria; text-align: center;" onclick="document.getElementById('myModal').style.display='none'">OK</p></button>
      		</div>
		</div>
	</div>
</c:if>

<p>&nbsp;</p>
<p class="h2" style=" text-align: center; color:#567080;">Logs</p>
<div class="container">
	<table width="100%" class="table">
		<tr>
			<td align="center">
				<input type="radio" name="userRadio" id="userRadio" value="User" onclick="checkRadio('user');"><label style=""><h5 style=" color:#567080;">&nbsp;&nbsp;UserId</h5></label>&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="fileNoRadio" id="fileNoRadio" onclick="checkRadio('fileNo')"><label style=""><h5 style=" color:#567080;">&nbsp;&nbsp;Document No.</h5></label>
			</td>
		</tr>
	</table>
    <div>
    <form action="logs" method="post" id="logForm">
    	<input type="hidden" value="" id="logType" name="logType">
        <table width="100%" class="table" id="userTable" style="display: none; margin-left: 35%;">
            <tr>
                <td width="30%" align="right"><label style=" color:#567080;" for="fileNo"><b>Select User Id:</b></label></td>
                <td width="70%">
                    <select  class="form-control" id="userName" name="userId" required>
                        <option class="form-control" value="Select">Select</option>
                        <c:forEach items="${users}" var="user">
                            <option class="form-control" value="${user}">${user}</option>
                        </c:forEach>
                    </select></td>
            </tr>
            <tr><td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Search"></td></tr>
        </table>

        <table width="100%" class="table" id="fileNoTable" style="display: none; margin-left: 35%;">
            <tr>
                <td align="right"><label style="color:#567080;" for="fileNo"><b>Enter Document No:</b></label></td>
                <td><input id="fileNo" name="fileNo"></td>
            </tr>
            <tr><td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Search"></td></tr>
        </table>
    </form>
    </div>
</div>
<c:if test="${not empty logs}">
    	<form action="generateLogReport" action="get">
    		<table id="logTable" class="table display">
    		<thead>
    			<tr>
    				<th style="width: 70px">User Id</th>
    				<th>Activity</th>
    				<th style="width: 70px">Date</th>
    				<th>Time</th>
    				<th style="width: 120px">MAC Address</th>
    				<th style="width: 140px">Device Name</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${logs}" var="log">
    				<tr>
    					<td><c:out value="${log.userId}"/></td>
    					<td><c:out value="${log.activity}"/></td>
    					<td><c:out value="${log.date}"/></td>
    					<td><c:out value="${log.time}"/></td>
    					<td><c:out value="${log.macAddress}"/></td>
    					<td><c:out value="${log.deviceName}"/></td>
    				</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    	<input style="margin-left: 45%; margin-top: 5px;" class="btn btn-primary" type="submit" value="Export to Excel">
    	</form>
    	<br><br>
    </c:if>
    <br>