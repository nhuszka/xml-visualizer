package com.nhuszka.web.spring.xml_visualizer.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;

public class GraphCreator {
	
	public void demo() {
		List<String> beans = new ArrayList<>();
		for (int i = 1; i <= 30; ++i) {
			beans.add("bean" + i);
		}

		Map<String, String> edges = new HashMap<>();
		for (int i = 1; i < 24; ++i) {
			edges.put("bean" + i, "bean" + (i + 4));
		}

		final int size = 1; // does not matter now
		new GraphCreator().createGraph(beans, edges, size, "D:\\graph.png");
	}

	public void createGraph(List<String> beans, Map<String, String> edges, int size, String filePath) {
		DirectedSparseMultigraph graph = createGraph(beans, edges);
		BufferedImage image = convertToImage(graph, size);
		saveFile(filePath, image);
	}

	private BufferedImage convertToImage(DirectedSparseMultigraph graph, int size) {
		VisualizationImageServer vis = new VisualizationImageServer(new KKLayout(graph), new Dimension(600, 600));
		GradientVertexRenderer vertexRenderer = new GradientVertexRenderer(
				Color.white, Color.red, Color.white, Color.blue, vis.getPickedVertexState(), false);
		vis.getRenderer().setVertexRenderer(vertexRenderer);

		vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vis.getRenderer().getVertexLabelRenderer().setPositioner(new BasicVertexLabelRenderer.InsidePositioner());
		vis.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);

		return (BufferedImage) vis.getImage(new Point2D.Double(300D, 300D), new Dimension(600, 600));
	}

	private DirectedSparseMultigraph createGraph(List<String> beans, Map<String, String> edges) {
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

	private void saveFile(String filePath, BufferedImage image) {
		try {
			ImageIO.write(image, "png", new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
