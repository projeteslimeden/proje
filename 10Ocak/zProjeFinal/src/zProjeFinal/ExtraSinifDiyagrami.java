package zProjeFinal;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExtraSinifDiyagrami {
	
	private JPanel sinifDiagram, kaynakca;
	
	public JPanel diagramPanel() {
		sinifDiagram = new JPanel();
		sinifDiagram.setLayout(null);
		sinifDiagram.setBounds(0, 0, 1030, 680);
		sinifDiagram.setBackground(Color.black);
		
		ImageIcon sImageIcon = new ImageIcon("Images/SýnýfDiyagramý.png");//fotoyu image icona kaydetti //s=selected
		Image sImage = sImageIcon.getImage();//imageicon u image e kaydetti
		Image yeniBoyutImage = sImage.getScaledInstance(1030, 680, Image.SCALE_SMOOTH); //image yeniden boyutlandýrma ve yeni kaydetme
		ImageIcon diagram = new ImageIcon(yeniBoyutImage);// yeniden boyutlandýrýlmýþ image i image icona kaydetti
		
	
		JLabel diagramLbl = new JLabel();
		diagramLbl.setBounds(10, 10, 1010, 660);
		diagramLbl.setIcon(diagram);
		sinifDiagram.add(diagramLbl);
	
		
		return sinifDiagram;
	}
	
	public JPanel kaynakcaPanel() {
		kaynakca = new JPanel();
		kaynakca.setLayout(null);
		kaynakca.setBounds(0, 0, 1030, 680);
		kaynakca.setBackground(Color.darkGray);
		
		
		JLabel Lb1 = new JLabel("Swing -> youtube.com/watch?v=xk4_1vDrzzo");
		Lb1.setForeground(Color.white);
		Lb1.setBounds(10, 310, 660, 20);
		kaynakca.add(Lb1);
		
		JLabel Lb2 = new JLabel("MySQL -> youtube.com/watch?v=hRnXI1qo0G0&list=PLzIWkToFwqHTXlvGnO47P71qTXAyb3pMZ");
		Lb2.setForeground(Color.white);
		Lb2.setBounds(10, 350, 660, 20);
		kaynakca.add(Lb2);
		
		
		return kaynakca;
	}
	
	
	public void setVisibleDiagramPanel(boolean a) {
		sinifDiagram.setVisible(a);
	}
	
	public void setVisibleKaynakcaPanel(boolean a) {
		kaynakca.setVisible(a);
	}
	
	
}
