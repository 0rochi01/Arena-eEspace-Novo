package pp_fp07.arenaespace.arena.espace1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RyanS
 */

public class Eventos {
    private String titulo; // Título do evento
    private LocalDateTime dataHora; // Data e hora em que o evento ocorrerá
    private String sala; // Sala onde o evento será realizado
    private String condicoesInscricao; // Condições para inscrição no evento
    private String modalidade; // Modalidade do evento (por exemplo, tipo de atividade)
    private int numeroMaximoDeParticipantes; // Número máximo de participantes permitidos no evento
    private String nomePromotor; // Nome do promotor responsável pelo evento
    private String emailPromotor; // Email do promotor responsável pelo evento

    // Construtor da classe Eventos que inicializa os atributos do evento
    public Eventos(String titulo, LocalDateTime dataHora, String sala, String condicoesInscricao,
                   String modalidade, int numeroMaximoDeParticipantes, String nomePromotor, String emailPromotor) {
        this.titulo = titulo; // Inicializa o título do evento
        this.dataHora = dataHora; // Inicializa a data e hora do evento
        this.sala = sala; // Inicializa a sala do evento
        this.condicoesInscricao = condicoesInscricao; // Inicializa as condições de inscrição
        this.modalidade = modalidade; // Inicializa a modalidade do evento
        this.numeroMaximoDeParticipantes = numeroMaximoDeParticipantes; // Inicializa o número máximo de participantes
        this.nomePromotor = nomePromotor; // Inicializa o nome do promotor
        this.emailPromotor = emailPromotor; // Inicializa o email do promotor
    }

    // Métodos getters e setters para acessar e modificar os atributos da classe

    public String getTitulo() {
        return titulo; // Retorna o título do evento
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo; // Define um novo título para o evento
    }

    public LocalDateTime getDataHora() {
        return dataHora; // Retorna a data e hora do evento
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora; // Define uma nova data e hora para o evento
    }

    public String getSala() {
        return sala; // Retorna a sala do evento
    }

    public void setSala(String sala) {
        this.sala = sala; // Define uma nova sala para o evento
    }

    public String getCondicoesInscricao() {
        return condicoesInscricao; // Retorna as condições de inscrição do evento
    }

    public void setCondicoesInscricao(String condicoesInscricao) {
        this.condicoesInscricao = condicoesInscricao; // Define novas condições de inscrição para o evento
    }

    public String getModalidade() {
        return modalidade; // Retorna a modalidade do evento
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade; // Define uma nova modalidade para o evento
    }

    public int getNumeroMaximoDeParticipantes() {
        return numeroMaximoDeParticipantes; // Retorna o número máximo de participantes do evento
    }

    public void setNumeroMaximoDeParticipantes(int numeroMaximoDeParticipantes) {
        this.numeroMaximoDeParticipantes = numeroMaximoDeParticipantes; // Define um novo número máximo de participantes
    }

    public String getNomePromotor() {
        return nomePromotor; // Retorna o nome do promotor do evento
    }

    public void setNomePromotor(String nomePromotor) {
        this.nomePromotor = nomePromotor; // Define um novo nome para o promotor do evento
    }

    public String getEmailPromotor() {
        return emailPromotor; // Retorna o email do promotor do evento
    }

    public void setEmailPromotor(String emailPromotor) {
        this.emailPromotor = emailPromotor; // Define um novo email para o promotor do evento
    }

static class GerirEventos {
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

    // Método toString que retorna uma representação em String do evento
    @Override
    public String toString() {
        return "Evento: " + titulo + ", Data e Hora: " + dataHora + ", Sala: " + sala +
               ", Modalidade: " + modalidade + ", Max Participantes: " + numeroMaximoDeParticipantes +
               ", Promotor: " + nomePromotor + " (" + emailPromotor + ")"; // Formata a saída com informações do evento
    }
}