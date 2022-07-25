package Controlador;

import Aplicacao.Produto;
import Aplicacao.Venda;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.RelatorioDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class RelatorioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RelatorioDAO relatorioDAO = new RelatorioDAO();
        String acao = (String) request.getParameter("acao");
        String date = (String) request.getParameter("date");

        switch (acao) {
            case "vendas":
                ArrayList<Venda> minhasVendas;
                minhasVendas = relatorioDAO.getListaVenda();
                request.setAttribute("minhasVendas", minhasVendas);
                RequestDispatcher vendas = getServletContext().getRequestDispatcher("/relatorios/RelatorioVendas.jsp");
                vendas.forward(request, response);
                break;
            case "estoque":
                ArrayList<Produto> meuEstoque;
                meuEstoque = relatorioDAO.getEstoqueProduto();
                request.setAttribute("meuEstoque", meuEstoque);
                RequestDispatcher estoque = getServletContext().getRequestDispatcher("/relatorios/RelatorioEstoque.jsp");
                estoque.forward(request, response);
                break;
            case "data":
                ArrayList<Venda> minhasVendasData;
                minhasVendasData = relatorioDAO.getListaVendaPorData(date);
                double total = 0.0;
                for (int i = 0; i < minhasVendasData.size(); i++) {
                    total = total + minhasVendasData.get(i).getValor_venda();
                }
                request.setAttribute("minhasVendas", minhasVendasData);
                request.setAttribute("totalDiario", total);
                RequestDispatcher data = getServletContext().getRequestDispatcher("/relatorios/RelatorioVendas.jsp");
                data.forward(request, response);
                break;
        }
    }

}
