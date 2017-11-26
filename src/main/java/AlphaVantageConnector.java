import parameters.ApiParameter;
import parameters.UrlParameterBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Connection to Alpha Vantage.
 */
public class AlphaVantageConnector implements ApiConnector {
  private static final String BASE_URL = "https://www.alphavantage.co/query?";
  private final String apiKey;
  private final int timeOut;

  public AlphaVantageConnector(String apiKey, int timeOut) {
    this.apiKey = apiKey;
    this.timeOut = timeOut;
  }

  @Override
  public String sendRequest(String symbol, ApiParameter... apiParameters) throws IOException {
    String params = getParameters(symbol, apiParameters);
    URL request = new URL(BASE_URL + params);

    URLConnection connection = request.openConnection();
    connection.setConnectTimeout(timeOut);
    connection.setReadTimeout(timeOut);

    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
    BufferedReader bufferedReader = new BufferedReader(inputStream);
    StringBuilder responseBuilder = new StringBuilder();

    String line;
    while ((line = bufferedReader.readLine()) != null) {
      responseBuilder.append(line);
    }
    bufferedReader.close();
    return responseBuilder.toString();
  }

  private String getParameters(String symbol, ApiParameter... apiParameters) {
    UrlParameterBuilder urlBuilder = new UrlParameterBuilder();
    for (ApiParameter parameter : apiParameters) {
      urlBuilder.append(parameter);
    }
    urlBuilder.append("symbol", symbol);
    urlBuilder.append("apikey", apiKey);
    return urlBuilder.getUrl();
  }
}