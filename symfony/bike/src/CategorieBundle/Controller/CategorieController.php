<?php

namespace CategorieBundle\Controller;

use CategorieBundle\Entity\categorie;
use CategorieBundle\Form\categorieType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class CategorieController extends Controller
{
    public function ListCategoriesAction(Request $request)
    {
        $categorie = new categorie();
        $form=$this->createForm(categorieType::class,$categorie);
        $form=$form->handleRequest($request);

        if($form->isValid())
        {

            $em=$this->getDoctrine()->getManager();
            $em->persist($categorie);


            $em->flush();
            return $this->redirect($request->getUri());

        }



        $db = $this->getDoctrine()->getManager();

        $listcategorie = $db->getRepository('CategorieBundle:categorie')->findAll();

        return $this->render('@Categorie/AdminCategorie/list_categories.html.twig', array('list' => $listcategorie,'form'=>$form->createView()));







    }

    public function DeletecategorieAction($idC)
    {

        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(categorie::class)->find($idC);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('_list_categories');




    }

    public function UpdatecategorieAction($idC,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository(categorie::class)->find($idC);
        $modele->setLibC($request->get('libC'));
        $modele->setDescription($request->get('description'));
        $modele->setType($request->get('type'));
        $em->persist($modele);
        $em->flush();
        return $this->redirectToRoute('_list_categories');

    }


}
