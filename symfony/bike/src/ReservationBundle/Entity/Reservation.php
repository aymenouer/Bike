<?php

namespace ReservationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reservation
 *
 * @ORM\Table(name="reservation")
 * @ORM\Entity(repositoryClass="ReservationBundle\Repository\ReservationRepository")
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="idR", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_D", type="date")
     */
    private $dateD;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_F", type="date")
     */
    private $dateF;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float")
     */
    private $prix;

    /**
     * @var int
     *
     * @ORM\Column(name="idV", type="integer")
     */
    private $idV;

    /**
     * @var int
     *
     * @ORM\Column(name="ID_U", type="integer")
     */
    private $ID_U;


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
     * Set dateD
     *
     * @param \DateTime $dateD
     *
     * @return Reservation
     */
    public function setDateD($dateD)
    {
        $this->dateD = $dateD;

        return $this;
    }

    /**
     * Get dateD
     *
     * @return \DateTime
     */
    public function getDateD()
    {
        return $this->dateD;
    }

    /**
     * Set dateF
     *
     * @param \DateTime $dateF
     *
     * @return Reservation
     */
    public function setDateF($dateF)
    {
        $this->dateF = $dateF;

        return $this;
    }

    /**
     * Get dateF
     *
     * @return \DateTime
     */
    public function getDateF()
    {
        return $this->dateF;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return Reservation
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Set idV
     *
     * @param string $idV
     *
     * @return Reservation
     */
    public function setIdV($idV)
    {
        $this->idV = $idV;

        return $this;
    }

    /**
     * Get idV
     *
     * @return string
     */
    public function getIdV()
    {
        return $this->idV;
    }

    /**
     * @return int
     */
    public function getIDU()
    {
        return $this->ID_U;
    }

    /**
     * @param int $ID_U
     */
    public function setIDU($ID_U)
    {
        $this->ID_U = $ID_U;
    }

}

