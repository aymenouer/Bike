<?php

namespace ReclamationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints as CaptchaAssert;
/**
 * reclamation
 *
 * @ORM\Table(name="reclamation", indexes={@ORM\Index(name="FK_rec", columns={"ID_U"})})
 * @ORM\Entity(repositoryClass="ReclamationBundle\Repository\reclamationRepository")
 */
class reclamation
{
    /**
     * @var integer
     *
     * @ORM\Column(name="ID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="ID_U", type="integer", nullable=false)
     */
    private $idU;

    /**
     * @var string
     * @Assert\NotBlank
     * @Assert\Length(min=10)
     * @ORM\Column(name="Contenu", type="text", length=65535, nullable=false)
     */
    private $contenu;

    /**
     * @var string
     *
     * @ORM\Column(name="Type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=false)
     */
    private $etat;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;
    /**
     * @var string
     *
     * @ORM\Column(name="traite", type="string", length=255, nullable=false)
     */
    private $traite;

    /**
     * @return string
     */
    public function getTraite()
    {
        return $this->traite;
    }

    /**
     * @param string $traite
     */
    public function setTraite(string $traite)
    {
        $this->traite = $traite;
    }


    /**
     * @return int
     */
    public function getId()
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
    public function getContenu()
    {
        return $this->contenu;
    }

    /**
     * @param string $contenu
     */
    public function setContenu(string $contenu)
    {
        $this->contenu = $contenu;
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
    public function setType(string $type)
    {
        $this->type = $type;
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
    public function setImage( $image)
    {
        $this->image = $image;
    }


}

