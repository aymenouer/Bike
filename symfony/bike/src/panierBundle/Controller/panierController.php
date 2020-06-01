<?php

namespace panierBundle\Controller;

use Esprit\ApiBundle\Entity\Task;
use panierBundle\Entity\panier;
use panierBundle\Repository\panierRepository;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class panierController extends Controller
{
    public function allAction()
    {
        $panier = $this->getDoctrine()->getManager()
            ->getRepository('panierBundle:panier')
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($panier);
        return new JsonResponse($formatted);
    }

    public function findAction($id)
    {
        $panier = $this->getDoctrine()->getManager()
            ->getRepository('panierBundle:panier')
            ->find($id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($panier);
        return new JsonResponse($formatted);
    }

    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $panier = new panier();
        $panier->setLabelle($request->get('labelle'));
        $panier->setQuantite($request->get('quantite'));
        $panier->setPrix($request->get('prix'));
        $em->persist($panier);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($panier);
        return new JsonResponse($formatted);
    }

    public function updateAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $p = $this->getDoctrine()->getManager()->getRepository("panierBundle:panier")->find($request->get('id'));
        $p->setLabelle($request->get('labelle'));
        $p->setQuantite($request->get('quantite'));
        $p->setPrix($request->get('prix'));
        $em->persist($p);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }
    public function deleteAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $pa=$this->getDoctrine()->getManager()->getRepository("panierBundle:panier")->find($request->get('id'));
        $em->remove($pa);
        $em->flush();
        //$userManager = $this->container->get('fos_user.user_manager');
       // $user->setEnabled(false);



        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($pa);
        return new JsonResponse($formated);
    }
    public function montantAction()
    {
        $em=$this->getDoctrine()->getManager();

        //$fs=$em->getRepository(clubs::class)->findQB();
        $fs=$em->getRepository(panier::class)->findDQL();

        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($fs);
        return new JsonResponse($formated);
    }

    }




