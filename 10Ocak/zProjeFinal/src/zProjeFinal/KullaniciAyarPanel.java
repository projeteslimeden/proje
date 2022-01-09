package zProjeFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class KullaniciAyarPanel implements ActionListener, MouseListener, KeyListener{

	JPanel kullaniciAyarPanel;
	JButton sifreDegistirBut;
	Kullanici giren;
	Color arkaPlanColor;
	
	public JPanel panel() {
		arkaPlanColor = new Color(0x306576);
		
		kullaniciAyarPanel = new JPanel();
		kullaniciAyarPanel.setLayout(null);
		kullaniciAyarPanel.setBounds(0, 0, 1030, 680);
		kullaniciAyarPanel.setBackground(arkaPlanColor);
		
		ImageIcon accountIcon = new ImageIcon("Images/accounticon120x120.png");
		JLabel accountIconLbl = new JLabel(accountIcon);
		accountIconLbl.setBounds(20, 286, 120, 120);
		
		giren = new Kullanici(Giris.idGiren);
		
		JLabel[] kullaniciInfo = new JLabel[10]; //10 SATIR kullanici adi-soyadý, adresi, telefonu, eposta, çalýþmaya baþlama tarihi, kazandýðý prim,
		for(int i=0; i<=9; i++) {
			kullaniciInfo[i] = new JLabel();
			kullaniciInfo[i].setBounds(10, (416+(i*26)), 560, 20);
			kullaniciInfo[i].setForeground(Color.white);
			kullaniciInfo[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
			kullaniciAyarPanel.add(kullaniciInfo[i]);
		}
		
		String mevki = "";
		switch (giren.getKullaniciSeviyesi()) {
		case 1: mevki="Yönetici"; break;
		case 2: mevki="Genel Müdür"; break;
		case 3: mevki="Ýnsan Kaynaklarý"; break;
		case 4: mevki="Muhasebe"; break;
		case 5: mevki="Kasiyer" ; break;
		case 6: mevki="Satýþ Sorumlusu"; break;
		}
		
		kullaniciInfo[0].setText("<HTML><U>#"+giren.getKullaniciNo()+" nolu Kullanýcý Bilgileri</U></HTML>"); //altýný çiz ->"<HTML><U>YOUR TEXT HERE</U></HTML>"
		kullaniciInfo[1].setText("Adý Soyadý: "+giren.getKullaniciAd()+" "+giren.getKullaniciSoyad());
		kullaniciInfo[2].setText("Konumu: "+mevki);
		kullaniciInfo[3].setText("Telefon Numarasý: "+giren.getKullaniciTelefon());
		kullaniciInfo[4].setText("E-Posta Adresi: "+giren.getKullaniciEposta());
		kullaniciInfo[5].setText("Adresi: "+giren.getKullaniciAdres());
		kullaniciInfo[6].setText("Doðum Günü: "+giren.getKullaniciDogumTarih());
		kullaniciInfo[7].setText("Çalýþmaya Baþladýðý Tarih: "+giren.getKullaniciEklenmeTarih());
		kullaniciInfo[8].setText(String.format("Aldýðý Maaþ: %,.2f", giren.getKullaniciAylikMaas())+'\u20ba');
		kullaniciInfo[9].setText(String.format("Bu ay aldýðý Prim: %,.2f", giren.getKullaniciPrim())+'\u20ba'); kullaniciInfo[9].setForeground(Color.red);
		
		
		sifreDegistirBut = new JButton("Giriþ Parolasý Deðiþtir");
		sifreDegistirBut.setBounds(840, 10, 180, 20);
		sifreDegistirBut.setFocusable(false);
		sifreDegistirBut.setFont(new Font(null, Font.BOLD, 12));
		sifreDegistirBut.setBorder(new LineBorder(arkaPlanColor, 1));
		sifreDegistirBut.addActionListener(this);
		
		
		kullaniciAyarPanel.add(sifreDegistirBut);
		kullaniciAyarPanel.add(accountIconLbl);
		
		return kullaniciAyarPanel;
	}
	
	public void panelSetVisible(boolean a) {
		kullaniciAyarPanel.setVisible(a);
	}
	
	private JPasswordField eskiSifreGirdi, yeniSifreGirdi;
	private JButton onay;
	private JFrame degisimFrame;
	private JLabel eskiSifreGosterLbl, yeniSifreGosterLbl;
	private JFrame sifreDegistirFrame() { //frame yerine buton altýna panel olarak açýlabilirdi daha modern gözükürdü
		degisimFrame = new JFrame();
		JPanel degisimPanel = new JPanel();
		degisimPanel.setLayout(null);
		degisimPanel.setBounds(10, 10, 235, 68); //sað-sol total 15 kýrpýyor üst bar 45
		degisimPanel.setBackground(arkaPlanColor);
		
		JLabel eskiSifreTxt = new JLabel   ("Eski Parola  :");
		eskiSifreTxt.setBounds(5, 5, 80, 30);
		JLabel yeniSifreTxt = new JLabel("Yeni Parola  :");
		yeniSifreTxt.setHorizontalAlignment(JLabel.LEFT);
		yeniSifreTxt.setBounds(5, 30, 80, 30);
		
		eskiSifreGirdi = new JPasswordField(20);
		eskiSifreGirdi.setBounds(90, 8, 110, 25);
		eskiSifreGirdi.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
		eskiSifreGirdi.setBorder(null);
		eskiSifreGirdi.addKeyListener(this);
		yeniSifreGirdi = new JPasswordField(20);
		yeniSifreGirdi.setBounds(90, 35, 110, 25);
		yeniSifreGirdi.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
		yeniSifreGirdi.setBorder(null);
		yeniSifreGirdi.addKeyListener(this);
		ImageIcon eskiSifreGoster = new ImageIcon("Images/showpassico222.png");
		eskiSifreGosterLbl = new JLabel();
		eskiSifreGosterLbl.setIcon(eskiSifreGoster);
		eskiSifreGosterLbl.setBounds(202, 8, 30, 25);
		eskiSifreGosterLbl.addMouseListener(this);
		ImageIcon yeniSifreGoster = new ImageIcon("Images/showpassico222.png");
		yeniSifreGosterLbl = new JLabel();
		yeniSifreGosterLbl.setIcon(yeniSifreGoster);
		yeniSifreGosterLbl.setBounds(202, 35, 30, 25);
		yeniSifreGosterLbl.addMouseListener(this);
	
		onay = new JButton("Onayla");
		onay.setBounds((155/2),88,100,30);
		onay.addActionListener(this);
		onay.setFocusable(false);
		onay.setBorder(BorderFactory.createLineBorder(null, 1));
		
		degisimPanel.add(eskiSifreTxt);
		degisimPanel.add(eskiSifreGirdi);
		degisimPanel.add(yeniSifreTxt);
		degisimPanel.add(yeniSifreGirdi);
		degisimPanel.add(eskiSifreGosterLbl);
		degisimPanel.add(yeniSifreGosterLbl);
		
		degisimFrame.add(degisimPanel);
		degisimFrame.add(onay);
		ImageIcon icon = new ImageIcon("Images/zIconFinal.png");
		degisimFrame.setIconImage(icon.getImage());
		degisimFrame.setLayout(null);
		degisimFrame.setTitle("Depom Sepette");
		degisimFrame.setSize(270, 163);
		degisimFrame.getContentPane().setBackground(Color.darkGray);
		degisimFrame.setResizable(false);
		degisimFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		degisimFrame.setLocationRelativeTo(null); //pencereyi ortada baþlatma kodu
		degisimFrame.setVisible(true);
		return degisimFrame;
	}
	
	private void sifreDegisimOnayReaksiyon() {
		String eskiSifreText = new String(eskiSifreGirdi.getPassword());
		String yeniSifreText = new String(yeniSifreGirdi.getPassword());
		SifreSifreliyici kripto = new SifreSifreliyici();
		if(kripto.kriptola(eskiSifreText).equals(giren.getKullaniciKriptoSifre())) {
			giren.setKullaniciKriptoSifre(kripto.kriptola(yeniSifreText));
			degisimFrame.dispose();
			JOptionPane.showMessageDialog(null, "Þifre baþarýyla deðiþtirildi.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Hatalý eski parola..!", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		}
//		System.out.println(eskiSifreText+"-"+yeniSifreText); //TEST SATIRI
		degisimFrame.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==sifreDegistirBut) {
			sifreDegistirFrame();
		}
		
		if(e.getSource()==onay) {
			sifreDegisimOnayReaksiyon();
		}
		
	}

	
	//----------MOUSE----------
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==eskiSifreGosterLbl) {
			eskiSifreGirdi.setEchoChar((char)0);
		}
		if(e.getSource()==yeniSifreGosterLbl) {
			yeniSifreGirdi.setEchoChar((char)0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==eskiSifreGosterLbl) {
			eskiSifreGirdi.setEchoChar('\u2022');
		}
		if(e.getSource()==yeniSifreGosterLbl) {
			yeniSifreGirdi.setEchoChar('\u2022');
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	
	//--------KLAVYE------
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==eskiSifreGirdi) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				eskiSifreGirdi.transferFocus();
			    }
		}
		
		if(e.getSource()==yeniSifreGirdi) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				sifreDegisimOnayReaksiyon();
			    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	
}
