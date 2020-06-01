<?php

namespace ReclamationBundle\Controller;

use Ivory\GoogleMap\Map;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        $provider = $this->container->get('fos_message.provider');

        $threads = $provider->getInboxThreads();
        // replace this example code with whatever you need


        return $this->render('@Reclamation/Default/t.html.twig',array('thread'=>$threads));
    }
}
