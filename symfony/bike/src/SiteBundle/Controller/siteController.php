<?php

namespace SiteBundle\Controller;

use SiteBundle\Entity\site;
use SiteBundle\Form\siteType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class siteController extends Controller
{
    public function ListSitesAction(Request $request)
    {
        $site = new site();
        $form=$this->createForm(siteType::class,$site);
        $form=$form->handleRequest($request);

        if($form->isValid())
        {

            $em=$this->getDoctrine()->getManager();
            $em->persist($site);


            $em->flush();
            return $this->redirect($request->getUri());

        }



        $db = $this->getDoctrine()->getManager();

        $listsite = $db->getRepository('SiteBundle:site')->findAll();

        return $this->render('@Site/AdminSite/list_sites.html.twig', array('list' => $listsite,'form'=>$form->createView()));




    }
    public function DeletesiteAction($idS)
    {

        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(site::class)->find($idS);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('_list_sites');




    }

    public function UpdatesiteAction($idS,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(site::class)->find($idS);
        $modele->setLibS($request->get('libS'));
        $modele->setCapacite($request->get('capacite'));
        $modele->setLieu($request->get('lieu'));
        $em->persist($modele);
        $em->flush();
        return $this->redirectToRoute('_list_sites');

    }
    public function MapsiteAction()
    {

        return $this->render('@Site/AdminSite/map_sites.html.twig');

    }

}
