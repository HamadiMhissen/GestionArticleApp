package miage.ta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import miage.ta.dao.ArticleRepository;
import miage.ta.dao.PanierRepository;
import miage.ta.entities.Article;
import miage.ta.entities.Panier;

@Controller
public class PanierController {
	@Autowired
	private PanierRepository panierRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping(path = "/Panier")
	public String detailPanier(Model model) {
		Panier p = panierRepository.findAll().get(0);
		model.addAttribute("panier", p);
		return "PanierForm";
	}
	@GetMapping(path="/AjouterAuPanier")
	public String ajouterAuPanier(Model model, Long id) {
		Article a = articleRepository.findById(id).get();
		Panier p = panierRepository.findAll().get(0);
		a.setPanier(p);
		p.getArticles().add(a);
		panierRepository.save(p);
		model.addAttribute("panier", p);
		return "PanierForm";
	}
	
	@GetMapping(path="/EditerPanier")
	public String editerPanier(Model model) {
		Panier p = panierRepository.findAll().get(0);
		model.addAttribute("panier", p);
		return "EditerPanierForm";
	}
	
	@GetMapping(path="/deleteFromPanier")
	public String supprimerDuPanier(Long id) {
		Article article = articleRepository.findById(id).get();
		panierRepository.findAll().get(0).getArticles().remove(article);
		article.setPanier(null);
		articleRepository.save(article);
		return "redirect:/Panier";
	}
}
