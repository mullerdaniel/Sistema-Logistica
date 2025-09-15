package org.example.Model;

import java.time.LocalDateTime;

public class Entrega {

    private int id;
    private int pedido_id;
    private int motorista_id;
    private LocalDateTime data_saida;
    private LocalDateTime data_entrega;
    private String statuc;  // ('EM_ROTA', 'ENTREGUE', 'ATRASADA')


    // METODO CONSTRUTOR COM ID E E SEM ID

    //COM ID
    public Entrega(int id, int pedido_id, int motorista_id, LocalDateTime data_saida, LocalDateTime data_entrega, String statuc) {
        this.id = id;
        this.pedido_id = pedido_id;
        this.motorista_id = motorista_id;
        this.data_saida = data_saida;
        this.data_entrega = data_entrega;
        this.statuc = statuc;
    }

    // SEM ID
    public Entrega(int pedido_id, int motorista_id, LocalDateTime data_saida, LocalDateTime data_entrega, String statuc) {
        this.pedido_id = pedido_id;
        this.motorista_id = motorista_id;
        this.data_saida = data_saida;
        this.data_entrega = data_entrega;
        this.statuc = statuc;
    }


    // GET E SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(int motorista_id) {
        this.motorista_id = motorista_id;
    }

    public LocalDateTime getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDateTime data_saida) {
        this.data_saida = data_saida;
    }

    public LocalDateTime getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDateTime data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getStatuc() {
        return statuc;
    }

    public void setStatuc(String statuc) {
        this.statuc = statuc;
    }
}
