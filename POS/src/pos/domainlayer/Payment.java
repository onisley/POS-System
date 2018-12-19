package pos.domainlayer;

// 계층을 패키지라고 생각할 수 있음 -> 패키지를 통한 계층 분리 구현

public class Payment {
	private Money amount; // 차후 여러 화폐에 통용되기 위해서는 Money를 단순 int형으로 표현하는 것은 바람직하지 않음 -> 클래스로 표현

	public Payment(Money cachTendered) {
		amount = cachTendered;
	}

	public Money getAmount() {
		return amount;
	}
}
