package pos.domainlayer;

public class Register {
	
	private ProductCatalog catalog;
	private Sale currentSale;
	
	public Register(ProductCatalog catalog){
		this.catalog = catalog;
	}
	
	public void endSale()
	{
		currentSale.becomeComplete();
	}
	
	public void enterItem(ItemID id, int quantity)
	{
		ProductDescription desc = catalog.getProductDescription(id);
		currentSale.makeLineItem(desc, quantity);
	}
	
	public Sale makeNewSale() // void였으니 Sale 객체에 대한 가시성을 갖기 위해 반환값을 가지도록 변환
	{
		currentSale = new Sale();
		
		return currentSale;
	}
	
	public void makePayment(Money cashTendered)
	{
		currentSale.makePayment(cashTendered);
	}
}
