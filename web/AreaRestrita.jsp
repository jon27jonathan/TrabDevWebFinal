<%@ page contentType="text/html; charset=UTF-8" import="Aplicacao.Funcionario" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.html" />
        <link rel="stylesheet" href="css/css/BodyPage1.css">
    </head>
    <body>        
        <% if (session == null) { %>
                <jsp:forward page="login.jsp" />
        <% } else {
                if (session.getAttribute("usuario") == null) {%>
                    <jsp:forward page="login.jsp" />
        <%      }    
           }%>

        <div class="container mt-2">
            <jsp:include page="navbar.jsp" />            
            <%
                Funcionario usuario = (Funcionario) session.getAttribute("usuario");
            %>
            <br>
            <div>
                <img src="assets/foto.png"/><br>
            </div>
            <br><br><h5>Seja bem vindo <%= usuario.getNome()%></h5><br>
            <h5>Espero que aproveite o nosso sistema!!!!</h5>

        </div>

        <jsp:include page="scripts.html" />
    </body>
</html>
