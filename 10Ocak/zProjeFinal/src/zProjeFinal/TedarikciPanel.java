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

public class TedarikciPanel implements ActionListener, MouseListener, KeyListener {
	

	private JPanel tedarikciPanel;
	private JPanel tedarikciTabloPanel;
	private JButton tedarikciEkleBut, tedarikciCikarBut, tedarikciGoruntuleBut;
	private DefaultTableModel tedarikciTabloModel; JTable tedarikciTablo;
	private Color icPanelColor;
	private JTextField arama;
	private JButton araBut;
	
	public JPanel panel() { //müþteri son uðrama tarihi felan gelebilir
		Color arkaPlanColor = new Color(0xCbb9a9);
		icPanelColor = new Color(0xe0d5cb);
		
		tedarikciPanel = new JPanel();
		tedarikciPanel.setLayout(null);
		tedarikciPanel.setBounds(0, 0, 1030, 680);
		tedarikciPanel.setBackground(arkaPlanColor);
		
		
		tedarikciTabloModel = new DefaultTableModel();
		Object[] kolonIsimleri = {"Tedarikçi No","Tedarikçi Unvan","Tedarikçi Telefon","Tedarikçi E-Posta","Tedarikçi M. Adres"};
		tedarikciTabloModel.setColumnIdentifiers(kolonIsimleri);
		
		DefaultTableCellRenderer hucreOrtala = new DefaultTableCellRenderer(); hucreOrtala.setHorizontalAlignment(JLabel.CENTER);
		
		tedarikciTabloPanel = new JPanel();
		tedarikciTabloPanel.setLayout(null);
		tedarikciTabloPanel.setBounds(10, 10, 840, 660);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(0, 0, 840, 660);
		tedarikciTablo = new JTable();
		tedarikciTablo.setModel(tedarikciTabloModel);
		tedarikciTablo.addMouseListener(this);
		sp.setBorder(new LineBorder(Color.black, 4)); //deneme
		sp.setViewportView(tedarikciTablo);
		tedarikciTabloPanel.add(sp);
		
		tedarikciTablo.getColumnModel().getColumn(0).setPreferredWidth(30);
		tedarikciTablo.getColumnModel().getColumn(1).setPreferredWidth(100);
		tedarikciTablo.getColumnModel().getColumn(2).setPreferredWidth(100); //Tablo otomatik þekillendiði için deðerler aslýný yansýtmaz
		tedarikciTablo.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		tedarikciTablo.getColumnModel().getColumn(0).setCellRenderer(hucreOrtala);
		
		tabloCek();
		
		
		
		tedarikciEkleBut = new JButton("Tedarikçi Ekle");
		tedarikciEkleBut.setBounds(860, 10, 160, 40);
		tedarikciEkleBut.setFocusable(false);
		tedarikciEkleBut.addActionListener(this);
		
		tedarikciCikarBut = new JButton("Tedarikçi Sil");
		tedarikciCikarBut.setBounds(860, 55, 160, 40);
		tedarikciCikarBut.setFocusable(false);
		tedarikciCikarBut.addActionListener(this);
		tedarikciCikarBut.setEnabled(false);
		
		tedarikciGoruntuleBut = new JButton("Tedarikçi Görüntüle");
		tedarikciGoruntuleBut.setBounds(860, 100, 160, 40);
		tedarikciGoruntuleBut.setFocusable(false);
		tedarikciGoruntuleBut.addActionListener(this);
		tedarikciGoruntuleBut.setEnabled(false);
		
		arama = new JTextField();
		arama.setBounds(860, 160, 160, 30);
		arama.setBorder(null);
		arama.setFont(new Font(null, Font.CENTER_BASELINE, 13));
		arama.setFocusable(false);
		
		arama.setForeground(Color.gray);
			arama.setHorizontalAlignment(JLabel.CENTER);
				arama.setText("Telefon-Unvan-Eposta"); 
					arama.setFont(new Font(null, Font.ITALIC, 15));
		
		arama.addKeyListener(this);
		arama.addMouseListener(this);
		tedarikciPanel.add(arama);
		
		araBut = new JButton("Tedarikçi Ara");
		araBut.setBounds(860, 192, 160, 20);
		araBut.setFocusable(false);
		araBut.addActionListener(this);
		araBut.setEnabled(false);
		tedarikciPanel.add(araBut);
		
		
		tedarikciEklePanel();
		tedarikciGoruntulePanel();
		
		tedarikciPanel.add(tedarikciEkleBut);
		tedarikciPanel.add(tedarikciCikarBut);
		tedarikciPanel.add(tedarikciGoruntuleBut);
		tedarikciPanel.add(tedarikciTabloPanel);
		tedarikciPanel.add(tedarikciEklePanel); tedarikciEklePanel.setVisible(false);
		tedarikciPanel.add(tedarikciGoruntulePanel); tedarikciGoruntulePanel.setVisible(false);
		return tedarikciPanel;
	}
	
	private JPanel tedarikciEklePanel;
	private JTextField[] ekleGirdiler;
	private JButton ekleOnay, ekleIptal;
	private void tedarikciEklePanel() {
		tedarikciEklePanel = new JPanel();
		tedarikciEklePanel.setLayout(null);
		tedarikciEklePanel.setBounds(10, 10, 840, 660);
		tedarikciEklePanel.setBackground(icPanelColor);
		
		
		JLabel[] tanim = new JLabel[11]; //9 SATIR
		for(int i=0; i<11; i++) {
			 tanim[i] = new JLabel();
			 tanim[i].setBounds(10, (10+(i*30)), 300, 20);
			 tanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 tedarikciEklePanel.add(tanim[i]);
		}
		tanim[0].setText("Yeni Tedarikçi Unvan");
		tanim[1].setText("Yeni Tedarikçi Sorumlu");
		tanim[2].setText("Yeni Tedarikçi Telefon");
		tanim[3].setText("Yeni Tedarikçi E-Posta");
		tanim[4].setText("Yeni Tedarikçi Web Adres");
		tanim[5].setText("Yeni Tedarikçi Merkez Adres");
		tanim[6].setText("Yeni Tedarikçi Daðýtým Adres");
		tanim[7].setText("Yeni Tedarikçi Anlaþmalý Kargo-1");
		tanim[8].setText("Yeni Tedarikçi Anlaþmalý Kargo-2");
		tanim[9].setText("Yeni Tedarikçi Anlaþmalý Kargo-3");
		tanim[10].setText("Yeni Tedarikçi Anlaþmalý Kargo-4");
		
		JLabel[] ikiNoktaAyrac = new JLabel[11]; // iki noktalarýn düz bir þekilde olmasý için ayrý label içinde alýndý
		for(int i=0; i<11; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(302, (10+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 tedarikciEklePanel.add(ikiNoktaAyrac[i]);
		}
		
		ekleGirdiler = new JTextField[11];
		for(int i=0; i<11; i++) {
			ekleGirdiler[i] = new JTextField();
			ekleGirdiler[i].setBounds(310, (10+(i*30)), 250, 20);
			ekleGirdiler[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			ekleGirdiler[i].setBorder(null);
			tedarikciEklePanel.add(ekleGirdiler[i]);
		}
		
		
		ekleOnay = new JButton("Onayla");
		ekleOnay.setBounds(315, 615, 100, 40);
		ekleOnay.setFocusable(false);
		ekleOnay.addActionListener(this);
		tedarikciEklePanel.add(ekleOnay);
		
		ekleIptal = new JButton("Ýptal");
		ekleIptal.setBounds(425, 615, 100, 40);
		ekleIptal.setFocusable(false);
		ekleIptal.addActionListener(this);
		tedarikciEklePanel.add(ekleIptal);
	}
	
	private JPanel tedarikciGoruntulePanel;
	private JLabel[] goruntuTanim; //nolu tedarikci deðiþimi için dýþarý alýndý
	private JButton goruntuleGeri;
	private JLabel[] tedarikciBilgi;
	private JButton[] tedarikciGuncelleButs;
	private JButton[] guncellemeOnayIptal;
	private JTextField[] tedarikciGuncellemeGirdi;
	
	private void tedarikciGoruntulePanel() {
		tedarikciGoruntulePanel = new JPanel();
		tedarikciGoruntulePanel.setLayout(null);
		tedarikciGoruntulePanel.setBounds(10, 10, 840, 660);
		tedarikciGoruntulePanel.setBackground(icPanelColor);
		goruntuTanim = new JLabel[16]; //9 SATIR
		for(int i=0; i<16; i++) {
			 goruntuTanim[i] = new JLabel();
			 goruntuTanim[i].setBounds(10, (10+(i*30)), 310, 20);
			 goruntuTanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 tedarikciGoruntulePanel.add(goruntuTanim[i]);
		}
//		goruntuTanim[0].setText("<HTML><U>#"+"5"+" nolu Ürün Bilgileri</U></HTML>"); //tanýmý tuþ basýnca oto yapýyor zaten
		goruntuTanim[1].setText("Tedarikçi Unvan");
		goruntuTanim[2].setText("Tedarikçi Sorumlu");
		goruntuTanim[3].setText("Tedarikçi Telefon");
		goruntuTanim[4].setText("Tedarikçi E-Posta");
		goruntuTanim[5].setText("Tedarikçi Web Adres"); // stok otomatik atanacak
		goruntuTanim[6].setText("Tedarikçi Merkez Adres");
		goruntuTanim[7].setText("Tedarikçi Daðýtým Adres");
		goruntuTanim[8].setText("Tedarikçi Anlaþmalý Kargo-1");
		goruntuTanim[9].setText("Tedarikçi Anlaþmalý Kargo-2");
		goruntuTanim[10].setText("Tedarikçi Anlaþmalý Kargo-3");
		goruntuTanim[11].setText("Tedarikçi Anlaþmalý Kargo-4");
		goruntuTanim[12].setText("Tedarikçi Ortalama Teslimat Saati");
		goruntuTanim[13].setText("Tedarikçi Eklenme Tarih");
		goruntuTanim[14].setText("Tedarikçi Son Ýþlem Tarih");
		goruntuTanim[15].setText("Tedarikçi Yapýlan Toplam Harcama");
		
		
		JLabel[] ikiNoktaAyrac = new JLabel[15]; // iki noktalarýn düz bir þekilde olmasý için ayrý label içinde alýndý
		for(int i=0; i<15; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(320, (40+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 tedarikciGoruntulePanel.add(ikiNoktaAyrac[i]);
		}
		
		tedarikciBilgi = new JLabel[15]; //9 SATIR
		for(int i=0; i<15; i++) {
			 tedarikciBilgi[i] = new JLabel();
			 tedarikciBilgi[i].setBounds(327, (40+(i*30)), 400, 20);
			 tedarikciBilgi[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 tedarikciGoruntulePanel.add(tedarikciBilgi[i]);
		}
		
		goruntuleGeri = new JButton("Geri");
		goruntuleGeri.setBounds(370, 615, 100, 40);
		goruntuleGeri.setFocusable(false);
		goruntuleGeri.addActionListener(this);
		tedarikciGoruntulePanel.add(goruntuleGeri);
		
		tedarikciGuncelleButs = new JButton[1];
		for(int i=0; i<1; i++) {
			tedarikciGuncelleButs[i] = new JButton();
			tedarikciGuncelleButs[i].setBounds(625, (635+(i*25)), 210, 20);
			tedarikciGuncelleButs[i].setFocusable(false);
			tedarikciGuncelleButs[i].addActionListener(this);
			tedarikciGoruntulePanel.add(tedarikciGuncelleButs[i]);
		}
		tedarikciGuncelleButs[0].setText("Tedarikçi Bilgilerini Güncelle");
		
		
		tedarikciGuncellemeGirdi = new JTextField[11];
		for(int i=0; i<11; i++) {
			 tedarikciGuncellemeGirdi[i] = new JTextField();
			 tedarikciGuncellemeGirdi[i].setBounds(327, (40+(i*30)), 300, 20);
			 tedarikciGuncellemeGirdi[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 tedarikciGuncellemeGirdi[i].setBorder(null);
			 tedarikciGuncellemeGirdi[i].setVisible(false);
			 tedarikciGoruntulePanel.add(tedarikciGuncellemeGirdi[i]);
		}
		
		
		guncellemeOnayIptal = new JButton[2];
		for(int i=0; i<2; i++) {
			guncellemeOnayIptal[i] = new JButton();
			guncellemeOnayIptal[i].setBounds((315+(i*110)), 615, 100, 40);
			guncellemeOnayIptal[i].setFocusable(false);
			guncellemeOnayIptal[i].addActionListener(this);
			guncellemeOnayIptal[i].setVisible(false);
			tedarikciGoruntulePanel.add(guncellemeOnayIptal[i]);
		}
		guncellemeOnayIptal[0].setText("Onayla");
		guncellemeOnayIptal[1].setText("Ýptal");
		
	}
	
	private void tabloCek() { //public yaptým çünkü ürün satýþýndan sonra sol panelden her týklamaya refresh lazým
		tedarikciTabloModel.setRowCount(0);
		String calisanlar_sorgu = "SELECT * FROM tedarikci";
		ResultSet dbVeri = DbBaglanti.sorgula(calisanlar_sorgu);
		Object[] satir = new Object[5];
		
		try {
			while(dbVeri.next()) {
				satir[0] = dbVeri.getInt("tedarikci_no");
				satir[1] = dbVeri.getString("tedarikci_ad"); //string yeterli olabilir
				satir[2] = dbVeri.getString("tedarikci_telefon");
				satir[3] = dbVeri.getString("tedarikci_eposta");
				satir[4] = dbVeri.getString("tedarikci_merkez_adres");
				tedarikciTabloModel.addRow(satir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void aramaReaksiyon() {
		ResultSet AramaSonuc;
		AramaSonuc = Tedarikci.arama(arama.getText());
		
		Object[] satir = new Object[5];
		tedarikciTabloModel.setRowCount(0);
		try {
			while(AramaSonuc.next()) {
				satir[0] = AramaSonuc.getInt("tedarikci_no");
				satir[1] = AramaSonuc.getString("tedarikci_ad"); //string yeterli olabilir
				satir[2] = AramaSonuc.getString("tedarikci_telefon");
				satir[3] = AramaSonuc.getString("tedarikci_eposta");
				satir[4] = AramaSonuc.getString("tedarikci_merkez_adres");
				tedarikciTabloModel.addRow(satir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//----------------BUTTON--------------
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//-------------------EKLE------------------------
		if(e.getSource()==tedarikciEkleBut) {
			tedarikciTabloPanel.setVisible(false);
			tedarikciGoruntulePanel.setVisible(false);
			tedarikciCikarBut.setEnabled(false); //kazayý engelleme
			tedarikciGoruntuleBut.setEnabled(false); // kazayý engelleme
			tedarikciEklePanel.setVisible(true);
			arama.setVisible(false); araBut.setVisible(false);
		}
		if(e.getSource()==ekleIptal) {
			tedarikciEklePanel.setVisible(false);
			tedarikciGoruntulePanel.setVisible(false);
			tabloCek(); // deneme
			arama.setForeground(Color.gray);
				arama.setHorizontalAlignment(JLabel.CENTER);
					arama.setText("Telefon-Unvan-Eposta"); araBut.setEnabled(false);
						arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
			arama.setVisible(true); araBut.setVisible(true);
			tedarikciTabloPanel.setVisible(true);
			for(int i=0; i<11; i++) {
				ekleGirdiler[i].setText("");
			}
		}
		if(e.getSource()==ekleOnay) {
				int basarili = Tedarikci.ekle(ekleGirdiler[0].getText(), ekleGirdiler[1].getText(), ekleGirdiler[5].getText(), ekleGirdiler[6].getText(), 
											ekleGirdiler[2].getText(), ekleGirdiler[3].getText(), ekleGirdiler[4].getText(), 
											ekleGirdiler[7].getText(), ekleGirdiler[8].getText(), ekleGirdiler[9].getText(), ekleGirdiler[10].getText());
				if(basarili==1) {
					for(int i=0; i<11; i++) {
						ekleGirdiler[i].setText("");
					}
					tedarikciCikarBut.setEnabled(false);
					tedarikciGoruntuleBut.setEnabled(false);
					tedarikciEklePanel.setVisible(false);
					tedarikciGoruntulePanel.setVisible(false);
					tabloCek();
					arama.setForeground(Color.gray); 
						arama.setHorizontalAlignment(JLabel.CENTER);
							arama.setText("Telefon-Unvan-Eposta"); araBut.setEnabled(false);
								arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
					arama.setVisible(true); araBut.setVisible(true);
					tedarikciTabloPanel.setVisible(true);
				}	
			
		}
		
		//---------------------SÝLME------------------
		if(e.getSource()==tedarikciCikarBut) {
			String[] cevap = {"Evet","Hayýr"};
			int answer = JOptionPane.showOptionDialog(
					null, //component
					secilenTedarikciNo+"'nolu tedarikçiyi silmek istediðinize emin misiniz?", //message 
					"Depom Sepette", //title
					JOptionPane.YES_NO_CANCEL_OPTION, //option type 
					JOptionPane.WARNING_MESSAGE, //message type
					null, // icon
					cevap, // option da yazacaklar 0-1-2 olarak deðiþtirir
					0);
			if(answer == 0) {
				String tedarikciSil_sql = "DELETE FROM `zprojefinal`.`tedarikci` WHERE (`tedarikci_no` = '"+secilenTedarikciNo+"')";
				DbBaglanti.sil(tedarikciSil_sql);
				tabloCek();
				secilenTedarikciNo = 0;
				tedarikciCikarBut.setEnabled(false);
				tedarikciGoruntuleBut.setEnabled(false);
			}
		}
		//---------------------GÖRÜNTÜLE-----------------
		if(e.getSource()==tedarikciGoruntuleBut) {
			tedarikciTabloPanel.setVisible(false);
			tedarikciEklePanel.setVisible(false);
			arama.setVisible(false); araBut.setVisible(false);
			tedarikciCikarBut.setEnabled(false); //kazayý engelleme
			tedarikciGoruntuleBut.setEnabled(false); // kazayý engelleme
			
			Tedarikci goruntulenecekTedarikci = new Tedarikci(secilenTedarikciNo);
			goruntuTanim[0].setText("<HTML><U>#"+goruntulenecekTedarikci.getTedarikciNo()+" nolu Tedarikçi Bilgileri</U></HTML>");
			tedarikciBilgi[0].setText(goruntulenecekTedarikci.getTedarikciAd());
			tedarikciBilgi[1].setText(""+goruntulenecekTedarikci.getTedarikciSorumlu());
			tedarikciBilgi[2].setText(goruntulenecekTedarikci.getTedarikciTelefon());
			tedarikciBilgi[3].setText(""+goruntulenecekTedarikci.getTedarikciEposta());
			tedarikciBilgi[4].setText(""+goruntulenecekTedarikci.getTedarikciWebAdres());
			tedarikciBilgi[5].setText(""+goruntulenecekTedarikci.getTedarikciMerkezAdres());
			tedarikciBilgi[6].setText(""+goruntulenecekTedarikci.getTedarikciDagitimAdres());
			
			if(goruntulenecekTedarikci.getTedarikciKargo1()==null || goruntulenecekTedarikci.getTedarikciKargo1().equals("")) {
				tedarikciBilgi[7].setText("Yok");
			} else tedarikciBilgi[7].setText(""+goruntulenecekTedarikci.getTedarikciKargo1());
			if(goruntulenecekTedarikci.getTedarikciKargo2()==null || goruntulenecekTedarikci.getTedarikciKargo2().equals("")) {
				tedarikciBilgi[8].setText("Yok");
			} else tedarikciBilgi[8].setText(""+goruntulenecekTedarikci.getTedarikciKargo2());
			if(goruntulenecekTedarikci.getTedarikciKargo3()==null || goruntulenecekTedarikci.getTedarikciKargo3().equals("")) {
				tedarikciBilgi[9].setText("Yok");
			} else tedarikciBilgi[9].setText(""+goruntulenecekTedarikci.getTedarikciKargo3());
			if(goruntulenecekTedarikci.getTedarikciKargo4()==null || goruntulenecekTedarikci.getTedarikciKargo4().equals("")) {
				tedarikciBilgi[10].setText("Yok");
			} else tedarikciBilgi[10].setText(""+goruntulenecekTedarikci.getTedarikciKargo4());
			
			if(goruntulenecekTedarikci.getTedarikciTeslimatSaat()==0) {
				tedarikciBilgi[11].setText("Hiç Alým Yapýlmadý");
			} else 
				if((goruntulenecekTedarikci.getTedarikciTeslimatSaat()/168)>0) {
					int hafta = goruntulenecekTedarikci.getTedarikciTeslimatSaat()/168;
					int gün = (goruntulenecekTedarikci.getTedarikciTeslimatSaat()%168)/24;
					int saat = (goruntulenecekTedarikci.getTedarikciTeslimatSaat()%168)%24;
					tedarikciBilgi[11].setText(hafta+"-hafta, "+gün+"-gün, "+saat+"-saat");
				} else if ((goruntulenecekTedarikci.getTedarikciTeslimatSaat()/24)>0) {
					int gün = goruntulenecekTedarikci.getTedarikciTeslimatSaat()/24;
					int saat = goruntulenecekTedarikci.getTedarikciTeslimatSaat()%24;
					tedarikciBilgi[11].setText(gün+"-gün, "+saat+"-saat");
				} else tedarikciBilgi[11].setText(""+goruntulenecekTedarikci.getTedarikciTeslimatSaat());
				
			
			tedarikciBilgi[12].setText(""+goruntulenecekTedarikci.getTedarikciEklemeTarih());
			
			if(goruntulenecekTedarikci.getTedarikciSonIslemTarih().toString().equals("0001-01-01 00:00:00.0")) {
				tedarikciBilgi[13].setText("Hiç Alým Yapýlmadý");
			} else tedarikciBilgi[13].setText(""+goruntulenecekTedarikci.getTedarikciSonIslemTarih());
			if(goruntulenecekTedarikci.getTedarikciToplamAlim()==0) {
				tedarikciBilgi[14].setText("Hiç Alým Yapýlmadý");
			} else tedarikciBilgi[14].setText(String.format("%,.2f",goruntulenecekTedarikci.getTedarikciToplamAlim())+'\u20ba');
			
			
			
			tedarikciGuncellemeGirdi[0].setText(goruntulenecekTedarikci.getTedarikciAd());
			tedarikciGuncellemeGirdi[1].setText(""+goruntulenecekTedarikci.getTedarikciSorumlu());
			tedarikciGuncellemeGirdi[2].setText(goruntulenecekTedarikci.getTedarikciTelefon());
			tedarikciGuncellemeGirdi[3].setText(""+goruntulenecekTedarikci.getTedarikciEposta());
			tedarikciGuncellemeGirdi[4].setText(""+goruntulenecekTedarikci.getTedarikciWebAdres());
			tedarikciGuncellemeGirdi[5].setText(""+goruntulenecekTedarikci.getTedarikciMerkezAdres());
			tedarikciGuncellemeGirdi[6].setText(""+goruntulenecekTedarikci.getTedarikciDagitimAdres());
			tedarikciGuncellemeGirdi[7].setText(""+goruntulenecekTedarikci.getTedarikciKargo1());
			if(goruntulenecekTedarikci.getTedarikciKargo2()==null || goruntulenecekTedarikci.getTedarikciKargo2().equals("")) {
				tedarikciGuncellemeGirdi[8].setText("");
			} else tedarikciGuncellemeGirdi[8].setText(""+goruntulenecekTedarikci.getTedarikciKargo2());
			if(goruntulenecekTedarikci.getTedarikciKargo3()==null || goruntulenecekTedarikci.getTedarikciKargo3().equals("")) {
				tedarikciGuncellemeGirdi[9].setText("");
			} else tedarikciGuncellemeGirdi[9].setText(""+goruntulenecekTedarikci.getTedarikciKargo3());
			if(goruntulenecekTedarikci.getTedarikciKargo4()==null || goruntulenecekTedarikci.getTedarikciKargo4().equals("")) {
				tedarikciGuncellemeGirdi[10].setText("");
			} else tedarikciGuncellemeGirdi[10].setText(""+goruntulenecekTedarikci.getTedarikciKargo4());
			

		
			tedarikciGoruntulePanel.setVisible(true);
		}
		
		if(e.getSource()==goruntuleGeri) {
			tedarikciGoruntulePanel.setVisible(false);
			tedarikciEklePanel.setVisible(false);
			tedarikciCikarBut.setEnabled(false); //kazayý engelleme
			tedarikciGoruntuleBut.setEnabled(false); // kazayý engelleme
			arama.setForeground(Color.gray); 
				arama.setHorizontalAlignment(JLabel.CENTER);
					arama.setText("Telefon-Unvan-Eposta");araBut.setEnabled(false);
						arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
			arama.setVisible(true); araBut.setVisible(true);
			tedarikciTabloPanel.setVisible(true);
		}
		if(e.getSource()==tedarikciGuncelleButs[0]) { //tedarikciu güncelle
			goruntuleGeri.setVisible(false);
			guncellemeOnayIptal[0].setVisible(true);
			guncellemeOnayIptal[1].setVisible(true);
			tedarikciCikarBut.setEnabled(false); //kazayý engelleme
			tedarikciGoruntuleBut.setEnabled(false); // kazayý engelleme
			for(int i=0; i<11; i++){
				tedarikciBilgi[i].setVisible(false);
			}
			
			for(int i=0; i<11; i++) {
				 tedarikciGuncellemeGirdi[i].setVisible(true);
			}
		}
		if(e.getSource()==guncellemeOnayIptal[1]) { //güncelleme iptal
			guncellemeOnayIptal[0].setVisible(false);
			guncellemeOnayIptal[1].setVisible(false);
			goruntuleGeri.setVisible(true);
			for(int i=0; i<11; i++){
				tedarikciBilgi[i].setVisible(true);
			}
			for(int i=0; i<11; i++) {
				 tedarikciGuncellemeGirdi[i].setVisible(false);
			}
		}
		if(e.getSource()==guncellemeOnayIptal[0]) { //onayla
				int basarili = Tedarikci.guncelle(secilenTedarikciNo, tedarikciGuncellemeGirdi[0].getText(), tedarikciGuncellemeGirdi[1].getText(), 
						tedarikciGuncellemeGirdi[5].getText(), tedarikciGuncellemeGirdi[6].getText(), 
						tedarikciGuncellemeGirdi[2].getText(), tedarikciGuncellemeGirdi[3].getText(), tedarikciGuncellemeGirdi[4].getText(), 
						tedarikciGuncellemeGirdi[7].getText(), tedarikciGuncellemeGirdi[8].getText(), tedarikciGuncellemeGirdi[9].getText(), 
						tedarikciGuncellemeGirdi[10].getText());
				
				if(basarili == 1) {
					tedarikciGoruntulePanel.setVisible(false);
					tedarikciEklePanel.setVisible(false);
					tedarikciCikarBut.setEnabled(false); //kazayý engelleme
					tedarikciGoruntuleBut.setEnabled(false); // kazayý engelleme
					
					guncellemeOnayIptal[0].setVisible(false);
					guncellemeOnayIptal[1].setVisible(false);
					goruntuleGeri.setVisible(true);
					for(int i=0; i<11; i++){
						tedarikciBilgi[i].setVisible(true);
					}
					for(int i=0; i<11; i++) {
						 tedarikciGuncellemeGirdi[i].setVisible(false);
					}
					
					arama.setForeground(Color.gray); 
						arama.setHorizontalAlignment(JLabel.CENTER);
							arama.setText("Telefon-Unvan-Eposta"); araBut.setEnabled(false);
								arama.setFocusable(false); arama.setFont(new Font(null, Font.ITALIC, 15));
					arama.setVisible(true); araBut.setVisible(true);
					
					tabloCek();
					tedarikciTabloPanel.setVisible(true);
				}
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
	private int secilenTedarikciNo;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==tedarikciTablo) {
			secilenTedarikciNo = (int) tedarikciTabloModel.getValueAt(tedarikciTablo.getSelectedRow(), 0);
			tedarikciCikarBut.setEnabled(true);
			tedarikciGoruntuleBut.setEnabled(true);
		}
		if(e.getSource()==arama) {
			arama.setFocusable(true);
			arama.grabFocus();
			if(arama.getText().equals("Telefon-Unvan-Eposta")) {
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
			tedarikciPanel.setVisible(a);
		}
	
	
	
}
