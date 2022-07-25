package Model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Application.Produto; //Modelo que representa um registro do BD
import javax.servlet.http.HttpServlet;

public class ProdutoDAO extends HttpServlet {
    
    private Connection conexao;
    
    public ProdutoDAO(){
        try{
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } 
        catch(Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Produto> getListaProduto() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Produto> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("SELECT * FROM produtos");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Produto produto = new Produto();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                produto.setId(rs.getInt("id"));
                produto.setNome_produto(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco_compra(rs.getDouble("preco_compra"));
                produto.setPreco_venda(rs.getDouble("preco_venda"));
                produto.setQuantidade_disponivel(rs.getInt("quantidade_disponível"));
                produto.setLiberado_venda(rs.getString("liberado_venda"));
                produto.setId_categoria(rs.getInt("id_categoria"));
                resultado.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public Produto getProdutoPorID(int id) {
        Produto produto = new Produto();
        try {
            String sql = "SELECT * FROM produtos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                produto.setId(rs.getInt("id"));
                produto.setNome_produto(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco_compra(rs.getDouble("preco_compra"));
                produto.setPreco_venda(rs.getDouble("preco_venda"));
                produto.setQuantidade_disponivel(rs.getInt("quantidade_disponível"));
                produto.setLiberado_venda(rs.getString("liberado_venda"));
                produto.setId_categoria(rs.getInt("id_categoria"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Contatos encontrados no banco de dados.
        return produto;
    }
    
    public boolean gravar(Produto produto) {
        try {
            String sql;
            if (produto.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO produtos (nome_produto,descricao,preco_compra,preco_venda,quantidade_disponível,liberado_venda,id_categoria) VALUES (?,?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE produtos SET nome_produto=?,descricao=?,preco_compra=?,preco_venda=?,quantidade_disponível=?,liberado_venda=?,id_categoria=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, produto.getNome_produto());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco_compra());
            ps.setDouble(4, produto.getPreco_venda());
            ps.setInt(5, produto.getQuantidade_disponivel());
            ps.setString(6, produto.getLiberado_venda());
            ps.setInt(7, produto.getId_categoria());

            if (produto.getId() > 0) {
                ps.setInt(8, produto.getId());
            }

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean incrementarEstoque(int id,int quantidade, double valor){
        try {
            String sql = "UPDATE produtos SET preco_compra = ?, quantidade_disponível = (quantidade_disponível + ?) WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.setInt(2, quantidade);
            ps.setInt(3, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean decrementarEstoque(int id,int quantidade){
        try {
            String sql = "UPDATE produtos SET quantidade_disponível = (quantidade_disponível - ?) WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, quantidade);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int id) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
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
