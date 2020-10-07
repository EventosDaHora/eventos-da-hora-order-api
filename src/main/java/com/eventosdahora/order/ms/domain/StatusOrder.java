package com.eventosdahora.order.ms.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_status_order")
public class StatusOrder extends PanacheEntity {

    @Column(name = "id_status_order")
    public Long id;

    @Column(name = "ds_status_order")
    @Enumerated(EnumType.STRING)
    public OrderState orderState;
}
