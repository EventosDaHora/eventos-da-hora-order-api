package com.eventosdahora.order.ms.resource;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.domain.OrderState;
import com.eventosdahora.order.ms.dto.OrderDTO;
import com.eventosdahora.order.ms.dto.request.OrderRequestDTO;
import com.eventosdahora.order.ms.repository.OrderItemRepository;
import com.eventosdahora.order.ms.repository.OrderRepository;
import com.eventosdahora.order.ms.rest.OrderRestClient;
import lombok.extern.java.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class OrderResource {
	
	@Inject
	@RestClient
	OrderRestClient orderRestClient;
	
	@Inject
	OrderRepository orderRepository;
	
	@Inject
	OrderItemRepository orderItemRepository;
	
	@POST
	public Response add(OrderRequestDTO orderRequestDTO) {
		log.info("--- Novo Pedido");
		log.info(orderRequestDTO.toString());

		Long orderId;
		
		Order order = orderRequestDTO.toEntity();
		log.info("--- Transformando Pedido");
		log.info(order.toString());
		
		order = orderRepository.saveAndFlush(order);
		orderId = order.getId();
		order.getItems().forEach(x -> x.setOrder(new Order(orderId)));
		order = orderRepository.save(order);

		orderRestClient.novoPedido(order.toOrderDTO(orderRequestDTO.getPayment(), orderRequestDTO.getEmailNotification()));

		return Response.ok(order).build();
	}
	
	@PUT
	public Response update(OrderDTO orderDTO) {
		log.info("--- Recebendo atualizacao de PEDIDO");
		log.info(orderDTO.toString());
		
		Optional<Order> orderOptional = orderRepository.findById(orderDTO.getOrderId());
		if (orderOptional.isPresent()) {
			
			orderRepository.updateOrderStatusAndIdPayment(orderDTO.getOrderState().toString(),
			                                              orderDTO.getPayment().getPaymentId(),
			                                              orderDTO.getOrderId());
			
			return Response.ok(orderOptional.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Order not found").build();
		}
	}
	
	@GET
	public Response getAll(@QueryParam("userId") Long userId) {
		List<Order> orderList =
				userId == null ? orderRepository.findAll() : orderRepository.findByUserIdOrderByDtCreate(userId);
		return Response.ok(orderList).build();
	}
	
}
