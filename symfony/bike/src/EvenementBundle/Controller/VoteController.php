<?php

namespace EvenementBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use EvenementBundle\Entity\Vote;

class VoteController extends Controller
{

    public function rateAction(\Symfony\Component\HttpFoundation\Request $request){
        $data = $request->getContent();
        $obj = json_decode($data,true);

        $em = $this->getDoctrine()->getManager();
        $rate =$obj['rate'];
        $idc = $obj['evenement'];
        $Evenement = $em->getRepository("EvenementBundle:Evenement")->find($idc);
        $note = ($Evenement->getRate()*$Evenement->getVote() + $rate)/($Evenement->getVote()+1);
        $Evenement->setVote($Evenement->getVote()+1);
        $Evenement->setRate($note);
        $em->persist($Evenement);
        $em->flush();
        return new Response($Evenement->getRate());
    }


    public function LikeAction(Request $request,$id)
    {
        // $count = $this->count($id);
        $em = $this->getDoctrine()->getManager();
        $vote = new Vote();
        $comment = $em->getRepository("EvenementBundle:Commentaire")->find(array("id" => $id));
        $user = $em->getRepository('UserBundle:User')->find(array('id' => $this->getUser()->getId()));
        $votepre = $em->getRepository("EvenementBundle:Vote")->findOneBy(array("idcomment" => $comment->getId(), 'idClient' => $user->getId()));
        if ($votepre != null) {
            $vote->setIdClient($user);
            $vote->setIdcomment($comment);
            $vote->setType(1);
            $em->remove($votepre);
            $em->persist($vote);
            $em->flush();
        } else{
            $vote->setIdClient($user);
        $vote->setIdcomment($comment);
        $vote->setType(1);

        $em->persist($vote);
        $em->flush();

              }
            return $this->redirectToRoute('Comment', ['id' => $comment->getIdevenement()->getId()]);
        }


    public function DesLikeAction(Request $request , $id)
    {

        $em = $this->getDoctrine()->getManager();
        $vote = new Vote();
        $comment = $em->getRepository("EvenementBundle:Commentaire")->find(array("id" => $id));
        $user  = $em->getRepository('UserBundle:User')->find(array('id'=>$this->getUser()->getId()));

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
        return $this->redirectToRoute('Comment', ['id' => $comment->getIdevenement()->getId()]);

    }


}
