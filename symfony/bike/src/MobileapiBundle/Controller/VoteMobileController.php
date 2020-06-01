<?php

namespace MobileapiBundle\Controller;

use EvenementBundle\Entity\Vote;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class VoteMobileController extends Controller
{

    public function LikeAction(Request $request,$id,$idu)
    {
        $em = $this->getDoctrine()->getManager();
        $vote = new Vote();
        $comment = $em->getRepository("EvenementBundle:Commentaire")->find(array("id" => $id));
        $user = $em->getRepository('UserBundle:User')->find(array('id' => $idu));
        $votepre = $em->getRepository("EvenementBundle:Vote")->findOneBy(array("idcomment" => $comment->getId(), 'idClient' => $user->getId()));
        if ($votepre != null) {
            $vote->setIdClient($user);
            $vote->setIdcomment($comment);
            $vote->setType(1);
            $em->remove($votepre);
            $em->persist($vote);
            $em->flush();
        } else {
            $vote->setIdClient($user);
            $vote->setIdcomment($comment);
            $vote->setType(1);

            $em->persist($vote);
            $em->flush();

        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($vote);
        return new JsonResponse($formated);

    }

        public function DesLikeAction(Request $request , $id,$idu)
    {

        $em = $this->getDoctrine()->getManager();
        $vote = new Vote();
        $comment = $em->getRepository("EvenementBundle:Commentaire")->find(array("id" => $id));
        $user = $em->getRepository('UserBundle:User')->find(array('id' => $idu));

        $votepre = $em->getRepository("EvenementBundle:Vote")->findOneBy(array("idcomment" => $comment->getId(), 'idClient' => $user->getId()));
        if ($votepre != null) {
            $vote->setIdClient($user);
            $vote->setIdcomment($comment);
            $vote->setType(2);
            $em->remove($votepre);
            $em->persist($vote);
            $em->flush();
        } else{
            $vote->setIdClient($user);
            $vote->setIdcomment($comment);
            $vote->setType(2);

            $em->persist($vote);
            $em->flush();

        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($vote);
        return new JsonResponse($formated);
    }

    public function rateAction(\Symfony\Component\HttpFoundation\Request $request){


        $em = $this->getDoctrine()->getManager();
        $rate =$request->get('rate');
        $idc = $request->get('evenement');
        $Evenement = $em->getRepository("EvenementBundle:Evenement")->find($idc);
        $note = ($Evenement->getRate()*$Evenement->getVote() + $rate)/($Evenement->getVote()+1);
        $Evenement->setVote($Evenement->getVote()+1);
        $Evenement->setRate($note);
        $em->persist($Evenement);
        $em->flush();
        return new Response("Done");
    }


    public function FindVoteAllAction()
    {
        $Vote = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Vote")->findAll();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($Vote);
        return new JsonResponse($formated);
    }




}
