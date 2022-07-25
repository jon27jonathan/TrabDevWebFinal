package Controllers;

import Application.Produto;
import Model.ProdutoDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> meusProdutos;
        meusProdutos = produtoDAO.getListaProduto();
        meusProdutos.removeIf(produto -> (!produto.getLiberado_venda().equals("S")));
        request.setAttribute("meusProdutosDisponiveis", meusProdutos);
        RequestDispatcher cliente = getServletContext().getRequestDispatcher("/clienteProdutos.jsp");
        cliente.forward(request, response);
    }

}
