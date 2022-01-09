package zProjeFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class CalisanlarPanel implements ActionListener, MouseListener{

	private JPanel calisanlarPanel;
	private JPanel calisanTabloPanel;
	private JPanel calisanEklePanel;
	private JButton calisanEkleBut, calisanCikarBut, calisanDuzenleBut;
	private JTextField[] ekleGirdiler;
	private JButton ekleOnay, ekleIptal;
	private DefaultTableModel calisanTabloModel; JTable calisanTablo;
	private JPanel calisanGuncellePanel;
	private JTextField[] guncelleGirdiler; private JLabel[] gTanim;
	private JButton guncelleOnay, guncelleIptal;
	private JComboBox mevki;
	
	public JPanel panel() {
		Color arkaPlanColor = new Color(0x193f8a);
		
		calisanlarPanel = new JPanel();
		calisanlarPanel.setLayout(null);
		calisanlarPanel.setBounds(0, 0, 1030, 680);
		calisanlarPanel.setBackground(arkaPlanColor);
		
		calisanTabloModel = new DefaultTableModel();
		Object[] kolonIsimleri = {"Kullanici No","Konumu","Ad-Soyad","Telefon","E-Posta","Ayl�k Maa�","Prim"};
		calisanTabloModel.setColumnIdentifiers(kolonIsimleri);
		
		DefaultTableCellRenderer hucreOrtala = new DefaultTableCellRenderer(); hucreOrtala.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer hucreSag = new DefaultTableCellRenderer(); hucreSag.setHorizontalAlignment(JLabel.RIGHT);

		calisanTabloPanel = new JPanel();
		calisanTabloPanel.setLayout(null);
		calisanTabloPanel.setBounds(10, 10, 840, 660);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(0, 0, 840, 660); //10-10-840-660
		calisanTablo = new JTable();
		calisanTablo.setModel(calisanTabloModel);
		calisanTablo.addMouseListener(this);
		sp.setBorder(new LineBorder(Color.black, 4));
		sp.setViewportView(calisanTablo);
		
		calisanTablo.getColumnModel().getColumn(0).setPreferredWidth(50);
		
		calisanTablo.getColumnModel().getColumn(0).setCellRenderer(hucreOrtala);
		calisanTablo.getColumnModel().getColumn(1).setCellRenderer(hucreOrtala);
		calisanTablo.getColumnModel().getColumn(5).setCellRenderer(hucreSag);
		calisanTablo.getColumnModel().getColumn(6).setCellRenderer(hucreSag);
		
		tabloCek();
		
		calisanEkleBut = new JButton("�al��an Ekle");
		calisanEkleBut.setBounds(860, 10, 160, 40);
		calisanEkleBut.setFocusable(false);
		calisanEkleBut.addActionListener(this);
		
		calisanCikarBut = new JButton("�al��an ��kar");
		calisanCikarBut.setBounds(860, 55, 160, 40);
		calisanCikarBut.setFocusable(false);
		calisanCikarBut.addActionListener(this);
		calisanCikarBut.setEnabled(false);
		
		calisanDuzenleBut = new JButton("�al��an D�zenle");
		calisanDuzenleBut.setBounds(860, 100, 160, 40);
		calisanDuzenleBut.setFocusable(false);
		calisanDuzenleBut.addActionListener(this);
		calisanDuzenleBut.setEnabled(false);
		
		calisanEklePanel();
		calisanGuncellePanel();
		
		calisanTabloPanel.add(sp);
		//----------------------------------
		calisanlarPanel.add(calisanEkleBut);
		calisanlarPanel.add(calisanCikarBut);
		calisanlarPanel.add(calisanDuzenleBut);
		calisanlarPanel.add(calisanTabloPanel);
		calisanlarPanel.add(calisanEklePanel); calisanEklePanel.setVisible(false);
		calisanlarPanel.add(calisanGuncellePanel); calisanGuncellePanel.setVisible(false);
		
		return calisanlarPanel;
	}
	
	public void panelSetVisible(boolean a) {
		calisanlarPanel.setVisible(a);
	}
	
	private void tabloCek() {
		String calisanlar_sorgu = "SELECT * FROM kullanicilar";
		ResultSet dbVeriForTable = DbBaglanti.sorgula(calisanlar_sorgu);
		Object[] satir = new Object[7];
		
		try {
			while(dbVeriForTable.next()) {
				String adSoyad = new String(dbVeriForTable.getString("kullanici_ad")+" "+dbVeriForTable.getString("kullanici_soyad"));
				
				String mevki = "";
				switch (dbVeriForTable.getInt("kullanici_seviyesi")) {
				case 1: mevki="Y�netici"; break;
				case 2: mevki="Genel M�d�r"; break;
				case 3: mevki="�nsan Kaynaklar�"; break;
				case 4: mevki="Muhasebe"; break;
				case 5: mevki="Kasiyer" ; break;
				case 6: mevki="Sat�� Sorumlusu"; break;
				}
				
				satir[0] = dbVeriForTable.getInt("kullanici_no");
				satir[1] = mevki; 
				satir[2] = adSoyad;
				satir[3] = dbVeriForTable.getString("kullanici_telefon");
				satir[4] = dbVeriForTable.getString("kullanici_eposta");
				satir[5] = String.format("%,.2f", dbVeriForTable.getDouble("kullanici_aylik_maas"))+'\u20ba';
				satir[6] = String.format("%,.2f", dbVeriForTable.getDouble("kullanici_prim"))+'\u20ba';
				calisanTabloModel.addRow(satir);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for(int i=0; i<calisanTabloModel.getRowCount(); i++) {
//			calisanTabloModel.setValueAt(new CalisanSiniflari(Integer.parseInt(calisanTabloModel.getValueAt(i, 0).toString())).isimlendir(), i, 1);
//		}
		
	}
	
	private JButton sifreSifirla;
	private void calisanGuncellePanel() {  //��FRE SIFIRLA BUTON EKLENMEL�
		calisanGuncellePanel = new JPanel();
		calisanGuncellePanel.setLayout(null);
		calisanGuncellePanel.setBounds(10, 10, 840, 660);
		calisanGuncellePanel.setBackground(new Color(0x94d9e3));
		
		ImageIcon accountIcon = new ImageIcon("Images/accounticon120x120.png");
		JLabel accountIconLbl = new JLabel(accountIcon);
		accountIconLbl.setBounds(20, 50, 120, 120);
		calisanGuncellePanel.add(accountIconLbl);
		
		gTanim = new JLabel[12]; //8 SATIR
		for(int i=0; i<12; i++) {
			 gTanim[i] = new JLabel();
			 gTanim[i].setBounds(10, (180+(i*30)), 250, 20);
			 gTanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 calisanGuncellePanel.add(gTanim[i]);
		}
//		gTanim[0].setText(""); //bo�a bellek tutmamas� i�in yoruma al�nd�
		gTanim[1].setText("Kullan�c� Ad�");
		gTanim[2].setText("�al��an Ad");
		gTanim[3].setText("�al��an Soyad");
		gTanim[4].setText("�al��an Telefon");
		gTanim[5].setText("�al��an E-Posta");
		gTanim[6].setText("�al��an Adres");
		gTanim[7].setText("�al��an Maa�");
		gTanim[8].setText("�al��an Mevki");
		gTanim[9].setText("�al��an Do�um G�n�");
//		gTanim[10].setText(""); //bo�a bellek tutmamas� i�in yoruma al�nd�
		gTanim[10].setSize(500, 20);
		gTanim[11].setSize(500, 20);
		
		JLabel[] ikiNoktaAyrac = new JLabel[11]; // iki noktalar�n d�z bir �ekilde olmas� i�in ayr� label i�inde al�nd�
		for(int i=0; i<11; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(202, (210+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 calisanGuncellePanel.add(ikiNoktaAyrac[i]);
		}
		
		guncelleGirdiler = new JTextField[9];
		for(int i=0; i<=8; i++) {
			guncelleGirdiler[i] = new JTextField();
			guncelleGirdiler[i].setBounds(210, (210+(i*30)), 200, 20);
			guncelleGirdiler[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			guncelleGirdiler[i].setBorder(null);
			calisanGuncellePanel.add(guncelleGirdiler[i]);
		}
		
		guncelleGirdiler[8].setText("�rnek:1991-07-25");
		
		guncelleOnay = new JButton("Onayla");
		guncelleOnay.setBounds(315, 615, 100, 40);
		guncelleOnay.setFocusable(false);
		guncelleOnay.addActionListener(this);
		calisanGuncellePanel.add(guncelleOnay);
		
		guncelleIptal = new JButton("�ptal");
		guncelleIptal.setBounds(425, 615, 100, 40);
		guncelleIptal.setFocusable(false);
		guncelleIptal.addActionListener(this);
		calisanGuncellePanel.add(guncelleIptal);
		
		sifreSifirla = new JButton("�al��an�n parolas�n� s�f�rla.");
		sifreSifirla.setBounds(630, 10, 200, 20);
		sifreSifirla.setFocusable(false);
		sifreSifirla.addActionListener(this);
		calisanGuncellePanel.add(sifreSifirla);
	}
	
	private void calisanEklePanel() {
		calisanEklePanel = new JPanel();
		calisanEklePanel.setLayout(null);
		calisanEklePanel.setBounds(10, 10, 840, 660);
		calisanEklePanel.setBackground(new Color(0x94d9e3));
		JLabel[] tanim = new JLabel[9]; //9 SATIR
		for(int i=0; i<=8; i++) {
			 tanim[i] = new JLabel();
			 tanim[i].setBounds(10, (10+(i*30)), 250, 20);
			 tanim[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 calisanEklePanel.add(tanim[i]);
		}
		tanim[0].setText("Yeni Kullan�c� Ad�");
		tanim[1].setText("Yeni �al��an Ad");
		tanim[2].setText("Yeni �al��an Soyad");
		tanim[3].setText("Yeni �al��an Telefon");
		tanim[4].setText("Yeni �al��an E-Posta");
		tanim[5].setText("Yeni �al��an Adres");
		tanim[6].setText("Yeni �al��an Maa�");
		tanim[7].setText("Yeni �al��an Mevki");
		tanim[8].setText("Yeni �al��an Do�um G�n�");
		
		JLabel[] ikiNoktaAyrac = new JLabel[9]; // iki noktalar�n d�z bir �ekilde olmas� i�in ayr� label i�inde al�nd�
		for(int i=0; i<9; i++) {
			 ikiNoktaAyrac[i] = new JLabel();
			 ikiNoktaAyrac[i].setBounds(242, (10+(i*30)), 8, 20);
			 ikiNoktaAyrac[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			 ikiNoktaAyrac[i].setText(":");
			 calisanEklePanel.add(ikiNoktaAyrac[i]);
		}
		
		ekleGirdiler = new JTextField[9];
		for(int i=0; i<=8; i++) {
			ekleGirdiler[i] = new JTextField();
			ekleGirdiler[i].setBounds(250, (10+(i*30)), 200, 20);
			ekleGirdiler[i].setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
			ekleGirdiler[i].setBorder(null);
			calisanEklePanel.add(ekleGirdiler[i]);
		}
	
		ekleGirdiler[8].setText("�rnek:1991-07-25");
		ekleGirdiler[7].setVisible(false); //yeni combobox geldi ayarlar� de�i�tirmemek ad�na g�r�n�rl�k kald�rd�m
		
		String[] mevkiler = {"Genel M�d�r", "�nsan Kaynaklar�", "Muhasebe", "Kasiyer", "Sat�� Sorumlusu"};
		
		mevki = new JComboBox(mevkiler);
		mevki.setBounds(250, 220, 200, 20);
		mevki.setSelectedIndex(4);
		calisanEklePanel.add(mevki);
		
		
		ekleOnay = new JButton("Onayla");
		ekleOnay.setBounds(315, 615, 100, 40);
		ekleOnay.setFocusable(false);
		ekleOnay.addActionListener(this);
		calisanEklePanel.add(ekleOnay);
		
		ekleIptal = new JButton("�ptal");
		ekleIptal.setBounds(425, 615, 100, 40);
		ekleIptal.setFocusable(false);
		ekleIptal.addActionListener(this);
		calisanEklePanel.add(ekleIptal);
	}
	
	private int secilenKullaniciNo;
	@Override
	public void actionPerformed(ActionEvent e) {
		//-----------------------------------EKLE-----------------------------------------
		if(e.getSource()==calisanEkleBut) {
			calisanTabloPanel.setVisible(false);
			calisanGuncellePanel.setVisible(false);
			calisanCikarBut.setEnabled(false); //kazay� engelleme
			calisanDuzenleBut.setEnabled(false); // kazay� engelleme
			calisanEklePanel.setVisible(true);
		}
		if(e.getSource()==ekleIptal) {
			calisanEklePanel.setVisible(false);
			calisanGuncellePanel.setVisible(false);
			calisanTabloModel.setRowCount(0); tabloCek(); // deneme
			calisanTabloPanel.setVisible(true);
			for(int i=0; i<=7; i++) {
				ekleGirdiler[i].setText("");
			}
			ekleGirdiler[8].setText("�rnek:1991-07-25");
		}
		if(e.getSource()==ekleOnay) {
			//id bo� b�rak�lamaz gelmeli @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ - �rnek m��teri g�ncelle
			int basarili = Kullanici.ekle(ekleGirdiler[0].getText(), ekleGirdiler[1].getText(), ekleGirdiler[2].getText(),
											ekleGirdiler[3].getText(), ekleGirdiler[4].getText(), ekleGirdiler[5].getText(), 
											ekleGirdiler[6].getText(), (mevki.getSelectedIndex()+2), ekleGirdiler[8].getText());
			
			if(basarili == 1) {
				for(int i=0; i<=7; i++) {						//
					ekleGirdiler[i].setText("");				//
				}												//NORMALDE BURASI �F DI�INDA AMA �F ���NDE DENEME
				ekleGirdiler[8].setText("�rnek:1991-07-25");	//
				calisanEklePanel.setVisible(false);
				calisanGuncellePanel.setVisible(false);
				calisanTabloModel.setRowCount(0); tabloCek();
				calisanTabloPanel.setVisible(true);
			}
		}
		//-------------------------------------------�IKAR--------------------------------------------------------------
		if(e.getSource()==calisanCikarBut) {
			String[] cevap = {"Evet","Hay�r"};
			int answer = JOptionPane.showOptionDialog(
					null, //component
					secilenKullaniciNo+"'nolu kullan�c�y� programdan ��karmak istedi�inize emin misiniz?", //message 
					"Depom Sepette", //title
					JOptionPane.YES_NO_CANCEL_OPTION, //option type 
					JOptionPane.WARNING_MESSAGE, //message type
					null, // icon
					cevap, // option da yazacaklar 0-1-2 olarak de�i�tirir
					0);
			if(answer == 0) {
				if(secilenKullaniciNo==1) {
					JOptionPane.showMessageDialog(null, secilenKullaniciNo+"'nolu kullan�c� programdan ��kar�lamaz!!!", "Depom Sepette", JOptionPane.ERROR_MESSAGE);
				} else {
					String calisanSil_sql = "DELETE FROM `zprojefinal`.`kullanicilar` WHERE (`kullanici_no` = '"+secilenKullaniciNo+"')";
					DbBaglanti.sil(calisanSil_sql);
					calisanTabloModel.setRowCount(0); tabloCek();
					secilenKullaniciNo = 0;
					calisanCikarBut.setEnabled(false);
					calisanDuzenleBut.setEnabled(false);
				}
			}
		}
		//---------------------------------------------G�NCELLE------------------------------------------------------------
		
				//��E G�R�� TAR�H�N� ADM�N G�RMEL�
		if(e.getSource()==calisanDuzenleBut) {
			if(secilenKullaniciNo == 1 && secilenKullaniciNo != Kullanici.getKullaniciNo(Giris.idGiren)) {
				JOptionPane.showMessageDialog(null, secilenKullaniciNo+"'nolu kullan�c� d�zenlenemez!!!", "Depom Sepette", JOptionPane.ERROR_MESSAGE);
			} else {
				calisanTabloPanel.setVisible(false);
				calisanEklePanel.setVisible(false);
				calisanCikarBut.setEnabled(false); //kazay� engelleme
				calisanDuzenleBut.setEnabled(false); // kazay� engelleme
				calisanGuncellePanel.setVisible(true);
				Kullanici guncellenecekKullanici = new  Kullanici(secilenKullaniciNo);
				gTanim[0].setText("<HTML><U>#"+guncellenecekKullanici.getKullaniciNo()+" nolu Kullan�c� Bilgileri</U></HTML>");
				gTanim[10].setText("�� Ba�lang�c�                "+guncellenecekKullanici.getKullaniciEklenmeTarih());
				gTanim[11].setText("�al��an Prim                "+guncellenecekKullanici.getKullaniciPrim());
				guncelleGirdiler[0].setText(guncellenecekKullanici.getKullaniciId());
				guncelleGirdiler[1].setText(guncellenecekKullanici.getKullaniciAd());
				guncelleGirdiler[2].setText(guncellenecekKullanici.getKullaniciSoyad());
				guncelleGirdiler[3].setText(guncellenecekKullanici.getKullaniciTelefon());
				guncelleGirdiler[4].setText(guncellenecekKullanici.getKullaniciEposta());
				guncelleGirdiler[5].setText(guncellenecekKullanici.getKullaniciAdres());
				guncelleGirdiler[6].setText(""+guncellenecekKullanici.getKullaniciAylikMaas());
				guncelleGirdiler[7].setText("Admin ComboBox");
				guncelleGirdiler[8].setText(""+guncellenecekKullanici.getKullaniciDogumTarih());
				}
			if(secilenKullaniciNo == Kullanici.getKullaniciNo(Giris.idGiren)) { //��MD�L�K DURSUN BUNA B�R ENGEL KONULMALI //OTURUMU KAPATA SE�ENEG�DE EKLENEB�L�R
				sifreSifirla.setEnabled(false);
			}	else {sifreSifirla.setEnabled(true);}
		}
		if(e.getSource()==guncelleIptal) {
			calisanEklePanel.setVisible(false);
			calisanGuncellePanel.setVisible(false);
			calisanTabloModel.setRowCount(0); tabloCek(); // gerek yok ama deneme dursun
			calisanTabloPanel.setVisible(true);
			calisanCikarBut.setEnabled(false);
			calisanDuzenleBut.setEnabled(false);
		}
		if(e.getSource()==guncelleOnay) {
			int basarili = Kullanici.degistir(secilenKullaniciNo, guncelleGirdiler[0].getText(), guncelleGirdiler[1].getText(), 
							guncelleGirdiler[2].getText(), guncelleGirdiler[8].getText(), guncelleGirdiler[3].getText(), 
							guncelleGirdiler[4].getText(), guncelleGirdiler[5].getText(), guncelleGirdiler[6].getText(), "Admin");
			if(basarili == 1) {
				calisanCikarBut.setEnabled(false);
				calisanDuzenleBut.setEnabled(false);
				calisanEklePanel.setVisible(false);
				calisanGuncellePanel.setVisible(false);
				calisanTabloModel.setRowCount(0); tabloCek(); // gerek yok ama deneme dursun
				calisanTabloPanel.setVisible(true);	
			}	
		}
		if(e.getSource()==sifreSifirla) {
			Kullanici.kullaniciSifreSifirla(secilenKullaniciNo);
		}
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==calisanTablo) {
			secilenKullaniciNo = (int) calisanTabloModel.getValueAt(calisanTablo.getSelectedRow(), 0);
			calisanCikarBut.setEnabled(true);
			calisanDuzenleBut.setEnabled(true);
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
	
}
