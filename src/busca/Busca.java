package busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import agente.No;
import agente.Puzzle;

public class Busca<E> {
	public No<E> estadoInicial;
	public ArrayList<No<E>> borda;
	public ArrayList<No<E>> caminho;
	public long tempoExecucao;
	public int numeroExtender;
	
	public Busca(No<E> estadoInicial) {
		this.estadoInicial = estadoInicial;
		this.estadoInicial.setNivel(0);
		this.estadoInicial.setCusto(0);
		this.borda = new ArrayList<No<E>>();
		this.caminho = new ArrayList<No<E>>();
		this.numeroExtender = 0;
	}

	public void buscaEmLargura() {
		System.out.println("Buscando...");
		long tempoInicial = System.currentTimeMillis();
		
		No<E> estado = estadoInicial;
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Solução não encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				long tempoFinal = System.currentTimeMillis();				
				tempoExecucao = tempoFinal - tempoInicial;
				
				fimBusca();

				break;
			}
			
			borda.addAll((Collection<? extends No<E>>) borda.get(0).extender());
			numeroExtender++;
			
			borda.remove(0);
		}		
	}
	
	public void buscaEmProfundidade() {
		System.out.println("Buscando...");
		long tempoInicial = System.currentTimeMillis();
		
		No<E> estado = estadoInicial;
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Solução não encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				long tempoFinal = System.currentTimeMillis();
				tempoExecucao = tempoFinal - tempoInicial;
				
				fimBusca();
				
				break;
			}
			
			borda.addAll(1,(Collection<? extends No<E>>) borda.get(0).extender());
			numeroExtender++;
			borda.remove(0);			
		}
	}
	
	public void buscaEmProfundidadeComVisitados() {
		System.out.println("Buscando...");
		long tempoInicial = System.currentTimeMillis();
		
		No<E> estado = estadoInicial;
		ArrayList<No<E>> visitados = new ArrayList<No<E>>();
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Solução não encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				long tempoFinal = System.currentTimeMillis();
				tempoExecucao = tempoFinal - tempoInicial;
				
				fimBusca();
	
				break;
			}
			
			ArrayList<No<E>> acoes = (ArrayList<No<E>>) borda.get(0).extender();
			numeroExtender++;
			ArrayList<No<E>> iguais = new ArrayList<No<E>>();
			
			visitados.forEach(visitado -> {
				for (No<E> acao : acoes) {
					if (visitado.equalsEstado(acao.getEstado())) {
						iguais.add(acao);
					}
				}
			});
			
			if (!acoes.isEmpty()) {
				iguais.forEach(igual -> acoes.remove(igual));	
			}
			
			
			borda.addAll(1,(Collection<? extends No<E>>) acoes);
			visitados.add((No<E>) borda.remove(0));
			
		}
	}
	
	public boolean buscaEmProfundidadeLimitada(int limite) {
		System.out.println("Buscando...");
		long tempoInicial = System.currentTimeMillis();
		
		No<E> estado = estadoInicial;
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				long tempoFinal = System.currentTimeMillis();
				tempoExecucao = tempoFinal - tempoInicial;
				
				System.out.print("Solução não encontrada");
				return false;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				long tempoFinal = System.currentTimeMillis();
				tempoExecucao = tempoFinal - tempoInicial;
				
				fimBusca();
				
				return true;
			}
			
			ArrayList<No<E>> acoes = (ArrayList<No<E>>) borda.get(0).extender();
			
			if(!acoes.isEmpty() && acoes.get(0).getNivel() <= limite) {				
				borda.addAll(1,(Collection<? extends No<E>>) borda.get(0).extender());
				numeroExtender++;
			}
			
			borda.remove(0);
		}
	}
	
	public boolean buscaComAprofundamentoIterativo() {		
		long tempoExecucaoTotal = 0;
		for (int i = 0; true; i++) {
			if (buscaEmProfundidadeLimitada(i)) {				
				tempoExecucaoTotal += tempoExecucao;
				System.out.println("Tempo de execução total: " + tempoExecucaoTotal + " ms");
				return true;
			} else {
				tempoExecucaoTotal += tempoExecucao;
				System.out.println(" com limite " + i);
			}
		}
	}
	
	public void buscaCustoUniforme() {
		System.out.println("Buscando...");
		long tempoInicial = System.currentTimeMillis();
		
		No<E> estado = estadoInicial;
		
		borda.add(estado);
		
		while(true) {
			if (borda.isEmpty()) {
				System.out.println("Solução não encontrada. \nBorda Vazia.");
				break;
			}
			
			if(((No<E>) borda.get(0)).testeObjetivo()) {
				long tempoFinal = System.currentTimeMillis();
				tempoExecucao = tempoFinal - tempoInicial;
				
				fimBusca();
				
				break;
			}
			
			borda.addAll((Collection<? extends No<E>>) borda.get(0).extender());
			numeroExtender++;
			borda.remove(0);
			
			borda.sort(new Comparator<No<E>>() {
				@Override
				public int compare(No no1, No no2) {
					if (no1.getCusto() < no2.getCusto())
			            return -1;
			        if (no1.getCusto() > no2.getCusto())
			            return 1;
					return 0;
				}
			});
			
		}
	}
	
	private void fimBusca() {
		System.out.println("Busca terminada! \nCaminho: ");
		
		caminho.add(borda.get(0));
		
		No<E> pai = ((No<E>)borda.get(0)).getPai();
		
		while(pai != null) {
			caminho.add(pai);
			pai = pai.getPai();
		}
		
		borda.remove(0);
		
		Collections.reverse(caminho);
		
		caminho.forEach(n -> {((No<E>) n).print();});
		
		System.out.println("Tempo de execução: " + tempoExecucao + " ms");
		System.out.println("Número de vezes que o método extender() foi executado: " + numeroExtender);
		System.out.println("Tamanho da borda: " + borda.size());
	}
}
