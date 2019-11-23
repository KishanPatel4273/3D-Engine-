package engine;

import camera.Camera;
import tools.*;

import java.util.ArrayList;

import Window.*;

public class Engine extends Render {
	
	private Camera camera;
	
	public Engine(int width, int height) {
		super(width, height);
		camera = new Camera();
	}
	
	float a =  (float) (Math.PI/4.0f) * 0;
	float x=500, y=500, z=0;
	public void pipeline(Entity[] e, boolean[] key) {
		clear();
		
		camera.update(key);
		//runs 60fps
		//if not updated doesn't rotate
		//if translated object doesn't updated rotation axis
			//translates the axis with it, so screen moves with no effect on camera
		//make it more simple less operations per second
		
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for(int i = 0; i < e.length; i++) {
			a+=Math.PI/100.0f;
			Mesh mesh = Mesh.rotate(e[i].getMesh(), new Vector(1,1,0), a);
			mesh = Mesh.translate(mesh, new Vector(x - camera.position.getX(),y - camera.position.getY(),z - camera.position.getZ()));		
			
			for(Triangle triangle : mesh.getTriangles()) {
				triangles.add(triangle);
			}
			
			//triangles = sort(triangles);
			
			for(int j = 0; j < triangles.size(); j++) {	
				if(Vector.dotPoduct(camera.orientation, Triangle.normal(triangles.get(j))) > 0.0f) {
					setColor(255);
					//fillTriangle(triangles.get(j));
					setColor(0);
					drawTriangle(triangles.get(j));
					//System.out.println(triangles[j].toString());
				}
			}	
		}
	}
	
	public ArrayList<Triangle> sort(ArrayList<Triangle> t){
		ArrayList<Triangle> t2 = t;
		for(int i = 0; i < t.size(); i++) {
			for(int j = i; j < t.size(); j++) {
				if(t2.get(i).getAvergageZ() < t2.get(j).getAvergageZ()) {
					Triangle temp = t2.get(i);
					t2.set(i, t2.get(j));
					t2.set(j, temp);
				}
			}
		}
		return t2;
	}
}
