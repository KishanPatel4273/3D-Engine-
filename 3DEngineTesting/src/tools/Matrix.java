package tools;

public class Matrix {
	
	private float[][] matrix;
	
	public Matrix() {
		this.matrix = identity().getMatrix();
	}
	
	public Matrix(float[][] matrix) {
		this.matrix = matrix;
	}
	
	public float[][] getMatrix(){
		return matrix;
	}
	
	public static Matrix identity() {
		float[][] m = new float[4][4];
		m[0][0] = 1.0f;
		m[1][1] = 1.0f;
		m[2][2] = 1.0f;
		m[3][3] = 1.0f;
		return new Matrix(m);
	}
	
	public Matrix matrixTimesMatrix(float[][] a, float[][] b){
		float[][] m = new float[4][4];
		for(int c = 0; c < 4; c++){
			for(int r = 0; r < 4; r++){
				m[r][c] = a[r][0] * b[0][c] + a[r][1] * b[1][c] + a[r][2] * b[2][c] + a[r][3] * b[3][c];
			}
		}
		return new Matrix(m);
	}
	
	
}
