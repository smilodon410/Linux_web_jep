package gg.bit.utils.matchData.vo;

public class ParticipantsVo {
	private Integer teamId;
	private Integer championId;
	private String champ_name;
	private Integer kill;
	private Integer death;
	private Integer assist;
	private Integer deal;
	private String win;
	private String summonerName;
	
	public Integer getTeamId() {
		return teamId;
	}
	public String getTeamIdString() {
		if (teamId == 100) {
			return "Blue";
		} else {
			return "Red";
		}
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getChampionId() {
		return championId;
	}
	
	public String getChamp_name() {
		return champ_name;
	}
	
	public void setChamp_name(String champ_name) {
		this.champ_name = champ_name;
	}
	
	public void setChampionId(Integer championId) {
		this.championId = championId;
	}
	public Integer getKill() {
		return kill;
	}
	public void setKill(Integer kill) {
		this.kill = kill;
	}
	public Integer getDeath() {
		return death;
	}
	public void setDeath(Integer death) {
		this.death = death;
	}
	public Integer getAssist() {
		return assist;
	}
	public void setAssist(Integer assist) {
		this.assist = assist;
	}
	public Integer getDeal() {
		return deal;
	}
	public void setDeal(Integer deal) {
		this.deal = deal;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getSummonerName() {
		return summonerName;
	}
	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	
	
}