<%-- 
    Document   : User Details
    Created on : 03 Dec, 2017, 05:32:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="userForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/staticResources/scripts/users.js' />"></script>
<script type="text/javascript">
	function updateUser()
	{
		var inputs=document.getElementsByTagName('input');
		for(var i=0;i<inputs.length;i++)
		{
			if(inputs[i].type=='checkbox' || inputs[i].type=='select')
				inputs[i].disabled=false;
		}
		document.getElementById('updateUserForm').submit();
	}
</script>

<div id="confModal" class="base-modal" style="display: none; margin: 0px; padding: 0px;">
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="margin-top: 10%; margin-left: 35%; float: left; width:30%; height:22%;">
    	<p class="h4" style="font-family: cambria; text-align: center;"><br>Are you sure about updating your details ?</p><br>
    	<button class="confBtn" onclick="updateUser();" style="margin-left: 29%; background-color: green; color: #ffffff;"><p class="h5" style="font-family: cambria; text-align: center;">OK</p></button>
    	<button class="confBtn" onclick="document.getElementById('confModal').style.display='none'" style="margin-left: 5%; background-color: red; color: #ffffff;"><p class="h5" style="font-family: cambria; text-align: center;">Cancel</p></button>
    </div>
</div>

<c:if test="${not empty msg}">
	<div id="myModal" class="base-modal" style="display: block;">
		<div class="base-modal-content" style="width: 40%;">
			<div class="base-container">
				<br><p class="h4" style="text-align: center; font-family: cambria math;">${msg}</p>
				<button class="confBtn" onclick="document.getElementById('myModal').style.display='none'" style="margin-left: 45%; background-color: green; color: #ffffff;"><p class="h5" style="font-family: cambria; text-align: center;">OK</p></button>
      		</div>
		</div>
	</div>
</c:if>

<div class="container">
      <div class="form-style-8">
        <div class="WelTxtHd">User Details</div>
        <userForm:form action="updateUser" id="updateUserForm" method="post" modelAttribute="userForm">
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
             <span id="passwordPatternError" style="color:red;display:none;">3-12 Characters-[1 number, 1 uppercase and lowercase letter].</span>
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
          	<br>&nbsp;User Privilege:
              <userForm:input path="userType" id="userType" placeholder="User Privilege" required="true" readonly="true"/>
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
        		<span style="font-size: 21; font-weight: 700;">Departments:</span>
        		<userForm:select class="form-control" style="width: 18%; height: 10%;" path="departments" id="departments" required="true" multiple="true" disabled="true">
              		<userForm:options items="${departments}"/>
            	</userForm:select>
        	</TD>
        </TR>
        <TR>
          <TD colspan="2">
            <br>&nbsp;Permissions:
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
              &nbsp;&nbsp;<userForm:checkbox path="upload" id="upload" disabled="true"/>&nbsp;Upload documents
              &nbsp;&nbsp;<userForm:checkbox path="view" id="view" disabled="true"/>&nbsp;View documents
              
              &nbsp;&nbsp;<userForm:checkbox value="true" path="download" id="download" disabled="true"/>&nbsp;Download documents
   
              &nbsp;&nbsp;<userForm:checkbox value="true" path="update" id="update" disabled="true"/>&nbsp;Update documents
              &nbsp;&nbsp;<userForm:checkbox value="true" path="report" id="report" disabled="true"/>&nbsp;Generate report
              &nbsp;&nbsp;<userForm:checkbox value="true" path="logs" id="logs" disabled="true"/>&nbsp;View Logs
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
            &nbsp;
          </TD>
        </TR>
        <tr>
            <td colspan="2"><userForm:hidden path="sno" id="sno"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="button" onclick="return validateForm()" value="Update"></td>
        </tr>
        <TR><TD colspan="2">&nbsp;</TD></TR>
        </TABLE>
       </userForm:form>
      </div>
    
    <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
           <TR><TD colspan="2">&nbsp;<br><br></TD></TR>
    </TABLE>
    </div>