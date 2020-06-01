<?php

namespace commandeBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande")
 * @ORM\Entity
 */
class Commande
{
    /**
     * @var string
     *
     * @ORM\Column(name="Libelle_P", type="string", length=255, nullable=false)
     */
    private $libelleP;

    /**
     * @var float
     *
     * @ORM\Column(name="Prix_P", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixP;

    /**
     * @var integer
     *
     * @ORM\Column(name="Quantite_P", type="integer", nullable=false)
     */
    private $quantiteP;

    /**
     * @var string
     *
     * @ORM\Column(name="Adresse", type="string", length=255, nullable=true)
     */
    private $adresse = '\'vide\'';

    /**
     * @var string
     *
     * @ORM\Column(name="Ville", type="string", length=255, nullable=true)
     */
    private $ville = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="Telephone", type="integer", nullable=true)
     */
    private $telephone = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="ID_Commande", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCommande;

    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     */
    private $id = '1';

    /**
     * @return string
     */
    public function getLibelleP(): string
    {
        return $this->libelleP;
    }

    /**
     * @param string $libelleP
     */
    public function setLibelleP(string $libelleP)
    {
        $this->libelleP = $libelleP;
    }

    /**
     * @return float
     */
    public function getPrixP(): float
    {
        return $this->prixP;
    }

    /**
     * @param float $prixP
     */
    public function setPrixP(float $prixP)
    {
        $this->prixP = $prixP;
    }

    /**
     * @return int
     */
    public function getQuantiteP(): int
    {
        return $this->quantiteP;
    }

    /**
     * @param int $quantiteP
     */
    public function setQuantiteP(int $quantiteP)
    {
        $this->quantiteP = $quantiteP;
    }

    /**
     * @return string
     */
    public function getAdresse(): string
    {
        return $this->adresse;
    }

    /**
     * @param string $adresse
     */
    public function setAdresse(string $adresse)
    {
        $this->adresse = $adresse;
    }

    /**
     * @return string
     */
    public function getVille(): string
    {
        return $this->ville;
    }

    /**
     * @param string $ville
     */
    public function setVille(string $ville)
    {
        $this->ville = $ville;
    }

    /**
     * @return int
     */
    public function getTelephone(): int
    {
        return $this->telephone;
    }

    /**
     * @param int $telephone
     */
    public function setTelephone(int $telephone)
    {
        $this->telephone = $telephone;
    }

    /**
     * @return int
     */
    public function getIdCommande(): int
    {
        return $this->idCommande;
    }

    /**
     * @param int $idCommande
     */
    public function setIdCommande(int $idCommande)
    {
        $this->idCommande = $idCommande;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id)
    {
        $this->id = $id;
    }


}

