import java.util.ArrayList;

import agente.OitoRainhas;
import agente.Puzzle;
import busca.Busca;

public class Index {

	public static void main(String[] args) {
//		Exemplos de teste

		char[][] tabuleiro = {{'3','1',' '},{'6','4','2'},{'7','8','5'}};	
		
		Puzzle puzzle = new Puzzle(tabuleiro);
		
		Busca<Puzzle> buscaPuzzle = new Busca<Puzzle>(puzzle); 
		
		buscaPuzzle.buscaCustoUniforme();
				
//		char tabuleiroInicial[][] = {
//				{' ',' ',' ',' ',' ',' ',' ',' '},
//				{' ',' ',' ',' ',' ',' ',' ',' '},
//				{' ',' ',' ',' ',' ',' ',' ',' '},
//				{' ',' ',' ',' ',' ',' ',' ',' '},
//				{' ',' ',' ',' ',' ',' ',' ',' '},
//				{' ',' ',' ',' ',' ',' ',' ',' '},
//				{' ',' ',' ',' ',' ',' ',' ',' '},
//				{' ',' ',' ',' ',' ',' ',' ',' '}
//			};
//		
//		OitoRainhas oitoRainhas = new OitoRainhas(tabuleiroInicial);
//		
//		Busca<OitoRainhas> buscaOitoRainhas = new Busca<OitoRainhas>(oitoRainhas);
//		buscaOitoRainhas.buscaComAprofundamentoIterativo();
	
	}

}
