package it.unipv.ingsw.exceptions;

import javax.swing.JOptionPane;

public class PopUpManager {
	public static void showPopup(String message) {
		JOptionPane.showMessageDialog(null, message, "Sistema", JOptionPane.INFORMATION_MESSAGE);
	}

	public static int showChoosing(Object[] options) {
		return JOptionPane.showOptionDialog(null, "Scegli un'opzione", "Sistema", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);
	}
}
