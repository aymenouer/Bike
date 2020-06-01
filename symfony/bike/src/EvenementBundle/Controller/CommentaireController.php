<?php


namespace EvenementBundle\Controller;


use EvenementBundle\Entity\Commentaire;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CommentaireController extends  Controller
{

    public function count($id)
    {
        $count = 0;
        $em = $this->getDoctrine()->getManager();
        $commentaire = $em->getRepository("EvenementBundle:Commentaire")->findBy(array('idevenement'=>$id));
        foreach ($commentaire as $e){
            $count = $count + 1;
        }

        return $count;

    }
    function filterwords($text){
        $filterWords = array('fuck', 'nike', 'pute','bitch');
        $filterCount = sizeof($filterWords);
        for ($i = 0; $i < $filterCount; $i++) {
            $text = preg_replace_callback('/\b' . $filterWords[$i] . '\b/i', function($matches){return str_repeat('*', strlen($matches[0]));}, $text);
        }
        return $text;
    }

    public function AfficheAddCommentAction(Request $request , $id)
    {

        $count = $this->count($id);
        $em = $this->getDoctrine()->getManager();

        $event = $em->getRepository("EvenementBundle:Evenement")->find($id);
        $vote = $em->getRepository("EvenementBundle:Vote")->findAll();
        $resev  = $em->getRepository('EvenementBundle:Reservation')->findBy(array('idUser'=>$this->getUser()->getId(),
            'idevenement'=>$id));
        $Comm = $em->getRepository("EvenementBundle:Commentaire")->findBy(array("idevenement" => $event));
        $user = $this->getUser();
        $comment = new Commentaire();
        $form = $this->createForm('EvenementBundle\Form\CommentaireType', $comment);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $comment->setCommentaire($this->filterwords($comment->getCommentaire()));
            $comment->setIdUser($user);
            $comment->setIdevenement($event);
            $em->persist($comment);
            $em->flush();
            return $this->redirectToRoute('Comment', ['id' => $id]);
        }
        return $this->render('@Evenement/Commentaire/Comment.html.twig', array(
            'form' => $form->createView(),
            'comment' => $Comm,
            'event' => $event,
            'reserv'=>$resev,
            'c'=>$count ,
            'user'=>$user,
            'vote'=>$vote


        ));

    }


    public function editCommentaireAction(Request $request, $idc,$ide)
    {
        $em = $this->getDoctrine()->getManager();
        $count = $this->count($ide);
        $comentaire = $em->getRepository('EvenementBundle:Commentaire')->find($idc);
        $editForm = $this->createForm('EvenementBundle\Form\CommentaireType', $comentaire);
        $vote = $em->getRepository("EvenementBundle:Vote")->findAll();
        $event = $em->getRepository("EvenementBundle:Evenement")->find(array("id" => $ide));
        $Comm = $em->getRepository("EvenementBundle:Commentaire")->findBy(array("idevenement" => $idc));
        $resev  = $em->getRepository('EvenementBundle:Reservation')->findBy(array('idUser'=>$this->getUser()->getId(),
            'idevenement'=>$ide));

        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($comentaire);
            $em->flush();

            return $this->redirectToRoute('Comment', ['id' => $ide]);
        }

        return $this->render('@Evenement/Commentaire/Comment.html.twig', array(
            'comm' => $comentaire,
            'event' => $event,
            'reserv'=>$resev,
            'comment' => $Comm,
            'c' => $count,
            'vote' =>$vote,
            'form' => $editForm->createView(),
        ));
    }

    public function deleteCommentAction(Request $request,$ide,$idc)
    {

        $em = $this->getDoctrine()->getManager();

        $Evenement = $em->getRepository("EvenementBundle:Commentaire")->find($idc);
        $em->remove($Evenement);
        $em->flush();

        return $this->redirectToRoute('Comment', ['id' => $ide]);
    }
}