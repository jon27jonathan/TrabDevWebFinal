<%@page import="Aplicacao.Funcionario"%>
<%@page import="Aplicacao.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../header.html" />
        <link rel="stylesheet" href="css/css/BodyPage1.css">
    </head>
    <body>
        <% if (session == null) { %>
                <jsp:forward page="login.jsp" />
        <% } else {
                if (session.getAttribute("usuario") == null) {%>
                    <jsp:forward page="login.jsp" />
        <%      } else {
                    Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                    // Validação baseada no Papel do Funcionário, somente o Comprador pode fazer
                    if (usuario.getPapel() != 2) {%>
                        <jsp:forward page="index.jsp" />
                    <%}
                }
           }%>
        <div class = "container mt-2">
            
            <jsp:include page="../navbar.jsp" />
            
            <%
               Categoria aux = (Categoria)request.getAttribute("categoria");
            %>
            
            <h1>Cadastro de Categorias</h1>
            <br>
            <form method="POST" action="CategoriaController" class="needs-validation" novalidate>
                <input type="hidden" class="form-control" id="id" name="id" value="<%= aux.getId() %>">
                <div class="form-row">
                    <div class="col-lg mb-3">
                        <label for="nome_categoria">Nome da Categoria</label>
                        <input type="text" class="form-control" id="nome_categoria" name="nome_categoria" value="<%= aux.getNome_categoria() %>" size="30" maxlength="100" placeholder="Insira a Categoria" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary" type="submit">Enviar</button>
                <a href="CategoriaController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
            </form>
        </div>
    </body>
    <jsp:include page="../scripts.html" />
</html>
