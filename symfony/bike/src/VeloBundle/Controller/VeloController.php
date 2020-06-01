<?php

namespace VeloBundle\Controller;

use AbonnementBundle\Entity\abonnement;
use ProduitBundle\Entity\produit;
use ProduitBundle\Form\produitType;
use SiteBundle\Entity\site;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use VeloBundle\Entity\Velo;
use VeloBundle\Form\VeloType;
use VeloBundle\VeloBundle;

class VeloController extends Controller
{


    public function displayVeloAction(Request $request)
    {
        $ID_P=null;
        $produit = new produit();
        $form=$this->createForm(produitType::class,$produit);
        $form=$form->handleRequest($request);
        $db = $this->getDoctrine()->getManager();

        $listcategorie = $db->getRepository('CategorieBundle:categorie')->findBy(['type' => 'Velo']);
        $listsite = $db->getRepository('SiteBundle:site')->findAll();

        if($form->isValid() && $form->isSubmitted() ) {

            $file=$produit->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('image_directory'),$fileName);
            $em = $this->getDoctrine()->getManager();
            $produit->setImage($fileName);
            $produit->setLibC((string)$request->get('categorie'));
            $produit->setLibS((string)$request->get('site'));
            $em->persist($produit);
            $em->flush();
            $velo = new velo();
            $emq = $this->getDoctrine()->getRepository(produit::class);
            $ID_P=$emq->return_produit_id($produit);
            $velo->setIdA((int)$ID_P['ID_P']);
            $velo->setType($request->get('type'));
            $velo->setAge((int)$request->get('age'));
            $velo->setCouleur($request->get('couleur'));
            $velo->setEtat($request->get('etat'));
            $em->persist($velo);
            $em->flush();
            return $this->redirect($request->getUri());
        }
        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_velo();
        return $this->render('@Velo/Velo/list_Velo.html.twig', array('form'=>$form->createView(),'velo'=>$list,'list_categorie'=>$listcategorie,'list_site'=>$listsite));

    }


    public function deleteVeloAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $velo=$em->getRepository("VeloBundle:Velo")->find($id);
        $produit=$em->getRepository(produit::class)->find($velo->getIdA());
        $em->remove($velo);
        $em->remove($produit);
        $em->flush();
        return $this->redirectToRoute("velo_display");
    }

    public function editVeloAction($id,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $velo=$em->getRepository("VeloBundle:Velo")->find($id);
        $produit=$em->getRepository(produit::class)->find($velo->getIdA());
        $velo->setAge((int)$request->get('age'));
        $velo->setCouleur($request->get('couleur'));
        $velo->setEtat($request->get('etat'));
        $velo->setType($request->get('type'));
        $produit->setDescription($request->get('Description'));
        $produit->setLibelle($request->get((string)'Libeller'));
        $produit->setPrix((float)$request->get('Prix'));
        $produit->setLibC($request->get('categorie'));
        $produit->setLibS($request->get('site'));
        $em->persist($produit);
        $em->persist($velo);
        $em->flush();

        return $this->redirectToRoute('velo_display');

    }




}
