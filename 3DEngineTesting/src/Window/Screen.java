package Window;
import engine.Engine;
import tools.*;

public class Screen {
	
	public Entity[] objects;
	public Engine engine;
	private Entity cube, sphere, torus;
		
	public Screen(int width, int height) {
		engine = new Engine(width, height);
		cube = new Entity("res/Cube.obj", new Vector(300, 0, 0), new Vector(0,0,0), 150f);
		sphere = new Entity("res/Sphere.obj", new Vector(0, 0, 0), new Vector(0,0,(float)Math.PI/4.0f), 150f);
		torus = new Entity("res/Torus.obj", new Vector(-300, 0, 0), new Vector(0,0,0), 150f);
		objects = new Entity[] {sphere, torus, cube};
		}
	
	public void update(boolean[] key) {
		engine.pipeline(objects, key);
		
	}
	
}
