package com.eventosdahora.order.ms.repository;

import com.eventosdahora.order.ms.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByUserIdOrderByDtCreate(Long userId);

}
