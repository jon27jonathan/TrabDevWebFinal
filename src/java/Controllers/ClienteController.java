package Controllers;

import Application.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.ClienteDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class ClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClienteDAO clienteDAO = new ClienteDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Cliente> meusClientes;

        Cliente cliente = new Cliente();
        switch (acao) {
            case "mostrar":
                meusClientes = clienteDAO.getListaCliente();
                request.setAttribute("meusClientes", meusClientes);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaClienteView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                //Enviar valores padrão
                cliente.setId(0);
                cliente.setNome("");
                cliente.setCpf("");
                cliente.setEndereco("");
                cliente.setBairro("");
                cliente.setCidade("");
                cliente.setUf("");
                cliente.setCep("");
                cliente.setTelefone("");
                cliente.setEmail("");

                request.setAttribute("cliente", cliente);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/forms/FormCliente.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                cliente = clienteDAO.getClientePorID(id);

                if (cliente.getId() > 0) {
                    request.setAttribute("cliente", cliente);
                    RequestDispatcher editar = request.getRequestDispatcher("/forms/FormCliente.jsp");
                    editar.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar cliente!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Erro.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                clienteDAO.excluir(id);

                meusClientes = clienteDAO.getListaCliente();
                request.setAttribute("meusClientes", meusClientes);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaClienteView.jsp");
                aposexcluir.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem;
        try {
            Cliente cliente = new Cliente();
            //Enviar valores dos atributos
            cliente.setId(Integer.parseInt(request.getParameter("id")));
            cliente.setNome(request.getParameter("nome"));
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setEndereco(request.getParameter("endereco"));
            cliente.setBairro(request.getParameter("bairro"));
            cliente.setCidade(request.getParameter("cidade"));
            cliente.setUf(request.getParameter("uf"));
            cliente.setCep(request.getParameter("cep"));
            cliente.setTelefone(request.getParameter("telefone"));
            cliente.setEmail(request.getParameter("email"));


            ClienteDAO dao = new ClienteDAO();

            if (dao.gravar(cliente)) {
                mensagem = "Cliente gravado com sucesso!";
            } else {
                mensagem = "Erro ao gravar cliente!";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Erro.jsp");
                rd.forward(request, response);
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar cliente!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        }
    }
}