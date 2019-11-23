package tools;

public class Quaternion {

	private float w, x, y, z;

	public Quaternion(float w, float x, float y, float z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Quaternion(double w, double x, double y, double z) {
		this.w = (float) w;
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
	}

	public Quaternion(float w, Vector q) {
		this.w = w;
		this.x = q.getX();
		this.y = q.getY();
		this.z = q.getZ();
	}

	public Quaternion(double w, Vector q) {
		this.w = (float) w;
		this.x = q.getX();
		this.y = q.getY();
		this.z = q.getZ();
	}

	public float getW() {
		return w;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float getMagnitude() {
		return (float) Math.sqrt(w * w + x * x + y * y + z * z);
	}

	public Vector getVector() {
		return new Vector(x, y, z);
	}

	public String toString() {
		return "[" + w + ", " + x + ", " + y + ", " + z + "]";
	}

	public static Quaternion normalize(Quaternion q) {
		return new Quaternion(q.getW() / q.getMagnitude(), Vector.scale(q.getVector(), 1.0f / q.getMagnitude()));
	}
	
	public static Quaternion scale(Quaternion q, float scale) {
		return new Quaternion(q.getW()*scale, Vector.scale(q.getVector(), scale));
	}

	public static Quaternion conjugate(Quaternion q) {
		return new Quaternion(q.getW(), -q.getX(), -q.getY(), -q.getZ());
	}
	
	public static Quaternion add(Quaternion q1, Quaternion q2) {
		return new Quaternion(q1.getW()+q2.getW(), Vector.add(q1.getVector(), q2.getVector()));
	}
	
	public static Quaternion multiply(Quaternion q1, Quaternion q2) {
		return new Quaternion(q1.getW() * q2.getW() - Vector.dotPoduct(q1.getVector(), q2.getVector()),
				Vector.add(Vector.add(Vector.scale(q2.getVector(), q1.getW()), Vector.scale(q1.getVector(), q2.getW())),
						Vector.crossProduct(q1.getVector(), q2.getVector())));
	}

	public static Quaternion rotation(double theta, Vector u) {
		Vector v = Vector.normalize(u);
		double c = Math.cos(theta/2.0f);
		double s = Math.sin(theta/2.0f);
		return new Quaternion(c, s*v.getX(), s*v.getY(), s*v.getZ());
	}
	
	public static Quaternion rotate(Vector u, Vector v, float theta) {
		Quaternion q = rotation(theta, u);
		Quaternion qC = conjugate(q);	
		Quaternion p = new Quaternion(0, v);
		return multiply(multiply(q,p),qC);
	}

}
