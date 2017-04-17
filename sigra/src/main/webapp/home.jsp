<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.wso2.carbon.identity.sso.agent.bean.LoggedInSessionBean" %>
<%@ page import="org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig" %>
<%@ page import="org.wso2.carbon.identity.sso.agent.SSOAgentConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>SIGRA Logado</title>
</head>
<%String identificador = null;%>
<%if(request.getSession(false) != null && 
	request.getSession(false).getAttribute(SSOAgentConstants.SESSION_BEAN_NAME) == null){%>
        <%request.getSession().invalidate();%>
        <script type="text/javascript">
        location.href = "index.jsp";
        </script>
<%return;}%>

<%SSOAgentConfig ssoAgentConfig = (SSOAgentConfig)getServletContext().getAttribute(SSOAgentConstants.CONFIG_BEAN_NAME);%>
<%LoggedInSessionBean sessaoBean = (LoggedInSessionBean)session.getAttribute(SSOAgentConstants.SESSION_BEAN_NAME);%>
<%LoggedInSessionBean.AccessTokenResponseBean accessTokenResponseBean = null;%>

<%if(sessaoBean != null){%>
	<%if(sessaoBean.getSAML2SSO() != null) {%>
    	<%identificador = sessaoBean.getSAML2SSO().getSubjectId();%>
        <%accessTokenResponseBean = sessaoBean.getSAML2SSO().getAccessTokenResponseBean();%>
    <%} else {%>
    		<script type="text/javascript">
            	location.href = "index.jsp";
            </script>
			<%return;}%>
	<%} else {%>
        <script type="text/javascript">
            location.href = "index.jsp";
        </script>
  <%return;}%>
<body>
	<div>
		<div class="login">
	    <%if(identificador != null && ssoAgentConfig.getSAML2().isSLOEnabled()){%>
	    	<h1>Sistema SIGRA</h1><br/>
	        <h2> Voce esta logado como: <%=identificador%></h2>
	        <a type="submit" class="btn btn-primary btn-block btn-large" href="logout?SAML2.HTTPBinding=HTTP-POST">Logout (HTTP Post)</a><br/>
	    <%}%>
		</div>
	</div>
</body>
</html>