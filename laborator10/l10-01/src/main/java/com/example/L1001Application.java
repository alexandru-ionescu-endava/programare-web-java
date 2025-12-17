package com.example;

import com.example.model.PaymentStatus;
import com.example.model.PaymentType;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class L1001Application
        implements CommandLineRunner {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;


    public static void main(String[] args) {
        SpringApplication.run(L1001Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

//        paymentService.add10Payments();

        paymentRepository.findAll();

        paymentRepository.findAllByPaymentStatus(PaymentStatus.NEW)
                .forEach(System.out::println);

//        double result1 = paymentRepository.getAverageAmountWithJPQL(PaymentType.ONLINE);
//        System.out.println(result1);
//
//        double result2 = paymentRepository.getAverageAmountByPaymentType(PaymentType.ONLINE.name());
//        System.out.println(result2);
//
//        paymentRepository.updateAmount(100, 1);

    }


}
