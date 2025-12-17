package com.example.repository;

import com.example.model.Payment;
import com.example.model.PaymentStatus;
import com.example.model.PaymentType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository
        extends JpaRepository<Payment, Integer> {


    //    query from method name
    List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);


    List<Payment> findAllByPaymentStatusOrPaymentType(PaymentStatus paymentStatus,
                                                      PaymentType paymentType);




    // JPQL query to find payments with amount greater than a specified value
    @Query("select avg(p.amount) from Payment p where p.paymentType = :paymentType")
    double getAverageAmountWithJPQL(PaymentType paymentType);



    //    native query
    @Query(
            nativeQuery = true,
            value = "select avg(p.amount) from payments p where p.payment_type = :paymentType"
    )
    double getAverageAmountByPaymentType(String paymentType);





    @Modifying
    // @Modifying tells Spring Data JPA that the @Query is a modifying query (update/insert/delete), not a select.
    @Query("update Payment p set p.amount = p.amount + :newAmount where p.id = :id")
    @Transactional // mandatory for data modifying queries
    void updateAmount(@Param("newAmount") double newAmount,
                      @Param("id") int id);


}