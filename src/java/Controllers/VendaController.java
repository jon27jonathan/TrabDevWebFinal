package Controllers;

import Application.Produto;
import Application.Venda;
import Model.ProdutoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.VendaDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class VendaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        VendaDAO vendaDAO = new VendaDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Venda> meusVendas;

        Venda venda = new Venda();
        switch (acao) {
            case "mostrar":
                meusVendas = vendaDAO.getListaVenda();
                request.setAttribute("meusVendas", meusVendas);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaVendaView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                //Enviar valores padrão
                venda.setId(0);
                venda.setQuantidade_venda(0);
                venda.setQuantidade_venda(0);
                venda.setData_venda("");
                venda.setValor_venda(0.00);
                venda.setId_cliente(0);
                venda.setId_produto(0);
                venda.setId_funcionario(0);

                request.setAttribute("venda", venda);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/forms/FormVenda.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                venda = vendaDAO.getVendaPorID(id);

                if (venda.getId() > 0) {
                    request.setAttribute("venda", venda);
                    RequestDispatcher editar = request.getRequestDispatcher("/forms/FormVenda.jsp");
                    editar.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar venda!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                vendaDAO.excluir(id);

                meusVendas = vendaDAO.getListaVenda();
                request.setAttribute("meusVendas", meusVendas);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaVendaView.jsp");
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
            Venda venda = new Venda();
            
            //Checando quantia em estoque para a venda
            Produto produto = produtoDAO.getProdutoPorID(Integer.parseInt(request.getParameter("id_produto")));
            int quantidade = Integer.parseInt(request.getParameter("quantidade_venda"));

            if ("S".equals(produto.getLiberado_venda()) && quantidade <= produto.getQuantidade_disponivel()) {
                //Enviar valores dos atributos
                venda.setId(Integer.parseInt(request.getParameter("id")));
                venda.setQuantidade_venda(Integer.parseInt(request.getParameter("quantidade_venda")));
                venda.setData_venda(request.getParameter("data_venda"));
                venda.setValor_venda(Double.parseDouble(request.getParameter("valor_venda")));
                venda.setId_cliente(Integer.parseInt(request.getParameter("id_cliente")));
                venda.setId_produto(Integer.parseInt(request.getParameter("id_produto")));
                venda.setId_funcionario(Integer.parseInt(request.getParameter("id_funcionario")));

                VendaDAO dao = new VendaDAO();

                if (dao.gravar(venda) && produtoDAO.decrementarEstoque(produto.getId(), quantidade)) {
                    mensagem = "Venda gravada com sucesso!";
                } else {
                    mensagem = "Erro ao gravar venda!";
                }
            } else {
                mensagem = "Quantidade Insuficiante em Estoque ou Não Liberado para Venda!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar venda!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        }
    }
}
