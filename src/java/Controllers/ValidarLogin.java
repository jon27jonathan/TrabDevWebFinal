package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Application.Funcionario;
import Model.FuncionarioDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class ValidarLogin extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");

        // checando se não está vazio
        if ((cpf_user.isEmpty())||(senha_user.isEmpty())){
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.getFuncionarioPorLoginSenha(cpf_user, senha_user);
        
        if (funcionario.getId() !=0){
            
            Funcionario usuario = new Funcionario();
            usuario.setId(funcionario.getId());
            usuario.setNome(funcionario.getNome());
            usuario.setPapel(funcionario.getPapel());
            
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            // redireciona para área restrita
            RequestDispatcher resposta = request.getRequestDispatcher("/AreaRestrita.jsp");
            resposta.forward(request, response);
            
        } else {
            
            request.setAttribute("mensagem", "Erro no Login");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }

}
