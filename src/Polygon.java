
public class Polygon {

	private Point[] _vertices = null;
	private int _noOfVertices;
	
	private final byte MAX_OF_VERTICES = 10;
	
	
	public Polygon () {
		
		_vertices = new Point [MAX_OF_VERTICES];
		_noOfVertices = 0;
	}
	
	/**
	 * The method will try to add a new vertex to the Array
	 * @param x value of the Vertex coordinate
	 * @param y value of the Vertex coordinate
	 * @return true if the insertion made succesfuly, else false;
	 */
	public boolean addVertex (double x , double y) {
			
		Point newVertex = new Point(x , y);
		
		for (int i = 0; i< _vertices.length ; i++) {
			
			if ( _vertices[i].equals(null) ) {
				_vertices[i] = newVertex;
				_noOfVertices++ ;
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * the method will return a copy of the Vertex that is the highest in the Polygon
	 * @return a copy of the Point with the highest Y value - the highest Point
	 */
	public Point highestVertex () {
			
		double highestYValue = 0 ;
		int indexOfHighestY = 0 ;
		
		// check if the first element in the array is null
		if ( _vertices[0].equals(null) )
			return null;
		
		// iterate over all array, and check all y values and keep their indexes. 
		for( int i =0; i< _noOfVertices ; i++) {
			
			if (  _vertices[i].getY() > highestYValue ) {
				highestYValue = _vertices[i].getY() ; //assign Max y value found until now
				indexOfHighestY = i ; // keep the max y value point position in the Array
			}
		}
		return new Point( _vertices[ indexOfHighestY ] );
	}
	
	
	/**
	 * overrides the existing toString method
	 * @return the string will contain a sentence of explanation with the coordinates
	 */
	public String toString() {
		
		String finalOutput = "";
		
		if ( _noOfVertices == 0)
			return "The polygon has 0 vertices";
		
		finalOutput += "The polygon has "+ _noOfVertices +" vertices: " ; // first Line of output
		finalOutput +=  "(" + _vertices[0].toString();
		
		for( int i = 1; i < _noOfVertices ; i++ ) 		
			finalOutput += "," + _vertices[i].toString();
		
		return finalOutput + ")";
	}
}
