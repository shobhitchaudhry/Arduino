package PredictiveWeb;

public class PlayerProfile {
	// public static final boolean ArduinoConnectedState = false;

	public String firstName;
	public String lastName;
	
	public int playerNumber;
	public Boolean arduinoConnectedState = false;

	public String level1score;
	public String level2score;
	public String level3score;
	public String level4score;
	public String level5score;
	
	public String level1prediction;
	public String level2prediction;
	public String level3prediction;
	public String level4prediction;
	public String level5prediction;
	
	public String age;
	public String coffee;
	public String drive;
	public String gender;
	
	
	
	public PlayerProfile(String firstName,String age ,Boolean state) {
		this.firstName = firstName;
		this.lastName = "";
		this.arduinoConnectedState = state;
		this.level1score = "0";
		this.level2score = "0";
		this.level3score = "0";
		this.level4score = "0";
		this.level5score = "0";
		this.level1prediction="0";
		this.level2prediction="0";
		this.level3prediction="0";
		this.level4prediction="0";
		this.level5prediction="0";
	}
}
