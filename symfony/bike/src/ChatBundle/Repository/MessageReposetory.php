<?php
/**
 * Created by PhpStorm.
 * User: Cascada
 * Date: 4/10/2019
 * Time: 10:38 PM
 */

namespace ChatBundle\Repository;

use Doctrine\DBAL\DBALException;
use Doctrine\ORM\EntityRepository;

class MessageReposetory extends EntityRepository
{
    public function getMessages($user1,$user2){
        return $this->getEntityManager()
            ->createQuery(
                'SELECT m FROM AppBundle:message m WHERE ((m.sender = :val1) OR (m.sender = :val2))'
            )
            ->setParameter('val1', $user1)
            ->setParameter('val2', $user2)
            ->getResult();
    }




}