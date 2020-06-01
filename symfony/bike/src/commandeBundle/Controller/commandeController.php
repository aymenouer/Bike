<?php

namespace commandeBundle\Controller;

use commandeBundle\Entity\commande;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class commandeController extends Controller
{
    public function allAction()
    {
        $em=$this->getDoctrine()->getManager();

        //$fs=$em->getRepository(clubs::class)->findQB();
        $fs=$em->getRepository(commande::class)->findDQL();

        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($fs);

        return new JsonResponse($formated);
    }
    public function afficheAction()
    {
        $em=$this->getDoctrine()->getManager();

        //$fs=$em->getRepository(clubs::class)->findQB();
        $fs=$em->getRepository(commande::class)->aDQL();

        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($fs);

        return new JsonResponse($formated);
    }
    public function findAction($id)
    {
        $commande = $this->getDoctrine()->getManager()
            ->getRepository('commandeBundle:commande')
            ->find($id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($commande);
        return new JsonResponse($formatted);
    }

    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $commande = new commande();
        $commande->setLabelle($request->get('labelle'));
        $commande->setAdresse($request->get('adresse'));
        $commande->setVille($request->get('ville'));
        $commande->setPrix($request->get('prix'));
        $commande->setNum($request->get('num'));
        $commande->setQuantite($request->get('quantite'));

        $em->persist($commande);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($commande);
        return new JsonResponse($formatted);
    }

    public function updateAction (Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $commande = $this->getDoctrine()->getManager()->getRepository("commandeBundle:commande")->find($request->get('id'));

        $commande->setAdresse($request->get('adresse'));
        $commande->setVille($request->get('ville'));

        $commande->setNum($request->get('num'));



        $em->persist($commande);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($commande);
        return new JsonResponse($formated);
    }
    public function deleteAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $commande = $this->getDoctrine()->getManager()->getRepository("commandeBundle:commande")->find($request->get('id'));

        $em->remove($commande);

        $em->flush();

        //$userManager = $this->container->get('fos_user.user_manager');
        // $user->setEnabled(false);



        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($commande);
        return new JsonResponse($formated);
    }
    public function montantAction()
    {
        $em=$this->getDoctrine()->getManager();

        //$fs=$em->getRepository(clubs::class)->findQB();
        $fs=$em->getRepository(commande::class)->montant();

        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($fs);
        return new JsonResponse($formated);
    }

}
