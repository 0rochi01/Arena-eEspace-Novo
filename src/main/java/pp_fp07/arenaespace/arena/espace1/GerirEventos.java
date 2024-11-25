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
    // Lista que armazena todos os eventos criados
    private List<Eventos> eventosCriados = new ArrayList<>();

    // Método para adicionar um evento à lista de eventos criados
    public void adicionarEvento(Eventos evento) {
        eventosCriados.add(evento); // Adiciona o evento à lista
    }

    // Método que retorna todos os eventos cadastrados
    public List<Eventos> getEventosCriados() {
        return eventosCriados; // Retorna a lista de eventos criados
    }

    // Método que retorna eventos associados a um promotor específico
    public List<Eventos> getEventosPorPromotor(String nomePromotor) {
        List<Eventos> eventosDoPromotor = new ArrayList<>(); // Lista para armazenar eventos do promotor
        // Itera sobre todos os eventos criados
        for (Eventos evento : eventosCriados) {
            // Verifica se o nome do promotor do evento corresponde ao nome fornecido
            if (evento.getNomePromotor().equals(nomePromotor)) {
                eventosDoPromotor.add(evento); // Adiciona o evento à lista se o promotor corresponder
            }
        }
        return eventosDoPromotor; // Retorna a lista de eventos do promotor
    }

    // Método para listar todos os eventos cadastrados
    public void listarEventos() {
        // Verifica se a lista de eventos está vazia
        if (eventosCriados.isEmpty()) {
            System.out.println("Não há eventos cadastrados."); // Mensagem informando que não há eventos
        } else {
            System.out.println("Eventos cadastrados:"); // Mensagem de cabeçalho
            // Itera sobre todos os eventos e exibe seus detalhes
            for (Eventos evento : eventosCriados) {
                System.out.println(evento.toString()); // Chama o método toString do evento para exibir detalhes
            }
        }
    }
}
