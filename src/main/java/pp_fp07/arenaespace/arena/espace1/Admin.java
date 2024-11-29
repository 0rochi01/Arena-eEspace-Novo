/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp07.arenaespace.arena.espace1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author RyanS
 */
public class Admin extends Utilizador { 
    
    // Construtor da classe Promotor que inicializa os atributos herdados da classe Utilizador
    public Admin(String NomeCompleto, String NomeDeUtilizador, String email, String password, String Privilegio) {
        // Chama o construtor da classe pai (Utilizador) para inicializar os atributos
        super(NomeCompleto, NomeDeUtilizador, email, password, Privilegio);
    }
    
    static class AdminMenu2 {
    
    private final Scanner menu2; // Declara um objeto Scanner que será utilizado para ler a entrada do utilizador a partir do console
    private final List<Eventos> eventosCriados;//lista de eventos criados
    private final List<Utilizador> utilizadoresCadastrados; // Lista de utilizadores cadastrados
    private final Menu_1 menuPrincipal; // Referência ao menu principal (Menu_1)
    
    // Construtor da classe
    public AdminMenu2(Scanner menu2, List<Utilizador> utilizadoresCadastrados, Menu_1 menuPrincipal, List<Eventos> eventosCriados) {
        this.menu2 = menu2; // Inicializa o scanner para entrada de dados
        this.utilizadoresCadastrados = utilizadoresCadastrados; // Inicializa a lista de utilizadores
        this.menuPrincipal = menuPrincipal; // Inicializa a referencia ao Menu_1
        this.eventosCriados = eventosCriados;//Inicializa a lista de eventos criados
    }
    
    // Método que executa o menu principal do administrador
    public void executa2() {
        System.out.println("Bem-vindo Administrador!");
        
        OpcaoMenu2 opcao; // Variável para armazenar a opção selecionada pelo usuário
        do {
            opcao = mostrarMenuEDevolverOpcaoSelected();  // Mostra o menu e obtém a opção selecionada
            // Executa a ação correspondente à opção selecionada
            switch (opcao) {
                case GestãoDePromotores -> gerenciarPromotores();//Chama o método para a gestão de Promotores
                case GestãoDeEventos -> vizualizarEventosPorPromotr();// Chama o método para gerenciar todos os eventos criados por cada promotor
                case SAIR -> { // Opcão para sair do menu
                    System.out.println("Saindo do menu."); // Mensqagem de saida 
                    menuPrincipal.executa();// Retorna ao Menu principal
                }
                default -> System.out.println("Opção inválida, tente novamente.");// Mensagem de erro para se não corresponde a nenhuma das apresentadas
            }
        } while (opcao != OpcaoMenu2.SAIR);//Quando for selecionado 0 volta para o Menu de Login
    }
    
    private void vizualizarEventosPorPromotr() {//Método para verificar todos os eventos criados por promotor
        // Verifica se existem eventos criados
        if (eventosCriados.isEmpty()) {
            System.out.println("Não existem eventos promovidos.");
            return; // Retorna se não houver eventos
        }

        // Cria uma lista para armazenar nomes de promotores únicos
        List<String> promotores = new ArrayList<>();

        // Coleta os nomes de promotores únicos a partir da lista de eventos
        for (Eventos evento : eventosCriados) {
            String nomePromotor = evento.getNomePromotor(); // Obtém o nome do promotor do evento
            // Adiciona o nome do promotor apenas se não estiver na lista
            if (!promotores.contains(nomePromotor)) {
                promotores.add(nomePromotor); // Adiciona o nome do promotor à lista se for único
            }
        }

        // Exibe a lista de promotores
        System.out.println("Promotores disponíveis:");
        for (String promotor : promotores) {
            System.out.println("- " + promotor); // Exibe o nome do promotor
        }

        // Solicita ao administrador que insira o nome do promotor para visualizar seus eventos
        System.out.print("Digite o nome do promotor para visualizar seus eventos: ");
        String nomePromotor = menu2.nextLine(); // Lê o nome do promotor utilizando o scanner menu2

        boolean encontrouEventos = false;// Variável para verificar se foram encontrados eventos Garantindo que a verificação da existencia de eventos seja sempre feita
        System.out.println("Eventos promovidos por " + nomePromotor + ":");
        // percorre os eventos criados para encontrar aqueles promovidos pelo promotor especificado
        for (Eventos evento : eventosCriados) {
            if (evento.getNomePromotor().equalsIgnoreCase(nomePromotor)) { // Compara o nome do promotor de forma case-insensitive
                System.out.println(evento.toString()); // Exibe detalhes do evento
                encontrouEventos = true; // Marca que foram encontrados eventos
            }
        }

        // Mensagem caso não encontre eventos para o promotor
        if (!encontrouEventos) {
            System.out.println("Nenhum evento encontrado para o promotor: " + nomePromotor);
        }
    }
    
    // Método para gerenciar promotores
    private void gerenciarPromotores() {
        int opcao; // Variável para armazenar a opção selecionada pelo usuário
        do {
            // Exibe o menu de gestão de promotores
            System.out.println("\n|==  Gestão de Promotores  ==|");
            System.out.println("|    1. Listar Promotores    |");
            System.out.println("|    2. Remover Promotor     |");
            System.out.println("|    3. Editar Promotor      |");
            System.out.println("|    0. Voltar               |"); // Opção para voltar ao menu anterior
            System.out.print("|----------------------------|\n");
            opcao = lerInteiro("Digite a opção: ");

            switch (opcao) { //Lê a opção selecionada
                case 1 -> listarPromotores(); // Chama o método para listar promotores
                case 2 -> removerPromotor(); // Chama o método para remover um promotor
                case 3 -> editarPromotor(); // Chama o método para editar um promotor
                case 0 -> System.out.println("Voltando ao menu principal.");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0); // Continua exibindo o menu até que a opção 0 (voltar) seja selecionada
    }
    
    // Método para listar promotores cadastrados
    private void listarPromotores() {
        // Verifica se existem promotores cadastrados na lista de utilizadores
        if (utilizadoresCadastrados.isEmpty()) {
            System.out.println("Não existem promotores cadastrados.");
        } else {//Apresenta a lista dos promotores caso eles existam
            System.out.println("Lista de Promotores:");
            for (Utilizador utilizador : utilizadoresCadastrados) { // percorre a lista de utilizadores cadastrados
                System.out.println(utilizador); // Exibe as informações de cada promotor
            }
        }
    }
    
    //Método para remover Promotores
    private void removerPromotor() {
        if(utilizadoresCadastrados.isEmpty()){//Verifica se existem promotores cadastrados
            System.out.println("Não existe promotores cadstrados.");
        } else {//Pede o nome do promtor após verificar que a lista de promotores cadastrados não está vazia
            String nome = lerString("Digite o nome do promotor a ser removido: ");
        boolean encontrado = false; // Variável para verificar se o promotor foi encontrado

        // percorre a lista e verifica se o promotor está na lista de utilizadores cadastrados
        for (int i = 0; i < utilizadoresCadastrados.size(); i++) {
            // Verifica se o nome do promotor está contido na representação em string do utilizador
            if (utilizadoresCadastrados.get(i).toString().contains(nome)) {
                utilizadoresCadastrados.remove(i); // Remove o promotor da lista
                System.out.println("Promotor removido com sucesso!");
                encontrado = true; // Marca que o promotor foi encontrado e removido
                break; // Sai do loop após a remoção
            }
        }

        if (!encontrado) {//Se o nome do Promotor inserido não existir na lista 
            System.out.println("Promotor não encontrado.");
            }
        }
        
    }
    
    private void editarPromotor() {//Método para alterar qualquer dado de um Promotor
    if (utilizadoresCadastrados.isEmpty()) {//Verifica se existem promotores cadastrados
        System.out.println("Não existem promotores cadastrados.");
        return;
    }

    String nome = lerString("Digite o nome do promotor a ser editado: ");
    boolean encontrado = false;

    // Procura o promotor na lista de utilizadores cadastrados
    for (Utilizador utilizador : utilizadoresCadastrados) {
        if (utilizador instanceof Promotor && utilizador.getNomeDeUtilizador().equalsIgnoreCase(nome)) {
            encontrado = true;

            // Exibir informações atuais do promotor
            System.out.println("Informações atuais do promotor:");
            System.out.println(utilizador);

            // Solicitar novas informações
            String novoNome = lerString("Digite o novo nome (ou pressione Enter para manter o mesmo): ");
            String novoEmail = lerString("Digite o novo email (ou pressione Enter para manter o mesmo): ");
            String novaSenha = lerString("Digite a nova senha (ou pressione Enter para manter a mesma): ");

            // Atualizar informações se o usuário fornecer novos dados
            if (!novoNome.isEmpty()) { // Verifica se um novo nome foi fornecido
                utilizador.setNomeDeUtilizador(novoNome); // Atualiza o nome de utilizador com o novo nome
            }
            if (!novoEmail.isEmpty()) { // Verifica se um novo email foi fornecido
                utilizador.setEmail(novoEmail); // Atualiza o email do utilizador com o novo email
            }
            if (!novaSenha.isEmpty()) { //...
                utilizador.setPassword(novaSenha); //...
            }

            System.out.println("Promotor atualizado com sucesso!");
            break; // Sai do loop após encontrar e editar o promotor
        }
    }

    if (!encontrado) {//Caso o nome do Promotor inserido não exista
        System.out.println("Promotor não encontrado.");
        }
    }

    private OpcaoMenu2 mostrarMenuEDevolverOpcaoSelected() {//Exibe o menu para o admin
        imprimeMenuAdmin(); // Chama o método que imprime o menu admin
        int opcao = menu2.nextInt(); // Lê a opção escolhida pelo usuário
        menu2.nextLine();
        return OpcaoMenu2.getFromCodigo(opcao); // Converte o código da opção em um enum e o retorna
    }
    
    //Método que imprime o menu admin
    private static void imprimeMenuAdmin() {
        System.out.print("|==   Sistema Arena-eSpace  ==|\n");
        System.out.print("|   1. Gestão de Promotores   |\n");
        System.out.print("|    2. Gestão de Eventos     |\n");
        System.out.print("|          0.  Sair           |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("Digite a opção:");//Solicita que seja inserida uma opção
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem); // Exibe a mensagem para o usuário solicitando a entrada
        while (true) { // Loop infinito até que uma entrada válida seja fornecida
            try {
                // Tenta converter a entrada do usuário para um inteiro
                return Integer.parseInt(menu2.nextLine());
            } catch (NumberFormatException e) {
                // Se ocorrer uma exceção, informa ao usuário que a entrada foi inválida
                System.out.print("Entrada inválida. " + mensagem);
            }
        }
    }

    private String lerString(String mensagem) {
        System.out.print(mensagem); // Exibe a mensagem para o usuário solicitando a entrada
        // Lê a linha de entrada do usuário e retorna como uma String
        return menu2.nextLine(); // Captura a entrada do usuário e a retorna
    }
    
    // enum para as opções do menu princiapl do admin
    public enum OpcaoMenu2 {
    
    GestãoDePromotores(1),//Opção de gestão de Promotores
    
    GestãoDeEventos(2),//Opção de gestão de Eventos
    
    SAIR(0);//Opção de sair
    
    
    private final int codigoMenu2; // Atributo que armazena o código associado a cada opção do menu
    
    // Construtor da enumeração que recebe o código da opção
    OpcaoMenu2(int codigoMenu2){
        this.codigoMenu2 = codigoMenu2;
    }
    
    // Método estático que retorna a opção do menu correspondente ao código fornecido
    public static OpcaoMenu2 getFromCodigo(int codigoMenu){
        // Obtém todas as opções da enumeração
        OpcaoMenu2[] todasAsOpcoes = OpcaoMenu2.values();
        for (OpcaoMenu2 opcao : todasAsOpcoes){ // Percorre todas as opções para encontrar a que corresponde ao código
            if (opcao.codigoMenu2 == codigoMenu){
                return opcao; // Retorna a opção correspondente se encontrada
            }
        }
        
        return null; // Retorna null se nenhuma opção correspondente for encontrads
        }
    } 
}
}


