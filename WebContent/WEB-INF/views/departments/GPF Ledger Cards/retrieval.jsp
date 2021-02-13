<%-- 
    Document   : Retrieve Files
    Created on : 29 Mar, 2018, 01:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="gpfForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>
<script type="text/javascript">
	var request,fileId;
	$(document).ready(function() 
	{
    	$('#recordTable').DataTable({
        	"pagingType":"full_numbers"
    	});
	});
	function viewFile(recordNo,sno,right)
	{
		if(right=='1')
		{
			fileId=recordNo;
			var url='viewFile?department=GPFs&fileId='+recordNo+'&sno='+sno;
			if(window.XMLHttpRequest)  
				request=new XMLHttpRequest();  
			else if(window.ActiveXObject)  
				request=new ActiveXObject("Microsoft.XMLHTTP");
			document.getElementById('id01').style.display='block';
			document.getElementById('div').innerHTML='<p style="margin-top: 5%; font-size: 22px;  text-align: center;">Opening File...</p>';
			try
			{
				request.onreadystatechange=setFile;
				request.open("GET",url,true);  
				request.send();
			}
			catch(e)
			{}
		}
		else
			setContent('You are not authorized to viewing files.');
	}
	function setFile()
	{
		if(request.readyState==4)
		{
			var div=document.getElementById('div');
			div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+fileId+'.pdf#toolbar=0"></object>';
		}
	}
	function downloadFile(sno,fileId,right)
	{
		if(right=='1')
			window.location="downloadFile?department=GPFs&fileId="+fileId+"&sno="+sno;
		else
			setContent('You are not authorised to download file!');
	}
	function generateReport(report)
	{
		if(report=='1')
			document.getElementById('reportForm').submit();
		else
			setContent('You are authorized to generate report!');
	}
	function updateFile(sno,right)
	{
		if(right=='1')
			window.location='updateFile?department=GPF Ledger Cards&sno='+sno;
		else
			setContent('You are not authorised to update Files.');
	}
	function trackFile(recordNo)
	{
		window.open("trackRecord?department=GPF Ledger Cards&fileId="+recordNo,"","width=1200,height=700");
	}
	function setContent(content)
	{
		document.getElementById('jsMsg').innerHTML=content;
		document.getElementById('jsModal').style.display='block';
	}
</script>

<c:if test="${not empty msg}">
	<div id="myModal" class="base-modal" style="display: block;">
		<div class="base-modal-content" style="width: 40%;">
			<div class="base-container">
				<br><p class="h4" style="text-align: center; font-family: cambria math;">${msg}</p>
				<button class="confBtn" style="margin-left: 45%; background-color: green; color: #ffffff;" onclick="document.getElementById('myModal').style.display='none'"><p class="h6" style="text-align: center;">OK</p></button>
      		</div>
		</div>
	</div>
</c:if>

<div id="jsModal" class="base-modal" style="display: none;">
	<div class="base-modal-content" style="width: 40%;">
		<div class="base-container">
			<br><p class="h4" style="text-align: center; font-family: cambria math;" id="jsMsg"></p>
			<button class="confBtn" style="margin-left: 45%; background-color: green; color: #ffffff;" onclick="document.getElementById('jsModal').style.display='none'"><p class="h6" style="text-align: center;">OK</p></button>
    	</div>
	</div>
</div>

<div id="id01" class="base-modal" style="display: none; margin: 0px; padding: 0px;">
    <a href="#" style="font-size: 26px;"><span onclick="document.getElementById('id01').style.display='none'" style="float: right; color: red;"><b>&times;</b></span></a>
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:100%; height:90%;"></div>
</div>

<p>&nbsp;</p>
<p class="h2" style=" text-align: center; color:#567080;">Retrieve GPF Ledger Cards</p>
<div class="form-style-8"  style="margin-bottom: 0px; padding-bottom: 0px;">
	<a href="${pageContext.request.contextPath}/retrieval?department=GPF Ledger Cards"><button class="btn btn-primary" style="float: right; margin-right: 10px; cursor: pointer;">Reset</button></a>
    <gpfForm:form action="retrieveG" id="gpfForm" method="get" modelAttribute="gpfForm">
        <table width="100%" style="border-spacing: 20px; border-top:0px; border-collapse: separate; align: center;">
            <tr>
                <td>
                	<label style="color:#567080;">Series:</label><br>
                	<gpfForm:input style="height: 35px;" id="series" path="series" list="seriesHelp" onkeyup="getHelp('series');"/>
                	<datalist id="seriesHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">Account No.:</label><br>
                	<gpfForm:input style="height: 35px;" id="accountNo" path="accountNo" list="accountNoHelp" onkeyup="getHelp('accountNo');"/>
                	<datalist id="accountNoHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">Name:</label><br>
                	<gpfForm:input style="height: 35px;" id="name" path="name" list="nameHelp" onkeyup="getHelp('name');"/>
                	<datalist id="nameHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">Date of Birth:</label><br>
                	<gpfForm:input  style="height: 35px;" id="dob" path="dob" list="dobelp" onkeyup="getHelp('dob');"/>
                	<datalist id="dobHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">Remarks:</label><br>
                	<gpfForm:input style="height: 35px;" id="remarks" path="remarks" list="remarksHelp" onkeyup="getHelp('remarks');"/>
                	<datalist id="remarksHelp"></datalist>
                </td>
            </tr>
            <tr><td colspan="6" align="center"><input type="submit" value="Search"></td></tr>
        </table>
    </gpfForm:form>
</div><br><br>

<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="get">
		<table id="recordTable" class="table display" style="width: 100%; background-color: #ffffff;">
			<thead>
				<tr>
					<th></th>
					<th>Series</th>
					<th>Account No.</th>
					<th>Name</th>
					<th>Date of Birth</th>
					<th>Remarks</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td>${record.series}</td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.accountNo}</a></td>
						<td>${record.name}</td>
						<td>${record.dob}</td>
						<td>${record.remarks}</td>
						<td>
							<a href="#" onclick="viewFile('${record.accountNo}','${record.sno}','${view}');" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<a href="#" onclick="downloadFile('${record.sno}','${record.accountNo}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;
							<c:if test="${not empty lg}"><a href="#" onclick="trackFile('${record.accountNo}')" style="text-decoration: none;">Record Audit</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="hidden" name="department" value="GPF Ledger Cards"/>
		<input  style="margin-left: 43.5%; margin-top: 5px;" class="btn btn-primary" type="button" onclick="generateReport('${report}')" value="Export to Excel">
	</form>
</c:if><br><br><br>