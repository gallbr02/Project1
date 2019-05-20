import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Browser extends JPanel{

	protected JEditorPane pane;
	protected JTextField address;
	protected JButton go;
	
	
	public Browser(){
		setLayout(new BorderLayout());
		
		//create UI objects
		pane = new JEditorPane();
		address = new JTextField(30);
		go = new JButton("Go");
		
		//layout
		JPanel panel = new JPanel();
		panel.add(address);
		panel.add(go);
		
		add(BorderLayout.NORTH, panel);
		add(BorderLayout.CENTER, new JScrollPane(pane));
		
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					URL url = new URL(address.getText());
					pane.setPage(url);
					//url.openStream()
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Browser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.add(new Browser());
		frame.setVisible(true);
	}

}