import React from 'react';
import { Col, Row } from 'antd';
import Totals from './Totals';
import Details from './Details';
import RevenuesChart from './RevenuesChart';
import OrdersChart from './OrdersChart';

const Orders = () => {    
    return (
        <Row>
            <Col span={14}>
                <Totals />
                <Details />
            </Col>
            <Col span={10}>
                <RevenuesChart />
                <OrdersChart />
            </Col>
        </Row>

    )
}

export default Orders;