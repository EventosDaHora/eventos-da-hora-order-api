package com.eventosdahora.order.ms.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TicketDTO {

    private Long id;

    private Long ammount;
}
