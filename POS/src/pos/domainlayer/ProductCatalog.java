package pos.domainlayer;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog { // 다수의 descriptions들을 유지하는 클래스
	
	// import 구문을 통해 Map과 HashMap 클래스 포함
	// 여기서 HashMap으로 선언하지 않은 이유 -> 확장성, 유연성 확보를 위함
	// 해쉬맵을 유지하기 위해 해쉬맵으로만 선언하면, 해쉬맵과 그의 자손만 다룰 수 있음
	// 자료구조의 형태가 바뀔 경우를 고려해 부모 타입으로 선언한 것
	private Map<String, ProductDescription> descriptions = new HashMap<String, ProductDescription>(); // ItemID -> String 형태로 받음
	
	public ProductCatalog(){
		//견본데이터
		ItemID id1 = new ItemID(100); // 좀더 일반화된 형식으로 저자아기 위해 ItemID도 클래스 형태로 작성
		Money price1 = new Money(1000);
		
		ItemID id2 = new ItemID(200);
		Money price2 = new Money(2000);
		
		ProductDescription desc;
		
		desc =  new ProductDescription( id1, price1, "펩시");
		descriptions.put(id1.toString(), desc);
		
		desc =  new ProductDescription( id2, price2, "product 2");
		descriptions.put(id2.toString(), desc);
	}
	
	public ProductDescription getProductDescription(ItemID id){
		
		return descriptions.get(id.toString());
		}

}
