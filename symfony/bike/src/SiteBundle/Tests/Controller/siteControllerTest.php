<?php

namespace SiteBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class siteControllerTest extends WebTestCase
{
    public function testListsites()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListSites');
    }

}
