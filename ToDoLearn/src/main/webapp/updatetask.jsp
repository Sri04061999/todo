<%@page import="java.time.Period"%>
<%@page import="dto.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Task</title>
</head>
<body>
	<%
	Task task = (Task) request.getAttribute("task");
	%>
	<form action="updatetask" method="post">
		<fieldset style="width: 400px">
			<legend> Add Task </legend>
			<table>
				<input type="text" name="id" value="<%=task.getId()%>"
					hidden="hidden">

				<tr>
					<th>Task Name</th>
					<td><input type="text" name="taskname" value="<%= task.getTaskName()%>"></td>
				</tr>
				<tr>
					<th>Task Description</th>
					<td><textarea rows="3" cols="5" name="taskdescription" value="<%= task.getTask_description()%>"  ></textarea></td>
				</tr>
				<tr>
					<th>Number Of Days</th>
					<td><input type="number" name="noofdays"  value="<%=Period.between(task.getTask_date(), task.getEnd_date()).getDays()%>"></td>
				</tr>
			</table>
			<br>
			<button style="margin-left: 20px" type="submit">Add</button>
			<button style="margin-left: 180px" type="reset">Cancel</button>
		</fieldset>
	</form>
	<br>
	<a style="margin-left: 40px" href="backtohome"><button
			type="button">Back</button></a>
</body>
</html>