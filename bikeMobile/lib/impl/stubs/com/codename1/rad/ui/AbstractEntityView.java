/**
 *  This package contains the foundation classes for CodeRAD views.  Nodes, Attributes, UI descriptors, etc..
 */
package com.codename1.rad.ui;


/**
 *  A base class for a view that can bind to an entity.  Sublasses just need toi implement {@link #update() }.
 *  @author shannah
 */
public abstract class AbstractEntityView extends com.codename1.ui.Container implements EntityView {

	public AbstractEntityView(com.codename1.rad.models.Entity entity) {
	}

	public void bind() {
	}

	public void unbind() {
	}

	@java.lang.Override
	protected void initComponent() {
	}

	@java.lang.Override
	protected void deinitialize() {
	}

	public com.codename1.rad.models.Entity getEntity() {
	}

	public void setEntity(com.codename1.rad.models.Entity entity) {
	}

	protected com.codename1.rad.models.Property findProperty(com.codename1.rad.models.Tag[] tags) {
	}
}
