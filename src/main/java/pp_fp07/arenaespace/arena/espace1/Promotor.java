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
public class Promotor extends Utilizador { 

    public Promotor(String NomeCompleto, String NomeDeUtilizador, String email, String password, String Privilegio) {
        super(NomeCompleto, NomeDeUtilizador, email, password, Privilegio);
    }

    @Override
    public String toString() {
        return "Promotor: " + getNomeCompleto() + " (Contacto: " + getEmail() + ")";
    }
}
