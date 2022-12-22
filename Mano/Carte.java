package carte;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class Carte extends JPanel{
	
	int valeur;
	String[] type = {"c","d","h","s"};
	String[] name = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	String imgName;
	int indice;

	public void setValeur(int val) {
		if(val < 1) {
			valeur = 1;			
		}else if(val > 13) {
			valeur = 13;
		}else {
			valeur = val;	
		}
	}

	public void setType(int ind){
		indice = ind;
	}

	public String getType() {
		return type[indice];		
	}

	
	public String getImgName() {
		return name[getValeur()-1]+getType();
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public boolean isMamo() {
		boolean flag = true;
		if(getValeur()<10) {
			flag = false;
		}
		return flag;
	}

	public void drawCarte(Graphics2D g2d,int x,int y) {
		Image carte = Toolkit.getDefaultToolkit().getImage("img\\"+getImgName()+".gif");
		g2d.drawImage(carte, x, y, 60, 90, getFocusCycleRootAncestor());
	}
	
}
