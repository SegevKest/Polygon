
public class Polygon {

	private Point[] _vertices;
	private int _noOfVertices;
	
	private final int MAX_OF_VERTICES = 10;
	private final int DEFAULT_ZERO = 0;

	
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
		
		_vertices[ _noOfVertices++ ] = new Point(x , y);
		return true;
	/*	
		for (int i = 0; i< _vertices.length ; i++) {
			
			if ( _vertices[i].equals(null) ) {
				_vertices[i] = new Point(x , y);
				_noOfVertices++ ;
				return true;
			}	
		}
		
	*/
	}
	
	/**
	 * the method will return a copy of the Vertex that is the highest in the Polygon
	 * @return a copy of the Point with the highest Y value - the highest Point
	 */
	public Point highestVertex () {
		
		
		double highestYValue = 0 ;
		double indexOfHighestY = 0 ;
		Point highestPoint = _vertices[0] ;
		/*
		// check if the first element in the array is null
		if ( _noOfVertices == 0 )
			return null;
		
		// iterate over all array, and check all y values and keep their indexes. 
		for( int i =0; i< _noOfVertices ; i++) {
			
			if (  _vertices[i].getY() > highestYValue ) {
				highestYValue = _vertices[i].getY() ; //assign Max y value found until now
				indexOfHighestY = i ; // keep the max y value point position in the Array
			}
		}
		return new Point( _vertices[ indexOfHighestY ] );
		
		*/
		
		// check if the first element in the array is null
				if ( _noOfVertices == DEFAULT_ZERO )
					return null;
		// iterate over all array, and check all y values and keep their indexes. 
				for( int i =1; i< _noOfVertices ; i++) {
					
					if (  _vertices[i].isAbove( highestPoint ) ) {
						highestPoint = _vertices[i] ; //assign Max y value found until now
					}
				}
				return new Point( highestPoint );
					
		
	}
	
	
	/**
	 * overrides the existing toString method
	 * @return the string will contain a sentence of explanation with the coordinates
	 */
	public String toString() {
		
		String finalOutput = "";
		
		if ( _noOfVertices == DEFAULT_ZERO)
			return "The polygon has 0 vertices";
		
		finalOutput += "The polygon has "+ _noOfVertices +" vertices: " ; // first Line of output
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
		final int twoVertexes = oneVertex + 1;
		

		switch ( _noOfVertices ) {
		
		case DEFAULT_ZERO : // no vertexes
				return returnedPerimeter;
		
		case oneVertex : // 1 vertex
				return returnedPerimeter;
		
		case twoVertexes : // 2 vertexes
				returnedPerimeter = _vertices[DEFAULT_ZERO].distance( _vertices[oneVertex] );
		
		default: // more than 2 vertexes
			for( int i = 0; i < _noOfVertices - 1 ; i++ ) {
				// calculate the distance between all the vertexes, except the distance between the first and last vertexes.
				returnedPerimeter += _vertices[i].distance( _vertices[i + 1] ); 
			}
			// calculate the distance betweent the first and last Vertexes
			returnedPerimeter += _vertices[DEFAULT_ZERO].distance( _vertices[_noOfVertices - 1] );	
		}
		
		// return the Perimeter after calculation 
		return returnedPerimeter;
		
	}
}



