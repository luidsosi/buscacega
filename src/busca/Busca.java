package busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import agente.No;
import agente.Puzzle;

public class Busca<E> {
	public No<E> estadoInicial;
	public No<E> estadoObjetivo;
	public ArrayList<E> borda;
	public ArrayList<E> caminho;
	
	public Busca(No<E> estadoInicial, No<E> estadoObjetivo) {
		this.estadoInicial = estadoInicial;
		this.estadoObjetivo = estadoObjetivo;
		this.borda = new ArrayList<E>();
		this.caminho = new ArrayList<E>();
	}

	public void buscaEmLargura() {
		No<E> estado = estadoInicial;
		
		borda.add((E) estado);
		
		while(true) {
			if(((No<E>) borda.get(0)).testeObjetivo() || borda.isEmpty()) {
				System.out.println("Terminou!");
				
				caminho.add(borda.get(0));
				
				No<E> pai = ((No<E>)borda.get(0)).getPai();
				
				while(pai != null) {
					caminho.add((E) pai);
					pai = pai.getPai();
				}
				
				Collections.reverse(caminho);
				
				caminho.forEach(n -> {((No) n).print();});
				
				break;
			}
			
			borda.addAll((Collection<? extends E>) ((Puzzle) borda.get(0)).extender());
			
			borda.remove(0);
		}
		
	}
	
	public void buscaEmProfundidade() {
		No<E> estado = estadoInicial;
		
		borda.add((E) estado);
		
		while(true) {
			if(((No<E>) borda.get(0)).testeObjetivo() || borda.isEmpty()) {
				System.out.println("Terminou!");
				
				caminho.add(borda.get(0));
				
				No<E> pai = ((No<E>)borda.get(0)).getPai();
				
				while(pai != null) {
					caminho.add((E) pai);
					pai = pai.getPai();
				}
				
				Collections.reverse(caminho);
				
				caminho.forEach(n -> {((No) n).print();});
				
				break;
			}
			borda.addAll(1,(Collection<? extends E>) ((Puzzle) borda.get(0)).extender());
			borda.remove(0);
			
//			estado = (No<E>) borda.get(0);
		}
	}
}
