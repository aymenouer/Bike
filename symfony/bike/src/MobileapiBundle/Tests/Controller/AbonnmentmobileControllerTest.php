<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class AbonnmentmobileControllerTest extends WebTestCase
{
    public function testAllabonnments()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AllAbonnments');
    }

    public function testUpdateabonnment()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/UpdateAbonnment');
    }

    public function testDeleteabonnment()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/DeleteAbonnment');
    }

    public function testNewabonnment()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/newAbonnment');
    }

}
