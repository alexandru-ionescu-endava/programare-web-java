package com.example.service;

import com.example.dto.PaymentDto;
import com.example.exceptions.PaymentException;
import com.example.model.Payment;
import com.example.model.PaymentStatus;
import com.example.model.PaymentType;
import com.example.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.example.dto.PaymentDto.fromDtoToPayment;


@Service
//@Transactional
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public void add10Payments() {
        for (int i = 0; i < 10; i++) {
            var payment = Payment
                    .builder()
                    .amount(i * 10 + 1)
                    .customer("Customer" + i)
                    .paymentType(i % 2 == 0 ? PaymentType.ONLINE : PaymentType.POS)
                    .paymentStatus(i % 2 == 0 ? PaymentStatus.NEW : PaymentStatus.PROCESSED)
                    .build();
//            if(i == 5) {
//                throw new RuntimeException("Something went wrong");
//            }
            paymentRepository.save(payment);
        }
    }

    public Payment addPayment(PaymentDto payment) {
        var databasePayment = paymentRepository.save(fromDtoToPayment(payment));
        LOGGER.debug("Payment {} was successfully saved in db.", databasePayment);

        return databasePayment;
    }


    public Payment cancelPayment(Integer paymentId) {
        var payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentException("The payment does not exist"));

        if (payment.getPaymentStatus() == PaymentStatus.CANCELLED)
            throw new PaymentException("Payment is already cancelled");

        payment.setPaymentStatus(PaymentStatus.CANCELLED);

        LOGGER.debug("Payment {} was successfully cancelled.", payment);

        return paymentRepository.save(payment);
    }


    public List<Payment> getPayments(Map<String, String> filters) {
        List<Payment> payments;

        if (filters != null && !filters.isEmpty()) {
            payments = paymentRepository
                    .findAllByPaymentStatusOrPaymentType(
                            PaymentStatus.getValueByString(filters.get("paymentStatus")),
                            PaymentType.getValueByString(filters.get("paymentType"))
                    );

            LOGGER.debug("Returning {} payments filtered by {}", payments, filters);
        } else {
            payments = paymentRepository.findAll();
            LOGGER.debug("Returning {} payments", payments);
        }

        return payments;
    }

}