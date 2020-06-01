<?php

namespace RatingBundle\Controller;

use RatingBundle\Entity\rating;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class ratingcontrollerController extends Controller
{
    public function ADD_RatingAction(Request $request)
    {
echo "hu";


            $rate = new rating();

            $user=$this->container->get('security.token_storage')->getToken()->getUser();
            $rate->setIdU($user->getId());
        $rate->setIdP((int)$request->request->get('idP'));
        $rate->setVote((int)$request->request->get('rate'));

            $em = $this->getDoctrine()->getManager();
            $em->persist($rate);
    $em->flush();


        return new Response('c bon' );




    }




}
