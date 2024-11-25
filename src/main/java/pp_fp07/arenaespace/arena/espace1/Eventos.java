package pp_fp07.arenaespace.arena.espace1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author RyanS
 */
public class Eventos {
    private String titulo;
    private LocalDateTime dataHora;
    private String sala;
    private String condicoesInscricao;
    private String modalidade;
    private int numeroMaximoDeParticipantes;
    private String nomePromotor;
    private String emailPromotor;

    public Eventos(String titulo, LocalDateTime dataHora, String sala, String condicoesInscricao,
                   String modalidade, int numeroMaximoDeParticipantes, String nomePromotor, String emailPromotor) {
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.sala = sala;
        this.condicoesInscricao = condicoesInscricao;
        this.modalidade = modalidade;
        this.numeroMaximoDeParticipantes = numeroMaximoDeParticipantes;
        this.nomePromotor = nomePromotor;
        this.emailPromotor = emailPromotor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getCondicoesInscricao() {
        return condicoesInscricao;
    }

    public void setCondicoesInscricao(String condicoesInscricao) {
        this.condicoesInscricao = condicoesInscricao;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public int getNumeroMaximoDeParticipantes() {
        return numeroMaximoDeParticipantes;
    }

    public void setNumeroMaximoDeParticipantes(int numeroMaximoDeParticipantes) {
        this.numeroMaximoDeParticipantes = numeroMaximoDeParticipantes;
    }

    public String getNomePromotor() {
        return nomePromotor;
    }

    public void setNomePromotor(String nomePromotor) {
        this.nomePromotor = nomePromotor;
    }

    public String getEmailPromotor() {
        return emailPromotor;
    }

    public void setEmailPromotor(String emailPromotor) {
        this.emailPromotor = emailPromotor;
    }
    
    @Override
    public String toString() {
        return "Evento: " + titulo + ", Data e Hora: " + dataHora + ", Sala: " + sala +
               ", Modalidade: " + modalidade + ", Max Participantes: " + numeroMaximoDeParticipantes +
               ", Promotor: " + nomePromotor + " (" + emailPromotor + ")";
    }
}