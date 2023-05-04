package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
	public void insertarNodoTemporal(PosicionPersona p) {
		NodoTemporal current = lista;
		NodoTemporal previous = null;
		boolean found = false;
		boolean endOfList = false;

		while (current != null && !found && !endOfList) {
			if (current.getFecha().equals(p.getFechaPosicion())) {
				found = true;
				NodoPosicion currentCoord = current.getListaCoordenadas();
				NodoPosicion previousCoord = null;
				boolean foundCoord = false;

				while (currentCoord != null && !foundCoord) {
					if (currentCoord.getCoordenada().equals(p.getCoordenada())) {
						foundCoord = true;
						currentCoord.setNumPersonas(currentCoord.getNumPersonas() + 1);
					} else {
						previousCoord = currentCoord;
						currentCoord = currentCoord.getSiguiente();
					}
				}

				if (!foundCoord) {
					NodoPosicion newCoord = new NodoPosicion(p.getCoordenada(), 1, null);
					if (current.getListaCoordenadas() == null) {
						current.setListaCoordenadas(newCoord);
					} else {
						previousCoord.setSiguiente(newCoord);
					}
				}
			} else if (current.getFecha().compareTo(p.getFechaPosicion()) < 0) {
				previous = current;
				current = current.getSiguiente();
			} else if (current.getFecha().compareTo(p.getFechaPosicion()) > 0) {
				endOfList = true;
			}
		}

		if (!found) {
			NodoTemporal newNode = new NodoTemporal();
			newNode.setFecha(p.getFechaPosicion());
			NodoPosicion currentCoord = newNode.getListaCoordenadas();
			NodoPosicion previousCoord = null;
			boolean foundCoord = false;

			while (currentCoord != null && !foundCoord) {
				if (currentCoord.getCoordenada().equals(p.getCoordenada())) {
					foundCoord = true;
					currentCoord.setNumPersonas(currentCoord.getNumPersonas() + 1);
				} else {
					previousCoord = currentCoord;
					currentCoord = currentCoord.getSiguiente();
				}
			}

			if (!foundCoord) {
				NodoPosicion newCoord = new NodoPosicion(p.getCoordenada(), 1, null);
				if (newNode.getListaCoordenadas() == null) {
					newNode.setListaCoordenadas(newCoord);
				} else {
					previousCoord.setSiguiente(newCoord);
				}
			}

			if (previous != null) {
				newNode.setSiguiente(current);
				previous.setSiguiente(newNode);
			} else {
				newNode.setSiguiente(lista);
				lista = newNode;
			}
			size++;
		}
	}


	public int personasEnCoordenadas () {
		NodoPosicion aux = this.lista.getListaCoordenadas();
		if(aux==null)
			return 0;
		else {
			int cont;
			for(cont=0;aux!=null;) {
				cont += aux.getNumPersonas();
				aux=aux.getSiguiente();
			}
			return cont;
		}
	}
	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena+= ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + nodo.getNumPersonas();
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + 1;
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	@Override
	public String toString() {
		String cadena="";
		int cont;
		cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" +  aux.getFecha().getHora().toString() + " ";
			aux=aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}
	
	
	
}
