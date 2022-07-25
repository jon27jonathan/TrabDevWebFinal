<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Aplicacao.*" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.html" />
        <link rel="stylesheet" href="css/css/BodyPage1.css">
    </head>
    <body>
        <div class="container mt-2">
            
            <jsp:include page="navbar.jsp" />
            
            <h1>Lista de Produtos</h1>
            <p></p><p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome do Produto</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Preço Compra</th>
                            <th scope="col">Preço Venda</th>
                            <th scope="col">Quantidade Disponivel</th>
                            <th scope="col">Liberado?</th>
                            <th scope="col">Id Categoria</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Produto> ListaProduto = (ArrayList<Produto>) request.getAttribute("meusProdutos");
                            for (int i = 0; i < ListaProduto.size(); i++) {
                                Produto aux = ListaProduto.get(i);
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getNome_produto()%></td>
                            <td><%=aux.getDescricao()%></td>
                            <td><%=aux.getPreco_compra()%></td> 
                            <td><%=aux.getPreco_venda()%></td> 
                            <td><%=aux.getQuantidade_disponivel()%></td> 
                            <td><%=aux.getLiberado_venda()%></td> 
                            <td><%=aux.getId_categoria()%></td> 
                            </td> 
                           
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <jsp:include page="scripts.html" /> 
</html>