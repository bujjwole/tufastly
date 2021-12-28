import React from "react";
import { Input, Form, Card, Col, Row, Statistic, Button} from 'antd';
import Title from "antd/lib/typography/Title";


const OrderDetails = () => {

return (
<div className="order-info-container">
<Title level={4}>Order Summary</Title>
<Row>
      <Col>
        <Card bordered={true} style={{ width: 400 }}>
          {/* <Statistic
            title="Subtotal"
            value=""
            precision={2}
            valueStyle={{ color: '#7dbcea' }}
            prefix="$"
          /> */}
          <Statistic
            title="Service fee"
            value=""
            precision={2}
            valueStyle={{ color: '#7dbcea' }}
            prefix="$"
          />
          <Statistic
            title="Estimated Tax"
            value=""
            precision={2}
            valueStyle={{ color: '#7dbcea' }}
            prefix="$"
          />
          <Statistic
            title="Order Total"
            value=""
            precision={2}
            valueStyle={{ color: '#7dbcea' }}
            prefix="$"
          />
        <Form.Item
        label="Promo Code"
        name="promocode"
        rules={[{ 
          required: false,
          message: '',
        }]}
      >
        <Input style={{ width: "60%" }}/>
      </Form.Item>
      <Form.Item >
          <Button type="primary">Apply promo code</Button>
        </Form.Item>
        </Card>
      </Col>
    </Row>
  </div>
)
}
export default OrderDetails;