<%@ page contentType="text/html; charset=UTF-8" import="Application.Funcionario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="AreaRestrita.jsp">Canadian Store</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <%
        Funcionario usuario = (Funcionario) session.getAttribute("usuario");
        if (usuario == null){ %>
            <a href="Login">Login</a>
        <%} else {
        switch (usuario.getPapel()){
            case 0:%>
                <jsp:include page="navbar-administrador.jsp" />
                <%break;
            case 1:%>
                <jsp:include page="navbar-vendedor.jsp" />
                <%break;
            case 2:%>
                <jsp:include page="navbar-comprador.jsp" />
                <%break;
            }
        }
    %>
    
</nav>