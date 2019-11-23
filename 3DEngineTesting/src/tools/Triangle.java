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
	
	public float getAvergageZ() {
		return (vertices[0].getZ() + vertices[1].getZ() + vertices[2].getZ())/3.0f;
	}
	
	public String toString() {
		return "\n" + vertices[0].toString() + "\n" +  vertices[1].toString() + "\n" +  vertices[2].toString();
	}
 	
	public static Vector normal(Triangle triangle) {
		return Vector.crossProduct(Vector.subtract(triangle.vertices[1], triangle.vertices[0]), Vector.subtract(triangle.vertices[2], triangle.vertices[0]));
	}
	
	public void orderXLeftToRight() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(vertices[i].getX() > vertices[j].getX()) {
					Vector v = vertices[i];
					vertices[i] = vertices[j];
					vertices[j] = v;
				}
			}
		}
	}
	
	public int[] getSameYIndexLeft() {
		if(vertices[0].getY() == vertices[1].getY()) {
			return new int[] {0, 1, 2};
		}
		if(vertices[0].getY() == vertices[2].getY()) {
			return new int[] {0, 2, 1};
		}
		if(vertices[2].getY() == vertices[1].getY()) {
			return new int[] {1, 2, 0};
		}
		return null;
	}
}
