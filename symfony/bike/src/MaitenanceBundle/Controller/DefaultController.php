<?php

namespace MaitenanceBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MaitenanceBundle:Default:index.html.twig');
    }
}
