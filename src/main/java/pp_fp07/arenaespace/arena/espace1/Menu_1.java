/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp07.arenaespace.arena.espace1;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import pp_fp07.arenaespace.arena.espace1.PromotorMenu3.Privilegio;
/**
 *
 * @author RyanS
 */
public class Menu_1 {
    
    private final List<Eventos> eventosCriados;
    public static Promotor promotorLogado; 
    private final Scanner menu;
    private final List<Utilizador> utilizadoresCadastrados = new ArrayList<>();  /* Eu crio uma lista de UtilizadoresCadastrados */
    
    public Menu_1(Scanner menu, List<Utilizador> utilizadoresCadastrados, List<Eventos> eventosCriados) {
        this.menu = menu;
        this.eventosCriados = eventosCriados;
    }
   
    public void executa() {
        try (menu) {
            
            OpcaoMenu1 opcao = mostrarMenuEDevolverOpcaoSelected();  /* Eu mostro o menu e devolvo a opção selecionada */
            while (opcao != OpcaoMenu1.SAIR) {  /* Enquanto a opção for diferente de SAIR, o looping continua */
                switch (opcao) {
                    case OpcaoMenu1.REGISTO -> {
                        Utilizador utilizador = cadastroDeUtilizador(); /* Eu cadastro um Utilizador */
                        if (utilizador != null) { // Verifica se o cadastro foi bem-sucedido
                            utilizadoresCadastrados.add(utilizador); /* Adiciono o utilizador na Lista */
                            utilizador.imprimeDados(); /* E exibo seus dados cadastrados (exceto password). */
                        }
                    }
                    case OpcaoMenu1.LOGIN -> loginDoUtilizador(menu, utilizadoresCadastrados); /* Eu faço o login do utilizador existente na lista de utilizador */
                    default -> System.out.println("Opção Inválida");
                }
                
                // Atualiza a opção para mostrar o menu novamente
                opcao = mostrarMenuEDevolverOpcaoSelected(); /* Eu mostro o menu e devolvo a opção selecionada */
            }       System.out.print("\nAté Logo!!"); /* O sistema envia uma mensagem de despedida */
            /* E fecha o menu, encerrando atividade */
        } 
    }
    
    private void loginDoUtilizador(Scanner menu, List<Utilizador> utilizadorList) {
        boolean continuar = true;
        int tentativas = 0; // Contador de tentativas

        while (continuar) {
            System.out.print("Nome de Utilizador: ");
            String nomeDeUtilizador = menu.nextLine();

            System.out.print("Password: ");
            String password = menu.nextLine();

            // Verifica se as credenciais são válidas
            if (nomeDeUtilizador.isEmpty() || password.isEmpty()) {
                System.out.println("Credenciais inválidas! Tente novamente.");
                tentativas++;
                continue; // Volta para o início do loop
            }

            // Itera sobre a lista de utilizadores
            for (Utilizador utilizador : utilizadorList) {
                if (utilizador.getNomeDeUtilizador().equals(nomeDeUtilizador) && utilizador.getPassword().equals(password)) {
                    System.out.println("Login realizado com sucesso!");

                    // Usando switch para determinar o tipo de utilizador
                    switch (utilizador.getPrivilegio().toLowerCase()) {
                        case "promotor" -> {
                            promotorLogado = (Promotor) utilizador; // Atribui o promotor logado
                            PromotorMenu3 menuPromotor = new PromotorMenu3(menu, this, eventosCriados); // Passa gerirEventos
                            menuPromotor.executa3(); // Chama o menu do promotor
                        }
                        case "admin" -> {
                            AdminMenu2 adminMenu = new AdminMenu2(menu, utilizadorList, this, eventosCriados); // Passa gerirEventos
                            adminMenu.executa2(); // Chama o menu do admin
                        }
                        default -> System.out.println("O utilizador não tem privilégios adequados.");
                    }
                    return; // Sai do método após login bem-sucedido
                }
            }

            // Se as credenciais não forem válidas
            System.out.println("Credenciais Inválidas! Tente novamente.");
            tentativas++;

            // Limite de tentativas
            if (tentativas >= 2) {
                System.out.println("Excedeu o número máximo de tentativas. Retornando ao menu principal....");
                return; // Retorna ao menu principal
            }
        }
    } 
    

    private Utilizador cadastroDeUtilizador() {
        System.out.println("Digite o seu Nome Completo: ");
        String nomeCompleto = menu.nextLine();

        String nomeDeUtilizador;
        while (true) {
            System.out.println("Digite o seu Nome de Utilizador: ");
            nomeDeUtilizador = menu.nextLine();

            // Verifica se o nome de utilizador já existe
            if (isNomeDeUtilizadorUnico(nomeDeUtilizador)) {
                break; // Sai do loop se o nome de utilizador for único
            } else {
                System.out.println("Nome de utilizador já existe! Por favor, escolha outro.");
            }
        }

        String email;
        while (true) {
            System.out.println("Digite o seu email: ");
            email = menu.nextLine();

            // Valida o formato do email
            if (isEmailValido(email)) {
                break; // Sai do loop se o email for válido
            } else {
                System.out.println("Email inválido! Por favor, digite um email válido.");
            }
        }

        System.out.println("Digite a sua password: ");
        String password = menu.nextLine();

        System.out.println("Selecione um privilégio: ");
        System.out.println("1. 'admin' ");
        System.out.println("2. 'promotor' ");

        int opcaoPrivilegio;

        try {
            opcaoPrivilegio = Integer.parseInt(menu.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!! Digite o número da opção.");
            return null;
        }

        // Obtém a opção correspondente ao número digitado
        Privilegio privilegio = Privilegio.getFromCodigo(opcaoPrivilegio);
        if (privilegio == null || (privilegio != Privilegio.ADMIN && privilegio != Privilegio.PROMOTOR)) {
            System.out.println("Privilégio inválido!!");
            return null;
        }

        Utilizador utilizador;
        if (privilegio == Privilegio.ADMIN) {
            utilizador = new Utilizador(nomeCompleto, nomeDeUtilizador, email, password, privilegio.name());
        } else {
            utilizador = new Promotor(nomeCompleto, nomeDeUtilizador, email, password, privilegio.name());
        }

        // Insere o utilizador na lista
        utilizadoresCadastrados.add(utilizador);
        // Exibe os dados do utilizador
        return utilizador;
    }

    // Método para verificar se o nome de utilizador é único
    private boolean isNomeDeUtilizadorUnico(String nomeDeUtilizador) {
        for (Utilizador utilizador : utilizadoresCadastrados) {
            if (utilizador.getNomeDeUtilizador().equalsIgnoreCase(nomeDeUtilizador)) {
                return false; // Nome de utilizador já existe
            }
        }
        return true; // Nome de utilizador é único
    }

    // Método para validar o formato do email
    private boolean isEmailValido(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
    
    public enum OpcaoMenu1 {
    
    LOGIN(1),
    
    REGISTO(2),
    
    SAIR(0);
    
    
    private final int codigoMenu1;
    
    OpcaoMenu1(int codigoMenu1){
        this.codigoMenu1 = codigoMenu1;
    }
    
    public static OpcaoMenu1 getFromCodigo(int codigoMenu){
        OpcaoMenu1[] todasAsOpcoes = OpcaoMenu1.values();
        for (OpcaoMenu1 opcao : todasAsOpcoes){
            if (opcao.codigoMenu1 == codigoMenu){
                return opcao;
            }
        }
        
        return null;
        }
    }
    
    private OpcaoMenu1 mostrarMenuEDevolverOpcaoSelected() {
    /* Método criado para mostrar o menu e devolver a opção selecionada,
       privado e só pode ser acessado pela própria classe */
    
    OpcaoMenu1 opcaoSelecionada = null; // Inicializa a variável para armazenar a opção selecionada
    boolean entradaValida = false; // Flag para controlar a validade da entrada

    while (!entradaValida) { // Loop até que uma entrada válida seja recebida
        imprimeMenuPrincipal(); // Exibe o menu ao usuário
        
        try {
            int opcao = menu.nextInt(); // Lê a opção do usuário
            menu.nextLine(); // Limpa o buffer

            opcaoSelecionada = OpcaoMenu1.getFromCodigo(opcao); // Tenta converter a opção

            if (opcaoSelecionada != null) {
                entradaValida = true; // Se a opção é válida, sai do loop
            } else {
                System.out.println("Opção inválida. Tente novamente."); // Mensagem de erro para opção inválida
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, insira um número válido."); // Mensagem de erro para entrada não numérica
            menu.nextLine(); // Limpa o buffer para evitar loop infinito
        }
    }

    return opcaoSelecionada; // Retorna a opção válida
}
    
    private static void imprimeMenuPrincipal() { /*Método estático que não retorna nada, criado para o sistema imprimir o menu principal, a classe não precisa ser instanciada para chamar este método*/
        System.out.print("|==   Sistema Arena-eSpace  ==|\n");
        System.out.print("|          1. login           |\n");   
        System.out.print("|          2. Registo         |\n");
        System.out.print("|          0.  Sair           |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("Digite a opção:");
    }
    
}