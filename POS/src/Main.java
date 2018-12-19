import pos.domainlayer.*;

public class Main {
	
	public static void main(String[] args) {
		
		Store store = new Store();
		Register register = store.getRegister();
		
		Sale sale = register.makeNewSale();
		
		register.enterItem(new ItemID(100), 3);
		register.enterItem(new ItemID(200), 2);
		
		register.endSale();
		
		// 레지스터에게 합계를 물어볼 경우 응집력이 떨어짐
		// Sale객체에 직접 이 판매에 대한 합계를 물어보리고 -> 문제는 Sale객체가 가시성을 갖고 있지 않다는 것
		// 가시성을 갖도록 makeNewSale()을 수정
		System.out.println("Total = " + sale.getTotal().toString());
		
		register.makePayment(new Money(10000));
		
		System.out.println("Balance = " + sale.getBalance().toString());
	}

}
