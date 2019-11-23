package Window;
import engine.Engine;
import tools.*;

public class Screen {
	
	public Entity[] objects;
	public Engine engine;
	private Entity cube, sphere, torus, teapot;
		
	public Screen(int width, int height) {
		engine = new Engine(width, height);
		cube = new Entity("res/Cube.obj", new Vector(0, 0, 0), new Vector(0,0,0), 150f);
		sphere = new Entity("res/Sphere.obj", new Vector(0, 0, 300), new Vector(0,0,0), 500f);
		torus = new Entity("res/Torus.obj", new Vector(0,0, 0), new Vector(1,0,0), 250f);
		teapot = new Entity("res/Teapot.obj", new Vector(0, 0, 0), new Vector(1,1,1), 150f);
		objects = new Entity[] {teapot};
		}
	
	public void update(boolean[] key) {
		engine.pipeline(objects, key);
		
	}
	
}
