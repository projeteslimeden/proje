package zProjeFinal;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{
	
	private JButton satisBut, alisBut, urunlerBut, tedarikcilerBut, musterilerBut,
			calisanlarBut, raporlarBut, kullaniciAyarBut, cikisBut;
	private JPanel solGenelMenu;
	private KarsilamaPanel karsilamaPanel;
	private SatisPanel satisPanel;
	private AlisPanel alisPanel;
	private UrunlerPanel urunlerPanel;
	private TedarikciPanel tedarikciPanel;
	private MusteriPanel musteriPanel;
	private KullaniciAyarPanel kullaniciAyarPanel;
	private CalisanlarPanel calisanlarPanel;
	private ExtraSinifDiyagrami proje;
	private JMenuItem kasa, cikis, diagram;
	
	public void MFrameRun() {
		MFrame();
		this.setLocationRelativeTo(null); //pencereyi ortada baþlatma kodu
		this.setVisible(true);
	}
		
	private void MFrame() {
		
		JPanel cont = new JPanel();
		cont.setBackground(Color.white);
		cont.setBounds(10, 10, 1260, 700);
		cont.setLayout(null);
		
		JPanel logo = new JPanel();
		logo.setBackground(null);
		logo.setBounds(10, 10, 200, 200);
		logo.setLayout(null);
		JLabel pLogoLbl = new JLabel(new ImageIcon("Images/migroslogo200x200.png"));
		pLogoLbl.setBounds(0, 0, 200, 200);
		logo.add(pLogoLbl);
		
		
		JPanel solPanel = new JPanel();
		solPanel.setBackground(Color.pink);
		solPanel.setBounds(10, 220, 200, 470);
		solPanel.setLayout(null);
		
		JPanel anaPanel = new JPanel();
		anaPanel.setBackground(Color.green);
		anaPanel.setBounds(220, 10, 1030, 680);
		anaPanel.setLayout(null);
		
//-----------------------------------------------------------------------------------------------		
		solGenelMenu = new JPanel();
		solGenelMenu.setBackground(Color.pink);
		solGenelMenu.setBounds(5, 5, 190, 460);
		solGenelMenu.setLayout(new GridLayout(9,1,5,5));
		
		satisBut = new			JButton("SATIÞ");
		alisBut = new			JButton("ALIÞ");
		urunlerBut = new		JButton("ÜRÜNLER"); 
		tedarikcilerBut = new 	JButton("TEDARÝKÇÝLER");
		musterilerBut = new		JButton("MÜÞTERÝLER");
		calisanlarBut = new		JButton("ÇALIÞANLAR");
		raporlarBut = new		JButton("RAPORLAR");
		kullaniciAyarBut = new 	JButton("KULLANICI AYARI");
		cikisBut = new			JButton("ÇIKIÞ YAP");
		
		satisBut.setFocusable(false);
		alisBut.setFocusable(false);
		urunlerBut.setFocusable(false);
		tedarikcilerBut.setFocusable(false);
		musterilerBut.setFocusable(false);
		calisanlarBut.setFocusable(false);
		raporlarBut.setFocusable(false);
		kullaniciAyarBut.setFocusable(false);
		cikisBut.setFocusable(false);
		
		solGenelMenu.add(satisBut); 		satisBut.addActionListener(this);
		solGenelMenu.add(alisBut);  		alisBut.addActionListener(this);
		solGenelMenu.add(urunlerBut);		urunlerBut.addActionListener(this);
		solGenelMenu.add(tedarikcilerBut); 	tedarikcilerBut.addActionListener(this);
		solGenelMenu.add(musterilerBut);	musterilerBut.addActionListener(this);
		solGenelMenu.add(calisanlarBut);	calisanlarBut.addActionListener(this);
		solGenelMenu.add(raporlarBut);		raporlarBut.addActionListener(this);
		solGenelMenu.add(kullaniciAyarBut);	kullaniciAyarBut.addActionListener(this);
		solGenelMenu.add(cikisBut); 		cikisBut.addActionListener(this);
		
		//------------YETKÝLER------------
		switch (new Kullanici(Giris.idGiren).getKullaniciSeviyesi()) {
/*ADM*/	case 1: break;
/*GM*/	case 2: break;
/*IK*/	case 3: satisBut.setVisible(false); alisBut.setVisible(false); urunlerBut.setVisible(false); raporlarBut.setVisible(false); break; //raporlarda satýþ iþlemlerini görebilir
/*MUH*/	case 4: satisBut.setVisible(false); break;
/*KAS*/	case 5: calisanlarBut.setVisible(false); raporlarBut.setVisible(false); alisBut.setVisible(false); tedarikcilerBut.setVisible(false); break;
/*SS*/	case 6: urunlerBut.setVisible(false); calisanlarBut.setVisible(false); satisBut.setVisible(false); 
				raporlarBut.setVisible(false); alisBut.setVisible(false); tedarikcilerBut.setVisible(false); break;
		}
//------------------------------------------------------------------------------------------------------------------	
		
		
		solPanel.add(solGenelMenu);
		
		satisPanel = new SatisPanel();
		karsilamaPanel = new KarsilamaPanel();
		alisPanel = new AlisPanel();
		urunlerPanel = new UrunlerPanel();
		tedarikciPanel = new TedarikciPanel();
		musteriPanel = new MusteriPanel();
		calisanlarPanel = new CalisanlarPanel();
		kullaniciAyarPanel = new KullaniciAyarPanel();
		proje = new ExtraSinifDiyagrami();
		
		
		anaPanel.add(karsilamaPanel.panel()); karsilamaPanel.panelSetVisible(true);
		anaPanel.add(satisPanel.panel()); satisPanel.panelSetVisible(false);
		anaPanel.add(alisPanel.panel()); alisPanel.panelSetVisible(false);
		anaPanel.add(urunlerPanel.panel()); urunlerPanel.panelSetVisible(false);
		anaPanel.add(tedarikciPanel.panel()); tedarikciPanel.panelSetVisible(false);
		anaPanel.add(musteriPanel.panel()); musteriPanel.panelSetVisible(false);
		anaPanel.add(calisanlarPanel.panel()); calisanlarPanel.panelSetVisible(false);
		anaPanel.add(kullaniciAyarPanel.panel()); kullaniciAyarPanel.panelSetVisible(false);
		anaPanel.add(proje.diagramPanel()); proje.setVisibleDiagramPanel(false);
		anaPanel.add(proje.kaynakcaPanel()); proje.setVisibleKaynakcaPanel(false);
		
		cont.add(logo);
		cont.add(solPanel);
		cont.add(anaPanel);
		
		this.add(cont);
		
		
		//------------------------ÜST MENÜ---------------------DENEME FRAMEDEKÝ DÜZENLENECEK-------------
		JMenuBar ustMenu = new JMenuBar();
		JMenu dosya = new JMenu("Dosya");
		JMenu proje = new JMenu("Proje");
		JMenu hakkinda = new JMenu("Hakkýnda");
		
		cikis = new JMenuItem("Çýkýþ Yap");
		oturumKapat = new JMenuItem("Oturumu Kapat");
		kasa = new JMenuItem("Kasa");
		version = new JMenuItem("Versiyon");
		diagram = new JMenuItem("Sýnýf Diyagramý");
		
		kaynakca = new JMenuItem("Kaynakça");
		
		ustMenu.add(dosya);
		ustMenu.add(proje);
		ustMenu.add(hakkinda);
		proje.add(diagram);
		proje.add(kaynakca);
		dosya.add(kasa);
		dosya.add(oturumKapat);
		dosya.add(cikis);
		hakkinda.add(version);
		
		kasa.addActionListener(this);
		cikis.addActionListener(this);
		version.addActionListener(this);
		oturumKapat.addActionListener(this);
		diagram.addActionListener(this);
		kaynakca.addActionListener(this);
		
		ImageIcon icon = new ImageIcon("Images/zIconFinal.png");
		this.setJMenuBar(ustMenu);
		this.setIconImage(icon.getImage());
		this.setLayout(null);
		this.setTitle("Depom Sepette");
		this.setSize(1295, 780); //1280-720 + saðsoltotal15 titlebar+38ye //menü bar öncesi#1295, 758# +22 menübar
		this.getContentPane().setBackground(Color.darkGray);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() { //internet
			@Override
			public void windowClosing(WindowEvent e) {
				new CikisUyariFrame();
			}
		});
	}


	JMenuItem version;
	JMenuItem oturumKapat;
	JMenuItem kaynakca;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==satisBut) {
			karsilamaPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(false);
			satisPanel.panelSetVisible(true);
		}
		if(e.getSource()==alisBut) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(false);
			alisPanel.panelSetVisible(true);
			JOptionPane.showMessageDialog(null, "Bir sonraki yamada aktif olacaktýr.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getSource()==urunlerBut) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(false);
			urunlerPanel.tabloCek();
			urunlerPanel.panelSetVisible(true);
		}
		
		if(e.getSource()==tedarikcilerBut) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(false);
			tedarikciPanel.panelSetVisible(true);
		}
		
		if(e.getSource()==musterilerBut) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(false);
			musteriPanel.panelSetVisible(true);
		}
		
		if(e.getSource()==calisanlarBut) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(false);
			calisanlarPanel.panelSetVisible(true);
		}
		
		if(e.getSource()==raporlarBut) {
			JOptionPane.showMessageDialog(null, "Veritabaný kurulma aþamasýnda.", "Depom Sepette", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getSource()==kullaniciAyarBut) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(false);
			kullaniciAyarPanel.panelSetVisible(true);
			
		}
		
		if(e.getSource()==cikisBut || e.getSource()==cikis) {
			new CikisUyariFrame();
		}
		if(e.getSource()==kasa) {
			new Kasa().getParaDurumu();
		}
		if(e.getSource()==version) {
			JOptionPane.showMessageDialog(null, "Depom Sepette - Version: Beta v1.0", "Depom Sepette", JOptionPane.DEFAULT_OPTION);
		}
		if(e.getSource()==oturumKapat) {
			new CikisUyariFrame(this); //frame i yolluyoruz
		}
		if(e.getSource()==diagram) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			proje.setVisibleKaynakcaPanel(false);
			proje.setVisibleDiagramPanel(true);
		}
		
		if(e.getSource()==kaynakca) {
			karsilamaPanel.panelSetVisible(false);
			satisPanel.panelSetVisible(false);
			alisPanel.panelSetVisible(false);
			urunlerPanel.panelSetVisible(false);
			musteriPanel.panelSetVisible(false);
			calisanlarPanel.panelSetVisible(false);
			tedarikciPanel.panelSetVisible(false);
			kullaniciAyarPanel.panelSetVisible(false);
			proje.setVisibleDiagramPanel(false);
			proje.setVisibleKaynakcaPanel(true);
		}
	}
	
	
}
