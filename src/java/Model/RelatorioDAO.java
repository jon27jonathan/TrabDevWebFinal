package Model;

import Application.Produto;
import Application.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author victo
 */
public class RelatorioDAO extends HttpServlet {

    private Connection conexao;
    
    public RelatorioDAO(){
        try{
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } 
        catch(Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Produto> getEstoqueProduto() {
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
    
    public ArrayList<Venda> getListaVendaPorData(String data) {
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
            //Remover valores que não são da Data Escolhida
            resultado.removeIf(venda -> (!venda.getData_venda().equals(data)));
            
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
}