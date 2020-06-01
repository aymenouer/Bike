<?php

namespace MobileapiBundle\Controller;

use EvenementBundle\Entity\Evenement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Validator\Constraints\DateTime;

class EvenementMobileController extends Controller
{
    public function AllEventsAction()
    {
        $evenements = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Evenement")->findAll();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($evenements);
        return new JsonResponse($formated);
    }

    public function FindEventByIdAction( $id)
    {
        $evenement = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Evenement")->find($id);
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($evenement);
        return new JsonResponse($formated);
    }

    public function updateEventAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $evenement = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Evenement")->find($request->get('id'));
        $evenement->setTitre($request->get('titre'));
        $evenement->setDiscription($request->get('discription'));
        $evenement->setLieu($request->get('lieu'));
        $evenement->setNbrplace((int)$request->get('nbrplace'));
        $evenement->setIamge($request->get('image'));
        $evenement->setDated(new \DateTime((String)$request->get('dated')));
        $evenement->setDateF(new \DateTime((String)$request->get('datef')));
        $evenement->setNbrparticipant($request->get('nbrparticipant'));


        $em->persist($evenement);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }



    public function newEventAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $evenement = new Evenement();
        $evenement->setTitre($request->get('titre'));
        $evenement->setDiscription($request->get('discription'));
        $evenement->setLieu($request->get('lieu'));
        $evenement->setNbrplace((int)$request->get('nbrplace'));
        $evenement->setIamge($request->get('image'));
        $evenement->setRate(0);
        $evenement->setVote(0);
        $evenement->setDated(new \DateTime((String)$request->get('dated')));
        $evenement->setDateF(new \DateTime((String)$request->get('dateF')));
        $evenement->setNbrparticipant((int)$request->get('nbrparticipant'));
        $em->persist($evenement);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($evenement);
        return new JsonResponse($formated);
    }

    public function deleteEventAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $evenement = $this->getDoctrine()->getManager()->getRepository("EvenementBundle:Evenement")->find(array('id'=>$id));
        $reservation = $em->getRepository("EvenementBundle:Reservation")->findBy(array('idevenement' => $id));

        $em->remove($evenement);
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
                            'titre' => $evenement->getTitre(),
                            'nom' => $resev->getIdUser()->getUsername(),
                            'dated' => $evenement->getDated(),
                            'datef' => $evenement->getDateF(),


                        )


                    ),
                    'text/html'
                );
            $this->get('mailer')->send($message);
        }

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }


}
