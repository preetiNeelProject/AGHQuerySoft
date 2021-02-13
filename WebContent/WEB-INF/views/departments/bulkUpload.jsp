

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function csvTemplate()
	{
		var department=document.getElementById('department').value;
		if(department=='Select')
			alert('Select valid Department!');
		else
			window.location="csvTemplate?department="+department;
	}
</script>

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
    	<button class="confBtn" style="margin-left: 29%;"><p class="h5" style="font-family: cambria; text-align: center;" onclick="document.getElementById('confModal').style.display='none';document.getElementById('folderUpload').submit();"><b>OK</b></p></button>
    	<button class="confBtn" style="margin-left: 5%;"><p class="h5" style="font-family: cambria; text-align: center;" onclick="document.getElementById('confModal').style.display='none'"><b>Cancel</b></p></button>
    </div>
</div>

<div class="container">
  <div class="form-style-8">
  	<a href="${pageContext.request.contextPath}/uploadBulk"><button class="btn btn-primary" style="float: right; margin-right: 10px;  background-color:#e7e7e7; color:black;  cursor: pointer;">Reset</button></a>
    <h2 style="text-align: center;">Index & Upload Documents</h2>
    <a href="#" onclick="csvTemplate();" style="text-decoration: none;">Download Csv Template</a>
    
      <form action="uploadBulk" method="post" id="folderUpload" enctype="multipart/form-data">
        <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
        <tr>
            <td>Select Csv File:<br><br>
                <input type="file" name="csvFile" accept=".csv" required>
            </td>
            <td>Select Folder:<br><br>
                <input type="file" name="folder" webkitdirectory directory required>
            </td>
            <td>Select Department<br><br>
            	<select class="form-control" name="department" id="department">
            		<option class="form-control" value="Select">Select</option>
            		<c:forEach items="${departments}" var="department">
            			<option class="form-control" value="${department}">${department}</option>
            		</c:forEach>
            	</select>
            </td>
        </tr>
        <tr><td colspan="3">&nbsp;</td></tr>
        <tr>
            <td colspan="3" align="center"><br><input type="button" onclick="document.getElementById('confModal').style.display='block'" value="Upload Documents"></td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        </TABLE>
      </form>
      <c:if test="${not empty err}">
      	<a href="${pageContext.request.contextPath}/errFile" style="text-decoration: none; color: red; text-align: center; align: center;">Click here to see details...</a>
      </c:if>
  </div>
</div>