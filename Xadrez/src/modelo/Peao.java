package modelo;

import javax.swing.JOptionPane;


public class Peao extends Peca {

	public Peao(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "/home/izanami/figs/PEAO"+cor+".png");
	}
	//PEAO OK
	public Peao(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);
		//Y= LINHA
		//X = COLUNA
		//Math.abs = valor absoluto (tira o negativo e vai pro outro lado)
		
		if(getCor().equals(EnumCor.PRETO)) {//para baixo == preto pois e peao e so se move em uma direcao
			if(linhaDestino <= getLinha()) {//nao ir para tras
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
			}else{// a coluna e outra?
				if( (linhaDestino-getLinha()==1) && Math.abs(getColuna()-colunaDestino)==1){//mover diagonal com apenas uma coluna de diferenca
					if(pecaDestino != null  && pecaDestino.getCor()!=this.getCor()){//so andar diagonal se houver peca da cor oposta
						return true;
					}
				}
			}
		}else{//tratativa de mover para cima, regra para o oposto, peças brancas
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
	
	}
	
}
