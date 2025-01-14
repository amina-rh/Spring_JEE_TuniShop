package org.projetjee.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Ligne_Commande {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLigneCommande;
	private int quantiteProduit;
	private double prixCommande;
	
	@ManyToOne
	@JoinColumn(name="idCommande")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Produit produit;
}
