package modelo;

public class Bispo extends Peca {
	
	public Bispo(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "C:/figs/BISPO"+cor+".png");
	}

	public Bispo(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		int colunaOffset=colunaDestino-getColuna();//deslocamento coluna
		int linhaOffset=linhaDestino-getLinha();//deslocamento linha
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);//local de destino
		int dir=0;//dire��o da diagonal que deseja mover
		
		if((Math.abs(colunaOffset)!=Math.abs(linhaOffset)) || (colunaOffset==0 && linhaOffset==0)){//n�o permite deslocamento de linha maior que coluna e vice-versa, e n�o permite deslocamento de linha e coluna iguais a zero(movimento n�o diagonal)
			return false;
		}
		
		if(colunaOffset>0 && linhaOffset>0 ){//deslocamento de linha e coluna positivos, diagonal norte-leste
			dir =1;
		}else if(colunaOffset>0 && linhaOffset<0){//deslocamento de coluna positivo e linha negativo, diagonal sul-leste
			dir=4;
		}else if(colunaOffset<0 && linhaOffset>0){//deslocamento de coluna negativo e linha positivo, diagonal norte-oeste
			dir=2;
		}else{//deslocamento de coluna e linha negativos, diagonal sul-oeste
			dir=3;
		}
		
		
		int x=getColuna(),y=getLinha();//define coluna atual como eixo x e linha atual como eixo y para poder incrementar e mover mais de uma de cada vez
		
		//**VER AQUI
		//Lugar l=null; //equivalente �:  
		//Peca l = null; //esta como privado no tabuleiro
		
		while(!(x==colunaDestino && y==linhaDestino)){// move a eixo x e y na dire��o do deslocamento at� alcan�ar o destino de linha e coluna, ent�o encerra o la�o
			switch(dir){
				case 1:
					y++;
					x++;
				break;
				case 2:
					x--;
					y++;
				break;
				case 3:
					x--;
					y--;
				break;
				case 4:
					x++;
					y--;
				break;
			}
			if(getTabuleiro().getPeca(y,x) != null){//se houver uma pe�a dentro do deslocamento da peca(n�o no destino, apenas na viagem) interrompe la�o e retorna falso no final;
				break;
			}			
		}
		
		if(x==colunaDestino && y==linhaDestino){//se eixo x e y forem iguais a coluna e linha de destino(chegou ao destino) ent�o:
			if(pecaDestino == null){//permite mover at� uma casa vazia
				return true;
			}
			if(pecaDestino.getCor()!=this.getCor()){//pemite comer pe�as da cor oposta
				return true;
			}
		}
		return false;
		
	}
}
