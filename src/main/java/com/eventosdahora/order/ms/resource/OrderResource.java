package com.eventosdahora.order.ms.resource;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.dto.OrderDTO;
import com.eventosdahora.order.ms.rest.OrderRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    @RestClient
    OrderRestClient orderRestClient;

    @POST
    public void novo(OrderDTO orderDTO) {
        Order.persist(orderDTO.toEntity());

        // Envia requisição para o orquestrador
        orderRestClient.novoPedido(orderDTO);
    }

    @PUT
    public void atualiza(OrderDTO orderDTO) {
        String sql = "status = " + orderDTO.getOrderState() + " WHERE id = ?1";
        Order.update(sql, orderDTO.getOrderId());
    }

}
