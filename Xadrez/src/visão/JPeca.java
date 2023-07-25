package visão;

import javax.swing.*;

import modelo.Peca;

import java.awt.*;

public class JPeca extends JLabel{
	
	private Peca peca;

	public JPeca(Peca peca) {
		this.peca = peca;
//		ORIGINAL, puxar tipo de peca e cor, como colocar esse peca.getImagem() dentro do getResource sem ser null?
//		this.setIcon(new ImageIcon(peca.getImagem()));
		String imagem = peca.getImagem();
//		System.out.println(imagem);
		this.setIcon(new ImageIcon(getClass().getResource(imagem)));
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	
	
}
