<?php

namespace RatingBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class ratingcontrollerControllerTest extends WebTestCase
{
    public function testAdd_rating()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ADD_Rating');
    }

}
