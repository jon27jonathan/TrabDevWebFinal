package Controllers;

import Application.Produto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.ProdutoDAOCli;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class ProdutoControllerCli extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProdutoDAOCli produtoDAO1 = new ProdutoDAOCli();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Produto> meusProdutos;

        Produto produto = new Produto();
        switch (acao) {
            case "mostrar":
                meusProdutos = produtoDAO1.getListaProduto();
                request.setAttribute("meusProdutos", meusProdutos);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/viewListaProdCli.jsp");
                mostrar.forward(request, response);
                break;
        }
    }
}
