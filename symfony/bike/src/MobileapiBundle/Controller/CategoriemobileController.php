<?php

namespace MobileapiBundle\Controller;

use CategorieBundle\Entity\categorie;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class CategoriemobileController extends Controller
{
    public function AllCategoriesAction()
    {
        $categories = $this->getDoctrine()->getManager()->getRepository("CategorieBundle:categorie")->findAll();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($categories);
        return new JsonResponse($formated);
    }
    public function UpdateCategoriesAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(categorie::class)->find((int)$request->get("id"));
        $modele->setLibC($request->get('lib_c'));
        $modele->setDescription($request->get('Description'));
        $modele->setType($request->get('type'));
        $em->persist($modele);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function DeleteCategoriesAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $site=$em->getRepository(categorie::class)->find((int)$request->get("id"));



        $em->remove($site);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

    public function newCategoriesAction(Request $request)
    {
        $categorie = new categorie();

        $categorie->setLibC($request->get('lib_c'));
        $categorie->setDescription($request->get('Description'));
        $categorie->setType($request->get('type'));
        $em=$this->getDoctrine()->getManager();
        $em->persist($categorie);


        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }



}
