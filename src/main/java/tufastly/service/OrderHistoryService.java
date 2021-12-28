package tufastly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tufastly.dao.OrderInfoDao;
import tufastly.model.OrderInfo;

import java.util.List;

@Service
public class OrderHistoryService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    public List<OrderInfo> getOrderHistory(int customerId) {
        return orderInfoDao.getOrderInfoByCustomerId(customerId);
    }
}