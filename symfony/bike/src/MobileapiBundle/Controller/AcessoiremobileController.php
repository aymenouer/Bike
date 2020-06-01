<?php

namespace MobileapiBundle\Controller;

use AccessoireBundle\Entity\accessoire;
use ProduitBundle\Entity\produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class AcessoiremobileController extends Controller
{
    public function AllAccessoiresAction()
    {
        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_accessoire();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($list);
        return new JsonResponse($formated);
    }

    public function UpdateAccessoireAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $acc=$em->getRepository(accessoire::class)->find((int)$request->get('id'));
        $produit=$em->getRepository(produit::class)->find($acc->getIdP());
        $produit->setDescription($request->get('description'));
        $produit->setLibC($request->get('libc'));
        $produit->setLibS($request->get('libs'));
        $produit->setImage($request->get('image'));
        $produit->setPrix((float)$request->get('prix'));
        $produit->setLibelle($request->get('libelle'));


        $acc->setQuantite((int)$request->get('quantite'));
        $em->persist($produit);
        $em->persist($acc);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function DeleteAccessoireAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $acc=$em->getRepository(accessoire::class)->find((int)$request->get('id'));
        $produit=$em->getRepository(produit::class)->find($acc->getIdP());



        $em->remove($acc);
        $em->remove($produit);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function newAccessoireAction(Request $request)
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

        $accessoire = new accessoire();

        $emq = $this->getDoctrine()->getRepository(produit::class);
        $ID_P=$emq->return_produit_id($produit);
        $accessoire->setIdP((int)$ID_P['ID_P']);
        $accessoire->setQuantite((int)$request->get('quantite'));
        $em->persist($accessoire);

        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);

    }

}
