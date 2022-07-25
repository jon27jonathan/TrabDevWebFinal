package Modelo;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Aplicacao.Categoria; //Modelo que representa um registro do BD
import javax.servlet.http.HttpServlet;

public class CategoriaDAO extends HttpServlet {
    
    private Connection conexao;
    
    public CategoriaDAO(){
        try{
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } 
        catch(Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Categoria> getListaCategoria() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Categoria> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Categoria categoria = new Categoria();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                categoria.setId(rs.getInt("id"));
                //Pega o conteúdo da coluna "nome" do ResultSet (rs)
                categoria.setNome_categoria(rs.getString("nome_categoria"));
                resultado.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public Categoria getCategoriaPorID(int id) {
        Categoria categoria = new Categoria();
        try {
            String sql = "SELECT * FROM categorias WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categoria.setId(rs.getInt("id"));
                categoria.setNome_categoria(rs.getString("nome_categoria"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Contatos encontrados no banco de dados.
        return categoria;
    }
    
    public boolean gravar(Categoria categoria) {
        try {
            String sql;
            if (categoria.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO categorias (nome_categoria) VALUES (?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE categorias SET nome_categoria=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, categoria.getNome_categoria());

            if (categoria.getId() > 0) {
                ps.setInt(2, categoria.getId());
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
            String sql = "DELETE FROM categorias WHERE id = ?";
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
