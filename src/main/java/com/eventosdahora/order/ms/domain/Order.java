package com.eventosdahora.order.ms.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order extends PanacheEntity {

    @Column(name = "id_order", length = 19)
    public Long id;

    @Column(name = "id_user", length = 19)
    public Long userId;

    @Column(name = "dt_create")
    public LocalDateTime dtHrCriacao;

    @Column(name = "id_status")
    @Enumerated(EnumType.STRING)
    public OrderState status;



}
