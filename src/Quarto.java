public class Quarto {
    private int id;
    private int numero;
    private int capacidade;
    private boolean estaOcupado;

    //Métodos - acções -> public
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
}
