package com.medware.controller;

import com.medware.controller.main.Attributes;
import com.medware.model.Ordering;
import com.medware.model.OrderingDetail;
import com.medware.model.StatProduct;
import com.medware.model.enums.OrderingStatus;
import com.medware.model.enums.ProductStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shipment")
public class ShipmentCont extends Attributes {

    @GetMapping
    public String Shipment(Model model) {
        AddAttributesShipment(model);
        return "shipment";
    }

    @GetMapping("/{idOrder}/shipped")
    public String ShipmentOrderShipped(@PathVariable Long idOrder) {
        Ordering ordering = orderingService.find(idOrder);

        List<OrderingDetail> orderingDetailList = orderingService.find(idOrder).getDetails();

        StatProduct statProduct;
        for (OrderingDetail i : orderingDetailList) {
            statProduct = statProductService.findByIdOrderingDetail(i.getId());
            statProduct.setProductStatus(ProductStatus.SHIPPED);
            statProduct.setDate(DateNow());
            statProductService.update(statProduct);
        }

        ordering.setOrderingStatus(OrderingStatus.SHIPPED);
        orderingService.update(ordering);
        return "redirect:/shipment";
    }

    @GetMapping("/{idOrder}/delete")
    public String ShipmentOrderDelete(@PathVariable Long idOrder) {
        DeleteOrderingAndOrderingDetailsAndStatProduct(idOrder);
        return "redirect:/shipment";
    }
}
