
public class RegularPolygon {
	
	private int n = 3;
	private double side = 1;
	private double x = 0;
	private double y = 0;
	
	public RegularPolygon(){
		
	}
	public RegularPolygon(int n, double side){
		this.n=n;
		this.side=side;
	}
	public RegularPolygon(int n, double side, double x, double y){
		this.n=n;
		this.side=side;
		this.x=x;
		this.y=y;
	}
	public int getn(){
		return n;
	}
	public void setn(int n){
		this.n=n;
	}
	public double getside(){
		return side;
	}
	public void setSide(double side){
		this.side=side;
	}
	public double getx(){
		return x;
	}
	public void setx(double x){
		this.x=x;
	}
	public double gety(){
		return y;
	}
	public void sety (double y){
		this.y=y;
	}
	public double getPerimeter(){
		return n * side;
	}
	public double getArea(){
		return (n* (side*side))/(4 * Math.tan(Math.PI/n));
	}

}
