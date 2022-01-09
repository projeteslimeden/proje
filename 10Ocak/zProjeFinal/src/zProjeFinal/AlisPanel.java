package zProjeFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.InsetsUIResource;

public class AlisPanel implements ActionListener{
	
	private JTabbedPane alisSekme;
	private JPanel alisPanel;
	private JPanel barkodSecimPanel;
	private JTextField barkodOku; private JButton barkodSorgulaBut;
	
	public JPanel panel() {
		Color alisColor = new Color(0xde7777); //þeffaf yeþil
		
		alisPanel = new JPanel();
		alisPanel.setBackground(alisColor);
		alisPanel.setBounds(0, 0, 1030, 680);
		alisPanel.setLayout(null);
		
		barkodSecimPanel = new JPanel();
		barkodSecimPanel.setBackground(Color.lightGray);
		barkodSecimPanel.setLayout(null);
		barkodOku = new JTextField();
		barkodOku.setBounds(10, 10, 150, 30);
		barkodOku.setBorder(null);
		barkodOku.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		
		barkodSorgulaBut = new JButton("Barkod Sorgula");
		barkodSorgulaBut.setBounds(10, 42, 150, 18);
		barkodSorgulaBut.setFocusable(false);
		barkodSorgulaBut.setFont(new Font(null, Font.BOLD, 12));
		barkodSorgulaBut.setBorder(null);
		barkodSorgulaBut.addActionListener(this);
		
		UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(-2, -1, -1, -1));
		UIManager.put("TabbedPane.selected", alisColor);
		UIManager.put("TabbedPane.focus", alisColor);
		alisSekme = new JTabbedPane();
		alisSekme.add("Barkoddan Seçim", barkodSecimPanel);
		alisSekme.setBounds(0, 0, 1030, 340);
		alisSekme.setBorder(null);
		alisSekme.setBackground(new Color(0x858683));
		
		
		
		
		alisPanel.add(alisSekme);
		
		return alisPanel;
	}
	
	public void panelSetVisible(boolean a) { //interface olabilir
		alisPanel.setVisible(a);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
