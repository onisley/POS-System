import pos.domainlayer.*;
import pos.presentationLayer.*;

public class MainGUI {
	public static void main(String[] args){
		if (args.length ==1)
	    {
			String dbFileName = args[0];
			
			Store store = new Store(dbFileName);
			Register register = store.getRegister();
			new ProcessSaleJFrame(register);
	    } else {
	    	System.out.println("Please run with dbFileName");
	    }
	}
}
