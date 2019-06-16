import agente.Puzzle;
import busca.Busca;

public class Index {

	public static void main(String[] args) {
		
//		char[][] tabuleiro = {{' ','2','3'},{'1','4','6'},{'7','5','8'}};
//		char[][] tabuleiro = {'2','4','3','7','1','6','5',' ','8'};
		char[][] tabuleiro = {{'1','2',' '},{'3','4','5'},{'6','7','8'}};
//		char[][] tabuleiro = {{'3',' ','2'},{'6','1','5'},{'7','4','8'}};		
//		char[][] tabuleiro = {{'2','3',' '},{'7','4','1'},{'5','8','6'}};
		
		char[][] tabuleiro2 = {{' ','1','2'},{'3','4','5'},{'6','7','8'}};
		Puzzle puzzle = new Puzzle(tabuleiro);
		
		Busca<Puzzle> busca = new Busca<Puzzle>(puzzle, new Puzzle(tabuleiro2)); 
		
		busca.buscaEmProfundidadeComVisitados();
		
		System.exit(0);
	}

}
