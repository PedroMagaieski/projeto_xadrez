package controle;

import javax.swing.JOptionPane;

public class ControlaPromo implements Runnable{
	public int escolha;
	@Override
	public void run() {
		while(true) {
			this.escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o que deseja transformar o peao:"
					+ "\n-1 Rainha"
					+ "\n-2 Cavalo"
					+ "\n-3 Bispo"
					+ "\n-4 Torre"));
		}
	}
	public int getEscolha() {
		return escolha;
	}
	public void setEscolha(int escolha) {
		this.escolha = escolha;
	}
	
}
