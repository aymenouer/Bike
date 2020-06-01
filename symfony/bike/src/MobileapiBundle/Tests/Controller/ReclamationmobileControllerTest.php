<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class ReclamationmobileControllerTest extends WebTestCase
{
    public function testListreclamationuser()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListReclamationUser');
    }

    public function testListreclamationusers()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListReclamationUsers');
    }

    public function testAddreclamationuser()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AddReclamationUser');
    }

    public function testDeletereclamation()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/DeleteReclamation');
    }

    public function testTraitereclamation()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/TraiteReclamation');
    }

}
