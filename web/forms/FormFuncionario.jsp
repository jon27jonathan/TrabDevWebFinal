<%@page import="Aplicacao.Funcionario"%>
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
                    // Validação baseada no Papel do Funcionário, só o ADM pode fazer
                    if (usuario.getPapel() != 0) {%>
                        <jsp:forward page="index.jsp" />
                    <%}
                }
           }%>
        <div class = "container mt-2">

            <jsp:include page="../navbar.jsp" />

            <%
                Funcionario aux = (Funcionario) request.getAttribute("funcionario");
            %>

            <h1>Cadastro de Funcionários</h1>
            <br>
            <form method="POST" action="FuncionarioController" class="needs-validation" novalidate>
                <input type="hidden" class="form-control" id="id" name="id" value="<%= aux.getId()%>">
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="Nome">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="<%= aux.getNome()%>" size="30" maxlength="100" placeholder="Insira seu Nome" required>
                        <div class="valid-feedback">
                            Parece bom!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="Cpf">CPF</label>
                        <input type="text" class="form-control cpf" id="cpf" name="cpf" value="<%= aux.getCpf()%>" placeholder="000.000.000-00" maxlength="14" autocomplete="on" required>
                        <div class="valid-feedback">
                            Parece bom!
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="Senha">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" value="<%= aux.getSenha()%>" placeholder="Senha" required>
                        <div class="valid-feedback">
                            Parece bom!
                        </div>
                    </div>
                    <div class="col-md-2 mb-3">
                        <label for="Papel">Papel</label>
                        <select class="custom-select custom-select-m" id="papel" name="papel" value="<%= aux.getPapel()%>" required>
                            <option value="1">Vendedor</option>
                            <option value="2">Comprador</option>
                            <option value="0">Administrador</option>
                        </select>
                    </div>
                </div>
                <br>
                <button class="btn btn-primary" type="submit">Enviar</button>
                <a href="FuncionarioController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
            </form>
        </div>
    </body>
    <jsp:include page="../scripts.html" />
</html>
