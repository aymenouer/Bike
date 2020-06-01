<?php

namespace commandeBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('commandeBundle:Default:index.html.twig');
    }
}
