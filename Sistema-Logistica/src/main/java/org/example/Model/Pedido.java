package org.example.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class Pedido {

    private int id;
    private int cliente_id;
    private LocalDateTime data_pedido;
    private double volume_m3;
    private double peso_kg;
    private String status;  // ('PENDENTE', 'ENTREGUE', 'CANCELADO')


    // METODO CONSTRUTOR COM ID E E SEM ID

    // COM ID
    public Pedido(int id, int cliente_id, LocalDateTime data_pedido, double volume_m3, double peso_kg, String status) {
        this.id = id;
        cliente_id = cliente_id;
        this.data_pedido = data_pedido;
        this.volume_m3 = volume_m3;
        this.peso_kg = peso_kg;
        this.status = status;
    }

    // SEM ID
    public Pedido(int cliente_id, LocalDateTime data_pedido, double volume_m3, double peso_kg, String status) {
        cliente_id = cliente_id;
        this.data_pedido = data_pedido;
        this.volume_m3 = volume_m3;
        this.peso_kg = peso_kg;
        this.status = status;
    }


    // GET E SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        cliente_id = cliente_id;
    }

    public LocalDateTime getData_pedido() {
        return data_pedido;
    }

    public void setDataPedido(LocalDateTime data_pedido) {
        this.data_pedido = data_pedido;
    }

    public double getVolume_m3() {
        return volume_m3;
    }

    public void setVolume_m3(double volume_m3) {
        this.volume_m3 = volume_m3;
    }

    public double getPeso_kg() {
        return peso_kg;
    }

    public void setPeso_kg(double peso_kg) {
        this.peso_kg = peso_kg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
