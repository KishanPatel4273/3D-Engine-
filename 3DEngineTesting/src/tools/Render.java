package tools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Render {
	
	protected int width, height, color;
	protected int[] pixels;
	
	public Render(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 16777215;
		}
		color = 0;
	}
	
	public void drawPoint(int x, int y, int color){
		if(x > 0 && x < width && y > 0 && y < height){
			pixels[x + (height - y) * width] = color;
		}
	}
	
	/**
	 * @return from (x1, y1) to (x2, y2) draws line
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		if(x1 != x2){
			float slope = ((float)(y1 - y2) / (x1 - x2));
			for(int x = Math.min(x1, x2); x < Math.max(x1, x2); x++){
				int y = (int) ((float)(slope * (x - x1) + y1)); 
				drawPoint(x, y, color);
			}
			for(int y = Math.min(y1, y2); y < Math.max(y1, y2); y++){
				int x = (int) ((float)((y - y1) / slope)) + x1; 
				drawPoint(x, y, color);
			}
		} else {
			for(int y = Math.min(y1, y2); y < Math.max(y1, y2); y++){
				drawPoint(x1, y, color);
			}
		}
	}

	public void drawHorizontalLine(int x1, int x2, int y) {
		for (int x = Math.min(x1, x2); x < Math.max(x1, x2); x++) {
			drawPoint(x, y, color);
		}
	}
	
	/**
	 * @param a and c have same y value
	 */
	public void fillFlatTriangle(Vector a, Vector b, Vector c) {
		float ABdy = (a.getX() - b.getX()) / (a.getY() - b.getY());
		float CBdy = (c.getX() - b.getX()) / (c.getY() - b.getY());
		if(a.getY() > b.getY()) {
			for(int y = 0; y <= a.getY() - b.getY(); y++) {
				drawHorizontalLine((int) (b.getX()+y*ABdy),(int) (b.getX()+y*CBdy), (int) (b.getY() + y));
			}
		} else if(a.getY() < b.getY()) {
			for(int y = 0; y <= b.getY() - a.getY(); y++) {
				drawHorizontalLine((int) (a.getX()+y*ABdy),(int) (c.getX()+y*CBdy), (int) (a.getY() + y));
			}
		}
	}
	
	public void fillTriangle(Vector a, Vector b, Vector c) {
		//split
		if(Math.min(b.getY(), c.getY()) <= a.getY() && a.getY() <= Math.max(b.getY(), c.getY())) {// a is middle
			float BCdx = (c.getY() - b.getY()) / (c.getX() - b.getX());
			float x = (a.getY() - b.getY()) / BCdx + b.getX();
			Vector d = new Vector(x, a.getY());
			fillFlatTriangle(a, b, d);
			fillFlatTriangle(a, c, d);
		} else if(Math.min(a.getY(), c.getY()) <= c.getY() && c.getY() <= Math.max(b.getY(), a.getY())) {// c is middle
			float ABdx = (a.getY() - b.getY()) / (a.getX() - b.getX());
			float x = (c.getY() - b.getY()) / ABdx + b.getX();
			Vector d = new Vector(x, c.getY());
			fillFlatTriangle(c, a, d);
			fillFlatTriangle(c, b, d);
		} else if(Math.min(a.getY(), c.getY()) <= b.getY() && b.getY() <= Math.max(a.getY(), c.getY())) {// b is middle
			float ACdx = (a.getY() - c.getY()) / (a.getX() - c.getX());
			float x = (b.getY() - a.getY()) / ACdx + a.getX();
			Vector d = new Vector(x, b.getY());
			fillFlatTriangle(b, a, d);
			fillFlatTriangle(b, c, d);
		}
	}
	
	public void fillTriangle(Triangle t) {
		t.orderXLeftToRight();
		fillTriangle(t.getVertices()[0], t.getVertices()[1],t.getVertices()[2]);
	}
	
	public void drawTriangle(Vector a, Vector b, Vector c) {
		drawLine((int) a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
		drawLine((int) a.getX(), (int)a.getY(), (int)c.getX(), (int)c.getY());
		drawLine((int) c.getX(), (int)c.getY(), (int)b.getX(), (int)b.getY());
	}
	
	public void drawTriangle(Triangle t) {
		drawTriangle(t.getVertices()[0], t.getVertices()[1],t.getVertices()[2]);
	}
	
	public void drawRectangle(int x1, int y1, int x2, int y2){
		drawLine(x1,y1, x1,y2);
		drawLine(x1,y1, x2,y1);
		drawLine(x2,y1, x2,y2);
		drawLine(x1,y2, x2+1,y2);
	}
	
	public void fillRectangle(int x1, int y1, int x2, int y2){
		for(int x = Math.min(x1, x2); x < Math.max(x1, x2); x++){
			drawLine(x, y1,x, y2);
		}
	}
	
	/**
	 * @param x has length of 3 x-components of the triangle
	 * @param y has length of 3 y-components of the triangle
	 */
	public void drawTriangle(float[] x, float[] y){
		if(x.length == 3 && y.length == 3){
			drawLine((int) x[0], (int) y[0], (int) x[1], (int) y[1]);
			drawLine((int) x[2], (int) y[2], (int) x[1], (int) y[1]);
			drawLine((int) x[0], (int) y[0], (int) x[2], (int) y[2]);
		}
	}
	
	public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3){
		drawTriangle(new float[]{x1,x2,x3},new float[]{y1,y2,y3});
	}
	
	public void drawEllipse(int centerX, int centerY, int a, int b){
		for(int x = centerX - a; x < centerX + a; x++){
			int y = (int) (float)(Math.sqrt((float)b*b * (1f - (float)((x - centerX)*(x - centerX))/(a*a))) + centerY);
			if(x > 0 && x < width && y > 0 && y < height){
				pixels[x + y * width] = color;
			}
			y = (int) (float)(-Math.sqrt((float)b*b * (1f - (float)((x - centerX)*(x - centerX))/(a*a))) + centerY);
			if(x > 0 && x < width && y > 0 && y < height){
				pixels[x + y * width] = color;
			}
		}
		for(int y = centerY - b; y < centerY + b; y++){
			int x = (int) ((float)(Math.sqrt((float)a*a * (1f - (float)(y - centerY)*(y - centerY)/(b*b)))) + centerX);
			if(x > 0 && x < width && y > 0 && y < height){
				pixels[x + y * width] = color;
			}
			x = (int) ((float)(-Math.sqrt((float)a*a * (1f - (float)(y - centerY)*(y - centerY)/(b*b)))) + centerX);
			if(x > 0 && x < width && y > 0 && y < height){
				pixels[x + y * width] = color;
			}
		}
	}
	
	public float lineY(float x, float x1, float y1, float x2, float y2){
		if(x1 != x2){
			return ((float)(((float)(y1 - y2) / (x1 - x2)) * (x - x1) + y1)); 
		} else {
			return x1;
		}
	}
	
	public float lineX(float y, float x1, float y1, float x2, float y2){
		if(x1 != x2){
			return (int) ((float)((y - y1) / ((float)(y1 - y2) / (x1 - x2)))) + x1; 
		} else {
			return x1;
		}
	}
	
	public float distance(float x1, float y1, float x2, float y2){
		return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public int grayScale(float dp) {
		float lem = (dp + 1.0f) * 10.0f;
		Color c = new Color(0);
		for(int i = 0; i < 5; i++) {
			c.brighter();
		}
		if(lem < 5) {
			for(int i = 0; i < lem; i++) {
				c.darker();
			}
		}
		if(lem > 5) {
			for(int i = 0; i < lem; i++) {
				c.brighter();
			}
		}
		return c.getRGB();
	}
	
	public void addImage(BufferedImage a, int offsetX, int offSetY){
		int[] image = getPixelArray(a);
		for(int x = 0; x < a.getWidth(); x++){
			for(int y = 0; y < a.getHeight(); y++){
				pixels[(x + offsetX) + (y + offSetY) * width] = image[x + y * a.getWidth()];
			}
		}
	}
	
	public static int[] getPixelArray(BufferedImage a){
		int[] imagePixels = new int[a.getHeight() * a.getWidth()];
		for(int r = 0; r < a.getWidth(); r++){
			for(int c = 0;c < a.getHeight(); c++){
				imagePixels[c + r * a.getWidth()] = a.getRGB(c, r);
			}
		}
		return imagePixels;
	}
	
	public static Color getINRGBTORGB(int intRGB) {
		int r = (intRGB >> 16) &  0xFF;
		int g = (intRGB >> 8) & 0xFF;
		int b = (intRGB >> 0) & 0xFF;
		return new Color(r,g,b);
	}
	
	
	public int getRandom(int a, int b){
		return (int) ((b-a + 1) * Math.random() + a);
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 16777215;
		}
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
}
