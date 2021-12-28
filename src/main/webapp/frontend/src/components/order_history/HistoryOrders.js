import React from 'react';
import { Col, Row } from 'antd';
import HistoryDetails from './HistoryDetails';
import HistoryOrdersChart from './HistoryOrdersChart';

const HistoryOrders = () => {    
    return (
        <Row>
            <Col span={14}>
                <HistoryDetails />
            </Col>
            <Col span={10}>
                <HistoryOrdersChart />
            </Col>
        </Row>

    )
}

export default HistoryOrders;