<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<script type="text/javascript">
   var modelAttributeValue = '${ServiceList}';
</script>
<body>
	<div>${message.fieldName}</div>
	<div>${message.message}</div>
	<form action="getClusterDetails" method="post">
		<input type="text" name="hdphost" placeholder="Ambari HDP Cluster Host" /><br>
		<input type="text" name="hdpport" placeholder="Ambari HDP Cluster Port" /><br>
		<input type="text" name="hdpcluster" placeholder="Ambari HDP Cluster Name" /><br>
		<input type="text" name="hdpusername" placeholder="Ambari HDP Cluster username" /><br>
		<input type="text" name="hdppassword" placeholder="Ambari HDP Cluster password" />
		
		<input type="Submit" value="Next">
	</form>
</body>
</html>