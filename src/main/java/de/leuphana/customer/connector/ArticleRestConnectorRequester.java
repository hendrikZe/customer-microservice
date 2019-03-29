package de.leuphana.customer.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.leuphana.article.component.structure.Article;

@Service
@FeignClient(name="article-Microservice")
public interface ArticleRestConnectorRequester {
	@GetMapping("api/articles/id/{articleId}")
	public Article getArticleById(@PathVariable("articleId") int articleId);
}