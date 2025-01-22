package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;

    @Mock
    WebClient mockClient;
    
    @Mock
    Mono<ApiExampleWrapper> mockWrapper;
    
    @Mock
    ResponseSpec mockRSpec;
    @Mock
    Article mockArticle;
    
    @Mock
    RequestHeadersUriSpec mockSpec;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    	newsApi = new NewsApi();
    	newsApi.setWebClient(mockClient);
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
    	String topic = "Teeth";
    	ApiExampleWrapper realResults = new ApiExampleWrapper();
    	when(mockClient.get()).thenReturn(mockSpec);
    	when(mockSpec.uri((Function<UriBuilder,URI>)any())).thenReturn(mockSpec);
    	when(mockSpec.retrieve()).thenReturn(mockRSpec);
    	when(mockRSpec.bodyToMono(ApiExampleWrapper.class)).thenReturn(mockWrapper);
        //when
    	when(mockWrapper.block()).thenReturn(realResults);
        //then
    	ApiExampleWrapper expectedResults = newsApi.getNewsStoryByTopic(topic);
    	verify(mockClient, times(1)).get();
        assertEquals(expectedResults, realResults);
    }

    @Test
    void itShouldFindStory(){
        //given
    	 String articleTitle = "How brushing your teeth regularly is RUINING your odds of getting laid";
         String articleContent = "just kidding lmao";
         String articleUrl = "theonion.com/articles/real";
         Article fakeArticle = new Article();
         fakeArticle.setTitle(articleTitle);
         fakeArticle.setContent(articleContent);
         fakeArticle.setUrl(articleUrl);

         String topic = "Teeth";
     	ApiExampleWrapper fakeWrapper = new ApiExampleWrapper();
     	List<Article> aList = new ArrayList<>(1);
     	aList.add(fakeArticle);
     	fakeWrapper.setArticles(aList);
     	when(mockClient.get()).thenReturn(mockSpec);
     	when(mockSpec.uri((Function<UriBuilder,URI>)any())).thenReturn(mockSpec);
     	when(mockSpec.retrieve()).thenReturn(mockRSpec);
     	when(mockRSpec.bodyToMono(ApiExampleWrapper.class)).thenReturn(mockWrapper);
     	when(mockWrapper.block()).thenReturn(fakeWrapper);
        //when
     	String message =
                articleTitle + " -\n"
                        + articleContent
                        + "\nFull article: " + articleUrl;
        //then
     	assertEquals(newsApi.findStory(topic),message);
    }


}