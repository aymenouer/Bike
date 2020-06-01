<?php

namespace AbonnementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use ProduitBundle\Entity\produit;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * abonnement
 *
 * @ORM\Table(name="abonnement", indexes={@ORM\Index(name="FK_ID_p", columns={"ID_P"})})
 * @ORM\Entity(repositoryClass="AbonnementBundle\Repository\abonnementRepository")
 */
class abonnement
{
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_A", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idA;

    /**
     * @var integer
     *
     * @ORM\Column(name="ID_P", type="integer", nullable=false)
     */
    protected $idP;

    /**
     * @var integer
     * @Assert\NotBlank
     * @Assert\GreaterThan(0)
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;


    /**
     * @return int
     */
    public function getIdA()
    {
        return $this->idA;
    }

    /**
     * @param int $idA
     */
    public function setIdA(int $idA)
    {
        $this->idA = $idA;
    }

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
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * @param int $quantite
     */
    public function setQuantite(int $quantite)
    {
        $this->quantite = $quantite;
    }



}

