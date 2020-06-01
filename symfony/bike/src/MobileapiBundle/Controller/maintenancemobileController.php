<?php

namespace MobileapiBundle\Controller;

use MaitenanceBundle\Entity\maitenance;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use UserBundle\Entity\User;

class maintenancemobileController extends Controller
{
    public function Add_maintenance_userAction(Request $request)
    {
        $maintenance = new maitenance();

        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('ID_U'));



        $maintenance->setImage($request->get('image'));
            $maintenance->setProblem($this->filterwords($request->get('problem')));
            $maintenance->setIdU($user->getId());
            $maintenance->setPrix(0);
            $maintenance->setEtat("non réparé");
        $maintenance->setType($request->get('type'));

            $maintenance->setDateD(new \DateTime());
            $date=new \DateTime();
            if ($maintenance->getType()=="accessoire")
            {
                $datefin=date_add($date, date_interval_create_from_date_string('7 days'));
            }
            else  if ($maintenance->getType()=="Velo")
            {
                $datefin=date_add($date, date_interval_create_from_date_string('14 days'));
            }
            else  if ($maintenance->getType()=="Piece")
            {
                $datefin=date_add($date, date_interval_create_from_date_string('5 days'));
            }
            else
            {
                $datefin=date_add($date, date_interval_create_from_date_string('12 days'));
            }


            $maintenance->setDateF($datefin);

            $em=$this->getDoctrine()->getManager();
            $em->persist($maintenance);


            $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
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
    public function List_MaintenanceUserAction(Request $request)
    {
        $this->getDoctrine()->getRepository(maitenance::class)->mise_a_jour();
        $db = $this->getDoctrine()->getManager();

        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('ID_U'));


        $listmaintenance = $this->getDoctrine()->getRepository(maitenance::class)->findBy(['idU' => $user->getId()]);

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($listmaintenance);
        return new JsonResponse($formated);


    }

    public function ListMaintenanceAdminAction()
    {
        $this->getDoctrine()->getRepository(maitenance::class)->mise_a_jour();
        $listmaintenance = $this->getDoctrine()->getRepository(maitenance::class)->findAll();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($listmaintenance);
        return new JsonResponse($formated);
    }
    public function UpdateMaintenanceAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $maitenance=$em->getRepository(maitenance::class)->find($request->get("id"));
        $maitenance->setEtat("en cours de reparation");
        if  ((int)$request->get("cas")==1)
        {
            $maitenance->setPrix((float)$request->get('prix'));
            $maitenance->setEtat("accept");
        }
{



}


        $em->persist($maitenance);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }
    public function DeleteMaintenanceAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(maitenance::class)->find((int)$request->get("id"));
        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('ID_U'));



        $message = \Swift_Message::newInstance()
            ->setSubject('Bike Regret')
            ->setFrom('faydrahammami@gmail.com')
            ->setTo($user->getEmail())
            ->setBody(
                $this->renderView(
                // app/Resources/views/Emails/registration.html.twig
                    'Emails/mail_maitenance.html.twig',
                    array(

                        'nom' =>  $user->getLastName() . " ". $user->getFirstName(),



                    )


                ),
                'text/html'
            );
        $this->get('mailer')->send($message);

        $em->remove($modele);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }



}
