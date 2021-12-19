package modelo;

public class Rei extends Peca{
	
	public Rei(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "C:/figs/REI"+cor+".png");
	}
	
	public Rei(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		int colunaOffset=Math.abs(getColuna()-colunaDestino);//deslocamento coluna
		int linhaOffset=Math.abs(getLinha()-linhaDestino);//deslocamento linha
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);//local de destino
		
		if(colunaOffset==0 && linhaOffset==0){//não permite se deslocar para a mesma casa
			return false;
		}
		if(colunaOffset>1 || linhaOffset>1){//deslocamento de apenas uma casa
		    return false;
		}
		if(pecaDestino != null && pecaDestino.getCor()==this.getCor() ){//não permite passar por peças da mesma cor, apenas oposta
		    return false;
		}		             
		return true;
	}
}
