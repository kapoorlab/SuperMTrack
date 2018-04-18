package filamentLocators;

import java.util.ArrayList;

import net.imglib2.algorithm.OutputAlgorithm;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

public interface  Locators <T extends RealType<T> & NativeType<T>>  extends  OutputAlgorithm<ArrayList<CommonOutputLocators<T>>> {
	
	
	

}
