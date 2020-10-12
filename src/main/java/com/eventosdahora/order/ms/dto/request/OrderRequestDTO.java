package com.eventosdahora.order.ms.dto.request;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.domain.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderRequestDTO {
	
	private Long userId;
	private BigDecimal fees;
	private PaymentRequestDTO payment;
	private List<TicketRequestDTO> tickets;
	
	public Order toEntity() {
		return Order.builder()
		            .id(new Date().getTime())
		            .userId(userId)
		            .dtCreate(LocalDateTime.now())
		            .fees(fees)
		            .items(tickets.stream().map(TicketRequestDTO::toEntity).collect(Collectors.toList()))
		            .status(OrderState.NOVO_PEDIDO)
		            .build();
	}
}
