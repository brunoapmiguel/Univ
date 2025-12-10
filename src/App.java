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
        String numDocumentoHospede;
        int numPessoas, numQuarto, numReserva;

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
            opcMenu = teclado.nextInt();
            switch (opcMenu) {
                case 1:
                    showMenuQuartos();
                    opcSubMenu = teclado.nextInt();
                    switch (opcSubMenu) {
                        case 1:
                            System.out.println("RESERVAS -> Listar todos os Quartos");
                            listarTodosOsQuartos();
                            break;
                        case 2:
                            System.out.println("RESERVAS -> Listar Quartos Livres");
                            listarQuartosLivres();
                            break;
                        case 3:
                            System.out.println("RESERVAS -> Listar Quartos Ocupados");
                            listarQuartosOcupados();
                            break;
                    }
                    break;
                case 2:
                    showMenuHospedes();
                    opcSubMenu = teclado.nextInt();
                    switch (opcSubMenu) {
                        case 1:
                            System.out.println("HOSPEDES -> Listar Hospedes");
                            listarHospedes();
                            break;
                        case 2:
                            System.out.println("HOSPEDES -> Procurar Hospede por documento");
                            System.out.print("Insira o número do documento (CC) do Hospede: ");
                            numDocumentoHospede = teclado.nextLine();
                            procurarHospedePorDocumento();
                            break;
                        case 3:
                            System.out.println("HOSPEDES -> Editar Hospede");
                            System.out.print("Insira o número do documento (CC) do Hospede: ");
                            numDocumentoHospede = teclado.nextLine();
                            editarHospede();
                            break;
                    }
                    break;
                case 3:
                    showMenuReservas();
                    opcSubMenu = teclado.nextInt();
                    switch (opcSubMenu) {
                        case 1:
                            System.out.println("RESERVAS -> Encontrar Quarto Livre por capacidade");
                            System.out.print("Insira o número de pessoas: ");
                            numPessoas = teclado.nextInt();
                            encontrarQuartoLivrePorCapacidade();
                            break;
                        case 2:
                            System.out.println("RESERVAS -> Selecionar Quarto especifico");
                            System.out.print("Insira o número do Quarto: ");
                            numQuarto = teclado.nextInt();
                            selecionarQuartoEspecifico();
                            break;
                        case 3:
                            System.out.println("RESERVAS -> Listar todas as Reservas");
                            listarTodasAsReservas();
                            break;
                        case 4:
                            System.out.println("RESERVAS -> Listar Reserva por Quarto");
                            System.out.print("Insira o número do Quarto: ");
                            numQuarto = teclado.nextInt();
                            listarReservaPorQuarto();
                            break;
                        case 5:
                            System.out.println("RESERVAS -> Listar Reserva por Hospede");
                            System.out.print("Insira o número do documento (CC) do Hospede: ");
                            numDocumentoHospede = teclado.nextLine();
                            listarReservaPorHospede();
                            break;
                        case 6:
                            System.out.println("RESERVAS -> Editar Reserva");
                            System.out.print("Insira o ID da Reserva: ");
                            numReserva = teclado.nextInt();
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
        pressEnterToContinue();
    }
    private static void listarQuartosLivres(){
        pressEnterToContinue();
    }
    private static void listarQuartosOcupados(){
        pressEnterToContinue();
    }

    //Funções de HOSPEDES
    private static void listarHospedes(){
        pressEnterToContinue();
    }
    private static void procurarHospedePorDocumento(){
        pressEnterToContinue();
    }
    private static void editarHospede(){
        pressEnterToContinue();
    }

    //Funções de RESERVAS
    private static void encontrarQuartoLivrePorCapacidade(){
        pressEnterToContinue();
    }
    private static void selecionarQuartoEspecifico(){
        pressEnterToContinue();
    }
    private static void listarTodasAsReservas(){
        pressEnterToContinue();
    }
    private static void listarReservaPorQuarto(){
        pressEnterToContinue();
    }
    private static void listarReservaPorHospede(){
        pressEnterToContinue();
    }
    private static void editarReserva(){
        pressEnterToContinue();
    }

    private static void pressEnterToContinue()
    {
        System.out.println("Press ENTER key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}

