<?php

namespace AbonnementBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class abonnementControllerTest extends WebTestCase
{
    public function testListabonnements()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListAbonnements');
    }

    public function testUpdate_abonnement()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Update_Abonnement');
    }

    public function testDelete_abonnement()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Delete_Abonnement');
    }

}
