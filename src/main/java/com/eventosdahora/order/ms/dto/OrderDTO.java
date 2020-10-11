package com.eventosdahora.order.ms.dto;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.domain.OrderEvent;
import com.eventosdahora.order.ms.domain.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDTO {
	
	public static final String IDENTIFICADOR = "ID_PEDIDO";
	
	private Long orderId;
	
	private LocalDateTime createdDate;
	
	private OrderState orderState;
	
	private OrderEvent orderEvent;
	
	private BigDecimal fees;
	
	private Long userId;
	
	@Builder.Default
	private List<TicketDTO> tickets = new ArrayList<>();
	
	private PaymentDTO payment;
	
	public Order toEntity() {
		return Order.builder()
		            .id(new Date().getTime())
		            .dtCreate(LocalDateTime.now())
		            .status(OrderState.NOVO_PEDIDO)
		            .userId(userId)
		            .build();
	}
}
