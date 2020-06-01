<?php

namespace panierBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('panierBundle:Default:index.html.twig');
    }
}
