<?php

namespace EvenementBundle\Form;

use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EvenementType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('titre')->add('discription')->add('dated', DateType::class, array(
        'widget' => 'single_text',
        'format' => 'yyyy-MM-dd'))
        ->add('dateF', DateType::class, array(
            'widget' => 'single_text',
            'format' => 'yyyy-MM-dd'))->add('lieu')->add('nbrplace')->add('file');
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EvenementBundle\Entity\Evenement'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'evenementbundle_evenements';
    }


}
