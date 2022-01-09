package zProjeFinal;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CikisUyariFrame {
	
	CikisUyariFrame() {
		if(engel() == true) {
			JOptionPane.showMessageDialog(null, "SEPETTE �R�N VAR!!! L�tfen sepeti bo�alt�n�z.", "Depom Sepette", JOptionPane.ERROR_MESSAGE);
		} else
			if(cikisUyari()==0) {
				System.exit(0);
			}
		
	}
	
	CikisUyariFrame(JFrame f) {
		if(engel() == true) {
			JOptionPane.showMessageDialog(null, "SEPETTE �R�N VAR!!! L�tfen sepeti bo�alt�n�z.", "Depom Sepette", JOptionPane.ERROR_MESSAGE);
		} else
			if(cikisUyari()==0) {
				f.dispose();
				new GirisFrame().GFrameRun();
			}
		
		
	}
	
	private int cikisUyari() {
		ImageIcon icon = new ImageIcon("Images/exitico.png");
		String[] responses= {"Evet","�ptal"};
		
		int answer =JOptionPane.showOptionDialog(
				null, //component
				"��kmak istedi�inize emin misiniz?", //message 
				"Depom Sepette", //title
				JOptionPane.OK_CANCEL_OPTION, //option type 
				JOptionPane.WARNING_MESSAGE, //message type
				icon, // icon
				responses, // option da yazacaklar 0-1-2 olarak de�i�tirir
				0);
		return answer; //evet ��k�� yap 0
		 				// iptal 1 - �arp� ile kapatma -1
	}
	
	private boolean engel() {
		boolean engel=false;
		if(SatisPanel.sepetSatisDolu==true) {
			engel=true;
		}
		
		return engel;
	}
	
	
}
