<%@page import="Aplicacao.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.html" />
        <link rel="stylesheet" href="css/css/BodyPage1.css">
    </head>
    <body>
        <% if (session.getAttribute("usuario") != null) { %>
            <jsp:forward page="AreaRestrita.jsp" />
        <%}%>
        <div class="container mt-2">
            
            <jsp:include page="navbar.jsp" />
            
            <h1>Trabalho de Desenvolvimento Web</h1>
            <h2>Victor Verdan & João Pedro</h2>
            
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Nome do Produto</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Preço</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Produto> ListaProduto = (ArrayList<Produto>) request.getAttribute("meusProdutosDisponiveis");
                            for (int i = 0; i < ListaProduto.size(); i++) {
                                Produto aux = ListaProduto.get(i);
                        %>
                        <tr>
                            <td><%=aux.getNome_produto()%></td>
                            <td><%=aux.getDescricao()%></td> 
                            <td><%=aux.getPreco_venda()%></td>                           
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
        </div>
    </body>
    <jsp:include page="scripts.html" /> 
</html>
