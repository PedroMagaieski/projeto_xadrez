package modelo;

public class Rainha extends Peca{
	
	public Rainha(EnumCor cor, int linha,int coluna) {
		super(cor,linha,coluna, "C:/figs/RAINHA"+cor+".png");
	}

	public Rainha(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		//COMENTAR FUNÇÕES DE CADA CONDICIONAL AQUI
		int colunaOffset=colunaDestino-getColuna();//deslocamento da coluna
		int linhaOffset=linhaDestino-getLinha();//deslocamento da linha
		int dir=0;//direção
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);//celula de destino
		
		if(colunaOffset==0 && linhaOffset==0){//se o deslocamento for nulo
			return false;
		}
		
		if((colunaOffset==0 && linhaOffset!=0) || (colunaOffset!=0 && linhaOffset==0 )){//se deslocar apenas a linha OU se deslocar apenas a coluna:
			//movimento reto
			if(colunaOffset==0){//se deslocamento de coluna for nulo(apenas move pela linha):
				if(linhaOffset<0){//se deslocamento da linha for negativo, direção passa a ser 1
					dir=1;
				}else if(linhaOffset>0){//se deslocamento da linha for positivo, direção passa a ser 5
					dir=5;
				}
			}
			if(linhaOffset==0){//se deslocamento de linha for nulo(apenas move pela coluna):
			
				if(colunaOffset>0){//se deslocamento de coluna for negativo, direção passa a ser 3
					dir=3;
				}else if(colunaOffset<0){//se deslocamento de coluna for negativo, direção passa a ser 7
					dir=7;
				}
			
			}
		//movimento diagonal
		}else if(Math.abs(colunaOffset)==Math.abs(linhaOffset)){//se deslocamento absoluto de coluna for igual ao absoluta da linha(diagonal com coluna e linha de mesmo valor de diferença):
			if(colunaOffset>0 && linhaOffset>0 ){//se deslocamento de coluna e linha forem positivos, direção vira 4
				dir =4;
			}else if(colunaOffset>0 && linhaOffset<0){//se deslocamento de coluna for positivo e linha negativo, direção vira 2
				dir=2;
			}else if(colunaOffset<0 && linhaOffset>0){//se deslocamento de coluna for negativo e linha positivo, direção vira 6
				dir=6;
			}else{//se deslocamento de coluna e linha forem negativos, direção vira 8
				dir=8;
			}
		}else{//se não for nem movimento reto ou diagonal
			return false;
		}
		
		int x=getColuna(),y=getLinha();//define coluna atual como eixo x e linha atual como eixo y para poder incrementar e mover mais de uma de cada vez
		
		//VER AQUI
		//Lugar l=null;
		
		while(!(x==colunaDestino && y==linhaDestino)){//enquanto coluna e linha atuais não forem iguais ao destino:
			switch(dir){
				case 1://move para sul
					y--;
				break;
				case 3: //move para o leste
					x++;
				break;
				case 5://move para norte
					y++;
				break;
				case 7://move para oeste
					x--;
				break;
				case 2://move diagonal sul-leste
					y--;
					x++;
				break;
				case 4://move para diagonal norte-leste
					x++;
					y++;
				break;
				case 6://move para diagonal norte-oeste
					x--;
					y++;
				break;
				case 8://move para diagonal sul-oeste
					x--;
					y--;
				break;
			}
			if(getTabuleiro().getPeca(y,x) != null){//se houver uma peça no caminho do deslocamento, encerra laço
				break;
			}			
		}
		
		if(x==colunaDestino && y==linhaDestino){//se chegou ao destino:
			if(pecaDestino == null){//se o destino for uma casa vazia:
				return true;
			}
			if(pecaDestino.getCor()!=this.getCor()){//se o destino for uma peça de outra cor:
				return true;
			}
		}
		
		return false;
	}
}
