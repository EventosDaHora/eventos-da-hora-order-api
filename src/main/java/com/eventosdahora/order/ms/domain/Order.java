package com.eventosdahora.order.ms.domain;

import com.eventosdahora.order.ms.dto.OrderDTO;
import com.eventosdahora.order.ms.dto.request.PaymentRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {
	
	@Id
	@SequenceGenerator(name = "seq_order", sequenceName = "seq_order", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
	@Column(name = "id_order", length = 19)
	public Long id;
	
	@Column(name = "id_user", length = 19)
	public Long userId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "dt_create")
	public LocalDateTime dtCreate;
	
	@Column(name = "id_status")
	public String status;
	
	@Column(name = "id_payment", length = 19)
	public Long idPayment;
	
	@Column(name = "vl_fees", length = 19)
	public BigDecimal fees;
	
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(targetEntity = OrderItem.class, mappedBy = "order", orphanRemoval=true, fetch = FetchType.EAGER)
	public List<OrderItem> items;
	
	public OrderDTO toOrderDTO(PaymentRequestDTO paymentRequestDTO, String emailNotification) {
		return OrderDTO.builder()
		               .orderId(id)
		               .createdDate(dtCreate)
		               .fees(fees)
		               .userId(userId)
		               .emailNotification(emailNotification)
		               .tickets(items.stream().map(OrderItem::toTicketDTO).collect(Collectors.toList()))
		               .payment(paymentRequestDTO.toPaymentDTO(fees))
		               .orderState(OrderState.NOVO_PEDIDO)
		               .build();
	}
	
	public Order(final Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Order{" +
		       "id=" + id +
		       ", status=" + status +
		       ", items=" + items +
		       '}';
	}
}
