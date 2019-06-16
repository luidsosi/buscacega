package busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import agente.No;
import agente.Puzzle;

public class Busca<E> {
	public No<E> estadoInicial;
	public No<E> estadoObjetivo;
	public ArrayList<No<E>> borda;
	public ArrayList<No<E>> caminho;
	
	public Busca(No<E> estadoInicial, No<E> estadoObjetivo) {
		this.estadoInicial = estadoInicial;
		this.estadoInicial.setNivel(0);
		this.estadoInicial.setCusto(0);
		this.estadoObjetivo = estadoObjetivo;
		this.borda = new ArrayList<No<E>>();
		this.caminho = new ArrayList<No<E>>();
	}

	public void buscaEmLargura() {
		No<E> estado = estadoInicial;
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Soulu��o n�o encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				System.out.println("Terminou!");
				
				caminho.add(borda.get(0));
				
				No<E> pai = ((No<E>)borda.get(0)).getPai();
				
				while(pai != null) {
					caminho.add(pai);
					pai = pai.getPai();
				}
				
				Collections.reverse(caminho);
				
				caminho.forEach(n -> {((No) n).print();});
				
				break;
			}
			
			borda.addAll((Collection<? extends No<E>>) borda.get(0).extender());
			
			borda.remove(0);
		}
		
	}
	
	public void buscaEmProfundidade() {
		No<E> estado = estadoInicial;
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Soulu��o n�o encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				System.out.println("Terminou!");
				
				caminho.add(borda.get(0));
				
				No<E> pai = ((No<E>)borda.get(0)).getPai();
				
				while(pai != null) {
					caminho.add(pai);
					pai = pai.getPai();
				}
				
				Collections.reverse(caminho);
				
				caminho.forEach(n -> {((No) n).print();});
				
				break;
			}
			borda.addAll(1,(Collection<? extends No<E>>) borda.get(0).extender());
			borda.remove(0);
			
		}
	}
	
	public void buscaEmProfundidadeComVisitados() {
		No<E> estado = estadoInicial;
		ArrayList<No<E>> visitados = new ArrayList<No<E>>();
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Soulu��o n�o encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				System.out.println("Terminou!");
				
				caminho.add(borda.get(0));
				
				No<E> pai = ((No<E>)borda.get(0)).getPai();
				
				while(pai != null) {
					caminho.add(pai);
					pai = pai.getPai();
				}
				
				Collections.reverse(caminho);
				
				System.out.println("Caminho:");
				caminho.forEach(n -> {((No) n).print();});
				System.out.println("Visitados:");
				visitados.forEach(visitado -> visitado.print());
				System.out.println("Borda:");
				borda.forEach(b -> ((No<E>) b).print());
				
				break;
			}
			
			ArrayList<No<E>> acoes = (ArrayList<No<E>>) borda.get(0).extender();
			
			acoes.forEach(acao -> {
				if (!visitados.isEmpty()) {
					for (int i = 0; i < visitados.size(); i++) {
						if (visitados.get(i).equalsEstado(acao.getEstado())) {
							acoes.remove(acao);
						}
					}
					
//					visitados.forEach(visitado -> {
//						if (visitado.equalsEstado(acao.getEstado())) {
//							acoes.remove(acao);
//						}
//					});
				}
			});
			
			borda.addAll(1,(Collection<? extends No<E>>) acoes);
			visitados.add((No<E>) borda.remove(0));
			
		}
	}
	
	public void buscaEmProfundidadeLimitada(int limite) {
		No<E> estado = estadoInicial;
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Soulu��o n�o encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				System.out.println("Terminou!");
				
				caminho.add(borda.get(0));
				
				No<E> pai = ((No<E>)borda.get(0)).getPai();
				
				while(pai != null) {
					caminho.add(pai);
					pai = pai.getPai();
				}
				
				Collections.reverse(caminho);
				
				caminho.forEach(n -> {((No) n).print();});
				
				break;
			}
			ArrayList<No<E>> acoes = (ArrayList<No<E>>) borda.get(0).extender();
			
			if(!acoes.isEmpty() && acoes.get(0).getNivel() <= limite) {				
				borda.addAll(1,(Collection<? extends No<E>>) borda.get(0).extender());
			}
			
			borda.remove(0);
		}
	}
}
