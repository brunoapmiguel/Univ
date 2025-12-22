import java.time.LocalDate;
public class Reserva {
    private int id;
    private int idQuarto;
    private int idHospede;
    private int numeroHospedes;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean estaAtiva; //ter em conta que o estaAtiva deve ficar 0, caso a data já tenha passado

    //Constuctor
    public Reserva(int id, int idQuarto, int idHospede, int numeroHospedes, LocalDate dataInicio, LocalDate dataFim, boolean estaAtiva) {
        this.id = id;
        this.idQuarto = idQuarto;
        this.idHospede = idHospede;
        this.numeroHospedes = numeroHospedes;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.estaAtiva = estaAtiva;
    }
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
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public void setDataFim(LocalDate dataFim) {
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
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public boolean getEstaAtiva() {
        return estaAtiva;
    }
    @Override
    public String toString(){
        return String.format("Reserva{id="+id+", idQuarto="+idQuarto+", idHospede="+idHospede+", numeroHospedes="+numeroHospedes+", dataInicio="+dataInicio+", dataFim="+dataFim+", estaAtiva="+estaAtiva+"}");
    }
}
