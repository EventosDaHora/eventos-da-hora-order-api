package com.eventosdahora.order.ms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OrderState {
    NOVO_PEDIDO, TICKET_RESERVADO, TICKET_RESERVADO_ERRO,
    PAGAMENTO_APROVADO, PAGAMENTO_NEGADO,
    TICKET_RESTAURADO, TICKET_RESTAURADO_ERRO, TICKET_COMPRADO
}
