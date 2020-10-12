package com.eventosdahora.order.ms.dto;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.domain.OrderEvent;
import com.eventosdahora.order.ms.domain.OrderState;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
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
		            .dtCreate(createdDate)
		            .status(OrderState.NOVO_PEDIDO)
		            .userId(userId)
		            .build();
	}
}
