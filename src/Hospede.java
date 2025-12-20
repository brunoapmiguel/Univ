public class Hospede {
    private int id;
    private String nome;
    private String documento;

    //Setters
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDocumento(String documento){
        this.documento = documento;
    }

    //Getters
    public String getNome(){
        return this.nome;
    }
    public String getDocumento(){
        return this.documento;
    }
}
