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
    //Arrays
    static Quarto[] quartos = new Quarto[maxQuartos];
    static Hospede[] hospedes = new Hospede[maxHospedes];
    static Reserva[] reservas = new Reserva[maxReservas];

    public static void main(String[] args) throws Exception {
        //Declaração de variaveis
        int opcMenu, opcSubMenu; //variaveis de menu
        Scanner teclado = new Scanner(System.in); //Definição de "teclado" como input
        loadAllData(); //Carregar todos os dados dos ficheiros .csv
        //IMPLEMENTAR: É necessário verificar todas as reservas versus a data atual para atualizar aquelas que já terminaram

        do { //Esta função de DO, tem como objectivo repetir o menu enquanto o utilizador não escolher a opção 0 para sair
            showMainMenu(); //Chama a função que desenha o Menu Principal
            opcMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
            switch (opcMenu) { //Switch Case para as diferentes opções
                case 1: //QUARTOS
                    showMenuQuartos(); //Chama a função que desenha o menu dos quartos
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1: listarTodosOsQuartos(); //Chama a função que vai listar todos os quartos
                            break;
                        case 2: listarQuartosLivres(); //Chama a função que vai listar os quartos livres
                            break;
                        case 3: listarQuartosOcupados(); //Chama a função que vai listar os quartos ocupados
                            break;
                        case 4: listarReservasDeQuarto(); //Chama a função que vai listar todas as reservas de um quarto
                            break;
                    }
                    break;
                case 2: //HOSPEDES
                    showMenuHospedes(); //Chama a fução que desenha o menu de hospedes
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1: adicionarHospede(); //Chama a função que vai adicionar hospedes
                            break;
                        case 2: listarHospedes(); //Chama a função que vai listar os hospedes
                            break;
                        case 3: procurarHospedePorDocumento();  //Chama a função que mostrar o hospede correspondente
                            break;
                        case 4: editarHospede(); //Chama a função que vai permitir editar os dados do hospede
                            break;
                    }
                    break;
                case 3: //RESERVAS
                    showMenuReservas(); //Chama a função que desenha o menu de reservas
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1: criarReserva(); //Chamar a função que vai encontrar os quartos livres para a capacidade desejada
                            break;
                        case 2: listarTodasAsReservas(); //Chama a função que vai listar todas as reservas
                            break;
                        case 3: listarReservaPorQuarto();
                            break;
                        case 4: listarReservaPorHospede();
                            break;
                        case 5: editarReserva(); //Chama a função que vai permitir editar a reserva
                            break;
                        case 6: cancelarReserva();
                            break;
                    }
                    break;
            }
            saveAllData(); //Guardar os dados nos ficheiros .csv
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
        System.out.println("4 - Listar Reservas de Quarto");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opcao: ");
    }
    //Desenhar o Menu de Hospedes
    private static void showMenuHospedes(){
        System.out.println("== HOSPEDES ==");
        System.out.println("1 - Adicionar Hospede");
        System.out.println("2 - Listar Hospedes");
        System.out.println("3 - Procurar Hospede por documento");
        System.out.println("4 - Editar Hospede");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opcao: ");
    }
    //Desenhar o Menu de Reservas
    private static void showMenuReservas(){
        System.out.println("== RESERVAS ==");
        System.out.println("1 - Criar Reserva");
        System.out.println("2 - Listar todas as Reservas");
        System.out.println("3 - Listar Reservas por Quarto"); //presentes ou futuras
        System.out.println("4 - Listar Reservas por Hospede"); //presentes ou futuras
        System.out.println("5 - Editar Reserva"); //numero de hospede ou datas e revalidar capacidade e conflitos
        System.out.println("6 - Cancelar Reserva");
        System.out.println("0 - Voltar");
        System.out.print("\nEscolha uma opcao: ");
    }

    //Funções de QUARTOS
    private static void listarTodosOsQuartos(){  //VALIDADO
        System.out.println("RESERVAS -> Listar todos os Quartos\n"); //Cabeçalho
        for (Quarto q : quartos) {
            if (q != null) {
                String linha = q.getIdQuarto() + "," + q.getNumero() + "," + q.getCapacidade() + "," + q.getEstaOcupado();
                System.out.println(linha);
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarQuartosLivres(){  //VAlIDADO
        System.out.println("RESERVAS -> Listar Quartos Livres\n"); //Cabeçalho
        for (Quarto q : quartos) {
            if (q != null && !q.getEstaOcupado()) {
                String linha = q.getIdQuarto() + "," + q.getNumero() + "," + q.getCapacidade() + "," + q.getEstaOcupado();
                System.out.println(linha);
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarQuartosOcupados(){
        System.out.println("RESERVAS -> Listar Quartos Ocupados\n"); //Cabeçalho
        for (Quarto q : quartos) {
            if (q != null && q.getEstaOcupado()) {
                String linha = q.getIdQuarto() + "," + q.getNumero() + "," + q.getCapacidade() + "," + q.getEstaOcupado();
                System.out.println(linha);
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarReservasDeQuarto() {
        int numQuarto, currentIdQuarto = -1;
        System.out.println("RESERVAS -> Listar Reservas de Quarto\n");
        Scanner teclado = new Scanner(System.in);
        System.out.print("Insira o número do Quarto: ");  //Pede o numero do Quarto
        numQuarto = teclado.nextInt();
        for (Quarto q : quartos) {
            if (q != null &&  q.getNumero() == numQuarto) {
                currentIdQuarto = q.getIdQuarto();
                break;
            }
        }
        for (Reserva r : reservas) {
            if (r != null) {
                String linha = r.getId() + "," + r.getIdQuarto() + "," + r.getIdHospede() + "," +
                        r.getNumeroHospedes() + "," + r.getDataInicio() + "," + r.getDataFim() + "," +
                        r.getEstaAtiva();
                if (r.getIdQuarto() == currentIdQuarto) {
                    System.out.println(linha);
                    break;
                }
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }

    //Funções de HOSPEDES
    private static void adicionarHospede() {
        System.out.println("HOSPEDES -> Adicionar Hospede\n"); //Cabeçalho
    }
    private static void listarHospedes(){
        System.out.println("HOSPEDES -> Listar Hospedes\n"); //Cabeçalho
        for (Hospede h : hospedes) {
            if (h != null) {
                String linha = h.getId() + "," + h.getNome() + "," + h.getDocumento();
                System.out.println(linha);
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void procurarHospedePorDocumento(){
        String numDocumentoHospede; //variavel para receber o numero de CC do hospede
        Scanner teclado = new Scanner(System.in);
        System.out.println("HOSPEDES -> Procurar Hospede por documento\n"); //Cabeçalho
        System.out.print("Insira o número do documento (CC) do Hospede: ");  //Pede o CC do hospede
        numDocumentoHospede = teclado.nextLine(); //Recebe o numero de CC. (AINDA FALTA VALIDAR O FORMATO)
        String currentDocument;
        for (Hospede h : hospedes) {
            if (h != null) {
                currentDocument = h.getDocumento();
                //String linha = h.getId() + "," + h.getNome() + "," + currentDocument;
                if (Objects.equals(numDocumentoHospede, currentDocument)) {
                    System.out.println("Encontrado:");
                    System.out.println("ID: " + h.getId());
                    System.out.println("Nome: " + h.getNome());
                    System.out.println("Documento: " + h.getDocumento());
                }
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void editarHospede(){
        String numDocumentoHospede = ""; //variavel para receber o numero de CC do hospede
        String currentDocument;
        String novoNomeHospede;
        String novoNumeroCC;
        Scanner teclado = new Scanner(System.in);
        System.out.println("HOSPEDES -> Editar Hospede\n"); //Cabeçalho
        System.out.print("Insira o número do documento (CC) do Hospede: ");  //Pede o CC do hospede
        numDocumentoHospede = teclado.nextLine();
        for (Hospede h : hospedes) {
            if (h != null) {
                currentDocument = h.getDocumento();
                //String linha = h.getId() + "," + h.getNome() + "," + currentDocument;
                if (Objects.equals(numDocumentoHospede, currentDocument)) {
                    System.out.println("Dados do Hospede:");
                    System.out.println("ID: " + h.getId());
                    System.out.println("Nome: " + h.getNome());
                    System.out.println("Documento: " + h.getDocumento());
                    System.out.println("Por favor, insira os novos dados. Caso não queira alterar, presione apenas ENTER\n");
                    System.out.print("Insira novo Nome do Hospede: ");
                    novoNomeHospede = teclado.nextLine();
                    if (novoNomeHospede != "") {
                        h.setNome(novoNomeHospede);
                    }
                    System.out.print("Insira novo Número de Cartão de Cidadão: ");
                    novoNumeroCC = teclado.nextLine(); //Recebe o numero de CC
                    if (novoNumeroCC != "") {
                        h.setDocumento(novoNumeroCC);
                    }
                    break;
                }
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    //Funções de RESERVAS
    private static void criarReserva(){
        int numPessoas, currentCapacidade; //variavel para receber o numero pessoas
        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Criar Reserva\n"); //Cabeçalho

        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }

    private static void listarTodasAsReservas(){
        System.out.println("RESERVAS -> Listar todas as Reservas\n"); //Cabeçalho
        for (Reserva r : reservas) {
            if (r != null) {
                String linha = r.getId() + "," + r.getIdQuarto() + "," + r.getIdHospede() + "," +
                        r.getNumeroHospedes() + "," + r.getDataInicio() + "," + r.getDataFim() + "," +
                        r.getEstaAtiva();
                System.out.println(linha);
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarReservaPorQuarto(){
        int numQuarto, currentRoom;
        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Listar Reserva por Quarto\n"); //Cabeçalho
        System.out.print("Insira o número do Quarto: "); //Pede o número do quarto (INTEGER)
        numQuarto = teclado.nextInt(); //Receber o numero de quarto
        for (Reserva r : reservas) {
            if (r != null) {
               String linha = r.getId() + "," + r.getIdQuarto() + "," + r.getIdHospede() + "," +
                        r.getNumeroHospedes() + "," + r.getDataInicio() + "," + r.getDataFim() + "," +
                        r.getEstaAtiva();
                currentRoom = r.getIdQuarto();
                if (Objects.equals(numQuarto, currentRoom)) {
                    System.out.print(linha);
                }

            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void listarReservaPorHospede(){
        String numDocumentoHospede, currentDocumentoHospede;
        String currentHospede;
        int hospedeId = -1;
        int currentHospedeId;

        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Listar Reserva por Hospede\n"); //Cabeçalho
        System.out.print("Insira o número do documento (CC) do Hospede: "); //Pedir o numero de CC do hospede
        numDocumentoHospede = teclado.nextLine(); //Recebe o numero de CC. (AINDA FALTA VALIDAR O FORMATO)
        for (Hospede h : hospedes) {
            if (h != null) {
                currentDocumentoHospede = h.getDocumento();
                //String linha = h.getId() + "," + h.getNome() + "," + currentDocument;
                if (Objects.equals(numDocumentoHospede, currentDocumentoHospede)) {
                    hospedeId = h.getId();
                    break;
                }
            }
        }
        for (Reserva r : reservas) {
            if (r != null) {
                String linha = r.getId() + "," + r.getIdQuarto() + "," + r.getIdHospede() + "," +
                        r.getNumeroHospedes() + "," + r.getDataInicio() + "," + r.getDataFim() + "," +
                        r.getEstaAtiva();
                currentHospedeId = r.getIdHospede();
                if (Objects.equals(hospedeId, currentHospedeId) && currentHospedeId != -1) {
                    System.out.println(linha);
                    break;
                }

            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void editarReserva(){
        int numReserva;
        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Editar Reserva\n"); //Cabeçalho
        System.out.print("Insira o ID da Reserva: "); //Pedir o ID da reserva
        numReserva = teclado.nextInt(); //Recebe o numero da revserva (INTEGER)
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void cancelarReserva(){
        int numReserva;
        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Cancelar Reserva\n");
        System.out.print("Insira o ID da Reserva: ");
        numReserva = teclado.nextInt();
    }
    //Funções de acesso a ficheiros
    private static void loadAllData() {
        loadQuartosData();
        loadHospedesData();
        loadReservasData();
    }
    //Carregar dados relativamente aos Quartos
    static void loadQuartosData() {
        //BufferedReader + FileReader
        int cnt = 0;
        int id;
        int numero;
        int capacidade;
        boolean estaOcupado;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileQuartos))) {
           // System.out.println("Ficheiro existente");
            String line;
            while ((line = reader.readLine()) != null && cnt < maxQuartos) { //Ler todas as linhas até apanhar valor nulo
                //System.out.println(line);
                String[] quartosLine = line.split(",");
                id = Integer.parseInt(quartosLine[0]);
                numero = Integer.parseInt(quartosLine[1]);
                capacidade = Integer.parseInt(quartosLine[2]);
                estaOcupado = Boolean.parseBoolean(quartosLine[3]);
                quartos[cnt] = new Quarto(id, numero, capacidade, estaOcupado);
                //System.out.println(quartos[cnt]);
                cnt++;
            }
        }
        catch(FileNotFoundException e){ //Ficheiro não encontrado
            System.out.println("ERRO: Ficheiro '" + fileQuartos + "' não encontrado");
        }
        catch(IOException e){ //Outros erros de acesso IO
            System.out.println("ERRO: Por favor, contacte o suporte!");
        }
    }
    //Carregar dados relativamente aos Hospedes
    static void loadHospedesData(){
        int cnt = 0;
        int id;
        String nome;
        String documento;
        //BufferedReader + FileReader
        try (BufferedReader reader = new BufferedReader(new FileReader(fileHospedes))) {
            // System.out.println("Ficheiro existente");
            String line;
            while ((line = reader.readLine()) != null && cnt < maxHospedes) { //Ler todas as linhas até apanhar valor nulo
                //System.out.println(line);
                String[] hospedesLine = line.split(",");
                id = Integer.parseInt(hospedesLine[0]);
                nome = hospedesLine[1];
                documento = hospedesLine[2];
                hospedes[cnt] = new Hospede(id, nome, documento);
                //System.out.println(hospedes[cnt]);
                cnt++;
            }
        }
        catch(FileNotFoundException e){ //Ficheiro não encontrado
            System.out.println("ERRO: Ficheiro '" + fileHospedes + "' não encontrado");
        }
        catch(IOException e){ //Outros erros de acesso IO
            System.out.println("ERRO: Por favor, contacte o suporte!");
        }
    }
    //Carregar dados relativamente as Reservas
    static void loadReservasData(){
        int cnt = 0;
        int id;
        int idQuarto;
        int idHospede;
        int numeroHospedes;
        LocalDate dataInicio;
        LocalDate dataFim;
        boolean estaAtiva;
        //BufferedReader + FileReader
        try (BufferedReader reader = new BufferedReader(new FileReader(fileReservas))) {
            // System.out.println("Ficheiro existente");
            String line;
            while ((line = reader.readLine()) != null && cnt < maxReservas) { //Ler todas as linhas até apanhar valor nulo
                //System.out.println(line);
                String[] reservasLine = line.split(",");
                id = Integer.parseInt(reservasLine[0]);
                idQuarto = Integer.parseInt(reservasLine[1]);
                idHospede = Integer.parseInt(reservasLine[2]);
                numeroHospedes = Integer.parseInt(reservasLine[3]);
                dataInicio = LocalDate.parse(reservasLine[4]);
                dataFim = LocalDate.parse(reservasLine[5]);
                estaAtiva = Boolean.parseBoolean(reservasLine[6]);
                reservas[cnt] = new Reserva(id, idQuarto, idHospede, numeroHospedes, dataInicio, dataFim, estaAtiva);
                //System.out.println(reservas[cnt]);
                cnt++;
            }
        }
        catch(FileNotFoundException e){ //Ficheiro não encontrado
            System.out.println("ERRO: Ficheiro '" + fileReservas + "' não encontrado");
        }
        catch(IOException e){ //Outros erros de acesso IO
            System.out.println("ERRO: Por favor, contacte o suporte!");
        }
    }
    //Guardar todos os dados. Chama as duas funções especificas
    static void saveAllData(){
        saveHospedesData();
        saveReservasData();
    }
    //Guardar dados relativamente aos hospedes
    static void saveHospedesData(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileHospedes))) {
            for (Hospede h : hospedes) {
                if (h != null) {
                    String linha = h.getId() + "," + h.getNome() + "," + h.getDocumento();
                    writer.write(linha);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possivel gravar " + fileHospedes + ": " + e.getMessage());
        }
    }
    //Guardar dados relativamente as reservas
    static void saveReservasData(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileReservas))) {
            for (Reserva r : reservas) {
                if (r != null) {
                    String linha = r.getId() + "," + r.getIdQuarto() + "," + r.getIdHospede() + "," +
                            r.getNumeroHospedes() + "," + r.getDataInicio() + "," + r.getDataFim() + "," +
                            r.getEstaAtiva();
                    writer.write(linha);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possivel gravar " + fileReservas + ": " + e.getMessage());
        }
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
}


