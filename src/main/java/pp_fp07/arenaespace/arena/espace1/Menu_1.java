
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
    
    private final List<Eventos> eventosCriados; // Declara uma lista imutável de eventos criados, que armazenará objetos do tipo Eventos
    public static Promotor promotorLogado; // Declara uma variável estática que guarda a instância do promotor que está logado no sistema
    private final Scanner menu; // Declara um objeto Scanner que será utilizado para ler a entrada do utilizador a partir do console
    private final List<Utilizador> utilizadoresCadastrados = new ArrayList<>();  // Cria uma lista que armazenará os utilizadores cadastrados no sistem
    
    // Construtor da classe
    public Menu_1(Scanner menu, List<Utilizador> utilizadoresCadastrados, List<Eventos> eventosCriados) {
        this.menu = menu; // Inicializa o objeto Scanner com o parâmetro fornecido
        this.eventosCriados = eventosCriados; // Inicializa a lista de eventos criados com a lista fornecida
    }
   
    public void executa() {
        try (menu) {
            
            OpcaoMenu1 opcao = mostrarMenuEDevolverOpcaoSelected();  // Mostra o menu e devolvo a opção selecionada
            while (opcao != OpcaoMenu1.SAIR) {  // Enquanto a opção for diferente de SAIR, o looping continua
                switch (opcao) {
                    case OpcaoMenu1.REGISTO -> {
                        Utilizador utilizador = cadastroDeUtilizador(); // Cadastra um Utilizador
                        if (utilizador != null) { // Verifica se o cadastro foi bem-sucedido
                            utilizadoresCadastrados.add(utilizador); // Adiciona o utilizador na Lista 
                            utilizador.imprimeDados(); // E exibo seus dados cadastrados (exceto password). 
                        }
                    }
                    case OpcaoMenu1.LOGIN -> loginDoUtilizador(menu, utilizadoresCadastrados); // Eu faço o login do utilizador existente na lista de utilizador
                    default -> System.out.println("Opção Inválida");
                }
                
                // Atualiza a opção para mostrar o menu novamente
                opcao = mostrarMenuEDevolverOpcaoSelected(); // Mostra o menu e devolvo a opção selecionada
            }       
            System.out.print("\nAté Logo!!"); // Fecha o menu, encerrando atividade
        } 
    }
    
    // Método responsável pelo login do utilizador
    private void loginDoUtilizador(Scanner menu, List<Utilizador> utilizadorList) {
        boolean continuar = true;  // Variável para controlar o loop de login
        int tentativas = 0; // Contador de tentativas de login

        while (continuar) { // Loop para permitir tentativas de login
            System.out.print("Nome de Utilizador: "); // Solicita o nome de utilizador
            String nomeDeUtilizador = menu.nextLine(); // Lê o nome de utilizador

            System.out.print("Password: "); // Solicita a password
            String password = menu.nextLine(); // Lê a password

            // Verifica se as credenciais forneidas são válidas
            if (nomeDeUtilizador.isEmpty() || password.isEmpty()) {
                System.out.println("Credenciais inválidas! Tente novamente.");
                tentativas++; // Incrementa o contador de tentativas
                continue; // Volta ao início do loop
            }

            // Itera sobre a lista de utilizadores para verificar as credenciais
            for (Utilizador utilizador : utilizadorList) {
                // Verifica se o nome de utilizador e a password estão corretos
                if (utilizador.getNomeDeUtilizador().equals(nomeDeUtilizador) && utilizador.getPassword().equals(password)) {
                    System.out.println("Login realizado com sucesso!");

                    // Usando switch para determinar o tipo de utilizador e redirecionar para o menu correspondente
                    switch (utilizador.getPrivilegio().toLowerCase()) {
                        case "promotor" -> {
                            promotorLogado = (Promotor) utilizador; // Atribui o promotor logado
                            PromotorMenu3 menuPromotor = new PromotorMenu3(menu, this, eventosCriados); // Cria o menu do promotor
                            menuPromotor.executa3(); // executa o menu do promotor
                        }
                        case "admin" -> {
                            AdminMenu2 adminMenu = new AdminMenu2(menu, utilizadorList, this, eventosCriados); // Cria o menu do admin
                            adminMenu.executa2(); // executa o menu do admin
                        }
                        default -> System.out.println("O utilizador não tem privilégios adequados.");
                    }
                    return; // Sai do método após login bem-sucedido
                }
            }

            // Se as credenciais não forem válidas
            System.out.println("Credenciais Inválidas! Tente novamente.");
            tentativas++; // Incrementa o contador de tentativas

            // Limite de tentativas
            if (tentativas >= 2) {
                System.out.println("Excedeu o número máximo de tentativas. Retornando ao menu principal....");
                return; // Retorna ao menu principal
            }
        }
    } 
    
    // Método responsável pelo cadastro de um novo utilizador
    private Utilizador cadastroDeUtilizador() {
        System.out.println("Digite o seu Nome Completo: "); // Solicita o nome completo
        String nomeCompleto = menu.nextLine(); // Lê o nome completo

        String nomeDeUtilizador;
        while (true) { // Loop para garantir que o nome de utilizador é único
            System.out.println("Digite o seu Nome de Utilizador: "); // Solicita um nome de utilizador
            nomeDeUtilizador = menu.nextLine(); // Lê o nome de utilizador

            // Verifica se o nome de utilizador já existe cadastrado no sistema
            if (isNomeDeUtilizadorUnico(nomeDeUtilizador)) {
                break; // Sai do loop se o nome de utilizador for único e não existir no sistema
            } else {
                System.out.println("Nome de utilizador já existe! Por favor, escolha outro.");
            }
        }

        String email;
        while (true) { // Loop para garantir que o email é válido
            System.out.println("Digite o seu email: "); // Solicita o email
            email = menu.nextLine(); // Lê o email

            // Valida o formato do email
            if (isEmailValido(email)) {
                break; // Sai do loop se o email for válido
            } else {
                System.out.println("Email inválido! Por favor, digite um email válido.");
            }
        }

        System.out.println("Digite a sua password: "); // Solicita password
        String password = menu.nextLine(); // Lê a password fornecida pelo utilizador
        
        System.out.println("Selecione um privilégio: "); // Solicita ao utilizador que selecione um privilégio
        System.out.println("1. 'admin' "); // Opção para privilégio de admin
        System.out.println("2. 'promotor' "); // Opção para privilégio de promotor

        int opcaoPrivilegio; // Variável para armazenar a opção de privilégio escolhida

        try {
            opcaoPrivilegio = Integer.parseInt(menu.nextLine()); // Lê a opção e converte para um inteiro
        } catch (NumberFormatException e) { // Captura exceção caso a conversão falhe
            System.out.println("Opção inválida!! Digite o número da opção."); // Mensagem de erro para entrada inválida
            return null; // Retorna null indicando que o cadastro não foi bem-sucedido
        }

        // Obtém a opção correspondente ao número digitado
        Privilegio privilegio = Privilegio.getFromCodigo(opcaoPrivilegio); // Chama método para obter o privilégio a partir do código
        if (privilegio == null || (privilegio != Privilegio.ADMIN && privilegio != Privilegio.PROMOTOR)) {  // Verifica se o privilégio é válido (se não é nulo e se é um dos tipos permitidos)
            System.out.println("Privilégio inválido!!"); // Mensagem de erro para privilégio inválido
            return null; // Retorna null indicando que o cadastro não foi bem-sucedido
        }
        
        // Cria um novo objeto 'Utilizador' ou 'Promotor' com as informações fornecidas
        Utilizador utilizador;
        if (privilegio == Privilegio.ADMIN) {
            // Se o privilégio for ADMIN, cria um novo utilizador do tipo Utilizador
            utilizador = new Utilizador(nomeCompleto, nomeDeUtilizador, email, password, privilegio.name());
        } else {
            // Se o privilégio for PROMOTOR, cria um novo utilizador do tipo Promotor
            utilizador = new Promotor(nomeCompleto, nomeDeUtilizador, email, password, privilegio.name());
        }

        // Insere o utilizador na lista
        utilizadoresCadastrados.add(utilizador);
         // Retorna o objeto 'utilizador' criado, que contém os dados do novo utilizador
        return utilizador;
    }

    // Método para verificar se o nome de utilizador é único
    private boolean isNomeDeUtilizadorUnico(String nomeDeUtilizador) {
        // Percorre a lista de utilizadores cadastrados
        for (Utilizador utilizador : utilizadoresCadastrados) {
            // Verifica se o nome de utilizador já existe (ignorando maiúsculas/minúsculas)
            if (utilizador.getNomeDeUtilizador().equalsIgnoreCase(nomeDeUtilizador)) {
                return false; // Nome de utilizador já existe, retorna falso
            }
        }
        return true; // Nome de utilizador é único, retorna verdadeiro
    }

    // Método para validar o formato do email
    private boolean isEmailValido(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Regex para validar o formato do email
        Pattern pattern = Pattern.compile(regex); // Compila a expressão regular
        return pattern.matcher(email).matches(); // Retorna verdadeiro se o email corresponder ao padrão
    }
    
    // Enum para as opções do menu principal
    public enum OpcaoMenu1 {
    
    LOGIN(1), // Opção de login
    
    REGISTO(2), // Opção de registro
    
    SAIR(0); // Opção de sair
    
    
    private final int codigoMenu1; // Código associado à opção do menu
    
    // Construtor da enumeração
    OpcaoMenu1(int codigoMenu1){
        this.codigoMenu1 = codigoMenu1; // Inicializa o código do menu
    }
    
    // Método estático para obter a opção a partir do código
    public static OpcaoMenu1 getFromCodigo(int codigoMenu){
        OpcaoMenu1[] todasAsOpcoes = OpcaoMenu1.values(); // Obtém todas as opções do enum
        for (OpcaoMenu1 opcao : todasAsOpcoes){
            if (opcao.codigoMenu1 == codigoMenu){ // Verifica se o código da opção corresponde ao código fornecido
                return opcao; // Retorna a opção correspondente ao código fornecido
            }
        }
        return null; // Retorna null se nenhum código corresponder a uma opção válida
        }
    }
    
    // Método privado para mostrar o menu principal e devolver a opção selecionada
    private OpcaoMenu1 mostrarMenuEDevolverOpcaoSelected() {
    /* Método criado para mostrar o menu e devolver a opção selecionada,
       privado e só pode ser acessado pela própria classe */
    
    OpcaoMenu1 opcaoSelecionada = null; // Inicializa a variável para guardar a opção selecionada
    boolean entradaValida = false; // Flag para controlar a validade da entrada

    while (!entradaValida) { // Loop até que uma entrada válida seja recebida
        imprimeMenuPrincipal(); // Chama o método para imprimir o menu principal
        
        try {
            int opcao = menu.nextInt(); // Lê a opção do utilizador     
            menu.nextLine(); // Limpa o buffer após a leitura

            opcaoSelecionada = OpcaoMenu1.getFromCodigo(opcao); // Tenta converter a opção para um valor do enum

            if (opcaoSelecionada != null) { 
                entradaValida = true; // Se a opção é válida, sai do loop
            } else {
                System.out.println("Opção inválida. Tente novamente."); // Mensagem de erro para opção inválida
            }
        } catch (InputMismatchException e) { // Captura exceção caso a entrada não seja um número
            System.out.println("Por favor, insira um número válido."); // Mensagem de erro para entrada não numérica
            menu.nextLine(); // Limpa o buffer para evitar loop infinito
        }
    }

    return opcaoSelecionada; // Retorna a opção válida selecionada pelo utilizador
}
    
    private static void imprimeMenuPrincipal() { // Método estático para imprimir o menu principal
        System.out.print("|==   Sistema Arena-eSpace  ==|\n");
        System.out.print("|          1. login           |\n");   
        System.out.print("|          2. Registo         |\n");
        System.out.print("|          0.  Sair           |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("Digite a opção:"); // Solicita que o utilizador digite a opção desejada
    }
    
}