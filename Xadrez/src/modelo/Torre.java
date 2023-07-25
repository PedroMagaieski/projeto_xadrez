package modelo;

import javax.swing.JLabel;

public class Torre extends Peca{
	
	
	public Torre(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "TORRE"+cor+".png");
	}

	public Torre(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		int colunaOffset=getColuna()-colunaDestino;//deslocamento coluna
		int linhaOffset=getLinha()-linhaDestino;//deslocamento linha
		int dir=0;//direcao que deseja mover
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);//local de destino
		
		if((colunaOffset!=0 && linhaOffset!=0)|| (colunaOffset==0 && linhaOffset==0)){//nao permite deslocamento de linha e coluna diferentes de zero(movimento diagonal), e nao permite deslocamento de linha e coluna iguais a zero
			return false;
		}
		
		if(colunaOffset==0){//se deslocamento de coluna for igual a zero entao:(mesma coluna)
			if(linhaOffset>0){//se deslocamento de linha for positivo, a direcao sera 1
				dir=1;
			}else if(linhaOffset<0){//se deslocamento de linha for negativo, a direcao sera 3
				dir=3;
			}
		}
		
		if(linhaOffset==0){// se deslocamento de linha for igual zero: (mesma linha)
		
			if(colunaOffset>0){//se deslocamento de coluna for positivo, direcao sera 4
				dir=4;
			}else if(colunaOffset<0){//se deslocamento de coluna for negativo, direcao sera 2
				dir=2;
			}
		
		}
		
		int x=getColuna(),y=getLinha();//define coluna atual como eixo x e linha atual como eixo y para poder incrementar e mover mais de uma de cada vez
		
		//VER AQUI
		//Lugar l=null;
		
		while(!(x==colunaDestino && y==linhaDestino)){//enquanto linha e colunas atuais nao forem iguais as do destino, move a peca pelo eixo ate que seja, entao encerra o laco
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
			if(getTabuleiro().getPeca(y,x) !=null){//se o deslocamento passar por cima de uma casa ocupada, encerrar laco
				break;
			}			
		}
		
		if(x==colunaDestino && y==linhaDestino){//se coluna e linha atuais alcancaram o destino, entao:  
			if(pecaDestino == null){//se nao houver peca no destino
				return true;
			}

			if(pecaDestino.getCor()!=this.getCor()){//se a peca no destino for da cor oposta: 
				return true;
			}
		}
		return false;
	
	}
}
