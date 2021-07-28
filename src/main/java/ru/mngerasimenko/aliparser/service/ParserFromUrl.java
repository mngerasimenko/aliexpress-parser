package ru.mngerasimenko.aliparser.service;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mngerasimenko.aliparser.domain.JProduct;
import ru.mngerasimenko.aliparser.domain.JResponse;
import ru.mngerasimenko.aliparser.exception.HTTPQueryException;


import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class ParserFromUrl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParserFromUrl.class);

    private Set<JProduct> productSet;

    public ParserFromUrl() {
        productSet = new HashSet<>();
    }

    public Set<JProduct> getProductSet() throws HTTPQueryException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        Long timestamp;
        int limit = 25;
        int offset = 0;
        int oldSizeSet = 0;
        while (productSet.size() < 100) {
            timestamp = new Date().getTime();
            HttpGet request = new HttpGet("https://gpsfront.aliexpress.com/getRecommendingResults.do" +
                    "?callback=jQuery" + timestamp +
                    "&widget_id=5547572" +
                    "&limit=" + limit +
                    "&offset=" + offset +
                    "&phase=1" +
                    "&postback=1" + //timestamp +
                    "_=" + timestamp);

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);

                Gson gson = new Gson();
                JResponse jResponse = gson.fromJson(getJsonFromResponse(result), JResponse.class);

                productSet.addAll(jResponse.getResults());

                if (oldSizeSet < productSet.size()) {
                    LOGGER.info("Parse {} products", productSet.size());
                    oldSizeSet = productSet.size();
                }
                offset += 25;
                if (offset > 5000) break;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new HTTPQueryException("URL Error", e);
            }
        }
        httpClient.close();
        LOGGER.info("Parse finished");
        return productSet;
    }

    private static String getJsonFromResponse(String result) {
        StringBuilder sb = new StringBuilder(result);
        int i = 0;
        while (sb.charAt(i) != '{') i++;
        sb.delete(0, i);
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
