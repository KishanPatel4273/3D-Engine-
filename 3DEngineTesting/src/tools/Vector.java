package tools;

public class Vector {
	
	public static Vector i = new Vector(1,0,0);
	public static Vector j = new Vector(0,1,0);
	public static Vector k = new Vector(0,0,1);
	
	private float x, y, z, w;

	public Vector() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 1;
	}
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
		this.w = 1;
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1;
	}

	public Vector(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public float getMagnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public void addX(float x) {
		this.x += x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void addY(float y) {
		this.y += y;
	}
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public void addZ(float z) {
		this.z += z;
	}
	
	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public String toString() {
		return "(" + x + ", " + y + ", " + z + ", " + w + ")";
	}

	public static Vector scale(Vector v, float scale) {
		return new Vector(v.getX() * scale, v.getY() * scale, v.getZ() * scale);
	}

	public static Vector normalize(Vector v) {
		return scale(v, 1.0f/v.getMagnitude());
	}

	public static float dotPoduct(Vector a, Vector b) {
		return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
	}

	public static Vector crossProduct(Vector a, Vector b) {
		return new Vector(a.getY() * b.getZ() - a.getZ() * b.getY(),
						  a.getZ() * b.getX() - a.getX() * b.getZ(),
				          a.getX() * b.getY() - a.getY() * b.getX());
	}

	public static Vector add(Vector a, Vector b) {
		return new Vector(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
	}

	public static Vector subtract(Vector a, Vector b) {
		return new Vector(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
	}

}
