package com.eventosdahora.order.ms.dto.request;

import com.eventosdahora.order.ms.domain.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRequestDTO {
	
	private Long id;
	private Long quantity;
	
	public OrderItem toEntity() {
		return OrderItem.builder()
		                .externalItemId(id)
		                .qtdItems(quantity)
		                .build();
	}
}
