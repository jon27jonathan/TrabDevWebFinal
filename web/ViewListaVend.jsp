<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Aplicacao.*" %>
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
        <%      } else {
                    Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                    // Validação baseada no Papel do Funcionário, só o Vendedor pode fazer
                    if (usuario.getPapel() != 1) {%>
                        <jsp:forward page="index.jsp" />
                    <%}
                }
           }%>
        <div class="container mt-2">
            
            <jsp:include page="navbar.jsp" />
            
            <h1>Lista de Vendas</h1>
            <p></p>
            <a href="VendaController?acao=incluir" class="btn btn-outline-primary">Incluir</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Quantidade Venda</th>
                            <th scope="col">Data Venda</th>
                            <th scope="col">Valor Venda</th>
                            <th scope="col">Id Cliente</th>
                            <th scope="col">Id Produto</th>
                            <th scope="col">Id Funcionario</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                            ArrayList<Venda> ListaVenda = (ArrayList<Venda>) request.getAttribute("meusVendas");
                            for (int i = 0; i < ListaVenda.size(); i++) {
                                Venda aux = ListaVenda.get(i);
                                String link_editar = "VendaController?acao=editar&id="+aux.getId();
                                String link_excluir = "VendaController?acao=excluir&id="+aux.getId();
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getQuantidade_venda()%></td>
                            <td><%=aux.getData_venda()%></td>
                            <td><%=aux.getValor_venda()%></td> 
                            <td><%=aux.getId_cliente()%></td> 
                            <td><%=aux.getId_produto()%></td> 
                            <td><%=aux.getId_funcionario()%></td> 
                            <% if (usuario.getId() == aux.getId_funcionario()) { %>
                            <td><a href="<%=link_excluir%>" class="btn btn-outline-danger float-right">Excluir</a> <a href="<%=link_editar%>" class="btn btn-outline-secondary float-right">Editar</a> 
                            </td> 
                        </tr>
                        <%
                            }
                        }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <jsp:include page="scripts.html" /> 
</html>
