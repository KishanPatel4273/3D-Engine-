package engine;

import camera.Camera;
import tools.*;
import Window.*;

public class Engine extends Render {
	
	private Camera camera;
	
	public Engine(int width, int height) {
		super(width, height);
		camera = new Camera();
	}
	
	float a =  (float) (Math.PI/4.0f);
	float x=500, y=500, z=0;
	public void pipeline(Entity[] e, boolean[] key) {
		clear();
		
		camera.update(key);

		for(int i = 0; i < e.length; i++) {
			a+=Math.PI/1240.0f;
			Mesh mesh = Mesh.rotate(e[i].getMesh(), new Vector(0,1,0), a);
			mesh = Mesh.translate(mesh, new Vector(x - camera.position.getX(),y - camera.position.getY(),z - camera.position.getZ()));		
			Triangle[] triangles = mesh.getTriangles();
			
			for(int j = 0; j < triangles.length; j++) {	
				if(Vector.dotPoduct(camera.orientation, Triangle.normal(triangles[j])) < -1.0f) {
					setColor(0);
					drawTriangle(triangles[j]);
					//System.out.println(triangles[j].toString());
				}
			}	
			
		}
	}
	
}
