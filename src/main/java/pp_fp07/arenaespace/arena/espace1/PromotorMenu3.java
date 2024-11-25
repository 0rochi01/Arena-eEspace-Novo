/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp07.arenaespace.arena.espace1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static pp_fp07.arenaespace.arena.espace1.Menu_1.promotorLogado;

/**
 *
 * @author RyanS
 */
public class PromotorMenu3 {

    private final Menu_1 menuPrincipal;
    private final Scanner menu3;
    private final List<Eventos> eventosCriados;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    
    public PromotorMenu3(Scanner menu3, Menu_1 menuPrincipal, List<Eventos> eventosCriados) {
        this.menu3 = menu3;
        this.menuPrincipal = menuPrincipal;
        this.eventosCriados = eventosCriados;
    }
    
    public void executa3() {
        System.out.println("Bem-vindo Promotor de Eventos!");

        OpcaoMenu3 opcao = mostrarMenuEDevolverOpcaoSelecionada();

        do {
            switch (opcao) {
                case GERIREVENTOS -> gerenciarEventos();
                case CRIAREVENTOS -> criarEventos();
                case SAIR -> {
                    System.out.print("\nAté Logo!!");
                    menuPrincipal.executa();
                }
                default -> System.out.println("Opção Inválida");
            }
            opcao = mostrarMenuEDevolverOpcaoSelecionada(); // Atualiza a opção para o próximo loop
        } while (opcao != OpcaoMenu3.SAIR);
    }
    
    private void gerenciarEventos() {
        OpcaoGerirEventos op;
        do {
            op = mostrarMenuEDevolverOpcaoSetada();
            switch (op) {
                case VIZUALIZARRESERVAS -> vizualizarReservas();
                case EDITARRESERVAS -> editarReservas();
                case REMOVEREVENTOS -> removerEvento();
                case MENUANTERIOR -> {
                }
                case SAIR -> {
                    System.out.print("\nAté Logo!!");
                    menuPrincipal.executa();
                }
                default -> System.out.println("Opção Inválida");
            }
        } while (op != OpcaoGerirEventos.MENUANTERIOR);
    }
    
    private void criarEventos() {
        boolean continuar = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        final int LIMITE_EVENTOS = 2; // Limite de eventos
        int contadorEventosCriados = 0;

        while (continuar) {
            if (contadorEventosCriados >= LIMITE_EVENTOS) {
                System.out.println("Limite de " + LIMITE_EVENTOS + " eventos já atingido. Não é possível criar mais eventos.");
                break; // Encerra o loop se o limite for atingido
            }

            String titulo = solicitarEntrada("Digite o título do Evento:");
            LocalDateTime dataHora = solicitarDataHora(formatter);
            String sala = solicitarEntrada("Digite a Sala do Evento:");

            // Verifica se já existe um evento na mesma data e sala
            if (verificarConflitoEventos(dataHora, sala)) {
                System.out.println("Já existe um evento na mesma data e sala. Tente novamente.");
                continue; // Retorna ao início do loop para criar um novo evento
            }

            String modalidade = solicitarEntrada("Escolha a modalidade do seu Evento:");
            int numeroMaximoDeParticipantes = solicitarNumeroMaximoParticipantes();

            // Verifica se o número máximo de participantes é válido
            if (numeroMaximoDeParticipantes <= 0) {
                System.out.println("Número máximo de participantes deve ser um número positivo. Tente novamente.");
                continue; // Retorna ao início do loop
            }

            String condicoesInscricao = solicitarEntrada("Digite as condições para participação no Evento:");

            // Cria o evento utilizando a variável promotorLogado
            Eventos evento = new Eventos(
                titulo,
                dataHora,
                sala,
                condicoesInscricao,
                modalidade,
                numeroMaximoDeParticipantes,
                Menu_1.promotorLogado.getNomeCompleto(),
                Menu_1.promotorLogado.getEmail()
            );

            eventosCriados.add(evento); // Adiciona o evento à lista de eventos criados
            contadorEventosCriados++; // Incrementa o contador de eventos criados

            System.out.println("Evento criado com sucesso:");
            System.out.println(evento.toString());

            // Pergunta se o usuário deseja criar outro evento
            continuar = perguntarSeDesejaCriarOutroEvento();
        }
    }
    
    private void vizualizarReservas(){
        if (eventosCriados.isEmpty()){
            System.out.println("Não existe reservas efetuadas");
        } else {
            for (Eventos evento : eventosCriados){
                System.out.println(evento.toString()); // Exibe detalhes do evento 
            }
           
        }
    }
    
    private void editarReservas() {
        if (eventosCriados.isEmpty()) {
            System.out.println("Não existem eventos cadastrados para editar.");
            return;
        }

        // Exibir eventos existentes
        System.out.println("Eventos cadastrados:");
        for (int i = 0; i < eventosCriados.size(); i++) {
            Eventos evento = eventosCriados.get(i);
            System.out.println((i + 1) + ". " + evento.toString());
        }

        // Solicitar ao usuário para escolher um evento para editar
        int eventoIndex = -1;
        while (eventoIndex < 0 || eventoIndex >= eventosCriados.size()) {
            System.out.println("Digite o número do evento que deseja editar:");
            if (menu3.hasNextInt()) {
                eventoIndex = menu3.nextInt() - 1; // Ajusta para índice zero
                menu3.nextLine(); // Consumir a nova linha
                if (eventoIndex < 0 || eventoIndex >= eventosCriados.size()) {
                    System.out.println("Número de evento inválido. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                menu3.nextLine(); // Limpar a entrada inválida
            }
        }

        Eventos eventoSelecionado = eventosCriados.get(eventoIndex);

        // Editar título
        String novoTitulo = solicitarEntrada("Título atual: " + eventoSelecionado.getTitulo() + 
                                            "\nDigite o novo título (ou pressione Enter para manter o atual):");
        if (!novoTitulo.isEmpty()) {
            eventoSelecionado.setTitulo(novoTitulo);
        }

        // Editar data e hora
        String novaDataHoraInput = solicitarEntrada("Data e Hora atual: " + eventoSelecionado.getDataHora().format(formatter) + 
                                                    "\nDigite a nova data e hora (dd/MM/yyyy HH:mm) ou pressione Enter para manter a atual:");
        if (!novaDataHoraInput.isEmpty()) {
            if (validarDataHora(novaDataHoraInput)) {
                try {
                    LocalDateTime novaDataHora = LocalDateTime.parse(novaDataHoraInput, formatter);
                    eventoSelecionado.setDataHora(novaDataHora);
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
            eventoSelecionado.setSala(novaSala);
        }

        // Exibir mensagem de sucesso
        System.out.println("Evento editado com sucesso:");
        System.out.println(eventoSelecionado.toString());
    }
    
    private boolean perguntarSeDesejaCriarOutroEvento() {
        while (true) {
            System.out.println("Deseja criar outro evento? (s/n)");
            String resposta = menu3.nextLine().trim();

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

    private String solicitarEntrada(String mensagem) {
        System.out.println(mensagem);
        return menu3.nextLine();
    }

    private boolean validarDataHora(String dataHoraInput) {
        return dataHoraInput.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}");
    }
    
    private LocalDateTime solicitarDataHora(DateTimeFormatter formatter) {
        LocalDateTime dataHora = null;
        while (dataHora == null) {
            String dataHoraInput = solicitarEntrada("Digite a Data e a Hora do seu evento (dd/MM/yyyy HH:mm):");
            try {
                dataHora = LocalDateTime.parse(dataHoraInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data e hora inválido. Tente novamente.");
            }
        }
        return dataHora;
    }
    
    private boolean verificarConflitoEventos(LocalDateTime dataHora, String sala) {
        return eventosCriados.stream()
            .anyMatch(evento -> evento.getDataHora().isEqual(dataHora) && evento.getSala().equalsIgnoreCase(sala));
    }
    
    private int solicitarNumeroMaximoParticipantes() {
        int numeroMaximoDeParticipantes = -1; // Valor inicial inválido
        while (numeroMaximoDeParticipantes < 0) {
            System.out.println("Digite um número máximo de participantes:");
            try {
                numeroMaximoDeParticipantes = menu3.nextInt();
                menu3.nextLine(); // Consome a nova linha
                if (numeroMaximoDeParticipantes < 0) {
                    System.out.println("O número máximo de participantes deve ser um número positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                menu3.nextLine(); // Limpa o buffer do scanner
            }
        }
        return numeroMaximoDeParticipantes;
    }
    
    private void removerEvento() {
    if (eventosCriados.isEmpty()) {
        System.out.println("Não existem eventos criados.");
        return;
    }

    // Exibe a lista de eventos
    System.out.println("Eventos criados:");
    for (int i = 0; i < eventosCriados.size(); i++) {
        System.out.println((i + 1) + ". " + eventosCriados.get(i).getTitulo());
    }

    // Solicita ao utilizador que selecione um evento
    System.out.println("Digite o número do evento que deseja visualizar ou apagar (ou 0 para voltar):");
    int escolha = menu3.nextInt();
    menu3.nextLine(); // Consumir o caracter de nova linha

    if (escolha == 0) {
        return; // Voltar ao menu anterior
    }

    // Verifica se a escolha é válida
    if (escolha < 0 || escolha > eventosCriados.size()) {
        System.out.println("Opção inválida.");
        return;
    }

    // Obter o evento selecionado
    Eventos eventoSelecionado = eventosCriados.get(escolha - 1);

    // Exibe os detalhes do evento
    System.out.println("\nDetalhes do evento:");
    System.out.println(eventoSelecionado.toString());

    // Solicita ao utilizador se deseja apagar o evento
    System.out.println("Deseja apagar este evento? (s/n)");
    String respostaSN = menu3.nextLine();
    Resposta resposta = Resposta.fromString(respostaSN);

    if (null == resposta) {
        System.out.println("Rsposta inválida. Por favor, insira 's' ou 'n'.");
    } else // Apaga o evento se a resposta for "s"
        switch (resposta) {
            case SIM -> {
                if (eventosCriados.contains(eventoSelecionado)){
                    eventosCriados.remove(eventoSelecionado);
                    System.out.println("Evento apagado com sucesso!");
                } else {
                    System.out.println("Evento não encontrado.");
                    
                }
        }
            case NAO -> {
                System.out.println("Operação cancelada.");
                menu3.nextLine();
        }
            default -> System.out.println("Rsposta inválida. Por favor, insira 's' ou 'n'.");
        }
    
    // mostrar lista de eventos que restam
    System.out.println("Eventos restantes: " + eventosCriados);
}
    
    private static void imprimeMenuPromotor(){
        System.out.print("|==   Sistema Arena-eSpace  ==|\n");
        System.out.print("|       1. Gerir Eventos      |\n");   
        System.out.print("|       2. Criar Eventos      |\n");
        System.out.print("|          0.  Sair           |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("Digite a opção:");
    }
    
    
    
    private static void imprimeMenuGerirEventos(){
        System.out.print("|==   Sistema Arena-eSpace  ==|\n");
        System.out.print("|    3. Vizualizar Reservas   |\n");   
        System.out.print("|      4. Editar Reservas     |\n");
        System.out.print("|      5. Remover Eventos     |\n");
        System.out.print("|       6. Menu Anterior      |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("Digite a opção:");
    }
    
    private OpcaoMenu3 mostrarMenuEDevolverOpcaoSelecionada(){ /*Método criado para mostrar o menu e devolver a opção selecionada, privado e só pode ser acessado pela própria classe*/
        imprimeMenuPromotor();
        int opcao = menu3.nextInt();
        menu3.nextLine();
        return OpcaoMenu3.getFromCodigo(opcao);
    }
    
    private OpcaoGerirEventos mostrarMenuEDevolverOpcaoSetada(){ /*Método criado para mostrar o menu e devolver a opção selecionada, privado e só pode ser acessado pela própria classe*/
        imprimeMenuGerirEventos();
        int opcao = menu3.nextInt();
        menu3.nextLine();
        return OpcaoGerirEventos.getFromCodigo(opcao);
    }
    
    public enum Resposta {
        SIM("s"),
        NAO("n");

        private final String valor;

        Resposta(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }

        public static Resposta fromString(String valor) {
            for (Resposta resposta : Resposta.values()) {
                if (resposta.getValor().equalsIgnoreCase(valor)) {
                    return resposta;
                }
            }
            return null; // Retorna null se não encontrar uma resposta válida
        }
    }
   
    public enum Privilegio {
    
    ADMIN(1),
    PROMOTOR(2);

    private final int codigoPrivilegio;

    Privilegio(int codigoPrivilegio) {
        this.codigoPrivilegio = codigoPrivilegio;
    }

    public static Privilegio getFromCodigo(int codigoPrivilegio) {
        for (Privilegio privilegio : values()) {
            if (privilegio.codigoPrivilegio == codigoPrivilegio) {
                return privilegio;
            }
        }
        return null; // Retorna null se não encontrar
        }
    
    }
    
    public enum OpcaoMenu3 {
    GERIREVENTOS(1),
    
    CRIAREVENTOS(2),
    
    SAIR(0);
    
    
    private final int codigoMenu3;
    
        OpcaoMenu3(int codigoMenu3){
            this.codigoMenu3 = codigoMenu3;
        }

        public static OpcaoMenu3 getFromCodigo(int codigoMenu){
            OpcaoMenu3[] todasAsOpcoes = OpcaoMenu3.values();
            for (OpcaoMenu3 opcao : todasAsOpcoes){
                if (opcao.codigoMenu3 == codigoMenu){
                    return opcao;
                }
            }

            return null;
        }   
    }

    public enum OpcaoGerirEventos {
        
        VIZUALIZARRESERVAS(3),

        EDITARRESERVAS(4),

        REMOVEREVENTOS(5),

        MENUANTERIOR(6),

        SAIR(0);
    
    
    private final int codigoGerirEventos;
    
        OpcaoGerirEventos(int codigoGerirEventos){
            this.codigoGerirEventos = codigoGerirEventos;
        }

        public static OpcaoGerirEventos getFromCodigo(int codigoGerirEventos){
            OpcaoGerirEventos[] todasAsOpcoes = OpcaoGerirEventos.values();
            for (OpcaoGerirEventos opcao : todasAsOpcoes){
                if (opcao.codigoGerirEventos == codigoGerirEventos){
                    return opcao;
                }
            }

            return null;
        }   
    }
    
}