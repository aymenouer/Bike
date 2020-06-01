<?php

namespace PieceBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * piece
 *
 * @ORM\Table(name="piece", indexes={@ORM\Index(name="FK_ID_p", columns={"ID_P"})})
 * @ORM\Entity(repositoryClass="PieceBundle\Repository\pieceRepository")
 */
class piece
{
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_Pi", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPi;
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
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255)
     */
    private $etat;

    /**
     * @return int
     */
    public function getIdPi(): int
    {
        return $this->idPi;
    }

    /**
     * @param int $idPi
     */
    public function setIdPi(int $idPi)
    {
        $this->idPi = $idPi;
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
     * @return string
     */
    public function getEtat(): string
    {
        return $this->etat;
    }

    /**
     * @param string $etat
     */
    public function setEtat(string $etat)
    {
        $this->etat = $etat;
    }


}

