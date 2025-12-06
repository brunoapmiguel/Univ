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

        do {
            System.out.println("== MENU ==");
            System.out.println("1 - Quartos");
            System.out.println("2 - Hospedes");
            System.out.println("3 - Reservas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcMenu = teclado.nextInt();
            switch (opcMenu) {
                case 1:
                    System.out.println("== QUARTOS ==");
                    System.out.println("1 - Listar todos os Quartos");
                    System.out.println("2 - Listar Quartos livres");
                    System.out.println("3 - Listar Quartos ocupados");
                    System.out.println("0 - Sair");
                    System.out.print("Escolha uma opcao: ");
                    opcMenu = teclado.nextInt();
                    break;
                case 2:
                    System.out.println("== HOSPEDES ==");
                    System.out.println("1 - Listar Hospedes");
                    System.out.println("2 - Procurar Hospede por documento");
                    System.out.println("3 - Editar Hospede");
                    System.out.println("0 - Sair");
                    System.out.print("Escolha uma opcao: ");
                    opcMenu = teclado.nextInt();
                    break;
                case 3:
                    System.out.println("== RESERVAS ==");
                    System.out.println("1 - Encontrar qualquer Quarto livre para determinada capacidade");
                    System.out.println("2 - Selecionar Quarto especifico"); //Se indisponivel sugerir automaticamente uma alternativa adequada
                    System.out.println("3 - Listar todas as Reservas");
                    System.out.println("4 - Listar Reservas por Quarto"); //presentes ou futuras
                    System.out.println("5 - Listar Reservas por Hospede"); //presentes ou futuras
                    System.out.println("6 - Editar Reserva"); //numero de hospede ou datas e revalidar capacidade e conflitos
                    System.out.println("0 - Sair");
                    System.out.print("Escolha uma opcao: ");
                    opcMenu = teclado.nextInt();
                    break;
            }
        } while (opcMenu != 0);
    }



}

