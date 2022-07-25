<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,Aplicacao.*" %>
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

            <h1>Relatorio de Vendas</h1>
            <p></p>
            <form method="GET" action="RelatorioController" id="relatorioVendas" class="form-inline">
                <div class="form-group mx-sm-3 mb-2">
                    <input type="text" class="form-control data" id="data_venda" name="data_venda" value="" size="30" maxlength="100" placeholder="Insira a Data" required>  
                </div>
                <input type="button" class="btn btn-primary mb-2" value="Pesquisar" id="GetData">
            </form>
            <% if (request.getAttribute("totalDiario") != null) { %>
            <h6>Total Diário: R$ <%= request.getAttribute("totalDiario") %></h6>
            <%}%>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Quantidade Vendida</th>
                            <th scope="col">Data</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Produto</th>
                            <th scope="col">Funcionario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Venda> RelatorioVendas = (ArrayList<Venda>) request.getAttribute("minhasVendas");
                            for (int i = 0; i < RelatorioVendas.size(); i++) {
                                Venda aux = RelatorioVendas.get(i);
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getQuantidade_venda()%></td>
                            <td><%=aux.getData_venda()%></td>
                            <td><%=aux.getValor_venda()%></td> 
                            <td><%=aux.getId_cliente()%></td> 
                            <td><%=aux.getId_produto()%></td> 
                            <td><%=aux.getId_funcionario()%></td> 
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
