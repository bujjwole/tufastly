import React from 'react';
import { Statistic, Col, Row, Button } from 'antd';

class Totals extends React.Component {
    constructor(props) {
        super(props);
        this.state = { totalorders: [], totalrevenues: [] };
        this.getTotals = this.getTotals.bind(this);
    }

    getTotals() {
        /*
        fetch( 'http://127.0.0.1:8080/tufastly/admin/orders',
        {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
        })*/
        fetch('./fakeorders.json')
            .then(response => response.json())
            .then(data => {
                this.setState({ totalorders: data.totals.totalorders })
                this.setState({ totalrevenues: data.totals.totalrevenues })
            })
            .catch((error) => {
                console.error('error', error)
            })
    }

    // call getTotals()
    componentDidMount() {
        this.getTotals();
    };

    render() {

        return (
            <div className='totals'>
                <Row gutter={18}>
                    <Col span={6}>
                        <Statistic
                            title="Total Orders"
                            value={this.state.totalorders} />
                    </Col>
                    <Col span={6}>
                        <Statistic
                            title="Total Revenues (USD)"
                            value={this.state.totalrevenues} />
                    </Col>
                    <Col span={6}>
                        <Statistic
                            title="Total Profits (USD)"
                            value={"N/A"} />
                    </Col>
                </Row>

            </div>

        );
    }
}

export default Totals;