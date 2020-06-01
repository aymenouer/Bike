<?php

namespace MobileapiBundle\Controller;

use AchatBundle\Entity\achat;
use FOS\MessageBundle\Tests\Functional\Entity\User;
use SiteBundle\Entity\site;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\Request;
class usermobileController extends Controller
{

    public function AllUsersAction()
    {

        $requestsql = $this->getDoctrine()->getRepository(achat::class)->mise_a_jour();
        $users = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->findAll();
        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($users);
        return new JsonResponse($formated);
    }



    public function desactiverUserAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('id'));

        //$em->remove($user);
       // $em->flush();

        $userManager = $this->container->get('fos_user.user_manager');
        $user->setEnabled(false);
        $em->persist($user);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($user);
        return new JsonResponse($formated);
    }
    public function activerUserAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('id'));

        //$em->remove($user);
        // $em->flush();

        $userManager = $this->container->get('fos_user.user_manager');
        $user->setEnabled(true);
        $em->persist($user);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($user);
        return new JsonResponse($formated);
    }

    public function updateUserAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($request->get('id'));
        $user->setUsername($request->get('username'));
        $user->setEmail($request->get('email'));
        $user->setPlainPassword($request->get('password'));
        $user->setFirstName($request->get('firstname'));
        $user->setLastName($request->get('lastname'));
        $user->setUserImage($request->get('image'));
        $user->setUserAge($request->get('age'));
        $user->setUserNumber($request->get('number'));


        $em->persist($user);
        $em->flush();

        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($user);
        return new JsonResponse($formated);
    }





    public function newUserAction(Request $request)
    {

        $userManager = $this->container->get('fos_user.user_manager');
        $user = $userManager->createUser();
        $user->setUsername($request->get('username'));
        $user->setEmail($request->get('email'));
        $user->setRoles(array('ROLE_USER'));
        $user->setPlainPassword($request->get('password'));
        $user->setFirstName($request->get('firstname'));
        $user->setLastName($request->get('lastname'));
        $user->setUserImage($request->get('image'));
        $user->setUserAge($request->get('age'));
        $user->setUserNumber($request->get('number'));
        $user->setEnabled(true);
        $userManager->updateUser($user);


        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($user);
        return new JsonResponse($formated);
    }
    public function reacapUserAction( $id)
    {


        $user = $this->getDoctrine()->getManager()->getRepository("UserBundle:User")->find($id);


        $serializer = new Serializer( [new ObjectNormalizer()]);
        $formated = $serializer->normalize($user);
        return new JsonResponse($formated);
    }












}
