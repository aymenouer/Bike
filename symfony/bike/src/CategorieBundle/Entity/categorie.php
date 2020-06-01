<?php

namespace CategorieBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity(repositoryClass="CategorieBundle\Repository\categorieRepository")
 */
class categorie
{
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_C", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idC;

    /**
     *
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=3)
     * @ORM\Column(name="Lib_C", type="string", length=255, nullable=false)
     */
    private $libC;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=10)
     * @ORM\Column(name="Description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="Type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @return int
     */
    public function getIdC()
    {
        return $this->idC;
    }

    /**
     * @param int $idC
     */
    public function setIdC($idC)
    {
        $this->idC = $idC;
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
    public function setLibC($libC)
    {
        $this->libC = $libC;
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
    public function setDescription($description)
    {
        $this->description = $description;
    }

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
    public function setType($type)
    {
        $this->type = $type;
    }

}

