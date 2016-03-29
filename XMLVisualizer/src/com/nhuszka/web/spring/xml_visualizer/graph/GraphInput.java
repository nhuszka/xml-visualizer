package com.nhuszka.web.spring.xml_visualizer.graph;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

public class GraphInput<E> {
	
	private Collection<E> vertexes = new ArrayList<>();
	private Collection<Pair<E, E>> edges = new ArrayList<>();

	private GraphInput() {
	}
	
	public static <E> GraphInput<E> createInput() {
		return new GraphInput<E>();
	}
	
	public void addVertex(E vertex) {
		vertexes.add(vertex);
	}
	
	public void addEdge(E fromVertex, E toVertex) {
		edges.add(Pair.of(fromVertex, toVertex));
	}

	public Collection<E> getVertexes() {
		return vertexes;
	}
	
	public Collection<Pair<E, E>> getEdges() {
		return edges;
	}
}
