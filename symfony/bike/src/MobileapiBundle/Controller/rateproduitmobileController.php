<?php

namespace MobileapiBundle\Controller;

use RatingBundle\Entity\rating;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class rateproduitmobileController extends Controller
{
    public function ADD_RatingAction(Request $request)
    {
        $rate = new rating();


        $rate->setIdU($request->get('ID_U'));
        $rate->setIdP((int)$request->get('idP'));
        $rate->setVote((int)$request->get('rate'));

        $em = $this->getDoctrine()->getManager();
        $em->persist($rate);
        $em->flush();


        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($rate);
        return new JsonResponse($formated);





    }
    public function Verif_RatingAction(Request $request)
    {
        $db = $this->getDoctrine()->getManager();

        $verif_rating = $db->getRepository('RatingBundle:rating')->findAll();





        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($verif_rating);
        return new JsonResponse($formated);





    }
}
