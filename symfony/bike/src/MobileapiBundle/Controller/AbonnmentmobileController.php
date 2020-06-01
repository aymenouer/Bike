<?php

namespace MobileapiBundle\Controller;

use AbonnementBundle\Entity\abonnement;
use ProduitBundle\Entity\produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class AbonnmentmobileController extends Controller
{
    public function AllAbonnmentsAction()
    {
        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_abonment();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($list);
        return new JsonResponse($formated);
    }

    public function UpdateAbonnmentAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find((int)$request->get('id'));
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());
        $produit->setDescription($request->get('description'));
        $produit->setLibC($request->get('libc'));
        $produit->setLibS($request->get('libs'));
        $produit->setImage($request->get('image'));
        $produit->setPrix((float)$request->get('prix'));
        $produit->setLibelle($request->get('libelle'));

        $abo->setQuantite((int)$request->get('quantite'));
        $em->persist($produit);
        $em->persist($abo);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function DeleteAbonnmentAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find((int)$request->get('id'));
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());


        unlink($this->getParameter('image_directory')."/qrproduit/".$produit->getImage()) ;
        $em->remove($abo);
        $em->remove($produit);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }
    public function StatAction()
    {
        $statistique=$this->getDoctrine()->getRepository(abonnement::class)->statistique_abo();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($statistique);
        return new JsonResponse($formated);
    }

    public function newAbonnmentAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $produit = new produit();
        $produit->setDescription($request->get('description'));
    $produit->setLibC($request->get('libc'));
    $produit->setLibS($request->get('libs'));
    $produit->setImage($request->get('image'));
    $produit->setPrix((float)$request->get('prix'));
    $produit->setLibelle($request->get('libelle'));
        $em->persist($produit);

        $em->flush();

        $abonnement = new abonnement();

        $emq = $this->getDoctrine()->getRepository(produit::class);
        $ID_P=$emq->return_produit_id($produit);
        $abonnement->setIdP((int)$ID_P['ID_P']);
        $abonnement->setQuantite((int)$request->get('quantite'));
        $em->persist($abonnement);

        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);



    }

}
