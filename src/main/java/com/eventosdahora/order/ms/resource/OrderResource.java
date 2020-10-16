package com.eventosdahora.order.ms.resource;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.dto.OrderDTO;
import com.eventosdahora.order.ms.dto.request.OrderRequestDTO;
import com.eventosdahora.order.ms.repository.OrderRepository;
import com.eventosdahora.order.ms.rest.OrderRestClient;
import lombok.extern.java.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Log
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
	
	@Inject
	@RestClient
	OrderRestClient orderRestClient;
	
	@Inject
	OrderRepository orderRepository;
	
	@POST
	public Response add(OrderRequestDTO orderRequestDTO) {
		log.info("--- Criando Novo pedido");
		log.info(orderRequestDTO.toString());
		
		Order order = orderRequestDTO.toEntity();
		orderRepository.save(order);
		
		orderRestClient.novoPedido(order.toOrderDTO(orderRequestDTO.getPayment()));
		
		return Response.ok(order).build();
	}
	
	@PUT
	public Response update(OrderDTO orderDTO) {
		log.info("--- Recebendo atualizacao de PEDIDO");
		
		Optional<Order> orderOptional = orderRepository.findById(orderDTO.getOrderId());
		if (orderOptional.isPresent()) {
			orderOptional.get().status = orderDTO.getOrderState();
			orderRepository.save(orderOptional.get());
			return Response.ok(orderOptional.get()).build();
		}else{
			return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Order not found").build();
		}
	}
	
	@GET
	public Response getAll() {
		return Response.ok(orderRepository.findAll()).build();
	}
	
}
