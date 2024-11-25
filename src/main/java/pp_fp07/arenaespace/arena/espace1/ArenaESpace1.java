/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pp_fp07.arenaespace.arena.espace1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author RyanS
 */
public class ArenaESpace1 {
    public static void main(String[] args) {
        // Cria a lista de utilizadores cadastrados
        try ( // Cria o Scanner para entrada do usuário
            Scanner scanner = new Scanner(System.in)) {
            // Cria a lista de utilizadores cadastrados
            List<Utilizador> utilizadoresCadastrados = new ArrayList<>();
            // Cria a lista de eventos criados
            List<Eventos> eventosCriados = new ArrayList<>();
            // Cria uma instância do Menu_1
            Menu_1 menu = new Menu_1(scanner, utilizadoresCadastrados, eventosCriados);
            // Executa o menu principal
            menu.executa();
            // Fecha o scanner ao final da execução
        }
    }
}
