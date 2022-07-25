package Controllers;

import Application.Fornecedor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.FornecedorDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class FornecedorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Fornecedor> meusFornecedores;

        Fornecedor fornecedor = new Fornecedor();
        switch (acao) {
            case "mostrar":
                meusFornecedores = fornecedorDAO.getListaFornecedor();
                request.setAttribute("meusFornecedores", meusFornecedores);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaFornecedorView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                //Enviar valores padrão
                fornecedor.setId(0);
                fornecedor.setRazao_social("");
                fornecedor.setCnpj("");
                fornecedor.setEndereco("");
                fornecedor.setBairro("");
                fornecedor.setCidade("");
                fornecedor.setUf("");
                fornecedor.setCep("");
                fornecedor.setTelefone("");
                fornecedor.setEmail("");

                request.setAttribute("fornecedor", fornecedor);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/forms/FormFornecedor.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                fornecedor = fornecedorDAO.getFornecedorPorID(id);

                if (fornecedor.getId() > 0) {
                    request.setAttribute("fornecedor", fornecedor);
                    RequestDispatcher editar = request.getRequestDispatcher("/forms/FormFornecedor.jsp");
                    editar.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar fornecedor!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Erro.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                fornecedorDAO.excluir(id);

                meusFornecedores = fornecedorDAO.getListaFornecedor();
                request.setAttribute("meusFornecedores", meusFornecedores);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaFornecedorView.jsp");
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
            Fornecedor fornecedor = new Fornecedor();
            //Enviar valores dos atributos
            fornecedor.setId(Integer.parseInt(request.getParameter("id")));
            fornecedor.setRazao_social(request.getParameter("razao_social"));
            fornecedor.setCnpj(request.getParameter("cnpj"));
            fornecedor.setEndereco(request.getParameter("endereco"));
            fornecedor.setBairro(request.getParameter("bairro"));
            fornecedor.setCidade(request.getParameter("cidade"));
            fornecedor.setUf(request.getParameter("uf"));
            fornecedor.setCep(request.getParameter("cep"));
            fornecedor.setTelefone(request.getParameter("telefone"));
            fornecedor.setEmail(request.getParameter("email"));


            FornecedorDAO dao = new FornecedorDAO();

            if (dao.gravar(fornecedor)) {
                mensagem = "Fornecedor gravado com sucesso!";
            } else {
                mensagem = "Erro ao gravar fornecedor!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar fornecedor!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
}