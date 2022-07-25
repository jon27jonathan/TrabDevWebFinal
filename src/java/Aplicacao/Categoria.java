package Aplicacao;

public class Categoria {
    
    private int id;
    private String nome_categoria;
    
    public Categoria(int id, String nome_categoria) {
        this.id = id;
        this.nome_categoria = nome_categoria;
    }
    
    public Categoria() {    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
    
}
