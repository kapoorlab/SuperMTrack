package filamentLocators;

import net.imglib2.FinalInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.integer.IntType;

public class CommonOutputLocators <T extends RealType<T> & NativeType<T>> {

	
		
	public final int framenumber;
	public final int roilabel;
	public final double[] lineparam;
	public final RandomAccessibleInterval<T> Roi;
	public final RandomAccessibleInterval<T> Actualroi;
	public final RandomAccessibleInterval<IntType> intimg;
	public final FinalInterval interval;
	
	public CommonOutputLocators(final int framenumber, final int roilabel, final double[] lineparam ,final RandomAccessibleInterval<T> Roi,
			final RandomAccessibleInterval<T> Actualroi, final FinalInterval interval){
		this.framenumber = framenumber;
		this.roilabel = roilabel;
		this.lineparam = lineparam;
		this.Roi = Roi;
		this.Actualroi = Actualroi;
		this.interval = interval;
		this.intimg = null;
		
	}

	public CommonOutputLocators(final int framenumber, final int roilabel, final double[] lineparam ,final RandomAccessibleInterval<T> Roi,
			final RandomAccessibleInterval<T> Actualroi, final RandomAccessibleInterval<IntType> intimg,  final FinalInterval interval) {
		this.framenumber = framenumber;
		this.roilabel = roilabel;
		this.lineparam = lineparam;
		this.Roi = Roi;
		this.Actualroi = Actualroi;
		this.interval = interval;
		this.intimg = intimg;
	}
		
		
	
}
