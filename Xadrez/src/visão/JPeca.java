package visão;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modelo.Peca;

public class JPeca extends JLabel{
	
	private Peca peca;

	public JPeca(Peca peca) {
		this.peca = peca;
		this.setIcon(new ImageIcon(peca.getImagem()));
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	
	
}
