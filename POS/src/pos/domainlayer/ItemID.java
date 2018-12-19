package pos.domainlayer;

public class ItemID {
	String id;

	public ItemID(String id) {
		this.id = id;
	}
	
	public ItemID(int id) { // 숫자로 id값을 입력받을 경우를 고려한 생성자 작성
		this.id = String.valueOf(id);
	}
	
	public String getID() {
		return id;
	}
	
	@Override
	public String toString() {
		return id;
	}
}
