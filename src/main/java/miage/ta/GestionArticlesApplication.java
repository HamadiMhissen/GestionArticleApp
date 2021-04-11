package miage.ta;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import miage.ta.dao.ArticleRepository;
import miage.ta.dao.PanierRepository;
import miage.ta.entities.Article;
import miage.ta.entities.Panier;

@SpringBootApplication
public class GestionArticlesApplication implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private PanierRepository panierRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionArticlesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		panierRepository.save(new Panier());
		articleRepository.save(new Article(null, "Alkaline Batteries",
				"AA 1.5 Volt Performance Alkaline Batteries - Pack of 48", 15.49));
		articleRepository.save(new Article(null, "Lightning to USB A Cable",
				"Nylon Braided Lightning to USB A Cable, MFi Certified iPhone Charger, Dark Gray, 6-Foot", 14.49));
		articleRepository.save(new Article(null, "Clothes Drying Laundry",
				"AmazonBasics Foldable Clothes Drying Laundry Rack - White", 26.99));
		articleRepository.save(new Article(null, "Storage Cubes Organizer",
				"Collapsible Fabric Storage Cubes Organizer with Handles, Beige - Pack of 6", 20.02));
		articleRepository.save(new Article(null, "Apple 12W USB Power Adapter",
				"Use this USB-based power adapter to charge your iPhone, iPad, or iPod with Lightning connector at home, on the road, or whenever it's not connected to a computer.",
				19.99));
		articleRepository.save(new Article(null, "Travel Case Organizer",
				"Universal Travel Case Organizer for Small Electronics and Accessories, Black", 14.83));
		articleRepository.save(new Article(null, "Microfiber Cleaning Cloth",
				"Ultra soft, non-abrasive microfiber cloths will not scratch paints, coats or other surfaces", 14.30));
		articleRepository.save(new Article(null, "Food Storage Containers",
				"AmazonBasics Glass Locking Lids Food Storage Containers, 14-Piece Set", 59.99));
		articleRepository
				.save(new Article(null, "Case for iPhone", "Dual-Layer Case for iPhone XS, iPhone X, Clear", 17.49));
		articleRepository.save(new Article(null, "Closet Storage Organizer",
				"AmazonBasics Closet Storage Organizer with Bins and Shelving", 24.99));
		// Pour afficher les articles dans la console
		articleRepository.findAll().forEach(article -> {
			System.out.println(article.getNom() + " " + article.getPrix());
		});
	}

}
