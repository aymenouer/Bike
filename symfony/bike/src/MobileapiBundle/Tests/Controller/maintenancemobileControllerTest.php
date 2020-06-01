<?php

namespace MobileapiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class maintenancemobileControllerTest extends WebTestCase
{
    public function testAdd_maintenance_user()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Add_maintenance_user');
    }

    public function testList_maintenanceuser()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/List_MaintenanceUser');
    }

    public function testListmaintenanceadmin()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListMaintenanceAdmin');
    }

}
