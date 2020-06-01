<?php

namespace MaitenanceBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * maitenance
 *
 * @ORM\Table(name="maitenance", indexes={@ORM\Index(name="FK_id_u", columns={"ID_U"})})
 * @ORM\Entity(repositoryClass="MaitenanceBundle\Repository\maitenanceRepository")
 */
class maitenance
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_M", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $IDM;
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_U", type="integer")
     */
    private $idU;
    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=10)
     * @ORM\Column(name="Problem", type="text", length=65535, nullable=false)
     */
    private $Problem;
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
     * @ORM\Column(name="etat", type="string", length=255, nullable=false)
     */
    private $etat;

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType(string $type)
    {
        $this->type = $type;
    }
    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=false)
     */
    private $type;
    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DATE_D", type="datetime", nullable=false, columnDefinition="DATETIME on update CURRENT_TIMESTAMP")
     */
    private $dateD;
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DATE_F", type="datetime")
     */
    private $dateF;

    /**
     * @return int
     */
    public function getIDM(): int
    {
        return $this->IDM;
    }

    /**
     * @param int $IDM
     */
    public function setIDM(int $IDM)
    {
        $this->IDM = $IDM;
    }





    /**
     * @return int
     */
    public function getIdU()
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
     * @return string
     */
    public function getProblem()
    {
        return $this->Problem;
    }

    /**
     * @param string $Problem
     */
    public function setProblem(string $Problem)
    {
        $this->Problem = $Problem;
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
    public function getEtat()
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
    public function setImage(string $image)
    {
        $this->image = $image;
    }

    /**
     * @return \DateTime
     */
    public function getDateD()
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
    public function getDateF()
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

}

