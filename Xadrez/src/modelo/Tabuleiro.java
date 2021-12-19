package modelo;


import java.util.ArrayList;
import java.util.List;

import controle.ControlaTempo;
import vis�o.JXadrez;

public class Tabuleiro {
	private Peca[][] pecas;
	private Peca pecaSelecioada = null;
	private EnumCor vez = EnumCor.PRETO;
	public static final int TEMPO_JOGADA = 10000;//tempo de cada jogada em milisegundos(100 segundos)
	private ControlaTempo controleTempo;
	private List<Peca> pecasForaDeJogo;
	
	public Tabuleiro(ControlaTempo controleTempo) {
		//int linhas, int colunas
		//this.pecas = new Peca[linhas][colunas];
		this.controleTempo = controleTempo;
		this.pecas = new Peca[8][8];
		this.pecasForaDeJogo = new ArrayList<>();
		
		
		//inicializa pe�as na posi��o inicial relativa das pe�as
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
			Peao peaoBranco = new Peao(EnumCor.BRANCO,6,i);
			this.adicionaPeca(peaoBranco);
			Peao peaoPreto = new Peao(EnumCor.PRETO,1,i);
			this.adicionaPeca(peaoPreto);
		}
	}
	
	//retorna posi��o da pe�a
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
		//define nova posi��o da pe�a e define tabuleiro na peca
		this.pecas[peca.getLinha()][peca.getColuna()] = peca;
		peca .setTabuleiro(this);
	}
	public void adicionaPeca(Peca peca) {
		this.pecas[peca.getLinha()][peca.getColuna()]= peca;
		peca.setTabuleiro(this);
	}
	public void selecionaPeca(Peca peca) {
		if(peca.isSelecionada()){//se pe�a j� estiver selecionada, desmarca a pe�a
			peca.setSelecionada(false);
			this.pecaSelecioada = null;
		}else {//se nenhuma pe�a estiver selecionada, seleciona a pe�a
			peca.setSelecionada(true);
			this.pecaSelecioada = peca;
		}
	}
	
	public void movePeca(Peca peca, int novaLinha,int novaColuna) {
		//iguala casa original da peca a null(esvazia) e ent�o define nova posi��o, passa vez.
		if(peca.validaMovimento(novaLinha, novaColuna)) {
			this.pecas[peca.getLinha()][peca.getColuna()] = null;
			peca.setLinha(novaLinha);
			peca.setColuna(novaColuna);
			if(peca instanceof Peao) {//checa se peca selecionada � um peao e ap�s mover muda o primeiro movimento para falso
				Peao peao = (Peao) peca;
				peao.setPrimeiroMovimento(false);
			}
			this.setPeca(peca);
			this.selecionaPeca(peca);
			this.inverteVez();
		}
	}
	public void inverteVez() {//PS: "==" compara endere�o de memoria, que � diferente nos objetos, por isso o equals que compara informa��o do objeto
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
		//se a n�o houver peca selecionada, selecionar um campo onde exista pe�a com a vez atual
		if(this.pecaSelecioada == null) {
			if(peca != null && peca.getCor().equals(this.vez)) {
				this.selecionaPeca(peca);
			}
		}else {//se selecionar uma pe�a j� selecionada, deseleciona a peca ou seleciona uma diferente
			if(this.pecaSelecioada == peca) {
				this.selecionaPeca(peca);
			}else {
				if(peca == null) {//se n�o houver peca no destino mover para linha e coluna
					this.movePeca(this.pecaSelecioada, linha, coluna);
				}else if(peca != null && peca.getCor().equals(this.vez)) {//se der nullPointer exception � aqui, seleciona outra peca da mesma cor e desceleciona a atual
					this.selecionaPeca(this.pecaSelecioada);
					this.selecionaPeca(peca);
				}
				if(peca != null && !peca.getCor().equals(this.pecaSelecioada.getCor())) { // comer peca adversaria, peca n�o nula e com a cor oposta
					peca.setEliminada(true);
					this.pecasForaDeJogo.add(peca);
					this.movePeca(this.pecaSelecioada, linha, coluna);
				}
			}
		}
	}
	
}
