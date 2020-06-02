/**
 *  This package contains the foundation classes for CodeRAD views.  Nodes, Attributes, UI descriptors, etc..
 */
package com.codename1.rad.ui;


/**
 *  Wrapper around a component that supports binding to a property.
 *  @author shannah
 */
public abstract class PropertyView extends com.codename1.ui.Container implements com.codename1.ui.Editable {

	public PropertyView(com.codename1.ui.Component component, com.codename1.rad.models.Entity entity, com.codename1.rad.nodes.FieldNode field) {
	}

	@java.lang.Override
	public void setNextFocusLeft(com.codename1.ui.Component nextFocusLeft) {
	}

	@java.lang.Override
	public void setNextFocusRight(com.codename1.ui.Component nextFocusRight) {
	}

	@java.lang.Override
	protected void initComponent() {
	}

	@java.lang.Override
	protected void deinitialize() {
	}

	public abstract void bind() {
	}

	public abstract void unbind() {
	}

	public com.codename1.ui.Component getComponent() {
	}

	public com.codename1.rad.models.Property getProperty() {
	}

	public com.codename1.rad.models.PropertySelector getPropertySelector() {
	}

	public com.codename1.rad.nodes.FieldNode getField() {
	}

	public com.codename1.rad.models.Entity getEntity() {
	}

	public abstract void update() {
	}

	public abstract void commit() {
	}
}
