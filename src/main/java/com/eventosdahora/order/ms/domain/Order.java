package com.eventosdahora.order.ms.domain;

import com.eventosdahora.order.ms.dto.OrderDTO;
import com.eventosdahora.order.ms.dto.request.PaymentRequestDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@ToString
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
	public LocalDateTime dtCreate;
	
	@Column(name = "id_status")
	@Enumerated(EnumType.STRING)
	public OrderState status;
	
	@Column(name = "id_payment", length = 19)
	public Long idPayment;
	
	@Column(name = "vl_fees", length = 19)
	public BigDecimal fees;
	
	@OneToMany(targetEntity = OrderItem.class, mappedBy = "order")
	public List<OrderItem> items;
	
	public OrderDTO toOrderDTO(PaymentRequestDTO paymentRequestDTO) {
		return OrderDTO.builder()
		               .orderId(id)
		               .createdDate(dtCreate)
                       .fees(fees)
		               .userId(userId)
		               .tickets(items.stream().map(OrderItem::toTicketDTO).collect(Collectors.toList()))
                       .payment(paymentRequestDTO.toPaymentDTO())
		               .orderState(OrderState.NOVO_PEDIDO)
		               .build();
	}
	
}
