package pos.domainlayer;

public class Money {
	int amount;

	public Money() {
		//this.amount = 0;
		this(0); // 바람직
	}
	
	public Money(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
	
	public Money times(int quantity) {
		return new Money(quantity * amount);
	}

	// 돈의 가치를 바꿀 일이 없으므로 setter는 설정하지 않음
	/*public void setAmount(int amount) {
		this.amount = amount;
	}*/
	
	// 원래는 일반화하여 적용하는 게 좋지만 지금은 부족한 코드 구현하느라 어디에는 반환값이 있고 없고가 됨...
	public void add(Money m) {
		amount += m.getAmount();
	}
	
	public Money minus(Money m) {
		return new Money(amount - m.getAmount());
	}
	
	@Override
	public String toString() {
		return String.valueOf(amount);
	} 
	
}
