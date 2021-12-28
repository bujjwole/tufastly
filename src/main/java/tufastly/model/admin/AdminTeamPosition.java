package tufastly.model.admin;

import org.springframework.stereotype.Component;
import tufastly.model.Coordinate;

import java.util.List;

@Component
public class AdminTeamPosition {
    private List<Coordinate> coordinates;
    private List<String> polylines;

	/**
	* Returns value of coordinates
	* @return
	*/
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	/**
	* Sets new value of coordinates
	* @param
	*/
	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	/**
	* Returns value of polylines
	* @return
	*/
	public List<String> getPolylines() {
		return polylines;
	}

	/**
	* Sets new value of polylines
	* @param
	*/
	public void setPolylines(List<String> polylines) {
		this.polylines = polylines;
	}
}
