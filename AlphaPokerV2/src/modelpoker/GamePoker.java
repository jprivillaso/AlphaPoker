package modelpoker;


public class GamePoker {

	private TablePoker tablePoker;
	private Dealer dealer;
	
	
	public GamePoker() {
		tablePoker = new TablePoker();
		dealer =  new Dealer();
	}
	
	
	//round
	public void checkStartRound(){
		if(tablePoker.getTotalPlayer() >= 2 && !tablePoker.isRound()){
			System.err.println("se comenozo ronda por que hay mas de 1 usuario");
			tablePoker.initRound();
		}else{
			System.err.println("idle");
			//IDLE
		}
	}
	
	//getters
	public Dealer getDealer() {
		return dealer;
	}
	
	public TablePoker getTablePoker() {
		return tablePoker;
	}
	
}
