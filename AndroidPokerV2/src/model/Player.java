package model;

public class Player {
	
	private String nickName;
	private int chips;
	private CharSequence enterChips;
	private String ip;
	
	public Player() {
		nickName = "";
		chips = 0;
		enterChips = "";
	}
	
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public void setEnterChips(CharSequence enterChips) {
		this.enterChips = enterChips;
	}
	
	public int getChips() {
		return chips;
	}
	
	public String getNickName() {
		return nickName;
	}

	public CharSequence getEnterChips() {
		return enterChips;
	}

	public void setIp(String ip) {
		this.ip = ip;
		
	}
	
	public String getIp() {
		return ip;
	}
	
}
