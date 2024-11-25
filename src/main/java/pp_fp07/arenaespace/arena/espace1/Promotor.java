/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp07.arenaespace.arena.espace1;

import java.time.LocalDateTime;

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

    // Método toString que retorna uma representação em String do promotor
    @Override
    public String toString() {
        // Retorna uma string formatada com o nome completo do promotor e seu email de contacto
        return "Promotor: " + getNomeCompleto() + " (Contacto: " + getEmail() + ")";
    }
}
