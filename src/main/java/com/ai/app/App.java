package com.ai.app;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }

    @Bean
    public TokenTextSplitter getTokenTextSplitter() {
        return new TokenTextSplitter();
    }

    @Bean
    public ChatClient chatClient(VectorStore vectorStore,ChatClient.Builder builder){
       return builder.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults())).build();
    }
}
