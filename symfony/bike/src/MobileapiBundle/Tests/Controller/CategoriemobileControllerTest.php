<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CategoriemobileControllerTest extends WebTestCase
{
    public function testAllcategories()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/categories/all');
    }

}
