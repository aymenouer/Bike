<?php

namespace MaitenanceBundle\Controller;


use MaitenanceBundle\Entity\maitenance;
use MaitenanceBundle\Form\maitenanceType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use UserBundle\Entity\User;

class maitenanceController extends Controller
{

    function filterwords($text){
        $filterWords = array('fuck', 'nike', 'pute','bitch');
        $filterCount = sizeof($filterWords);
        for ($i = 0; $i < $filterCount; $i++) {
            $text = preg_replace_callback('/\b' . $filterWords[$i] . '\b/i', function($matches){return str_repeat('*', strlen($matches[0]));}, $text);
        }
        return $text;
    }
    public function Add_product_MaitenanceAction(Request $request)
    {
        $maintenance = new maitenance();
        $form=$this->createForm(maitenanceType::class,$maintenance);
        $form=$form->handleRequest($request);


        if($form->isValid())
        {

            $file=$form->get('image')->getData();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('image_directory')."/Maitenance/",$fileName);
            $maintenance->setImage($fileName);
$maintenance->setProblem($this->filterwords($maintenance->getProblem()));
            $maintenance->setIdU($this->container->get('security.token_storage')->getToken()->getUser()->getId());
            $maintenance->setPrix(0);
            $maintenance->setEtat("non réparé");


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

            return $this->redirectToRoute('_list__product__maitenance__user');

        }


        return $this->render('MaitenanceBundle:maitenance:add.product.maitenance.html.twig', array(
            'form'=>$form->createView()
        ));
    }

    public function List_Product_Maitenance_UserAction()
    {
        $db = $this->getDoctrine()->getManager();

        $user=$this->container->get('security.token_storage')->getToken()->getUser();


        $listmaintenance = $this->getDoctrine()->getRepository(maitenance::class)->return_maitenance_user($user->getId());




        return $this->render('@Maitenance/maitenance/list_product_maitenance_user.html.twig', array(
         'listmaintenance'=>$listmaintenance
        ));
    }

    public function List_Product_Maitenance_adminAction()
    {

        $listmaintenance=$this->getDoctrine()->getRepository('MaitenanceBundle:maitenance')->findAll();

        return $this->render('@Maitenance/maitenance/list_product_maitenance.html.twig', array(
            'list'=>$listmaintenance
        ));
    }

    public function Update_Product_MaitenanceAction(Request $request,$IDM)
    {
        $em=$this->getDoctrine()->getManager();
        $maitenance=$em->getRepository(maitenance::class)->find($IDM);

        $maitenance->setPrix((float)$request->get('prix'));
        $maitenance->setEtat("accept");
        $em->persist($maitenance);
        $em->flush();
        return $this->redirectToRoute('_list__product__maitenance_admin');
    }
    public function delete_Product_MaitenanceAction($IDM)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(maitenance::class)->find($IDM);

        $user=$em->getRepository(User::class)->find($modele->getIdU());


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
        return $this->redirectToRoute('_list__product__maitenance_admin');
    }

    public function Update_Product_Maitenance_trait_userAction($IDM)
    {
        $em=$this->getDoctrine()->getManager();
        $maitenance=$em->getRepository(maitenance::class)->find($IDM);


        $maitenance->setEtat("en cours de reparation");
        $em->persist($maitenance);
        $em->flush();
        return $this->redirectToRoute('_list__product__maitenance__user');
    }
    public function delete_Product_Maitenance_userAction($IDM)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(maitenance::class)->find($IDM);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('_list__product__maitenance__user');
    }
}
