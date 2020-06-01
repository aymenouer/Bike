<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\Sponsor;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class SponsorController extends Controller
{
    public function AjoutSponsorAction( \Symfony\Component\HttpFoundation\Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $sponsor = new Sponsor();
    
        $form = $this->createForm('EvenementBundle\Form\SponsorType', $sponsor);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

          
            $em->persist($sponsor);
            $em->flush();
            return $this->redirectToRoute('AffichierSponsor');
        }
        return $this->render('@Evenement/Sponsor/AjouterSponsor.html.twig', array(
            'form' => $form->createView(),
        ));
    }


    public function AfficheSponsorAction()
    {
        $m = $this->getDoctrine()->getManager();
        $sponsor = $m->getRepository("EvenementBundle:Sponsor")->findAll();
        return $this->render('@Evenement/Sponsor/AfficheSponsor.html.twig', array(
            'event' => $sponsor,
        ));
    }

    public function deleteSponsorAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $sponsors = $em->getRepository("EvenementBundle:Sponsor")->find($id);
        $em->remove($sponsors);
        $em->flush();
        return $this->redirectToRoute('AffichierSponsor');

    }

    public function editSponsorAction(\Symfony\Component\HttpFoundation\Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $sponsor = $em->getRepository('EvenementBundle:Sponsor')->find($id);

        $editForm = $this->createForm('EvenementBundle\Form\SponsorType', $sponsor);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($sponsor);
            $em->flush();

            return $this->redirectToRoute('AffichierSponsor');
        }

        return $this->render('@Evenement/Sponsor/editSponsor.html.twig', array(
            'event' => $sponsor,
            'form' => $editForm->createView(),
        ));
    }

}
