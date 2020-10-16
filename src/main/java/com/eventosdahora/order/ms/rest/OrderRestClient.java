package com.eventosdahora.order.ms.rest;

import com.eventosdahora.order.ms.dto.OrderDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/orchestrator")
@RegisterRestClient
@RequestScoped
public interface OrderRestClient {

    @POST
    @Path("/start-order")
    @Produces(MediaType.APPLICATION_JSON)
    void novoPedido(OrderDTO orderDTO);
}
