package zProjeFinal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

public class KarsilamaPanel {
	
	JPanel karsilamaPanel;
	
	public JPanel panel() {
		
		karsilamaPanel = new JPanel();
		karsilamaPanel.setLayout(null);
		karsilamaPanel.setBounds(0, 0, 1030, 680);
		karsilamaPanel.setBackground(new Color(0x306576));
		
		ImageIcon accountIcon = new ImageIcon("Images/accounticon250x250.png");
		JLabel accountIconLbl = new JLabel(accountIcon);
		accountIconLbl.setBounds(390, 50, 250, 250);
		
		
		Kullanici giren = new Kullanici(Giris.idGiren);
		
		String isim = giren.getKullaniciAd().concat(" ").concat(giren.getKullaniciSoyad()) ; //dbden çekilecek
		JLabel hosgeldin = new JLabel("Hoþgeldin "+isim); //+isim yazýlacak
		hosgeldin.setHorizontalAlignment(JLabel.CENTER);
		hosgeldin.setForeground(Color.white);
		hosgeldin.setFont(new Font("Arial", Font.BOLD, 20));
		hosgeldin.setBounds(10, 310, 1010, 40);
		
		DateTimeFormatter trTipSaat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
		LocalDateTime saat = LocalDateTime.now();
		JLabel girisSaat = new JLabel("Giriþ Saati: "+trTipSaat.format(saat));
		girisSaat.setBounds(10, 355, 1010, 40);
		girisSaat.setFont(new Font("Arial", Font.ITALIC, 20));
		girisSaat.setHorizontalAlignment(JLabel.CENTER);
		girisSaat.setForeground(Color.white);
		
		double prim=giren.getKullaniciPrim();
		JLabel kazanýlanPrim = new JLabel("Kazandýðýnýz Prim: "+prim+'\u20ba');
		kazanýlanPrim.setBounds(10, 400, 1010, 40);
		kazanýlanPrim.setFont(new Font("Arial", Font.ITALIC, 20));
		kazanýlanPrim.setHorizontalAlignment(JLabel.CENTER);
		kazanýlanPrim.setForeground(Color.white);
		
		
		karsilamaPanel.add(hosgeldin);
		karsilamaPanel.add(accountIconLbl);
		karsilamaPanel.add(girisSaat);
		karsilamaPanel.add(kazanýlanPrim);
		
		return karsilamaPanel;
	}
	
	public void panelSetVisible(boolean a) {
		karsilamaPanel.setVisible(a);
	}
	
}
