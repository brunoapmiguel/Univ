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
        int opcMenu, opcSubMenu; //variaveis de menu

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
            showMainMenu();
            System.out.print("Escolha uma opcao: ");
            opcMenu = teclado.nextInt();
            switch (opcMenu) {
                case 1:
                    showMenuQuartos();
                    opcSubMenu = teclado.nextInt();
                    switch (opcSubMenu) {
                        case 1:
                            listarTodosOsQuartos();
                            break;
                        case 2:
                            listarQuartosLivres();
                             break;
                        case 3:
                            listarQuartosOcupados();
                            break;
                    }
                    break;
                case 2:
                    showMenuHospedes();
                    opcSubMenu = teclado.nextInt();
                    switch (opcSubMenu) {
                        case 1:
                            listarHospedes();
                            break;
                        case 2:
                            procurarHospedePorDocumento();
                            break;
                        case 3:
                            editarHospede();
                            break;
                    }
                    break;
                case 3:
                    showMenuReservas();
                    opcSubMenu = teclado.nextInt();
                    switch (opcSubMenu) {
                        case 1:
                            encontrarQuartoLivrePorCapacidade();
                            break;
                        case 2:
                            selecionarQuartoEspecifico();
                            break;
                        case 3:
                            listarTodasAsReservas();
                            break;
                        case 4:
                            listarReservaPorQuarto();
                            break;
                        case 5:
                            listarReservaPorHospede();
                            break;
                        case 6:
                            editarReserva();
                            break;
                    }
                    break;
            }
        } while (opcMenu != 0);
    }

    //Design do MENU PRINCIPAL
    private static void showMainMenu(){
        System.out.println("== MENU ==");
        System.out.println("1 - Quartos");
        System.out.println("2 - Hospedes");
        System.out.println("3 - Reservas");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    //Design dos SUB-MENUS
    private static void showMenuQuartos(){
        System.out.println("== QUARTOS ==");
        System.out.println("1 - Listar todos os Quartos");
        System.out.println("2 - Listar Quartos livres");
        System.out.println("3 - Listar Quartos ocupados");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }
    private static void showMenuHospedes(){
        System.out.println("== HOSPEDES ==");
        System.out.println("1 - Listar Hospedes");
        System.out.println("2 - Procurar Hospede por documento");
        System.out.println("3 - Editar Hospede");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void showMenuReservas(){
        System.out.println("== RESERVAS ==");
        System.out.println("1 - Encontrar qualquer Quarto livre para determinada capacidade");
        System.out.println("2 - Selecionar Quarto especifico"); //Se indisponivel sugerir automaticamente uma alternativa adequada
        System.out.println("3 - Listar todas as Reservas");
        System.out.println("4 - Listar Reservas por Quarto"); //presentes ou futuras
        System.out.println("5 - Listar Reservas por Hospede"); //presentes ou futuras
        System.out.println("6 - Editar Reserva"); //numero de hospede ou datas e revalidar capacidade e conflitos
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    //Funções de QUARTOS
    private static void listarTodosOsQuartos(){
        System.out.println("RESERVAS -> Listar todos os Quartos");
        break;
    }
    private static void listarQuartosLivres(){
        System.out.println("RESERVAS -> Listar Quartos Livres");
        break;
    }
    private static void listarQuartosOcupados(){
        System.out.println("RESERVAS -> Listar Quartos Ocupados");
        break;
    }

    //Funções de HOSPEDES
    private static void listarHospedes(){
        System.out.println("HOSPEDES -> Listar Hospedes");
        break;
    }
    private static void procurarHospedePorDocumento(){
        System.out.println("HOSPEDES -> Procurar Hospede por documento");
        break;
    }
    private static void editarHospede(){
        System.out.println("HOSPEDES -> Editar Hospede");
        break;
    }

    //Funções de RESERVAS
    private static void encontrarQuartoLivrePorCapacidade(){
        System.out.println("RESERVAS -> Encontrar Quarto Livre por capacidade");
        break;
    }
    private static void selecionarQuartoEspecifico(){
        System.out.println("RESERVAS -> Selecionar Quarto especifico");
        break;
    }
    private static void listarTodasAsReservas(){
        System.out.println("RESERVAS -> Listar todas as Reservas");
        break;
    }
    private static void listarReservaPorQuarto(){
        System.out.println("RESERVAS -> Listar Reserva por Quarto");
        break;
    }
    private static void listarReservaPorHospede(){
        System.out.println("RESERVAS -> Listar Reserva por Hospede");
        break;
    }
    private static void editarReserva(){
        System.out.println("RESERVAS -> Editar Reserva");
        break;
    }
}

