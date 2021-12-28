package tufastly.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tufastly.dao.OrderDao;
import tufastly.model.OrderInfo;
import tufastly.model.admin.AdminOrderDetail;
import tufastly.model.admin.AdminOrderInfo;
import tufastly.model.admin.AdminOrderTotal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IncomeManagementService {

    @Autowired
    private OrderDao orderDao;

    public long getTotalNumOfOrder() {
        return orderDao.getTotalNumOfOrder();
    }

    public double getIncome() {
        return orderDao.getIncome();
    }

    public AdminOrderInfo getGeneral() {
        AdminOrderInfo aoi = new AdminOrderInfo();
        AdminOrderTotal aot = new AdminOrderTotal();
        aot.setTotalorders(getTotalNumOfOrder());
        aot.setTotalrevenues(getIncome());
        aoi.setTotals(aot);
        List<OrderInfo> l = orderDao.getAllOrder();
        List<AdminOrderDetail> details = new ArrayList();
        for (OrderInfo order : l) {
            if (order.getEnableOrder()) {
                AdminOrderDetail aod = new AdminOrderDetail();
                aod.setOrderNumber(order.getId());
                aod.setName(order.getCustomer().getFirstName() + " " + order.getCustomer().getFirstName());
                aod.setPickupaddress(order.getPickUpAddress().getAddress());
                aod.setDeliveryaddress(order.getShippingAddress().getAddress());
                aod.setType(Arrays.asList(order.getServiceMethod()));
                aod.setRevenue(order.getPrice());
                details.add(aod);
            }
        }

        aoi.setDetails(details);
        return aoi;
    }
}
