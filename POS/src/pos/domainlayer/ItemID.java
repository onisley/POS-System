package pos.domainlayer;

public class ItemID {
	String id;

	public ItemID(String id) {
		this.id = id;
	}
	
	public ItemID(int id) { // ���ڷ� id���� �Է¹��� ��츦 ����� ������ �ۼ�
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
