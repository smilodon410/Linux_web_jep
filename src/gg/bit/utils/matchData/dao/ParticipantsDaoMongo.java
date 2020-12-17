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

import gg.bit.utils.matchData.vo.ParticipantsVo;
import gg.bit.utils.matchData.vo.WinnerVo;

public class ParticipantsDaoMongo implements ParticipantsDao {
	
	private String ip = "192.168.56.100";
//	private String port = "27017";
	private String database = "lol";
	private String collection = "match_json";
	private String s_c = "champion";
	int a = 0;
	int teamId = 0;
	String champ_name = null;

	
	
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
	
	private MongoCollection<Document> s_getCollection(MongoClient mongoClient) 
			throws MongoClientException {
			
			MongoDatabase database = mongoClient.getDatabase(this.database);
			MongoCollection<Document> collection = database.getCollection(this.s_c); 
					
			return collection;
		}
	
	public List<ParticipantsVo> getList() {
		// vo 객체 담을 list 생성
		List<ParticipantsVo> list = new ArrayList<>();
		
		// 몽고 디비 접속 클라이언트
		MongoClient mongoClient = null;
		// 클라이언트로 접속 후 collection 가져오기
		MongoCollection<Document> coll = null;
		MongoCollection<Document> s_coll = null;

		try {
			mongoClient = connection();
			coll = getCollection(mongoClient);
			s_coll = s_getCollection(mongoClient);

			// 컬렉션에서 find 한 결과
			FindIterable<Document> it = coll.find();
			FindIterable<Document> s_it = s_coll.find();

			for (Document doc: it) {
				for (int i = 0; i < 10; i++) {

					// vo 객체 생성
					ParticipantsVo vo = new ParticipantsVo();

					vo.setSummonerName(doc.getList("participantIdentities", Document.class).get(i).get("player", Document.class).getString("summonerName"));

					Integer teamId = doc.getList("participants",Document.class).get(i).getInteger("teamId");

					vo.setTeamId(teamId);

					Integer Champ_id = doc.getList("participants",Document.class).get(i).getInteger("championId");
					
					for (Document s_doc: s_it) {
						if (s_doc.getInteger("key").equals(Champ_id) ) {
							champ_name = s_doc.getString("name");
							vo.setChamp_name(champ_name);
						}
					}

//					vo.setChampionId(doc.getList("participants",Document.class).get(i).getInteger("championId"));
//					vo.setChamp_name(champ_name);


					vo.setKill(doc.getList("participants",Document.class).get(i).get("stats", Document.class).getInteger("kills"));


					vo.setDeath(doc.getList("participants",Document.class).get(i).get("stats", Document.class).getInteger("deaths"));


					vo.setAssist(doc.getList("participants",Document.class).get(i).get("stats", Document.class).getInteger("assists"));


					vo.setDeal(doc.getList("participants",Document.class).get(i).get("stats", Document.class).getInteger("totalDamageDealtToChampions"));

					vo.setWin(doc.getList("participants",Document.class).get(i).get("stats", Document.class).getString("win"));

					list.add(vo);
					
					


				}
				a++;
				if(a==1) break;				

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
