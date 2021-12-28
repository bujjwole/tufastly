import React, { useState } from 'react';
import {GoogleMap, withScriptjs, withGoogleMap, Marker, Polyline, InfoWindow} from "react-google-maps";

const MyMapComponent = withScriptjs(withGoogleMap((props) => {
  const [selectedPoint, setSelectedPoint] = useState(null);

  return (
    <GoogleMap
      defaultZoom={13} 
      defaultCenter={{ lat: 37.774929, lng: -122.419418}}
    >
      {props.waypoints_coordinates && props.waypoints_coordinates.map((coordinates, index) => (
        <Marker
          position={{
            lat: coordinates.lat,
            lng: coordinates.lng
          }}
          onClick={() => {
            setSelectedPoint({
              coordinates: coordinates,
              address: props.waypoints_address[index]
            });
          }}
        />
      ))}
      {props.transportation === "drone" && props.waypoints_coordinates && (
        <Polyline
          path={props.waypoints_coordinates}
          geodesic={true}
          options={{
            strokeColor: "#FF7F24",
            strokeWeight: 1.5
          }}
        />
      )}
      {props.transportation === "robot" && props.polyline && (
        <Polyline
          path={props.google.maps.geometry.encoding.decodePath(props.polyline)}
          geodesic={true}
          options={{
            strokeColor: "#FF7F24",
            strokeWeight: 1.5
          }}
        />
      )}
      {selectedPoint && (
        <InfoWindow
          position={{
            lat: selectedPoint.coordinates.lat,
            lng: selectedPoint.coordinates.lng
          }}
          onCloseClick={() => {
            setSelectedPoint(null);
          }}
        >
          <div>Location: {selectedPoint.address}</div>
        </InfoWindow>
      )}
    </GoogleMap>
  )
}))

function AppMap({previewInfo, transportation}) {
  console.log(previewInfo);
  console.log(transportation);
  const waypoints_address=previewInfo.waypoints_address;
  const waypoints_coordinates=previewInfo.waypoints_coordinates;
  const distance=previewInfo.distance;
  const price=previewInfo.price;
  const polyline=previewInfo.polyline;
  
  return <div style={{marginLeft:'20px', marginTop: '100px', width: '700px', height: '600px'}}>
    <MyMapComponent 
      googleMapURL={'https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyA4QT83GA29C4GWTs-jFa-wJwi0_iWMNt4'}
      loadingElement={<div style={{ height: "100%" }} />}
      containerElement={<div style={{ height: "100%" }} />}
      mapElement={<div style={{ height: "100%" }} />}
      waypoints_address={waypoints_address}
      waypoints_coordinates={waypoints_coordinates}
      distance={distance}
      price={price}
      polyline={polyline}
      transportation={transportation}
      google={window.google}
    />
  </div>
}

export default AppMap;