import com.msiops.ground.either.Either;

import parameters.FromCurrency;
import parameters.Function;
import parameters.ToCurrency;

/**
 * 
 * Foreign Exchange Rate
 * 
 */
public class ForeignExchange {
	
	  private final ApiConnector apiConnector;
	  /**
	   * TODO
	   * @param apiConnector the connection to the api
	   */
	  public ForeignExchange(ApiConnector apiConnector) {
		    this.apiConnector = apiConnector;
	  }

	public Either<String, Exception> currencyExchangeRate(FromCurrency fromCCY, ToCurrency toCCY){
	    return apiConnector.getRequest(null,Function.CURRENCY_EXCHANGE, fromCCY, toCCY);
	}

}
