package Controlador;

import Aplicacao.Funcionario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.FuncionarioDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class FuncionarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Funcionario> meusFuncionarios;

        Funcionario funcionario = new Funcionario();
        switch (acao) {
            case "mostrar":
                meusFuncionarios = funcionarioDAO.getListaFuncionario();
                request.setAttribute("meusFuncionarios", meusFuncionarios);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ViewListaFunc.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                //Enviar valores padrão
                funcionario.setId(0);
                funcionario.setNome("");
                funcionario.setCpf("");
                funcionario.setSenha("");
                funcionario.setPapel(0);

                request.setAttribute("funcionario", funcionario);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/forms/FormFuncionario.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                funcionario = funcionarioDAO.getFuncionarioPorID(id);

                if (funcionario.getId() > 0) {
                    request.setAttribute("funcionario", funcionario);
                    RequestDispatcher editar = request.getRequestDispatcher("/forms/FormFuncionario.jsp");
                    editar.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar funcionario!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                funcionarioDAO.excluir(id);

                meusFuncionarios = funcionarioDAO.getListaFuncionario();
                request.setAttribute("meusFuncionarios", meusFuncionarios);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ViewListaFunc.jsp");
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
            Funcionario funcionario = new Funcionario();
            //Enviar valores dos atributos
            funcionario.setId(Integer.parseInt(request.getParameter("id")));
            funcionario.setNome(request.getParameter("nome"));
            funcionario.setCpf(request.getParameter("cpf"));
            funcionario.setSenha(request.getParameter("senha"));
            funcionario.setPapel(Integer.parseInt(request.getParameter("papel")));

            FuncionarioDAO dao = new FuncionarioDAO();

            if (dao.gravar(funcionario)) {
                mensagem = "Funcionario gravado com sucesso!";
            } else {
                mensagem = "Erro ao gravar funcionario!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar funcionario!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        }
    }
}