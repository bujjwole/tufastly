import React from 'react';
import { Statistic, Col, Row, Button } from 'antd';

class Overview extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 
            robotsaway: [], 
            robotscharging: [],
            dronesaway: [], 
            dronescharging: [] };
        this.getTotals = this.getTotals.bind(this);
    }

    getTotals() {
        fetch( 'http://127.0.0.1:8080/tufastly/admin/teams',
        {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
        })
        // fetch('./faketeams.json')
            .then(response => response.json())
            .then(data => {
                this.setState({ robotsaway: data.robots.away })
                this.setState({ robotscharging: data.robots.charging })
                this.setState({ dronesaway: data.drones.away })
                this.setState({ dronescharging: data.drones.charging })
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
                <Row>
                <Col span={4}>
                    <Statistic className='robot' title="Away Robots" value={this.state.robotsaway} />
                </Col>
                <Col span={4} >
                    <Statistic className='robot' title="Charging Robots" value={this.state.robotscharging} />
                </Col>
                <Col span={4} >
                    <Statistic className='drone' title="Away Drones" value={this.state.dronesaway} />
                </Col>
                <Col span={4} >
                    <Statistic className='drone' title="Charging Drones" value={this.state.dronescharging} />
                </Col>
                <Col span={4}>
                    <Button style={ {marginTop: 16, marginLeft: 32}} type='primary'>
                        Display On-Duty Routes
                </Button>
                </Col>
            </Row>
            </div>

        );
    }
}

export default Overview;