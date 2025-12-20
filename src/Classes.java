public class Classes {
    //Definição de classes
    class Quarto {
        int id, numero, capacidade;
        boolean estaOcupado;
    }

    class Hospede {
        int id;
        String nome, documento;
    }

    class Reserva {
        int id, idQuarto, idHospede, numeroHospede, dataInicio, dataFim;
        boolean ativa;
    }



}
