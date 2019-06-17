package agente;

import java.util.ArrayList;

public class OitoRainhas extends No {
	
	private int ultimaColunaComRainha;
	private boolean diagonalPrincipal[];
	private boolean diagonalSecundaria[];
	
	public OitoRainhas(char [][] tabuleiro) {
		setEstado(tabuleiro);		
		
		ultimaColunaComRainha = -1;
		diagonalPrincipal = new boolean[15];
		diagonalSecundaria = new boolean[15];
		
		for (int i = 0; i < diagonalPrincipal.length; i++) {
			diagonalPrincipal[i] = false;
			diagonalSecundaria[i] = false;
		}
	}
	
	private OitoRainhas(char [][] tabuleiro,OitoRainhas pai, boolean[] diagonalPrincipal, boolean[] diagonalSecundaria, int linha, int coluna) {
		tabuleiro[linha][coluna] = 'R';
		setEstado(tabuleiro);
		setCusto(0);
		setPai(pai);
		setNivel(pai.getNivel() + 1);
		
		this.diagonalPrincipal = diagonalPrincipal;
		this.diagonalSecundaria = diagonalSecundaria;
		
		this.diagonalPrincipal[linha + coluna] = true;
		this.diagonalSecundaria[linha - coluna + 7] = true;
		
		ultimaColunaComRainha = pai.getUltimaColunaComRainha() + 1;
	}	
	

	@Override
	public ArrayList extender() {
		ArrayList<OitoRainhas> acoes = new ArrayList<OitoRainhas>();
		
		char[][] estado = ((char [][]) getEstado()).clone();
		
		if(ultimaColunaComRainha == -1) {
			int linha = (int) (Math.random() * 8);
						
			acoes.add(new OitoRainhas(estado, this, diagonalPrincipal, diagonalSecundaria, linha, 0));
			return acoes;
		}
		
		
		for (int j = 0; j < 8; j++) {
			boolean valido = true;
			if (diagonalPrincipal[j + ultimaColunaComRainha + 1] || diagonalSecundaria[j - ultimaColunaComRainha + 6]) {
				valido = false;
			} else {			
				//verifica linha
				for (int k = ultimaColunaComRainha; k >= 0; k--) {
					if (estado[j][k] == 'R') {
						valido = false;
						break;
					}
				}
			}
			
			
			if (valido)	{
				char[][] tabuleiro = new char[8][8];
				
				for (int i = 0; i < tabuleiro.length; i++) {
					for (int k = 0; k < tabuleiro[i].length; k++) {
						tabuleiro[i][k] = ((char [][])estado)[i][k];
					}
				}

				tabuleiro[j][ultimaColunaComRainha + 1] = 'R';
				
				boolean [] d1 = new boolean[15];
				boolean [] d2 = new boolean[15];
				
				for (int i = 0; i < d2.length; i++) {
					d1[i] = diagonalPrincipal[i];
					d2[i] = diagonalSecundaria[i];
				}
				d1[j + ultimaColunaComRainha + 1] = true;
				d2[j - ultimaColunaComRainha + 6] = true;
				
				OitoRainhas acao = new OitoRainhas(tabuleiro); 
				acao.setPai(this);
				acao.setNivel(getNivel() + 1);
				acao.setDiagonalPrincipal(d1);
				acao.setDiagonalSecundaria(d2);
				acao.setUltimaColunaComRainha(ultimaColunaComRainha + 1);
				acoes.add(acao);
				
					
			
			}	
		}
		
		return acoes;
	}
	
	public boolean verifica(int row, int col) {
		char[][] board = (char[][]) getEstado();
		   //Check Left Upper Diagonal
		  for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
		   if(board[i][j] == 1){
		    return false;
		   }
		  }
		 
		  //Check Right Upper Diagonal
		  for (int i = row-1, j = col+1; i >= 0 && j < board.length; i--, j++) {
		   if(board[i][j] == 1){
		    return false;
		   }
		  }
		 
		  //Check in same Column
		  for (int i = row-1; i >= 0; i--) {
		   if(board[i][col] == 1){
		    return false;
		   }
		  }
		 
		  return true;
	}
	
	@Override
	public boolean testeObjetivo() {
		return ultimaColunaComRainha == 7 ? true: false;
	}

	@Override
	public void print() {
		System.out.println(toString());
		System.out.println();		
	}

	@Override
	public boolean equalsEstado(Object estado) {
		estado = (char [][]) estado;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (((char [][]) getEstado())[i][j] != ((char [][]) estado)[i][j]) {
					return false;
				}
			}
		}
		
		
		return true;
	}

	@Override
	public String toString() {
		char [][] estado = (char[][]) getEstado();
		
		String msg = "";
		msg += "---------------------------------";
		
		for (int i = 0; i < estado.length; i++) {
			msg += "\n";
			for (int j = 0; j < estado.length; j++) {
				msg += "| " + estado[i][j] + " ";
			}
			msg += "|";
			if (i == 4) msg += " Nivel: " + getNivel();
			msg += "\n";
			msg += "---------------------------------";
		}
		
		return msg;
	}

	public int getUltimaColunaComRainha() {
		return ultimaColunaComRainha;
	}

	public void setUltimaColunaComRainha(int ultimaColunaComRainha) {
		this.ultimaColunaComRainha = ultimaColunaComRainha;
	}

	public boolean[] getDiagonalPrincipal() {
		return diagonalPrincipal;
	}

	public void setDiagonalPrincipal(boolean[] diagonalPrincipal) {
		this.diagonalPrincipal = diagonalPrincipal;
	}

	public boolean[] getDiagonalSecundaria() {
		return diagonalSecundaria;
	}

	public void setDiagonalSecundaria(boolean[] diagonalSecundaria) {
		this.diagonalSecundaria = diagonalSecundaria;
	}
	
}
