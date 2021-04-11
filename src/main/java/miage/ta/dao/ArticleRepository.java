package miage.ta.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import miage.ta.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
