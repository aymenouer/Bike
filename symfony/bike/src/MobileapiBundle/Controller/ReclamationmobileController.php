<?php

namespace MobileapiBundle\Controller;

use ReclamationBundle\Entity\reclamation;
use ReclamationBundle\Form\reclamationType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class ReclamationmobileController extends Controller
{
    public function ListReclamationUserAction(Request $request)
    {
        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('ID_U'));

        $listreclamation = $this->getDoctrine()->getRepository(reclamation::class)->return_reclamation_user($user->getId());
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($listreclamation);
        return new JsonResponse($formated);
    }
    function filterwords($text){
        $filterWords = array('fuck', 'nike', 'pute','bitch');
        $filterCount = sizeof($filterWords);
        for ($i = 0; $i < $filterCount; $i++) {
            $text = preg_replace_callback('/\b' . $filterWords[$i] . '\b/i', function($matches){return str_repeat('*', strlen($matches[0]));}, $text);
        }
        return $text;
    }
    public function ListReclamationUsersAction()
    {
        $listreclamation = $this->getDoctrine()->getRepository('ReclamationBundle:reclamation')->findAll();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($listreclamation);
        return new JsonResponse($formated);
    }

    public function AddReclamationUserAction(Request $request)
    {
        $reclamation = new reclamation();
        $reclamation->setImage($request->get('image'));


        $reclamation->setTraite("Non Traite");
        if ($request->get('etat') == "Bien")
        {

            $reclamation->setTraite("Traite");
        }

            $reclamation->setContenu($this->filterwords($request->get('contenu')));
        $reclamation->setType($request->get('Type'));

            $reclamation->setEtat($request->get('etat'));
            $reclamation->setIdU($request->get('ID_U'));
            $em=$this->getDoctrine()->getManager();
            $em->persist($reclamation);


            $em->flush();
        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('ID_U'));
        if( $request->get('etat')=="mauvais")
            { $message = \Swift_Message::newInstance()
                ->setSubject('BIKE: Réponse suite à la réclamation')
                ->setFrom('bike.pidev@gmail.com')
                ->setTo($user->getEmail())
                ->setBody(
                    $this->renderView(
                    // app/Resources/views/Emails/registration.html.twig
                        'Emails/mail_reclamation_mauvais.html.twig',
                        array(

                            'nom' =>  $user->getLastName() . " ". $user->getFirstName(),



                        )


                    ),
                    'text/html'
                );
                $this->get('mailer')->send($message);
            }
            else
            {
                $message = \Swift_Message::newInstance()
                    ->setSubject('BIKE: Réponse suite à la réclamation')
                    ->setFrom('bike.pidev@gmail.com')
                    ->setTo($user->getEmail())
                    ->setBody(
                        $this->renderView(
                        // app/Resources/views/Emails/registration.html.twig
                            'Emails/mail_reclamation_Bien.html.twig',
                            array(

                                'nom' =>  $user->getLastName() . " ". $user->getFirstName(),



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

    public function DeleteReclamationAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(reclamation::class)->find((int)$request->get('id'));
        $em->remove($modele);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function TraiteReclamationAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $reclamation=$em->getRepository(reclamation::class)->find((int)$request->get('id'));
        $reclamation->setTraite("Traite");
        $em->persist($reclamation);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

}
