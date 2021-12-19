package modelo;

import javax.swing.JOptionPane;

public class Peao extends Peca {
	private boolean primeiroMovimento = true;
	private int linhaTeste;


	public Peao(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "C:/figs/PEAO"+cor+".png");
	}
	//PEÃO OK
	public Peao(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);//
		//Y= LINHA
		//X = COLUNA
		//Math.abs = valor absoluto (tira o negativo e vai pro outro lado)
		
		if(getCor().equals(EnumCor.PRETO)) {//para baixo == preto pois é peão e só se move em uma direção
			if(linhaDestino <= getLinha()) {//não ir para tras
				return false;
			}
			if(colunaDestino==getColuna()) {//apenas na mesma coluna
				if(getLinha() == 1) {//se for na primeira linha(casa inicial) 
					if((linhaDestino-getLinha())==1 && pecaDestino == null ){//mover uma linha(casa) para frente na primeira
						return true;
					}else if((linhaDestino-getLinha())==2 && pecaDestino == null && getTabuleiro().getPeca(linhaDestino-1, colunaDestino) == null){//mover duas linhas(casas) para frente se for vazio o destino e a casa entre ela
						return true;
					}
				}else if((linhaDestino-getLinha())==1 && pecaDestino == null){//mover uma linha(casa) para frente nas jogadas subsequentes
					return true;
				}
			}else{// a coluna é outra?
				if( (linhaDestino-getLinha()==1) && Math.abs(getColuna()-colunaDestino)==1){//mover diagonal com apenas uma coluna de diferença
					if(pecaDestino != null  && pecaDestino.getCor()!=this.getCor()){//só andar diagonal se houver peca da cor oposta
						return true;
					}
				}
			}
		}else{//tratativa de mover para cima, regra para o oposto do tabuleiro mover para baixo
			if(linhaDestino>=getLinha()){
				return false;
			}
			if(colunaDestino==getColuna()){
				if(getLinha()==6){//se se for na sexta linha(casa inicial) 
					if((linhaDestino-getLinha())==-1 && pecaDestino == null ){
						return true;
					}else if((linhaDestino-getLinha())==-2 && pecaDestino == null && getTabuleiro().getPeca(linhaDestino+1, colunaDestino) == null){
						return true;
					}
				}else if((linhaDestino-getLinha())==-1 && pecaDestino == null ){
					return true;
				}
								
			}else{
				if( (linhaDestino-getLinha()==-1) && Math.abs(this.getColuna()-colunaDestino)==1){
					if(pecaDestino != null && pecaDestino.getCor()!=this.getCor()){
						return true;
					} 
				}
			}
			
		}
		return false;
		
		
		/*
		if(pecaDestino == null && colunaDestino != getColuna()) {//não pemite mover para outra coluna se estiver vazio
			return false;
		}
		return true;*/
	}

	public void setPrimeiroMovimento(boolean primeiroMovimento) {
		this.primeiroMovimento = primeiroMovimento;
	}
	
	
}
