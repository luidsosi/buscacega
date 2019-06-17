package agente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class OitoRainhas extends No{	
	private ArrayList<Rainha> rainhas;
	private int ultimaColunaComRainha;
	private boolean diagonalPrincipal[];
	private boolean diagonalSecundaria[];
	
	public OitoRainhas(char [][] tabuleiro) {
		setEstado(tabuleiro);
		
		rainhas = new ArrayList<Rainha>();
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
			if (diagonalPrincipal[j + ultimaColunaComRainha + 1] || diagonalSecundaria[j - ultimaColunaComRainha + 7]) {
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
			
			
			if (valido) {
				acoes.add(new OitoRainhas(estado.clone(), this, diagonalPrincipal, diagonalSecundaria, j, ultimaColunaComRainha + 1));	
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
		return rainhas.size() == 8 ? true: false;
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
			msg += "|\n";
			msg += "---------------------------------";
		}
		
		return msg;
	}

	public ArrayList<Rainha> getRainhas() {
		return rainhas;
	}

	public void setRainhas(ArrayList<Rainha> rainhas) {
		this.rainhas = rainhas;
	}

	public int getUltimaColunaComRainha() {
		return ultimaColunaComRainha;
	}

	public void setUltimaColunaComRainha(int ultimaColunaComRainha) {
		this.ultimaColunaComRainha = ultimaColunaComRainha;
	}
}
