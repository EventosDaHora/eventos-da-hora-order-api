package com.eventosdahora.order.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Column(name = "id_order_item")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    public Order order;

    @Column(name = "id_external_item")
    public Long externalItemId;

    @Column(name = "qt_item")
    public Long qtdItems;
}
