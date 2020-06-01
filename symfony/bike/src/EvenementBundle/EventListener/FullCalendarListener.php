<?php
namespace EvenementBundle\EventListener;

use Toiba\FullCalendarBundle\Entity\Event;
use Toiba\FullCalendarBundle\Event\CalendarEvent;
use EvenementBundle\Repository\EvenementRepository;

class FullCalendarListener
{
    /* private $eventRepository;

     public function __construct(
         EventRepository $eventRepository
     ) {
         $this->eventRepository = $eventRepository;
     }*/
    public function loadEvents(CalendarEvent $calendar)
    {
        //$events =  $this->eventRepository->findAll();

        $startDate = $calendar->getStart();
        $endDate = $calendar->getEnd();
        $filters = $calendar->getFilters();

        // You may want to make a custom query to populate the calendar
        // foreach ($events as $booking) {
        $cevent = new Event(
            'Event 1',
            new \DateTime('Tuesday this week'),
            new \DateTime('Wednesdays this week')
        );

        // If the end date is null or not defined, it creates a all day event
        $calendar->addEvent($cevent);
        // }
    }
}
