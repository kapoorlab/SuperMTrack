package filamentLocators.Simple;

import java.util.ArrayList;

import filamentLocators.CommonOutputLocators;
import filamentLocators.Locators;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

public class LocatebyMSER  <T extends RealType<T> & NativeType<T>>  implements Locators<T> {

	@Override
	public ArrayList<CommonOutputLocators<T>> getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean process() {
		// TODO Auto-generated method stub
		return false;
	}

}
