package tools;

public class Triangle {
	
	private Vector[] vertices;
	
	public Triangle() {
		vertices = new Vector[3];
	}
	
	public Triangle(Vector a, Vector b, Vector c) {
		vertices = new Vector[] {a, b, c};
	}
	
	public Triangle(Vector[] vertices) {
		this.vertices = vertices;
	}
	
	public Vector[] getVertices() {
		return vertices;
	}
	
	public String toString() {
		return "\n" + vertices[0].toString() + "\n" +  vertices[1].toString() + "\n" +  vertices[2].toString();
	}
 	
	public static Vector normal(Triangle triangle) {
		return Vector.crossProduct(Vector.subtract(triangle.vertices[1], triangle.vertices[0]), Vector.subtract(triangle.vertices[2], triangle.vertices[0]));
	}
	
}
