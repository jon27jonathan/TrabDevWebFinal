<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Aplicacao.*" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.html" />
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
        
        <div class="container mt-2">
            
            <jsp:include page="navbar.jsp" />
            
            <h1>Lista de Compras</h1>
            <p></p>
            <a href="CompraController?acao=incluir" class="btn btn-outline-primary">Incluir</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Quantidade_compra</th>
                            <th scope="col">Data_compra</th>
                            <th scope="col">Valor_compra</th>
                            <th scope="col">Id_fornecedor</th>
                            <th scope="col">Id_produto</th>
                            <th scope="col">Id_funcionario</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Compra> ListaCompra = (ArrayList<Compra>) request.getAttribute("meusCompras");
                            Funcionario usuario = (Funcionario) session.getAttribute("usuario");
                            for (int i = 0; i < ListaCompra.size(); i++) {
                                Compra aux = ListaCompra.get(i);
                                String link_editar = "CompraController?acao=editar&id="+aux.getId();
                                String link_excluir = "CompraController?acao=excluir&id="+aux.getId();
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getQuantidade_compra()%></td>
                            <td><%=aux.getData_compra()%></td>
                            <td><%=aux.getValor_compra()%></td> 
                            <td><%=aux.getId_fornecedor()%></td> 
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
