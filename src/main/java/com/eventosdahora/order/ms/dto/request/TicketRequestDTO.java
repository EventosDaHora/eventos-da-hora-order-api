package com.eventosdahora.order.ms.dto.request;

import com.eventosdahora.order.ms.domain.OrderItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TicketRequestDTO {
	
	private Long id;
	private Long quantity;
	
	public OrderItem toEntity() {
		return OrderItem.builder()
		                .id(new Date().getTime())
		                .externalItemId(id)
		                .qtdItems(quantity)
		                .build();
	}
}
