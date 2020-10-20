package com.eventosdahora.order.ms.domain;

import com.eventosdahora.order.ms.dto.TicketDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order_item")
public class OrderItem {
	
	@Id
	@SequenceGenerator(name="seq_order_item", sequenceName="seq_order_item", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_order_item")
	@Column(name = "id_order_item")
	public Long id;
	
	@JsonIgnore
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
		                .quantity(qtdItems)
		                .build();
	}
	
	@Override
	public String toString() {
		return "OrderItem{" +
		       "id=" + id +
		       ", externalItemId=" + externalItemId +
		       ", qtdItems=" + qtdItems +
		       '}';
	}
}
