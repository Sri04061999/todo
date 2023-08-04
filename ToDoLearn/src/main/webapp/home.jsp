<%@page import="dto.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
</head>
<body>

	<h1>Welcome to home</h1>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Task Name</th>
				<th>Task Description</th>
				<th>Task Date</th>
				<th>Complete Before</th>
				<th>Status</th>
				<th>Change Status</th>
				<th>Delete</th>
				<th>Update</th>
			</tr>
			<!-- <tr>
					<td><input type="number" name=id></td>
					<td><input type="text" name="taskname"></td>
					<td><textarea rows="3" cols="5" name=description></textarea></td>
					<td><input type="date" name="taskdate"></td>
					<td><input type="date" name="taskdate"></td>
					<td><input type="date" name="completebefore"></td>
					<td><input type="text" name="status"></td>
					<td>Delete</td>
					<td>Update</td>
				</tr> -->
			<%
			List<Task> list = (List<Task>) request.getAttribute("list");
			for (Task task : list) {
			%>
			<tr>
				<td><%=task.getId()%></td>
				<td><%=task.getTaskName()%></td>
				<td><%=task.getTask_description()%></textarea></td>
				<td><%=task.getTask_date()%></td>
				<td><%=task.getEnd_date()%></td>
				<td><%=task.isStatus()%></td>
				<td>
					<button>change</button>
				</td>
				<td><a href="delete?id=<%=task.getId()%>">
						<button>delete</button>
				</a></td>
				<td><a href="edit?id=<%=task.getId()%>">
						<button>update</button>
				</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<br> <br> <a href="checkaddtasksession"><button
				style="margin-left: 0px" type="button">Add Task</button> </a> <a
			href="login.html">
			<button style="margin-left: 600px" type="button">LogOut</button>
		</a>
</body>
</html>