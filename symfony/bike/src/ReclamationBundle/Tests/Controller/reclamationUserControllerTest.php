<?php

namespace ReclamationBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class reclamationUserControllerTest extends WebTestCase
{
    public function testListreclamationuser()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListReclamationUser');
    }

    public function testDetailreclamationuser()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/DetailReclamationUser');
    }

    public function testAddreclamationuser()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AddReclamationUser');
    }

}
