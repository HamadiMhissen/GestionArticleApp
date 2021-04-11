package miage.ta.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data	
public class Panier {
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nom;
		
		@OneToMany(targetEntity=Article.class, mappedBy="panier", fetch=FetchType.EAGER)
		private List<Article> articles;
		
		public Panier() {
			super();
			this.articles = new ArrayList<Article>();
		}
		public Panier(Long id, String nom, List<Article> articles) {
			super();
			this.id = id;
			this.nom = nom;
			this.articles = articles;
		}	
}
