<?php

namespace MobileapiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MobileapiBundle:Default:index.html.twig');
    }
}
