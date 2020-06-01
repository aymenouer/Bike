<?php

namespace MobileapiBundle\Controller;

use EvenementBundle\Entity\Commentaire;
use EvenementBundle\Entity\Reservation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class ParticiperMobileController extends Controller
{
    public function ParticiperAction(Request $request ,$ide,$idu)
    {

        $em = $this->getDoctrine()->getManager();
        $participe = new Reservation();
        $event = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Evenement")->find($ide);
        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($idu);
        $event->setNbrparticipant($event->getNbrparticipant()+1);
        $participe->setIdUser($user);
        $participe->setIdevenement($event);
        $em->persist($event);
        $em->persist($participe);
        $em->flush();


        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($participe);
        return new JsonResponse($formated);
    }
    public function FindParticiperAction($ide,$idu)
    {
        $participe = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Reservation")->findBy(array('idevenement'=>$ide,'idUser'=>$idu));
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($participe);
        return new JsonResponse($formated);
    }

    public function FindParticiperAllAction()
    {
        $participe = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Reservation")->findAll();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($participe);
        return new JsonResponse($formated);
    }

    public function deletParticiperAction(Request $request,$idu,$ide)
    {

        $em=$this->getDoctrine()->getManager();
        $participe = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Reservation")->findOneBy(array('idevenement'=>$ide,'idUser'=>$idu));
        $event = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Evenement")->find($participe->getIdevenement()->getId());
        $event->setNbrparticipant($event->getNbrparticipant()-1);
        $em->persist($event);
        $em->remove($participe);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }
}
