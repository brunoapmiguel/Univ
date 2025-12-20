public class Hospede {
    private int id;
    private String nome;
    private String documento;

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDocumento(String documento){
        this.documento = documento;
    }

    //Getters
    public int getId(){
        return id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getDocumento(){
        return this.documento;
    }
}
