package tools;


public class Entity {
	
	private Mesh mesh;
	private Vector position, orientation;
	
	public Entity(Mesh mesh, Vector position, Vector orientation, float scale) {
		this.mesh = meshToWorld(mesh, position, orientation, scale);
		this.position = position;
		this.orientation = orientation;
	}

	public Entity(String filePath, Vector position, Vector orientation, float scale) {
		this.mesh = meshToWorld(new ObjectLoader(filePath).getMesh(), position, orientation, scale);
		this.position = position;
		this.orientation = orientation;
	}

	public void update() {
		
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public Vector getPosition() {
		return position;
	}

	public Vector getOrientation() {
		return orientation;
	}

	public static Mesh meshToWorld(Mesh mesh, Vector position, Vector orientation, float scale) {
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
