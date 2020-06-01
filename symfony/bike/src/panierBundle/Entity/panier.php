<?php

namespace panierBundle\Entity;

use Doctrine\ORM\Mapping as ORM;


/**
 * Panier
 *
 * @ORM\Table(name="panier")
 * @ORM\Entity
 */
class Panier
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_panier", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPanier;

    /**
     * @var integer
     *
     * @ORM\Column(name="ID_P", type="integer", nullable=false)
     */
    private $idP;

    /**
     * @var string
     *
     * @ORM\Column(name="Libelle_P", type="string", length=255, nullable=false)
     */
    private $libelleP;

    /**
     * @var integer
     *
     * @ORM\Column(name="Quantite_P", type="integer", nullable=false)
     */
    private $quantiteP;

    /**
     * @var float
     *
     * @ORM\Column(name="Prix_P", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixP;

    /**
     * @return int
     */
    public function getIdPanier(): int
    {
        return $this->idPanier;
    }

    /**
     * @param int $idPanier
     */
    public function setIdPanier(int $idPanier)
    {
        $this->idPanier = $idPanier;
    }

    /**
     * @return int
     */
    public function getIdP(): int
    {
        return $this->idP;
    }

    /**
     * @param int $idP
     */
    public function setIdP(int $idP)
    {
        $this->idP = $idP;
    }

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


}


