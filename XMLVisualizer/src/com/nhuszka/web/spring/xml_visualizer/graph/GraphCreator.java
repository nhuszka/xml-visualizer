package com.nhuszka.web.spring.xml_visualizer.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.tuple.Pair;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel;

public class GraphCreator<E> {

	public String createGraphInBase64String(GraphInput<E> graphInput) {
		DirectedSparseMultigraph<E, Integer> graph = createGraph(graphInput);
		return encodeGraphInBase64(graph);
	}

	private DirectedSparseMultigraph<E, Integer> createGraph(GraphInput<E> graphInput) {
		DirectedSparseMultigraph<E, Integer> graph = new DirectedSparseMultigraph<E, Integer>();
		addVertexes(graphInput, graph);
		addEdges(graphInput, graph);
		return graph;
	}

	private void addVertexes(GraphInput<E> graphInput, DirectedSparseMultigraph<E, Integer> graph) {
		for (E vertex : graphInput.getVertexes()) {
			graph.addVertex(vertex);
		}
	}

	private void addEdges(GraphInput<E> graphInput, DirectedSparseMultigraph<E, Integer> graph) {
		int edgeIndex = 1;
		for (Pair<E, E> edge : graphInput.getEdges()) {
			E edgeLeftVertex = edge.getLeft();
			E edgeRightVertex = edge.getRight();
			graph.addEdge(edgeIndex++, edgeLeftVertex, edgeRightVertex, EdgeType.DIRECTED);
		}
	}

	private BufferedImage convertToImage(DirectedSparseMultigraph<E, Integer> graph) {
		KKLayout<E, Integer> kkLayout = new KKLayout<E, Integer>(graph);
		Dimension dimension = new Dimension(600, 600);

		VisualizationImageServer<E, Integer> imageServer = new VisualizationImageServer<E, Integer>(
				kkLayout, dimension);
		setUpImageServer(imageServer);
		return (BufferedImage) imageServer.getImage(new Point2D.Double(300D, 300D), dimension);
	}

	private void setUpImageServer(VisualizationImageServer<E, Integer> imageServer) {
		GradientVertexRenderer<E, Integer> vertexRenderer = new GradientVertexRenderer<E, Integer>(
				Color.white, Color.red, Color.white, Color.blue,
				imageServer.getPickedVertexState(), false);
		imageServer.getRenderer().setVertexRenderer(vertexRenderer);
		imageServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<E>());

		VertexLabel<E, Integer> vertexLabelRenderer = imageServer.getRenderer().getVertexLabelRenderer();
		vertexLabelRenderer.setPositioner(new BasicVertexLabelRenderer.InsidePositioner());
		vertexLabelRenderer.setPosition(Renderer.VertexLabel.Position.AUTO);
	}

	private String encodeGraphInBase64(DirectedSparseMultigraph<E, Integer> graph) {
		byte[] imageInBytes = getImageInBytes(convertToImage(graph));
		byte[] encodedByteArray = Base64.encodeBase64(imageInBytes);
		return new String(encodedByteArray);
	}

	private byte[] getImageInBytes(BufferedImage image) {
		byte[] imageInByte = {};
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			ImageIO.write(image, "png", stream);
			imageInByte = stream.toByteArray();
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageInByte;
	}
}
