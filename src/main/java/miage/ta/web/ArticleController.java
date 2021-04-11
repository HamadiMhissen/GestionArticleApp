package miage.ta.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.Param;

import com.mysql.fabric.xmlrpc.base.Params;

import miage.ta.dao.ArticleRepository;
import miage.ta.entities.Article;

@Controller
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	/*
	 * @GetMapping(path = "/Panier") public String detailPanier(Model model) {
	 * //List<Article> articles = articleRepository.findAll();
	 * //model.addAttribute("article", a); return "PanierForm"; }
	 */

	@GetMapping(path = { "/", "/articles" })
	public String listArticles(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "taille", defaultValue = "5") int taille) {
		Page<Article> articles = articleRepository.findAll(PageRequest.of(page, taille));
		model.addAttribute("articles", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("pageCourante", page);
		return "index";
	}

	@GetMapping(path = "/AjouterArticle")
	public String ajouterArticle(Model model) {
		model.addAttribute("article", new Article());
		return "AjouterArticleForm";
	}

	@PostMapping(path = "/EnregistrerArticleAjout")
	public String enregistrerArticleAjout(@Valid Article a, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "AjouterArticleForm";
		else
			articleRepository.save(a);
		return "redirect:/articles ";
	}

	@PostMapping(path = "/EnregistrerArticleEdit")
	public String enregistrerArticleEdit(@Valid Article a, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "EditerArticleForm";
		else
			articleRepository.save(a);
		return "EditerArticleForm";
	}

	@GetMapping(path = "/deleteArticle")
	public String SupprimerArticle(Long id, @RequestParam(name = "page") int page) {
		articleRepository.deleteById(id);
		return "redirect:/articles?page=" + page;
	}

	@GetMapping(path = "/editArticle")
	public String ModifierArticle(Model model, Long id) {
		Article a = articleRepository.findById(id).get();
		model.addAttribute("article", a);
		return "EditerArticleForm";
	}

	@GetMapping(path = "/voirArticle")
	public String VoirArticle(Model model, Long id) {
		Article a = articleRepository.findById(id).get();
		model.addAttribute("article", a);
		return "DetailArticleForm";
	}

	@PostMapping(path = "/DetailArticle")
	public String detailArticle(Long id, @RequestParam(value = "action", required = true) String action, Model model) {
		switch (action) {
		case "edit": {
			Article a = articleRepository.findById(id).get();
			model.addAttribute("article", a);
			return "EditerArticleForm";
		}
		case "supprimer": {
			articleRepository.deleteById(id);
			return "redirect:/articles";
		}
		}
		return "index";
	}

}
