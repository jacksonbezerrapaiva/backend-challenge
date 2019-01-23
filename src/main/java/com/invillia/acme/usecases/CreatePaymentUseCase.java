package com.invillia.acme.usecases;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.dto.PaymentDto;
import com.invillia.acme.exception.OrderNotFoundException;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class CreatePaymentUseCase {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    @Transactional(rollbackFor = Exception.class)
    public void save(PaymentDto paymentDto) throws OrderNotFoundException {
        Order order = orderRepository.findByClientId(paymentDto.getOrderId());
        if (order == null) {
            throw new OrderNotFoundException();
        }
        Payment pa = new Payment();
        pa.setDatePayment(paymentDto.getDatePayment());
        pa.setNumberCard(paymentDto.getNumberCard());
        pa.setClientId(paymentDto.getId());
        pa.setOrder(order);
        paymentRepository.save(pa);
    }
}
