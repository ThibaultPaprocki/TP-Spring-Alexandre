package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class GithubRepositoryDao {

    private final RestTemplate restTemplate;

    @Autowired
    public GithubRepositoryDao(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate=restTemplateBuilder.build();
    }

    public GitRepositoryDTO getGithubLink(String name, String owner) throws URISyntaxException {
        return this.restTemplate.getForEntity(
                new URI("https://api.github.com/repos/"+owner+"/"+name)
                ,GitRepositoryDTO.class).getBody();
    }

    public GitRepositoryDTO postGithubLink(String name, String owner) throws URISyntaxException {
        return this.restTemplate.postForEntity(new URI("https://api.github.com/repos/" + owner + "/" + name), null,
                GitRepositoryDTO.class).getBody();
    }

    public IssueResponse createIssue(String owner, String nameRepo, IssueRequest issue) throws RestClientException, URISyntaxException{
        HttpHeaders newHttpHeaders = new HttpHeaders();
        newHttpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer 40a2496684f7a56470e9c72ade3d2f8d17909363");
        HttpEntity<IssueRequest> newEntity = new HttpEntity<>(issue, newHttpHeaders);
        return this.restTemplate.postForEntity(new URI("https://api.github.com/repos/"+owner+"/"+nameRepo+"/issue"),newEntity,IssueResponse.class).getBody();
    }

}
