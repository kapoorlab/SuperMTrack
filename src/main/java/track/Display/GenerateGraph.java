package track.Display;

import java.util.ArrayList;
import java.util.Iterator;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import net.imglib2.util.Pair;
import net.imglib2.util.ValuePair;
import track.Identifiers.Trackproperties;
import utils.DistanceMetrics;

public class GenerateGraph implements FilamentTracker {

	/**
	 * 
	 * Takes in ArrayList of ArrayList of the Filament locations located in all the
	 * frames and creates a graph of the deterministic tracking result
	 * 
	 */

	private final ArrayList<ArrayList<Trackproperties>> Alltracks;
	private final long maxframe;
	private SimpleWeightedGraph<double[], DefaultWeightedEdge> graph;
	protected String errorMessage;
	private ArrayList<Pair<Integer, double[]>> ID;

	public GenerateGraph(final ArrayList<ArrayList<Trackproperties>> Alltracks, final int maxframe) {
		this.Alltracks = Alltracks;
		this.maxframe = maxframe;

	}

	public ArrayList<Pair<Integer, double[]>> getSeedID() {

		return ID;
	}

	@Override
	public boolean process() {

		reset();

		/*
		 * Outputs
		 */
		ID = new ArrayList<Pair<Integer, double[]>>();
		graph = new SimpleWeightedGraph<double[], DefaultWeightedEdge>(DefaultWeightedEdge.class);
		for (int frame = 1; frame < maxframe; ++frame) {

			ArrayList<Trackproperties> Baseframestart = Alltracks.get(frame - 1);

			Iterator<Trackproperties> baseobjectiterator = Baseframestart.iterator();

			while (baseobjectiterator.hasNext()) {

				final Trackproperties source = baseobjectiterator.next();

				double sqdist = DistanceMetrics.StraightLineDistance(source.oldpoint, source.newpoint);

				if (sqdist > 0) {
					synchronized (graph) {

						graph.addVertex(source.oldpoint);
						graph.addVertex(source.newpoint);
						final DefaultWeightedEdge edge = graph.addEdge(source.oldpoint, source.newpoint);
						graph.setEdgeWeight(edge, sqdist);
					}

					if (frame == 1) {
						Pair<Integer, double[]> currentid = new ValuePair<Integer, double[]>(source.seedlabel,
								source.oldpoint);
						ID.add(currentid);
					}

				}
			}

		}

		return true;

	}

	@Override
	public SimpleWeightedGraph<double[], DefaultWeightedEdge> getResult() {
		return graph;
	}

	@Override
	public boolean checkInput() {
		final StringBuilder errrorHolder = new StringBuilder();
		;
		final boolean ok = checkInput();
		if (!ok) {
			errorMessage = errrorHolder.toString();
		}
		return ok;
	}

	public void reset() {
		graph = new SimpleWeightedGraph<double[], DefaultWeightedEdge>(DefaultWeightedEdge.class);

		graph.addVertex(Alltracks.get(0).get(0).oldpoint);
	}

	@Override
	public String getErrorMessage() {

		return errorMessage;
	}

}
