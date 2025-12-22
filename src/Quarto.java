public class Quarto {
    private int id;
    private int numero;
    private int capacidade;
    private boolean estaOcupado;

    //Constructor
    public Quarto(int id, int numero, int capacidade, boolean estaOcupado) {
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
        this.estaOcupado = estaOcupado;
    }

    //Setters
    public void setEstaOcupado(boolean estaOcupado){
        this.estaOcupado = estaOcupado;
    }

    //Getters
    public int getIdQuarto() {
        return this.id;
    }
    public int getNumero() {
        return this.numero;
    }
    public int getCapacidade() {
        return this.capacidade;
    }
    public boolean getEstaOcupado(){
        return this.estaOcupado;
    }
    @Override
    public String toString(){
        return String.format("Quarto{id="+id+", numero="+numero+", capacidade="+capacidade+", estaOcupado="+estaOcupado+"}");
    }
}
