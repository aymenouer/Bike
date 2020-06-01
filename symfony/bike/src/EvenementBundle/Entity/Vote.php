<?php
namespace EvenementBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
/**
 * Vote
 *
 * @ORM\Table(name="vote2", indexes={@ORM\Index(name="id_client", columns={"id_client"})})
 * @ORM\Entity
 */
class Vote
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
     * @ORM\Column(name="type", type="integer", nullable=false)
     */
    private $type;
    /**
     * @var \EvenementBundle\Entity\Commentaire
     *
     * @ORM\ManyToOne(targetEntity="\EvenementBundle\Entity\Commentaire")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_comm", referencedColumnName="idcomment",onDelete="CASCADE")
     * })
     */
    private $idcomment;
    /**
     * @var \UserBundle\Entity\User
     *
     * @ORM\ManyToOne(targetEntity="\UserBundle\Entity\User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="id",onDelete="CASCADE")
     * })
     */
    private $idClient;
    /**
     * @return int
     */
    public function getType()
    {
        return $this->type;
    }
    /**
     * @param int $type
     */
    public function setType($type)
    {
        $this->type = $type;
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
    public function setId($id)
    {
        $this->id = $id;
    }
    /**
     * @return \EvenementBundle\Entity\Commentaire
     */
    public function getIdcomment()
    {
        return $this->idcomment;
    }
    /**
     * @param \EvenementBundle\Entity\Commentaire $idcomment
     */
    public function setIdcomment($idcomment)
    {
        $this->idcomment = $idcomment;
    }
    /**
     * @return User
     */
    public function getIdClient()
    {
        return $this->idClient;
    }
    /**
     * @param User $idClient
     */
    public function setIdClient($idClient)
    {
        $this->idClient = $idClient;
    }
}

