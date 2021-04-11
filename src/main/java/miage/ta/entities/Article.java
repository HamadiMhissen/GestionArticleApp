package miage.ta.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor 
public class Article {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min=3,max=30)
	private String nom;
	@NotNull
	@Size(min=5,max=1000)
	private String descArt;
	@NotNull
	@DecimalMin("0.1") 
	private double prix;
	
	@ManyToOne
	private Panier panier;

	public Article(Long id, @NotNull @Size(min = 3, max = 20) String nom,
			@NotNull @Size(min = 5, max = 1000) String descArt, @NotNull @DecimalMin("0.1") double prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.descArt = descArt;
		this.prix = prix;
	}
}
