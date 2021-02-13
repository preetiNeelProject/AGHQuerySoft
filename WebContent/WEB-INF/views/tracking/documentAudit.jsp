
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() 
	{
		$('#logTable').DataTable({
			"pagingType":"full_numbers"
		});
	});
</script>

<c:if test="${not empty logs}">
	<div id="logModal" class="modal" style="display: block; overflow: auto;">
  		<div class="modal-content" style="width: 100%; background-color: #F2F4FB;">
  			<div class="model-header"><span class="close" onclick="window.close();" style="margin-left: 99%; color: black; cursor: pointer;"><b>&times;</b><br></span></div>
  			<p class="h3" style="text-align: center; font-family: cambria;">Document Log</p>
    		<form action="generateDocumentLogReport">
    		<table id="logTable" class="table display" style="margin-top: 10px; width: 100%; background-color: #ffffff;">
    		<thead>
    			<tr>
    				<th align="center">User</th>
    				<th align="center">Activity</th>
    				<th style="width: 100px;" align="center">Date</th>
    				<th align="center">Time</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${logs}" var="log">
    				<tr>
    					<td><c:out value="${log.userId}"/></td>
    					<td><c:out value="${log.activity}"/></td>
    					<td><c:out value="${log.date}"/></td>
    					<td><c:out value="${log.time}"/></td>
    				</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    	</form>
    	<br>
  		</div>
	</div>
</c:if>