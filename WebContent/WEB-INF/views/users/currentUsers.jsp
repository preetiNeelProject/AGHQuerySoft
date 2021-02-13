<%-- 
    Document   : Managing Users
    Created on : 29 Mar, 2018, 12:40:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="userForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jsSHA/2.0.2/sha.js"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/users.js' />"></script>

<script>
	function deleteUser()
	{
	  	window.location="removeUser?userId="+document.getElementById('userId').value;
	}
</script>

<div id="confModal" class="base-modal" style="display: none; margin: 0px; padding: 0px;">
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="margin-top: 10%; margin-left: 35%; float: left; width:30%; height:22%;">
    	<p class="h4" style="font-family: cambria; text-align: center;"><br>Are you sure about updating this user ?</p><br>
    	<button class="confBtn" style="margin-left: 29%; background-color: green; color: #ffffff;" onclick="document.getElementById('updateUserForm').submit();"><p class="h6" style="text-align: center;">OK</p></button>
    	<button class="confBtn" style="margin-left: 5%; background-color: red; color: #ffffff;" onclick="document.getElementById('confModal').style.display='none'"><p class="h6" style="text-align: center;">Cancel</p></button>
    </div>
</div>

<div id="confModal2" class="base-modal" style="display: none; margin: 0px; padding: 0px;">
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="margin-top: 10%; margin-left: 35%; float: left; width:30%; height:22%;">
    	<p class="h4" style="font-family: cambria; text-align: center;"><br>Are you sure about removing this User Id ?</p><br>
    	<button class="confBtn" style="margin-left: 29%; background-color: green; color: #ffffff;" onclick="deleteUser();"><p class="h5" style="font-family: cambria; text-align: center;">OK</p></button>
    	<button class="confBtn" style="margin-left: 5%; background-color: red; color: #ffffff;" onclick="document.getElementById('confModal2').style.display='none'"><p class="h5" style="font-family: cambria; text-align: center;">Cancel</p></button>
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
<c:if test="${not empty users}">
      <div class="form-style-8">
        <h2 style="text-align: center;">Current Users:</h2>
        <form action="${pageContext.request.contextPath}/userDetails" method="post">
        <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
        <TR>
          <TD align="center">
              <select class="form-control" style="width: 250px;" name="userId">
                  <c:forEach items="${users}" var="user">
                      <option class="form-control" value="${user}">${user}</option>
                  </c:forEach>
              </select>
          </TD>
        </TR>
        <TR>
          <TD align="center">
            <input type="submit" value="View Details" required >
          </TD>
        </TR>
        </TABLE>
       </form>
      </div>
</c:if>
</div>
<div class="container">
      <c:if test="${not empty userForm}">
		<div class="form-style-8">
		<input style="float: right;" type="button" onclick="document.getElementById('confModal2').style.display='block'" value="Remove">
        <h2 style="text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;User Details:</h2>
        <userForm:form action="updateUser" id="updateUserForm" method="post" modelAttribute="userForm">
        <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
        <TR>
          <TD>
          	&nbsp;User Id:
            <userForm:input  id="userId" path="userId" autocomplete="off" placeholder="User ID"  required="true" readonly="true"/>
            <span id="userIdEmptyError" style="color:red;display:none;">UserId can't be null.</span>
             <span id="userIdPatternError" style="color:red;display:none;">Please enter only alphabet and numeric value.</span>
          </TD>
          <TD>
          	&nbsp;Password:
            <userForm:input type="password" class="form-control" path="password" id="password" placeholder="Password"  autocomplete="off" required="true"/>
		 <span id="passwordEmptyError" style="color:red;display:none;">Password can't be null.</span>
             <span id="passwordPatternError" style="color:red;display:none;">At least 3 Characters-[1 number, 1 uppercase and lowercase letter].</span>
          </TD>
        </TR>
        <TR>
          <TD>
          <br>&nbsp;Email Id:
            <userForm:input type="email" class="form-control" path="emailId" id="emailId" placeholder="Email ID"  required="true"/>
 <span id="emailEmptyError" style="color:red;display:none;">Email can't be null.</span>
             <span id="emailPatternError" style="color:red;display:none;">You have entered an invalid email address.</span>
          </TD>
          <TD>
          	<br>&nbsp;Mobile No.:
            <userForm:input class="form-control" path="mobileNo" id="mobileNo" placeholder="Mobile Number"  required="true"/>
 <span id="mobileNoEmptyError" style="color:red;display:none;">Number can't be null.</span>
             <span id="mobileNoPatternError" style="color:red;display:none;">Only numeric value and 10 digits.</span>
          </TD>
        </TR>
        <TR>
          <TD>
          	<br>&nbsp;First Name:
            <userForm:input class="form-control" path="firstName" id="firstName" placeholder="First Name"   required="true"/>
 <span id="firstNameEmptyError" style="color:red;display:none;">First Name can't be null.</span>
             <span id="firstNamePatternError" style="color:red;display:none;">Please enter only alphabet and numeric value.</span>
          </TD>
          <TD>
          	<br>&nbsp;Last Name:
            <userForm:input class="form-control" path="lastName" id="lastName" placeholder="Last Name"  required="true"/>
		 <span id="lastNameEmptyError" style="color:red;display:none;">Last Name can't be null.</span>
             <span id="lastNamePatternError" style="color:red;display:none;">Please enter only alphabet and numeric value.</span>
          </TD>
        </TR>
        <TR>
          <TD>
          	<br>&nbsp;User Privilege:
            <userForm:select class="form-control" path="userType" id="userType" required="true" >
 <userForm:option class="form-control" value="Select User Privilege"/>
              <userForm:option class="form-control" value="Administrator"/>
              <userForm:option class="form-control" value="User"/>
            </userForm:select>
 <span id="userTypeEmptyError" style="color:red;display:none;">Select User Type.</span>
          </TD>
          <TD>
          	<br>&nbsp;Employee Id:
            <userForm:input class="form-control" path="employeeId" id="employeeId" placeholder="Employee ID"  required="true"/>
 <span id="employeeIdEmptyError" style="color:red;display:none;">Employee-Id can't be null.</span>
             <span id="employeeIdPatternError" style="color:red;display:none;">Please enter only alphabet, numeric, UnderScore and Dash value.</span>
          </TD>
        </TR>
        <TR>
        	<TD COLSPAN="2" ALIGN="CENTER"><br>
        		<span style="font-size: 21; font-weight: 700;">Departments:</span>
        		<userForm:select class="form-control" style="width: 18%; height: 10%;" path="departments" id="departments" required="true" multiple="true">
              		<userForm:options items="${departments}"/>
            	</userForm:select>
        	</TD>
        </TR>
        <TR>
          <TD colspan="2"><br>
            <span>&nbsp;Permissions:</span>
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
            &nbsp;
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
              &nbsp;&nbsp;&nbsp;<userForm:checkbox path="upload" id="upload"/>&nbsp;Upload Records
              &nbsp;&nbsp;&nbsp;<userForm:checkbox path="view" id="view"/>&nbsp;View Records
              
              &nbsp;&nbsp;&nbsp;<userForm:checkbox value="true" path="download" id="download"/>&nbsp;Download Records
   
              &nbsp;&nbsp;&nbsp;<userForm:checkbox value="true" path="update" id="update"/>&nbsp;Update Records
              &nbsp;&nbsp;&nbsp;<userForm:checkbox value="true" path="report" id="report"/>&nbsp;Generate Reports
              &nbsp;&nbsp;&nbsp;<userForm:checkbox value="true" path="logs" id="logs"/>&nbsp;View Logs
          </TD>
        </TR>
        <TR>
          <TD colspan="2">
            &nbsp;
          </TD>
        </TR>
        <tr><td colspan="2"><userForm:hidden path="sno" id="sno"/></td></tr>
        <TR>
          <TD colspan="2" align="center">
            <br><input type="button" onclick="return validateForm('updateUserForm')" value="Update">
          </TD>
        </TR>
        <TR><TD colspan="2">&nbsp;</TD></TR>
        </TABLE>
       </userForm:form>
      </div>
      </c:if>
    
    <TABLE ALIGN="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">
           <TR><TD colspan="2">&nbsp;<br><br></TD></TR>
    </TABLE>
    </div>