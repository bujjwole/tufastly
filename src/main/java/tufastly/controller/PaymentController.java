package tufastly.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tufastly.URLUtils;
import tufastly.external.PaypalClient;
import tufastly.model.OrderInfo;
import tufastly.service.PaymentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaypalClient paypalClient;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getOrder() {
        return "/order";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute(value = "OrderInfo") OrderInfo orderInfo, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/order";
        }
        String serviceMethod = orderInfo.getServiceMethod();
        if (serviceMethod.equals("drone")) {
            paymentService.createOrder(orderInfo);
        } else {
            paymentService.createOrder(orderInfo);
        }
        return "/payment";
    }

    @RequestMapping(value = "/orderdetail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public OrderInfo getOrderDetail(@PathVariable(value = "orderId") int orderId) {
        OrderInfo orderInfo = paymentService.getOrderById(orderId);
        return orderInfo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payment")
    public String getPayment() {
        return "/payment";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/payment")
    public String doPayment(@RequestBody String price, HttpServletRequest request) throws PayPalRESTException {
        String cancelUrl = URLUtils.getBaseURl(request) + "/payment/cancel";
        String successUrl = URLUtils.getBaseURl(request) + "/payment/success";
        Double amount = Double.parseDouble(price);

        Payment payment = paypalClient.createPayment(amount, "USD", "paypal", "sale", "payment description", cancelUrl, successUrl);
        for(Links links: payment.getLinks()){
            if(links.getRel().equals("approval_url")){
                return "redirect:" + links.getHref();
            }
        }
        return "fail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payment/success")
    public String PaymentSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws PayPalRESTException {
        Payment payment = paypalClient.executePayment(paymentId, payerId);
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payment/cancel")
    public String cancelPay(){
        return "cancel";
    }

}
