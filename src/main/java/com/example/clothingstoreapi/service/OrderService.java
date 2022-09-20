package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.CheckoutItemDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.List;

public interface OrderService {
    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDTO checkoutItemDto);
    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDTO checkoutItemDto);
    Session createSession(List<CheckoutItemDTO> checkoutItemDtoList) throws StripeException;
}
