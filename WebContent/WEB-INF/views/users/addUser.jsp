<%-- 
    Document   : User Details
    Created on : 03 Dec, 2017, 05:32:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="userForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jsSHA/2.0.2/sha.js"></script>

<script type="text/javascript" src="<c:url value='/staticResources/scripts/users.js' />"></script>
<script>
	$(document).ready(function() 
	{
		document.getElementById('userId').value='';
		document.getElementById('password').value='';
	});
</script>
<style>
input:invalid {
  border: red solid 3px;
}
</style>
<script type="text/javascript">
/* function mySubmit(obj) {
	// var userId = document.getElementById('userId');
	  var password = document.getElementById('password');
	  var hashObj = new jsSHA("SHA-512", "TEXT", {numRounds: 1});
	  hashObj.update(password.value);
	  var hash = hashObj.getHash("HEX");
	  password.value = hash;
	  document.getElementById('addUserForm').submit();
	} */
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
            <userForm:input  id="userId" path="userId" autocomplete="off" placeholder="User ID" pattern="/^[0-9a-zA-Z]+$/" onkeyup="userIdFunction();" required="true" />
             <span id="userIdEmptyError" style="color:red;display:none;">UserId can't be null.</span>
             <span id="userIdPatternError" style="color:red;display:none;">Please enter only alphabet and numeric value.</span>
          </TD>
          <TD>
            <userForm:input type="password" class="form-control" path="password" id="password" placeholder="Password" onkeyup="passwordFunction();" autocomplete="off" required="true"/>
            <span id="passwordEmptyError" style="color:red;display:none;">Password can't be null.</span>
             <span id="passwordPatternError" style="color:red;display:none;">At least 3 Characters-[1 number, 1 uppercase and lowercase letter].</span>
          </TD>
        </TR>

        <TR>
          <TD>
            <userForm:input type="email" class="form-control" path="emailId" id="emailId" placeholder="Email ID" onkeyup="emailsFunction();" required="true"/>
            <span id="emailEmptyError" style="color:red;display:none;">Email can't be null.</span>
             <span id="emailPatternError" style="color:red;display:none;">You have entered an invalid email address.</span>
          </TD>
          <TD>
            <userForm:input class="form-control" path="mobileNo" id="mobileNo" placeholder="Mobile Number" onkeyup="mobileNoFunction();" size="2" required="true"/>
            <span id="mobileNoEmptyError" style="color:red;display:none;">Number can't be null.</span>
             <span id="mobileNoPatternError" style="color:red;display:none;">Only numeric value and 10 digits.</span>
          </TD>
        </TR>

        <TR>
          <TD>
            <userForm:input class="form-control" path="firstName" id="firstName" onkeyup="firstNameFunction();" placeholder="First Name" required="true"/>
            <span id="firstNameEmptyError" style="color:red;display:none;">First Name can't be null.</span>
             <span id="firstNamePatternError" style="color:red;display:none;">Please enter only alphabet and numeric value.</span>
          </TD>
          <TD>
            <userForm:input class="form-control" path="lastName" id="lastName" placeholder="Last Name" onkeyup="lastNameFunction();" required="true"/>
            <span id="lastNameEmptyError" style="color:red;display:none;">Last Name can't be null.</span>
             <span id="lastNamePatternError" style="color:red;display:none;">Please enter only alphabet and numeric value.</span>
          </TD>
        </TR>

        <TR>
          <TD>
            <userForm:select class="form-control" path="userType" id="userType" required="true" onmouseover="userTypeValidate();" onkeyup="userTypeValidate();">
              <userForm:option class="form-control" value="Select User Privilege"/>
              <userForm:option class="form-control" value="Administrator"/>
              <userForm:option class="form-control" value="User"/>
            </userForm:select>
              <span id="userTypeEmptyError" style="color:red;display:none;">Select User Type.</span>
          </TD>
          <TD>
            <userForm:input class="form-control" path="employeeId" id="employeeId" placeholder="Employee ID" onkeyup="employeeIdFunction();" required="true"/>
            <span id="employeeIdEmptyError" style="color:red;display:none;">Employee-Id can't be null.</span>
             <span id="employeeIdPatternError" style="color:red;display:none;">Please enter only alphabet, numeric, UnderScore and Dash value.</span>
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
            <td colspan="2" align="center"><br><input type="button" onclick="return validateForm('addUserForm')" value="Add User"></td>
        </tr>
        <TR><TD colspan="2">&nbsp;</TD></TR>
        </TABLE>
       </userForm:form>
      </div>
    
    <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
      <TR><TD colspan="2">&nbsp;<br><br></TD></TR>
    </TABLE>
    </div>