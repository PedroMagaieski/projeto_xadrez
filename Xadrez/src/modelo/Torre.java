package modelo;

public class Torre extends Peca{
	
	
	public Torre(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "C:/figs/TORRE"+cor+".png");
	}

	public Torre(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		int colunaOffset=getColuna()-colunaDestino;//deslocamento coluna
		int linhaOffset=getLinha()-linhaDestino;//deslocamento linha
		int dir=0;//dire��o que deseja mover
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);//local de destino
		
		if((colunaOffset!=0 && linhaOffset!=0)|| (colunaOffset==0 && linhaOffset==0)){//n�o permite deslocamento de linha e coluna diferentes de zero(movimento diagonal), e n�o permite deslocamento de linha e coluna iguais a zero
			return false;
		}
		
		if(colunaOffset==0){//se deslocamento de coluna for igual a zero ent�o:(mesma coluna)
			if(linhaOffset>0){//se deslocamento de linha for positivo, a dire��o sera 1
				dir=1;
			}else if(linhaOffset<0){//se deslocamento de linha for negativo, a dire��o sera 3
				dir=3;
			}
		}
		
		if(linhaOffset==0){// se deslocamento de linha for igual zero: (mesma linha)
		
			if(colunaOffset>0){//se deslocamento de coluna for positivo, dire��o sera 4
				dir=4;
			}else if(colunaOffset<0){//se deslocamento de coluna for negativo, dire��o sera 2
				dir=2;
			}
		
		}
		
		int x=getColuna(),y=getLinha();//define coluna atual como eixo x e linha atual como eixo y para poder incrementar e mover mais de uma de cada vez
		
		//VER AQUI
		//Lugar l=null;
		
		while(!(x==colunaDestino && y==linhaDestino)){//enquanto linha e colunas atuais n�o forem iguais as do destino, move a peca pelo eixo at� que seja, ent�o encerra o la�o
			switch(dir){
				case 1:
					y--;
				break;
				case 2:
					x++;
				break;
				case 3:
					y++;
				break;
				case 4:
					x--;
				break;
			}
			if(getTabuleiro().getPeca(y,x) !=null){//se o deslocamento passar por cima de uma casa ocupada, encerrar la�o
				break;
			}			
		}
		
		if(x==colunaDestino && y==linhaDestino){//se coluna e linha atuais alcan�aram o destino, ent�o:  
			if(pecaDestino == null){//se n�o houver peca no destino
				return true;
			}

			if(pecaDestino.getCor()!=this.getCor()){//se a pe�a no destino for da cor oposta: 
				return true;
			}
		}
		return false;
	
	}
}
