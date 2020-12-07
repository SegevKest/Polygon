/**
 * Polygon Class
 * Represent a Polygon in the Polar System - by array of Vertexes
 * @author Segev Kestenbaum
 * @version 05.12.2020
*/
public class Polygon {

	private Point[] _vertices;
	private int _noOfVertices;
	
	private final int MAX_OF_VERTICES = 10;
	private final int DEFAULT_ZERO = 0;

	
	/**
	 * Constructor of the class
	 * Initiate all private attributes of Class
	 */
	public Polygon () {
		
		_vertices = new Point [MAX_OF_VERTICES];
		_noOfVertices = DEFAULT_ZERO;
	}
	
	
	/**
	 * The method will try to add a new vertex to the Array
	 * @param x value of the Vertex coordinate
	 * @param y value of the Vertex coordinate
	 * @return true if the insertion made succesfuly, else false;
	 */
	public boolean addVertex (double x , double y) {
				
		if ( _noOfVertices == MAX_OF_VERTICES )
			return false;
		
		_vertices[ _noOfVertices ] = new Point(x , y);
		_noOfVertices++; // update number of Vertices
		
		return true;
	}
	
	
	
	/**
	 * the method will return a copy of the Vertex that is the highest in the Polygon
	 * @return a copy of the Point with the highest Y value - the highest Point
	 */
	public Point highestVertex () {
		
		Point highestPoint = _vertices[0] ;
		
		// check if the first element in the array is null
		if ( _noOfVertices == DEFAULT_ZERO )
					return null;
		// iterate over all array, and check all y values and keep their indexes. 
		for( int i = 1; i< _noOfVertices ; i++) {
		
			if (  _vertices[i].isAbove( highestPoint ) ) 
				highestPoint = _vertices[i] ; //assign Max y value found until now
		}
		
		return new Point( highestPoint );  // return new Point Object as a copy of the higest Point
	}
	
	
	
	
	/**
	 * overrides the existing toString method
	 * @return the string will contain a sentence of explanation with the coordinates
	 */
	public String toString() {
		
		String finalOutput = "";
		
		if ( _noOfVertices == DEFAULT_ZERO)
			return "The polygon has 0 vertices.";
		
		finalOutput += "The polygon has "+ _noOfVertices +" vertices:\n" ; // first Line of output
		finalOutput +=  "(" + _vertices[0].toString();
		
		for( int i = 1; i < _noOfVertices ; i++ ) 		
			finalOutput += "," + _vertices[i].toString();
		
		return finalOutput + ")";
	}

	
	
	
	/**
	 * The method will calculate and return the Perimeter of the Polygon
	 * @return the perimeter of the all the Polygon
	 */
	public double calcPerimeter() {
		
		double returnedPerimeter = DEFAULT_ZERO;
		final int oneVertex = DEFAULT_ZERO + 1;
		final int twoVertices = oneVertex + 1;
		
		// use switch to differ the different scenarios of vertices
		// 0, 1 ,2 , more than 2 vertices
		switch ( _noOfVertices ) {
		
		case DEFAULT_ZERO : // no vertices
				return DEFAULT_ZERO;
		
		case oneVertex : // 1 vertex
				return DEFAULT_ZERO;
		
		case twoVertices : // 2 vertices
				returnedPerimeter = _vertices[DEFAULT_ZERO].distance( _vertices[oneVertex] );
				break;
 
		default: // more than 2 vertices
				for( int i = 0; i < _noOfVertices - 1 ; i++ ) 
					// calculate the distance between all the Vertices, except the distance between the first and last vertexes.
					returnedPerimeter += _vertices[i].distance( _vertices[i + 1] ); 
				
				// calculate the distance between the first and last Vertices
				returnedPerimeter += _vertices[DEFAULT_ZERO].distance( _vertices[_noOfVertices - 1] );	
		}
		
		// return the Perimeter after calculation in any case matched
		return returnedPerimeter;
	}
	
	
	
	
	// private method to calculate the area of a triangle using the Heron formula
	// using all sides of triangle and semiperimeter of the polygon
	private double heronCalcuteAreaOfTriangle( Point pointA, Point pointB, Point pointC ) {
		
		double sideA = DEFAULT_ZERO,  sideB = DEFAULT_ZERO, sideC = DEFAULT_ZERO, semiPerimeter = DEFAULT_ZERO;
		double innerMultipli = DEFAULT_ZERO, currentTriangle = DEFAULT_ZERO;
				
		// calculate first side 
		sideA = pointA.distance( pointB );
		
		// calculate second side 
		sideB = pointA.distance( pointC );
		
		// calculate third side 
		sideC = pointB.distance( pointC );
		
		// calculate semi premieter of the triangle
		semiPerimeter = ( sideA + sideB + sideC ) / 2 ;
		
		// call the inner multiplication between the Parenthesis
		innerMultipli = ( semiPerimeter - sideA ) * ( semiPerimeter - sideB ) * ( semiPerimeter - sideC );
		
		currentTriangle = Math.sqrt( semiPerimeter * innerMultipli );
		
		return currentTriangle;
	}

	
	
	
	/**
	 *  The method will calculate the Area of the Polygon using the Heron Formula
	 * @return a number represent the Area of the Polygon
	 */
	public double calcArea() {
		
		double totalArea = DEFAULT_ZERO;
		final int THREE_VERTICES = 3;
		
		Point firstVertex = _vertices[DEFAULT_ZERO]; // first Point, to calculate the  
				
		// if there are less than 3 vertices - return 0
		if( _noOfVertices < THREE_VERTICES)
			return DEFAULT_ZERO;
		
		for ( int i = 1; i < _noOfVertices - 1; i ++ ) 
			// add every triangle area to the total are - calculate using the private method, with the semiperimeter.
		    totalArea += this.heronCalcuteAreaOfTriangle( firstVertex, _vertices[i], _vertices[i + 1]);
		
		return totalArea;
	}
	
	
	
	/**
	 * The method will check if the current Polygon is bigger than the parameter Polygon
	 * by checking which area is bigger.
	 * @param other a new Polygon to check areas with the current Polygon
	 * @return true if the current Polygon is bigger that the Parameter 
	 */
	public boolean isBigger( Polygon other ) {
		
		// calculate the expression, using the calcArea method, used the 'this' for more readabilty
		boolean imBiggerPolygon = this.calcArea() > other.calcArea();
		
		return imBiggerPolygon;
	}
	
	
	
	
	
	/**
	 * The method will try to find a given point in between the vertices - if found - returns true, else - false.
	 * @param p represent a new point to find in the Array of vertices
	 * @return the index of the matched point if matched, else -1.
	 */
	public int findVertex( Point p ) {
		
		int matchedPointIndex = DEFAULT_ZERO ;
		final int RESULT_IF_NOT_FOUND = -1 ;
		boolean found = false;
		
		for( int i = 0; i < _noOfVertices && ! found ; i++ ) {
			
			// check if any of the vertices is equal to the Point parameter
			if( _vertices[i].equals( p )) {
				matchedPointIndex = i; // saves the Index in the 
				found = true;
			}
		}
		
		if ( ! found ) 
			return  RESULT_IF_NOT_FOUND;
		
		return matchedPointIndex;				
	}
	
	
	
	
	/**
	 * The method will return the next Point in the Polygon, after the given point,
	 * If the Point given is not exist in the polygon, - return null;
	 * @param p
	 * @return
	 */
	public Point getNextVertex(Point p) {
		
		final int SINGLE_VERTEX = 1;
		boolean isPointExistInPolygon, isPointLastInPolygon;
		
		// get the index of the Point given as parameter
		int indexOfParamPoint = this.findVertex( p );		
		
		// evaluate these expressions
		isPointExistInPolygon =  indexOfParamPoint >= DEFAULT_ZERO ;
		
		// if the Point does not belongs to the Polygon - return null
		if ( ! isPointExistInPolygon )
			return null;
		
		isPointLastInPolygon = p.equals( _vertices[ _noOfVertices - 1] ); 
		
		 
		// if there is only single vertex OR the point given is the last in the Array - return the first vertex in the Array
		if( _noOfVertices == SINGLE_VERTEX  || isPointLastInPolygon )
			return new Point ( _vertices[DEFAULT_ZERO ] );
		
		// if all the conditions above did not occur - return a copy of the next point in the array
		return new Point( _vertices [ indexOfParamPoint + 1] );
	}
	
	
	
	// the private method will return the point located at the exterme end of each side of the polyon
	// the most right point, the most left point and the bottom point
	private Point returnEndPointByRequest( String sideOfEndPoint ) {
		
		Point returnedPoint = _vertices[0] ;
		
		// in case of string equals : Left, Right , Bottom
		switch ( sideOfEndPoint ) {
		
			case "Left":
				// find the most left Point in the Polygon 
				for( int i = 1; i< _noOfVertices ; i++) {
					if (  _vertices[i].isLeft( returnedPoint ) ) 
							returnedPoint = _vertices[i] ; //assign left X value found until now
				}
				break;
				
			case "Right":
				// find the most right Point in the Polygon 
				for( int i = 1; i< _noOfVertices ; i++) {
					if (  _vertices[i].isRight( returnedPoint ) ) 
							returnedPoint = _vertices[i] ; //assign right X value found until now
				}
				break;
			
			case "Bottom":
				// find the most lowest Point in the Polygon 
				for( int i = 1; i< _noOfVertices ; i++) {
					if (  _vertices[i].isUnder(returnedPoint) ) 
							returnedPoint = _vertices[i] ; //assign lowest y value found until now
				}
				break;	
		}

		return new Point( returnedPoint );
	}
	
	/**
	 * The method will return a Polygon object - represent the bounding box rectangle to the polygon
	 * @return Polygon Object - Bounding rectangle of the Polygon
	 */
	public Polygon getBoundingBox() {
		
		Polygon boundingBox = new Polygon();
		
		final int THREE_VERTICES = 3;
		
		// check if smaller than 3 vertices
		if ( _noOfVertices < THREE_VERTICES )
			return null;		
		
		// get the Y Value of the highest Point in the Polygon
		double yValueOfhighestPoint = this.highestVertex().getY(); 
		
		// get the Y Value of the Lowest Point in the Polygon
		double yValuelowestPoint = this.returnEndPointByRequest("Bottom").getY();
		
		// get the X Value of the Right Point in the Polygon
		double xValueRightPoint = this.returnEndPointByRequest("Right").getX();

		// get the X Value of the Left Point in the Polygon
		double xValueLeftPoint = this.returnEndPointByRequest("Left").getX();
		
		
		// Adding all the vertices to the Polygon 
		
		// first Vertex - Bottom Left
		boundingBox.addVertex( xValueLeftPoint, yValuelowestPoint );
		
		// second Vertex - Bottom Right
		boundingBox.addVertex( xValueRightPoint, yValuelowestPoint );
		
		// third Vertex - Top Right
		boundingBox.addVertex( xValueRightPoint, yValueOfhighestPoint );
		
		// fourth Vertex - Top Left
		boundingBox.addVertex( xValueLeftPoint, yValueOfhighestPoint );
		
		return boundingBox;
	}
}



