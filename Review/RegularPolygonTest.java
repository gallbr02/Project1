
public class RegularPolygonTest {

	public static void main(String[] args) {
		RegularPolygon regularpolygon1 = new RegularPolygon();
		RegularPolygon regularpolygon2 = new RegularPolygon(6,4);
		RegularPolygon regularpolygon3 = new RegularPolygon(10,4,5.6,7.8);
		
		System.out.println(regularpolygon1.getPerimeter());
		System.out.println(regularpolygon1.getArea());
		
		System.out.println(regularpolygon2.getPerimeter());
		System.out.println(regularpolygon2.getArea());
		
		System.out.println(regularpolygon3.getPerimeter());
		System.out.println(regularpolygon3.getArea());
	}

}
