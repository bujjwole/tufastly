package tufastly.external;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaypalClient {

    public static final String clientID = "ASBYJq6x-VUlwF2iPrTBuXu2zykyxvfHx1dpzJvaHnOskbQES0HKN3TD1SpQs1gK-Lka_j--k5cr3hav";
    public static final String clientSecret = "EPuxL8pJW-wn_qhIc0Ov0JPVTEuSPFsuZI83mrOI8NfRl7O6nDBEhP0Qu0yFuDZKO-Lmn1CNQg3U2_VR";
    public static final String mode = "sandbox";


    public static Payment createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException {

        APIContext apiContext = new APIContext(clientID, clientSecret, mode);
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(clientID, clientSecret, mode);
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    public static void main(String[] args) throws PayPalRESTException {
        PaypalClient paypalClient = new PaypalClient();
        Payment PaymentResponse = paypalClient.createPayment(
                5.00,
                "USD",
                "paypal",
                "sale",
                "payment description",
                "www.baidu.com",
                "www.google.com");
        Payment ExecuteResponse = paypalClient.executePayment(PaymentResponse.getId(), PaymentResponse.getPayer().toString());
        System.out.println(ExecuteResponse);
    }

}

