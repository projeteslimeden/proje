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
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SatisPanel implements ActionListener, KeyListener, MouseListener{
	
	private JTabbedPane satisSekme;
	private JPanel satisPanel;
	private JPanel barkodSecimPanel, elleSecimPanel;
	private JTextField barkodOku; private JButton barkodSorgulaBut;
	private JLabel stokTxt;
	private JLabel urunFiyat, adetXurunFiyat; private JButton adetOnaylaBut; private JTextField istenenAdetGirdi;
	private JLabel urunFoto;
	private JLabel urunAdi; 
	private JLabel urunKategori; 
	private JButton sepetEkleBut;
	private JButton copIconBut;
	private JLabel sepetTutar�;
	private JButton sepetOnaylaBut;
	private JTable sepetTablo;
	private JTextField[] musteriPrimGirdi = new JTextField[2];
	private JButton[] musteriPrimBut = new JButton[2];
	private JButton[] degistirBut = new JButton[2];
	private JButton sepettenCikar;
	static boolean sepetSatisDolu=false;
	
	public JPanel panel() {
		Color satisPanelColor = new Color(0x4e9d54); //0x4e9d54 koyu ye�il
		Color satisSekmeColor = new Color(0xd1f9ce); //�effaf ye�il 0xd1f9ce
		
		satisPanel = new JPanel();
		satisPanel.setBackground(satisPanelColor);
		satisPanel.setBounds(0, 0, 1030, 680);
		satisPanel.setLayout(null);
		
		barkodSecimPanel = new JPanel();
		barkodSecimPanel.setBackground(satisSekmeColor); 
		barkodSecimPanel.setLayout(null);
		barkodOku = new JTextField();
		barkodOku.setBounds(10, 10, 150, 30);
		barkodOku.setBorder(null);
		barkodOku.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		barkodOku.addKeyListener(this);
		
		barkodSorgulaBut = new JButton("Barkod Sorgula");
		barkodSorgulaBut.setBounds(10, 42, 150, 19);
		barkodSorgulaBut.setFocusable(false);
		barkodSorgulaBut.setFont(new Font(null, Font.BOLD, 12));
		barkodSorgulaBut.setBorder(new LineBorder(satisSekmeColor, 1));
		barkodSorgulaBut.addActionListener(this);
		
		urunAdi = new JLabel();
		urunAdi.setText("");  //<HTML><U>�r�n Adi:</U></HTML> �irkin duruyor kald�rd�m //Metro �ikolataaaa -->17 karakter karakter gelene g�re art�rabilir
		urunAdi.setBounds(170, 15, 700, 30);
		urunAdi.setFont(new Font("Arial", Font.BOLD, 20));
		
		urunKategori = new JLabel();
		urunKategori.setText("");  //<HTML><U>�r�n Adi:</U></HTML> �irkin duruyor kald�rd�m //Metro �ikolataaaa -->17 karakter karakter gelene g�re art�rabilir
		urunKategori.setBounds(170, 40, 700, 15);
		barkodSecimPanel.add(urunKategori);
		
		ozellik = new JLabel[8]; //8 SATIR
		for(int i=0; i<8; i++) {
			 ozellik[i] = new JLabel();
			 ozellik[i].setBounds(10, (70+(i*30)), 560, 20);
			 ozellik[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 barkodSecimPanel.add(ozellik[i]);
		}
		
		//----------------PANEL SA� TARAF---------------------------
		urunFoto = new JLabel(); //�r�n foto�raf� i�in panelde yer tahsisi //urunFotoBaslik kald�r�ld� �irkin duruyordu...
		urunFoto.setBounds(815, 3, 200, 200);
		urunFoto.setHorizontalAlignment(JLabel.CENTER);
		urunFoto.setVerticalAlignment(JLabel.CENTER);
		
		JLabel birimFiyatText = new JLabel("Birim Fiyat�:");
		birimFiyatText.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		birimFiyatText.setBounds(610, 230, 120, 40);
		birimFiyatText.setHorizontalAlignment(JLabel.RIGHT);
		urunFiyat = new JLabel(String.format("%,.2f", 0.00)+'\u20ba');//de�i�kenlere gerek yok kald�r�p direk yerine 0 de�eri elle girildi temel de�er 0 olacak
		urunFiyat.setBounds(735, 230, 120, 40);
		urunFiyat.setOpaque(true); urunFiyat.setBackground(Color.white);
		urunFiyat.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		urunFiyat.setHorizontalAlignment(JLabel.RIGHT);
		
		
		JLabel toplamFiyatText = new JLabel("Toplam Fiyat:");
		toplamFiyatText.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		toplamFiyatText.setBounds(600, 272, 130, 40);
		toplamFiyatText.setHorizontalAlignment(JLabel.RIGHT);
		adetXurunFiyat = new JLabel(String.format("%,.2f", 0.00)+'\u20ba'); //de�i�kenlere gerek yok kald�r�p direk yerine 0 de�eri elle girildi temel de�er 0 olacak
		adetXurunFiyat.setBounds(735, 272, 120, 40);
		adetXurunFiyat.setOpaque(true); adetXurunFiyat.setBackground(Color.white);
		adetXurunFiyat.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		adetXurunFiyat.setHorizontalAlignment(JLabel.RIGHT);
		
		stokTxt = new JLabel("Kalan Stok Adet: "+0); //de�i�kenlere gerek yok kald�r�p direk yerine 0 de�eri elle girildi temel de�er 0 olacak
		stokTxt.setBounds(860, 202, 130, 35);
		
		JLabel istenenAdet = new JLabel("�stenen Adet      :");
		istenenAdet.setBounds(860, 232, 130, 30);
		istenenAdetGirdi = new JTextField(3); //art� 1 eksi 1 butonlar� konulabilir ama gerek yok �imdilik
		istenenAdetGirdi.setText("0");
		istenenAdetGirdi.setBounds(961, 232, 25, 30);
		istenenAdetGirdi.setBorder(null);
		istenenAdetGirdi.setHorizontalAlignment(JTextField.CENTER);
		istenenAdetGirdi.addKeyListener(this);
		istenenAdetGirdi.setEnabled(false);
		
		ImageIcon artiOnay = new ImageIcon("Images/addicon30x30.png"); //adetonayla buton logosu de�i�tirilebilir yada rengi
		adetOnaylaBut = new JButton(artiOnay);
		adetOnaylaBut.setBounds(986, 232, 30, 30);
		adetOnaylaBut.setOpaque(false);
		adetOnaylaBut.setContentAreaFilled(true);
		adetOnaylaBut.setBorderPainted(false);
		adetOnaylaBut.setBackground(new Color(0x858683));
		adetOnaylaBut.addActionListener(this);
		
		ImageIcon sepetEkleIcon = new ImageIcon("Images/sepetekle30x30.png"); //sepete ekle butonu de�i�tirilmeli sepet �st�nde + olmal�
		JLabel sepetEkleTxt = new JLabel();
		sepetEkleTxt.setIcon(sepetEkleIcon);
		sepetEkleTxt.setText("SEPETE EKLE");
		sepetEkleTxt.setHorizontalAlignment(JLabel.LEFT);
		sepetEkleTxt.setIconTextGap(1);
		sepetEkleBut = new JButton();
		sepetEkleBut.add(sepetEkleTxt);
		sepetEkleBut.setBounds(860, 272, 155, 40);
		sepetEkleBut.addActionListener(this);
		sepetEkleBut.setEnabled(false); //�r�n bulmadan sepete ekleme yapmas�n
		
		
		
		
		barkodSecimPanel.add(birimFiyatText);
		barkodSecimPanel.add(urunFiyat);
		barkodSecimPanel.add(toplamFiyatText);
		barkodSecimPanel.add(adetXurunFiyat);
		barkodSecimPanel.add(urunAdi);
		barkodSecimPanel.add(barkodOku);
		barkodSecimPanel.add(barkodSorgulaBut);

		barkodSecimPanel.add(urunFoto);
		barkodSecimPanel.add(sepetEkleBut);
		barkodSecimPanel.add(stokTxt);
		barkodSecimPanel.add(istenenAdet);
		barkodSecimPanel.add(istenenAdetGirdi);
		barkodSecimPanel.add(adetOnaylaBut);
		
		
		//-----------ELLE SE��M SEKMES�N�N PANEL�----------
		elleSecimPanel = new JPanel();
		elleSecimPanel.setBackground(satisSekmeColor); //0x4e9d54 koyu ye�il
		elleSecimPanel.setLayout(null);
		
		//-----------SATI� SEKMES�------------
		UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(-2, -1, -1, -1));
		UIManager.put("TabbedPane.selected", new Color(0xd1f9ce));
		UIManager.put("TabbedPane.focus", satisPanelColor);
		satisSekme = new JTabbedPane();
		satisSekme.add("Barkoddan Se�im", barkodSecimPanel);
		satisSekme.add("Elle Se�im", elleSecimPanel);
		satisSekme.setBounds(5, 0, 1020, 340); // paneli k���lt arka panel arka plan tam olsun
		satisSekme.setBorder(null);
		satisSekme.setBackground(Color.gray);
		
		
		//------------SEPET------------------------
		sepetTabloModel = new DefaultTableModel();
		Object[] kolonIsimleri = {"#","�r�n Barkodu","�r�n Ad�","Al�nan Adet","Birim Fiyat","Toplam Fiyat"};
		sepetTabloModel.setColumnIdentifiers(kolonIsimleri);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 345, 830, 330);
		sepetTablo = new JTable();
		sepetTablo.setModel(sepetTabloModel);
		sepetTablo.addMouseListener(this);
//		sepetTablo.setBounds(512, 181, 310, 331); // bu de�erler gerekli mi ara�t�r�ls�n scroll a entegre oldu�u i�in gereksiz veya hatal� olabilir
		
		sepetTablo.getColumnModel().getColumn(0).setPreferredWidth(1);
		sepetTablo.getColumnModel().getColumn(1).setPreferredWidth(15);
		sepetTablo.getColumnModel().getColumn(2).setPreferredWidth(120);
		sepetTablo.getColumnModel().getColumn(3).setPreferredWidth(1);
//		sepetTablo.getColumnModel().getColumn(4).setPreferredWidth(7);
//		sepetTablo.getColumnModel().getColumn(5).setPreferredWidth(10);
		
		sepetTablo.getColumnModel().getColumn(0).setResizable(false);
		sepetTablo.getColumnModel().getColumn(1).setResizable(false);
		sepetTablo.getColumnModel().getColumn(2).setResizable(false);
		sepetTablo.getColumnModel().getColumn(3).setResizable(false);
		sepetTablo.getColumnModel().getColumn(4).setResizable(false);
		sepetTablo.getColumnModel().getColumn(5).setResizable(false);
		
		DefaultTableCellRenderer hucreOrtala = new DefaultTableCellRenderer(); hucreOrtala.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer hucreSag = new DefaultTableCellRenderer(); hucreSag.setHorizontalAlignment(JLabel.RIGHT);
		sepetTablo.getColumnModel().getColumn(0).setCellRenderer(hucreOrtala);
		sepetTablo.getColumnModel().getColumn(3).setCellRenderer(hucreOrtala);
		sepetTablo.getColumnModel().getColumn(4).setCellRenderer(hucreSag);
		sepetTablo.getColumnModel().getColumn(5).setCellRenderer(hucreSag);
		scroll.setViewportView(sepetTablo);
		
		JLabel sepetTutar�Txt = new JLabel("<HTML><U>SEPET TUTARI</U></HTML>"); //alt�n� �iz ->"<HTML><U>YOUR TEXT HERE</U></HTML>"
		sepetTutar�Txt.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		sepetTutar�Txt.setHorizontalAlignment(JLabel.CENTER);
		sepetTutar�Txt.setBounds(841, 550, 120, 30);
		sepetTutar�Txt.setOpaque(true);sepetTutar�Txt.setBackground(Color.cyan); //alan testi i�in -di ama fena durmuyor �imdilik kals�n
		sepetTutar� = new JLabel(String.format("%,.2f", 0.00)+'\u20ba'); //de�i�kenlere gerek yok kald�r�p direk yerine 0 de�eri elle girildi temel de�er 0 olacak
		sepetTutar�.setHorizontalAlignment(JLabel.RIGHT);
		sepetTutar�.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		sepetTutar�.setOpaque(true); sepetTutar�.setBackground(Color.cyan); //alan testi i�in -di ama fena durmuyor �imdilik kals�n
		sepetTutar�.setBounds(841, 574, 120, 30);
		
		ImageIcon copIcon = new ImageIcon("Images/newcop50x61.png");
		copIconBut = new JButton(copIcon);
		copIconBut.setBounds(965, 544, 50, 61);
		copIconBut.setOpaque(false);
		copIconBut.setContentAreaFilled(true);
		copIconBut.setBorderPainted(false);
		copIconBut.setBackground(new Color(0x858683));
		copIconBut.addActionListener(this);
		copIconBut.setBorder(null);
		copIconBut.setFocusable(false);
		
		ImageIcon sepetOnayIcon = new ImageIcon("Images/sepetOnay50x50.png");
		JLabel sepetOnaylaTxt = new JLabel();
		sepetOnaylaTxt.setIcon(sepetOnayIcon);
		sepetOnaylaTxt.setText("SEPET� ONAYLA");
		sepetOnaylaTxt.setFont(new Font("Arial", Font.CENTER_BASELINE, 11));
		sepetOnaylaBut = new JButton();
		sepetOnaylaBut.add(sepetOnaylaTxt);
		sepetOnaylaBut.setBounds(841, 614, 183, 60);
		sepetOnaylaBut.setFocusable(false);
		sepetOnaylaBut.setHorizontalTextPosition(JLabel.RIGHT);
		sepetOnaylaBut.addActionListener(this);
		
		
		//-------------------M��TER� - PR�M-----------------------------
		JLabel[] musteriPrim = new JLabel[2];
		ImageIcon[] araIcon = new ImageIcon[2];
		ImageIcon[] changeIcon = new ImageIcon[2];
		musteriPrimGirdi = new JTextField[2];
		musteriPrimBut = new JButton[2];
		degistirBut = new JButton[2];
		for(int i=0; i<2; i++) {
			musteriPrim[i] = new JLabel();
			musteriPrim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
			musteriPrim[i].setBounds(841, (345+(i*80)), 180, 40);
			
			musteriPrimGirdi[i] = new JTextField();
			musteriPrimGirdi[i].setBounds(840, (380+(i*80)), 140, 40);
			musteriPrimGirdi[i].setBorder(null);
			musteriPrimGirdi[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
			musteriPrimGirdi[i].setBackground(new Color(0xedf5ee));
			musteriPrimGirdi[i].addKeyListener(this);
			
			araIcon[i] = new ImageIcon("Images/newsearchicon40x40.png");
			changeIcon[i] = new ImageIcon("Images/changeicon40x40.png");
			
			musteriPrimBut[i] = new JButton(araIcon[i]);
			musteriPrimBut[i].setBounds(982, (380+(i*80)), 41, 41);
			musteriPrimBut[i].setFocusable(false);
			musteriPrimBut[i].addActionListener(this);
			musteriPrimBut[i].setBorder(null);
			musteriPrimBut[i].setOpaque(false);
			musteriPrimBut[i].setContentAreaFilled(true);
			musteriPrimBut[i].setBorderPainted(false);
			musteriPrimBut[i].setBackground(satisPanelColor);
			
			degistirBut[i] = new JButton(changeIcon[i]);
			degistirBut[i].setBounds(982, (380+(i*80)), 41, 41);
			degistirBut[i].setFocusable(false);
			degistirBut[i].addActionListener(this);
			degistirBut[i].setBorder(null);
			degistirBut[i].setOpaque(false);
			degistirBut[i].setContentAreaFilled(true);
			degistirBut[i].setBorderPainted(false);
			degistirBut[i].setBackground(satisPanelColor);
			degistirBut[i].setVisible(false);
			
			satisPanel.add(musteriPrim[i]);
			satisPanel.add(musteriPrimGirdi[i]);
			satisPanel.add(musteriPrimBut[i]);
			satisPanel.add(degistirBut[i]);
		}
		musteriPrim[0].setText("Kay�tl� M��teri"); musteriPrim[1].setText("Sat�� Sorumlusu:");
		
		ImageIcon silIcon = new ImageIcon("Images/delete20x20.png"); //18x18
		sepettenCikar = new JButton(silIcon);
		sepettenCikar.setEnabled(false);
		sepettenCikar.setBounds(841, 520, 100, 20);
		sepettenCikar.setText("��kar");
		sepettenCikar.setFocusable(false);
		sepettenCikar.setBorder(null);
		sepettenCikar.addActionListener(this);
		
		satisPanel.add(sepettenCikar);
		satisPanel.add(copIconBut);
		satisPanel.add(sepetTutar�Txt);
		satisPanel.add(sepetTutar�);
		satisPanel.add(scroll);
		satisPanel.add(sepetOnaylaBut);
		satisPanel.add(satisSekme);
		
		return satisPanel;
	}
	

	
	private DefaultTableModel sepetTabloModel;
	private int istenenAdet = 1;//adetonayla i�inde ama sepetekle i�in laz�m
	private int sepetNo=1;
	private double sepetToplamTutar = 0;
	private Urunler seciliUrun;
	private JLabel[] ozellik;
	private void barkodSorgulaReaksiyon() {
		boolean hata=false;
		BigInteger barkod_int = new BigInteger("0"); // D��ar�dayd� i�eri al�nd�
		try {
			barkod_int = new BigInteger(barkodOku.getText());
		} catch (Exception e2) {
//			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "L�TFEN SADECE NUMARA KULLANINIZ", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
			barkodOku.setText("");
			hata=true;
		}
		
		if(hata==false) {
			if(Urunler.barkodKontrol(barkod_int)==false) {
				
				JOptionPane.showMessageDialog(null, "Bu barkoda �r�n tan�ml� de�il.", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
				sepetEkleBut.setEnabled(false);
				istenenAdetGirdi.setEnabled(false); istenenAdetGirdi.setText("0"); istenenAdet=0;
				
				for(int i=0; i<8; i++) {
					ozellik[i].setText("");
				}
				urunKategori.setText("");
				urunFoto.setIcon(null);
				urunAdi.setText("");  //"<HTML><U>YOUR TEXT HERE</U></HTML>" //<HTML><U>�r�n Adi:</U></HTML> �irkin duruyor kald�rd�m
				urunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); //birimFiyat de�i�keni ��kar�ld�
				adetXurunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); // birimFiyat de�i�keni ��kar�ld�
				istenenAdetGirdi.setText("0"); istenenAdet = 0;
				stokTxt.setText("Kalan Stok Adet: "+ 0);
			} else {
				seciliUrun = new Urunler(barkod_int);

				
				for(int i=0; i<8; i++) {
					ozellik[i].setText(seciliUrun.getOzellikKategorili()[i]);
				}
				urunKategori.setText(seciliUrun.getKategoriAd());
				
				Image sImage = seciliUrun.getUrunFoto().getImage();//imageicon u image e kaydetti
				Image yeniBoyutImage = sImage.getScaledInstance(200, 150, Image.SCALE_SMOOTH); //image yeniden boyutland�rma ve yeni kaydetme
				ImageIcon gelenUrunFoto = new ImageIcon(yeniBoyutImage);// yeniden boyutland�r�lm�� image i image icona kaydetti
				urunFoto.setIcon(gelenUrunFoto);
				
				urunAdi.setText("<HTML><U>�r�n Adi:</U> "+seciliUrun.getUrunAd()+"</HTML>");  //"<HTML><U>YOUR TEXT HERE</U></HTML>"
				urunFiyat.setText(String.format("%,.2f", seciliUrun.getUrunSatisFiyat())+'\u20ba'); //birimFiyat de�i�keni ��kar�ld�
				adetXurunFiyat.setText(String.format("%,.2f", seciliUrun.getUrunSatisFiyat())+'\u20ba'); // birimFiyat de�i�keni ��kar�ld�
				istenenAdetGirdi.setText("1"); istenenAdet = 1;
				stokTxt.setText("Kalan Stok Adet: "+seciliUrun.getUrunKalanAdet());	
				sepetEkleBut.setEnabled(true);
				istenenAdetGirdi.setEnabled(true);
			}
		}	
	}
	
	private void adetOnayReaksiyon() {
		if(istenenAdetGirdi.getText().equals("")) {
			istenenAdet=0; istenenAdetGirdi.setText("0");
		} else {
			try {
				istenenAdet = Integer.parseInt(this.istenenAdetGirdi.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "L�TFEN TAM SAYI B�R DE�ER G�R�N", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
				istenenAdet=0; istenenAdetGirdi.setText("0");
//				e2.printStackTrace();
			}
			if(istenenAdet==0) {
				sepetEkleBut.setEnabled(false);
			}else if(istenenAdet<0) {
				istenenAdet=0; istenenAdetGirdi.setText("0");
				sepetEkleBut.setEnabled(false);
			} else {
				sepetEkleBut.setEnabled(true); //secili �r�n stoktan d��me
			}
			
			if(istenenAdet <= seciliUrun.getUrunKalanAdet())
				adetXurunFiyat.setText(String.format("%,.2f", (istenenAdet * seciliUrun.getUrunSatisFiyat()))+'\u20ba'); //adetxbirimfiyat de�i�keni ��kar�ld�
			else {
				JOptionPane.showMessageDialog(null, "�STENEN ADET KALAN ADETTEN FAZLA - YETERS�Z STOK", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
				istenenAdet=0; istenenAdetGirdi.setText("0");
				adetXurunFiyat.setText(String.format("%,.2f", (istenenAdet * seciliUrun.getUrunSatisFiyat()))+'\u20ba');
				sepetEkleBut.setEnabled(false);
			}
		}
		
		
	}
	
	private Musteri satinAlanMusteri;
	private void musteriSecReaksiyon() {
		int noDonusum = 0;
		int select = 0;
		try {
			noDonusum = Integer.parseInt(musteriPrimGirdi[0].getText()); //burda int uzunlu�undan faydalan�yoruz  2.147.483.647 m��teri no int destekliyor
			select = 0;											//ayr�ca telefon no + 90 123 456 78 90 int de�eri her zaman ge�er tr'de
		} catch (Exception e2) {
			select = 1;
		}
		
		if(select == 0) {
			satinAlanMusteri = new Musteri(noDonusum);
		} else {
			satinAlanMusteri = new Musteri(musteriPrimGirdi[0].getText());
		}
		
		if(satinAlanMusteri.getMusteriAdSoyad() != null) {
			musteriPrimGirdi[0].setText(satinAlanMusteri.getMusteriAdSoyad());	//m��teri bulunamazsa null de�eri geri d�ner ama girdiye yans�maz
			musteriPrimGirdi[0].setEnabled(false);
			musteriPrimBut[0].setVisible(false);
			degistirBut[0].setVisible(true);
//			System.out.println("E�er m��teri bulunduysa --> "+satinAlanMusteri);
		}	else {
			satinAlanMusteri = null;
//			System.out.println("E�er m��teri bulunmazsa --> "+satinAlanMusteri);
		}
	}
	
	private Kullanici primAlanCalisan;
	private String calisanSaptayici;
	private void calisanSecReaskiyon() {
		int noDonusum = 0;
		int select = 0;
		try {
			noDonusum = Integer.parseInt(musteriPrimGirdi[1].getText()); //burda int yada direk id
			select = 0;											
		} catch (Exception e2) {
			select = 1;
		}
		
		if(select == 0) {
			primAlanCalisan = new Kullanici(noDonusum);
			calisanSaptayici = Integer.toString(noDonusum);
		} else {
			primAlanCalisan = new Kullanici(musteriPrimGirdi[1].getText());
			calisanSaptayici = musteriPrimGirdi[1].getText();
		}
		
		if(primAlanCalisan.getKullaniciAdSoyad() != null) {
			musteriPrimGirdi[1].setText(primAlanCalisan.getKullaniciAdSoyad());	//m��teri bulunamazsa null de�eri geri d�ner ama girdiye yans�maz
			musteriPrimGirdi[1].setEnabled(false);
			musteriPrimBut[1].setVisible(false);
			degistirBut[1].setVisible(true);
//			System.out.println("E�er m��teri bulunduysa --> "+satinAlanMusteri);
		}	else {
			primAlanCalisan = null;
			calisanSaptayici = null;
//			System.out.println("E�er m��teri bulunmazsa --> "+satinAlanMusteri);
		}
	}
	
	private void musteriDegistirReaksiyon() {
		satinAlanMusteri = null;
//		System.out.println("De�i�tir butonla bo�alt�ld� yeni m��teri ara --> "+satinAlanMusteri);
		degistirBut[0].setVisible(false);
		musteriPrimBut[0].setVisible(true);
		musteriPrimGirdi[0].setEnabled(true);
		musteriPrimGirdi[0].setText("");
	}
	private void calisanDegistirReaksiyon() {
		primAlanCalisan = null;
		calisanSaptayici = null;
//		System.out.println("De�i�tir butonla bo�alt�ld� yeni m��teri ara --> "+satinAlanMusteri);
		degistirBut[1].setVisible(false);
		musteriPrimBut[1].setVisible(true);
		musteriPrimGirdi[1].setEnabled(true);
		musteriPrimGirdi[1].setText("");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==barkodSorgulaBut) {
			barkodSorgulaReaksiyon();
		}
		
		if(e.getSource()==adetOnaylaBut) {
			adetOnayReaksiyon();
		}
		
		if(e.getSource()==musteriPrimBut[0]) { //m��teriBul
			musteriSecReaksiyon();
		}
		
		if(e.getSource()==degistirBut[0]) {
			musteriDegistirReaksiyon();
		}
		
		if(e.getSource()==musteriPrimBut[1]) { //Prim alacak �al��an bul
			calisanSecReaskiyon();
		}
		
		if(e.getSource()==degistirBut[1]) {
			calisanDegistirReaksiyon();
		}
		
		
		if(e.getSource()==sepetEkleBut) {
			Object[] satir = new Object[6];
			satir[0]= sepetNo;
			satir[1]= seciliUrun.getUrunBarkod(); 
			satir[2]= seciliUrun.getUrunAd();	  
			satir[3]= istenenAdet;
			satir[4]= String.format("%,.2f \u20ba", seciliUrun.getUrunSatisFiyat());
			satir[5]= String.format("%,.2f \u20ba", (istenenAdet * seciliUrun.getUrunSatisFiyat()));
			
			if(istenenAdet <= seciliUrun.getUrunKalanAdet()) {
				stokTxt.setText("Kalan Stok Adet: " + (seciliUrun.getUrunKalanAdet() - istenenAdet));
				seciliUrun.setUrunKalanAdet((seciliUrun.getUrunKalanAdet() - istenenAdet));
				sepetTabloModel.addRow(satir); //sepete ekler
				sepetToplamTutar = sepetToplamTutar + (istenenAdet * seciliUrun.getUrunSatisFiyat());
				sepetTutar�.setText(String.format("%,.2f", sepetToplamTutar)+'\u20ba');
					
				sepetNo++;
				
				sepetSatisDolu=true;
			}else {
				JOptionPane.showMessageDialog(null, "�STENEN ADET KALAN ADETTEN FAZLA - YETERS�Z STOK", "Depom Sepette", JOptionPane.WARNING_MESSAGE);
				istenenAdet=0; istenenAdetGirdi.setText("0");
				adetXurunFiyat.setText(String.format("%,.2f", (istenenAdet * seciliUrun.getUrunSatisFiyat()))+'\u20ba');
				sepetEkleBut.setEnabled(false);
				}
		}
		
		
		if(e.getSource()==copIconBut) {
			for(int i=0; i<sepetTabloModel.getRowCount(); i++) {
				int geriStok = (int) sepetTabloModel.getValueAt(i, 3);
				Urunler.setUrunKalanAdet(sepetTabloModel.getValueAt(i, 1).toString(), geriStok);
			}
			sepetTabloModel.setRowCount(0);
			sepetToplamTutar = 0; sepetTutar�.setText(String.format("%,.2f", sepetToplamTutar)+'\u20ba');
			sepetNo=1;
			musteriDegistirReaksiyon();
			calisanDegistirReaksiyon();
			sepetEkleBut.setEnabled(false);
			istenenAdetGirdi.setEnabled(false); istenenAdetGirdi.setText("0"); istenenAdet=0;
			
			for(int i=0; i<8; i++) {
				ozellik[i].setText("");
			}
			urunKategori.setText("");
			urunFoto.setIcon(null);
			urunAdi.setText("");  //"<HTML><U>YOUR TEXT HERE</U></HTML>" //<HTML><U>�r�n Adi:</U></HTML> �irkin duruyor kald�rd�m
			urunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); //birimFiyat de�i�keni ��kar�ld�
			adetXurunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); // birimFiyat de�i�keni ��kar�ld�
			istenenAdetGirdi.setText("0"); istenenAdet = 0;
			stokTxt.setText("Kalan Stok Adet: "+ 0);
			sepettenCikar.setEnabled(false);// koruma ama�l�
			sepetSatisDolu=false;
		}
		
		if(e.getSource()==sepettenCikar) {
			String[] cevap = {"Evet","Hay�r"};
			int answer = JOptionPane.showOptionDialog(
					null, //component
					(secilenTabloSirasi+1)+"'nolu �r�n� sepetten ��karmak istedi�inize emin misiniz?", //message 
					"Depom Sepette", //title
					JOptionPane.YES_NO_CANCEL_OPTION, //option type 
					JOptionPane.WARNING_MESSAGE, //message type
					null, // icon
					cevap, // option da yazacaklar 0-1-2 olarak de�i�tirir
					0);
			if(answer == 0) {
				Object[] satir = new Object[6];
				Object[] tempSatir = new Object[6];
				DefaultTableModel temp = new DefaultTableModel();
				Object[] kolonIsimleri = {"#","�r�n Barkodu","�r�n Ad�","Al�nan Adet","Birim Fiyat","Toplam Fiyat"}; //kolon isimleri olmadan �ok hata verdi gereksiz u�ra�
				temp.setColumnIdentifiers(kolonIsimleri);
				for(int i=0; i<sepetTabloModel.getRowCount(); i++) {
					tempSatir[0]= 0;
					tempSatir[1]= sepetTabloModel.getValueAt(i, 1);
					tempSatir[2]= sepetTabloModel.getValueAt(i, 2);	  
					tempSatir[3]= sepetTabloModel.getValueAt(i, 3);															//\\. olacak . olmuyor					//binli fiyatlardaki �nce nokta kalkt�
					tempSatir[4]= String.format("%,.2f \u20ba", Double.parseDouble(sepetTabloModel.getValueAt(i, 4).toString().replaceAll("\\u20ba", "").replaceAll("\\.", "").replaceAll("\\s", "").replaceAll("\\,", "\\.")));
					tempSatir[5]= String.format("%,.2f \u20ba", Double.parseDouble(sepetTabloModel.getValueAt(i, 5).toString().replaceAll("\\u20ba", "").replaceAll("\\.", "").replaceAll("\\s", "").replaceAll("\\,", "\\.")));
				
					temp.addRow(tempSatir);
				}
//				System.out.println("0"+tempSatir[0]);
//				System.out.println("1"+tempSatir[1]);
//				System.out.println("2"+tempSatir[2]);
//				System.out.println("3"+tempSatir[3]);
//				System.out.println("4   "+tempSatir[4]);
//				System.out.println("5   "+tempSatir[5]);
				
				//YUKARI ALINDI TABLO SIFIRLANMADAN ��LEMLER� YAPMALI
				sepetToplamTutar = sepetToplamTutar - Double.parseDouble(sepetTabloModel.getValueAt(secilenTabloSirasi, 5).toString().replaceAll("\\u20ba", "").replaceAll("\\.", "").replaceAll("\\s", "").replaceAll("\\,", "\\."));
				sepetTutar�.setText(String.format("%,.2f", sepetToplamTutar)+'\u20ba');
//				System.out.println(Double.parseDouble(temp.getValueAt(secilenTabloSirasi, 5).toString().replaceAll("\\u20ba", "").replaceAll(",", ".").replaceAll("\\s", "")));
				
				Urunler.setUrunKalanAdet(sepetTabloModel.getValueAt(secilenTabloSirasi, 1).toString(),  Integer.parseInt(sepetTabloModel.getValueAt(secilenTabloSirasi, 3).toString()));
				
//BU�PTAL UNUTMU�UM			seciliUrun.setUrunKalanAdet((seciliUrun.getUrunKalanAdet() + Integer.parseInt(sepetTabloModel.getValueAt(secilenTabloSirasi, 3).toString())));
				//HATA son secili �r�ne ekleme yap�yor �r�n� tekrar buldurmak laz�m
//				System.out.println(Integer.parseInt(temp.getValueAt(secilenTabloSirasi, 3).toString()));
				
				
				
				sepetTabloModel.setRowCount(0);
				int sepetNo = 1;
				for(int i=0; i<temp.getRowCount(); i++) {
					if(i == secilenTabloSirasi) {
						continue;
					}
					satir[0]= sepetNo;
					satir[1]= temp.getValueAt(i, 1);
					satir[2]= temp.getValueAt(i, 2);	  
					satir[3]= temp.getValueAt(i, 3);
					satir[4]= String.format("%,.2f \u20ba", Double.parseDouble(temp.getValueAt(i, 4).toString().replaceAll("\\u20ba", "").replaceAll("\\.", "").replaceAll("\\s", "").replaceAll("\\,", "\\.")));
					satir[5]= String.format("%,.2f \u20ba", Double.parseDouble(temp.getValueAt(i, 5).toString().replaceAll("\\u20ba", "").replaceAll("\\.", "").replaceAll("\\s", "").replaceAll("\\,", "\\.")));
					this.sepetNo = sepetNo;
					sepetNo++;
					sepetTabloModel.addRow(satir);
				}
				
				temp.setRowCount(0);
				
				for(int i=0; i<8; i++) {
					ozellik[i].setText("");
				}
				urunKategori.setText("");
				urunFoto.setIcon(null);
				urunAdi.setText("");  //"<HTML><U>YOUR TEXT HERE</U></HTML>" //<HTML><U>�r�n Adi:</U></HTML> �irkin duruyor kald�rd�m
				urunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); //birimFiyat de�i�keni ��kar�ld�
				adetXurunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); // birimFiyat de�i�keni ��kar�ld�
				istenenAdetGirdi.setText("0"); istenenAdet = 0;
				stokTxt.setText("Kalan Stok Adet: "+ 0);
			}
			sepettenCikar.setEnabled(false);
			if(sepetTabloModel.getRowCount()==0) {
				sepetSatisDolu=false;
			}
		}
		
		if(e.getSource()==sepetOnaylaBut) {
			if(sepetTabloModel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Sepet Bo�", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
			} else {
				
				Kasa.setPara(1, sepetToplamTutar);
				
				if(calisanSaptayici != null) {
					Kullanici.primEkle(calisanSaptayici, sepetToplamTutar);
				}
				
				if(satinAlanMusteri != null) {
					satinAlanMusteri.setMusteriToplamHarcama(sepetToplamTutar);
					satinAlanMusteri.setMusteriSonIslemTarih();
				}
				
				for(int i=0; i<sepetTabloModel.getRowCount(); i++) {
					DateTimeFormatter sqlTipSaat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
					LocalDateTime eklenenSaat = LocalDateTime.now();
					String updt_sql = "UPDATE `zprojefinal`.`urunler` SET `urun_son_satis_tarih` = '"+sqlTipSaat.format(eklenenSaat)+"' WHERE (`urun_barkod` = '"+new BigInteger(sepetTabloModel.getValueAt(i, 1).toString())+"')";
					int basarili = DbBaglanti.degistir(updt_sql);
					if(basarili!=1) {
						System.out.println("�r�n satis tarih update problem.");
					}
				
				}
				
				
				sepetNo=1;
				
				sepetEkleBut.setEnabled(false);
				istenenAdetGirdi.setEnabled(false); istenenAdetGirdi.setText("0"); istenenAdet=0;
				
				for(int i=0; i<8; i++) {
					ozellik[i].setText("");
				}
				urunKategori.setText("");
				urunFoto.setIcon(null);
				urunAdi.setText("");  //"<HTML><U>YOUR TEXT HERE</U></HTML>" //<HTML><U>�r�n Adi:</U></HTML> �irkin duruyor kald�rd�m
				urunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); //birimFiyat de�i�keni ��kar�ld�
				adetXurunFiyat.setText(String.format("%,.2f", 0.00)+'\u20ba'); // birimFiyat de�i�keni ��kar�ld�
				istenenAdetGirdi.setText("0"); istenenAdet = 0;
				stokTxt.setText("Kalan Stok Adet: "+ 0);
				sepettenCikar.setEnabled(false);// koruma ama�l�
				
				
				if(satinAlanMusteri != null) {
					Fatura.yaz(sepetTabloModel, sepetToplamTutar, satinAlanMusteri); //, satinAlanMusteri
//					System.out.println("M��terili fatura devrede"); //test
				} else {
					Fatura.yaz(sepetTabloModel, sepetToplamTutar); //, satinAlanMusteri
//					System.out.println("M��terisiz fatura devrede"); //test
				}
				
				
				musteriDegistirReaksiyon();
				calisanDegistirReaksiyon();
				sepetToplamTutar = 0; sepetTutar�.setText(String.format("%,.2f", sepetToplamTutar)+'\u20ba');
				sepetTabloModel.setRowCount(0);
				
				
				
				JOptionPane.showMessageDialog(null, "Al��veri� Tamamland� - Fatura Kesildi", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
				sepetSatisDolu=false;
			}
			
		}
		
	}

	
	
	//---------------------------KLAVYE-------------------------
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==barkodOku) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				barkodSorgulaReaksiyon();
			    }
		}
		if(e.getSource()==istenenAdetGirdi) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				adetOnayReaksiyon();
			    }
		}
		if(e.getSource()==musteriPrimGirdi[0]) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				musteriSecReaksiyon();
			    }
		}
		if(e.getSource()==musteriPrimGirdi[1]) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				calisanSecReaskiyon();
			    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	//--------------------------MOUSE--------------------------
	private int secilenTabloSirasi;
	@Override
	public void mouseClicked(MouseEvent e) {
			if(e.getSource()==sepetTablo) {
				secilenTabloSirasi = sepetTablo.getSelectedRow();
//				System.out.println(secilenTabloSirasi);
				sepettenCikar.setEnabled(true);
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
	
	//-------PANEL SET-------------
	public void panelSetVisible(boolean a) { //interface olabilir
		satisPanel.setVisible(a);
	}
}
