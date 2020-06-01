<?php

namespace ProduitBundle\Controller;

use ProduitBundle\Entity\produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class produitcontrollerController extends Controller
{
    public function Affichage_ProductsAction()
    {
        $db = $this->getDoctrine()->getManager();


        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $listproduit=$this->getDoctrine()->getRepository(produit::class)->produit_abonment();
        $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire();
        $listvelo = $this->getDoctrine()->getRepository(produit::class)->produit_velo();

        $listpiece=$this->getDoctrine()->getRepository(produit::class)->produit_piece();

        return $this->render('@Produit/produitsfront/affichage_products.html.twig', array(
            'listsite' => $listsite,
            'listproduit' => $listproduit,
            'verif_abonnment'=>$verif_abonnment,
            'listaccessoire'=>$listacessoire,
            'listvelo'=>$listvelo,
            'listpiece'=>$listpiece

        ));
    }




    public function Affichage_Products_casAction(Request $request,$cas)
    {



        $db = $this->getDoctrine()->getManager();


        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $listproduit=$this->getDoctrine()->getRepository(produit::class)->produit_abonment();
        $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire();
        $listvelo = $this->getDoctrine()->getRepository(produit::class)->produit_velo();

        $listpiece=$this->getDoctrine()->getRepository(produit::class)->produit_piece();

        return $this->render('@Produit/produitsfront/affichage_products_cas.html.twig', array(
            'listsite' => $listsite,
            'listproduit' => $listproduit,
            'verif_abonnment'=>$verif_abonnment,
            'listaccessoire'=>$listacessoire,
            'listvelo'=>$listvelo,
            'listpiece'=>$listpiece,
            'cas'=>$cas

        ));
    }
    public function Affichage_Products_prixAction($cas)
    {

    if ($cas==1)
    {
        $db = $this->getDoctrine()->getManager();


        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $listproduit=$this->getDoctrine()->getRepository(produit::class)->produit_abonment_prix(300,400);
        $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire_prix(300,400);
        $listvelo=$this->getDoctrine()->getRepository(produit::class)->produit_velo_prix(300,400);
        $listpiece=$this->getDoctrine()->getRepository(produit::class)->produit_piece_prix(300,400);

        return $this->render('@Produit/produitsfront/affichage_products.html.twig', array(
            'listsite' => $listsite,
            'listproduit' => $listproduit,
            'verif_abonnment'=>$verif_abonnment,
            'listaccessoire'=>$listacessoire,
            'listvelo'=>$listvelo,
            'listpiece'=>$listpiece
        ));
    }
    else if ($cas == 2)
    {
        $db = $this->getDoctrine()->getManager();


        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $listproduit=$this->getDoctrine()->getRepository(produit::class)->produit_abonment_prix(400,500);
        $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire_prix(400,500);
        $listvelo=$this->getDoctrine()->getRepository(produit::class)->produit_velo_prix(400,500);
        $listpiece=$this->getDoctrine()->getRepository(produit::class)->produit_piece_prix(400,500);
        return $this->render('@Produit/produitsfront/affichage_products.html.twig', array(
            'listsite' => $listsite,
            'listproduit' => $listproduit,
            'verif_abonnment'=>$verif_abonnment,
            'listaccessoire'=>$listacessoire,
            'listvelo'=>$listvelo,
               'listpiece'=>$listpiece

        ));
    }
    else if ($cas == 3)
    {
        $db = $this->getDoctrine()->getManager();


        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $listproduit=$this->getDoctrine()->getRepository(produit::class)->produit_abonment_prix(500,600);
        $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire_prix(500,600);
        $listvelo=$this->getDoctrine()->getRepository(produit::class)->produit_velo_prix(500,600);
        $listpiece=$this->getDoctrine()->getRepository(produit::class)->produit_piece_prix(500,600);
        return $this->render('@Produit/produitsfront/affichage_products.html.twig', array(
            'listsite' => $listsite,
            'listproduit' => $listproduit,
            'verif_abonnment'=>$verif_abonnment,
            'listaccessoire'=>$listacessoire,
            'listvelo'=>$listvelo,
            'listpiece'=>$listpiece
        ));
    }

        $db = $this->getDoctrine()->getManager();


        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $listproduit=$this->getDoctrine()->getRepository(produit::class)->produit_abonment_prix(600,9000);
        $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire_prix(600,9000);
        $listvelo=$this->getDoctrine()->getRepository(produit::class)->produit_velo_prix(600,9000);
        $listpiece=$this->getDoctrine()->getRepository(produit::class)->produit_piece_prix(600,9000);
        return $this->render('@Produit/produitsfront/affichage_products.html.twig', array(
            'listsite' => $listsite,
            'listproduit' => $listproduit,
            'verif_abonnment'=>$verif_abonnment,
            'listaccessoire'=>$listacessoire,
            'listvelo'=>$listvelo,
            'listpiece'=>$listpiece
        ));



    }
    public function Affichage_Products_selon_siteAction($lib_S)
    {
        $db = $this->getDoctrine()->getManager();


        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $listproduit=$this->getDoctrine()->getRepository(produit::class)->produit_abonment_selon_site($lib_S);
        $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire_selon_site($lib_S);
        $listvelo = $this->getDoctrine()->getRepository(produit::class)->produit_velo_selon_site($lib_S);



        return $this->render('@Produit/produitsfront/affichage_products_selon site.html.twig', array(
            'listsite' => $listsite,
            'listproduit' => $listproduit,
            'verif_abonnment'=>$verif_abonnment,
            'listaccessoire'=>$listacessoire,
            'lib_site'=>$lib_S,
            'listvelo'=>$listvelo,
        ));
    }

}
