package pos.domainlayer;

import java.util.ArrayList;

public class Store {
	private ProductCatalog catalog;
	private Register register;
	private ArrayList<Sale> CompleteSale;
	
	public Store() {
		catalog = new ProductCatalog();
		register = new Register(catalog);
		CompleteSale = new ArrayList<Sale>();
	}
	
	// *권소영: ucanaccess database를 사용하기 위한 생성자
	public Store(String dbFileName) {
		catalog = new ProductCatalog(dbFileName);
		register = new Register(catalog);
		CompleteSale = new ArrayList<Sale>();
	}
	
	public Register getRegister() { return register; }
}
