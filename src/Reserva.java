public class Reserva {
    private int id;
    private int idQuarto;
    private int idHospede;
    private int numeroHospedes;
    private int dataInicio;
    private int dataFim;
    private boolean estaAtiva; //ter em conta que o estaAtiva deve ficar 0, caso a data já tenha passado

    //Setters
    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }
    public void setIdHospede(int idHospede) {
        this.idHospede = idHospede;
    }
    public void setNumeroHospede(int numeroHospedes) {
        this.numeroHospedes = numeroHospedes;
    }
    public void setDataInicio(int dataInicio) {
        this.dataInicio = dataInicio;
    }
    public void setDataFim(int dataFim) {
        this.dataFim = dataFim;
    }
    public void setEstaAtiva(boolean estaAtiva) {
        this.estaAtiva = estaAtiva;
    }

    //Getters
    public int getId() {
        return id;
    }
    public int getIdQuarto() {
        return idQuarto;
    }
    public int getIdHospede() {
        return idHospede;
    }
    public int getNumeroHospedes() {
        return numeroHospedes;
    }
    public int getDataInicio() {
        return dataInicio;
    }
    public int getDataFim() {
        return dataFim;
    }
    public boolean getEstaAtiva() {
        return estaAtiva;
    }
}
