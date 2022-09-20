package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.CheckoutItemDTO;
import com.example.clothingstoreapi.dto.StripeResponseDTO;
import com.example.clothingstoreapi.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponseDTO> checkoutList(@RequestBody List<CheckoutItemDTO> checkoutItemDtoList) throws StripeException, StripeException {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponseDTO stripeResponse = new StripeResponseDTO(session.getId());
        return new ResponseEntity<StripeResponseDTO>(stripeResponse, HttpStatus.OK);
    }
}
