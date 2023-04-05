package com.medware.controller;

import com.medware.controller.main.Attributes;
import com.medware.model.Ordering;
import com.medware.model.enums.OrderingStatus;
import com.medware.model.enums.PaymentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentCont extends Attributes {

    @GetMapping
    public String Payment(Model model) {
        AddAttributesPayments(model);
        return "payment";
    }

    @GetMapping("/{idOrder}/shipment")
    public String PaymentOrderShipment(@PathVariable Long idOrder) {
        Ordering ordering = orderingService.find(idOrder);
        ordering.setOrderingStatus(OrderingStatus.SHIPMENT);
        orderingService.update(ordering);
        return "redirect:/payment";
    }

    @PostMapping("/{id}/edit")
    public String PaymentEdit(@PathVariable Long id, @RequestParam PaymentType paymentType) {
        Ordering ordering = orderingService.find(id);
        ordering.setPaymentType(paymentType);
        orderingService.update(ordering);
        return "redirect:/payment";
    }

    @GetMapping("/{idOrder}/delete")
    public String PaymentOrderDelete(@PathVariable Long idOrder) {
        DeleteOrderingAndOrderingDetailsAndStatProduct(idOrder);
        return "redirect:/payment";
    }
}
