package tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectLoader {

	private ArrayList<Vector> vectors;
	private ArrayList<Vector> vertices;
	private String directory = System.getProperty("user.dir");
	
	public ObjectLoader(String fileName) {
		vectors = new ArrayList<Vector>();
		vertices = new ArrayList<Vector>();
		File file = new File(fileName);
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(file));
			try {
				while ((line = br.readLine()) != null) {
					getVector(line);
					getVertices(line);
				}
				System.out.println(file + " loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void getVector(String line) {
		if (line.indexOf("v") == 0) {
			line = line.substring(2);
			String x = line.substring(0, line.indexOf(" "));
			line = line.substring(line.indexOf(" ") + 1);
			String y = line.substring(0, line.indexOf(" "));
			line = line.substring(line.indexOf(" ") + 1);
			String z = line.substring(0).trim();
			vectors.add(new Vector(new Float(x), new Float(y), new Float(z)));
		}
	}

	private void getVertices(String line) {
		if (line.indexOf("f") == 0) {
			line = line.substring(2);
			String x = line.substring(0, line.indexOf(" "));
			line = line.substring(line.indexOf(" ") + 1);
			String y = line.substring(0, line.indexOf(" "));
			line = line.substring(line.indexOf(" ") + 1);
			String z = line.substring(0).trim();
			vertices.add(new Vector(new Float(x), new Float(y), new Float(z)));
		}
	}
	
	public Mesh getMesh() {
		Triangle[] triangles = new Triangle[vertices.size()];
		for (int i = 0; i < vertices.size(); i++) {
			triangles[i] = new Triangle(vectors.get((int) (vertices.get(i).getX() - 1)),
					vectors.get((int) (vertices.get(i).getY() - 1)), vectors.get((int) (vertices.get(i).getZ() - 1)));
		}
		return new Mesh(triangles);
	}
}