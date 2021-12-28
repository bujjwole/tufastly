const K_WIDTH = 30;
const K_HEIGHT = 30;

const CenterMarkerStyle = {
  // initially any map object has left top corner at lat lng coordinates
  // it's on you to set object origin to 0,0 coordinates
  position: 'absolute',
  width: K_WIDTH,
  height: K_HEIGHT,
  left: -K_WIDTH / 2,
  top: -K_HEIGHT / 2,

  border: '3px solid #000000',
  borderRadius: K_HEIGHT,
  backgroundColor: 'red',
  textAlign: 'center',
  color: 'white',
  fontSize: 20,
  fontWeight: 'bold',
};

export {CenterMarkerStyle};