package camera;

import java.awt.event.KeyEvent;

import tools.Vector;

public class Camera {
	
	public Vector position, orientation;
	private float movementspeed;
	
	public Camera() {
		position = new Vector(0,0,0);
		orientation = new Vector(0,0,1);
		movementspeed = 10;
	}
	
	public void update(boolean[] key) {
		if(key[KeyEvent.VK_D]) {
			position.addX(movementspeed);
		}
		if(key[KeyEvent.VK_A]) {
			position.addX(-movementspeed);
		}
		if(key[KeyEvent.VK_W]) {
			position.addY(-movementspeed);
		}
		if(key[KeyEvent.VK_S]) {
			position.addY(movementspeed);
		}
		if(key[KeyEvent.VK_RIGHT]) {
			orientation.addZ(movementspeed/180.0f);
		}
	}
	
}
