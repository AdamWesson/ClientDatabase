<%--
  Created by Adam Wesson
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>File Uploading Form</title>
</head>

<body>
<h1>Welcome to the Client Database Viewer</h1>
<strong>Step 1: </strong>Load Client records:
<p>
<h3><u>File Upload:</u></h3>
<u><strong>Select a file to upload: </strong></u>(ensure that file name = 'Client_List.txt')<br /><br />
<form action = "uploadFile.jsp" method = "post" enctype = "multipart/form-data">
  <input type = "file" name = "file" size = "5120" /> (Max size 5Mb)
  <p>
  <input type = "submit" value = "Upload File" />
</form>
<br>
<a href="iterator.jsp">View Client Data</a>
</body>

</html>
