import React from "react";
import { Col, Row, Divider } from "antd";
import BillingAddress from "./BillingAddress";
import OrderDetails from "./OrderDetails"
import PaymentMethods from "./PaymentMethods"

const Main = () => {
  return (

    <div style={{ margin: "80px" }}>
      <div className="container-fluid">
        <div className="titleHolder">
          <h2> Checkout </h2>
          <p>Please enter your billing information</p>
        </div>
      </div>
      <Row>
        <Col span={16}>
          <BillingAddress />
        </Col>
        <Col flex="auto">
          <OrderDetails />
        </Col>
      </Row>
      <Divider orientation="left"></Divider>
      <Row>
        <Col>
          <PaymentMethods />
        </Col>
      </Row>
    </div>
  );
};

export default Main;