/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp07.arenaespace.arena.espace1;
/**
 *
 * @author RyanS
 */
import java.util.ArrayList;
import java.util.List;

public class GerirEventos {
    private List<Eventos> eventosCriados = new ArrayList<>();

    // Adiciona um evento à lista
    public void adicionarEvento(Eventos evento) {
        eventosCriados.add(evento);
    }

    // Retorna todos os eventos
    public List<Eventos> getEventosCriados() {
        return eventosCriados;
    }

    // Retorna eventos associados a um promotor específico
    public List<Eventos> getEventosPorPromotor(String nomePromotor) {
        List<Eventos> eventosDoPromotor = new ArrayList<>();
        for (Eventos evento : eventosCriados) {
            if (evento.getNomePromotor().equals(nomePromotor)) {
                eventosDoPromotor.add(evento);
            }
        }
        return eventosDoPromotor;
    }

    // Método para listar todos os eventos
    public void listarEventos() {
        if (eventosCriados.isEmpty()) {
            System.out.println("Não há eventos cadastrados.");
        } else {
            System.out.println("Eventos cadastrados:");
            for (Eventos evento : eventosCriados) {
                System.out.println(evento.toString()); // Exibe detalhes do evento
            }
        }
    }
}
