<?php

namespace AccessoireBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * accessoire
 *
 * @ORM\Table(name="accessoire", indexes={@ORM\Index(name="FK_idproduitss", columns={"ID_P"})})
 * @ORM\Entity(repositoryClass="AccessoireBundle\Repository\accessoireRepository")
 */
class accessoire
{
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_P", type="integer", nullable=false)
     */
    private $idP;

    /**
     * @var integer
     * @Assert\GreaterThan(1)
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var integer
     *
     * @ORM\Column(name="ID_A", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idA;

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
     * @return int
     */
    public function getQuantite(): int
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

    /**
     * @return int
     */
    public function getIdA(): int
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

}

