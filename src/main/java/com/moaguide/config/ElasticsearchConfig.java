//package com.moaguide.config;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import javax.net.ssl.SSLContext;
//import java.security.KeyStore;
//
//@Configuration
//@Profile({"blue", "green"})
//public class ElasticsearchConfig {
//
//    @Value("${spring.elasticsearch.username}")  // application.yml에서 값을 가져옴
//    private String username;
//
//    @Value("${spring.elasticsearch.password}")  // application.yml에서 값을 가져옴
//    private String password;
//
//    @Value("${spring.elasticsearch.uris}")  // application.yml에서 Elasticsearch URL을 가져옴
//    private String elasticsearchUrl;
//
//    @Bean
//    public RestHighLevelClient client() throws Exception {
//        // SSLContextBuilder를 사용해 SSL 컨텍스트 생성
//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), (chain, authType) -> true) // 모든 인증서 신뢰
//                .build();
//
//        // 인증 정보를 설정
//        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials(username, password));
//
//        RestClientBuilder builder = RestClient.builder(HttpHost.create(elasticsearchUrl))
//                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder
//                        .setSSLContext(sslContext)
//                        .setDefaultCredentialsProvider(credentialsProvider));  // 인증 정보 설정
//
//        return new RestHighLevelClient(builder);
//    }
//}