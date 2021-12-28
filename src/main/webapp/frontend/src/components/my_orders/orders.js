import React from 'react';
import { Col, Row } from 'antd';
import Totals from './totals';
import Details from './details';
import OrdersChart from './orders_chart';

const Orders = () => {    
    return (
        <Row>
            <Col span={14}>
                <Totals />
                <Details />
            </Col>
            <Col span={10}>
                <OrdersChart />
            </Col>
        </Row>

    )
}

export default Orders;