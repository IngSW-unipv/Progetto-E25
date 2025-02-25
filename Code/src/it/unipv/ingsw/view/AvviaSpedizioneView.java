package it.unipv.ingsw.view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AvviaSpedizioneView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel formPanel;
	private JTextField mailDestField;
	private JTextField lockerDestinazioneField;
	private JTextField coperturaField;
	private JTextField pesoPaccoField;
	private JTextField lockerInizioField;
	private JComboBox<String> dimPaccoField;
	
	private JButton confirmButton;
	
	public AvviaSpedizioneView() {

	    setTitle("Avvio Spedizione");
	    setSize(1000, 600);
	    setLayout(new BorderLayout());

	    JPanel textAreaPanel = new JPanel(new BorderLayout());
	    JTextArea textArea = new JTextArea("  Indicazioni spedizione:\n  Dimensioni del pacco: S: dimensione max 35 x 25 x 12 cm.\r"
	    		+ ", M: dimensione max 35 x 25 x 36 cm.\r"
	    		+ ", L: dimensione max 35 x 25 x 48 cm.\r"
	    		+ ", XL: dimensione max 35 x 25 x 66 cm.\r\n"
	    		+ "  Peso del pacco max 10 kg.\r\n"
	    		+ "");
	    textArea.setEditable(false);  // Impedisce che l'utente modifichi il testo
	    textArea.setWrapStyleWord(true);  
	    textArea.setLineWrap(true); 
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
	    scrollPane.setPreferredSize(new Dimension(400, 80));  
	    textAreaPanel.add(scrollPane, BorderLayout.CENTER);

	    add(textAreaPanel, BorderLayout.NORTH); 
	    formPanel = new JPanel(new GridLayout(9, 2, 10, 10));  
	    add(formPanel, BorderLayout.CENTER);  

	    formPanel.add(new JLabel("  Email del destinatario:"));
	    mailDestField = new JTextField();
	    formPanel.add(mailDestField);


	    formPanel.add(new JLabel("  Locker inizio:"));
	    lockerInizioField = new JTextField();
	    formPanel.add(lockerInizioField);

	    
	    formPanel.add(new JLabel("  Destinazione(locker):"));
	    lockerDestinazioneField = new JTextField();
	    formPanel.add(lockerDestinazioneField);
	    
	    formPanel.add(new JLabel("  Dimensione del pacco:"));
	    String[] sizeOptions = { "S", "M", "L", "XL" };
        dimPaccoField = new JComboBox<>(sizeOptions);;
        formPanel.add(dimPaccoField);  
	    
	    formPanel.add(new JLabel(" Peso del pacco:"));
	    pesoPaccoField = new JTextField();
	    formPanel.add(pesoPaccoField);
	    
	    formPanel.add(new JLabel("  Eventuale copertura assicurativa:"));
	    coperturaField = new JTextField();
	    formPanel.add(coperturaField);
	    
	    confirmButton = new JButton("Conferma");
	    confirmButton.setBackground(Color.YELLOW);
	    formPanel.add(confirmButton);

	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    setVisible(true);
	}





	public JTextField getMailDestField() {
		return mailDestField;
	}


	public void setMailDestField(JTextField mailDestField) {
		this.mailDestField = mailDestField;
	}


	public JTextField getLockerDestinazioneField() {
		return lockerDestinazioneField;
	}


	public void setLockerDestinazioneField(JTextField lockerDestinazioneField) {
		this.lockerDestinazioneField = lockerDestinazioneField;
	}


	public JTextField getCoperturaField() {
		return coperturaField;
	}


	public void setCoperturaField(JTextField coperturaField) {
		this.coperturaField = coperturaField;
	}


	public JComboBox getDimPaccoField() {
		return dimPaccoField;
	}


	public void setDimPaccoField(JComboBox dimPaccoField) {
		this.dimPaccoField = dimPaccoField;
	}


	public JButton getConfirmButton() {
		return confirmButton;
	}
	
	
	public JTextField getLockerInizioField() {
		return lockerInizioField;
	}


	public void setLockerInizioField(JTextField lockerInizioField) {
		this.lockerInizioField = lockerInizioField;
	}
	
	public JTextField getPesoPaccoField() {
		return pesoPaccoField;
	}


	public void setPesoPaccoField(JTextField pesoPaccoField) {
		this.pesoPaccoField = pesoPaccoField;
	}


	public void setConfirmButton(JButton confirmButton) {
		this.confirmButton = confirmButton;
	}

	public static void main(String[] args) {
	    // Imposta l'aspetto dell'interfaccia grafica in modo più fluido (threading Swing)
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            // Crea una nuova istanza di UtenteView
	        	AvviaSpedizioneView utenteView = new AvviaSpedizioneView();
	            // Puoi usare il metodo setVisible(true) per mostrare la finestra
	        	utenteView.setVisible(true);
	        }
	    });
	}

}
