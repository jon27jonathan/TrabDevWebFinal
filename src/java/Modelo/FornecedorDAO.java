package Modelo;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Aplicacao.Fornecedor; //Modelo que representa um registro do BD
import javax.servlet.http.HttpServlet;

public class FornecedorDAO extends HttpServlet {
    
    private Connection conexao;
    
    public FornecedorDAO(){
        try{
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        } 
        catch(Exception e) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Fornecedor> getListaFornecedor() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Fornecedor> resultado = new ArrayList<>();
        try {
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("SELECT * FROM fornecedores");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while (rs.next()) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Fornecedor fornecedor = new Fornecedor();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                fornecedor.setId(rs.getInt("id"));
                //Pega o conteúdo da coluna "razao_social" do ResultSet (rs)
                fornecedor.setRazao_social(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                resultado.add(fornecedor);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }

        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public Fornecedor getFornecedorPorID(int id) {
        Fornecedor fornecedor = new Fornecedor();
        try {
            String sql = "SELECT * FROM fornecedores WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setRazao_social(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Contatos encontrados no banco de dados.
        return fornecedor;
    }
    
    public boolean gravar(Fornecedor fornecedor) {
        try {
            String sql;
            if (fornecedor.getId() == 0) {
                // Realizar uma inclusão
                sql = "INSERT INTO fornecedores (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE fornecedores SET razao_social=?, cnpj=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
            }

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, fornecedor.getRazao_social());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getBairro());
            ps.setString(5, fornecedor.getCidade());
            ps.setString(6, fornecedor.getUf());
            ps.setString(7, fornecedor.getCep());
            ps.setString(8, fornecedor.getTelefone());
            ps.setString(9, fornecedor.getEmail());

            if (fornecedor.getId() > 0) {
                ps.setInt(10, fornecedor.getId());
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
            String sql = "DELETE FROM fornecedores WHERE id = ?";
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
