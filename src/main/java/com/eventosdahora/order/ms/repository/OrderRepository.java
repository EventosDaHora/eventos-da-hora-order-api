package com.eventosdahora.order.ms.repository;

import com.eventosdahora.order.ms.domain.Order;
import com.eventosdahora.order.ms.domain.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByUserIdOrderByDtCreate(Long userId);
	
	@Modifying
	@Query("update Order o set o.status = ?1, o.idPayment = ?2 where o.id = ?3")
	void updateOrderStatusAndIdPayment(String status, Long idPayment, Long idOrder);

}
