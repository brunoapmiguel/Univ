/*
* Classes
Quarto: id, numero, capacidade, estaOcupado
Hospede: id, nome, documento
Reserva: id, idQuarto, idHospede, numeroHospede, dataInicio, dataFim, ativa
* */
import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in); //Definição de "teclado" como input
        //Declaração de variaveis
        int numeroHospedes, opcMenu;

        //Definição de classes
        class quarto {
            int id, numero, capacidade;
            boolean estaOcupado;
        }
        class hospede{
            int id;
            String nome, documento;
        }
        class reserva{
            int id, idQuarto, idHospede, numeroHospede, dataInicio, dataFim;
            boolean ativa;
        }


    }



}

