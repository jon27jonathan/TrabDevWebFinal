<%@page import="Aplicacao.Funcionario"%>
<%@page import="Aplicacao.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../header.html" />
        <link rel="stylesheet" href="css/css/BodyPage1.css">
    </head>
    <body>
        
        <% if (session == null){ %>
            <jsp:forward page="login.jsp" />
        <% } else {
                if(session.getAttribute("usuario") == null) {%>
                <jsp:forward page="login.jsp"/>
        <%      } else {
                    Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                    if(usuario.getPapel() != 2){%>
                        <jsp:forward page="index.jsp"/>
        <%          }
                }
        }%>
        
        <div class = "container mt-2">
            
            <jsp:include page="../navbar.jsp" />
            
            <%
               Produto aux = (Produto)request.getAttribute("produto");
            %>
            
            <h1>Cadastro de Produtos</h1>
            <br>
            <form method="POST" action="ProdutoController" class="needs-validation" novalidate>
                <input type="hidden" class="form-control" id="id" name="id" value="<%= aux.getId() %>">
                <div class="form-row">
                    <div class="col-lg mb-3">
                        <label for="nome_produto">Nome do Produto</label>
                        <input type="string" class="form-control" id="nome_produto" name="nome_produto" value="<%= aux.getNome_produto() %>" size="30" maxlength="100" placeholder="Insira o Nome do Produto" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="descricao">Descrição</label>
                        <input type="string" class="form-control" id="descricao" name="descricao" value="<%= aux.getDescricao() %>" size="30" maxlength="100" placeholder="Descricao..." required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="preco_compra">Preço de Compra</label>
                        <input type="double" class="form-control" id="preco_compra" name="preco_compra" value="<%= aux.getPreco_compra() %>" size="30" maxlength="100" placeholder="0.00" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="preco_venda">Preço de Venda</label>
                        <input type="double" class="form-control" id="preco_venda" name="preco_venda" value="<%= aux.getPreco_venda() %>" size="30" maxlength="100" placeholder="0.00" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="quantidade_disponivel">Quant. Disponivel</label>
                        <input type="int" class="form-control" id="quantidade_disponivel" name="quantidade_disponivel" value="<%= aux.getQuantidade_disponivel() %>" size="30" maxlength="100" placeholder="Insira a Quantidade Disponivel" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="liberado_venda">Liberado?</label>
                        <select class="custom-select custom-select-m" id="liberado_venda" name="liberado_venda" value="<%= aux.getLiberado_venda() %>" required>
                            <option value="S">Sim</option>
                            <option value="N">Não</option>
                        </select>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                    <div class="col-lg mb-3">
                        <label for="id_categoria">ID da Categoria</label>
                        <input type="int" class="form-control" id="id_categoria" name="id_categoria" value="<%= aux.getId_categoria() %>" size="30" maxlength="100" placeholder="Insira o ID da Categoria" required>
                        <div class="valid-feedback">
                            Parece Bom!
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary" type="submit">Enviar</button>
                <a href="ProdutoController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
            </form>
        </div>
    </body>
    <jsp:include page="../scripts.html" />
</html>
