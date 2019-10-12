package Logic;

class Dice{
	
	Player p;

	int move;
	
	Dice(Player p1, int num){
		this.move = num;

		this.p=p1;
	}
	
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public int getDieNum() {
		return move;
	}
	public void setDieNum(int dieNum) {
		this.move = dieNum;
	}

}

class RollOne extends Dice{

	RollOne(Player p1, int num) {
		super(p1, num);
		// TODO Auto-generated constructor stub
	}
	
	String backToIt() {
		
		if(getDieNum()== 1){
			this.p.move(-3);
			return "3 tiles backward";
		}

		else if(getDieNum() == 2){
			this.p.setPosition(21);
			return "Go to jail";
		}

		else if (getDieNum() ==3){
			this.p.move(2);
			return "2 tile forward";
			
		}

		else if (getDieNum()== 4){
			this.p.setPosition(0);
			return "Move to GO";
			
		}

		else{
			this.p.addMoney(200);
			return "Bank gave you 200";
			
		}
	}
	
}


