<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\Evenement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\BrowserKit\Request;
use Symfony\Component\HttpFoundation\JsonResponse;

class EvenementController extends Controller
{
    public function AjoutEvenementAction( \Symfony\Component\HttpFoundation\Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $evenment = new Evenement();
        $evenment->setNbrparticipant(0);
        $evenment->setRate(0);
        $evenment->setVote(0);
        $form = $this->createForm('EvenementBundle\Form\EvenementType', $evenment);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $evenment->setIamge("3.jpg");
            $evenment->getUploadFile();
            $em->persist($evenment);
            $em->flush();
            return $this->redirectToRoute('evenement_Affiche');
        }
        return $this->render('@Evenement/Evenement/AjouterEvenement.html.twig', array(
            'form' => $form->createView(),
        ));
    }


    public function AfficheEvenementAction()
    {
        $m = $this->getDoctrine()->getManager();
        $Evenement = $m->getRepository("EvenementBundle:Evenement")->findAll();
        return $this->render('@Evenement/Evenement/AfficheEvenement.html.twig', array(
            'event' => $Evenement,
        ));
    }

    public function deleteEventAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $Evenements = $em->getRepository("EvenementBundle:Evenement")->find($id);
        $reservation = $em->getRepository("EvenementBundle:Reservation")->findBy(array('idevenement' => $id));
        $em->remove($Evenements);
        $em->flush();
        foreach ($reservation as $resev) {
            $message = \Swift_Message::newInstance()
                ->setSubject('Evenemet Annuler')
                ->setFrom('bike.pidev@gmail.com')
                ->setTo($resev->getIdUser()->getEmail())
                ->setBody(
                    $this->renderView(
                    // app/Resources/views/Emails/registration.html.twig
                        'Emails/mail.html.twig',
                        array(
                            'titre' => $Evenements->getTitre(),
                            'nom' => $resev->getIdUser()->getUsername(),
                            'dated' => $Evenements->getDated(),
                            'datef' => $Evenements->getDateF(),


                        )


                    ),
                    'text/html'
                );
            $this->get('mailer')->send($message);
        }
        return $this->redirectToRoute('evenement_Affiche');

    }

    public function editEvenementAction(\Symfony\Component\HttpFoundation\Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $evenement = $em->getRepository('EvenementBundle:Evenement')->find($id);

        $editForm = $this->createForm('EvenementBundle\Form\EvenementType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($evenement);
            $em->flush();

            return $this->redirectToRoute('evenement_Affiche');
        }

        return $this->render('@Evenement/Evenement/editEvenement.html.twig', array(
            'event' => $evenement,
            'form' => $editForm->createView(),
        ));
    }

    public function AfficheEvenementFrontAction(\Symfony\Component\HttpFoundation\Request $request)
    {
        $m = $this->getDoctrine()->getManager();
        $Evenement = $m->getRepository("EvenementBundle:Evenement")->findAll();

        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate(
            $Evenement, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            $request->query->getInt('limit', 2)
        );/*page number*/
        return $this->render('@Evenement/Evenement/FrontEvent.html.twig', array(
            'event' => $result,
        ));
    }

    public function ListeReservationClientAction()
    {
        $m = $this->getDoctrine()->getManager();
        $reservation = $m->getRepository("EvenementBundle:Evenement");
        $events= $reservation->createQueryBuilder('c')
            ->getQuery()
            ->getArrayResult();
        $ev = [];
        $tab = [];
        foreach ($events as $event) {

            $ev['title'] = $event['titre'];
            $ev['start'] = $event['dated']->format('Y-m-d H:i:s');
            $tab[] = $ev;
        }
        return $response = new JsonResponse($tab);

    }

    public function CallendreAction() {
        return $this->render('@Evenement/Evenement/OurCalendre.html.twig');
    }


}
