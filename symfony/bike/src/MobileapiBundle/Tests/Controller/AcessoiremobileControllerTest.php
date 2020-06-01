<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class AcessoiremobileControllerTest extends WebTestCase
{
    public function testAllaccessoires()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Accessoires/all');
    }

    public function testUpdateaccessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/accessoire/update');
    }

    public function testDeleteaccessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Accessoires/remove');
    }

    public function testNewaccessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/accessoires/new');
    }

}
