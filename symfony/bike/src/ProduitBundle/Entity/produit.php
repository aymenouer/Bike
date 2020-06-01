<?php

namespace ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * produit
 *
 * @ORM\Table(name="produit", indexes={@ORM\Index(name="FK_Lib_S", columns={"Lib_S"}), @ORM\Index(name="FK_cat", columns={"Lib_C"})})
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\produitRepository")
 */
class produit
{
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_P", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idP;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=3)
     * @ORM\Column(name="Libelle", type="string", length=255, nullable=false)
     */
    private $libelle;

    /**
     *
     * @ORM\Column(name="Image", type="string", length=255, nullable=false)
     */
    private $image;

    /**
     * @var string
     *  @Assert\NotBlank
     * @Assert\Length(min=5)
     * @ORM\Column(name="Description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var float
     *
     * @Assert\Range(
     *      min = 1,
     *      max = 999,
     *      minMessage = "Nombre petit",
     *      maxMessage = "Nombre grand"
     * )
     * @ORM\Column(name="Prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="Lib_C", type="string", length=255, nullable=false)
     */
    private $libC;

    /**
     * @var string
     *
     * @ORM\Column(name="Lib_S", type="string", length=255, nullable=false)
     */
    private $libS;

    /**
     * @return int
     */
    public function getIdP()
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
    public function getLibelle()
    {
        return $this->libelle;
    }

    /**
     * @param string $libelle
     */
    public function setLibelle(string $libelle)
    {
        $this->libelle = $libelle;
    }

    /**
     * @return mixed
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param mixed $image
     */
    public function setImage( $image)
    {
        $this->image = $image;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description)
    {
        $this->description = $description;
    }

    /**
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @param float $prix
     */
    public function setPrix(float $prix)
    {
        $this->prix = $prix;
    }

    /**
     * @return string
     */
    public function getLibC()
    {
        return $this->libC;
    }

    /**
     * @param string $libC
     */
    public function setLibC(string $libC)
    {
        $this->libC = $libC;
    }

    /**
     * @return string
     */
    public function getLibS()
    {
        return $this->libS;
    }

    /**
     * @param string $libS
     */
    public function setLibS(string $libS)
    {
        $this->libS = $libS;
    }




}

