package com.eventosdahora.order.ms.resource;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.dto.OrderDTO;
import com.eventosdahora.order.ms.dto.request.OrderRequestDTO;
import com.eventosdahora.order.ms.dto.request.PaymentRequestDTO;
import com.eventosdahora.order.ms.rest.OrderRestClient;
import lombok.extern.java.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Log
@Path("/pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    @RestClient
    OrderRestClient orderRestClient;
    
    @POST
    @Transactional
    public void novo(OrderRequestDTO orderRequestDTO) {
        log.info("--- Recebendo ORDER REQUEST");
        log.info(orderRequestDTO.toString());
    
        Order order = orderRequestDTO.toEntity();
        log.info("--- Criando ORDER");
        log.info(order.toString());
        
        Order.persist(order);
    
        log.info("--- Criando ORDER DTO");
        log.info(order.toOrderDTO(orderRequestDTO.getPayment()).toString());
        orderRestClient.novoPedido(order.toOrderDTO(orderRequestDTO.getPayment()));
    }

    @PUT
    @Transactional
    public void atualiza(OrderDTO orderDTO) {
        log.info("PUT");
        log.info(orderDTO.toString());
        String sql = "status = ?1 WHERE id = ?2";
        Order.update(sql, orderDTO.getOrderState(), orderDTO.getOrderId());
    }

}
