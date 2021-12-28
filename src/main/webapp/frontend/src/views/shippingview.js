import React, { useState } from 'react';
import { Layout, Row, Col } from 'antd';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import AppShipping from '../components/home/shipping_fee';
import AppMap from '../components/home/google_map';
import UserViewHeader from '../components/common/user_view_header'
import HistoryOrders from '../components/order_history/HistoryOrders';
import Main from '../components/payment/components/Main';
import 'antd/dist/antd.css';




function ShippingView() {
    const [previewInfo, setPreviewInfo] = useState({});
    const [transportation, setTransportation] = useState("");
    const { Header, Content } = Layout;

    return (
        <Router>
            <Layout className="shippingViewLayout">
                <Header>
                    <UserViewHeader />
                </Header>
                <Content>
                    <Switch>
                        <Route path="/user/shipping">
                            <div className="shipping_view">
                                <Row>
                                    <Col>
                                        <AppShipping
                                            previewInfo={previewInfo}
                                            setPreviewInfo={setPreviewInfo}
                                            setTransportation={setTransportation}
                                        />
                                    </Col>
                                    <Col>
                                        <AppMap
                                            previewInfo={previewInfo}
                                            transportation={transportation}
                                        />
                                    </Col>
                                </Row>
                            </div>
                        </Route>

                        <Route path="/user/orders">
                            <div style={{ marginTop: "60px" }}>
                                <HistoryOrders />
                            </div>
                        </Route>

                        <Route path="/user/checkout">
                            <div style={{ marginTop: "60px" }}>
                                <Main />
                            </div>
                        </Route>
                    </Switch>

                </Content>
            </Layout>
        </Router>
    )
}

export default ShippingView;