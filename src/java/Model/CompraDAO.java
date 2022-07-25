package Model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Application.Compra; //Modelo que representa um registro do BD
import javax.servlet.http.HttpServlet;

public class CompraDAO extends HttpServlet {
    
    private Connection conexao;
    
    public CompraDAO(){
        try{
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } 
        catch(Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Compra> getListaCompra() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Compra> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("SELECT * FROM compras");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Compra compra = new Compra();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                compra.setId(rs.getInt("id"));
                compra.setQuantidade_compra(rs.getInt("quantidade_compra"));
                compra.setData_compra(rs.getString("data_compra"));
                compra.setValor_compra(rs.getDouble("valor_compra"));
                compra.setId_fornecedor(rs.getInt("id_fornecedor"));
                compra.setId_produto(rs.getInt("id_produto"));
                compra.setId_funcionario(rs.getInt("id_funcionario"));
                resultado.add(compra);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public Compra getCompraPorID(int id) {
        Compra compra = new Compra();
        try {
            String sql = "SELECT * FROM compras WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                compra.setId(rs.getInt("id"));
                compra.setQuantidade_compra(rs.getInt("quantidade_compra"));
                compra.setData_compra(rs.getString("data_compra"));
                compra.setValor_compra(rs.getDouble("valor_compra"));
                compra.setId_fornecedor(rs.getInt("id_fornecedor"));
                compra.setId_produto(rs.getInt("id_produto"));
                compra.setId_funcionario(rs.getInt("id_funcionario"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Contatos encontrados no banco de dados.
        return compra;
    }
    
    public boolean gravar(Compra compra) {
        try {
            String sql;
            if (compra.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario) VALUES (?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE compras SET quantidade_compra=?, data_compra=?, valor_compra=?, id_fornecedor=?, id_produto=?, id_funcionario=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, compra.getQuantidade_compra());
            ps.setString(2, compra.getData_compra());
            ps.setDouble(3, compra.getValor_compra());
            ps.setInt(4, compra.getId_fornecedor());
            ps.setInt(5, compra.getId_produto());
            ps.setInt(6, compra.getId_funcionario());

            if (compra.getId() > 0) {
                ps.setInt(7, compra.getId());
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
            String sql = "DELETE FROM compras WHERE id = ?";
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
