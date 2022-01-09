package zProjeFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class UrunlerPanel implements ActionListener, MouseListener, KeyListener{

	private JPanel urunlerPanel;
	private JPanel urunTabloPanel;
	private JButton urunEkleBut, urunCikarBut, urunGoruntuleBut;
	private DefaultTableModel urunTabloModel; JTable urunTablo;
	private Color icPanelColor;
	private JTextField arama;
	private JButton araBut;

	public JPanel panel() {
		Color arkaPlanColor = new Color(0xffa400);
		icPanelColor = new Color(0xffc866);
		
		urunlerPanel = new JPanel();
		urunlerPanel.setLayout(null);
		urunlerPanel.setBounds(0, 0, 1030, 680);
		urunlerPanel.setBackground(arkaPlanColor);
		
		
		urunTabloModel = new DefaultTableModel();
		Object[] kolonIsimleri = {"Ürün No","Ürün Barkod","Ürün Adý","Kategori","Stok Adeti","Ürün Fiyatý"};
		urunTabloModel.setColumnIdentifiers(kolonIsimleri);
		
		DefaultTableCellRenderer hucreOrtala = new DefaultTableCellRenderer(); hucreOrtala.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer hucreSag = new DefaultTableCellRenderer(); hucreSag.setHorizontalAlignment(JLabel.RIGHT);
		
		urunTabloPanel = new JPanel();
		urunTabloPanel.setLayout(null);
		urunTabloPanel.setBounds(10, 10, 840, 660);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(0, 0, 840, 660); //10-10-840-660
		urunTablo = new JTable();
		urunTablo.setModel(urunTabloModel);
		urunTablo.addMouseListener(this);
		sp.setBorder(new LineBorder(Color.black, 4)); //deneme
		sp.setViewportView(urunTablo);
		urunTabloPanel.add(sp);
		
		urunTablo.getColumnModel().getColumn(0).setPreferredWidth(30);
		urunTablo.getColumnModel().getColumn(1).setPreferredWidth(40);
		urunTablo.getColumnModel().getColumn(2).setPreferredWidth(150); //Tablo otomatik þekillendiði için deðerler aslýný yansýtmaz
		urunTablo.getColumnModel().getColumn(4).setPreferredWidth(10);
		
		urunTablo.getColumnModel().getColumn(0).setCellRenderer(hucreOrtala);
		urunTablo.getColumnModel().getColumn(3).setCellRenderer(hucreOrtala);
		urunTablo.getColumnModel().getColumn(4).setCellRenderer(hucreOrtala);
		urunTablo.getColumnModel().getColumn(5).setCellRenderer(hucreSag);
		
		tabloCek();
		
		
		
		urunEkleBut = new JButton("Ürün Ekle");
		urunEkleBut.setBounds(860, 10, 160, 40);
		urunEkleBut.setFocusable(false);
		urunEkleBut.addActionListener(this);
		
		urunCikarBut = new JButton("Ürün Sil");
		urunCikarBut.setBounds(860, 55, 160, 40);
		urunCikarBut.setFocusable(false);
		urunCikarBut.addActionListener(this);
		urunCikarBut.setEnabled(false);
		
		urunGoruntuleBut = new JButton("Ürün Görüntüle");
		urunGoruntuleBut.setBounds(860, 100, 160, 40);
		urunGoruntuleBut.setFocusable(false);
		urunGoruntuleBut.addActionListener(this);
		urunGoruntuleBut.setEnabled(false);
		
		arama = new JTextField();
		arama.setBounds(860, 160, 160, 30);
		arama.setBorder(null);
		arama.setFont(new Font(null, Font.CENTER_BASELINE, 13));
		arama.setFocusable(false);
		
		arama.setForeground(Color.gray);
			arama.setHorizontalAlignment(JLabel.CENTER);
				arama.setText("Barkod - Ad"); 
					arama.setFont(new Font(null, Font.ITALIC, 15));
		
		arama.addKeyListener(this);
		arama.addMouseListener(this);
		urunlerPanel.add(arama);
		
		araBut = new JButton("Ürün Ara");
		araBut.setBounds(860, 192, 160, 20);
		araBut.setFocusable(false);
		araBut.addActionListener(this);
		araBut.setEnabled(false);
		urunlerPanel.add(araBut);
		
		
		urunEklePanel();
		urunGoruntulePanel();
		
		urunlerPanel.add(urunEkleBut);
		urunlerPanel.add(urunCikarBut);
		urunlerPanel.add(urunGoruntuleBut);
		urunlerPanel.add(urunTabloPanel);
		urunlerPanel.add(urunEklePanel); urunEklePanel.setVisible(false);
		urunlerPanel.add(urunGoruntulePanel); urunGoruntulePanel.setVisible(false);
		return urunlerPanel;
	}
	
	
	
	private JPanel urunGoruntulePanel;
	private JLabel goruntuleFotoLbl;
	private JLabel[] goruntuTanim; //nolu urun deðiþimi için dýþarý alýndý
	private JButton goruntuleGeri;
	private JLabel[] urunBilgi;
	private JButton[] urunGuncelleButs;
	private FileInputStream fotoGuncelleInput;
	private JButton[] guncellemeOnayIptal;
	private JTextField[] urunGuncellemeGirdi;
	
	private void urunGoruntulePanel() {
		urunGoruntulePanel = new JPanel();
		urunGoruntulePanel.setLayout(null);
		urunGoruntulePanel.setBounds(10, 10, 840, 660);
		urunGoruntulePanel.setBackground(icPanelColor);
		
		//BÝLGÝLENDÝRME
		JLabel info = new JLabel("Not: Ürün özellikleri görüntüleme ve");
		JLabel info2 = new JLabel("düzenleme sonra eklenecek.");
		info.setBounds(5, 630, 250, 20);
		info2.setBounds(5, 640, 250, 20);
		info.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		info2.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		urunGoruntulePanel.add(info);
		urunGoruntulePanel.add(info2);
		
		goruntuTanim = new JLabel[11]; //9 SATIR
		for(int i=0; i<11; i++) {
			 goruntuTanim[i] = new JLabel();
			 goruntuTanim[i].setBounds(10, (10+(i*30)), 250, 20);
			 goruntuTanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 urunGoruntulePanel.add(goruntuTanim[i]);
		}
		goruntuTanim[0].setText("<HTML><U>#"+"5"+" nolu Ürün Bilgileri</U></HTML>");
		goruntuTanim[1].setText("Ürün Kategori");
		goruntuTanim[2].setText("Ürün Barkod");
		goruntuTanim[3].setText("Ürün Ad");
		goruntuTanim[4].setText("Ürün Alýþ Fiyat");
		goruntuTanim[5].setText("Ürün Satýþ Fiyat"); // stok otomatik atanacak
		goruntuTanim[6].setText("Ürün Kalan Adet");
		goruntuTanim[7].setText("Ürün Ana Tedarikçi");
		goruntuTanim[8].setText("Ürün Ýlk Eklenme Tarihi");
		goruntuTanim[9].setText("Ürün Son Alis Tarihi");
		goruntuTanim[10].setText("Ürün Son Satis Tarihi");
		
		JLabel[] ikiNoktaAyrac = new JLabel[10]; // iki noktalarýn düz bir þekilde olmasý için ayrý label içinde alýndý
		for(int i=0; i<10; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(220, (40+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 urunGoruntulePanel.add(ikiNoktaAyrac[i]);
		}
		
		urunBilgi = new JLabel[10]; //9 SATIR
		for(int i=0; i<10; i++) {
			 urunBilgi[i] = new JLabel();
			 urunBilgi[i].setBounds(227, (40+(i*30)), 250, 20);
			 urunBilgi[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 urunGoruntulePanel.add(urunBilgi[i]);
		}
		
		goruntuleFotoLbl = new JLabel();
		goruntuleFotoLbl.setBounds(470, 20, 350, 262);
		urunGoruntulePanel.add(goruntuleFotoLbl);
		
		goruntuleGeri = new JButton("Geri");
		goruntuleGeri.setBounds(370, 615, 100, 40);
		goruntuleGeri.setFocusable(false);
		goruntuleGeri.addActionListener(this);
		urunGoruntulePanel.add(goruntuleGeri);
		
		urunGuncelleButs = new JButton[2];
		for(int i=0; i<2; i++) {
			urunGuncelleButs[i] = new JButton();
			urunGuncelleButs[i].setBounds(655, (610+(i*25)), 180, 20);
			urunGuncelleButs[i].setFocusable(false);
			urunGuncelleButs[i].addActionListener(this);
			urunGoruntulePanel.add(urunGuncelleButs[i]);
		}
		urunGuncelleButs[0].setText("Ürün Fotoðrafý Güncelle");
		urunGuncelleButs[1].setText("Ürün Bilgilerini Güncelle");
		
		
		urunGuncellemeGirdi = new JTextField[6];
		for(int i=0; i<6; i++) {
			 urunGuncellemeGirdi[i] = new JTextField();
			 urunGuncellemeGirdi[i].setBounds(227, (40+(i*30)), 200, 20);
			 urunGuncellemeGirdi[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 urunGuncellemeGirdi[i].setBorder(null);
			 urunGuncellemeGirdi[i].setVisible(false);
			 urunGoruntulePanel.add(urunGuncellemeGirdi[i]);
		}
		urunGuncellemeGirdi[5].setBounds(227, 220, 200, 20);
		
		guncellemeOnayIptal = new JButton[2];
		for(int i=0; i<2; i++) {
			guncellemeOnayIptal[i] = new JButton();
			guncellemeOnayIptal[i].setBounds((315+(i*110)), 615, 100, 40);
			guncellemeOnayIptal[i].setFocusable(false);
			guncellemeOnayIptal[i].addActionListener(this);
			guncellemeOnayIptal[i].setVisible(false);
			urunGoruntulePanel.add(guncellemeOnayIptal[i]);
		}
		guncellemeOnayIptal[0].setText("Onayla");
		guncellemeOnayIptal[1].setText("Ýptal");
		
	}
	
	
	
	private JPanel urunEklePanel;
	private JTextField[] ekleGirdiler, ekleOzellikGirdiler;
	private JButton ekleOnay, ekleIptal;
	private JLabel fotoLbl; private JButton fotoEkle; private ImageIcon noImage;//tekrar eklememk için
	private FileInputStream fotoInput;
	private JComboBox<String> kategoriBox, kategori2Box;
	private JLabel[] yeniUrunOzellikTanim;
	private int ustKategoriNo, altKategoriNo, kategoriNo;
	
	private void urunEklePanel() {
		urunEklePanel = new JPanel();
		urunEklePanel.setLayout(null);
		urunEklePanel.setBounds(10, 10, 840, 660);
		urunEklePanel.setBackground(icPanelColor);
		
				//BÝLGÝLENDÝRME
				JLabel info = new JLabel("Not: Yeni foto yüklenmeyince");
				JLabel info2 = new JLabel("bazen noImage'i çekemiyor.");
				info.setBounds(5, 630, 250, 20);
				info2.setBounds(5, 640, 250, 20);
				info.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
				info2.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
				urunEklePanel.add(info);
				urunEklePanel.add(info2);
		
		JLabel[] tanim = new JLabel[7]; //9 SATIR
		for(int i=0; i<7; i++) {
			 tanim[i] = new JLabel();
			 tanim[i].setBounds(10, (10+(i*30)), 250, 20);
			 tanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 urunEklePanel.add(tanim[i]);
		}
		tanim[0].setText("Kategori");
		tanim[1].setText("Yeni Ürün Barkod");
		tanim[2].setText("Yeni Ürün Ad");
		tanim[3].setText("Yeni Ürün Alýþ Fiyat");
		tanim[4].setText("Yeni Ürün Satýþ Fiyat"); // stok otomatik atanacak
		tanim[5].setText("Yeni Ürün Ana Tedarikçi");
		tanim[6].setText("Yeni Ürün Fotoðraf");
		
		JLabel[] ikiNoktaAyrac = new JLabel[7]; // iki noktalarýn düz bir þekilde olmasý için ayrý label içinde alýndý
		for(int i=0; i<7; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(242, (10+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 urunEklePanel.add(ikiNoktaAyrac[i]);
		}
		
		yeniUrunOzellikTanim = new JLabel[8]; //9 SATIR
		for(int i=0; i<8; i++) {
			 yeniUrunOzellikTanim[i] = new JLabel();
			 yeniUrunOzellikTanim[i].setBounds(10, (280+(i*30)), 250, 20);
			 yeniUrunOzellikTanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 urunEklePanel.add(yeniUrunOzellikTanim[i]);
		}
		
		ikiNoktaAyracForOzellik = new JLabel[8]; // iki noktalarýn düz bir þekilde olmasý için ayrý label içinde alýndý
		for(int i=0; i<8; i++) {
			 ikiNoktaAyracForOzellik[i] = new JLabel();
			 ikiNoktaAyracForOzellik[i].setBounds(242, (280+(i*30)), 8, 20);
			 ikiNoktaAyracForOzellik[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 urunEklePanel.add(ikiNoktaAyracForOzellik[i]);
		}
		
		kategori2Box = new JComboBox<String>();
		kategori2Box.setBounds(250, 250, 200, 20);
		kategori2Box.setVisible(false);
		kategori2Box.addActionListener(this);
		kategori2Box.addMouseListener(this);
		urunEklePanel.add(kategori2Box);
		
		ekleOzellikGirdiler = new JTextField[8];
		for(int i=0; i<8; i++) {
			 ekleOzellikGirdiler[i] = new JTextField();
			 ekleOzellikGirdiler[i].setBounds(250, (280+(i*30)), 200, 20);
			 ekleOzellikGirdiler[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ekleOzellikGirdiler[i].setBorder(null);
			 ekleOzellikGirdiler[i].setVisible(false);
			 urunEklePanel.add(ekleOzellikGirdiler[i]);
		}
		
		ekleGirdiler = new JTextField[6];
		for(int i=0; i<6; i++) {
			ekleGirdiler[i] = new JTextField();
			ekleGirdiler[i].setBounds(250, (10+(i*30)), 200, 20);
			ekleGirdiler[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			ekleGirdiler[i].setBorder(null);
			urunEklePanel.add(ekleGirdiler[i]);
		}
		ekleGirdiler[3].setText("Örnek: 1322.55");
		ekleGirdiler[4].setText("Örnek: 2624.81");
		ekleGirdiler[0].setVisible(false); //önceki ayarlarý deðiþtirmemek için direk görünürlük kapattým
		
		String[] kategoriler = {"Seçim Yapýnýz", "Teknoloji", "Giyim", "Kitap", "Gýda"};
		kategoriBox = new JComboBox<String>(kategoriler);
		kategoriBox.setSelectedIndex(0);
		kategoriBox.setBounds(250, 10, 200, 20);
		kategoriBox.addActionListener(this);
		kategoriBox.addMouseListener(this);
		urunEklePanel.add(kategoriBox);
		
		noImage = new ImageIcon("Images/NoimagetransparanSiyah350x262.png");
		fotoLbl = new JLabel();
		fotoLbl.setIcon(noImage);
		fotoLbl.setBounds(470, 20, 350, 262);
		urunEklePanel.add(fotoLbl);
		
		try {
			fotoInput = new FileInputStream("Images/NoimagetransparanSiyah350x262.png");
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		
		fotoEkle = new JButton("Fotoðraf Ekle");
		fotoEkle.setBounds(250, 190, 200, 20);
		fotoEkle.setFocusable(false);
		fotoEkle.addActionListener(this);
		urunEklePanel.add(fotoEkle);
		
		ekleOnay = new JButton("Onayla");
		ekleOnay.setBounds(315, 615, 100, 40);
		ekleOnay.setFocusable(false);
		ekleOnay.addActionListener(this);
		urunEklePanel.add(ekleOnay);
		
		ekleIptal = new JButton("Ýptal");
		ekleIptal.setBounds(425, 615, 100, 40);
		ekleIptal.setFocusable(false);
		ekleIptal.addActionListener(this);
		urunEklePanel.add(ekleIptal);
		
	}
	
	public void tabloCek() { //public yaptým çünkü ürün satýþýndan sonra sol panelden her týklamaya refresh lazým
		urunTabloModel.setRowCount(0);
		String calisanlar_sorgu = "SELECT * FROM urunler";
		ResultSet dbVeri = DbBaglanti.sorgula(calisanlar_sorgu);
		
		Object[] satir = new Object[7];
		
		try {
			while(dbVeri.next()) {
//				String kategori = new Kategori(dbVeri.getInt("kategori_no")).getKategoriAd();
				
				satir[0] = dbVeri.getInt("urun_no");
				satir[1] = dbVeri.getString("urun_barkod"); //string yeterli olabilir
				satir[2] = dbVeri.getString("urun_ad");
				satir[3] = "";
				satir[4] = dbVeri.getInt("urun_kalan_adet");
				satir[5] = String.format("%,.2f", dbVeri.getDouble("urun_satis_fiyat"))+'\u20ba';
				urunTabloModel.addRow(satir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//diðer türlü resultset close hatasý veriyor
		for(int i=0; i<urunTabloModel.getRowCount(); i++) {
			urunTabloModel.setValueAt(new Urunler(new BigInteger(urunTabloModel.getValueAt(i, 1).toString())).getKategoriAd(), i, 3);
		}
		
	}
	
	private ImageIcon yeniBoyutsImageIcon;
	private int dosyaEkleOnay;
	private FileInputStream fotoInputCek() {
		FileInputStream gonderInput = null;
		
		UIManager.put("FileChooser.cancelButtonText","Ýptal"); //cancel yerine iptal yazdý
		JFileChooser dosyaEkle = new JFileChooser();
		dosyaEkle.setApproveButtonText("Seç"); //open yerine seç yazdý
		dosyaEkle.setCurrentDirectory(new File(System.getProperty("user.home")+"/Desktop")); //HATA VEREBÝLÝR PCDEN PCYE DEÐÝÞÝR
		
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("*.Fotoðraf", "jpg", "png");
		dosyaEkle.addChoosableFileFilter(filtre);
		
		dosyaEkleOnay = dosyaEkle.showOpenDialog(null);
		String dosyaAdý=".jpg"; //dosyadý if e takýlmamasý için formalite isim verdim
		if(dosyaEkleOnay == JFileChooser.APPROVE_OPTION) { //0
			dosyaAdý = dosyaEkle.getSelectedFile().getName(); //normalde direk int dosyaEkleOnayun altýnda tanýmlý
		} else { //garip þekilde kategorileri ekledikten sonra bazen fotoinput a yüklenme olmuyor test amaçlý burayada ekledim ama hala hatayý bulamadým
			try {
				fotoInput = new FileInputStream("Images/NoimagetransparanSiyah350x262.png");
			} catch (FileNotFoundException e2) {
				System.out.println("FOTO CEKMEDE HATA LINE 416");
				e2.printStackTrace();
			}
			ImageIcon noFoto = new ImageIcon("Images/NoimagetransparanSiyah350x262.png"); 
			fotoLbl.setIcon(noFoto);
		}
		
		if(dosyaAdý.endsWith(".jpg")||dosyaAdý.endsWith(".JPG")||dosyaAdý.endsWith(".Jpg")
				||dosyaAdý.endsWith(".png")||dosyaAdý.endsWith(".PNG")||dosyaAdý.endsWith(".Png")) {
			if(dosyaEkleOnay == JFileChooser.APPROVE_OPTION) {
				ImageIcon sImageIcon = new ImageIcon(dosyaEkle.getSelectedFile().getAbsolutePath());//fotoyu image icona kaydetti //s=selected
				Image sImage = sImageIcon.getImage();//imageicon u image e kaydetti
				Image yeniBoyutImage = sImage.getScaledInstance(350, 262, Image.SCALE_SMOOTH); //image yeniden boyutlandýrma ve yeni kaydetme
				yeniBoyutsImageIcon = new ImageIcon(yeniBoyutImage);// yeniden boyutlandýrýlmýþ image i image icona kaydetti
				

				try {
					gonderInput = new FileInputStream(dosyaEkle.getSelectedFile().getAbsolutePath());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			return gonderInput;
		} else {
			JOptionPane.showMessageDialog(null, "HATA: Sadece *.png veye *.jpg formatý kabul edilir.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
		}
		return gonderInput;
	}
	
	private void fotoToLbl(int a) {
		if(a==0) { // eðer eklenen ürünse fotolbl a sýfýr gönderilmeli
			fotoLbl.setIcon(yeniBoyutsImageIcon);
		} else if(a==1) { // eðer güncellenecek ürünse  fotolbl a 1 gönderilmeli
			goruntuleFotoLbl.setIcon(yeniBoyutsImageIcon);
		}
	}
	
	private void aramaReaksiyon() {
		ResultSet AramaSonuc;
		try {
//			System.out.println("Sayi çekti");
			BigInteger barkod = new BigInteger(arama.getText());
			AramaSonuc = Urunler.arama(barkod);
		} catch (Exception e) {
//			System.out.println("String çekti");
//			e.printStackTrace(); //dönüþüm hata olursa incelemek için aç
			AramaSonuc = Urunler.arama(arama.getText());
		}
		
		
		Object[] satir = new Object[7];
		String kategori = "Teknoloji/Laptop";
		urunTabloModel.setRowCount(0);
		try {
			while(AramaSonuc.next()) {
				satir[0] = AramaSonuc.getInt("urun_no");
				satir[1] = AramaSonuc.getString("urun_barkod"); //string yeterli olabilir
				satir[2] = AramaSonuc.getString("urun_ad");
				satir[3] = kategori;
				satir[4] = AramaSonuc.getInt("urun_kalan_adet");
				satir[5] = String.format("%,.2f", AramaSonuc.getDouble("urun_satis_fiyat"))+'\u20ba';
				urunTabloModel.addRow(satir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private JLabel[] ikiNoktaAyracForOzellik;
	private String[] baslik;
	//----------------------BUTONLAR-----------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		//-------------------------URUN EKLE----------------------------
		if(e.getSource()==urunEkleBut) {
			urunTabloPanel.setVisible(false);
			urunGoruntulePanel.setVisible(false);
			urunCikarBut.setEnabled(false); //kazayý engelleme
			urunGoruntuleBut.setEnabled(false); // kazayý engelleme
			urunEklePanel.setVisible(true);
			arama.setVisible(false); araBut.setVisible(false);
		}
		if(e.getSource()==ekleIptal) {
			urunEklePanel.setVisible(false);
			urunGoruntulePanel.setVisible(false);
			tabloCek(); // deneme
			arama.setForeground(Color.gray);
				arama.setHorizontalAlignment(JLabel.CENTER);
					arama.setText("Barkod - Ad"); araBut.setEnabled(false);
						arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
			arama.setVisible(true); araBut.setVisible(true);
			urunTabloPanel.setVisible(true);
			for(int i=0; i<6; i++) {
				ekleGirdiler[i].setText("");
			}
			ekleGirdiler[3].setText("Örnek: 1322.55");
			ekleGirdiler[4].setText("Örnek: 2624.81");
			fotoLbl.setIcon(noImage);
		}
		if(e.getSource()==fotoEkle) {
			int ekleKod=0;
			fotoInput = fotoInputCek();	
			fotoToLbl(ekleKod);
		}
		
		//---------COMBO BOX 1
		if(e.getSource()==kategoriBox) {
			ustKategoriNo=0;
			altKategoriNo=0;
			kategoriNo=0;
			int altKategoriVarMi = 0;
//			System.out.println(kategoriBox.getSelectedItem().toString()); 
			String kategori_sql = "SELECT * FROM zprojefinal.ust_kategori_basliklari where ust_kategori_ad = '"+kategoriBox.getSelectedItem().toString()+"'";
			ResultSet dbVeriKategori = DbBaglanti.sorgula(kategori_sql);
			
			try {
				while(dbVeriKategori.next()) {
					for(int i=0; i<8; i++) {
						String ic = new String("ust_kategori_baslik"+(i+1));
						yeniUrunOzellikTanim[i].setText(dbVeriKategori.getString(ic));
					}
					ustKategoriNo = dbVeriKategori.getInt("ust_kategori_basliklari_no");
					kategoriNo = 1000000 + (ustKategoriNo*1000);
					altKategoriVarMi = dbVeriKategori.getInt("alt_kategori_mevcut");
				}
//				System.out.println(kategoriNo); //TEST
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if(altKategoriVarMi==1) {
				String altKategori_sql = "SELECT * FROM zprojefinal.kategori_basliklari where ust_kategori_no = '"+ustKategoriNo+"'";
				ResultSet dbVeriAltKategori = DbBaglanti.sorgula(altKategori_sql);
				kategori2Box.removeAllItems();
				kategori2Box.addItem("Seçim Yapýnýz");
//				kategori2Box.setSelectedIndex(0); //gerek yok
				try {
					while(dbVeriAltKategori.next()) {
						kategori2Box.addItem(dbVeriAltKategori.getString("kategori_adi"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if(kategori2Box.getItemCount()==0) {
					kategori2Box.setVisible(false);
				} else kategori2Box.setVisible(true);
			} else { kategori2Box.setVisible(false); kategori2Box.removeAllItems(); }
			

			baslik = new String[8];
			for(int i=0; i<8; i++) {
				if(kategoriBox.getSelectedIndex()!=0) {
					try {
						 if(!yeniUrunOzellikTanim[i].getText().replaceAll("\\W", "").equals("")) {		 
							 ikiNoktaAyracForOzellik[i].setText(":");
							 ekleOzellikGirdiler[i].setVisible(true);
							 baslik[i] = yeniUrunOzellikTanim[i].getText();
						 } else {ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false);} 
					} catch (Exception e2) {
//						e2.printStackTrace();
						ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false);
					}
				} else {ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false); yeniUrunOzellikTanim[i].setText("");} 
			}
			
			if(kategoriBox.getSelectedIndex()!=0) {
				kategoriBox.setEnabled(false);
			}
			
		}
		
		
		//---------COMBO BOX 2
		if(e.getSource()==kategori2Box) {
			//SELECT * FROM zprojefinal.kategori_basliklari where ust_kategori_no = '1';
			if(kategori2Box.getSelectedIndex()==0 || kategori2Box==null) { //stoper
//				System.out.println("beklemede");
			} else {
				String kategoriAdiNe = "";
				try {
					kategoriAdiNe = kategori2Box.getSelectedItem().toString();
				} catch (Exception e2) {
					System.out.println("Seçim boþ");
				}
				
				
				String kategori_sql = "SELECT * FROM kategori_basliklari where kategori_adi = '"+kategoriAdiNe+"'";
				ResultSet dbVeri3 = DbBaglanti.sorgula(kategori_sql);
				
				try {
					while(dbVeri3.next()) {
						
						altKategoriNo = dbVeri3.getInt("kategori_no");
						kategoriNo += altKategoriNo;
						
					}
//	/*TEST*/		System.out.println("Alt dahil = "+kategoriNo); //TEST
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				baslik = Kategori.altInfo(baslik, altKategoriNo);
				for(int i=0; i<8; i++) {
					yeniUrunOzellikTanim[i].setText(baslik[i]);
				}
				
				for(int i=0; i<8; i++) {
					if(kategori2Box.getSelectedIndex()!=0) {
						try {
							 if(!yeniUrunOzellikTanim[i].getText().replaceAll("\\W", "").equals("")) {		 
								 ikiNoktaAyracForOzellik[i].setText(":");
								 ekleOzellikGirdiler[i].setVisible(true);
							 } else {ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false);} 
						} catch (Exception e2) {
//							e2.printStackTrace();
							ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false);
						}
					} else {ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false); yeniUrunOzellikTanim[i].setText("");} 
				}
				
				if(kategori2Box.getSelectedIndex()!=0) {
					kategori2Box.setEnabled(false);
				}
				
			}
			
			

		}
		
		if(e.getSource()==ekleOnay) {
			int basarili = Urunler.ekle(ekleGirdiler[1].getText(), ekleGirdiler[2].getText(), ekleGirdiler[3].getText(), 
							ekleGirdiler[4].getText(), ekleGirdiler[5].getText(), fotoInput, kategoriNo, ekleOzellikGirdiler[0].getText(), 
							ekleOzellikGirdiler[1].getText(), ekleOzellikGirdiler[2].getText(), ekleOzellikGirdiler[3].getText(), 
							ekleOzellikGirdiler[4].getText(), ekleOzellikGirdiler[5].getText(), ekleOzellikGirdiler[6].getText(), 
							ekleOzellikGirdiler[7].getText());
			if(basarili==1) {
				for(int i=0; i<6; i++) {
					ekleGirdiler[i].setText("");
				}
				ekleGirdiler[3].setText("Örnek: 1322.55");
				ekleGirdiler[4].setText("Örnek: 2624.81");
				fotoLbl.setIcon(noImage);
				try {
					fotoInput = new FileInputStream("Images/NoimagetransparanSiyah350x262.png");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				urunCikarBut.setEnabled(false);
				urunGoruntuleBut.setEnabled(false);
				urunEklePanel.setVisible(false);
				urunGoruntulePanel.setVisible(false);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
				tabloCek();
				arama.setForeground(Color.gray); 
					arama.setHorizontalAlignment(JLabel.CENTER);
						arama.setText("Barkod - Ad"); araBut.setEnabled(false);
							arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
				arama.setVisible(true); araBut.setVisible(true);
				urunTabloPanel.setVisible(true);
			}
		}
		
		//----------------------ÜRÜN SÝLME-------------------------
		if(e.getSource()==urunCikarBut) {
			String[] cevap = {"Evet","Hayýr"};
			int answer = JOptionPane.showOptionDialog(
					null, //component
					secilenUrunNo+"'nolu ürünü silmek istediðinize emin misiniz?", //message 
					"Depom Sepette", //title
					JOptionPane.YES_NO_CANCEL_OPTION, //option type 
					JOptionPane.WARNING_MESSAGE, //message type
					null, // icon
					cevap, // option da yazacaklar 0-1-2 olarak deðiþtirir
					0);
			if(answer == 0) {
				//DELETE FROM `zprojefinal`.`urunler` WHERE (`urun_no` = '18');
				String urunSil_sql = "DELETE FROM `zprojefinal`.`urunler` WHERE (`urun_no` = '"+secilenUrunNo+"')";
				DbBaglanti.sil(urunSil_sql);
				tabloCek();
				secilenUrunNo = 0;
				urunCikarBut.setEnabled(false);
				urunGoruntuleBut.setEnabled(false);
			}
		}
		
		//------------------GORUNTULEME--------------------------
		if(e.getSource()==urunGoruntuleBut) {
			urunTabloPanel.setVisible(false);
			urunEklePanel.setVisible(false);
			arama.setVisible(false); araBut.setVisible(false);
			urunCikarBut.setEnabled(false); //kazayý engelleme
			urunGoruntuleBut.setEnabled(false); // kazayý engelleme

			Urunler goruntulenecekUrun = new Urunler(new BigInteger(urunTabloModel.getValueAt(secilenUrunTabloSirasi, 1).toString()));
			
			goruntuTanim[0].setText("<HTML><U>#"+goruntulenecekUrun.getUrunNo()+" nolu Ürün Bilgileri</U></HTML>");
			urunBilgi[0].setText("Kategori");
			urunBilgi[1].setText(""+goruntulenecekUrun.getUrunBarkod());
			urunBilgi[2].setText(goruntulenecekUrun.getUrunAd());
			urunBilgi[3].setText(""+goruntulenecekUrun.getUrunAlisFiyat());
			urunBilgi[4].setText(""+goruntulenecekUrun.getUrunSatisFiyat()); // stok otomatik atanacak
			urunBilgi[5].setText(""+goruntulenecekUrun.getUrunKalanAdet());
			urunBilgi[6].setText(goruntulenecekUrun.getUrunTedarikci());
			urunBilgi[7].setText(goruntulenecekUrun.getUrunIlkEklemeTarih()+"");
			if(goruntulenecekUrun.getUrunSonAlisTarih().toString().equals("0001-01-01 00:00:00.0")) {
				urunBilgi[8].setText("Hiç Alým Olmadý");
			} else urunBilgi[8].setText(""+goruntulenecekUrun.getUrunSonAlisTarih());
			
			if(goruntulenecekUrun.getUrunSonSatisTarih().toString().equals("0001-01-01 00:00:00.0")) {
				urunBilgi[9].setText("Hiç Satým Olmadý");
			} else urunBilgi[9].setText(""+goruntulenecekUrun.getUrunSonSatisTarih());
			
			urunGuncellemeGirdi[0].setText("Kategori");
			urunGuncellemeGirdi[1].setText(""+goruntulenecekUrun.getUrunBarkod());
			urunGuncellemeGirdi[2].setText(goruntulenecekUrun.getUrunAd());
			urunGuncellemeGirdi[3].setText(""+goruntulenecekUrun.getUrunAlisFiyat());
			urunGuncellemeGirdi[4].setText(""+goruntulenecekUrun.getUrunSatisFiyat());
			urunGuncellemeGirdi[5].setText(goruntulenecekUrun.getUrunTedarikci());
			
			Image sImage = goruntulenecekUrun.getUrunFoto().getImage();//imageicon u image e kaydetti
			Image yeniBoyutImage = sImage.getScaledInstance(350, 262, Image.SCALE_SMOOTH); //image yeniden boyutlandýrma ve yeni kaydetme
			ImageIcon urunFoto = new ImageIcon(yeniBoyutImage);// yeniden boyutlandýrýlmýþ image i image icona kaydetti
			goruntuleFotoLbl.setIcon(urunFoto);
			
			
			urunGoruntulePanel.setVisible(true);
		}
		
		if(e.getSource()==goruntuleGeri) {
			urunGoruntulePanel.setVisible(false);
			urunEklePanel.setVisible(false);
			urunCikarBut.setEnabled(false); //kazayý engelleme
			urunGoruntuleBut.setEnabled(false); // kazayý engelleme
			arama.setForeground(Color.gray); 
				arama.setHorizontalAlignment(JLabel.CENTER);
					arama.setText("Barkod - Ad");araBut.setEnabled(false);
						arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
			arama.setVisible(true); araBut.setVisible(true);
			urunTabloPanel.setVisible(true);
		}
		
		if(e.getSource()==urunGuncelleButs[0]) { //fotoðrafý güncelle
			int guncelleKod = 1;
			fotoGuncelleInput = fotoInputCek();
			if(dosyaEkleOnay==0) {
				int basarili = new Urunler(new BigInteger(urunTabloModel.getValueAt(secilenUrunTabloSirasi, 1).toString())).setUrunFoto(fotoGuncelleInput);
				if(basarili==1) {
					JOptionPane.showMessageDialog(null, "Fotoðraf güncellemesi baþarýlý.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
					fotoToLbl(guncelleKod);
				} else JOptionPane.showMessageDialog(null, "Ürünün fotoðrafý güncellenemedi.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==urunGuncelleButs[1]) { //urunu güncelle
			goruntuleGeri.setVisible(false);
			guncellemeOnayIptal[0].setVisible(true);
			guncellemeOnayIptal[1].setVisible(true);
			urunCikarBut.setEnabled(false); //kazayý engelleme
			urunGoruntuleBut.setEnabled(false); // kazayý engelleme
			for(int i=0; i<5; i++){
				urunBilgi[i].setVisible(false);
			}
			urunBilgi[6].setVisible(false);
			
			for(int i=0; i<6; i++) {
				 urunGuncellemeGirdi[i].setVisible(true);
			}
			
		}
		if(e.getSource()==guncellemeOnayIptal[0]) { //onayla
			int basarili = Urunler.degistir(urunBilgi[1].getText(), urunGuncellemeGirdi[1].getText(), urunGuncellemeGirdi[2].getText(), 
											urunGuncellemeGirdi[3].getText(), urunGuncellemeGirdi[4].getText(), urunGuncellemeGirdi[5].getText());
			if(basarili == 1) {
				urunGoruntulePanel.setVisible(false);
				urunEklePanel.setVisible(false);
				urunCikarBut.setEnabled(false); //kazayý engelleme
				urunGoruntuleBut.setEnabled(false); // kazayý engelleme
				
				guncellemeOnayIptal[0].setVisible(false);
				guncellemeOnayIptal[1].setVisible(false);
				goruntuleGeri.setVisible(true);
				for(int i=0; i<5; i++){
					urunBilgi[i].setVisible(true);
				}
				urunBilgi[6].setVisible(true);
				for(int i=0; i<6; i++) {
					 urunGuncellemeGirdi[i].setVisible(false);
				}
				
				arama.setForeground(Color.gray); 
					arama.setHorizontalAlignment(JLabel.CENTER);
						arama.setText("Barkod - Ad"); araBut.setEnabled(false);
							arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
				arama.setVisible(true); araBut.setVisible(true);
				tabloCek();
				urunTabloPanel.setVisible(true);
			}
		}
		if(e.getSource()==guncellemeOnayIptal[1]) { //güncelleme iptal
			guncellemeOnayIptal[0].setVisible(false);
			guncellemeOnayIptal[1].setVisible(false);
			goruntuleGeri.setVisible(true);
			for(int i=0; i<5; i++){
				urunBilgi[i].setVisible(true);
			}
			urunBilgi[6].setVisible(true);
			for(int i=0; i<6; i++) {
				 urunGuncellemeGirdi[i].setVisible(false);
			}
		}
		//---------------ARAMA-------------------------
		if(e.getSource()==araBut) {
			aramaReaksiyon();
		}
		
	}

	
	//-----------------------MOUSE-------------------------
	private int secilenUrunTabloSirasi;
	private int secilenUrunNo;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==urunTablo) {
			secilenUrunNo = (int) urunTabloModel.getValueAt(urunTablo.getSelectedRow(), 0);
			secilenUrunTabloSirasi = urunTablo.getSelectedRow();
//			System.out.println(secilenUrunTabloSirasi);
			urunCikarBut.setEnabled(true);
			urunGoruntuleBut.setEnabled(true);
		}
		
		if(e.getSource()==arama) {
			arama.setFocusable(true);
			arama.grabFocus();
			if(arama.getText().equals("Barkod - Ad")) {
				arama.setForeground(Color.black);
				arama.setFont(new Font(null, Font.CENTER_BASELINE, 13));
				arama.setHorizontalAlignment(JLabel.LEFT);
				arama.setText("");
				arama.grabFocus();
			}
			araBut.setEnabled(true);
		}
		
		if(e.getSource()==kategoriBox) {
			if(kategoriBox.isEnabled()==false) {
				for(int i=0; i<8; i++) {
					ekleOzellikGirdiler[i].setText("");
				}
				kategoriBox.setSelectedIndex(0);
				kategoriBox.setEnabled(true);
			}
		}
		
		if(e.getSource()==kategori2Box) {
			if(kategori2Box.isEnabled()==false) {
				for(int i=0; i<8; i++) {
					yeniUrunOzellikTanim[i].setText("");
					ekleOzellikGirdiler[i].setText("");
					if(kategori2Box.getSelectedIndex()!=0) {
						try {
							 if(!yeniUrunOzellikTanim[i].getText().replaceAll("\\W", "").equals("")) {		 
								 ikiNoktaAyracForOzellik[i].setText(":");
								 ekleOzellikGirdiler[i].setVisible(true);
							 } else {ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false);} 
						} catch (Exception e2) {
//							e2.printStackTrace();
							ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setVisible(false);
							ekleOzellikGirdiler[i].setText("");
						}
					} else {ikiNoktaAyracForOzellik[i].setText(""); ekleOzellikGirdiler[i].setText(""); ekleOzellikGirdiler[i].setVisible(false); yeniUrunOzellikTanim[i].setText("");} 
				}
				kategori2Box.setSelectedIndex(0);
				kategori2Box.setEnabled(true);
				kategoriNo -= altKategoriNo; //iptal olduðu için normale dönecek
//				System.out.println("Alt dahil = "+kategoriNo); //TEST
			}
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

	
	//-----------------------KLAVYE------------------------
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
	
	//------------------CLASS SET--------------------------
	public void panelSetVisible(boolean a) {
		urunlerPanel.setVisible(a);
	}
}
