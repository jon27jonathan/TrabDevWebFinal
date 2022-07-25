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
                    // Validação baseada no Papel do Funcionário, só o Comprador pode fazer
                    if (usuario.getPapel() != 2) {%>
                        <jsp:forward page="index.jsp" />
                    <%}
                }
           }%>
        <div class="container mt-2">
            
            <jsp:include page="navbar.jsp" />
            
            <h1>Lista de Fornecedores</h1>
            <p></p>
            <a href="FornecedorController?acao=incluir" class="btn btn-outline-primary">Incluir</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Razão Social</th>
                            <th scope="col">CNPJ</th>
                            <th scope="col">Endereco</th>
                            <th scope="col">Bairro</th>
                            <th scope="col">Cidade</th>
                            <th scope="col">Uf</th>
                            <th scope="col">Cep</th>
                            <th scope="col">Telefone</th>
                            <th scope="col">Email</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Fornecedor> ListaFornecedor = (ArrayList<Fornecedor>) request.getAttribute("meusFornecedores");
                            for (int i = 0; i < ListaFornecedor.size(); i++) {
                                Fornecedor aux = ListaFornecedor.get(i);
                                String link_editar = "FornecedorController?acao=editar&id="+aux.getId();
                                String link_excluir = "FornecedorController?acao=excluir&id="+aux.getId();
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getRazao_social()%></td>
                            <td><%=aux.getCnpj()%></td>
                            <td><%=aux.getEndereco()%></td> 
                            <td><%=aux.getBairro()%></td> 
                            <td><%=aux.getCidade()%></td> 
                            <td><%=aux.getUf()%></td> 
                            <td><%=aux.getCep()%></td> 
                            <td><%=aux.getTelefone()%></td> 
                            <td><%=aux.getEmail()%></td> 
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
