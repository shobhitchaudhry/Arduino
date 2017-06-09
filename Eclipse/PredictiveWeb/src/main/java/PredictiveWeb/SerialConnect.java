package PredictiveWeb;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SerialConnect {

	static Boolean arduinoAConnectedState = false;
	static String playerFirstNameB;

	static ArrayList<ComPortStream> list = new ArrayList<ComPortStream>();

	public static ArrayList<ComPortStream> main(ArrayList<PlayerProfile> args) throws Exception {

		getPorts();
		engageGameMode();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).playerFirstName);
		}
		Thread.sleep(10000);

		getScores();
		
		// System.out.print("The End");
		// System.exit(0);
		// output.close();
		
		startLevel4();
		Thread.sleep(10000);
		getScores();
		
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).output.close();;
		}
		
		return list;
	}

	public static void engageGameMode() throws Exception {

		System.out.println("Please, Pick up your controller !");
		// playerFirstNameA = "test";
		for (int i = 0; i < list.size(); i++) {
			list.get(i).output.write("test".getBytes());
			Thread.sleep(100);
		}
		return;
	}

	public static void startLevel4() throws Exception {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).output.write("start".getBytes());
			Thread.sleep(100);
		}
		return;
	}

	public static void getScores() throws Exception {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).output.write("sendscore".getBytes());
			String scores = readSerial(i);
			//long[] parsedScore = parseScores(scores, list.get(i));
			System.out.println(scores);
		}
		return;
	}

	public static long[] parseScores(String score, ComPortStream comPortStream) {
		long[] parsedScore = new long[3];
		if ((score.toLowerCase()).contains("score:")) {
			comPortStream.level1score = score.split("l1:")[1].split(";")[0];
			comPortStream.level2score = score.split("l2:")[1].split(";")[0];
			comPortStream.level3score = score.split("l3:")[1].split(";")[0];
			return parsedScore;
		} else {
			return null;
		}
	}

	public static void getPorts() {
		int controllerNumber = 0;
		CommPortIdentifier commPortIdentifier = null;
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		try {
			while (portEnum.hasMoreElements()) {
				CommPortIdentifier portIdentifier = portEnum.nextElement();
				System.out.println(portIdentifier.getName() + " found ");
				commPortIdentifier = portIdentifier;

				System.out.println(" found ");
				ComPortStream cps1;
				cps1 = connectArduinos(commPortIdentifier);
				if (cps1 != null && cps1.arduinoConnectedState) {
					cps1.playerFirstName = Integer.toString(controllerNumber + 1);
					list.add(cps1);
					System.out.println(cps1.arduinoConnectedState);
					controllerNumber++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return;
	}

	public static ComPortStream connectArduinos(CommPortIdentifier commId) {
		InputStream input;
		OutputStream output;
		SerialPort port;
		ComPortStream cps = null;
		try {

			port = (SerialPort) commId.open("serial talk", 4000);
			Thread.sleep(3000);
			input = port.getInputStream();
			output = port.getOutputStream();
			port.disableReceiveThreshold();
			port.enableReceiveTimeout(3000);
			port.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			System.out.println("Using");
			output.write("ack".getBytes());

			long startTime = System.currentTimeMillis();
			long elapsedTime = 0L;

			while (elapsedTime < 3 * 1000) {
				if (input.available() > 0) {
					BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(port.getInputStream()));
					String inputLine = inputBuffer.readLine();
					System.out.println(inputLine);
					arduinoAConnectedState = true;
					cps = new ComPortStream(input, output, Long.toString(elapsedTime), null, true);
					break;
				}
				elapsedTime = (new Date()).getTime() - startTime;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cps;
	}

	public static String readSerial(int number) throws Exception {
		String inputx = "";
		while (true) {
			while (list.get(number).input.available() > 0) {
				inputx = new BufferedReader(new InputStreamReader(list.get(number).input)).readLine();
				// System.out.println(inputx);
				return inputx;
			}
		}
	}
}