package Controlador;

import Aplicacao.Categoria;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.CategoriaDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class CategoriaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Categoria> meusCategorias;

        Categoria categoria = new Categoria();
        switch (acao) {
            case "mostrar":
                meusCategorias = categoriaDAO.getListaCategoria();
                request.setAttribute("meusCategorias", meusCategorias);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaCategoriaView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                //Enviar valores padrão
                categoria.setId(0);
                categoria.setNome_categoria("");

                request.setAttribute("categoria", categoria);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/forms/FormCategoria.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                categoria = categoriaDAO.getCategoriaPorID(id);

                if (categoria.getId() > 0) {
                    request.setAttribute("categoria", categoria);
                    RequestDispatcher editar = request.getRequestDispatcher("/forms/FormCategoria.jsp");
                    editar.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar categoria!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                categoriaDAO.excluir(id);

                meusCategorias = categoriaDAO.getListaCategoria();
                request.setAttribute("meusCategorias", meusCategorias);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaCategoriaView.jsp");
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
            Categoria categoria = new Categoria();
            //Enviar valores dos atributos
            categoria.setId(Integer.parseInt(request.getParameter("id")));
            categoria.setNome_categoria(request.getParameter("nome_categoria"));

            CategoriaDAO dao = new CategoriaDAO();

            if (dao.gravar(categoria)) {
                mensagem = "Categoria gravada com sucesso!";
            } else {
                mensagem = "Erro ao gravar categoria!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar categoria!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        }
    }
}