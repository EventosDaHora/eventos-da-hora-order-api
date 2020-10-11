package com.eventosdahora.order.ms.dto.request;

import com.eventosdahora.order.ms.dto.PaymentDTO;
import com.eventosdahora.order.ms.dto.PaymentType;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentRequestDTO {
	
	public BigDecimal vlAmount;
	public PaymentType paymentType;
	
	public PaymentDTO toPaymentDTO() {
		return PaymentDTO.builder().vlAmount(vlAmount).paymentType(paymentType).build();
	}
}
