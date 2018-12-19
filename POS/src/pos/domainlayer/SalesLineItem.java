package pos.domainlayer;

public class SalesLineItem {

	private int quantity;
	private ProductDescription description;
	
	public SalesLineItem(ProductDescription desc, int quantity)
	{
		this.description = desc;
		this.quantity= quantity;
	}
	
	public Money getSubtotal()
	{
		// NullPointerException
		return description.getPrice().times(quantity);
	}
}
