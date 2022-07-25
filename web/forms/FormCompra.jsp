<%@page import="Application.Funcionario"%>
<%@page import="Application.Compra"%>
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
                    // Validação baseada no Papel do Funcionário, só o comprador pode fazer
                    if (usuario.getPapel() != 2) {%>
                        <jsp:forward page="index.jsp" />
                    <%}
                }
           }%>
        <div class = "container mt-2">
            
            <jsp:include page="../navbar.jsp" />
            
            <%
               Compra aux = (Compra)request.getAttribute("compra");
            %>
            
            <h1>Cadastro de Compras</h1>
            <br>
            <form method="POST" action="CompraController" class="needs-validation" novalidate>
                <input type="hidden" class="form-control" id="id" name="id" value="<%= aux.getId() %>">
                <div class="form-row">
                    <div class="col-lg mb-3">
                        <label for="quantidade_compra">Quantidade da Compra</label>
                        <input type="int" class="form-control" id="quantidade_compra" name="quantidade_compra" value="<%= aux.getQuantidade_compra() %>" size="30" maxlength="100" placeholder="Insira a Quantidade" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="data_compra">Data da Compra</label>
                        <input type="string" class="form-control data" id="data_compra" name="data_compra" value="<%= aux.getData_compra() %>" size="30" maxlength="100" placeholder="0000-00-00" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="valor_compra">Valor da Compra</label>
                        <input type="double" class="form-control" id="valor_compra" name="valor_compra" value="<%= aux.getValor_compra() %>" size="30" maxlength="100" placeholder="0.00" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="id_fornecedor">ID do Fornecedor</label>
                        <input type="int" class="form-control" id="id_fornecedor" name="id_fornecedor" value="<%= aux.getId_fornecedor() %>" size="30" maxlength="100" placeholder="Insira o ID do Fornecedor" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="id_produto">ID do Produto</label>
                        <input type="int" class="form-control" id="id_produto" name="id_produto" value="<%= aux.getId_produto() %>" size="30" maxlength="100" placeholder="Insira o ID do Produto" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="id_funcionario">ID do Funcionario</label>
                        <input type="int" class="form-control" id="id_funcionario" name="id_funcionario" value="<%= aux.getId_funcionario() %>" size="30" maxlength="100" placeholder="Insira o ID do Funcionario" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary" type="submit">Enviar</button>
                <a href="CompraController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
            </form>
        </div>
    </body>
    <jsp:include page="../scripts.html" />
</html>
