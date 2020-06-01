<?php

namespace ReclamationBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class ReclamationControllerTest extends WebTestCase
{
    public function testListreclamation()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListReclamation');
    }

    public function testDeletereclamation()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/DeleteReclamation');
    }

}
