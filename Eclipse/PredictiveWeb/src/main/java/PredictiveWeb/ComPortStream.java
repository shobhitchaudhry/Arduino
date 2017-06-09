package PredictiveWeb;

import java.io.InputStream;
import java.io.OutputStream;

public class ComPortStream {
	// public static final boolean ArduinoConnectedState = false;
	public InputStream input;
	public OutputStream output;

	public String playerFirstName;
	public String playerLastName;

	public Boolean arduinoConnectedState = false;

	public String level1score;
	public String level2score;
	public String level3score;

	public ComPortStream(InputStream input, OutputStream output, String firstName, String lastName, Boolean state) {
		this.input = input;
		this.output = output;
		this.playerFirstName = firstName;
		this.playerLastName = lastName;
		this.arduinoConnectedState = state;
		this.level1score = "0";
		this.level2score = "0";
		this.level3score = "0";

	}

}
