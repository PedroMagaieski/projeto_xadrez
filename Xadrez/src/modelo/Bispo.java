package modelo;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bispo extends Peca {
	//ImageIcon imagem = new ImageIcon(getClass().getResource("BISPOPRETO.png"));
	//JLabel label = new JLabel(imagem);
	public Bispo(EnumCor cor, int linha,int coluna) {
		//tenta JLabel do swing ou o label do javaFx
		super(cor,linha,coluna, "BISPO"+cor+".png");
	}
	public Bispo(EnumCor cor, int linha, int coluna, String imagem) {
		super(cor, linha, coluna, imagem);
	}
	@Override
	public boolean validaMovimento(int linhaDestino,int colunaDestino) {
		int colunaOffset=colunaDestino-getColuna();//deslocamento coluna
		int linhaOffset=linhaDestino-getLinha();//deslocamento linha
		Peca pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);//local de destino
		int dir=0;//direcao da diagonal que deseja mover
		
		if((Math.abs(colunaOffset)!=Math.abs(linhaOffset)) || (colunaOffset==0 && linhaOffset==0)){//nao permite deslocamento de linha maior que coluna e vice-versa, e nao permite deslocamento de linha e coluna iguais a zero(movimento nao diagonal)
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
		//Lugar l=null; //equivalente e:  
		//Peca l = null; //esta como privado no tabuleiro
		
		while(!(x==colunaDestino && y==linhaDestino)){// move a eixo x e y na direcao do deslocamento ate alcancar o destino de linha e coluna, entao encerra o laco
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
			if(getTabuleiro().getPeca(y,x) != null){//se houver uma peca dentro do deslocamento da peca(nao no destino, apenas na viagem) interrompe laco e retorna falso no final;
				break;
			}			
		}
		
		if(x==colunaDestino && y==linhaDestino){//se eixo x e y forem iguais a coluna e linha de destino(chegou ao destino) entao:
			if(pecaDestino == null){//permite mover ate uma casa vazia
				return true;
			}
			if(pecaDestino.getCor()!=this.getCor()){//pemite comer pecas da cor oposta
				return true;
			}
		}
		return false;
		
	}
}
