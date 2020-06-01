<?php

namespace ProduitBundle\Form;

use CategorieBundle\Entity\categorie;
use CategorieBundle\Form\categorieType;
use FOS\CKEditorBundle\Form\Type\CKEditorType;
use SiteBundle\Entity\site;
use SiteBundle\Form\siteType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class produitType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('libelle')->add('image',FileType::class,array('label'=>'inserer une imamge'))->add('description')->add('prix',IntegerType::class,array('attr'=>array('myAttr'=>'prix')))


         ;
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ProduitBundle\Entity\produit'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'produitbundle_produit';
    }


}
