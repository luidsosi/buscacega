package agente;

import java.util.ArrayList;

public abstract class No<E>{
	private No<E> pai;
	protected E estado;
	private int custo;
	private int nivel;
	
	
	public abstract ArrayList<E> extender();
	
	public abstract boolean testeObjetivo();
	
	public abstract void print();

	public abstract boolean equalsEstado(E estado);
	
	public No<E> getPai() {
		return pai;
	}

	public void setPai(No<E> pai) {
		this.pai = pai;
	}

	public E getEstado() {
		return estado;
	}

	public void setEstado(E estado) {
		this.estado = estado;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}
