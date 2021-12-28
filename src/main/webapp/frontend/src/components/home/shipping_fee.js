import React from "react";
import { Form, Input, Button, Select } from 'antd';
import { Link } from 'react-router-dom';
import Modal from "antd/lib/modal/Modal";

export default class AppShipping extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      pick_up_address: "",
      shipping_address: "",
      departure_station_address: "",
      transportation: "",
      dimension: {
        length: "",
        width: "",
        height: ""
      },
      weight: "",
      visible: false,
    }
  }


  submit() {
    console.log(this.state)
    // based on user's selection on transportation, call different APIs
    var baseURL = "/tufastly/preview/";
    if (this.state.transportation === "robot") {
      baseURL += "robot";
      console.log(baseURL);
    } else if (this.state.transportation === "drone") {
      baseURL += "drone";
      console.log(baseURL);
    }

    this.props.setTransportation(this.state.transportation);

    // fetch data from backend using baseURL, "./robot" or "./drone"
    fetch(baseURL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
      },
      mode: 'cors',
      body: JSON.stringify(this.state)
    })
      .then(response => response.json())
      .then(data => { this.props.setPreviewInfo(data) })
      .catch((error) => {
        console.error('error', error)
      })
      .finally(() => {
        this.setState({
          visible: true,
        })
      })
  }

  handleOk = e => {
    this.setState({
      visible: false,
    });
  };

  handleCancel = e => {
    this.setState({
      visible: false,
    });
  };

  render() {
    const { Option } = Select;
    return (
      <div className="block contactBlock" style={{ margin: "30px" }}>
        <div className="container-fluid">
          <div className="titleHolder">
            <h2> Shipping </h2>
            <p>begin your shipping right now</p>
          </div>
        </div>
        <Form
          name="normal_shipping"
          className="package-form"
          initialValues={{ remember: true }}
        >
          {/* User enter pick up address */}
          <Form.Item
            value={this.state.pick_up_address}
            name="pick_up_address "
            onChange={(data) => { this.setState({ pick_up_address: data.target.value }) }}
            rules={[{ required: true, message: 'Please input your pick-up address!' }]}
          >
            <Input placeholder="Pick-up Address" />

          </Form.Item>

          {/* User enter shipping address */}
          <Form.Item
            value={this.state.shipping_address}
            name="shipping_address "
            onChange={(data) => { this.setState({ shipping_address: data.target.value }) }}
            rules={[{ required: true, message: 'Please input your shipping address!' }]}
          >
            <Input placeholder="Shipping Address" />

          </Form.Item>

          {/* User enter dimensions */}
          <Form.Item name="dimension " label="Dimension" style={{ marginBottom: 0 }}>
            <Form.Item
              name="length"
              rules={[{ required: true }]}
              style={{ display: 'inline-block', width: 'calc(33.2% - 8px)' }}
            >
              <Input placeholder="Input length" />
            </Form.Item>
            <Form.Item
              name="width"
              rules={[{ required: true }]}
              style={{ display: 'inline-block', width: 'calc(33.2% - 8px)', margin: '0 8px' }}
            >
              <Input placeholder="Input width" />
            </Form.Item>
            <Form.Item
              name="height"
              rules={[{ required: true }]}
              style={{ display: 'inline-block', width: 'calc(33.2% - 8px)' }}
            >
              <Input placeholder="Input height" />
            </Form.Item>
          </Form.Item>

          {/* User enter weight */}
          <Form.Item
            value={this.state.weight}
            name="weight"
            label="Weight"
            onChange={(data) => { this.setState({ weight: data.target.value }) }}
            rules={[{ required: true, message: 'Please input your package weight!' }]}
          >
            <Input placeholder="Package Weight" />
          </Form.Item>

          {/* User select way of transportation */}
          <Form.Item name="transportation" label="Transportation" rules={[{ required: true, message: 'Please choose your transportation!' }]}>
            <Select
              placeholder="Select a transportation"
              onChange={(option) => (this.setState({ transportation: option }))}
              allowClear
            >
              <Option value="robot">Robot</Option>
              <Option value="drone">Drone</Option>
            </Select>
          </Form.Item>

          {/* User select departure station */}
          <Form.Item name="departure_station_address"
            label="Departure Station" rules={[{ required: true, message: 'Please choose your departure station' }]}>
            <Select
              onChange={(option) => { this.setState({ departure_station_address: option }) }}
              placeholder="Select a departure station"
              allowClear
            >
              <Option value={0}>Center 0: Asian Art Museum</Option>
              <Option value={1}>Center 1: San Francisco Cable Car Museum</Option>
              <Option value={2}>Center 2: San Francisco Museum of Modern Art</Option>
            </Select>
          </Form.Item>
          <Form.Item>
            <div style={{marginLeft: "140px"}}>
              <Button onClick={() => { this.submit() }} type="primary" htmlType="submit" className="package-form-button">
                View Shipping Cost
            </Button>
              <Button onClick={() => { console.log("go to payment page") }} style={{ marginLeft: "50px" }} className="place_order_button" type="primary html" htmlType="submit">
                <Link to="/user/checkout">
                  <span>Checkout</span>
                </Link>
              </Button>
            </div>
          </Form.Item>
        </Form>
        <Modal
          title="Order Preview"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <p>
            The total estimated distance would be {this.props.previewInfo.distance} meters and that will cost you ${this.props.previewInfo.price}.
          </p>
        </Modal>
      </div >
    );
  }
}
