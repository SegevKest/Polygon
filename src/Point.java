/**
 * Point Class
 * Represent a Point in the Polar system, by radius and Alpha angle
 * @author Segev Kestenbaum
 * @version 20.11.2020
*/
public class Point {

	private double _radius; 
	private double _alpha; // value in degrees
	
	//Private variables
	private final byte Q1_DEFAULT_VAL = 0;
	private final byte TWO_POW = 2;
	private final int FIXED_ROUND_PARAM= 10000;
	private final int RIGHT_ANGLE_DEGREES = 90;
	private final double PIE_CONST = Math.PI;	
	private final double CONVERT_DEGREES_TO_RADIANS = PIE_CONST / 180 ;
	private final double CONVERT_RADIANS_TO_DEGREES = 1 / CONVERT_DEGREES_TO_RADIANS ;
	
	/**
	 * Construct a Point By 2 coordinates.
	 * If any parameter is negative, we will set it to 0 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Point (double x, double y) {
		
		// check if the new X, Y in the First quarter
		boolean isValidXCoordinate =  x > Q1_DEFAULT_VAL;
		boolean isValidYCoordinate =  y > Q1_DEFAULT_VAL;
		
		// assign values to coordinates by their check
		x = isValidXCoordinate ? x : Q1_DEFAULT_VAL;
		y = isValidYCoordinate ? y : Q1_DEFAULT_VAL;
		
		// Reference to the private method to set the Radius and Alpha
		setRadiusAndAlpha(x, y);
	}
	
	/**
	 * Copy Constructor for a Point by new Point Object
	 * @param other object to create new copy from 
	 */
	public Point (Point other) {
		
		// check if the new Point Object is not null
		boolean validObj =  other != null ;
		
		if (validObj) {
			_radius = other._radius;
			_alpha = other._alpha;
		}
	}
	
	/*
	  Private function that sets the Alpha and Radius By the Coordinates thats been given
	  @param x  coordinate
	  @param y  coordinate
	 */
	private void setRadiusAndAlpha(double x, double y) {
		
		double radiusCalculateInnerPhrase = Q1_DEFAULT_VAL,
				alphaInRadians = Q1_DEFAULT_VAL;
		
		boolean isXEqualsZero =  x == Q1_DEFAULT_VAL;

		//alpha calculation - convert to degrees from radians
		if (! isXEqualsZero) {		
			alphaInRadians = Math.atan( y / x );
			_alpha = CONVERT_RADIANS_TO_DEGREES * alphaInRadians;
		}
		else
			_alpha = RIGHT_ANGLE_DEGREES;
				
		//radius calculation
		radiusCalculateInnerPhrase = Math.pow(x, TWO_POW) +  Math.pow(y, TWO_POW);
		_radius = Math.sqrt(radiusCalculateInnerPhrase);		
	}
	
	/*
	   Private method that Return a number after round
	   @param coordinate to round value
	   @return Rounded number
	 */
	private double roundCoordinate(double coordinate) {
		
		return ( Math.round(coordinate * FIXED_ROUND_PARAM ) / (double)FIXED_ROUND_PARAM );
	}
	
	/*
	  Private method that Convert alpha to radians
	  @param alpha angle in degrees for convert
	  @return alpha in radians
	 */
	private double convertToRadians(double alpha) {
		
		return CONVERT_DEGREES_TO_RADIANS * alpha;
	}
	
	/**
	 * Returns the X coordinate of the Point
	 * @return the X coordinate
	 */
	public double getX() {
		
		double xCoordinate = Q1_DEFAULT_VAL,
				convertedAlpha = Q1_DEFAULT_VAL;
		
		// convert alpha to radians
		convertedAlpha = convertToRadians(_alpha);
		// calculate the X value, by the Math.cos() function
		xCoordinate = Math.cos(convertedAlpha) * _radius;
		
		return roundCoordinate(xCoordinate);

		//return xCoordinate;
	}
	
	/**
	 * Returns the Y coordinate of the Point
	 * @return the Y coordinate
	 */
	public double getY() {
		
		double yCoordinate = Q1_DEFAULT_VAL,
				convertedAlpha = Q1_DEFAULT_VAL;
		
		// convert alpha to radians
		convertedAlpha = convertToRadians(_alpha);
		// calculate the Y value, by the Math.Sin() function
		yCoordinate = Math.sin(convertedAlpha) * _radius;
		
		
		return roundCoordinate(yCoordinate);
		//return yCoordinate;
	}
	
	/**
	 * Sets the X Coordinate of the point
	 * if negative value is received, the X coordinate will not change
	 * @param num the new X Value to set
	 */
	public void setX(double num) {
		
		// validate the new X value to set X
		boolean isNumNegative =  num < Q1_DEFAULT_VAL;
		
		if (! isNumNegative ) {
			setRadiusAndAlpha(num, getY());
		}
	}
	
	/**
	 * Sets the Y Coordinate of the point
	 * if negative value is received, the Y coordinate will not change
	 * @param num the new Y Value to set
	 */
	public void setY(double num) {
		
		// validate the new Y value to set Y
		boolean isNumNegative =  num < Q1_DEFAULT_VAL;
		
		if (! isNumNegative ) {
			setRadiusAndAlpha(getX(), num);
		}
	}
	
	/**
	 * Return a string representing of the Point.
	 * @return String Represent the Point coordinates
	 */
	public String toString() {
	
		// Round coordinates when print Point
		double xToPrint = roundCoordinate(getX());
		double yToPrint = roundCoordinate(getY());
		
		return "("+xToPrint+","+yToPrint+")";
	}
	
	/**
	 * Check if this point is equal to other.
	 * @param other new Point to compare with this point
	 * @return true is this point is equal to the other Point
	 */
	public boolean equals(Point other) {
		
		// check if the X values are same
		boolean areXValusMatch = getX() == other.getX();
		// check if the Y values are same
		boolean areYValusMatch = getY() == other.getY();
		
		return areXValusMatch && areYValusMatch ;
	}
	
	/**
	 * Check if this point is above other point
	 * @param other point to compare with this point
	 * @return true if this point is above other point
	 */
	public boolean isAbove(Point other) {
		
		return getY() > other.getY();
	}
	
	/**
	 * Check if this point is Under other point
	 * @param other point to compare with this point
	 * @return true if this point is Under other point
	 */
	public boolean isUnder(Point other) {
		
		return other.isAbove(this);
	}
		
	/**
	 * Check if this point is to the left of other point
	 * @param other point to compare with this point
	 * @return true if this point is to the left of other point
	 */
	public boolean isLeft(Point other) {
		
		return getX() < other.getX();
	}

	/**
	 * Check if this point is to the right of other point
	 * @param other point to compare with this point
	 * @return true if this point is to the right of other point
	 */
	public boolean isRight(Point other) {
		
		return other.isLeft(this);
	}
	
	/**
	 * Calculate the distance between this point and other point
	 * @param p new point to calculate the distance from
	 * @return the distance between this and P Point
	 */
	public double distance(Point p) {
		
		double totalDistance = Q1_DEFAULT_VAL,
				xDistance = Q1_DEFAULT_VAL,
				yDistance = Q1_DEFAULT_VAL;
		
		// calculate each distance between X's and Y's
		xDistance = Math.pow( ( getX() - p.getX() ) , TWO_POW);
		yDistance = Math.pow( ( getY() - p.getY() ) , TWO_POW);
		// final distance calculation
		totalDistance = Math.sqrt(yDistance + xDistance );
		
		return totalDistance;
	}
	
	/**
	 * Moves the X Coordinate By dX and Y Coordinate By dY.
	 * if movement moves the point outside of first quadrant, then the
	 * point will remain at the same place and will not move.
	 * @param dx the amount to move in the X direction
	 * @param dy the amount to move in the Y direction
	 */
	public void move(double dx, double dy) {
		
		//calculate the new x value after change
		double newXLocation = getX() + dx;
		//calculate the new Y value after change
		double newYLocation = getY() + dy;
		
		// check if the changes lead to any unvalid move
		boolean isXUnvalidAfterMove = newXLocation < Q1_DEFAULT_VAL; 
		boolean isYUnvalidAfterMove = newYLocation < Q1_DEFAULT_VAL;
		
		// check if any one of the checks above turned TRUE
		boolean isUnvalidMove = isXUnvalidAfterMove || isYUnvalidAfterMove ;
		
		// Only if all the checks came VALID, we will set the inner attributes accordingly
		if ( ! isUnvalidMove )
			setRadiusAndAlpha( newXLocation, newYLocation );
	}
}
