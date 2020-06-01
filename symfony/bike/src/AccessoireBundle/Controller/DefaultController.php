<?php

namespace AccessoireBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AccessoireBundle:Default:index.html.twig');
    }
}
