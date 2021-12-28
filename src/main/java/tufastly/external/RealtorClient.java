package tufastly.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import tufastly.model.RealtorResponseItem;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class RealtorClient {
    private static final String URL_TEMPLATE = "https://realtor.p.rapidapi.com/properties/v2/list-sold?sort=sold_date&city=%s&offset=0&state_code=%s&limit=%s";
    private static final String DEFAULT_CITY = "San Francisco";
    private static final String DEFAULT_STATE_CODE = "CA";

    public RealtorResponseItem searchProperty(int limit, String city, String state_code) {
        if (city == null) {
            city = DEFAULT_CITY;
        }
        if (state_code == null) {
            state_code = DEFAULT_STATE_CODE;
        }
        try {
            city = URLEncoder.encode(city, "UTF-8");
            state_code = URLEncoder.encode(state_code, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = String.format(URL_TEMPLATE, city, state_code, limit);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet(url);
        request.setHeader("x-rapidapi-host", "realtor.p.rapidapi.com");
        request.setHeader("x-rapidapi-key", "YOUR_REALTOR_CLIENT_KEY");

        // Create a custom response handler
        ResponseHandler<RealtorResponseItem> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(entity.getContent(), RealtorResponseItem.class);
        };

        try {
            return httpClient.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        RealtorClient client = new RealtorClient();
        RealtorResponseItem res = client.searchProperty(10, null, null);
        System.out.println(res);
    }
}
