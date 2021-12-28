import Title from "antd/lib/typography/Title";
import React from "react";
import {  Form, Input, Checkbox } from "antd";


const BillingAddress = () => {
  const onFormFinish = (BillingAddress) => {
  }

  const layout = {
    labelCol: { span: 4 },
    wrapperCol: { span: 24 },
  };

  const tailLayout = {
    wrapperCol: { offset: 3, span: 24 },
};

return (
  <div className="billing-info-container">
    <Title level={4}>Billing Information</Title>
    <Form
      {...layout}
      initialValues={{ 
        firstName: "",
        lastName: "",
        address: "",
        city: "",
        state: "",
        zipCode: "",
        sameAddress: false  
      }}
      onFinish={onFormFinish}
    >
    
      <Form.Item required tooltip="This is a required field"
        label="First Name"
        name="firstName"
        rules={[{ 
          required: true,
          message: 'Please enter a valid first name!',
        }]}
      >
        <Input style={{ width: "40%" }}/>
      </Form.Item>

      <Form.Item required tooltip="This is a required field"
        label="Last Name"
        name="lastName"
        rules={[{ 
          required: true,
          message: 'Please enter a valid last name!',
        }]}
      >
        <Input style={{ width: "40%" }}/>
      </Form.Item>
      <Form.Item label="Address" required tooltip="This is a required field">
        <Input.Group compact>
          <Form.Item
            name={['street']}
            noStyle
          >
            <Input 
            style={{ width: '80%' }} placeholder="Street number">
            </Input>
          </Form.Item>
          <Form.Item
            name={['unit']}
            noStyle
            rules={[{ required: true, message: 'optional' }]}
          >
            <Input style={{ width: '80%' }} placeholder="Apt#, suite, floor" />
          </Form.Item>
          <Form.Item
            name={['city']}
            noStyle
            rules={[{ required: true, message: 'city is required' }]}
          >
            <Input style={{ width: '80%' }} placeholder="City"/>
          </Form.Item>
          <Form.Item
            name={['state']}
            noStyle
            rules={[{ required: true, message: 'state is required' }]}
          >
            <Input style={{ width: '80%' }} placeholder="State"/>
          </Form.Item>
          <Form.Item
            name={['zipCode']}
            noStyle
            rules={[{ required: true, message: 'ZIP code is required' }]}
          >
            <Input style={{ width: '80%' }} placeholder="ZIP Code" />
          </Form.Item>
        </Input.Group>
      </Form.Item>

      <Form.Item {...tailLayout} name="sameAddress" valuePropName="checked">
        <Checkbox>Same as pick up address</Checkbox>
      </Form.Item>

    </Form>
  </div>
)
}

export default BillingAddress;
