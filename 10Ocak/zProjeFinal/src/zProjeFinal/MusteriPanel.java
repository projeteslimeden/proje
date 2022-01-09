package zProjeFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MusteriPanel implements ActionListener, MouseListener, KeyListener{
	
	private JPanel musteriPanel;
	private JPanel musteriTabloPanel;
	private JButton musteriEkleBut, musteriCikarBut, musteriGoruntuleBut;
	private DefaultTableModel musteriTabloModel; JTable musteriTablo;
	private Color icPanelColor;
	private JTextField arama;
	private JButton araBut;
	
	public JPanel panel() { //müþteri son uðrama tarihi felan gelebilir
		Color arkaPlanColor = new Color(0xA06f26);
		icPanelColor = new Color(0xc6a97d);
		
		musteriPanel = new JPanel();
		musteriPanel.setLayout(null);
		musteriPanel.setBounds(0, 0, 1030, 680);
		musteriPanel.setBackground(arkaPlanColor);
		
		
		musteriTabloModel = new DefaultTableModel();
		Object[] kolonIsimleri = {"Müþteri No","Müþteri Ad Soyad","Müþteri Telefon","Müþteri E-Posta","Müþteri Adres"};
		musteriTabloModel.setColumnIdentifiers(kolonIsimleri);
		
		DefaultTableCellRenderer hucreOrtala = new DefaultTableCellRenderer(); hucreOrtala.setHorizontalAlignment(JLabel.CENTER);
		
		musteriTabloPanel = new JPanel();
		musteriTabloPanel.setLayout(null);
		musteriTabloPanel.setBounds(10, 10, 840, 660);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(0, 0, 840, 660);
		musteriTablo = new JTable();
		musteriTablo.setModel(musteriTabloModel);
		musteriTablo.addMouseListener(this);
		sp.setBorder(new LineBorder(Color.black, 4)); //deneme
		sp.setViewportView(musteriTablo);
		musteriTabloPanel.add(sp);
		
		musteriTablo.getColumnModel().getColumn(0).setPreferredWidth(30);
		musteriTablo.getColumnModel().getColumn(1).setPreferredWidth(100);
		musteriTablo.getColumnModel().getColumn(2).setPreferredWidth(100); //Tablo otomatik þekillendiði için deðerler aslýný yansýtmaz
		musteriTablo.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		musteriTablo.getColumnModel().getColumn(0).setCellRenderer(hucreOrtala);
		
		tabloCek();
		
		
		
		musteriEkleBut = new JButton("Müþteri Ekle");
		musteriEkleBut.setBounds(860, 10, 160, 40);
		musteriEkleBut.setFocusable(false);
		musteriEkleBut.addActionListener(this);
		
		musteriCikarBut = new JButton("Müþteri Sil");
		musteriCikarBut.setBounds(860, 55, 160, 40);
		musteriCikarBut.setFocusable(false);
		musteriCikarBut.addActionListener(this);
		musteriCikarBut.setEnabled(false);
		
		musteriGoruntuleBut = new JButton("Müþteri Görüntüle");
		musteriGoruntuleBut.setBounds(860, 100, 160, 40);
		musteriGoruntuleBut.setFocusable(false);
		musteriGoruntuleBut.addActionListener(this);
		musteriGoruntuleBut.setEnabled(false);
		
		arama = new JTextField();
		arama.setBounds(860, 160, 160, 30);
		arama.setBorder(null);
		arama.setFont(new Font(null, Font.CENTER_BASELINE, 13));
		arama.setFocusable(false);
		
		arama.setForeground(Color.gray);
			arama.setHorizontalAlignment(JLabel.CENTER);
				arama.setText("Telefon - Ýsim"); 
					arama.setFont(new Font(null, Font.ITALIC, 15));
		
		arama.addKeyListener(this);
		arama.addMouseListener(this);
		musteriPanel.add(arama);
		
		araBut = new JButton("Müþteri Ara");
		araBut.setBounds(860, 192, 160, 20);
		araBut.setFocusable(false);
		araBut.addActionListener(this);
		araBut.setEnabled(false);
		musteriPanel.add(araBut);
		
		
		musteriEklePanel();
		musteriGoruntulePanel();
		
		musteriPanel.add(musteriEkleBut);
		musteriPanel.add(musteriCikarBut);
		musteriPanel.add(musteriGoruntuleBut);
		musteriPanel.add(musteriTabloPanel);
		musteriPanel.add(musteriEklePanel); musteriEklePanel.setVisible(false);
		musteriPanel.add(musteriGoruntulePanel); musteriGoruntulePanel.setVisible(false);
		return musteriPanel;
	}
	
	private JPanel musteriEklePanel;
	private JTextField[] ekleGirdiler;
	private JButton ekleOnay, ekleIptal;
	private void musteriEklePanel() {
		musteriEklePanel = new JPanel();
		musteriEklePanel.setLayout(null);
		musteriEklePanel.setBounds(10, 10, 840, 660);
		musteriEklePanel.setBackground(icPanelColor);
		
		ImageIcon icon = new ImageIcon("Images/add-account118x100.png");
		JLabel iconLbl = new JLabel(icon);
		iconLbl.setBounds(10, 10, 118, 100);
		musteriEklePanel.add(iconLbl);
		
		JLabel[] tanim = new JLabel[4]; //9 SATIR
		for(int i=0; i<4; i++) {
			 tanim[i] = new JLabel();
			 tanim[i].setBounds(10, (120+(i*30)), 250, 20);
			 tanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 musteriEklePanel.add(tanim[i]);
		}
		tanim[0].setText("Yeni Müþteri Ad Soyad");
		tanim[1].setText("Yeni Müþteri Telefon");
		tanim[2].setText("Yeni Müþteri E-Posta");
		tanim[3].setText("Yeni Müþteri Adres");
		
		JLabel[] ikiNoktaAyrac = new JLabel[4]; // iki noktalarýn düz bir þekilde olmasý için ayrý label içinde alýndý
		for(int i=0; i<4; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(212, (120+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 musteriEklePanel.add(ikiNoktaAyrac[i]);
		}
		
		ekleGirdiler = new JTextField[4];
		for(int i=0; i<4; i++) {
			ekleGirdiler[i] = new JTextField();
			ekleGirdiler[i].setBounds(220, (120+(i*30)), 250, 20);
			ekleGirdiler[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			ekleGirdiler[i].setBorder(null);
			musteriEklePanel.add(ekleGirdiler[i]);
		}
		
		
		ekleOnay = new JButton("Onayla");
		ekleOnay.setBounds(315, 615, 100, 40);
		ekleOnay.setFocusable(false);
		ekleOnay.addActionListener(this);
		musteriEklePanel.add(ekleOnay);
		
		ekleIptal = new JButton("Ýptal");
		ekleIptal.setBounds(425, 615, 100, 40);
		ekleIptal.setFocusable(false);
		ekleIptal.addActionListener(this);
		musteriEklePanel.add(ekleIptal);
	}
	
	private JPanel musteriGoruntulePanel;
	private JLabel[] goruntuTanim; //nolu musteri deðiþimi için dýþarý alýndý
	private JButton goruntuleGeri;
	private JLabel[] musteriBilgi;
	private JButton[] musteriGuncelleButs;
	private JButton[] guncellemeOnayIptal;
	private JTextField[] musteriGuncellemeGirdi;
	
	private void musteriGoruntulePanel() {
		musteriGoruntulePanel = new JPanel();
		musteriGoruntulePanel.setLayout(null);
		musteriGoruntulePanel.setBounds(10, 10, 840, 660);
		musteriGoruntulePanel.setBackground(icPanelColor);
		goruntuTanim = new JLabel[8]; //9 SATIR
		for(int i=0; i<8; i++) {
			 goruntuTanim[i] = new JLabel();
			 goruntuTanim[i].setBounds(10, (10+(i*30)), 250, 20);
			 goruntuTanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 musteriGoruntulePanel.add(goruntuTanim[i]);
		}
//		goruntuTanim[0].setText("<HTML><U>#"+"5"+" nolu Ürün Bilgileri</U></HTML>"); //tanýmý tuþ basýnca oto yapýyor zaten
		goruntuTanim[1].setText("Müþteri Ad Soyad");
		goruntuTanim[2].setText("Müþteri Telefon");
		goruntuTanim[3].setText("Müþteri E-Posta");
		goruntuTanim[4].setText("Müþteri Adres");
		goruntuTanim[5].setText("Müþteri Kayýt Tarih"); // stok otomatik atanacak
		goruntuTanim[6].setText("Müþteri Son Alýþveriþ Tarih");
		goruntuTanim[7].setText("Müþteri Toplam Harcama");
		
		
		JLabel[] ikiNoktaAyrac = new JLabel[7]; // iki noktalarýn düz bir þekilde olmasý için ayrý label içinde alýndý
		for(int i=0; i<7; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(245, (40+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 musteriGoruntulePanel.add(ikiNoktaAyrac[i]);
		}
		
		musteriBilgi = new JLabel[7]; //9 SATIR
		for(int i=0; i<7; i++) {
			 musteriBilgi[i] = new JLabel();
			 musteriBilgi[i].setBounds(252, (40+(i*30)), 400, 20);
			 musteriBilgi[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 musteriGoruntulePanel.add(musteriBilgi[i]);
		}
		
		goruntuleGeri = new JButton("Geri");
		goruntuleGeri.setBounds(370, 615, 100, 40);
		goruntuleGeri.setFocusable(false);
		goruntuleGeri.addActionListener(this);
		musteriGoruntulePanel.add(goruntuleGeri);
		
		musteriGuncelleButs = new JButton[1];
		for(int i=0; i<1; i++) {
			musteriGuncelleButs[i] = new JButton();
			musteriGuncelleButs[i].setBounds(635, (635+(i*25)), 200, 20);
			musteriGuncelleButs[i].setFocusable(false);
			musteriGuncelleButs[i].addActionListener(this);
			musteriGoruntulePanel.add(musteriGuncelleButs[i]);
		}
		musteriGuncelleButs[0].setText("Müþteri Bilgilerini Güncelle");
		
		
		musteriGuncellemeGirdi = new JTextField[4];
		for(int i=0; i<4; i++) {
			 musteriGuncellemeGirdi[i] = new JTextField();
			 musteriGuncellemeGirdi[i].setBounds(252, (40+(i*30)), 300, 20);
			 musteriGuncellemeGirdi[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 musteriGuncellemeGirdi[i].setBorder(null);
			 musteriGuncellemeGirdi[i].setVisible(false);
			 musteriGoruntulePanel.add(musteriGuncellemeGirdi[i]);
		}
		
		
		guncellemeOnayIptal = new JButton[2];
		for(int i=0; i<2; i++) {
			guncellemeOnayIptal[i] = new JButton();
			guncellemeOnayIptal[i].setBounds((315+(i*110)), 615, 100, 40);
			guncellemeOnayIptal[i].setFocusable(false);
			guncellemeOnayIptal[i].addActionListener(this);
			guncellemeOnayIptal[i].setVisible(false);
			musteriGoruntulePanel.add(guncellemeOnayIptal[i]);
		}
		guncellemeOnayIptal[0].setText("Onayla");
		guncellemeOnayIptal[1].setText("Ýptal");
		
	}
	
	private void tabloCek() { //public yaptým çünkü ürün satýþýndan sonra sol panelden her týklamaya refresh lazým
		musteriTabloModel.setRowCount(0);
		String calisanlar_sorgu = "SELECT * FROM musteri";
		ResultSet dbVeri = DbBaglanti.sorgula(calisanlar_sorgu);
		Object[] satir = new Object[5];
		
		try {
			while(dbVeri.next()) {
				satir[0] = dbVeri.getInt("musteri_no");
				satir[1] = dbVeri.getString("musteri_ad_soyad"); //string yeterli olabilir
				satir[2] = dbVeri.getString("musteri_telefon");
				satir[3] = dbVeri.getString("musteri_eposta");
				satir[4] = dbVeri.getString("musteri_adres");
				musteriTabloModel.addRow(satir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void aramaReaksiyon() {
		ResultSet AramaSonuc;
		AramaSonuc = Musteri.arama(arama.getText());
		
		
		Object[] satir = new Object[5];
		musteriTabloModel.setRowCount(0);
		try {
			while(AramaSonuc.next()) {
				satir[0] = AramaSonuc.getInt("musteri_no");
				satir[1] = AramaSonuc.getString("musteri_ad_soyad"); //string yeterli olabilir
				satir[2] = AramaSonuc.getString("musteri_telefon");
				satir[3] = AramaSonuc.getString("musteri_eposta");
				satir[4] = AramaSonuc.getString("musteri_adres");
				musteriTabloModel.addRow(satir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//----------------BUTTON--------------
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//-------------------EKLE------------------------
		if(e.getSource()==musteriEkleBut) {
			musteriTabloPanel.setVisible(false);
			musteriGoruntulePanel.setVisible(false);
			musteriCikarBut.setEnabled(false); //kazayý engelleme
			musteriGoruntuleBut.setEnabled(false); // kazayý engelleme
			musteriEklePanel.setVisible(true);
			arama.setVisible(false); araBut.setVisible(false);
		}
		if(e.getSource()==ekleIptal) {
			musteriEklePanel.setVisible(false);
			musteriGoruntulePanel.setVisible(false);
			tabloCek(); // deneme
			arama.setForeground(Color.gray);
				arama.setHorizontalAlignment(JLabel.CENTER);
					arama.setText("Telefon - Ýsim"); araBut.setEnabled(false);
						arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
			arama.setVisible(true); araBut.setVisible(true);
			musteriTabloPanel.setVisible(true);
			for(int i=0; i<4; i++) {
				ekleGirdiler[i].setText("");
			}
		}
		if(e.getSource()==ekleOnay) {
			boolean girdiKontrol=false;
			if(ekleGirdiler[0].getText().replaceAll("\\W", "").equals("") || ekleGirdiler[1].getText().replaceAll("\\W", "").equals("") || 
					ekleGirdiler[2].getText().replaceAll("\\W", "").equals("") ||ekleGirdiler[3].getText().replaceAll("\\W", "").equals("")) {
				girdiKontrol=false;
			} else girdiKontrol=true;
			
			if(girdiKontrol==true) {
				int basarili = Musteri.ekle(ekleGirdiler[0].getText(), ekleGirdiler[1].getText(),
		 				ekleGirdiler[2].getText(), ekleGirdiler[3].getText());
				if(basarili==1) {
					for(int i=0; i<4; i++) {
						ekleGirdiler[i].setText("");
					}
					
					musteriCikarBut.setEnabled(false);
					musteriGoruntuleBut.setEnabled(false);
					musteriEklePanel.setVisible(false);
					musteriGoruntulePanel.setVisible(false);
					tabloCek();
					arama.setForeground(Color.gray); 
						arama.setHorizontalAlignment(JLabel.CENTER);
							arama.setText("Telefon - Ýsim"); araBut.setEnabled(false);
								arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
					arama.setVisible(true); araBut.setVisible(true);
					musteriTabloPanel.setVisible(true);
				}	
			} else JOptionPane.showMessageDialog(null, "Lütfen bütün boþluklarý uygun þekilde giriniz.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//---------------------SÝLME------------------
		if(e.getSource()==musteriCikarBut) {
			String[] cevap = {"Evet","Hayýr"};
			int answer = JOptionPane.showOptionDialog(
					null, //component
					secilenMusteriNo+"'nolu müþteriyi silmek istediðinize emin misiniz?", //message 
					"Depom Sepette", //title
					JOptionPane.YES_NO_CANCEL_OPTION, //option type 
					JOptionPane.WARNING_MESSAGE, //message type
					null, // icon
					cevap, // option da yazacaklar 0-1-2 olarak deðiþtirir
					0);
			if(answer == 0) {
				String musteriSil_sql = "DELETE FROM `zprojefinal`.`musteri` WHERE (`musteri_no` = '"+secilenMusteriNo+"')";
				DbBaglanti.sil(musteriSil_sql);
				tabloCek();
				secilenMusteriNo = 0;
				musteriCikarBut.setEnabled(false);
				musteriGoruntuleBut.setEnabled(false);
			}
		}
		//---------------------GÖRÜNTÜLE-----------------
		if(e.getSource()==musteriGoruntuleBut) {
			musteriTabloPanel.setVisible(false);
			musteriEklePanel.setVisible(false);
			arama.setVisible(false); araBut.setVisible(false);
			musteriCikarBut.setEnabled(false); //kazayý engelleme
			musteriGoruntuleBut.setEnabled(false); // kazayý engelleme
			
			Musteri goruntulenecekMusteri = new Musteri(secilenMusteriNo);
			goruntuTanim[0].setText("<HTML><U>#"+goruntulenecekMusteri.getMusteriNo()+" nolu Müþteri Bilgileri</U></HTML>");
			musteriBilgi[0].setText(goruntulenecekMusteri.getMusteriAdSoyad());
			musteriBilgi[1].setText(""+goruntulenecekMusteri.getMusteriTelefon());
			musteriBilgi[2].setText(goruntulenecekMusteri.getMusteriEposta());
			musteriBilgi[3].setText(""+goruntulenecekMusteri.getMusteriAdres());
			musteriBilgi[4].setText(""+goruntulenecekMusteri.getMusteriEklenmeTarih()); // stok otomatik atanacak
			if(goruntulenecekMusteri.getMusteriSonIslemTarih().toString().equals("0001-01-01 00:00:00.0")) {
				musteriBilgi[5].setText("Hiç Alýþ Veriþ Yapmadý");
			} else musteriBilgi[5].setText(""+goruntulenecekMusteri.getMusteriSonIslemTarih());
			musteriBilgi[6].setText(String.format("%,.2f", goruntulenecekMusteri.getMusteriToplamHarcama())+'\u20ba');
			
			musteriGuncellemeGirdi[0].setText(goruntulenecekMusteri.getMusteriAdSoyad());
			musteriGuncellemeGirdi[1].setText(""+goruntulenecekMusteri.getMusteriTelefon());
			musteriGuncellemeGirdi[2].setText(goruntulenecekMusteri.getMusteriEposta());
			musteriGuncellemeGirdi[3].setText(""+goruntulenecekMusteri.getMusteriAdres());

		
			musteriGoruntulePanel.setVisible(true);
		}
		
		if(e.getSource()==goruntuleGeri) {
			musteriGoruntulePanel.setVisible(false);
			musteriEklePanel.setVisible(false);
			musteriCikarBut.setEnabled(false); //kazayý engelleme
			musteriGoruntuleBut.setEnabled(false); // kazayý engelleme
			arama.setForeground(Color.gray); 
				arama.setHorizontalAlignment(JLabel.CENTER);
					arama.setText("Telefon - Ýsim");araBut.setEnabled(false);
						arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
			arama.setVisible(true); araBut.setVisible(true);
			musteriTabloPanel.setVisible(true);
		}
		if(e.getSource()==musteriGuncelleButs[0]) { //musteriu güncelle
			goruntuleGeri.setVisible(false);
			guncellemeOnayIptal[0].setVisible(true);
			guncellemeOnayIptal[1].setVisible(true);
			musteriCikarBut.setEnabled(false); //kazayý engelleme
			musteriGoruntuleBut.setEnabled(false); // kazayý engelleme
			for(int i=0; i<4; i++){
				musteriBilgi[i].setVisible(false);
			}
			
			for(int i=0; i<4; i++) {
				 musteriGuncellemeGirdi[i].setVisible(true);
			}
		}
		if(e.getSource()==guncellemeOnayIptal[1]) { //güncelleme iptal
			guncellemeOnayIptal[0].setVisible(false);
			guncellemeOnayIptal[1].setVisible(false);
			goruntuleGeri.setVisible(true);
			for(int i=0; i<4; i++){
				musteriBilgi[i].setVisible(true);
			}
			for(int i=0; i<4; i++) {
				 musteriGuncellemeGirdi[i].setVisible(false);
			}
		}
		if(e.getSource()==guncellemeOnayIptal[0]) { //onayla
			boolean girdiKontrol=false;
			if(musteriGuncellemeGirdi[1].getText().replaceAll("\\W", "").equals("") || musteriGuncellemeGirdi[0].getText().replaceAll("\\W", "").equals("")) {
				girdiKontrol=false;
			} else girdiKontrol=true;
			
			if(girdiKontrol == true) {
				int basarili = Musteri.guncelle(secilenMusteriNo,musteriGuncellemeGirdi[0].getText(), musteriGuncellemeGirdi[1].getText(), 
						musteriGuncellemeGirdi[2].getText(), musteriGuncellemeGirdi[3].getText());
				if(basarili == 1) {
					musteriGoruntulePanel.setVisible(false);
					musteriEklePanel.setVisible(false);
					musteriCikarBut.setEnabled(false); //kazayý engelleme
					musteriGoruntuleBut.setEnabled(false); // kazayý engelleme
					
					guncellemeOnayIptal[0].setVisible(false);
					guncellemeOnayIptal[1].setVisible(false);
					goruntuleGeri.setVisible(true);
					for(int i=0; i<4; i++){
						musteriBilgi[i].setVisible(true);
					}
					for(int i=0; i<4; i++) {
						 musteriGuncellemeGirdi[i].setVisible(false);
					}
					
					arama.setForeground(Color.gray); 
						arama.setHorizontalAlignment(JLabel.CENTER);
							arama.setText("Telefon - Ýsim"); araBut.setEnabled(false);
								arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
					arama.setVisible(true); araBut.setVisible(true);
					
					tabloCek();
					musteriTabloPanel.setVisible(true);
				}
			}	else JOptionPane.showMessageDialog(null, "Müþteri Telefon veya Ýsim Boþ Býrakýlamaz", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		}
		
		//------------ARAMA---------
		if(e.getSource()==araBut) {
			aramaReaksiyon();
		}
	}//action override clsoe
	
	
	//----------------KLAVYE--------------
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==arama) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				aramaReaksiyon();
			    }
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	//----------------MOUSE--------------
//	private int secilenMusteriTabloSirasi; //þmdilik gerek yok gibi
	private int secilenMusteriNo;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==musteriTablo) {
			secilenMusteriNo = (int) musteriTabloModel.getValueAt(musteriTablo.getSelectedRow(), 0);
//			secilenMusteriTabloSirasi = musteriTablo.getSelectedRow();
			musteriCikarBut.setEnabled(true);
			musteriGoruntuleBut.setEnabled(true);
		}
		if(e.getSource()==arama) {
			arama.setFocusable(true);
			arama.grabFocus();
			if(arama.getText().equals("Telefon - Ýsim")) {
				arama.setForeground(Color.black);
				arama.setFont(new Font(null, Font.CENTER_BASELINE, 13));
				arama.setHorizontalAlignment(JLabel.LEFT);
				arama.setText("");
				arama.grabFocus();
			}
			araBut.setEnabled(true);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	//------------------CLASS SET--------------------------
		public void panelSetVisible(boolean a) {
			musteriPanel.setVisible(a);
		}
}
