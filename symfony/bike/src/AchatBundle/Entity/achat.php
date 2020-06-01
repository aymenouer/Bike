<?php

namespace AchatBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * achat
 *
 * @ORM\Table(name="achat", indexes={@ORM\Index(name="FK_id_u", columns={"ID_U"}), @ORM\Index(name="FK_id_a", columns={"ID_A"})})
 * @ORM\Entity(repositoryClass="AchatBundle\Repository\achatRepository")
 */
class achat
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="ID_U", type="integer", nullable=false, unique=true)
     */
    private $idU;

    /**
     * @var integer
     *
     * @ORM\Column(name="ID_A", type="integer", nullable=false)
     */
    private $idA;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DATE_D", type="datetime", nullable=false, columnDefinition="DATETIME on update CURRENT_TIMESTAMP")
     */
    private $dateD;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DATE_F", type="datetime", nullable=false)
     */
    private $dateF;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;
    /**
     *
     * @ORM\Column(name="Image", type="string", length=255, nullable=false)
     */
    private $image;

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
    public function setImage($image)
    {
        $this->image = $image;
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

    /**
     * @return int
     */
    public function getIdU(): int
    {
        return $this->idU;
    }

    /**
     * @param int $idU
     */
    public function setIdU(int $idU)
    {
        $this->idU = $idU;
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

    /**
     * @return \DateTime
     */
    public function getDateD(): \DateTime
    {
        return $this->dateD;
    }

    /**
     * @param \DateTime $dateD
     */
    public function setDateD(\DateTime $dateD)
    {
        $this->dateD = $dateD;
    }

    /**
     * @return \DateTime
     */
    public function getDateF(): \DateTime
    {
        return $this->dateF;
    }

    /**
     * @param \DateTime $dateF
     */
    public function setDateF(\DateTime $dateF)
    {
        $this->dateF = $dateF;
    }

    /**
     * @return float
     */
    public function getPrix(): float
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

}

