package com.invillia.acme.usecases;

import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Refund;
import com.invillia.acme.exception.IntervalNotPermitException;
import com.invillia.acme.exception.OrderItemNotFoundException;
import com.invillia.acme.repository.OrderItemRepository;
import com.invillia.acme.repository.RefundRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
@AllArgsConstructor
public class RefundUseCase {

    private OrderItemRepository orderItemRepository;
    private RefundRepository refundRepository;

    public void refund(String orderItemId) throws OrderItemNotFoundException, IntervalNotPermitException {
        OrderItem oi = orderItemRepository.findByClientId(orderItemId);
        if (oi == null) {
            throw new OrderItemNotFoundException();
        }
        LocalDate dateTimeNow = LocalDate.now();

        Period intervalPeriod = Period.between(oi.getOrder().getConfirmationDate(), dateTimeNow);
        if (intervalPeriod.getDays() > 10) {
            throw new IntervalNotPermitException();
        }
        Refund refund = new Refund();
        refund.setOrderItem(oi);
        refundRepository.save(refund);
    }
}
