package ua.kiev.supersergey.deputysearch.google_search_client;

import java.util.HashMap;
import java.util.Map;

public class GoogleSearchClientRequest {
    public static GoogleSearchClientRequestBuilder newBuilder() {
        return new GoogleSearchClientRequestBuilder();
    };

    public static class GoogleSearchClientRequestBuilder {
        private String cx;
        private String apiKey;
        private String companyName;

        public GoogleSearchClientRequestBuilder withCx(String cx) {
            this.cx = cx;
            return this;
        }

        public GoogleSearchClientRequestBuilder withApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public GoogleSearchClientRequestBuilder withCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("cx", cx);
            requestParams.put("key", apiKey);
            requestParams.put("query", "\"" + companyName + "\"");
            return requestParams;
        }
    };
}
