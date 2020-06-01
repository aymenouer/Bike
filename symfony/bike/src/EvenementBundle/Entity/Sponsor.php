<?php

namespace EvenementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;



/**
 * Sponsor
 *
 * @ORM\Table(name="sponsor")
 * @UniqueEntity(fields="nomsponsor", message="Un Sponsor existe déjà avec ce nom.")
 * @ORM\Entity(repositoryClass="EvenementBundle\Repository\SponsorRepository")
 */
class Sponsor
{
    /**
     * @var int
     *
     * @ORM\Column(name="idsponsor", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     * @Assert\Length(
     *      min = 8,
     *      max = 20,
     *      minMessage = "Le numéro doit avoir au moins{{ limit }} chiffres",
     *      maxMessage = "le numéro doit avoir au plus  {{ limit }} chiffres"
     * )
     * @ORM\Column(name="num", type="integer", nullable=false)
     */
    private $num;


    /**
     * @var integer
     *
     * @ORM\Column(name="typesponsor", type="string", length=200, nullable=false)
     */
    private $typesponsor;


    /**
     * @var integer
     *
     * @ORM\Column(name="adresse", type="string", length=255, nullable=false)
     */
    private $adresse;


    /**
     * @var integer
     *
     * @ORM\Column(name="nomsponsor", type="string", length=255, nullable=false)
     */
    private $nomsponsor;


    /**
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="ide", referencedColumnName="id",onDelete="CASCADE",)
     * })
     */
    private $idevenement;

    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return int
     */
    public function getTypesponsor()
    {
        return $this->typesponsor;
    }

    /**
     * @param int $typesponsor
     */
    public function setTypesponsor($typesponsor)
    {
        $this->typesponsor = $typesponsor;
    }

    /**
     * @return int
     */
    public function getAdresse()
    {
        return $this->adresse;
    }

    /**
     * @param int $adresse
     */
    public function setAdresse($adresse)
    {
        $this->adresse = $adresse;
    }

    /**
     * @return int
     */
    public function getNomsponsor()
    {
        return $this->nomsponsor;
    }

    /**
     * @param int $nomsponsor
     */
    public function setNomsponsor($nomsponsor)
    {
        $this->nomsponsor = $nomsponsor;
    }

    /**
     * @return mixed
     */
    public function getIdevenement()
    {
        return $this->idevenement;
    }

    /**
     * @param mixed $idevenement
     */
    public function setIdevenement($idevenement)
    {
        $this->idevenement = $idevenement;
    }

    /**
     * @return int
     */
    public function getNum()
    {
        return $this->num;
    }

    /**
     * @param int $num
     */
    public function setNum($num)
    {
        $this->num = $num;
    }





}

