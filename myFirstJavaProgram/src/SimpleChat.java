import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Basic test util class used to test the implementation of the a simple jgroups
 * chat function over a network.This will most likely not be the code that will
 * be submitted as part of the work submission.
 * 
 * @author subin.babu
 * 
 */
public class SimpleChat {

	static JFrame myFrame;

	public static void main(String[] args) throws IOException {
		createUI();
	}

	/**
	 * Method used to create the dialog box frame of the chat application.
	 * Perform all the UI creation operation here.
	 * 
	 * @author subin.babu
	 */
	private static void createUI() {
		/**
		 * create new frame as the background
		 */
		myFrame = new JFrame();

		/**
		 * set the code to exit on window close.
		 */
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
}
