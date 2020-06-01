<?php

namespace SiteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * site
 *
 * @ORM\Table(name="site")
 * @ORM\Entity(repositoryClass="SiteBundle\Repository\siteRepository")
 */
class site
{
    /**
     * @var integer
     *
     * @ORM\Column(name="capacite", type="integer", nullable=false)
     */
    private $capacite;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=3)
     * @ORM\Column(name="lieu", type="string", length=255, nullable=false)
     */
    private $lieu;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=2)
     * @ORM\Column(name="Lib_S", type="string", length=255, nullable=false)
     */
    private $libS;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_S", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idS;

    /**
     * @return int
     */
    public function getCapacite()
    {
        return $this->capacite;
    }

    /**
     * @param int $capacite
     */
    public function setCapacite(int $capacite)
    {
        $this->capacite = $capacite;
    }

    /**
     * @return string
     */
    public function getLieu()
    {
        return $this->lieu;
    }

    /**
     * @param string $lieu
     */
    public function setLieu(string $lieu)
    {
        $this->lieu = $lieu;
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

    /**
     * @return int
     */
    public function getIdS()
    {
        return $this->idS;
    }

    /**
     * @param int $idS
     */
    public function setIdS(int $idS)
    {
        $this->idS = $idS;
    }

}

