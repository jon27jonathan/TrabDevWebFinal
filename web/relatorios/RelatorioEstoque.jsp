<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,Application.*" %>
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
        <div class="container mt-2">
            
            <jsp:include page="../navbar.jsp" />
            
            <h1>Relatorio de Estoque</h1>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Quantidade Disponivel</th>
                            <th scope="col">Liberado?</th>
                            <th scope="col">Id Categoria</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Produto> RelatorioEstoque = (ArrayList<Produto>) request.getAttribute("meuEstoque");
                            for (int i = 0; i < RelatorioEstoque.size(); i++) {
                                Produto aux = RelatorioEstoque.get(i);
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getNome_produto()%></td>
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
    <jsp:include page="../scripts.html" /> 
</html>
