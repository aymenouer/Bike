<?php

namespace AccessoireBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class accessoireControllerTest extends WebTestCase
{
    public function testListaccessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListAccessoire');
    }

    public function testUpdate_accessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Update_Accessoire');
    }

    public function testDelete_accessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Delete_Accessoire');
    }

    public function testDetail_accessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Detail_Accessoire');
    }

    public function testPrintpdf_accessoire()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/PrintPDF_Accessoire');
    }

}
