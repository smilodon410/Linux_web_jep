package gg.bit.utils.matchData.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClientException;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import gg.bit.utils.matchData.vo.ChampionVo;
import gg.bit.utils.matchData.vo.ParticipantsVo;

public class ChampionDaoMongo implements ChampionDao{
	
	private String ip = "192.168.56.100";
//	private String port = "27017";
	private String database = "lol";
	private String collection = "champion";
	
	
	private MongoClient connection() {
		MongoClient mongoClient = 		
				MongoClients.create(
						MongoClientSettings.builder()
						.applyToClusterSettings(builder ->
						builder.hosts(Arrays.asList(new ServerAddress(ip)))).build());
		
		return mongoClient;
	}
	
	
	private MongoCollection<Document> getCollection(MongoClient mongoClient) 
		throws MongoClientException {
		
		MongoDatabase database = mongoClient.getDatabase(this.database);
		MongoCollection<Document> collection = database.getCollection(this.collection); 
				
		return collection;
	}
	
	public List<ChampionVo> getList() {
		// vo 객체 담을 list 생성
		List<ChampionVo> list = new ArrayList<>();
		
		// 몽고 디비 접속 클라이언트
		MongoClient mongoClient = null;
		// 클라이언트로 접속 후 collection 가져오기
		MongoCollection<Document> coll = null;
		
		try {
			mongoClient = connection();
			coll = getCollection(mongoClient);
			
			// 컬렉션에서 find 한 결과
			FindIterable<Document> it = coll.find();
			
			for (Document doc: it) {
				// vo 객체 생성
									
				ChampionVo vo = new ChampionVo();
				
//				Integer key = doc.getInteger("key");
//				String name = doc.getString("name");
//				System.out.println(key + " " +name);
				
				vo.setKey(doc.getInteger("key"));
				vo.setName(doc.getString("name"));

				
				list.add(vo);
				

			}
		} catch (MongoClientException e) {
			e.printStackTrace();
		} finally {
			try {
				mongoClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	

}
