package modelo;

public class Cavalo extends Peca{
	
	public Cavalo(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "C:/figs/CAVALO"+cor+".png");
	}

	public Cavalo(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	
	//CAVALO OK
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		//variaveis de deslocamento do cavalo
		int colunaOffset=Math.abs(getColuna()-colunaDestino);
		int linhaOffset=Math.abs(getLinha()-linhaDestino);
		
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);
		
		if((colunaOffset==1 && linhaOffset==2) || (colunaOffset==2 && linhaOffset==1) ){//permite mover uma coluna e duas linhas ou duas colunas e uma linha
		   if(pecaDestino != null){
		   		if(pecaDestino.getCor()!=this.getCor()){//se cor da peça no destino for diferente da atual permite "comer"
		   			return true;
		   		}	
		   }else{
		    	return true;
		   }
		}
		
		return false;
	}
}
