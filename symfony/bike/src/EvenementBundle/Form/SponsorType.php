<?php

namespace EvenementBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class SponsorType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('num')->add('typesponsor', ChoiceType::class, [
            'choices' => [
                'sponsoring financier' =>"sponsoring financier",
                'sponsoring en nature' => "sponsoring en nature",



            ],])
            ->add('adresse')->add('nomsponsor')->add('idevenement',EntityType::class, array(
            'class' => 'EvenementBundle:Evenement',
            'choice_label' => 'titre',));
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EvenementBundle\Entity\Sponsor'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'evenementbundle_sponsor';
    }


}
