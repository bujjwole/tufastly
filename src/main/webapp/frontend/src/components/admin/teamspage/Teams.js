import React from 'react';
import { Col, Row } from 'antd';
import AllTeams from './AllTeams';
import Map from '../teamspage/Map';
import Overview from './Overview';


const Orders = () => {
    return (
        <Row>
            <Col span={12}>
                <Overview />
                <AllTeams />
            </Col>
            <Col span={12}>
                <Map />
            </Col>
        </Row>
        
    )
}

export default Orders;