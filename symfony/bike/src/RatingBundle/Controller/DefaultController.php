<?php

namespace RatingBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('RatingBundle:Default:index.html.twig');
    }
}
