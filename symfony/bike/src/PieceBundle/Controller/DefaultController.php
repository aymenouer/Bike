<?php

namespace PieceBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('PieceBundle:Default:index.html.twig');
    }
}
