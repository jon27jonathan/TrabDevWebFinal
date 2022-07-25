package Controlador;

import Aplicacao.Compra;
import Aplicacao.Produto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.CompraDAO;
import Modelo.ProdutoDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class CompraController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CompraDAO compraDAO = new CompraDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Compra> meusCompras;

        Compra compra = new Compra();
        switch (acao) {
            case "mostrar":
                meusCompras = compraDAO.getListaCompra();
                request.setAttribute("meusCompras", meusCompras);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaCompraView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                //Enviar valores padrão
                compra.setId(0);
                compra.setQuantidade_compra(0);
                compra.setData_compra("");
                compra.setValor_compra(0.00);
                compra.setId_fornecedor(0);
                compra.setId_produto(0);
                compra.setId_funcionario(0);

                request.setAttribute("compra", compra);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/forms/FormCompra.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                compra = compraDAO.getCompraPorID(id);

                if (compra.getId() > 0) {
                    request.setAttribute("compra", compra);
                    RequestDispatcher editar = request.getRequestDispatcher("/forms/FormCompra.jsp");
                    editar.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar compra!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                compraDAO.excluir(id);

                meusCompras = compraDAO.getListaCompra();
                request.setAttribute("meusCompras", meusCompras);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaCompraView.jsp");
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
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Compra compra = new Compra();
            
            //Checando quantia em estoque para a venda
            Produto produto = produtoDAO.getProdutoPorID(Integer.parseInt(request.getParameter("id_produto")));
            int quantidade = Integer.parseInt(request.getParameter("quantidade_compra"));
            double valor = Double.parseDouble(request.getParameter("valor_compra"));
            
            //Enviar valores dos atributos
            compra.setId(Integer.parseInt(request.getParameter("id")));
            compra.setQuantidade_compra(Integer.parseInt(request.getParameter("quantidade_compra")));
            compra.setData_compra(request.getParameter("data_compra"));
            compra.setValor_compra(Double.parseDouble(request.getParameter("valor_compra")));
            compra.setId_fornecedor(Integer.parseInt(request.getParameter("id_fornecedor")));
            compra.setId_produto(Integer.parseInt(request.getParameter("id_produto")));
            compra.setId_funcionario(Integer.parseInt(request.getParameter("id_funcionario")));

            CompraDAO dao = new CompraDAO();

            if (dao.gravar(compra) && produtoDAO.incrementarEstoque(produto.getId(), quantidade, valor)) {
                mensagem = "Compra gravada com sucesso!";
            } else {
                mensagem = "Erro ao gravar compra!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar compra!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        }
    }
}