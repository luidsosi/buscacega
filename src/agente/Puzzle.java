package agente;

import java.util.ArrayList;
import java.util.Collections;

public class Puzzle extends No{
	public static final char[][] TABULEIRO_ORGANIZADO = {{' ','1','2'},{'3','4','5'},{'6','7','8'}};
	
	public Puzzle(char tabuleiro[][]) {
		setEstado(tabuleiro);
	}
	
	public Puzzle movimentoCima(){
		int [] posicao = getPosicaoVazia();
		int posicaoX = posicao[0];
		int posicaoY = posicao[1];
		
		if(posicaoX == 0) return null;
		
		char[][] tabuleiro = new char[3][3];
		
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = ((char [][])estado)[i][j];
			}
		}
		
		tabuleiro[posicaoX][posicaoY] = tabuleiro[posicaoX - 1][posicaoY];
		tabuleiro[posicaoX - 1][posicaoY] = ' ';
		
		Puzzle result = new Puzzle(tabuleiro);
		result.setPai(this);
		result.setEstado(tabuleiro);
		result.setCusto(result.getPai().getCusto() + 1);
		result.setNivel(result.getPai().getNivel() + 1);
		
		return result;
	}
	
	public Puzzle movimentoBaixo(){
		int [] posicao = getPosicaoVazia();
		int posicaoX = posicao[0];
		int posicaoY = posicao[1];
		
		if(posicaoX == ((char[][]) getEstado()).length-1) return null;
		
		char[][] tabuleiro = new char[3][3];
		
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = ((char [][])estado)[i][j];
			}
		}
		
		tabuleiro[posicaoX][posicaoY] = tabuleiro[posicaoX + 1][posicaoY];
		tabuleiro[posicaoX + 1][posicaoY] = ' ';
		
		Puzzle result = new Puzzle(tabuleiro);
		result.setPai(this);
		result.setEstado(tabuleiro);
		result.setCusto(result.getPai().getCusto() + 1);
		result.setNivel(result.getPai().getNivel() + 1);		
		
		return result;
	}
	
	public Puzzle movimentoEsquerda(){
		int [] posicao = getPosicaoVazia();
		int posicaoX = posicao[0];
		int posicaoY = posicao[1];
				
		if(posicaoY == 0) return null;

		char[][] tabuleiro = new char[3][3];
		
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = ((char [][])estado)[i][j];
			}
		}
		
		tabuleiro[posicaoX][posicaoY] = tabuleiro[posicaoX][posicaoY - 1];
		tabuleiro[posicaoX][posicaoY - 1] = ' ';
		
		Puzzle result = new Puzzle(tabuleiro);
		result.setPai(this);
		result.setEstado(tabuleiro);
		result.setCusto(result.getPai().getCusto() + 1);
		result.setNivel(result.getPai().getNivel() + 1);
		
		return result;
	}
	
	public Puzzle movimentoDireita(){
		int [] posicao = getPosicaoVazia();
		int posicaoX = posicao[0];
		int posicaoY = posicao[1];
		
		if(posicaoY == ((char[][]) getEstado()).length-1) return null;
		
		char[][] tabuleiro = new char[3][3];
		
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = ((char [][])estado)[i][j];
			}
		}
		
		tabuleiro[posicaoX][posicaoY] = tabuleiro[posicaoX][posicaoY + 1];
		tabuleiro[posicaoX][posicaoY + 1] = ' ';
		
		Puzzle result = new Puzzle(tabuleiro);
		result.setPai(this);
		result.setEstado(tabuleiro);
		result.setCusto(result.getPai().getCusto() + 1);
		result.setNivel(result.getPai().getNivel() + 1);
		
		return result;
	}
	
	public int[] getPosicaoVazia() {
		char[][] estado = (char[][]) getEstado();
		int [] posicao = {0,0};
		for (int i = 0; i < estado.length; i++) {
			for (int j = 0; j < estado[i].length; j++) {
				if(estado[i][j] == ' ') {
					posicao[0] = i;
					posicao[1] = j;
				}
			}
		}
		
		return posicao;
	}
	
	
	
	@Override
	public void print() {
		System.out.println(toString());
		System.out.println();
	}

	@Override
	public ArrayList<Puzzle> extender() {
		ArrayList<Puzzle> movimentosPossiveis = new ArrayList<Puzzle>();
				
		if (movimentoEsquerda() != null) movimentosPossiveis.add(movimentoEsquerda());
		
		if (movimentoCima() != null) movimentosPossiveis.add(movimentoCima());
	
		if (movimentoDireita() != null)	movimentosPossiveis.add(movimentoDireita());
	
		if (movimentoBaixo() != null) movimentosPossiveis.add(movimentoBaixo());
		
		Collections.shuffle(movimentosPossiveis);
		
		return movimentosPossiveis;
	}

	@Override
	public boolean testeObjetivo() {
		char [][] estado = (char[][]) getEstado();
		for (int i = 0; i < estado.length; i++) {
			for (int j = 0; j < estado[i].length; j++) {
				if (estado[i][j] != TABULEIRO_ORGANIZADO[i][j]) return false; 
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		char [][] estado = (char[][]) getEstado();
		
		String msg = "";
		
		msg += "-------------";
		msg += "\n| " + estado[0][0] + " | " + estado[0][1] + " | " + estado[0][2] + " |\n";
		msg += "-------------";
		msg += "\n| " + estado[1][0] + " | " + estado[1][1] + " | " + estado[1][2] + " |  No # Nível: " + getNivel() + "; Custo: " + getCusto() + "\n";
		msg += "-------------" ;
		msg += "\n| " + estado[2][0] + " | " + estado[2][1] + " | " + estado[2][2] + " |\n";
		msg += "------------- \n" ;
		
		return msg;
	}

	@Override
	public boolean equalsEstado(Object estado) {
		estado = (char [][]) estado;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (((char [][]) getEstado())[i][j] != ((char [][]) estado)[i][j]) {
					return false;
				}
			}
		}
		
		
		return true;
	}	
}