package tufastly.model;

public class PreviewRequestBody {

    private int departure_station_address;

    private String pick_up_address;

    private String shipping_address;

    public int getDeparture_station_address() {
        return departure_station_address;
    }

    public void setDeparture_station_address(int departure_station_address) {
        this.departure_station_address = departure_station_address;
    }

    public String getPick_up_address() {
        return pick_up_address;
    }

    public void setPick_up_address(String pick_up_address) {
        this.pick_up_address = pick_up_address;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

}
