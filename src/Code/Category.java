package Code;

public enum Category {
	
	IN("0", "출석"), OUT("1", "불참"), SICK("2", "병가");
	
	private String name;
	private String value;
	
	private Category(String name, String value) {
		this.name = name;
		this.value = value;
		
	}

	public String getName() {
		return name;
	}
	public String getValue()	{
		return value;
	}


	
}
