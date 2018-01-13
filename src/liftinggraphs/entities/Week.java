package liftinggraphs.entities;

public class Week {

	private int weekNumber;
	private int volume;
	
	public Week(int weekNumber, int volume){
		this.weekNumber = weekNumber;
		this.volume = volume;
	}
	
	public int getWeekNumber(){
		return this.weekNumber;
	}
	
	public int getVolume(){
		return this.volume;
	}
}
