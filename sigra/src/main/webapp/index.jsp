<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig"%>
<%@ page import="org.wso2.carbon.identity.sso.agent.SSOAgentConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Login SIGRA</title>
</head>
<body>
	<hr />
	<div class="login">
		<form method="post" action="samlsso?SAML2.HTTPBinding=HTTP-POST">
			<h1>Login Sistema SIGRA</h1>
			<input type="text" placeholder="Usuario" name="username" /> 
			<input type="password" placeholder="Senha" name="password"> 
			<input type="submit" class="btn btn-primary btn-block btn-large" value="Login" />
		</form>
	</div>

</body>
</html>