package com.eventosdahora.order.ms.domain;

import com.eventosdahora.order.ms.dto.TicketDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order_item")
public class OrderItem extends PanacheEntity {
    @Column(name = "id_order_item")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    public Order order;

    @Column(name = "id_external_item")
    public Long externalItemId;

    @Column(name = "qt_item")
    public Long qtdItems;
    
    public TicketDTO toTicketDTO() {
        return TicketDTO.builder()
                        .id(externalItemId)
                        .build();
    }
    
    
    
}
