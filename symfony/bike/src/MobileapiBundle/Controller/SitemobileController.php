<?php


namespace MobileapiBundle\Controller;


use SiteBundle\Entity\site;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class SitemobileController extends Controller
{
    public function AllsitesAction()
    {
        $sites = $this->getDoctrine()->getManager()->getRepository("SiteBundle:site")->findAll();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($sites);
        return new JsonResponse($formated);
    }

    public function deletesiteAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $site=$em->getRepository(site::class)->find((int)$request->get("id"));



        $em->remove($site);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }


    public function updatesiteAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(site::class)->find((int)$request->get("id"));
        $modele->setLibS($request->get('lib_s'));
        $modele->setCapacite($request->get('capacite'));
        $modele->setLieu($request->get('lieu'));
        $em->persist($modele);
        $em->flush();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }
    public function newsiteAction(Request $request)
    {
        $site = new site();


        $site->setLibS($request->get("lib_s"));
        $site->setLieu($request->get("lieu"));
        $site->setCapacite((int)$request->get("capacite"));
        $em=$this->getDoctrine()->getManager();
        $em->persist($site);


        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize("ok");
        return new JsonResponse($formated);
    }

}