<?php

namespace RatingBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * rating
 *
 * @ORM\Table(name="rating", indexes={@ORM\Index(name="FK_id_u", columns={"ID_U"}),@ORM\Index(name="FK_ID_p", columns={"ID_P"})})
 * @ORM\Entity(repositoryClass="RatingBundle\Repository\ratingRepository")
 */
class rating
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_R", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idR;
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_U", type="integer", nullable=false)
     */
    private $idU;
    /**
     * @var integer
     *
     * @ORM\Column(name="ID_P", type="integer", nullable=false)
     */
    private $idP;
    /**
     * @var integer
     *
     * @ORM\Column(name="vote", type="integer", nullable=false)
     */
    private $vote;

    /**
     * @return int
     */
    public function getIdR()
    {
        return $this->idR;
    }

    /**
     * @param int $idR
     */
    public function setIdR(int $idR)
    {
        $this->idR = $idR;
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
    public function getVote()
    {
        return $this->vote;
    }

    /**
     * @param int $vote
     */
    public function setVote(int $vote)
    {
        $this->vote = $vote;
    }



}

