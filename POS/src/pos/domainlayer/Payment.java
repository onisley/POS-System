package pos.domainlayer;

// ������ ��Ű����� ������ �� ���� -> ��Ű���� ���� ���� �и� ����

public class Payment {
	private Money amount; // ���� ���� ȭ�� ���Ǳ� ���ؼ��� Money�� �ܼ� int������ ǥ���ϴ� ���� �ٶ������� ���� -> Ŭ������ ǥ��

	public Payment(Money cachTendered) {
		amount = cachTendered;
	}

	public Money getAmount() {
		return amount;
	}
}
