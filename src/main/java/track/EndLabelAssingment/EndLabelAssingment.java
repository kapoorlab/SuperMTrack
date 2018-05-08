package track.EndLabelAssingment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.imglib2.util.Pair;
import net.imglib2.util.ValuePair;

public class EndLabelAssingment {

	/**
	 * Method for assigning the plus minus or zero end label to the microtubule
	 * being tracked based on user input If only one end is marked to be tracked it
	 * is called the zero end and if both of the ends are to be tracked the program
	 * determines which is the faster growing end and labels it as plus end
	 * 
	 * 
	 * @author aimachine
	 *
	 */
	public static enum Whichend {

		start, end, both, none, user;
	}

	public final int seedid;
	public final String plusorminus;

	public EndLabelAssingment(final int seedid, final String plusorminus) {

		this.seedid = seedid;
		this.plusorminus = plusorminus;

	}

	public static Pair<ArrayList<EndLabelAssingment>, ArrayList<EndLabelAssingment>> AssignLabels(
			final HashMap<Integer, Double> startseedmap, final HashMap<Integer, Double> endseedmap,
			final HashMap<Integer, Whichend> seedmap) {

		ArrayList<EndLabelAssingment> plusminusendlist = new ArrayList<EndLabelAssingment>();
		ArrayList<EndLabelAssingment> plusminusstartlist = new ArrayList<EndLabelAssingment>();

		Pair<ArrayList<EndLabelAssingment>, ArrayList<EndLabelAssingment>> SeedPairList = new ValuePair<ArrayList<EndLabelAssingment>, ArrayList<EndLabelAssingment>>(
				plusminusendlist, plusminusstartlist);

		Iterator<Entry<Integer, Double>> it = startseedmap.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<Integer, Double> pair = (Map.Entry<Integer, Double>) it.next();

			int key = pair.getKey();
			double endrate = 0;
			double startrate = 0;

			if (endseedmap.containsKey(key) && endseedmap != null)
				endrate = endseedmap.get(key);
			if (startseedmap.containsKey(key) && startseedmap != null)
				startrate = startseedmap.get(key);
			String plusorminusend = (startrate > endrate) ? "Minus" : "Plus";
			String plusorminusstart = (startrate > endrate) ? "Plus" : "Minus";
			if (seedmap.get(key) == Whichend.start
					|| seedmap.get(key) == Whichend.end && seedmap.get(key) != Whichend.both) {
				plusorminusend = "Zeroend";
				plusorminusstart = "Zeroend";
			}

			EndLabelAssingment pmseedEndB = new EndLabelAssingment(key, plusorminusend);
			plusminusendlist.add(pmseedEndB);

			EndLabelAssingment pmseedEndA = new EndLabelAssingment(key, plusorminusstart);
			plusminusstartlist.add(pmseedEndA);
		}

		Iterator<Entry<Integer, Double>> itend = endseedmap.entrySet().iterator();

		while (itend.hasNext()) {

			Map.Entry<Integer, Double> pair = (Map.Entry<Integer, Double>) itend.next();

			int key = pair.getKey();
			double endrate = 0;
			double startrate = 0;

			if (endseedmap.containsKey(key) && endseedmap != null)
				endrate = endseedmap.get(key);
			if (startseedmap.containsKey(key) && startseedmap != null)
				startrate = startseedmap.get(key);

			String plusorminusend = (startrate > endrate) ? "Minus" : "Plus";
			String plusorminusstart = (startrate > endrate) ? "Plus" : "Minus";
			if (seedmap.get(key) == Whichend.start
					|| seedmap.get(key) == Whichend.end && seedmap.get(key) != Whichend.both) {
				plusorminusend = "Zeroend";
				plusorminusstart = "Zeroend";
			}

			EndLabelAssingment pmseedEndB = new EndLabelAssingment(key, plusorminusend);
			if (plusminusendlist.size() > 0) {
				for (int i = 0; i < plusminusendlist.size(); ++i) {

					if (plusminusendlist.get(i).seedid != key)
						plusminusendlist.add(pmseedEndB);
				}
			} else
				plusminusendlist.add(pmseedEndB);

			EndLabelAssingment pmseedEndA = new EndLabelAssingment(key, plusorminusstart);
			if (plusminusstartlist.size() > 0) {
				for (int i = 0; i < plusminusstartlist.size(); ++i) {

					if (plusminusstartlist.get(i).seedid != key)
						plusminusstartlist.add(pmseedEndA);
				}
			}

			else
				plusminusstartlist.add(pmseedEndA);

		}

		return SeedPairList;

	}

}
