<%@page import="Application.Funcionario"%>
<%@page import="Application.Venda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../header.html" />
        <link rel="stylesheet" href="css/css/BodyPage1.css">
    </head>
    <body>
        <%
               Venda aux = (Venda)request.getAttribute("venda");
            %>
        <% if (session == null) { %>
                <jsp:forward page="login.jsp" />
        <% } else {
                if (session.getAttribute("usuario") == null) {%>
                    <jsp:forward page="login.jsp" />
        <%      } else {
                    Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                    // Validação baseada no Papel do Funcionário, só o Vendedor pode fazer
                    if ((usuario.getPapel() != 1)) {%>
                        <jsp:forward page="index.jsp" />
                    <%}
                }
           }%>
        <div class = "container mt-2">
            
            <jsp:include page="../navbar.jsp" />
            <h1>Cadastro de Vendas</h1>
            <br>
            <form method="POST" action="VendaController" class="needs-validation" novalidate>
                <input type="hidden" class="form-control" id="id" name="id" value="<%= aux.getId() %>">
                <div class="form-row">
                    <div class="col-lg mb-3">
                        <label for="quantidade_venda">Quantidade da Venda</label>
                        <input type="int" class="form-control" id="quantidade_venda" name="quantidade_venda" value="<%= aux.getQuantidade_venda() %>" size="30" maxlength="100" placeholder="Insira a Quantidade" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="data_venda">Data da Venda</label>
                        <input type="string" class="form-control data" id="data_venda" name="data_venda" value="<%= aux.getData_venda() %>" size="30" maxlength="100" placeholder="0000-00-00" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="valor_venda">Valor da Venda</label>
                        <input type="double" class="form-control" id="valor_venda" name="valor_venda" value="<%= aux.getValor_venda() %>" size="30" maxlength="100" placeholder="0.00" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="id_cliente">ID do Cliente</label>
                        <input type="int" class="form-control" id="id_cliente" name="id_cliente" value="<%= aux.getId_cliente() %>" size="30" maxlength="100" placeholder="Insira o ID do Cliente" required>
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
                        <%  Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                    if (usuario.getId() == aux.getId_funcionario() || aux.getId_funcionario() == 0) { %>
                <button class="btn btn-primary" type="submit">Enviar</button>
                <%  }
                %>
                <a href="VendaController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
            </form>
        </div>
    </body>
    <jsp:include page="../scripts.html" />
</html>
