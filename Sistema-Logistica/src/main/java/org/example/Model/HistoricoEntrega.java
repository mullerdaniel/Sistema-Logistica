package org.example.Model;

import java.time.LocalDateTime;

public class HistoricoEntrega {

    private int id;
    private int entrega_id;
    private LocalDateTime data_evento;
    private String descricao;


    // METODO CONSTRUTOR COM ID E E SEM ID


    // COM ID
    public HistoricoEntrega(int id, int entrega_id, LocalDateTime data_evento, String descricao) {
        this.id = id;
        this.entrega_id = entrega_id;
        this.data_evento = data_evento;
        this.descricao = descricao;
    }

    // SEM ID
    public HistoricoEntrega(int entrega_id, LocalDateTime data_evento, String descricao) {
        this.entrega_id = entrega_id;
        this.data_evento = data_evento;
        this.descricao = descricao;
    }


    // GET E SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntrega_id() {
        return entrega_id;
    }

    public void setEntrega_id(int entrega_id) {
        this.entrega_id = entrega_id;
    }

    public LocalDateTime getData_evento() {
        return data_evento;
    }

    public void setData_evento(LocalDateTime data_evento) {
        this.data_evento = data_evento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
