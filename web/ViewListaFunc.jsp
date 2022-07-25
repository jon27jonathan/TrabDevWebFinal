<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,Aplicacao.*" %>
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
                    // Validação baseada no Papel do Funcionário, só o ADM pode fazer
                    if (usuario.getPapel() != 0) {%>
                        <jsp:forward page="index.jsp" />
                    <%}
                }
           }%>
        <div class="container mt-2">
            
            <jsp:include page="navbar.jsp" />
            
            <h1>Lista de Funcionarios</h1>
            <p></p>
            <a href="FuncionarioController?acao=incluir" class="btn btn-outline-primary">Incluir</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome</th>
                            <th scope="col">CPF</th>
                            <th scope="col">Papel</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Funcionario> ListaFuncionario = (ArrayList<Funcionario>) request.getAttribute("meusFuncionarios");
                            for (int i = 0; i < ListaFuncionario.size(); i++) {
                                Funcionario aux = ListaFuncionario.get(i);
                                String link_editar = "FuncionarioController?acao=editar&id="+aux.getId();
                                String link_excluir = "FuncionarioController?acao=excluir&id="+aux.getId();
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getNome()%></td>
                            <td><%=aux.getCpf()%></td>
                            <td><%=aux.getPapel()%></td> 
                            <td><a href="<%=link_excluir%>" class="btn btn-outline-danger float-right">Excluir</a> <a href="<%=link_editar%>" class="btn btn-outline-secondary float-right">Editar</a> 
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
