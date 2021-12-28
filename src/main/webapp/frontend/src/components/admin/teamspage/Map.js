import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import {CenterMarkerStyle} from './CenterMarkerStyle.js';

const centers = [
  { lat: 37.7968, lng: -122.4178, title: 'A' },
  { lat: 37.7785, lng: -122.4428, title: 'B' },
  { lat: 37.7769, lng: -122.4089, title: 'C' }
];

const CenterMarker = ({ text }) => (
  <div className="circle" style={CenterMarkerStyle}>
       {text}  
  </div>
);

class Map extends Component {

  render() {
    return (
      // Important! Always set the container height explicitly
      <div className='map' style={{ height: '650px', width: '650px' }}>
        <GoogleMapReact
          bootstrapURLKeys={{ key: 'AIzaSyCEkdlBcPTWhd9uyZsIx30VrnoHCWujrsc' }}
          defaultCenter={{
            lat: 37.7828,
            lng: -122.4250
          }}
          defaultZoom={14}
            >
          {
            centers.map(({ lat, lng, title }) => {
              return (
                <CenterMarker
                  lat={lat}
                  lng={lng}
                  text={title}
                />
              );
            })
          }
        </GoogleMapReact>
      </div>
    );
  }
}

export default Map;