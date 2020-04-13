package org.projetjee.web;

import java.util.List;

import org.projetjee.dao.ProduitRepository;
import org.projetjee.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Puisque c'est un controleur, il daut utiliser l'annotaion controller
@Controller
public class ProduitController {
	
	//Afficher la liste des produits: nous avons besoins de la couche DAO
	//Il faut décalarer l'interface ProduitRepository
	@Autowired
	private ProduitRepository produitRepository;
	
	//Méthode qui permet de retourner une vue index.html. Quand on écrit index tt cours, par défaut c index.html
	//Pour accéder à cette méthode on utilise GetMapping càd si une requete http est envoyé avec get vers path="/index" c'est cette méthode qui va s'éxecuter
	//Il faut créer le fichier index dans le fichier ressources
	@GetMapping(path="/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(path="/products")
	//Récupérer les paramètres
	public String products(Model model, 
			@RequestParam(name="page", defaultValue = "0")int page,
			//içi même si nous avons x produits, on lui demande de nous envoyer que 5
			@RequestParam(name="size", defaultValue="5") int size,
			//Ajouter le paramètre motCle qui contient le mot recherché saisit par l'utilisateur
			@RequestParam(name="motCle", defaultValue="")String motCle
			) {
		
		//Récupérer tout les produits dans une liste ou page ensuite il faut stocker la liste dans le modèle: donc il faut le déclarer dans l'entête de la méthode  List<Produit> produits=produitRepository.findAll();
		
		//On utilise page et size que nous avons reçu comme paramètre
		Page<Produit> pageProduits=produitRepository
				.findByDesignationContains(motCle,PageRequest.of(page, size));
		//On stocke dans le modèle un attribut =clé quis'appelle listProduits dont la valeur est la liste des produits
		//Ensuite dans la vue il faut écrire le code thymeleaf pour faire une boucle sur les produits qui se trouve dans la liste des produits revenir vers la vue ==> products.html
		model.addAttribute("pageProduits", pageProduits);
		//Ajouter un attribut qui s'appelle pages et on créer un tableau de dimensions = nombre de pages ==> avoir les pages dans le modèle
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		//Ajouter le mot clé dans le modèle
		model.addAttribute("motCle", motCle);
		model.addAttribute("pages", new int[pageProduits.getTotalPages()]);
		return "products";
	}

}