package com.example.controller;

import com.example.dto.PaymentDto;
import com.example.model.Payment;
import com.example.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.invoker.UriBuilderFactoryArgumentResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<?> add() {
        paymentService.add10Payments();
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Payment>> retrievePayment(@RequestParam(required = false) Map<String, String> filters) {
        return ResponseEntity.ok().body(paymentService.getPayments(filters));
    }

    @PostMapping("/add")
    public ResponseEntity<Payment> createPayment(@RequestBody @Valid PaymentDto paymentRequest) {
        var payment = paymentService.addPayment(paymentRequest);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(payment.getId())
                .toUri();

        return ResponseEntity.created(uri).body(payment);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Payment> cancelPayment(@RequestParam @NotNull Integer paymentId) {
        return ResponseEntity
                .ok()
                .body(paymentService.cancelPayment(paymentId));
    }

}