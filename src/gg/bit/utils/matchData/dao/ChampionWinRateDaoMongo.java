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

import gg.bit.utils.matchData.vo.ChampionWinOrLoseVo;

public class ChampionWinRateDaoMongo {
	private String ip = "192.168.56.100";
//	private String port = "27017";
	private String database = "lol";
	
	private MongoClient connection() {
		MongoClient mongoClient = 		
				MongoClients.create(
						MongoClientSettings.builder()
						.applyToClusterSettings
						(builder ->
						builder.hosts(Arrays.asList(new ServerAddress(ip)))).build());
		
		return mongoClient;
	}
	
	private MongoCollection<Document> getCollection(MongoClient mongoClient) 
		throws MongoClientException {
		
		MongoDatabase database = mongoClient.getDatabase(this.database);
		MongoCollection<Document> collection = database.getCollection("match_json"); 
				
		return collection;
	}
	
	public List<ChampionWinOrLoseVo> getList() {
		// vo 객체 담을 list 생성
		List<ChampionWinOrLoseVo> list = new ArrayList<>();
		
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

				List<Document> participantsList = doc.getList("participants", Document.class);
				
				for (Document doc1 : participantsList) {
					
					ChampionWinOrLoseVo vo = new ChampionWinOrLoseVo();
					
					Integer championId = doc1.getInteger("championId");
					vo.setChampionId(championId);
					
					String winOrLose = (String) doc1.get("stats", Document.class).get("win");
					vo.setWinOrLose(winOrLose);
					
					// 리스트에 등록
					list.add(vo);
				}
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
