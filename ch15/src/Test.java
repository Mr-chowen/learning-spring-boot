import java.util.Locale;

public class Test {

	public static void main(String[] args) {
		Locale locale[]=Locale.getAvailableLocales();
		for (int i = 0; i < locale.length; i++) {
			System.out.println(locale[i].getDisplayCountry()+"="
					+locale[i].getCountry()+" "
					+locale[i].getDisplayLanguage()+"="
					+locale[i].getLanguage());
		}
	}

}
