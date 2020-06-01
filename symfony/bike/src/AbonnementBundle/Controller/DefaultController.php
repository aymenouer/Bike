<?php

namespace AbonnementBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AbonnementBundle:Default:index.html.twig');
    }
}
