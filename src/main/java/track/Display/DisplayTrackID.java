package track.Display;

import java.awt.Color;
import java.util.ArrayList;

import ij.ImagePlus;
import ij.gui.Line;
import ij.gui.Overlay;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Pair;
import net.imglib2.view.IntervalView;

public class DisplayTrackID {

	/**
	 * 
	 * After the calculation is over, this class displays the track IDs 
	 * in front of all the Filaments that were tracked.
	 * 
	 * @param name
	 * @param seedimg
	 * @param IDALL
	 */
	public static void displayseeds(String name, IntervalView<FloatType> seedimg,
			ArrayList<Pair<Integer, double[]>> IDALL) {

		ImagePlus displayimp;

		displayimp = ImageJFunctions.show(seedimg);
		displayimp.setTitle(name + "Display Track ID's");

		Overlay o = displayimp.getOverlay();

		if (displayimp.getOverlay() == null) {
			o = new Overlay();
			displayimp.setOverlay(o);
		}

		o.clear();

		for (int index = 0; index < IDALL.size(); ++index) {

			Line newellipse = new Line(IDALL.get(index).getB()[0], IDALL.get(index).getB()[1],
					IDALL.get(index).getB()[0], IDALL.get(index).getB()[1]);

			newellipse.setStrokeColor(Color.WHITE);
			newellipse.setStrokeWidth(1);
			newellipse.setName("TrackID: " + IDALL.get(index).getA());

			o.add(newellipse);

			o.drawLabels(true);

			o.drawNames(true);

		}

	}

}
