package Modelo;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Aplicacao.Produto; //Modelo que representa um registro do BD
import javax.servlet.http.HttpServlet;

public class ProdutoDAOCli extends HttpServlet {
    
    private Connection conexao;
    
    public ProdutoDAOCli(){
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
                
                String compara = "S";
                String valor = rs.getString("liberado_venda");
                if(rs.getInt("quantidade_disponível") > 0 && (valor.equals(compara)) ){
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
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
}