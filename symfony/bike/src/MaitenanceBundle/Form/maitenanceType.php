<?php

namespace MaitenanceBundle\Form;

use FOS\CKEditorBundle\Form\Type\CKEditorType;
use Gregwar\CaptchaBundle\Type\CaptchaType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class maitenanceType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('Problem', CKEditorType::class, array(
            'config' => array(
                'toolbar' => 'full',
            ),))->add('type', ChoiceType::class, [
            'choices'  => [
                 'accessoire' => 'accessoire',
                'Velo' => 'Velo',
                'Piece'=>'Piece',
                'Others'=>'Others'
            ],
        ])->add('image',FileType::class,array('label'=>'inserer une imamge'))->add('captchaCode', CaptchaType::class);;
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'MaitenanceBundle\Entity\maitenance'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'maitenancebundle_maitenance';
    }


}
