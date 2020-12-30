package CAS2xb3_A2_zaman_AZ.DistanceCalculator;

/** @file DFS.java
@author Anando Zaman
@brief Calculates the distance between two points
@date March 25,2020
@details Given 2 coordinates in (lng,lat) form, calculates distance in Kilometers
*/
/* Student Information
* -------------------
* Student Name: Zaman, Anando
* Course Code: CS/SE 2XB3
* Lab Section: 02
*
* I attest that the following code being submitted is my own individual
work.
*/

public class Distancecalc {

	public static double distance(double lat1, double lon1, double lat2, double lon2) {

	    final int R = 6371; // Radius of the earth in KM

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * (Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2));
	    double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    return Math.sqrt(R*c);
	}
}
