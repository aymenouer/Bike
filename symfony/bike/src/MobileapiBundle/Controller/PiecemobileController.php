<?php

namespace MobileapiBundle\Controller;

use PieceBundle\Entity\piece;
use ProduitBundle\Entity\produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class PiecemobileController extends Controller
{
    public function AllPiecesAction()
    {
        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_piece();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($list);
        return new JsonResponse($formated);
    }

    public function UpdatePieceAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $piece=$em->getRepository(piece::class)->find((int)$request->get('id'));
        $produit=$em->getRepository(produit::class)->find($piece->getIdP());
        $produit->setDescription($request->get('description'));
        $produit->setLibC($request->get('libc'));
        $produit->setLibS($request->get('libs'));
        $produit->setImage($request->get('image'));
        $produit->setPrix((float)$request->get('prix'));
        $produit->setLibelle($request->get('libelle'));

        $piece->setEtat($request->get('etat'));
        $piece->setQuantite((int)$request->get('quantite'));
        $em->persist($produit);
        $em->persist($piece);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function DeletePieceAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();

        $piece=$em->getRepository(piece::class)->find((int)$request->get('id'));
        $produit=$em->getRepository(produit::class)->find($piece->getIdP());


        $em->remove($piece);
        $em->remove($produit);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function newPieceAction(Request $request)
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




        $piece = new piece();

        $emq = $this->getDoctrine()->getRepository(produit::class);
        $ID_P=$emq->return_produit_id($produit);
        $piece->setIdP((int)$ID_P['ID_P']);
        $piece->setEtat($request->get('etat'));
        $piece->setQuantite((int)$request->get('quantite'));
        $em->persist($piece);

        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);






    }

}
