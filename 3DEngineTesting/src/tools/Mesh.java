package tools;

public class Mesh {

	private Triangle[] triangles;

	public Mesh(Triangle[] triangles) {
		this.triangles = triangles;
	}

	public Triangle[] getTriangles() {
		return triangles;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < triangles.length; i++) {
			str += triangles[i].toString();
		}
		return str;
	}

	public static Mesh rotate(Mesh mesh, Vector orientation) {
		Triangle[] triangles = mesh.getTriangles();
		Triangle[] toWorldMesh = new Triangle[triangles.length];
		for(int i = 0; i < triangles.length; i++) {//runs through triangle
			Triangle trangle = triangles[i];
			Vector[] toWorldTriangle = new Vector[3];
			for(int j = 0; j < 3; j++) {//runs through points
				Quaternion vertex = new Quaternion(0, trangle.getVertices()[j]);
				vertex = Quaternion.rotate(Vector.i, vertex.getVector(), orientation.getX());//x
				vertex = Quaternion.rotate(Vector.j, vertex.getVector(), orientation.getY());//y
				vertex = Quaternion.rotate(Vector.k, vertex.getVector(), orientation.getZ());//z
				toWorldTriangle[j] = vertex.getVector();
			}
			toWorldMesh[i] = new Triangle(toWorldTriangle);
		}
		return new Mesh(toWorldMesh);
	}

	public static Mesh translate(Mesh mesh, Vector position) {
		Triangle[] triangles = mesh.getTriangles();
		Triangle[] toWorldMesh = new Triangle[triangles.length];
		for(int i = 0; i < triangles.length; i++) {//runs through triangle
			Triangle trangle = triangles[i];
			Vector[] toWorldTriangle = new Vector[3];
			for(int j = 0; j < 3; j++) {//runs through points
				Quaternion vertex = new Quaternion(0, trangle.getVertices()[j]);
				vertex = Quaternion.add(vertex, new Quaternion(0, position));
				toWorldTriangle[j] = vertex.getVector();
			}
			toWorldMesh[i] = new Triangle(toWorldTriangle);
		}
		return new Mesh(toWorldMesh);
	}
	
	public static Mesh rotate(Mesh mesh, Vector axis, float theta) {
		if(axis.getMagnitude() == 0) {
			return mesh;
		}
		Triangle[] triangles = mesh.getTriangles();
		Triangle[] toWorldMesh = new Triangle[triangles.length];
		for(int i = 0; i < triangles.length; i++) {//runs through triangle
			Triangle trangle = triangles[i];
			Vector[] toWorldTriangle = new Vector[3];
			for(int j = 0; j < 3; j++) {//runs through points
				Quaternion vertex = new Quaternion(0, trangle.getVertices()[j]);
				vertex = Quaternion.rotate(axis, vertex.getVector(), theta);//x
				toWorldTriangle[j] = vertex.getVector();
			}
			toWorldMesh[i] = new Triangle(toWorldTriangle);
		}
		return new Mesh(toWorldMesh);
	}

	public static Mesh scale(Mesh mesh, float scale) {
		Triangle[] triangles = mesh.getTriangles();
		Triangle[] toWorldMesh = new Triangle[triangles.length];
		for(int i = 0; i < triangles.length; i++) {//runs through triangle
			Triangle trangle = triangles[i];
			Vector[] toWorldTriangle = new Vector[3];
			for(int j = 0; j < 3; j++) {//runs through points
				Quaternion vertex = new Quaternion(0, trangle.getVertices()[j]);
				vertex = Quaternion.scale(vertex, scale);
				toWorldTriangle[j] = vertex.getVector();
			}
			toWorldMesh[i] = new Triangle(toWorldTriangle);
		}
		return new Mesh(toWorldMesh);
	}
	
	public static Mesh rotateTranslate(Mesh mesh, Vector position, Vector orientation) {
		return translate(rotate(mesh, orientation), position);
	}
	
	
	private static Mesh meshUpdate(Mesh mesh, Vector position, Vector orientation, float scale) {
		Triangle[] triangles = mesh.getTriangles();
		Triangle[] toWorldMesh = new Triangle[triangles.length];
		for(int i = 0; i < triangles.length; i++) {//runs through triangle
			Triangle trangle = triangles[i];
			Vector[] toWorldTriangle = new Vector[3];
			for(int j = 0; j < 3; j++) {//runs through points
				Quaternion vertex = new Quaternion(0, trangle.getVertices()[j]);
				vertex = Quaternion.rotate(Vector.i, vertex.getVector(), orientation.getX());//x
				vertex = Quaternion.rotate(Vector.j, vertex.getVector(), orientation.getY());//y
				vertex = Quaternion.rotate(Vector.k, vertex.getVector(), orientation.getZ());//z
				vertex = Quaternion.scale(vertex, scale);
				vertex = Quaternion.add(vertex, new Quaternion(0, position));
				toWorldTriangle[j] = vertex.getVector();
			}
			toWorldMesh[i] = new Triangle(toWorldTriangle);
		}
		return new Mesh(toWorldMesh);
	}
	
}
