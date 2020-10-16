package com.eventosdahora.order.ms.repository;

import com.eventosdahora.order.ms.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
