<%-- 
    Document   : User Details
    Created on : 03 Dec, 2017, 05:32:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="userForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/staticResources/scripts/users.js' />"></script>
<script>
	$(document).ready(function() 
	{
		document.getElementById('userId').value='';
		document.getElementById('password').value='';
	});
</script>

<c:if test="${not empty msg}">
	<div id="myModal" class="base-modal" style="display: block;">
		<div class="base-modal-content" style="width: 40%;">
			<div class="base-container">
				<br><p class="h4" style="text-align: center; font-family: cambria math;">${msg}</p>
				<button class="confBtn" onclick="document.getElementById('myModal').style.display='none'" style="margin-left: 45%; background-color: green; color: #ffffff;"><p class="h6" style="text-align: center;">OK</p></button>
      		</div>
		</div>
	</div>
</c:if>

<div id="confModal" class="base-modal" style="display: none; margin: 0px; padding: 0px;">
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="margin-top: 10%; margin-left: 35%; float: left; width:30%; height:22%;">
    	<p class="h4" style="font-family: cambria; text-align: center;"><br>Are you sure you want to add this user ?</p><br>
    	<button class="confBtn" onclick="document.getElementById('addUserForm').submit();" style="margin-left: 29%; background-color: green;"><p class="h5" style="text-align: center; color: #ffffff;">OK</p></button>
    	<button class="confBtn" onclick="document.getElementById('confModal').style.display='none'" style="margin-left: 5%; background-color: red;"><p class="h5" style="text-align: center; center; color: #ffffff;">Cancel</p></button>
    </div>
</div>

<div class="container">
      <div class="form-style-8">
        <div class="WelTxtHd">Add New User</div><br>
        <userForm:form action="addUser" id="addUserForm" method="post" modelAttribute="userForm">
        <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
        <TR>
          <TD>
            <userForm:input  id="userId" path="userId" autocomplete="off" placeholder="User ID" required="true" />
          </TD>
          <TD>
            <userForm:input type="password" class="form-control" path="password" id="password" placeholder="Password" required="true"/>
          </TD>
        </TR>

        <TR>
          <TD>
            <userForm:input type="email" class="form-control" path="emailId" id="emailId" placeholder="Email ID" required="true"/>
          </TD>
          <TD>
            <userForm:input class="form-control" path="mobileNo" id="mobileNo" placeholder="Mobile Number" required="true"/>
          </TD>
        </TR>

        <TR>
          <TD>
            <userForm:input class="form-control" path="firstName" id="firstName" placeholder="First Name" required="true"/>
          </TD>
          <TD>
            <userForm:input class="form-control" path="lastName" id="lastName" placeholder="Last Name" required="true"/>
          </TD>
        </TR>

        <TR>
          <TD>
            <userForm:select class="form-control" path="userType" id="userType" required="true">
              <userForm:option class="form-control" value="Select User Privilege"/>
              <userForm:option class="form-control" value="Administrator"/>
              <userForm:option class="form-control" value="User"/>
            </userForm:select>
          </TD>
          <TD>
            <userForm:input class="form-control" path="employeeId" id="employeeId" placeholder="Employee ID"  required="true"/>
          </TD>
        </TR>
        <TR>
        	<TD COLSPAN="2" ALIGN="CENTER"><br>
        		<span style="font-size: 21; font-weight: 700;">Select Departments:</span>
        		<userForm:select class="form-control" style="width: 18%; height: 10%;" path="departments" id="departments" required="true" multiple="true">
              		<userForm:options items="${departments}"/>
            	</userForm:select>
        	</TD>
        </TR>
        <TR>
          <TD colspan="2">
            <br><br><span style="font-size: 21; font-weight: 700;">Select Permissions:</span>
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
              &nbsp;&nbsp;<userForm:checkbox path="upload" id="upload"/>&nbsp;Upload Records
              &nbsp;&nbsp;<userForm:checkbox path="view" id="view"/>&nbsp;View Records
              
              &nbsp;&nbsp;<userForm:checkbox value="true" path="download" id="download"/>&nbsp;Download Records
   
              &nbsp;&nbsp;<userForm:checkbox value="true" path="update" id="update"/>&nbsp;Update Records

              &nbsp;&nbsp;<userForm:checkbox value="true" path="report" id="report"/>&nbsp;Generate Reports
              &nbsp;&nbsp;<userForm:checkbox value="true" path="logs" id="logs"/>&nbsp;View Logs
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
            &nbsp;
          </TD>
        </TR>
        <tr>
            <td colspan="2" align="center"><br><input type="button" onclick="document.getElementById('confModal').style.display='block'" value="Add User"></td>
        </tr>
        <TR><TD colspan="2">&nbsp;</TD></TR>
        </TABLE>
       </userForm:form>
      </div>
    
    <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
      <TR><TD colspan="2">&nbsp;<br><br></TD></TR>
    </TABLE>
    </div>