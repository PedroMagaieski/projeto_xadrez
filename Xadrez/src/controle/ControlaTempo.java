package controle;

import java.awt.Color;
import java.lang.Runnable;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;


import modelo.Tabuleiro;
import visão.JTabuleiro;

public class ControlaTempo implements Runnable{
		
	private JTabuleiro jTabuleiro;
	
	private int tempoGasto = 0;
	
	private JProgressBar barraProgresso;

	
	public ControlaTempo(JProgressBar pbTempo) {
		super();
		this.barraProgresso = pbTempo;
	}
	
	public void zeraCronometro() {
		this.tempoGasto = 0;
	}
	

	public void setJTabuleiro(JTabuleiro jTabuleiro) {
		this.jTabuleiro = jTabuleiro;
	}

	@Override
	public void run() {
		while(true) {
			try {//espera 1 sec, adiciona 1 sec, para ao alcancar limite e desceleciona peca selecionada
				Thread.sleep(1);
				tempoGasto += 1;
				barraProgresso.setValue(tempoGasto);
				//mudar cor da barra de progresso ao longo do tempo
				if(tempoGasto >= 0 && tempoGasto <Tabuleiro.TEMPO_JOGADA/2) {
					barraProgresso.setForeground(Color.GREEN);
				}else if(tempoGasto >=Tabuleiro.TEMPO_JOGADA/2 && tempoGasto <(Tabuleiro.TEMPO_JOGADA)*3/4) {
					barraProgresso.setForeground(Color.YELLOW);
				}else if(tempoGasto >=Tabuleiro.TEMPO_JOGADA/4) {
					barraProgresso.setForeground(Color.RED);
				}
				//interrompe vez ao chegar no tempo maximo de jogada
				if(tempoGasto >= Tabuleiro.TEMPO_JOGADA) {
					JOptionPane.showMessageDialog(null, "o jogador "+ jTabuleiro.getTabuleiro().getVez()+ " perdeu a vez!");
					if(jTabuleiro.getTabuleiro().getPecaSelecioada() != null) {						
						jTabuleiro.getTabuleiro().getPecaSelecioada().setSelecionada(false);//desceleciona peca se jogada não foi realizada
						jTabuleiro.getTabuleiro().setPecaSelecioada(null);
					}
					jTabuleiro.getTabuleiro().inverteVez();
					jTabuleiro.desenhaTabuleiro();
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
