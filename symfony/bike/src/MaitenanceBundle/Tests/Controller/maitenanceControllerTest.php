<?php

namespace MaitenanceBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class maitenanceControllerTest extends WebTestCase
{
    public function testAdd_product_maitenance()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Add_product_Maitenance');
    }

    public function testList_product_maitenance_user()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/List_Product_Maitenance_User');
    }

    public function testList_product_maitenance()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/List_Product_Maitenance');
    }

    public function testUpdate_product_maitenance()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Update_Product_Maitenance');
    }

}
