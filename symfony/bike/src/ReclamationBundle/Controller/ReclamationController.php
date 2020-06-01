<?php

namespace ReclamationBundle\Controller;

use ReclamationBundle\Entity\reclamation;
use ReclamationBundle\Form\reclamationType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use UserBundle\Entity\User;

class ReclamationController extends Controller
{
    public function DetailsReclamationAction($ID_R)
    {
        $em=$this->getDoctrine()->getManager();
        $reclamation=$em->getRepository(reclamation::class)->find($ID_R);
        $user = $em->getRepository(User::class)->find($reclamation->getIdU());
        return $this->render('@Reclamation/Reclamation/details_reclamation.html.twig',array('user'=>$user,'reclamation'=>$reclamation));
    }
    public function TraiteReclamationAction($ID_R)
    {
        $em=$this->getDoctrine()->getManager();
        $reclamation=$em->getRepository(reclamation::class)->find($ID_R);
        $reclamation->setTraite("Traite");
        $em->persist($reclamation);
        $em->flush();
        return $this->redirectToRoute('redirect');
           }
    public function DeleteReclamationAction($ID_R)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(reclamation::class)->find($ID_R);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('redirect');

    }

}
