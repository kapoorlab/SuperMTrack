package track.Display;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import net.imglib2.algorithm.OutputAlgorithm;

public interface FilamentTracker extends OutputAlgorithm< SimpleWeightedGraph< double[], DefaultWeightedEdge > > {

}
