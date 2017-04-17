<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig"%>
<%@ page import="org.wso2.carbon.identity.sso.agent.SSOAgentConstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Login Matricula Web</title>
</head>
<body>
<div class="login-form">
     <h1>Matricula Web</h1>
     <form method="post" action="samlsso?SAML2.HTTPBinding=HTTP-POST">
     <div class="form-group ">
       <input type="text" class="form-control" placeholder="Usuario" id="username">
       <i class="fa fa-user"></i>
     </div>
     <div class="form-group log-status">
       <input type="password" class="form-control" placeholder="Senha" id="password">
       <i class="fa fa-lock"></i>
     </div>
      <span class="alert">Invalid Credentials</span>
     <input type="submit" class="log-btn" value="Login"/>
     </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="js/index.js"></script>
</body>
</html>