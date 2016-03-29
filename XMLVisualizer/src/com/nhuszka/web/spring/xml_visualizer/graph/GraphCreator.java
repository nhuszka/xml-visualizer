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

public class GraphCreator {
	
	public String createGraphInBase64String(List<String> beans, Map<String, String> edges) {
		DirectedSparseMultigraph graph = createGraphFrom(beans, edges);
		BufferedImage image = convertToImage(graph);

		byte[] imageInBytes = getImageInBytes(image);
		byte[] encodedByteArray = Base64.encodeBase64(imageInBytes);
		return new String(encodedByteArray);
	}

	private DirectedSparseMultigraph createGraphFrom(List<String> beans, Map<String, String> edges) {
		DirectedSparseMultigraph g = new DirectedSparseMultigraph();
		for (String bean : beans) {
			g.addVertex(bean);
		}

		int edgeIndex = 1;
		for (Entry<String, String> edge : edges.entrySet()) {
			String from = edge.getKey();
			String to = edge.getValue();
			g.addEdge(edgeIndex++, from, to, EdgeType.DIRECTED);
		}
		return g;
	}
	
	private BufferedImage convertToImage(DirectedSparseMultigraph graph) {
		VisualizationImageServer imageServer = new VisualizationImageServer(new KKLayout(graph), new Dimension(600, 600));
		setUpImageServer(imageServer);
		return (BufferedImage) imageServer.getImage(new Point2D.Double(300D, 300D), new Dimension(600, 600));
	}

	private void setUpImageServer(VisualizationImageServer imageServer) {
		GradientVertexRenderer vertexRenderer = new GradientVertexRenderer(Color.white, Color.red, Color.white,
				Color.blue, imageServer.getPickedVertexState(), false);
		imageServer.getRenderer().setVertexRenderer(vertexRenderer);

		imageServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		imageServer.getRenderer().getVertexLabelRenderer().setPositioner(new BasicVertexLabelRenderer.InsidePositioner());
		imageServer.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);
	}
	
	private byte[] getImageInBytes(BufferedImage image) {
		byte[] imageInByte = {};
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()){
			ImageIO.write(image, "png", stream);
			imageInByte = stream.toByteArray();
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageInByte;
	}
}
