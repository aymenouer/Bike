<?php

namespace EvenementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Vangrg\ProfanityBundle\Validator\Constraints as ProfanityAssert;

/**
 * Commentaire
 *
 * @ORM\Table(name="comment")
 * @ORM\Entity(repositoryClass="EvenementBundle\Repository\CommentaireRepository")
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="idcomment", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;


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
     * @var string
     *
     * @ORM\Column(name="commentaire", type="string", length=200, nullable=false)
     */
    private $commentaire;

    /**
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idevenement", referencedColumnName="id",onDelete="CASCADE")
     * })
     */
    private $idevenement;

    /**
     * @var \Symfony\Bridge\Doctrine\Security\User\EntityUserProvider
     *
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="iduser", referencedColumnName="id",onDelete="CASCADE")
     * })
     */

    private $id_user;

    /**
     * @return string
     */
    public function getCommentaire()
    {
        return $this->commentaire;
    }

    /**
     * @param string $commentaire
     */
    public function setCommentaire($commentaire)
    {
        $this->commentaire = $commentaire;
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
     * @return mixed
     */
    public function getIdUser()
    {
        return $this->id_user;
    }

    /**
     * @param mixed $id_user
     */
    public function setIdUser($id_user)
    {
        $this->id_user = $id_user;
    }




}

