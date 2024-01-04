package Code;

public class CategoryUtil {
	public String getDispValue(String name) {
		switch(name)  {
			case "0" :
				return Category.IN.getValue();
		
			case "1" :
				return Category.OUT.getValue();
			case "2" :
				return Category.SICK.getValue();
			default : 
				return "출석여부불명";
		}
	}
}
