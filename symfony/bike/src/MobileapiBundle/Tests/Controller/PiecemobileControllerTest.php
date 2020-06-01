<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class PiecemobileControllerTest extends WebTestCase
{
    public function testAllpieces()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AllPieces');
    }

    public function testUpdatepiece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/UpdatePiece');
    }

    public function testDeletepiece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/DeletePiece');
    }

    public function testNewpiece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/newPiece');
    }

}
