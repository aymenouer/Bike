<?php

use Symfony\Component\DependencyInjection\ContainerBuilder;
use Symfony\Component\HttpKernel\Kernel;
use Symfony\Component\Config\Loader\LoaderInterface;

class AppKernel extends Kernel
{
    public function registerBundles()
    {
        $bundles = [
            new Symfony\Bundle\FrameworkBundle\FrameworkBundle(),
            new Symfony\Bundle\SecurityBundle\SecurityBundle(),
            new Symfony\Bundle\TwigBundle\TwigBundle(),
            new Symfony\Bundle\MonologBundle\MonologBundle(),
            new Symfony\Bundle\SwiftmailerBundle\SwiftmailerBundle(),
            new Doctrine\Bundle\DoctrineBundle\DoctrineBundle(),
            new Sensio\Bundle\FrameworkExtraBundle\SensioFrameworkExtraBundle(),
            new AppBundle\AppBundle(),
            new FOS\UserBundle\FOSUserBundle(),
            new FOS\CKEditorBundle\FOSCKEditorBundle(),
            new UserBundle\UserBundle(),
            new CategorieBundle\CategorieBundle(),
            new SiteBundle\SiteBundle(),
            new ProduitBundle\ProduitBundle(),
            new Vangrg\ProfanityBundle\VangrgProfanityBundle(),
            new CMEN\GoogleChartsBundle\CMENGoogleChartsBundle(),
            new AbonnementBundle\AbonnementBundle(),
            new BG\BarcodeBundle\BarcodeBundle(),
            new Knp\Bundle\SnappyBundle\KnpSnappyBundle(),
            new FOS\MessageBundle\FOSMessageBundle(),
            new AchatBundle\AchatBundle(),
            new Gos\Bundle\PubSubRouterBundle\GosPubSubRouterBundle(),
            new Gos\Bundle\WebSocketBundle\GosWebSocketBundle(),
            new Gregwar\CaptchaBundle\GregwarCaptchaBundle,
            new ReclamationBundle\ReclamationBundle(),
            new AccessoireBundle\AccessoireBundle(),
            new Knp\Bundle\TimeBundle\KnpTimeBundle(),
            new Vresh\TwilioBundle\VreshTwilioBundle(),
            new RatingBundle\RatingBundle(),
            new VeloBundle\VeloBundle(),
            new ReservationBundle\ReservationBundle(),
            new Knp\Bundle\PaginatorBundle\KnpPaginatorBundle(),
            new PieceBundle\PieceBundle(),
            new ChatBundle\ChatBundle(),
            new MaitenanceBundle\MaitenanceBundle(),
            new EvenementBundle\EvenementBundle(),
            new Nomaya\SocialBundle\NomayaSocialBundle(),
            new MobileapiBundle\MobileapiBundle(),
            new Toiba\FullCalendarBundle\FullCalendarBundle(),
            new panierBundle\panierBundle(),
            new commandeBundle\commandeBundle(),
        ];

        if (in_array($this->getEnvironment(), ['dev', 'test'], true)) {
            $bundles[] = new Symfony\Bundle\DebugBundle\DebugBundle();
            $bundles[] = new Symfony\Bundle\WebProfilerBundle\WebProfilerBundle();
            $bundles[] = new Sensio\Bundle\DistributionBundle\SensioDistributionBundle();

            if ('dev' === $this->getEnvironment()) {
                $bundles[] = new Sensio\Bundle\GeneratorBundle\SensioGeneratorBundle();
                $bundles[] = new Symfony\Bundle\WebServerBundle\WebServerBundle();
            }
        }

        return $bundles;
    }

    public function getRootDir()
    {
        return __DIR__;
    }

    public function getCacheDir()
    {
        return dirname(__DIR__).'/var/cache/'.$this->getEnvironment();
    }

    public function getLogDir()
    {
        return dirname(__DIR__).'/var/logs';
    }

    public function registerContainerConfiguration(LoaderInterface $loader)
    {
        $loader->load(function (ContainerBuilder $container) {
            $container->setParameter('container.autowiring.strict_mode', true);
            $container->setParameter('container.dumper.inline_class_loader', true);

            $container->addObjectResource($this);
        });
        $loader->load($this->getRootDir().'/config/config_'.$this->getEnvironment().'.yml');
    }
}
