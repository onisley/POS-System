package pos.domainlayer;

public class Money {
	int amount;

	public Money() {
		//this.amount = 0;
		this(0); // �ٶ���
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

	// ���� ��ġ�� �ٲ� ���� �����Ƿ� setter�� �������� ����
	/*public void setAmount(int amount) {
		this.amount = amount;
	}*/
	
	// ������ �Ϲ�ȭ�Ͽ� �����ϴ� �� ������ ������ ������ �ڵ� �����ϴ��� ��𿡴� ��ȯ���� �ְ� ���� ��...
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
