package visão;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import controle.ControlaTempo;
import modelo.EnumCor;
import modelo.Tabuleiro;

public class JXadrez extends JFrame{
	private static JLabel lbVez;
	private Tabuleiro tabuleiro;
	private final JButton btReiniciarJogo;
	private final ControlaTempo controleTempo;
	private final JTabuleiro jTabuleiro;
	public static final JPanel cemiterio = new JPanel();;
	private boolean xeque;
	public JProgressBar pbTempo;
	
	//criar janela do jogo de xadrez
	public JXadrez() {
		
		//painel de titulo
		setTitle("Jogo de Xadrez");
		this.setLayout(new BorderLayout());
		//pb tempo inicia o contador de tempo de cada jogada, e usado na barra inferior
		pbTempo = new JProgressBar();
		pbTempo.setMinimum(0);//tamanho minimo da barra de tempo
		pbTempo.setMaximum(Tabuleiro.TEMPO_JOGADA);//tamanho maximo da barra de tempo
		this.controleTempo = new ControlaTempo(pbTempo);
		this.tabuleiro = new Tabuleiro(controleTempo);
		this.jTabuleiro = new JTabuleiro(tabuleiro);
		controleTempo.setJTabuleiro(jTabuleiro);
		this.add(jTabuleiro, BorderLayout.CENTER);
		
		//painel do topo
		JPanel pnTopo = new JPanel();
		lbVez = new JLabel("VEZ DE: PRETO");
		pnTopo.add(lbVez);
		this.add(pnTopo, BorderLayout.NORTH);
		
		//painel da lateral
		JPanel pnLateral = new JPanel();
		pnLateral.setLayout(new GridLayout(10,1));
		btReiniciarJogo = new JButton("Reiniciar jogo");
		btReiniciarJogo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {				
				reiniciaJogo();
			}
		});
		
		//inicia painel do cemiterio ao leste
		/*cemiterio.setLayout(new FlowLayout());
		this.add(cemiterio, BorderLayout.EAST);*/
		
		
		pnLateral.add(btReiniciarJogo);
		this.add(pnLateral,BorderLayout.WEST);
		
		//painel inferior
		this.add(pbTempo, BorderLayout.SOUTH);
		//PS:pn=painel & lb=label & bt= button
		
		//botao de fechar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//controle de tempo de execucao de thread PS: nao executar a mesma mais de uma vez
		Thread threadTempo = new Thread(controleTempo);
		threadTempo.start();
		
		
		
		this.pack();
		this.setVisible(true);
	}
	
	//metodo para reiniciar tabuleiro, pois o actionPerformed nao aceita variaveis dessa classe, apenas metodos
	private void reiniciaJogo() {
		controleTempo.zeraCronometro();
		tabuleiro = new Tabuleiro(controleTempo);
		this.jTabuleiro.setTabuleiro(tabuleiro);
		this.jTabuleiro.desenhaTabuleiro();
		setVez(tabuleiro.getVez());
	}
	
	public static void setVez(EnumCor corVez) {
		lbVez.setText("VEZ DE: "+ corVez);
	}

	public boolean isXeque() {
		return xeque;
	}

	public void setXeque(boolean xeque) {
		this.xeque = xeque;
	}
	
}
