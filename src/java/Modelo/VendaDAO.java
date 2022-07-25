package Modelo;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Aplicacao.Venda; //Modelo que representa um registro do BD
import javax.servlet.http.HttpServlet;

public class VendaDAO extends HttpServlet {
    
    private Connection conexao;
    
    public VendaDAO(){
        try{
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } 
        catch(Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Venda> getListaVenda() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Venda> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("SELECT * FROM vendas");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Venda venda = new Venda();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                venda.setId(rs.getInt("id"));
                venda.setQuantidade_venda(rs.getInt("quantidade_venda"));
                venda.setData_venda(rs.getString("data_venda"));
                venda.setValor_venda(rs.getDouble("valor_venda"));
                venda.setId_cliente(rs.getInt("id_cliente"));
                venda.setId_produto(rs.getInt("id_produto"));
                venda.setId_funcionario(rs.getInt("id_funcionario"));
                resultado.add(venda);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public Venda getVendaPorID(int id) {
        Venda venda = new Venda();
        try {
            String sql = "SELECT * FROM vendas WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venda.setId(rs.getInt("id"));
                venda.setQuantidade_venda(rs.getInt("quantidade_venda"));
                venda.setData_venda(rs.getString("data_venda"));
                venda.setValor_venda(rs.getDouble("valor_venda"));
                venda.setId_cliente(rs.getInt("id_cliente"));
                venda.setId_produto(rs.getInt("id_produto"));
                venda.setId_funcionario(rs.getInt("id_funcionario"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Contatos encontrados no banco de dados.
        return venda;
    }
    
    public boolean gravar(Venda venda) {
        try {
            String sql;
            if (venda.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO vendas (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_funcionario) VALUES (?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE vendas SET quantidade_venda=?, data_venda=?, valor_venda=?, id_cliente=?, id_produto=?, id_funcionario=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, venda.getQuantidade_venda());
            ps.setString(2, venda.getData_venda());
            ps.setDouble(3, venda.getValor_venda());
            ps.setInt(4, venda.getId_cliente());
            ps.setInt(5, venda.getId_produto());
            ps.setInt(6, venda.getId_funcionario());

            if (venda.getId() > 0) {
                ps.setInt(7, venda.getId());
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
            String sql = "DELETE FROM vendas WHERE id = ?";
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
