<?php

namespace CategorieBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CategorieControllerTest extends WebTestCase
{
    public function testListcategories()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListCategories');
    }

    public function testDelete()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Delete');
    }

    public function testUpdate()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Update');
    }

    public function testDetail()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Detail');
    }

}
