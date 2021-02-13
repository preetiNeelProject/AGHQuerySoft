

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="pensionForm"%>
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
			var url='viewFile?department=Pension&fileId='+recordNo+'&sno='+sno;
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
			window.location="downloadFile?department=Pension&fileId="+fileId+"&sno="+sno;
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
			window.location='updateFile?department=Pension&sno='+sno;
		else
			setContent('You are not authorised to update Files.');
	}
	function trackFile(recordNo)
	{
		window.open("trackRecord?department=Pension&fileId="+recordNo,"","width=1200,height=700");
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
<p class="h2" style=" text-align: center; color:#567080;">Retrieve Pension Records</p>
<div class="form-style-8"  style="margin-bottom: 0px; padding-bottom: 0px;">
	<a href="${pageContext.request.contextPath}/retrieval?department=Pension"><button class="btn btn-primary" style="float: right; margin-right: 10px; cursor: pointer;">Reset</button></a>
    <pensionForm:form action="retrieveP" id="pensionForm" method="get" modelAttribute="pensionForm">
        <table width="100%" style="border-spacing: 20px; border-top:0px; border-collapse: separate; align: center;">
            <tr>
                <td>
                	<label style="color:#567080;">File Id:</label><br>
                	<pensionForm:input style="height: 35px;" id="fileId" path="fileId" list="fileIdHelp" onkeyup="getHelp('fileId');"/>
                	<datalist id="fileIdHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">Name of Pensioner:</label><br>
                	<pensionForm:input style="height: 35px;" id="pensionerName" path="pensionerName" list="pensionerNameHelp" onkeyup="getHelp('pensionerName');"/>
                	<datalist id="pensionerNameHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">Old File No.:</label><br>
                	<pensionForm:input style="height: 35px;" id="oldFileNo" path="oldFileNo" list="oldFileNoHelp" onkeyup="getHelp('oldFileNo');"/>
                	<datalist id="oldFileNoHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">PPO No.:</label><br>
                	<pensionForm:input style="height: 35px;" id="ppoNo" path="ppoNo" list="ppoNoHelp" onkeyup="getHelp('ppoNo');"/>
                	<datalist id="ppoNoHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">GPO No.:</label><br>
                	<pensionForm:input style="height: 35px;" id="gpoNo" path="gpoNo" list="gpoNoHelp" onkeyup="getHelp('gpoNo');"/>
                	<datalist id="gpoNoHelp"></datalist>
                </td>
                <td>
                	<label style="color:#567080;">Remarks/Others:</label><br>
                	<pensionForm:input style="height: 35px;" path="remarks" id="remarks" list="remarksHelp" onkeyup="getHelp('remarks');"/>
                	<datalist id="remarks"></datalist>
                </td>
            </tr>
            <tr>
            	<td>
            		<label style="color:#567080;">Date of Retirement:</label><br>
                	<pensionForm:input  style="height: 35px;" path="retirementDate" id="retirementDate"/>
            	</td>
            	<td>
            		<label style="color:#567080;">Date of Death:</label><br>
                	<pensionForm:input  style="height: 35px;" path="deathDate" id="deathDate"/>
            	</td>
            </tr>
            <tr><td colspan="6" align="center"><input type="submit" value="Search"></td></tr>
        </table>
    </pensionForm:form>
</div><br><br>

<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="get">
		<table id="recordTable" class="table display" style="width: 100%; background-color: #ffffff;">
			<thead>
				<tr>
					<th></th>
					<th>File Id</th>
					<th>Name of Pensioner</th>
					<th>Old File No.</th>
					<th>PPO No.</th>
					<th>GPO No.</th>
					<th>Remarks/Others</th>
					<th>Retirement Date</th>
					<th>Date of Death</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.fileId}</a></td>
						<td>${record.pensionerName}</td>
						<td>${record.oldFileNo}</td>
						<td>${record.ppoNo}</td>
						<td>${record.gpoNo}</td>
						<td>${record.remarks}</td>
						<td>${record.retirementDate}</td>
						<td>${record.deathDate}</td>
						<td>
							<a href="#" onclick="viewFile('${record.fileId}','${record.sno}','${view}');" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<a href="#" onclick="downloadFile('${record.sno}','${record.fileId}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;
							<c:if test="${not empty lg}"><a href="#" onclick="trackFile('${record.fileId}')" style="text-decoration: none;">Record Audit</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="hidden" name="department" value="Pension"/>
		<input  style="margin-left: 43.5%; margin-top: 5px;" class="btn btn-primary" type="button" onclick="generateReport('${report}')" value="Export to Excel">
	</form>
</c:if><br><br><br>