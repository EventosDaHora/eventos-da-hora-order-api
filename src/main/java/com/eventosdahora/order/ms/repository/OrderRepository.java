package com.eventosdahora.order.ms.repository;

import com.eventosdahora.order.ms.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
