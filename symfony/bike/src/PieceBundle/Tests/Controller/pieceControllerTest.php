<?php

namespace PieceBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class pieceControllerTest extends WebTestCase
{
    public function testListpiece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListPiece');
    }

    public function testUpdate_piece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Update_Piece');
    }

    public function testDelete_piece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Delete_Piece');
    }

    public function testDetail_piece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Detail_Piece');
    }

    public function testDetail_front_piece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Detail_Front_Piece');
    }

    public function testPrintpdf_piece()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/PrintPDF_Piece');
    }

}
