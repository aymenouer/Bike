<?php

namespace AchatBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class AchatControllerTest extends WebTestCase
{
    public function testAchat_troismois()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat_troisMois');
    }

    public function testAchat_unans()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat_unans');
    }

    public function testAchat_unmois()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat_unmois');
    }

    public function testAffichageoffre()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AffichageOffre');
    }

}
