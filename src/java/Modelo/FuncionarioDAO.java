package Modelo;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Aplicacao.Funcionario; //Modelo que representa um registro do BD
import javax.servlet.http.HttpServlet;

public class FuncionarioDAO extends HttpServlet {
    
    private Connection conexao;
    
    public FuncionarioDAO(){
        try{
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } 
        catch(Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Funcionario> getListaFuncionario() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Funcionario> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("SELECT * FROM funcionarios");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Funcionario funcionario = new Funcionario();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                funcionario.setId(rs.getInt("id"));
                //Pega o conteúdo da coluna "nome" do ResultSet (rs)
                funcionario.setNome(rs.getString("nome"));
                //Pega o conteúdo da coluna "cpf" do ResultSet (rs)
                funcionario.setCpf(rs.getString("cpf"));
                //Pega o conteúdo da coluna "papel" do ResultSet (rs)
                funcionario.setPapel(rs.getInt("papel"));
                //Adiciona o objeto criado na ArrayList resultado
                resultado.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public Funcionario getFuncionarioPorID(int id) {
        Funcionario funcionario = new Funcionario();
        try {
            String sql = "SELECT * FROM funcionarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setPapel(rs.getInt("papel"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Contatos encontrados no banco de dados.
        return funcionario;
    }
    
    public Funcionario getFuncionarioPorLoginSenha(String cpf, String senha) {
        Funcionario funcionario = new Funcionario();
        try {
            funcionario.setId(0);
            
            String sql = "SELECT * FROM funcionarios WHERE cpf = ? and senha = ? limit 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setPapel(rs.getInt("papel"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return funcionario;
    }
    
    public boolean gravar(Funcionario funcionario) {
        try {
            String sql;
            if (funcionario.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO funcionarios (nome, cpf, senha, papel) VALUES (?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE funcionarios SET nome=?, cpf=?, senha=?, papel=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getSenha());
            ps.setInt(4, funcionario.getPapel());    

            if (funcionario.getId() > 0) {
                ps.setInt(5, funcionario.getId());
            }

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM funcionarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
}
