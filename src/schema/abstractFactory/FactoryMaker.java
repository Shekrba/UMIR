package schema.abstractFactory;

public class FactoryMaker {

	private static AbstractFactory factory=null;
	
	public static AbstractFactory getFactory(String choice){
		if(choice.equals("JSON")){
			factory=new JSONFactory();
		}else if(choice.equals("DB")){
			factory=new DBFactory();
		}
		return factory;
	}
	
}
