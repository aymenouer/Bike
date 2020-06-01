<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class AchatmobileControllerTest extends WebTestCase
{
    public function testAchat_troismois()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat/trois_mois');
    }

    public function testAchat_unans()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat/un_ans');
    }

    public function testAchat_unmois()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat/un_mois');
    }

    public function testAchatall()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat/all');
    }

    public function testDeleteoffre()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Achat/remove');
    }

}
