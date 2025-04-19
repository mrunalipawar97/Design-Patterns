package edu.neu.csye7374;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 100 TOTAL POINTS
 * WeatherModel Midterm SKELETON
 * 
 * Complete the coding in this class.
 * 
 * Use the following Design Patterns:
 *  Adapter, Builder, Composite, Decorator, Factory, Singleton, Strategy
 * 
 * Model a Weather Bureau (WeatherBureau) with weather forecasts
 *  a forecast is Weather Conditions (WeatherAPI)
 *  for a locale (Locale), e.g. Boston, NY, DC, etc.
 *  Weather Bureau contain forecasts for various Locale, e.g. Boston, NY, DC, etc.
 * 
 * Weather conditions are TWO specific metrics: 
 * 	measurement for TEMPERATURE (degrees) AND 
 * 	probability of PRECIPITATION (e.g. % chance of rain).
 * 
 * Utilize one SINGLE factory class (WeatherFactoryAPI)
 *  injected with VARIOUS builder objects (WeatherBuilderAPI):
 *  
 *  	20 POINTS 1. Create Locale builder for weather conditions in a place:
 *  	20 POINTS 2. Create Weather condition builder:
 *  	20 POINTS 3. Create MODELED Weather condition builder:
 *  	20 POINTS 4. Create a forecast builder (weather condition affecting a Locale):
 *  	20 POINTS 5. Create a REGIONAL forecast builder  (COMBINED weather condition at MULTIPLE Locale):
 *  
 *  to create the DIFFERENT forecast objects (WeatherAPI)
 *  to initialize the Weather Bureau.
 *  
 *  DETAILS FOLLOWING IN CLASS
 *  
 */
public class WeatherModel {
	private static final int MAJOR_VERSION;
	private static final int MINOR_VERSION;
	private static final String VERSION;
	/**
	 * One and ONLY WeatherFactoryAPI Builder Factory use only one factory
	 * customized by using Builder objects (WeatherAPI)
	 */
	private static final WeatherFactoryAPI f;

	static {
		MAJOR_VERSION = 1;
		MINOR_VERSION = 9;
		VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + 22;
		f = WeatherFactory.getInstance();
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * WeatherBureau class models broadcast of forecasts: (weather conditions, e.g.
	 * TEMPERATURE and PRECIPITATION % at various Locale)
	 */
	private static class WeatherBureau implements Runnable {
		private static final List<WeatherAPI> localeList;
		private static final WeatherBuilderAPI DEFAULT;
		private static final WeatherBuilderAPI NASHUA;
		private static final WeatherBuilderAPI BOSTONa;
		private static final WeatherBuilderAPI BOSTONb;
		private static final WeatherBuilderAPI BOSTONc;
		private static final WeatherBuilderAPI CT;
		private static final WeatherBuilderAPI NJ;
		private static final WeatherBuilderAPI NYC;
		private static final WeatherBuilderAPI NYCb;
		private static final WeatherBuilderAPI DC;
		private static final WeatherBuilderAPI DCa;
		private static final WeatherBuilderAPI NYComposite;

		/**
		 * ** STUDENT TODO **
		 *
		 * static initialization block (executed with class) used to initialize Weather
		 * Bureau
		 */
		static {
			/**
			 * ** STUDENT TODO **
			 *
			 * use Strategy pattern to create Weather Modeling Algorithms which will
			 * SPECIFICALLY affect and alter weather conditions AS FOLLOWS: 
			 * Weather Model A: Increases Temperature by 2 degrees and increases Probability of Precipitation by 2% 
			 * Weather Model B: Decreases Temperature by 2 degrees and Decreases Probability of Precipitation by 2%
			 */
			WeatherStrategyAPI weatherModelA = new WeatherModelA();
			WeatherStrategyAPI weatherModelB = new WeatherModelB();
			
			/**
			 * ** STUDENT TODO **
			 * 
			 * Create Builder objects (WeatherBuilderAPI)
			 *  which will be used to create weather forecasts (WeatherAPI)
			 *  where a forecast is a Locale with one or more Weather Condition.
			 * 
			 * 20 POINTS 1. Create Locale builder for weather conditions in a place:
			 * 	use Builder pattern to create a Locale builder,
			 *  e.g. Boston, NY etc., with DEFAULT Weather Conditions,
			 *  e.g. current temp of NORMAL_TEMP degrees and a NORMAL_PRECIP % chance of precipitation
			 * 
			 * 20 POINTS 2. Create Weather condition builder:
			 *  (use Builder and Decorator patterns to create a Weather Condition decorator)
			 *  NOTE: Weather Conditions change the current Weather Conditions in a place (Locale).
			 *  
			 * 20 POINTS 3. Create MODELED Weather condition builder:
			 *  (use Builder, Decorator and Strategy patterns to create a Weather Condition decorator
			 *   altered by a weather model)
			 *   
			 * 20 POINTS 4. Create a forecast builder (weather condition affecting a Locale):
			 *  using Builder, Decorator, Factory and Strategy patterns
			 *  to create a Locale affected with one or more weather conditions
			 * 
			 * 20 POINTS 5. Create a REGIONAL forecast builder  (COMBINED weather condition at MULTIPLE Locale):
			 *  an AVERAGE weather condition from MULTIPLE Locale
			 *  using Adapter, Builder, Composite, Decorator, Factory, Strategy patterns
			 *  to create a COMPOSITE of multiple Locale
			 *  each affected with one or more Weather Conditions
			 * 
			 * 
			 * 1. Simulate forecast: Start with locale at NORMAL TEMP and NORMAL PRECIPITATION
			 * 2. AND ADD to Locale additional weather conditions as specified below
			 *	OR 
			 * 1. Simulate changing forecast: Use a PREVIOUS forecast
			 * 2. AND ADD additional weather conditions as specified below
			 *	OR 
			 * 1. Simulate MODELED forecast using a weather Model A or B: Start with locale at NORMAL TEMP and NORMAL PRECIPITATION
			 * 2. AND ADD additional weather conditions ALTERED by Weather Model as specified below
			 *	OR 
			 * 1. Simulate multi-locale REGIONAL forecast using a Combination of PREVIOUS forecast at multiple Local
			 */
			

			/**
			 * Forecast #1: Nashua + FREEZING TEMPS and RAIN...
			 */
			NASHUA = new LocaleBuilder("Nashua", WeatherAPI.NORMAL_TEMP + WeatherAPI.FREEZING_TEMP_OFFSET,
					WeatherAPI.NORMAL_PRECIP + WeatherAPI.RAIN_PRECIP_OFFSET);

			/**
			 * Boston has degrading weather conditions (each successive forecast adds
			 * weather conditions to previous forecast)
			 */
			/**
			 * Forecast #2: Boston + COOL TEMPS and MIST...
			 */
			BOSTONa = new LocaleBuilder("Boston", WeatherAPI.NORMAL_TEMP + WeatherAPI.COOL_TEMP_OFFSET,
					WeatherAPI.NORMAL_PRECIP + WeatherAPI.MIST_PRECIP_OFFSET);

			/**
			 * Forecast #3: Forecast #2 + COOL TEMPS and MIST...
			 */
			BOSTONb = new WeatherDecoratorBuilder(f.getObject(BOSTONa), WeatherAPI.COOL_TEMP_OFFSET,
					WeatherAPI.MIST_PRECIP_OFFSET);

			/**
			 * Forecast #4: Forecast #3 + COOL TEMPS and MIST...
			 */
			BOSTONc = new WeatherDecoratorBuilder(f.getObject(BOSTONb), WeatherAPI.COOL_TEMP_OFFSET,
					WeatherAPI.MIST_PRECIP_OFFSET);

			/**
			 * Forecast #5: Connecticut + COOL TEMPS and SLIGHT rain...
			 */
			CT = new LocaleBuilder("Connecticut", WeatherAPI.NORMAL_TEMP + WeatherAPI.COOL_TEMP_OFFSET,
					WeatherAPI.NORMAL_PRECIP + WeatherAPI.SLIGHT_PRECIP_OFFSET);

			/**
			 * Forecast #6: New Jersey + HOT TEMPS and SLIGHT rain...
			 */
			NJ = new LocaleBuilder("New Jersey", WeatherAPI.NORMAL_TEMP + WeatherAPI.HOT_TEMP_OFFSET,
					WeatherAPI.NORMAL_PRECIP + WeatherAPI.SLIGHT_PRECIP_OFFSET);

			/**
			 * Forecast #7: New York + WARM TEMPS and SLIGHT rain...
			 */
			NYC = new LocaleBuilder("New York", WeatherAPI.NORMAL_TEMP + WeatherAPI.WARM_TEMP_OFFSET,
					WeatherAPI.NORMAL_PRECIP + WeatherAPI.SLIGHT_PRECIP_OFFSET);

			/**
			 * Forecast #8 MODELED from Model B (weatherModelB): New York + WARM TEMPS and
			 * SLIGHT rain...
			 */
			NYCb = new WeatherBuilderModeled(f.getObject(NYC), weatherModelB);

			/**
			 * Forecast #9: DC + HOT TEMPS and SHOWERS rain...
			 */
			DC = new LocaleBuilder("DC", WeatherAPI.NORMAL_TEMP + WeatherAPI.HOT_TEMP_OFFSET,
					WeatherAPI.NORMAL_PRECIP + WeatherAPI.SHOWER_PRECIP_OFFSET);

			/**
			 * Forecast #10 MODELED from Model A (weatherModelA): DC + HOT TEMPS and SHOWERS
			 * rain...
			 */
			DCa = new WeatherBuilderModeled(f.getObject(DC), weatherModelA);

			/**
			 * Forecast #11: REGIONAL TriState CT, NJ, NYC (Forecast #5,#6 & #7) COMBINED
			 * (AVERAGED FORECAST METRICS) TRI-STATE REGIONAL FORCAST...
			 */
			NYComposite = new CompositeForecastBuilder("NY Tri-State Region", f.getObject(CT), f.getObject(NJ),
					f.getObject(NYC));

			DEFAULT = new LocaleBuilder("Default", WeatherAPI.NORMAL_TEMP, WeatherAPI.NORMAL_PRECIP);
			/**
			 * GIVEN: use the one and only builder factory with different builder objects to
			 * create the weather forecast object for each locale and initialize all weather
			 * forecasts in Weather Bureau
			 */
			WeatherAPI[] initializedLocaleArray = { f.getObject(DEFAULT), f.getObject(NASHUA), f.getObject(BOSTONa),
					f.getObject(BOSTONb), f.getObject(BOSTONc), f.getObject(CT), f.getObject(NJ), f.getObject(NYC),
					f.getObject(NYCb), f.getObject(DC), f.getObject(DCa), f.getObject(NYComposite), };
			localeList = new ArrayList<WeatherAPI>(Arrays.asList(initializedLocaleArray));
		}

		@Override
		public void run() {
			System.out.println(this);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();

			for (WeatherAPI locale : localeList) {
				sb.append(locale).append("\n");
			}
			return sb.toString();
		}

	}

	/**
	 * interface WeatherAPI implemented by each weather forecast for each locale
	 */
	private interface WeatherAPI {
		int ARCTIC_TEMP_OFFSET = -70;
		int SUB_FREEZING_TEMP_OFFSET = -40;
		int FREEZING_TEMP_OFFSET = -30;
		int COLD_TEMP_OFFSET = -20;
		int COOL_TEMP_OFFSET = -10;
		int NORMAL_TEMP = 65;
		int WARM_TEMP_OFFSET = 10;
		int HOT_TEMP_OFFSET = 20;
		int HEAT_WAVE_TEMP_OFFSET = 30;
		int NORMAL_PRECIP = 0;
		int MIST_PRECIP_OFFSET = 10;
		int CHANCE_PRECIP_OFFSET = 20;
		int SLIGHT_PRECIP_OFFSET = 30;
		int SHOWER_PRECIP_OFFSET = 60;
		int RAIN_PRECIP_OFFSET = 80;
		DecimalFormat fmt = new DecimalFormat("##.##");

		String getName();

		int getTemperature();

		int getProbabilityPrecipitation();

		default String weatherToString() {
			StringBuilder sb = new StringBuilder("Weather in ");

			sb.append(getName());
			sb.append(" NOW: current temp of ").append(getTemperature()).append(" degrees");
			sb.append(" and a ").append(getProbabilityPrecipitation()).append("% chance of precipitation (rain)");

			return sb.toString();
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * interface WeatherBuilderAPI
	 */
	private interface WeatherBuilderAPI {
		WeatherAPI build();
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * interface WeatherFactoryAPI
	 */
	private interface WeatherFactoryAPI {
		WeatherAPI getObject(WeatherBuilderAPI builder);
	}

	/**
	 * Singleton implementation of WeatherFactory
	 */
	private static class WeatherFactory implements WeatherFactoryAPI {
		
		private static WeatherFactory instance;

		private WeatherFactory() {
			
		}

		public static synchronized WeatherFactory getInstance() {
			if (instance == null) {
				instance = new WeatherFactory();
			}
			return instance;
		}

		@Override
		public WeatherAPI getObject(WeatherBuilderAPI builder) {
			return builder.build();
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * WeatherEnumSingletonFactory
	 */
	public enum WeatherEnumSingletonFactory {
		
		INSTANCE;

		private final WeatherFactoryAPI factory = new WeatherFactory();

		public WeatherFactoryAPI getFactory() {
			return factory;
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * class WeatherDecoratorAPI
	 */
	private static abstract class WeatherDecoratorAPI implements WeatherAPI {
		
		protected WeatherAPI weather;

		public WeatherDecoratorAPI(WeatherAPI weather) {
			this.weather = weather;
		}

		@Override
		public String getName() {
			return weather.getName();
		}

		@Override
		public String toString() {
			return weatherToString();
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * Locale class represents a weather location
	 */
	private static class Locale implements WeatherAPI {
		
		private String name;
		private int temperature;
		private int precipitation;

		public Locale(String name, int temperature, int precipitation) {
			this.name = name;
			this.temperature = temperature;
			this.precipitation = precipitation;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getTemperature() {
			return temperature;
		}

		@Override
		public int getProbabilityPrecipitation() {
			return precipitation;
		}

		@Override
		public String toString() {
			return weatherToString();
		}
	}

	private static class LocaleBuilder implements WeatherBuilderAPI {
		
		private String name;
		private int temperature;
		private int precipitation;

		public LocaleBuilder(String name, int temperature, int precipitation) {
			this.name = name;
			this.temperature = temperature;
			this.precipitation = precipitation;
		}

		@Override
		public WeatherAPI build() {
			return new Locale(name, temperature, precipitation);
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * WeatherDecorator class represents a compounding weather condition 
	 * which contributes to the forecast
	 */
	private static class WeatherDecorator extends WeatherDecoratorAPI {
		
		private int temperatureOffset;
		private int precipitationOffset;

		public WeatherDecorator(WeatherAPI weather, int temperatureOffset, int precipitationOffset) {
			super(weather);
			this.temperatureOffset = temperatureOffset;
			this.precipitationOffset = precipitationOffset;
		}

		@Override
		public int getTemperature() {
			return weather.getTemperature() + temperatureOffset;
		}

		@Override
		public int getProbabilityPrecipitation() {
			return weather.getProbabilityPrecipitation() + precipitationOffset;
		}
	}

	/**
	 * Weather Decorator Builder implementation
	 */
	private static class WeatherDecoratorBuilder implements WeatherBuilderAPI {
		
		private WeatherAPI baseWeather;
		private int temperatureOffset;
		private int precipitationOffset;

		public WeatherDecoratorBuilder(WeatherAPI baseWeather, int temperatureOffset, int precipitationOffset) {
			this.baseWeather = baseWeather;
			this.temperatureOffset = temperatureOffset;
			this.precipitationOffset = precipitationOffset;
		}

		@Override
		public WeatherAPI build() {
			return new WeatherDecorator(baseWeather, temperatureOffset, precipitationOffset);
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * interface WeatherStrategyAPI
	 *
	 * Strategy pattern
	 */
	private interface WeatherStrategyAPI {
		
		int modifyTemperature(int temp);

		int modifyPrecipitation(int precipitation);
	}

	/**
	 * Weather Model A: Increases Temperature by 2 degrees and increases Probability of Precipitation by 2% 
	 */
	private static class WeatherModelA implements WeatherStrategyAPI {
		
		@Override
		public int modifyTemperature(int temp) {
			return temp + 2;
		}

		@Override
		public int modifyPrecipitation(int precipitation) {
			return precipitation + 2;
		}
	}

	/**
	 * Weather Model B: Decreases Temperature by 2 degrees and Decreases Probability of Precipitation by 2%
	 */
	private static class WeatherModelB implements WeatherStrategyAPI {
		
		@Override
		public int modifyTemperature(int temp) {
			return temp - 2;
		}

		@Override
		public int modifyPrecipitation(int precipitation) {
			return precipitation - 2;
		}
	}

	/**
	 * ModeledWeather class uses Strategy pattern to modify weather conditions
	 */
	private static class ModeledWeather extends WeatherDecoratorAPI {
		
		private WeatherStrategyAPI strategy;

		public ModeledWeather(WeatherAPI weather, WeatherStrategyAPI strategy) {
			super(weather);
			this.strategy = strategy;
		}

		@Override
		public int getTemperature() {
			return strategy.modifyTemperature(weather.getTemperature());
		}

		@Override
		public int getProbabilityPrecipitation() {
			return strategy.modifyPrecipitation(weather.getProbabilityPrecipitation());
		}
	}

	/**
	 * WeatherBuilderModeled implementation
	 */
	private static class WeatherBuilderModeled implements WeatherBuilderAPI {
		
		private WeatherAPI baseWeather;
		private WeatherStrategyAPI strategy;

		public WeatherBuilderModeled(WeatherAPI baseWeather, WeatherStrategyAPI strategy) {
			this.baseWeather = baseWeather;
			this.strategy = strategy;
		}

		@Override
		public WeatherAPI build() {
			return new ModeledWeather(baseWeather, strategy);
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * interface LocaleComponentAPI
	 *
	 * Composite pattern
	 */
	private interface LocaleComponentAPI extends WeatherAPI {
		int getSize();
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * class LocaleComponentBase
	 *
	 * Weather Locale Component Base class abstraction for all locale (composite and leaf alike)
	 * 
	 * @author dpeters
	 *
	 */
	private static abstract class LocaleComponentBase implements LocaleComponentAPI {
		
		@Override
		public String toString() {
			return weatherToString();
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * class LeafLocaleAdapter is a Locale Leaf object with no children (single Locale) 
	 * Adapting a WeatherAPI object to a LocaleComponentAPI Leaf object
	 */
	private static class LeafLocaleAdapter extends LocaleComponentBase {
		private WeatherAPI locale;

		public LeafLocaleAdapter(WeatherAPI locale) {
			this.locale = locale;
		}

		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getName() {
			return locale.getName();
		}

		@Override
		public int getTemperature() {
			return locale.getTemperature();
		}

		@Override
		public int getProbabilityPrecipitation() {
			return locale.getProbabilityPrecipitation();
		}

		@Override
		public String toString() {
			return weatherToString();
		}
	}

	/**
	 * ** STUDENT TODO **
	 *
	 * class CompositeLocaleAdapter is a composite Locale (representing multiple locale) object with children 
	 * Adapting a group of WeatherAPI object to a LocaleComponentAPI composite object
	 */
	private static class CompositeLocaleAdapter extends LocaleComponentBase {
		
		private String name;
		private List<LocaleComponentAPI> components = new ArrayList<>();

		public CompositeLocaleAdapter(String name) {
			this.name = name;
		}

		public void addComponent(WeatherAPI locale) {
			components.add(new LeafLocaleAdapter(locale));
		}

		@Override
		public int getSize() {
			return components.size();
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getTemperature() {
			if (components.isEmpty()) {
				return NORMAL_TEMP;
			}

			int total = 0;
			for (LocaleComponentAPI component : components) {
				total += component.getTemperature();
			}
			return total / components.size();
		}

		@Override
		public int getProbabilityPrecipitation() {
			if (components.isEmpty()) {
				return NORMAL_PRECIP;
			}

			int total = 0;
			for (LocaleComponentAPI component : components) {
				total += component.getProbabilityPrecipitation();
			}
			return total / components.size();
		}

		@Override
		public String toString() {
			return weatherToString();
		}
	}

	/**
	 * CompositeForecastBuilder for combining multiple locales
	 */
	private static class CompositeForecastBuilder implements WeatherBuilderAPI {
		
		private String regName;
		
		private List<WeatherAPI> locales = new ArrayList<>();

		public CompositeForecastBuilder(String regionName, WeatherAPI... locales) {
			this.regName = regionName;
			this.locales.addAll(Arrays.asList(locales));
		}

		@Override
		public WeatherAPI build() {
			CompositeLocaleAdapter composite = new CompositeLocaleAdapter(regName);
			for (WeatherAPI locale : locales) {
				composite.addComponent(locale);
			}
			return composite;
		}
	}
	
	/**
	 * GIVEN:
	 * 
	 * demonstrate the use of this class
	 */
	public static void demo() {
		System.out.println("\n\t" + WeatherModel.class.getName() + ".demo() [version " + VERSION + "]...");
		
		System.out.println("\n\t *** TODAY's WEATHER FORECASTS: *** \n");

		new WeatherBureau().run();
		
		System.out.println("\n\t" + WeatherModel.class.getName() + ".demo() [version " + VERSION + "]... done!");
	}
}
