package Controlador;

import Aplicacao.Produto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.ProdutoDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Produto> meusProdutos;

        Produto produto = new Produto();
        switch (acao) {
            case "mostrar":
                meusProdutos = produtoDAO.getListaProduto();
                request.setAttribute("meusProdutos", meusProdutos);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaProdutoView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                //Enviar valores padrão
                produto.setId(0);
                produto.setNome_produto("");
                produto.setDescricao("");
                produto.setPreco_compra(0.00);
                produto.setPreco_venda(0.00);
                produto.setQuantidade_disponivel(0);
                produto.setLiberado_venda("");
                produto.setId_categoria(0);

                request.setAttribute("produto", produto);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/forms/FormProduto.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                produto = produtoDAO.getProdutoPorID(id);

                if (produto.getId() > 0) {
                    request.setAttribute("produto", produto);
                    RequestDispatcher editar = request.getRequestDispatcher("/forms/FormProduto.jsp");
                    editar.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar produto!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                produtoDAO.excluir(id);

                meusProdutos = produtoDAO.getListaProduto();
                request.setAttribute("meusProdutos", meusProdutos);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaProdutoView.jsp");
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
            Produto produto = new Produto();
            //Enviar valores dos atributos
            produto.setId(Integer.parseInt(request.getParameter("id")));
            produto.setNome_produto(request.getParameter("nome_produto"));
            produto.setDescricao(request.getParameter("descricao"));
            produto.setPreco_compra(Double.parseDouble(request.getParameter("preco_compra")));
            produto.setPreco_venda(Double.parseDouble(request.getParameter("preco_venda")));
            produto.setQuantidade_disponivel(Integer.parseInt(request.getParameter("quantidade_disponivel")));
            produto.setLiberado_venda(request.getParameter("liberado_venda"));
            produto.setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));

            ProdutoDAO dao = new ProdutoDAO();

            if (dao.gravar(produto)) {
                mensagem = "Produto gravado com sucesso!";
            } else {
                mensagem = "Erro ao gravar produto!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar produto!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        }
    }
}
