<?php
// src/AppBundle/Entity/User.php

namespace UserBundle\Entity;

use FOS\MessageBundle\Model\ParticipantInterface;
use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity
 * @ORM\Table(name="user")
 */
class User extends BaseUser implements ParticipantInterface
{
    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="First_Name", type="string", length=255, nullable=false)
     */
    private $firstName;

    /**
     * @var integer
     *
     * @ORM\Column(name="User_Number", type="integer", nullable=false)
     */
    private $userNumber;

    /**
     * @var mixed
     *
     * @ORM\Column(name="User_Image", type="string", length=255, nullable=false)
     */
    private $userImage;

    /**
     * @var integer
     *
     * @ORM\Column(name="User_age", type="integer", nullable=false)
     */
    private $userAge;

    /**
     * @return string
     */
    public function getLastName()
    {
        return $this->lastName;
    }

    /**
     * @param string $lastName
     */
    public function setLastName($lastName)
    {
        $this->lastName = $lastName;
    }

    /**
     * @var string
     *
     * @ORM\Column(name="Last_Name", type="string", length=255, nullable=false)
     */
    private $lastName;

    /**
     * @return string
     */
    public function getFirstName()
    {
        return $this->firstName;
    }

    /**
     * @param string $firstName
     */
    public function setFirstName($firstName)
    {
        $this->firstName = $firstName;
    }

    public function __construct()
    {
        parent::__construct();
        // your own logic
    }

    /**
     * @return int
     */
    public function getUserNumber()
    {
        return $this->userNumber;
    }

    /**
     * @param int $userNumber
     */
    public function setUserNumber($userNumber)
    {
        $this->userNumber = $userNumber;
    }

    /**
     * @return mixed
     */
    public function getUserImage()
    {
        return $this->userImage;
    }

    /**
     * @param string $userImage
     */
    public function setUserImage($userImage)
    {
        $this->userImage = $userImage;
    }

    /**
     * @return int
     */
    public function getUserAge()
    {
        return $this->userAge;
    }

    /**
     * @param int $userAge
     */
    public function setUserAge($userAge)
    {
        $this->userAge = $userAge;
    }

}