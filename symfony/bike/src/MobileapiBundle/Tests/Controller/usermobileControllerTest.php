<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class usermobileControllerTest extends WebTestCase
{
    public function testAllusers()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AllUsers');
    }

}
