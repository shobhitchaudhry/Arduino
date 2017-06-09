package PredictiveWeb;
import java.util.StringTokenizer;

import com.kxen.CommonInterf.KxenContext;

public class KxContext implements KxenContext {
	
	/**
	 * Empty default constructor 
	 */
	public KxContext() {
	}

	/**
	 * @see com.kxen.KxModelJni.IKxenContext#
	 * 		userMessage(java.lang.String, java.lang.String, int)
	 */
	public void userMessage(String iSource, String iMessage, int iLevel) {
		String lType = null;
		String lFullMesg = null;
		if (iLevel == 0) {
			lType = "ERROR ";
		} else {
			lType = "WARNING ";
		}
		if (iLevel == 6) {
			// Special processing for progress bar - the message 
			// is "completed_nb total_nb text..."
			StringTokenizer	lTk = new StringTokenizer(iMessage);
			lTk.nextToken();		// Skip this one
			int	lCompleted = 0;
			int	lTotal = 0;
			try {
				lCompleted = Integer.parseInt(lTk.nextToken());
				lTotal = Integer.parseInt(lTk.nextToken());
			} catch (java.lang.Exception e) { ; }
			String lPhase = lTk.nextToken("");
			// here we should process it
			// something like: updateProgressBar(lTotal, lCompleted, lPhase);
			System.out.println("On phase '" + lPhase + "' -> [" + lCompleted 
				+ "/" + lTotal + "]");
		}
		if (iMessage.startsWith("KXEN_")) {
			lFullMesg = lType + "[" + iSource + "] : " + iMessage + "\n";
		} else {
			lFullMesg = iMessage + "\n";
		}
		if (iLevel == 0) {
			System.err.print(lFullMesg);
		} else {
			System.out.print(lFullMesg);
		}
	}

	/**
	 * @see com.kxen.CommonInterf.KxenContext#userConfirm(
	 * java.lang.String, java.lang.String)
	 */
	public boolean userConfirm(String iSource, String iPrompt) {
		return true;
	}

	/**
	 * @see com.kxen.CommonInterf.KxenContext#userAskOne(
	 * java.lang.String, java.lang.String, boolean)
	 */
	public String userAskOne(String iSource, String iPrompt, boolean iHidden) {
		return "";
	}

	/**
	 * @see com.kxen.CommonInterf.KxenContext#stopCallBack(java.lang.String)
	 */
	public boolean stopCallBack(String iSource) {
		return true;
	}

}
