/*
* Classes
Quarto: id, numero, capacidade, estaOcupado
Hospede: id, nome, documento
Reserva: id, idQuarto, idHospede, numeroHospede, dataInicio, dataFim, ativa
* */
import java.io.*;
import java.time.LocalDate; //necessario para as datas
import java.util.*;

public class Main {
    //Constantes
    static final int maxQuartos = 200;
    static final int maxHospedes = 1000;
    static final int maxReservas = 1000;

    //Ficheiros
    static final String fileQuartos = "quartos.csv";
    static final String fileHospedes = "hospedes.csv";
    static final String fileReservas = "reservas.csv";

    public static void main(String[] args) throws Exception {
        //Declaração de variaveis
        int opcMenu, opcSubMenu; //variaveis de menu
        String numDocumentoHospede; //variavel para receber o numero de CC do hospede
        int numPessoas, numQuarto, numReserva; //Variaveis para o Numero de Pessoas, Numero de Quarto e Numero de Reserva
        Scanner teclado = new Scanner(System.in); //Definição de "teclado" como input

        //loadAllData();
        //IMPLEMENTAR: É necessário verificar todas as reservas versus a data atual para atualizar aquelas que já terminaram

        do { //Esta função de DO, tem como objectivo repetir o menu enquanto o utilizador não escolher a opção 0 para sair
            showMainMenu(); //Chama a função que desenha o Menu Principal
            opcMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
            switch (opcMenu) { //Switch Case para as diferentes opções
                case 1:
                    showMenuQuartos(); //Chama a função que desenha o menu dos quartos
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1:
                            System.out.println("RESERVAS -> Listar todos os Quartos\n"); //Cabeçalho
                            listarTodosOsQuartos(); //Chama a função que vai listar todos os quartos
                            break;
                        case 2:
                            System.out.println("RESERVAS -> Listar Quartos Livres\n"); //Cabeçalho
                            listarQuartosLivres(); //Chama a função que vai listar os quartos livres
                            break;
                        case 3:
                            System.out.println("RESERVAS -> Listar Quartos Ocupados\n"); //Cabeçalho
                            listarQuartosOcupados(); //Chama a função que vai listar os quartos ocupados
                            break;
                    }
                    break;
                case 2:
                    showMenuHospedes(); //Chama a fução que desenha o menu de hospedes
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1:
                            System.out.println("HOSPEDES -> Listar Hospedes\n"); //Cabeçalho
                            listarHospedes(); //Chama a função que vai listar os hospedes
                            break;
                        case 2:
                            System.out.println("HOSPEDES -> Procurar Hospede por documento\n"); //Cabeçalho
                            System.out.print("Insira o número do documento (CC) do Hospede: ");  //Pede o CC do hospede
                            numDocumentoHospede = teclado.nextLine(); //Recebe o numero de CC. (AINDA FALTA VALIDAR O FORMATO)
                            procurarHospedePorDocumento();  //Chama a função que mostrar o hospede correspondente
                            break;
                        case 3:
                            System.out.println("HOSPEDES -> Editar Hospede\n"); //Cabeçalho
                            System.out.print("Insira o número do documento (CC) do Hospede: ");  //Pede o CC do hospede
                            numDocumentoHospede = teclado.nextLine(); //Recebe o numero de CC. (AINDA FALTA VALIDAR O FORMATO)
                            editarHospede(); //Chama a função que vai permitir editar os dados do hospede
                            break;
                    }
                    break;
                case 3:
                    showMenuReservas(); //Chama a fução que desenha o menu de reservas
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1:
                            System.out.println("RESERVAS -> Encontrar Quarto Livre por capacidade\n"); //Cabeçalho
                            System.out.print("Insira o número de pessoas: "); //Pede o numero de pessoas (INTEGER)
                            numPessoas = teclado.nextInt(); //Receber o numero de pessoas
                            encontrarQuartoLivrePorCapacidade(); //Chamar a função que vai encontrar os quartos livres para a capacidade desejada
                            break;
                        case 2:
                            System.out.println("RESERVAS -> Selecionar Quarto especifico\n"); //Cabeçalho
                            System.out.print("Insira o número do Quarto: "); //Pede o numero do quarto (INTEGER)
                            numQuarto = teclado.nextInt(); //Receber o numero de quarto
                            selecionarQuartoEspecifico(); //Função que vai escolher um quarto especifico para a reserva
                            break;
                        case 3:
                            System.out.println("RESERVAS -> Listar todas as Reservas\n"); //Cabeçalho
                            listarTodasAsReservas(); //Chama a função que vai listar todas as reservas
                            break;
                        case 4:
                            System.out.println("RESERVAS -> Listar Reserva por Quarto\n"); //Cabeçalho
                            System.out.print("Insira o número do Quarto: "); //Pede o número do quarto (INTEGER)
                            numQuarto = teclado.nextInt(); //Receber o numero de quarto
                            listarReservaPorQuarto();
                            break;
                        case 5:
                            System.out.println("RESERVAS -> Listar Reserva por Hospede\n"); //Cabeçalho
                            System.out.print("Insira o número do documento (CC) do Hospede: "); //Pedir o numero de CC do hospede
                            numDocumentoHospede = teclado.nextLine(); //Recebe o numero de CC. (AINDA FALTA VALIDAR O FORMATO)
                            listarReservaPorHospede();
                            break;
                        case 6:
                            System.out.println("RESERVAS -> Editar Reserva\n"); //Cabeçalho
                            System.out.print("Insira o ID da Reserva: "); //Pedir o ID da reserva
                            numReserva = teclado.nextInt(); //Recebe o numero da revserva (INTEGER)
                            editarReserva(); //Chama a função que vai permitir editar a reserva
                            break;
                    }
                    break;
            }
        } while (opcMenu != 0); //Repetir equanto a opção escolhida não for 0. Se for 0, sai
        //saveAllData(); //Guarda toda a informação
    }

    //Desenhar o MENU PRINCIPAL
    private static void showMainMenu(){
        System.out.println("== MENU ==");
        System.out.println("1 - Quartos");
        System.out.println("2 - Hospedes");
        System.out.println("3 - Reservas");
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opcao: ");
    }

    //Desenhar SUB-MENUS
    private static void showMenuQuartos(){
        System.out.println("== QUARTOS ==");
        System.out.println("1 - Listar todos os Quartos");
        System.out.println("2 - Listar Quartos livres");
        System.out.println("3 - Listar Quartos ocupados");
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opcao: ");
    }
    //Desenhar o Menu de Hospedes
    private static void showMenuHospedes(){
        System.out.println("== HOSPEDES ==");
        System.out.println("1 - Listar Hospedes");
        System.out.println("2 - Procurar Hospede por documento");
        System.out.println("3 - Editar Hospede");
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opcao: ");
    }
    //Desenhar o Menu de Reservas
    private static void showMenuReservas(){
        System.out.println("== RESERVAS ==");
        System.out.println("1 - Encontrar qualquer Quarto livre para determinada capacidade");
        System.out.println("2 - Selecionar Quarto especifico"); //Se indisponivel sugerir automaticamente uma alternativa adequada
        System.out.println("3 - Listar todas as Reservas");
        System.out.println("4 - Listar Reservas por Quarto"); //presentes ou futuras
        System.out.println("5 - Listar Reservas por Hospede"); //presentes ou futuras
        System.out.println("6 - Editar Reserva"); //numero de hospede ou datas e revalidar capacidade e conflitos
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opcao: ");
    }

    //Funções de QUARTOS
    private static void listarTodosOsQuartos(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarQuartosLivres(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarQuartosOcupados(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }

    //Funções de HOSPEDES
    private static void listarHospedes(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }

    private static void procurarHospedePorDocumento(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void editarHospede(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }

    //Funções de RESERVAS
    private static void encontrarQuartoLivrePorCapacidade(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void selecionarQuartoEspecifico(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarTodasAsReservas(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarReservaPorQuarto(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarReservaPorHospede(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void editarReserva(){
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    //Função para aguardar um ENTER por parte do utilizador antes de mudar o menu
    private static void pressEnterToContinue()
    {
        System.out.println("\nPress ENTER key to continue..."); //Mostrar a mensagem a pedir que o utilizador pressione a tecla ENTER
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    //Funções de acesso a ficheiros
    private static void loadAllData() {
        loadQuartosData();
        loadHospedesData();
        loadReservasData();
    }
    //Carregar dados relativamente aos Quartos
    static void loadQuartosData() {

    }
    //Carregar dados relativamente aos Hospedes
    static void loadHospedesData(){

    }
    //Carregar dados relativamente as Reservas
    static void loadReservasData(){

    }
    //Guardar todos os dados. Chama as duas funções especificas
    static void saveAllData(){
        saveHospedesData();
        saveReservasData();
    }
    //Guardar dados relativamente aos hospedes
    static void saveHospedesData(){

    }
    //Guardar dados relativamente as reservas
    static void saveReservasData(){

    }
}


