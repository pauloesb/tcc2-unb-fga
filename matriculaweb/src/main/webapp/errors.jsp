<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Erro Login</title>
</head>
<body>
<div id="login">
        <h1>Erro Login SIGRA</h1>
        <h2>O seguinte erro ocorreu ao logar no servico:  <%=exception.getMessage()%></h2>
        <hr/>
</div>
</body>
</html>