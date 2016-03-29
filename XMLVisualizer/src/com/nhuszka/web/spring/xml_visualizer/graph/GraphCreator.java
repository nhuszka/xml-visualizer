package com.nhuszka.web.spring.xml_visualizer.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel;

public class GraphCreator {

	public String createGraphInBase64String(List<String> vertexes, Map<String, String> edges) {
		DirectedSparseMultigraph<String, Integer> graph = createGraph(vertexes, edges);
		return encodeGraphInBase64(graph);
	}

	private DirectedSparseMultigraph<String, Integer> createGraph(List<String> vertexes, Map<String, String> edges) {
		DirectedSparseMultigraph<String, Integer> graph = new DirectedSparseMultigraph<String, Integer>();
		addVertexes(vertexes, graph);
		addEdges(edges, graph);
		return graph;
	}

	private void addVertexes(List<String> vertexes, DirectedSparseMultigraph<String, Integer> graph) {
		for (String vertex : vertexes) {
			graph.addVertex(vertex);
		}
	}

	private void addEdges(Map<String, String> edges, DirectedSparseMultigraph<String, Integer> graph) {
		int edgeIndex = 1;
		for (Entry<String, String> edge : edges.entrySet()) {
			String edgeFrom = edge.getKey();
			String edgeTo = edge.getValue();
			graph.addEdge(edgeIndex++, edgeFrom, edgeTo, EdgeType.DIRECTED);
		}
	}

	private BufferedImage convertToImage(DirectedSparseMultigraph<String, Integer> graph) {
		KKLayout<String, Integer> kkLayout = new KKLayout<String, Integer>(graph);
		Dimension dimension = new Dimension(600, 600);

		VisualizationImageServer<String, Integer> imageServer = new VisualizationImageServer<String, Integer>(
				kkLayout, dimension);
		setUpImageServer(imageServer);
		return (BufferedImage) imageServer.getImage(new Point2D.Double(300D, 300D), dimension);
	}

	private void setUpImageServer(VisualizationImageServer<String, Integer> imageServer) {
		GradientVertexRenderer<String, Integer> vertexRenderer = new GradientVertexRenderer<String, Integer>(
				Color.white, Color.red, Color.white, Color.blue,
				imageServer.getPickedVertexState(), false);
		imageServer.getRenderer().setVertexRenderer(vertexRenderer);
		imageServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());

		VertexLabel<String, Integer> vertexLabelRenderer = imageServer.getRenderer().getVertexLabelRenderer();
		vertexLabelRenderer.setPositioner(new BasicVertexLabelRenderer.InsidePositioner());
		vertexLabelRenderer.setPosition(Renderer.VertexLabel.Position.AUTO);
	}

	private String encodeGraphInBase64(DirectedSparseMultigraph<String, Integer> graph) {
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
