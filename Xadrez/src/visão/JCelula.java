package visão;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JCelula extends JPanel{
	
	private JPeca jPeca;
	private int linha,coluna;
	
	public JCelula(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public JCelula(JPeca jPeca) {
		this.jPeca = jPeca;
		this.linha = jPeca.getPeca().getLinha();
		this.coluna = jPeca.getPeca().getColuna();
		this.add(jPeca);
		if((jPeca.getPeca() != null) && jPeca.getPeca().isSelecionada()) {
			this.setBorder(BorderFactory.createLineBorder(Color.GREEN,5));
		}
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
}
