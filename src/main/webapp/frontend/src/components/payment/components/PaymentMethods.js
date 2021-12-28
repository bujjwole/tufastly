import React, { useState } from "react";
import { Form, Button } from "antd";
import Title from "antd/lib/typography/Title";


const PaymentMethods = () => {
  const [form] = Form.useForm();
  const [formLayout, setFormLayout] = useState('horizontal');

  const onFormLayoutChange = ({ layout }) => {
    setFormLayout(layout);
  };

  const submit = () => {
    fetch("/tufastly/payment", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
      },
      mode: 'cors',
      body: JSON.stringify({price: "5.00"})
    })
      .then(response => {console.log(response)} )
      .then(data => { console.log(data) })
      .catch((error) => {
        console.error('error', error)
      })
  };

  const formItemLayout =
    formLayout === 'horizontal'
      ? {
        labelCol: {
          span: 4,
        },
        wrapperCol: {
          span: 12,
        },
      }
      : null;
  const buttonItemLayout =
    formLayout === 'horizontal'
      ? {
        wrapperCol: {
          span: 1,
          offset: 1,
        },
      }
      : null;
  return (
    <div className="payment-info-container">
      <Title level={4} strong>Payment Methods</Title>
      <div className="paymethod">
        <Form
          {...formItemLayout}
          layout={formLayout}
          form={form}
          initialValues={{
            layout: formLayout,
          }}
          onValuesChange={onFormLayoutChange}
        >
          <Form.Item {...buttonItemLayout}>
            <Button type="primary">Continue with Debit/Credit card</Button>
          </Form.Item>
          <Form.Item {...buttonItemLayout}>
            <Button type="primary" onClick={submit}>Continue with Paypal</Button>
          </Form.Item>
        </Form>
      </div>
    </div>

  );
};
export default PaymentMethods;

