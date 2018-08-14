
public class Polyline {
	public double[] PolylineX;
	public double[] PolylineY;
	private int currentIndex = 0;
	public final int size;
	public final boolean useOffset;
	
	
	
	public Polyline(int size, boolean useOffset) {
		PolylineX = new double[size];
		PolylineY = new double[size];
		this.size = size;
		this.useOffset = useOffset;
	}
	
	public void addPoint(Point p, int index) {
		PolylineX[index] = p.getX();
		PolylineY[index] = p.getY();
	}
	
	public void addPoint(Point p) {
		PolylineX[currentIndex] = p.getX();
		PolylineY[currentIndex] = p.getY();
		currentIndex += 1;
	}
	
	public Point getPoint(int index ) {
		return new Point(PolylineX[index], PolylineY[index]);
	}
	
	public int getSize() {
		return size;
	}
	
	
	public void printPoints() {
		
		for(int i = 0; i < size; i++) {
			System.out.println("[" + PolylineX[i] + "," + PolylineY[i] + "]");
		}
		System.out.println("SIZE: " + size + "----------- FINISH -------------");
	}
	
	public double[] getPointsX() {		
		return PolylineX;
	}
	
	public double[] getPointsY() {
		return PolylineY;
	}
	
	public int[] getPixelPointsX() {	
		int[] pixelpoints = new int[PolylineX.length];
		for(int i = 0; i < PolylineX.length; i++) {
			pixelpoints[i] = (int) Math.round(PolylineX[i]);
		}
		return pixelpoints;
	}
	
	public int[] getPixelPointsY() {	
		int[] pixelpoints = new int[PolylineY.length];
		for(int i = 0; i < PolylineY.length; i++) {
			pixelpoints[i] = (int) Math.round(PolylineY[i]);
		}
		return pixelpoints;
	}
	
}
