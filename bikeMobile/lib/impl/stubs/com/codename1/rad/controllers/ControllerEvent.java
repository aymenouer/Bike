/**
 *  This package contains controllers for CodeRAD applications.
 *  
 */
package com.codename1.rad.controllers;


/**
 *  A base event for all controller events.  This is the fundamental building block for how information
 *  propagates up the controller hierarchy from views or child-controllers.
 *  @author shannah
 */
public class ControllerEvent extends com.codename1.ui.events.ActionEvent {

	/**
	 *  Creates a new controller event.
	 */
	public ControllerEvent() {
	}

	/**
	 *  Creates a new controller event with a specified source.
	 *  @param source 
	 */
	public ControllerEvent(Object source) {
	}

	public ControllerEvent as(Class type) {
	}
}
