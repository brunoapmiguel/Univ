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
    public boolean getEstaOcupado(){
        return this.estaOcupado;
    }
}
