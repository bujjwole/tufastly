package tufastly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tufastly.dao.PaymentDao;
import tufastly.model.OrderInfo;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public void createOrder(OrderInfo orderInfo){
        paymentDao.createOrder(orderInfo);
    }

    public OrderInfo getOrderById(int orderId){
        return paymentDao.getOrderById(orderId);
    }
}
