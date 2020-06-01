<?php

namespace AchatBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AchatBundle:Default:index.html.twig');
    }
}
