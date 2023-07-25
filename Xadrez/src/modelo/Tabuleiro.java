package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import controle.ControlaTempo;
import visão.JXadrez;


public class Tabuleiro {
	private Peca[][] pecas;
	private Peca pecaSelecioada = null;
	private EnumCor vez = EnumCor.BRANCO;
	public static final int TEMPO_JOGADA = 10000;//tempo de cada jogada em milisegundos(100 = 1 segundo),não colocar abaixo de 5 segundos
	private ControlaTempo controleTempo;
	private List<Peca> pecasForaDeJogo;
	public Tabuleiro(ControlaTempo controleTempo) {
		this.controleTempo = controleTempo;
		this.pecas = new Peca[8][8];
		this.pecasForaDeJogo = new ArrayList<>();
		
		//inicializa peças na posicao inicial relativa das peças
		Torre torreBranco1 = new Torre(EnumCor.BRANCO,7,0);
		Torre torreBranco2 = new Torre(EnumCor.BRANCO,7,7);
		this.adicionaPeca(torreBranco1);
		this.adicionaPeca(torreBranco2);
		
		Torre torrePreto1 = new Torre(EnumCor.PRETO,0,0);
		Torre torrePreto2 = new Torre(EnumCor.PRETO,0,7);
		this.adicionaPeca(torrePreto1);
		this.adicionaPeca(torrePreto2);
		
		Cavalo cavaloBranco1 = new Cavalo(EnumCor.BRANCO,7,1);
		Cavalo cavaloBranco2 = new Cavalo(EnumCor.BRANCO,7,6);
		this.adicionaPeca(cavaloBranco1);
		this.adicionaPeca(cavaloBranco2);
		
		Cavalo cavaloPreto1 = new Cavalo(EnumCor.PRETO,0,1);
		Cavalo cavaloPreto2 = new Cavalo(EnumCor.PRETO,0,6);
		this.adicionaPeca(cavaloPreto1);
		this.adicionaPeca(cavaloPreto2);
		
		Bispo bispoBranco1 = new Bispo(EnumCor.BRANCO,7,2);
		Bispo bispoBranco2 = new Bispo(EnumCor.BRANCO,7,5);
		this.adicionaPeca(bispoBranco1);
		this.adicionaPeca(bispoBranco2);
		
		Bispo bispoPreto1 = new Bispo(EnumCor.PRETO,0,2);
		Bispo bispoPreto2 = new Bispo(EnumCor.PRETO,0,5);
		this.adicionaPeca(bispoPreto1);
		this.adicionaPeca(bispoPreto2);
		
		Rei reiBranco = new Rei(EnumCor.BRANCO,7,3);
		this.adicionaPeca(reiBranco);
		Rei reiPreto = new Rei(EnumCor.PRETO,0,4);
		this.adicionaPeca(reiPreto);
		
		Rainha rainhaBranco = new Rainha(EnumCor.BRANCO,7,4);
		this.adicionaPeca(rainhaBranco);
		Rainha rainhaPreto = new Rainha(EnumCor.PRETO,0,3);
		this.adicionaPeca(rainhaPreto);
		
		for(int i = 0 ; i < 8 ; i++) {
			Peca peaoBranco = new Peao(EnumCor.BRANCO,6,i);
			this.adicionaPeca(peaoBranco);
			Peca peaoPreto = new Peao(EnumCor.PRETO,1,i);
			this.adicionaPeca(peaoPreto);
		}		
	}
	
	//retorna posicao da peca
	public Peca	getPeca(int linha,int coluna) {
		return this.pecas[linha][coluna];
	}
	
	public List<Peca> getPecasForaDeJogo() {
		return this.pecasForaDeJogo;
	}

	public EnumCor getVez() {
		return this.vez;
	}
	
	public void setPecaSelecioada(Peca peca) {
		this.pecaSelecioada = peca;
	}

	public Peca getPecaSelecioada() {
		return this.pecaSelecioada;
	}

	public void setPeca(Peca peca) {
		//define nova posição da peça e define tabuleiro na peca
		this.pecas[peca.getLinha()][peca.getColuna()] = peca;
		peca .setTabuleiro(this);
	}
	public void adicionaPeca(Peca peca) {
		this.pecas[peca.getLinha()][peca.getColuna()]= peca;
		peca.setTabuleiro(this);
	}
	public void selecionaPeca(Peca peca) {
		if(peca.isSelecionada()){//se peça ja estiver selecionada, desmarca a peça
			peca.setSelecionada(false);
			this.pecaSelecioada = null;
		}else {//se nenhuma peça estiver selecionada, seleciona a peça
			peca.setSelecionada(true);
			this.pecaSelecioada = peca;
		}
	}
	
	public void movePeca(Peca peca, int novaLinha,int novaColuna) {
		//iguala casa original da peca a null(esvazia) e então define nova posição, passa vez.
		if(peca.validaMovimento(novaLinha, novaColuna)) {
			this.pecas[peca.getLinha()][peca.getColuna()] = null;
			peca.setLinha(novaLinha);
			peca.setColuna(novaColuna);
			if(peca instanceof Peao) {//checa se peca selecionada é um peao
				if(peca.getLinha()==7 || peca.getLinha()==0) {//checa se chegou na ultima linha para poder transformar
					Object [] itens = {"Rainha","Cavalo","Bispo","Torre"};
					Object selectedValue = JOptionPane.showInputDialog(null,"Escolha uma peça","Tranformar Peão",JOptionPane.PLAIN_MESSAGE,null,itens,itens[0]);
					if(selectedValue == "Rainha") {
						peca = new Rainha(vez, novaLinha, novaColuna);
					}else if(selectedValue =="Cavalo") {
						peca = new Cavalo(vez, novaLinha, novaColuna);
					}else if(selectedValue =="Bispo") {
						peca = new Bispo(vez, novaLinha, novaColuna);
					}else if(selectedValue =="Torre") {
						peca = new Torre(vez, novaLinha, novaColuna);
					}else {
						peca = new Rainha(vez, novaLinha, novaColuna);
					}
					this.selecionaPeca(peca);//para 'desselecionar' o peão transformado e ele não jogar duas vezes
				}
			}
			this.setPeca(peca);
			this.selecionaPeca(peca);//para 'desselecionar outra peca, no caso do peão ocorre duas vezes se for promovido, mas não foi observado nenhum erro
			this.inverteVez();
		}
	}
	public void inverteVez() {//PS: "==" compara endereço de memoria, que é diferente nos objetos, por isso o equals que compara informação do objeto
		if(this.vez.equals(EnumCor.BRANCO)) {
			this.vez = EnumCor.PRETO;
		}else {
			this.vez = EnumCor.BRANCO;
		}
		JXadrez.setVez(this.vez);
		controleTempo.zeraCronometro();
	}
	
	
	public void realizaJogada(int linha, int coluna) {
		Peca peca = this.getPeca(linha, coluna);
		//se a não houver peca selecionada, selecionar um campo onde exista peça com a vez atual
		if(this.pecaSelecioada == null) {
			if(peca != null && peca.getCor().equals(this.vez)) {
				this.selecionaPeca(peca);
			}
		}else {//se selecionar uma peça já selecionada, deseleciona a peca ou seleciona uma diferente
			if(this.pecaSelecioada == peca) {
				this.selecionaPeca(peca);
			}else {
				if(peca == null) {//se não houver peca no destino mover para linha e coluna
					this.movePeca(this.pecaSelecioada, linha, coluna);
				}else if(peca != null && peca.getCor().equals(this.vez)) {//se der nullPointer exception é aqui, seleciona outra peca da mesma cor e desceleciona a atual
					this.selecionaPeca(this.pecaSelecioada);
					this.selecionaPeca(peca);
				}
				if(peca != null && !peca.getCor().equals(this.pecaSelecioada.getCor())) { // comer peca adversaria, peca nao nula e com a cor oposta
					peca.setEliminada(true);
					this.pecasForaDeJogo.add(peca);
					this.movePeca(this.pecaSelecioada, linha, coluna);
				}
			}
		}
	}
	public void testaXeque() {
		//Pecas vez, rei como capturado
	}
	
}
