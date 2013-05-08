package comunication;


public class Session {
	
	private User [] hashTable;
	
	public Session() {
		hashTable =  new User[8];
	}
	
	
	//add user
	public void addUser(User user){
		int pos = 0;
		while(hashTable[pos] != null && pos < 8){
			pos ++;
		}
		hashTable[pos] = user;
	}
	
	public void removeUser(User user){
		int pos = 0;
		while(hashTable[pos] != user && pos < 8 ){
			pos ++;
		}
		hashTable[pos] = null;
	}
	
	
	public boolean checkUser(String nickName){
		for (int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] != null){
				if( ((hashTable[i]).getNickName()).equals(nickName) ){
					return true;
				}
			}
		}
		return false;
	}
	
	public User getUser(String nickName){
		for (int i = 0; i < hashTable.length; i++) {
			if( ( ((User)hashTable[i]).getNickName() ).equals(nickName) ){
				return hashTable[i];
			}
		}
		return null;
	}
	
	public int getPositionUser(String nickName){
		int pos = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if( ( ((User)hashTable[i]).getNickName().equals(nickName) ) ){
				pos = i;
				break;
			}
		}
		return pos;
	}
	
	public int getTotalUsers(){
		int totalUsers = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] != null){
				totalUsers++;
			}
		}
		return totalUsers;
	}
	
}
