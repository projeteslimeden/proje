package zProjeFinal;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GirisFrame extends JFrame implements ActionListener, MouseListener, KeyListener {
	
	private JTextField idGirdi;
	private JPasswordField sifreGirdi;
	private JButton onay;
	
	public void GFrameRun() {
		DbBaglanti.kur(); //ilk giriþten database baðlantýsýný kurmak sadece tek sefer
		GFrame();
		this.setLocationRelativeTo(null); //pencereyi ortada baþlatma kodu
		this.setVisible(true);
	}
	
	
	private void GFrame() {
		JPanel girisPanel = new JPanel();
		girisPanel.setLayout(null);
		girisPanel.setBounds(10, 10, 235, 68); //sað-sol total 15 kýrpýyor üst bar 45
		girisPanel.setBackground(Color.lightGray);
		
		JLabel idTxt = new JLabel   ("Kullanýcý Adý:");
		idTxt.setBounds(5, 5, 80, 30);
		JLabel sifreTxt = new JLabel("Parola           :");
		sifreTxt.setHorizontalAlignment(JLabel.LEFT);
		sifreTxt.setBounds(5, 30, 80, 30);
		
		idGirdi = new JTextField(20);
		idGirdi.setBounds(90, 8, 140, 25);
		idGirdi.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		idGirdi.setBorder(null);
		idGirdi.addKeyListener(this);
		sifreGirdi = new JPasswordField(20);
		sifreGirdi.setBounds(90, 35, 110, 25);
		sifreGirdi.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		sifreGirdi.setBorder(null);
		sifreGirdi.addKeyListener(this);
		
		///////////////////////////////// ----DÝKKAT-----\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		idGirdi.setText("Admin"); //her giriþte tek tek þifre yazmak zorunda kalma
		sifreGirdi.setText("pass");
	
		
		ImageIcon sifreGoster = new ImageIcon("Images/showpassico222.png");
		JLabel sifreGosterLbl = new JLabel();
		sifreGosterLbl.setIcon(sifreGoster);
		sifreGosterLbl.setBounds(202, 35, 30, 25);
		sifreGosterLbl.addMouseListener(this);
	
		onay = new JButton("Giriþ Yap");
		onay.setBounds((155/2),88,100,30);
		onay.addActionListener(this);
		onay.setFocusable(false);
		onay.setBorder(BorderFactory.createLineBorder(null, 1));
		
		girisPanel.add(idTxt);
		girisPanel.add(idGirdi);
		girisPanel.add(sifreTxt);
		girisPanel.add(sifreGirdi);
		girisPanel.add(sifreGosterLbl);
		
		this.add(girisPanel);
		this.add(onay);
		
		ImageIcon icon = new ImageIcon("Images/zIconFinal.png");
		this.setIconImage(icon.getImage());
		this.setLayout(null);
		this.setTitle("Depom Sepette");
		this.setSize(270, 163);
		this.getContentPane().setBackground(Color.darkGray);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void girisReaksiyon () {
			String sifretext = new String(sifreGirdi.getPassword());
			Giris kullanici = new Giris(idGirdi.getText(), sifretext);
			
			if(kullanici.kontrol()==true) {
/*ANAPENCERE*/	new MainFrame().MFrameRun();        //MainFrame anapencere = new MainFrame();
/*  AÇILIÞ  */										//anapencere.MFrameRun();
				this.dispose();
			} else {
					String[] responses= {"Tekrar Dene","Çýkýþ Yap"};
					int answer = JOptionPane.showOptionDialog(
					null, //component
					"Kullanýcý adý ve parola eþleþmiyor.", //message 		
					"Depom Sepette", 
					JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.ERROR_MESSAGE, 
					null, responses, 0);
					if(answer==1) {
						System.exit(0);
					} else if(answer==0) {
						sifreGirdi.setText("");
						idGirdi.setText("");	
					}
				}
	}
	
	
	//----------BUTON---------\\
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==onay) {
			girisReaksiyon();
		}
	}
	
	
	//----------FARE---------\\
	@Override
	public void mousePressed(MouseEvent e) {
		sifreGirdi.setEchoChar((char)0);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		sifreGirdi.setEchoChar('\u2022');
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}

	
	//--------KLAVYE-------\\
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==idGirdi) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				idGirdi.transferFocus();
			    }
		}
		if(e.getSource()==sifreGirdi) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				girisReaksiyon();
			    }
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}
