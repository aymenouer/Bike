<?php

namespace ProduitBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class produitcontrollerControllerTest extends WebTestCase
{
    public function testAffichage_products()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Affichage_Products');
    }

}
