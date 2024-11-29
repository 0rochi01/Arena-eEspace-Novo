/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp07.arenaespace.arena.espace1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author RyanS
 */

// A classe Promotor que herda da classe Utilizador todos os atributos, indicando que um promotor é um tipo de utilizador
public class Promotor extends Utilizador { 
    
    // Construtor da classe Promotor que inicializa os atributos herdados da classe Utilizador
    public Promotor(String NomeCompleto, String NomeDeUtilizador, String email, String password, String Privilegio) {
        // Chama o construtor da classe pai (Utilizador) para inicializar os atributos
        super(NomeCompleto, NomeDeUtilizador, email, password, Privilegio);
    }

static class PromotorMenu3 {

    private final Menu_1 menuPrincipal;// Referência ao menu principal (Menu_1)
    private final Scanner menu3;//Declara um objeto Scanner 
    private final List<Eventos> eventosCriados;//Lista de eventos criados
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");//Formatação da data e Hora
    
    //construtor da classe
    public PromotorMenu3(Scanner menu3, Menu_1 menuPrincipal, List<Eventos> eventosCriados) {
        this.menu3 = menu3;//Inicializa o Menu 3
        this.menuPrincipal = menuPrincipal;//Inicializa o Menu Principal
        this.eventosCriados = eventosCriados;//Inicializa a lista de eventos criados 
    }
    //Método que executa o Menu 3
    public void executa3() {
        System.out.println("Bem-vindo Promotor de Eventos!");//Dá as boas vindas ao Promotor

        // Chama o método que exibe o menu e retorna a opção selecionada pelo usuário
        OpcaoMenu3 opcao = mostrarMenuEDevolverOpcaoSelecionada();

        // Inicio do loop que continuará até que o usuário selecione a opção de sair
        do {
            //switch para lidar com a opção selecionada
            switch (opcao) {
                case GERIREVENTOS -> gerenciarEventos();//Chama o método para gerenciar eventos
                case CRIAREVENTOS -> criarEventos();//Chama o método para criar eventos 
                case SAIR -> {//Opção de sair do menu
                    System.out.print("\nAté Logo!!");
                    menuPrincipal.executa();//Chama o método do menu principal para voltar ao menu de login do sistema
                }
                default -> System.out.println("Opção Inválida");//Se a opção selecionada não existir
            }
            opcao = mostrarMenuEDevolverOpcaoSelecionada(); // Atualiza a opção para o próximo loop
        } while (opcao != OpcaoMenu3.SAIR);//Mantém o loop enquanto a opção sair não é selecionada
    }
    
    //Método para Gerenciar eventos
    private void gerenciarEventos() {
        //Variável para guardar a opção escolhida no menu gerenciar eventos
        OpcaoGerirEventos op;//Opção gerir eventos
        
        //Inicio do loop que continuará até que o utiliizador escolha voltar para o menu anterior
        do {
            op = mostrarMenuEDevolverOpcaoSetada();// Chama o método que exibe o menu e retorna a opção selecionada pelo usuário
            switch (op) {
                case VIZUALIZARRESERVAS -> vizualizarReservas();// Chama o método para visualizar reservas 
                case EDITARRESERVAS -> editarReservas();// Chama o método para editar reservas
                case REMOVEREVENTOS -> removerEvento();// Chama o método para remover eventos
                case MENUANTERIOR -> {// Opção para voltar ao menu anterior
                }
                case SAIR -> {//Opção sair
                    System.out.print("\nAté Logo!!");
                    menuPrincipal.executa();// Executa método do menu principal para voltar ao menu de login
                }
                default -> System.out.println("Opção Inválida");//Caso a opção inserida não seja válida
            }
        } while (op != OpcaoGerirEventos.MENUANTERIOR);//Mantém o loop enquanto não selecionar a opção de voltar para o menu anterior
    }
    
    private void criarEventos() {//Método para criar eventos
        boolean continuar = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");//Formato da data e hora
        final int LIMITE_EVENTOS = 2; // Limite de eventos que podem ser criados
        int contadorEventosCriados = 0;// Contador para rastrear o número de eventos criados

        while (continuar) {
            //Verifica se o limite de eventos foi atingido
            if (contadorEventosCriados >= LIMITE_EVENTOS) { 
                System.out.println("Limite de " + LIMITE_EVENTOS + " eventos já atingido. Não é possível criar mais eventos.");
                break; // Encerra o loop se o limite for atingido
            }

            String titulo = solicitarEntrada("Digite o título do Evento:");//Solicita e armazena o título do evento
            LocalDateTime dataHora = solicitarDataHora(formatter);//Solicita e armazena a data e hora do evento
            String sala = solicitarEntrada("Digite a Sala do Evento:");//SOlicita a armazena a sala do evento selecionada

            // Verifica se já existe um evento na mesma data e sala
            if (verificarConflitoEventos(dataHora, sala)) {
                System.out.println("Já existe um evento na mesma data e sala. Tente novamente.");
                continue; // Retorna ao início do loop para criar um novo evento
            }

            String modalidade = solicitarEntrada("Escolha a modalidade do seu Evento:");//Solicita e armazena a modalidade do evento
            int numeroMaximoDeParticipantes = solicitarNumeroMaximoParticipantes();//Solicita o Número máximo de participantes

            // Verifica se o número máximo de participantes é válido
            if (numeroMaximoDeParticipantes <= 0) {
                System.out.println("Número máximo de participantes deve ser um número positivo. Tente novamente.");
                continue; // Retorna ao início do loop
            }

            String condicoesInscricao = solicitarEntrada("Digite as condições para participação no Evento:");  // Solicita as condições para participação no evento

            // Cria um novo evento utilizando os dados coletados e a variável promotorLogado
            Eventos evento = new Eventos(
                titulo,
                dataHora,
                sala,
                condicoesInscricao,
                modalidade,
                numeroMaximoDeParticipantes,
                Menu_1.promotorLogado.getNomeCompleto(), // Nome do promotor logado no sistema
                Menu_1.promotorLogado.getEmail() // Email do promotor logado no sistema
            );

            eventosCriados.add(evento); // Adiciona o evento à lista de eventos criados
            contadorEventosCriados++; // Incrementa o contador de eventos criados

            System.out.println("Evento criado com sucesso:");
            System.out.println(evento.toString());

            // Pergunta se o utilizador deseja criar outro evento
            continuar = perguntarSeDesejaCriarOutroEvento();
        }
    }
    
    // Método para vizualizar as reservas realizada
    private void vizualizarReservas(){
        if (eventosCriados.isEmpty()){//Verifica se existem reservas realizadas pelo promotor
            System.out.println("Não existe reservas efetuadas");
        } else {
            for (Eventos evento : eventosCriados){//Para cada evento criado
                System.out.println(evento.toString()); // Exibe detalhes do evento 
            }
           
        }
    }
    
    //Método para editar reservas
    private void editarReservas() { 
        if (eventosCriados.isEmpty()) { //Verifica se existem eventos para que possam ser editados
            System.out.println("Não existem eventos cadastrados para editar.");
            return; //Sai do método se não houver eventos
        }

        // Exibir eventos existentes
        System.out.println("Eventos cadastrados:");
        for (int i = 0; i < eventosCriados.size(); i++) {//Lista todos os eventos do promotor para editar
            Eventos evento = eventosCriados.get(i);
            System.out.println((i + 1) + ". " + evento.toString()); // Mostra o índice e a descrição do evento
        }

        // Solicitar ao usuário para escolher um evento para editar
        int eventoIndex = -1;
        while (eventoIndex < 0 || eventoIndex >= eventosCriados.size()) {
            System.out.println("Digite o número do evento que deseja editar:");
            if (menu3.hasNextInt()) {
                eventoIndex = menu3.nextInt() - 1; // Ajusta para índice zero
                menu3.nextLine(); // Consumir a nova linha
                if (eventoIndex < 0 || eventoIndex >= eventosCriados.size()) {//caso a opção seja inválida
                    System.out.println("Número de evento inválido. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                menu3.nextLine(); // Limpar a entrada inválida
            }
        }
        
        // Obtém o evento selecionado para edição
        Eventos eventoSelecionado = eventosCriados.get(eventoIndex);

        // Editar título
        String novoTitulo = solicitarEntrada("Título atual: " + eventoSelecionado.getTitulo() + 
                                            "\nDigite o novo título (ou pressione Enter para manter o atual):");
        if (!novoTitulo.isEmpty()) {
            eventoSelecionado.setTitulo(novoTitulo); // Atualiza o título se uma nova entrada for fornecida
        }

        // Editar data e hora
        String novaDataHoraInput = solicitarEntrada("Data e Hora atual: " + eventoSelecionado.getDataHora().format(formatter) + 
                                                    "\nDigite a nova data e hora (dd/MM/yyyy HH:mm) ou pressione Enter para manter a atual:");
        if (!novaDataHoraInput.isEmpty()) {
            if (validarDataHora(novaDataHoraInput)) { // Valida o formato da nova data e hora
                try {
                    LocalDateTime novaDataHora = LocalDateTime.parse(novaDataHoraInput, formatter);// Converte a entrada para LocalDateTime
                    eventoSelecionado.setDataHora(novaDataHora); // Atualiza a data e hora do evento
                } catch (Exception e) {
                    System.out.println("Erro ao processar a nova data e hora. Tente novamente.");
                }
            } else {
                System.out.println("Formato de data e hora inválido. Tente novamente.");
            }
        }

        // Editar sala
        String novaSala = solicitarEntrada("Sala atual: " + eventoSelecionado.getSala() + 
                                           "\nDigite a nova sala (ou pressione Enter para manter a atual):");
        if (!novaSala.isEmpty()) {
            eventoSelecionado.setSala(novaSala); // Atualiza a sala se uma nova entrada for fornecida
        }

        // Exibir mensagem de sucesso
        System.out.println("Evento editado com sucesso:");
        System.out.println(eventoSelecionado.toString()); // Mostra os detalhes do evento editado
    }
    
    // Método auxiliar para perguntar se o usuário deseja criar outro evento
    private boolean perguntarSeDesejaCriarOutroEvento() {
        while (true) {
            System.out.println("Deseja criar outro evento? (s/n)");
            String resposta = menu3.nextLine().trim(); // Lê a resposta do usuário

            if (resposta.equalsIgnoreCase("s")) {
                return true; // O usuário deseja criar outro evento
            } else if (resposta.equalsIgnoreCase("n")) {
                System.out.println("Até Logo!!");
                return false; // O usuário não deseja criar outro evento
            } else {
                System.out.println("Resposta inválida. Por favor, digite 's' para sim ou 'n' para não. ");
            }
        }
    }

    // Método para solicitar entrada do usuário    
    private String solicitarEntrada(String mensagem) {
        System.out.println(mensagem); // Exibe a mensagem solicitando a entrada
        return menu3.nextLine(); // Lê e retorna a entrada do usuário
    }

    // Método para validar o formato da data e hora
    private boolean validarDataHora(String dataHoraInput) {
        // Verifica se a entrada corresponde ao formato "dd/MM/yyyy HH:mm"
        return dataHoraInput.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}");
    }
    
    // Método para solicitar e retornar uma data e hora válidas
    private LocalDateTime solicitarDataHora(DateTimeFormatter formatter) {
        LocalDateTime dataHora = null; // Inicializa a variável como nula
        while (dataHora == null) { // Continua pedindo até que uma data e hora válidas sejam fornecidas
            String dataHoraInput = solicitarEntrada("Digite a Data e a Hora do seu evento (dd/MM/yyyy HH:mm):");
            try {
                // Tenta converter a entrada para LocalDateTime usando o formato especificado
                dataHora = LocalDateTime.parse(dataHoraInput, formatter); 
            } catch (DateTimeParseException e) {
                // Mensagem de erro se a entrada não puder ser convertida
                System.out.println("Formato de data e hora inválido. Tente novamente.");
            }
        }
        return dataHora; // Retorna a data e hora válidas
    }
    
    // Método para verificar conflitos de eventos
    private boolean verificarConflitoEventos(LocalDateTime dataHora, String sala) {
        // Verifica se já existe um evento na mesma data e sala
        return eventosCriados.stream()
            .anyMatch(evento -> evento.getDataHora().isEqual(dataHora) && evento.getSala().equalsIgnoreCase(sala));
    }
    
    // Método para solicitar o número máximo de participantes
    private int solicitarNumeroMaximoParticipantes() {
        int numeroMaximoDeParticipantes = -1; // Valor inicial inválido
        while (numeroMaximoDeParticipantes < 0) { // Continua pedindo até que um número válido seja fornecido
            System.out.println("Digite um número máximo de participantes:");
            try {
                numeroMaximoDeParticipantes = menu3.nextInt(); // Lê o número máximo de participantes
                menu3.nextLine(); // Consome a nova linha
                if (numeroMaximoDeParticipantes < 0) {
                    // Mensagem de erro se o número for negativo
                    System.out.println("O número máximo de participantes deve ser um número positivo.");
                }
            } catch (InputMismatchException e) {
                // Mensagem de erro se a entrada não for um número inteiro
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                menu3.nextLine(); // Limpa o buffer do scanner
            }
        }
        return numeroMaximoDeParticipantes; // Retorna o número máximo de participantes válido
    }
    
    // Método para remover um evento
    private void removerEvento() {
        // Verifica se existem eventos criados
        if (eventosCriados.isEmpty()) {
            System.out.println("Não existem eventos criados.");
            return; // Sai do método se não houver eventos
        }

        // Exibe a lista de eventos
        System.out.println("Eventos criados:");
        for (int i = 0; i < eventosCriados.size(); i++) {
            // Mostra o índice e o título de cada evento
            System.out.println((i + 1) + ". " + eventosCriados.get(i).getTitulo());
        }

        // Solicita ao utilizador que selecione um evento paara visualizar ou apagar
        System.out.println("Digite o número do evento que deseja visualizar ou apagar (ou 0 para voltar):");
        int escolha = menu3.nextInt(); // Lê a escolha do utilizador
        menu3.nextLine(); // Consumir o caracter de nova linha

        if (escolha == 0) {
            return; // Volta ao menu anterior se o utilizadoro escolher 0
        }

        // Verifica se a escolha é válida
        if (escolha < 0 || escolha > eventosCriados.size()) {
            System.out.println("Opção inválida.");
            return; // Sai do método se a opção for inválida
        }

        // Obter o evento selecionado
        Eventos eventoSelecionado = eventosCriados.get(escolha - 1);

        // Exibe os detalhes do evento
        System.out.println("\nDetalhes do evento:");
        System.out.println(eventoSelecionado.toString());

        // Solicita ao utilizador se deseja apagar o evento
        System.out.println("Deseja apagar este evento? (s/n)");
        String respostaSN = menu3.nextLine(); // Lê a resposta do usuário
        Resposta resposta = Resposta.fromString(respostaSN); // Converte a resposta para o enum Resposta

        // Verifica se a resposta é válida
        if (null == resposta) {
            System.out.println("Rsposta inválida. Por favor, insira 's' ou 'n'.");
        } else { 
            // Apaga o evento se a resposta for "s"
            switch (resposta) {
                case SIM -> {
                    // Verifica se o evento está na lista antes de tentar removê-lo
                    if (eventosCriados.contains(eventoSelecionado)){
                        eventosCriados.remove(eventoSelecionado); // Remove o evento da lista
                        System.out.println("Evento apagado com sucesso!");
                    } else {
                        System.out.println("Evento não encontrado.");

                    }
                }
                case NAO -> {
                    // Mensagem de cancelamento se a resposta for "n"
                    System.out.println("Operação cancelada.");
                    menu3.nextLine();
                }
                default -> System.out.println("Rsposta inválida. Por favor, insira 's' ou 'n'.");
            }
        }

        // mostrar lista de eventos que restam
        System.out.println("Eventos restantes: " + eventosCriados);
    }
    
    // Método para imprimir o menu principal do promotor
    private static void imprimeMenuPromotor(){
        System.out.print("|==   Sistema Arena-eSpace  ==|\n");
        System.out.print("|       1. Gerir Eventos      |\n");   
        System.out.print("|       2. Criar Eventos      |\n");
        System.out.print("|          0.  Sair           |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("Digite a opção:"); // Solicita ao usuário que insira uma opção
    }
    
    // Método para imprimir o menu de gerenciar de eventos
    private static void imprimeMenuGerirEventos(){
        System.out.print("|==   Sistema Arena-eSpace  ==|\n");
        System.out.print("|    3. Vizualizar Reservas   |\n");   
        System.out.print("|      4. Editar Reservas     |\n");
        System.out.print("|      5. Remover Eventos     |\n");
        System.out.print("|       6. Menu Anterior      |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("Digite a opção:"); // Solicita ao usuário que insira uma opção
    }
    
    // Método para mostrar o menu do promotor e devolver a opção selecionada
    private OpcaoMenu3 mostrarMenuEDevolverOpcaoSelecionada(){ 
        // Exibe o menu do promotor
        imprimeMenuPromotor(); 
        int opcao = menu3.nextInt();// Lê a opção selecionada pelo usuário
        menu3.nextLine(); // Consome a nova linha
        return OpcaoMenu3.getFromCodigo(opcao); // Retorna a opção correspondente ao código inserido
    }
    
    // Método para mostrar o menu de gerenciamento de eventos e devolver a opção selecionada
    private OpcaoGerirEventos mostrarMenuEDevolverOpcaoSetada(){
        // Exibe o menu de gerenciamento de eventos
        imprimeMenuGerirEventos();
        int opcao = menu3.nextInt(); // Lê a opção selecionada pelo usuário
        menu3.nextLine(); // Consome a nova linha
        return OpcaoGerirEventos.getFromCodigo(opcao); // Retorna a opção correspondente ao código inserido
    }
    
    // enum para respostas sim/não
    public enum Resposta {
        SIM("s"), // Representa a resposta "sim"
        
        NAO("n"); // Representa a resposta "não"

        private final String valor; // Valor associado à resposta

        // Construtor do enum
        Resposta(String valor) {
            this.valor = valor;
        }

        // Método para obter o valor da resposta
        public String getValor() {
            return valor;
        }

        // Método estático para converter uma string em uma resposta enum
        public static Resposta fromString(String valor) {
            for (Resposta resposta : Resposta.values()) {
                // Verifica se o valor fornecido corresponde a uma das respostas
                if (resposta.getValor().equalsIgnoreCase(valor)) {
                    return resposta; // Retorna a resposta correspondente
                }
            }
            return null; // Retorna null se não encontrar uma resposta válida
        }
    }
   
    // classe enum para representar o privilégio do utilizador
    public enum Privilegio {
    
    ADMIN(1), // Privilégio de administrador
    
    PROMOTOR(2); // Privilégio de promotor

    private final int codigoPrivilegio; // Código associado ao privilégio

    // Construtor do enum
    Privilegio(int codigoPrivilegio) {
        this.codigoPrivilegio = codigoPrivilegio;
    }

    // Método estático para obter um privilégio a partir de um código
    public static Privilegio getFromCodigo(int codigoPrivilegio) {
        for (Privilegio privilegio : values()) {
            // Verifica se o código fornecido corresponde a um dos privilégios
            if (privilegio.codigoPrivilegio == codigoPrivilegio) {
                return privilegio; // Retorna o privilégio correspondente
            }
        }
        return null; // Retorna null se não encontrar
        }
    
    }
    
    // classe enum para representar opções do menu principal
    public enum OpcaoMenu3 {
        GERIREVENTOS(1), // Opção para gerenciar eventos
        
        CRIAREVENTOS(2),// Opção para criar eventos
        
        SAIR(0); // Opção para sair


        private final int codigoMenu3; // Código associado à opção

        // Construtor do enum
        OpcaoMenu3(int codigoMenu3){
            this.codigoMenu3 = codigoMenu3;
        }

         // Método estático para obter uma opção do menu a partir de um código
        public static OpcaoMenu3 getFromCodigo(int codigoMenu){
            OpcaoMenu3[] todasAsOpcoes = OpcaoMenu3.values();
            for (OpcaoMenu3 opcao : todasAsOpcoes){
                // Verifica se o código fornecido corresponde a uma das opções
                if (opcao.codigoMenu3 == codigoMenu){
                    return opcao; // Retorna a opção correspondente
                }
            }
            return null; // Retorna null se não encontrar
        }   
    }

    // classe enum para representar opções do menu de gerenciamento de eventos
    public enum OpcaoGerirEventos {
        
        VIZUALIZARRESERVAS(3), // Opção para visualizar reservas

        EDITARRESERVAS(4), // Opção para editar reservas

        REMOVEREVENTOS(5), // Opção para remover eventos

        MENUANTERIOR(6), // Opção para voltar ao menu anterior

        SAIR(0); // Opção para sair
    
    
    private final int codigoGerirEventos; // Código associado à opção
    
        // Construtor do enum
        OpcaoGerirEventos(int codigoGerirEventos){
            this.codigoGerirEventos = codigoGerirEventos;
        }

        // Método estático para obter uma opção de gerenciamento de eventos a partir de um código
        public static OpcaoGerirEventos getFromCodigo(int codigoGerirEventos){
            OpcaoGerirEventos[] todasAsOpcoes = OpcaoGerirEventos.values();
            for (OpcaoGerirEventos opcao : todasAsOpcoes){
                // Verifica se o código fornecido corresponde a uma das opções
                if (opcao.codigoGerirEventos == codigoGerirEventos){
                    return opcao; // Retorna a opção correspondente
                }
            }

            return null; // Retorna null se não encontrar
        }   
    }
}    
    // Método toString que retorna uma representação em String do promotor
    @Override
    public String toString() {
        // Retorna uma string formatada com o nome completo do promotor e seu email de contacto
        return "Promotor: " + getNomeCompleto() + " (Contacto: " + getEmail() + ")";
    }
}
