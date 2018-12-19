import pos.domainlayer.*;

public class Main {
	
	public static void main(String[] args) {
		
		Store store = new Store();
		Register register = store.getRegister();
		
		Sale sale = register.makeNewSale();
		
		register.enterItem(new ItemID(100), 3);
		register.enterItem(new ItemID(200), 2);
		
		register.endSale();
		
		// �������Ϳ��� �հ踦 ��� ��� �������� ������
		// Sale��ü�� ���� �� �Ǹſ� ���� �հ踦 ������� -> ������ Sale��ü�� ���ü��� ���� ���� �ʴٴ� ��
		// ���ü��� ������ makeNewSale()�� ����
		System.out.println("Total = " + sale.getTotal().toString());
		
		register.makePayment(new Money(10000));
		
		System.out.println("Balance = " + sale.getBalance().toString());
	}

}
