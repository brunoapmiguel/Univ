import java.io.*;
import java.time.LocalDate; //necessario para as datas
import java.time.Year;
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
    //variaveis
    static int numReservas; //Contador do numero total de reservas
    static int numHospedes; //Contador do numero total de hospedes

    public static void main(String[] args) throws Exception {
        //Declaração de variaveis
        int opcMenu, opcSubMenu; //variaveis de menu
        Scanner teclado = new Scanner(System.in); //Definição de "teclado" como input
        loadAllData(); //Carregar todos os dados dos ficheiros .csv
        atualizarTodasAsReservas();
        atualizarQuartosOcupados();
        String dataToCheck = "0";
        do { //Esta função de DO, tem como objectivo repetir o menu enquanto o utilizador não escolher a opção 0 para sair
            showMainMenu(); //Chama a função que desenha o Menu Principal
            //System.out.println(LocalDate.now());
            //System.out.println(verificarSeHospedeExiste("22222222"));
            opcMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
            switch (opcMenu) { //Switch Case para as diferentes opções
                case 0:
                    saveAllData();
                    break;
                case 1: //MENU QUARTOS
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
                case 2: //MENU HOSPEDES
                    showMenuHospedes(); //Chama a fução que desenha o menu de hospedes
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1: adicionarHospede(""); //Chama a função que vai adicionar hospedes
                            break;
                        case 2: listarHospedes(); //Chama a função que vai listar os hospedes
                            break;
                        case 3: procurarHospedePorDocumento();  //Chama a função que mostrar o hospede correspondente
                            break;
                        case 4: editarHospede(); //Chama a função que vai permitir editar os dados do hospede
                            break;
                    }
                    break;
                case 3: //MENU RESERVAS
                    showMenuReservas(); //Chama a função que desenha o menu de reservas
                    opcSubMenu = teclado.nextInt(); //Espera pela opção escolhida pelo utilizador
                    switch (opcSubMenu) {
                        case 1: criarReserva(); //Chamar a função que vai encontrar os quartos livres para a capacidade desejada
                            break;
                        case 2: listarTodasAsReservas(); //Chama a função que vai listar todas as reservas
                            break;
                        case 3: listarReservaPorQuarto(); //Listar reservas por quarto
                            break;
                        case 4: listarReservaPorHospede(); //Listar reservas por hospede
                            break;
                        case 5: editarReserva(); //Chama a função que vai permitir editar a reserva
                            break;
                        case 6: cancelarReserva(); //Cancelar reserva
                            break;
                    }
                    break;
            }
            //saveAllData(); //Guardar os dados nos ficheiros .csv
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
        System.out.println("ID,Numero,Capacidade,Esta Ocupado"); //Cabeçalho
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
        System.out.println("ID,Numero,Capacidade,Esta Ocupado"); //Cabeçalho
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
        System.out.println("ID,Numero,Capacidade,Esta Ocupado"); //Cabeçalho
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
        System.out.println("ID,ID Quarto,ID Hospede,Numero Hospedes, Data Inicio, Data Fim, Está Ativa"); //Cabeçalho
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
    //Adicionar Hospedes
    private static void adicionarHospede(String doc) {
        //VERIFICAR SE O NUMERO DE HOSPEDES JÁ ESTA NO MAXIMO
        String nome;
        String documento;
        Scanner teclado = new Scanner(System.in);
        System.out.println("HOSPEDES -> Adicionar Hospede\n"); //Cabeçalho
        if (doc == "") {
            System.out.print("Número do Cartão de Cidadão: ");
            documento = teclado.nextLine();
        } else {
            System.out.println("Número do Cartão de Cidadão: " + doc);
            documento = doc;
        }
        if (!verificarSeHospedeExiste(documento)) {
            numHospedes++;
            System.out.print("Nome do Hóspede: ");
            nome = teclado.nextLine();
            hospedes[numHospedes] = new Hospede(numHospedes, nome, documento);
            saveHospedesData();
            System.out.println("\nHóspede adicionado!");
        } else {
            System.out.println("\nHóspede já existente!");
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    //Listar Hospedes
    private static void listarHospedes(){
        System.out.println("HOSPEDES -> Listar Hospedes\n"); //Cabeçalho
        System.out.println("ID,Nome,Documentos"); //Cabeçalho
        for (Hospede h : hospedes) {
            if (h != null) {
                String linha = h.getId() + "," + h.getNome() + "," + h.getDocumento();
                System.out.println(linha);
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    //Procurar Hospede por documento
    private static void procurarHospedePorDocumento(){
        String numDocumentoHospede; //variavel para receber o numero de CC do hospede
        Scanner teclado = new Scanner(System.in);
        System.out.println("HOSPEDES -> Procurar Hospede por documento\n"); //Cabeçalho
        System.out.print("Insira o número do documento (CC) do Hospede: ");  //Pede o CC do hospede
        numDocumentoHospede = teclado.nextLine(); //Recebe o numero de CC. (AINDA FALTA VALIDAR O FORMATO)
        String currentDocument;
        boolean encontrado = false;
        char opt;
        for (Hospede h : hospedes) {
            if (h != null) {
                currentDocument = h.getDocumento();
                //String linha = h.getId() + "," + h.getNome() + "," + currentDocument;
                if (Objects.equals(numDocumentoHospede, currentDocument)) {
                    System.out.println("Encontrado:");
                    System.out.println("ID: " + h.getId());
                    System.out.println("Nome: " + h.getNome());
                    System.out.println("Documento: " + h.getDocumento());
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado) {
            System.out.println("Hóspede não encontrado!");
            System.out.print("\nDeseja adicionar (S/N)? ");
            opt = teclado.next().charAt(0);
            if (opt == 'S' || opt == 's') {
                adicionarHospede(numDocumentoHospede);
            }
        } else {
            pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
        }
    }
    //Função auxiliar para verificar se hospede existe?
    private static boolean verificarSeHospedeExiste(String numDocumentoHospede){
        boolean existe = false;
        for (Hospede h : hospedes) {
            if (h != null) {
                if (Objects.equals(numDocumentoHospede, h.getDocumento())) {
                //if (numDocumentoHospede == h.getDocumento()) {
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }
    private static int obterIdDeHospede(String numDocumentoHospede){
        int hid = -1;
        for (Hospede h : hospedes) {
            if (h != null) {
                //System.out.println(h.getDocumento());
                if (Objects.equals(numDocumentoHospede, h.getDocumento())) {
                    //if (numDocumentoHospede == h.getDocumento()) {
                    hid = h.getId();
                    //System.out.println("ENCONTRADO");
                    break;
                }
            }
        }
        return hid;
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
                    System.out.println("\nPor favor, insira os novos dados. Caso não queira alterar, presione apenas ENTER\n");
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
                    saveHospedesData();
                    break;
                } else {
                    System.out.println("Hóspede não encontrado!");
                }
            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    //Funções de RESERVAS
    private static void criarReserva(){
        int numPessoas = 0, hospedeId, quartoId; //variavel para receber o numero pessoas
        char opt;
        boolean dataInicioValida = false, dataFimValida = false, datasOK = false;
        LocalDate dataInicio = LocalDate.parse("1970-01-02"), dataFim = LocalDate.parse("1970-01-01");
        String dataI = "0", dataF = "0", nxline;
        String numDocumentoHospede;
        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Criar Reserva\n"); //Cabeçalho
        System.out.print("Insira o número do documento (CC) do Hospede: ");  //Pede o CC do hospede
        numDocumentoHospede = teclado.nextLine();
        //VALIDAR SE O NUMERO DE RESERVAS ESTA NO MAXIMO
        if (verificarSeHospedeExiste(numDocumentoHospede)) {
            hospedeId = obterIdDeHospede(numDocumentoHospede);
            System.out.print("Insira o Número total de pessoas: ");
            numPessoas = teclado.nextInt();
            nxline = teclado.nextLine(); //Ler nextline para o nextint acima
            while (!datasOK) {
                while (!dataInicioValida) {
                    System.out.print("Insira a Data de Inicio (AAAA-MM-DD): ");
                    dataI = teclado.nextLine(); //Recebe a data de inicio
                    dataInicioValida = dataValida(dataI);
                }
                dataInicio = LocalDate.parse(dataI);
                while (!dataFimValida) {
                    System.out.print("Insira a Data de Fim (AAAA-MM-DD): ");
                    dataF = teclado.nextLine(); //Recebe a data de fim
                    dataFimValida = dataValida(dataF);
                }
                dataFim = LocalDate.parse(dataF);
                if (dataInicio.isAfter(dataFim)) {
                    System.out.println("\nA data de Inicio não pode ser posterior á data de Fim\n");
                    dataInicioValida = false;
                    dataFimValida = false;
                    datasOK = false;
                }
                if (dataInicio.isEqual(dataFim)) {
                    System.out.println("\nA data de Inicio não pode ser igual á data de Fim\n");
                    dataInicioValida = false;
                    dataFimValida = false;
                    datasOK = false;
                }
                if (!dataInicio.isAfter(dataFim) && !dataInicio.isEqual(dataFim)) {
                    datasOK = true;
                }
            }
            quartoId = procurarQuartoId(numPessoas, dataInicio, dataFim);
            System.out.println("Quarto sugerido: " + obterNumerodeQuartoPeloId(quartoId));
            //necessária função para receber o numero de quarto, e datas para validar se o quarto esta livre
            numReservas++;
            reservas[numReservas] = new Reserva(numReservas, quartoId, hospedeId, numPessoas, dataInicio, dataFim, true);
            saveReservasData();
            pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
        } else {
            System.out.println("Hóspede não encontrado!");
            System.out.print("\nDeseja adicionar (S/N)? ");
            opt = teclado.next().charAt(0);
            if (opt == 'S' || opt == 's') {
                adicionarHospede(numDocumentoHospede);
            }
        }
    }
    //Função auxiliar para procurar quartos para um determinado numero de hospedes
    private static int procurarQuartoId(int numPessoas, LocalDate dataI, LocalDate dataF){
        int quartoIdDisponivel = -1;
        for (Quarto q : quartos) {
            if (q != null && !q.getEstaOcupado()) {
                if (q.getCapacidade() >= numPessoas) {
                    //System.out.print(q.getIdQuarto());
                    if (!quartoReservadoNesteIntervalo(q.getIdQuarto(), dataI, dataF)) {
                        quartoIdDisponivel = q.getIdQuarto();
                        break;
                    }
                }
            }
        }
        return quartoIdDisponivel;
    }
    private static boolean quartoReservadoNesteIntervalo(int quartoId, LocalDate dataI, LocalDate dataF){
        boolean reservado = false;
        for (Reserva r : reservas) {
            if (r != null) {
                //System.out.println("ID Reserva: " + r.getId() + " para o quarto: " + r.getIdQuarto());
                if (quartoId == r.getIdQuarto() && r.getEstaAtiva()) {
                    if (dataI.isEqual(r.getDataInicio()) ||  dataF.isEqual(r.getDataFim())) {
                        reservado = true;
                        break;
                    }
                    if (dataI.isAfter(r.getDataInicio()) && dataI.isBefore(r.getDataFim())) {
                        reservado = true;
                        break;
                    }
                    if (dataF.isAfter(r.getDataInicio()) && dataI.isBefore(r.getDataFim())) {
                        reservado = true;
                        break;
                    }
                }
            }
        }
        return reservado;
    }
    private static int obterNumerodeQuartoPeloId(int quartoId){
        int qnum = -1;
        for (Quarto q : quartos) {
            if (q != null && !q.getEstaOcupado()) {
                if (q.getIdQuarto() == quartoId) {
                    qnum = q.getNumero();
                    break;
                }
            }
        }
        return qnum;
    }
    private static void atualizarTodasAsReservas(){
        for (Reserva r : reservas) {
            if (r != null) {
                //System.out.println(r.getDataFim().isBefore(LocalDate.now()));
                if (r.getEstaAtiva() && r.getDataFim().isBefore(LocalDate.now())) {
                    r.setEstaAtiva(false);
                }
            }
        }
        saveReservasData();
    }
    //Função para atualizar flag de estaOcupado nos quartos
    private static void atualizarQuartosOcupados() {
        int quartoId = -1;
        for (Quarto q : quartos) {
            if (q != null) {
                if (verificarSeExisteReservaAtivaEQuartoOcupado(q.getIdQuarto())){
                    q.setEstaOcupado(true);
                }
            }
        }
        saveQuartosData();
    }
    //Verificar se os quartos estão ocupados nesta data
    private static boolean verificarSeExisteReservaAtivaEQuartoOcupado(int quartoId){
        boolean quartoOcupado = false;
        for (Reserva r : reservas) {
            if (r != null) {
                //System.out.println(r.getDataFim().isBefore(LocalDate.now()));
                if (r.getEstaAtiva() && quartoId == r.getIdQuarto()) {
                    if (r.getDataInicio().isEqual(LocalDate.now()) || r.getDataFim().isEqual(LocalDate.now())) {
                        quartoOcupado = true;
                        //System.out.println("OCUPADO");
                        break;
                    }
                    if (r.getDataInicio().isBefore(LocalDate.now()) && r.getDataFim().isAfter(LocalDate.now())) {
                        //System.out.println("OCUPADO");
                        quartoOcupado = true;
                        break;
                    }
                }
            }
        }
        return quartoOcupado;
    }
    private static void listarTodasAsReservas(){
        System.out.println("RESERVAS -> Listar todas as Reservas\n"); //Cabeçalho
        System.out.println("ID,ID Quarto,ID Hospede,Numero Hospedes, Data Inicio, Data Fim, Está Ativa"); //Cabeçalho
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
        System.out.println("ID,ID Quarto,ID Hospede,Numero Hospedes, Data Inicio, Data Fim, Está Ativa"); //Cabeçalho
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
        System.out.println("ID,ID Quarto,ID Hospede,Numero Hospedes, Data Inicio, Data Fim, Está Ativa"); //Cabeçalho
        for (Reserva r : reservas) {
            if (r != null) {
                String linha = r.getId() + "," + r.getIdQuarto() + "," + r.getIdHospede() + "," +
                        r.getNumeroHospedes() + "," + r.getDataInicio() + "," + r.getDataFim() + "," +
                        r.getEstaAtiva();
                currentHospedeId = r.getIdHospede();
                if (Objects.equals(hospedeId, currentHospedeId) && hospedeId != -1) {
                    System.out.println(linha);
                    //break;
                }

            }
        }
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static void editarReserva(){
        int numReserva;
        String novaDataI = "0", novaDataF = "0";
        int novoNumeroHospedes;
        char opt = 'z';
        String nxtint;
        LocalDate novaDataInicio, novaDataFim;
        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Editar Reserva\n"); //Cabeçalho
        System.out.print("Insira o ID da Reserva: "); //Pedir o ID da reserva
        numReserva = teclado.nextInt(); //Recebe o numero da reserva (INTEGER)
        for (Reserva r : reservas) {
            if (r != null) {
                if (Objects.equals(numReserva, r.getId())) {
                    System.out.println("ID Reserva: " + r.getId());
                    System.out.println("Nome da Reserva: " + obterNomeDeHospede(r.getIdHospede()));
                    System.out.println("Número de Hospedes: " + r.getNumeroHospedes());
                    System.out.println("Data de Inicio: " + r.getDataInicio());
                    System.out.println("Data de Fim: " + r.getDataFim());
                    //pressEnterToContinue();
                    if (!r.getEstaAtiva()) {
                        System.out.println("ATENÇÃO: A reserva encontra-se inativa");
                        System.out.print("Deseja continuar (S/N)?: ");
                        opt = teclado.next().charAt(0);
                    }
                    if ((r.getEstaAtiva()) || (opt == 'S' || opt =='s')) {
                        System.out.print("\nPor favor, insira os novos dados\n");
                        System.out.print("Número de Hospedes: ");
                        novoNumeroHospedes = teclado.nextInt();
                        nxtint = teclado.nextLine();
                        while (!dataValida(novaDataI)) {
                            System.out.print("Data de Inicio (AAAA-MM-DD): ");
                            novaDataI = teclado.nextLine(); //Recebe a data de inicio
                        }
                        novaDataInicio = LocalDate.parse(novaDataI);
                        while (!dataValida(novaDataF)) {
                            System.out.print("Data de Fim (AAAA-MM-DD): ");
                            novaDataF = teclado.nextLine(); //Recebe a data de fim
                        }
                        novaDataFim = LocalDate.parse(novaDataF);
                        r.setNumeroHospedes(novoNumeroHospedes);
                        r.setDataInicio(novaDataInicio);
                        r.setDataFim(novaDataFim);
                        System.out.println("\nDados de reserva alterados!");
                    }
                    break;
                }
            }
        }
        saveReservasData();
        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
    }
    private static String obterNomeDeHospede(int hospedeId){
        String nomeHospede = null;
        for (Hospede h : hospedes) {
            if (h != null) {
                //System.out.println(h.getDocumento());
                if (Objects.equals(hospedeId, h.getId())) {
                    //if (numDocumentoHospede == h.getDocumento()) {
                    nomeHospede = h.getNome();
                    //System.out.println("ENCONTRADO");
                    break;
                }
            }
        }
        return nomeHospede;
    }
    private static void cancelarReserva(){
        int numReserva;
        char opt;
        Scanner teclado = new Scanner(System.in);
        System.out.println("RESERVAS -> Cancelar Reserva\n");
        System.out.print("Insira o ID da Reserva: ");
        numReserva = teclado.nextInt();
        for (Reserva r : reservas) {
            if (r != null) {
                if (numReserva == r.getId()) {
                    System.out.println("ID: " + r.getId());
                    System.out.println("Data Inicio: " + r.getDataInicio());
                    System.out.println("Data Fim: " + r.getDataFim());
                    System.out.println("Reserva Ativa? " + r.getEstaAtiva());
                    if (r.getEstaAtiva()) {
                        System.out.print("\nDeseja cancelar (S/N)? ");
                        opt = teclado.next().charAt(0);
                        if (opt == 'S' || opt =='s') {
                            r.setEstaAtiva(false);
                            System.out.println("\nReserva cancelada!");
                            saveReservasData();
                            pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
                        } else {
                            break;
                        }
                    } else if (!r.getEstaAtiva()) {
                        System.out.println("\nA reserva já se encontrava inativa!");
                        pressEnterToContinue(); //Esperar por um ENTER por parte do utilizador
                    }
                }
            }
        }
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
                numHospedes = cnt;
                cnt++;
            }
            //System.out.println(numHospedes);
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
                numReservas = cnt;
                cnt++;
            }
            //System.out.println(numReservas);
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
        saveQuartosData();
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
    //Guardar dados relativamente aos quartos
    static void saveQuartosData(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileQuartos))) {
            for (Quarto q : quartos) {
                if (q != null) {
                    String linha = q.getIdQuarto() + "," + q.getNumero() + "," + q.getCapacidade() + "," + q.getEstaOcupado();
                    writer.write(linha);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possivel gravar " + fileQuartos + ": " + e.getMessage());
        }
    }
    //Função para aguardar um ENTER por parte do utilizador antes de mudar o menu
    private static void pressEnterToContinue()
    {
        System.out.println("\nPress ENTER key to continue..."); //Mostrar a mensagem a pedir que o utilizador pressione a tecla ENTER
        try {
            System.in.read();
        }
        catch(Exception e) {}
    }
    private static boolean dataValida(String data) {
        int ano = 0, mes = 0, dia = 0;
        boolean dataEstaValida = false;
        try {
            String[] quartosLine = data.split("-");
            ano = Integer.parseInt(quartosLine[0]);
            mes = Integer.parseInt(quartosLine[1]);
            dia = Integer.parseInt(quartosLine[2]);
            dataEstaValida = true;
        }
        catch(Exception e) {
            dataEstaValida = false;
        }
        int anoAtual, anosFuturos;
        anoAtual = Year.now().getValue();
        anosFuturos = anoAtual + 2;
        if (ano < anoAtual || ano > anosFuturos) {
            dataEstaValida = false;
        }
        if (mes < 1 || mes > 12) {
            dataEstaValida = false;
        }
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            if (dia <1 || dia > 31) {
                dataEstaValida = false;
            }
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia <1 || dia > 30) {
                dataEstaValida = false;
            }
        }
        if (mes == 2) {
            if (Year.isLeap(ano) && dia < 1  || dia > 29) {
                dataEstaValida = false;
            } else if (!Year.isLeap(ano) && dia < 1  || dia > 28) {
                dataEstaValida = false;
            }
        }
        //System.out.println("Data: " + ano + "-" + mes + "-" + dia);
        return dataEstaValida;
    }
}
