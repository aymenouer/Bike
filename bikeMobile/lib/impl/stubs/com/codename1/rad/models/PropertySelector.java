/**
 *  This package contains classes for CodeRAD models, such as {@link Entity}, {@link Property}, and {@link Tag}.
 */
package com.codename1.rad.models;


/**
 *  A class for retrieving properties from an entity hierarchy in a dynamic way.  E.g.
 *  If you have an entity that is expected to have a property, which is itself an entity,
 *  which has a property that we want to retrieve.  This class encapsulates the retrieval
 *  of such a sub-property by specifying a "path" to the property.
 *  @author shannah
 */
public class PropertySelector {

	public PropertySelector(Entity root, Tag[] tags) {
	}

	public PropertySelector(Entity root, Property property) {
	}

	public PropertySelector(PropertySelector parent, Tag[] tags) {
	}

	public PropertySelector(PropertySelector parent, Property property) {
	}

	public void addPropertyChangeListener(com.codename1.ui.events.ActionListener l) {
	}

	public void removePropertyChangeListener(com.codename1.ui.events.ActionListener l) {
	}

	public Object get(ContentType type, Object defaultValue) {
	}

	public PropertySelector createChildSelector(Property property) {
	}

	public PropertySelector createChildSelector(Tag[] tags) {
	}

	public PropertySelector child(Property prop) {
	}

	public PropertySelector child(Tag[] tags) {
	}

	public String getText(String defaultValue) {
	}

	public Boolean getBoolean(boolean defaultVal) {
	}

	public java.util.Date getDate(java.util.Date defaultVal) {
	}

	public Entity getEntity(Entity defaultVal) {
	}

	public EntityList getEntityList(EntityList defaultVal) {
	}

	public Float getFloat(float defaultVal) {
	}

	public Double getDouble(double defaultVal) {
	}

	public Integer getInt(int defaultVal) {
	}

	public boolean isEmpty() {
	}

	public boolean isFalsey() {
	}

	public com.codename1.ui.Image createImageToStorage(com.codename1.ui.EncodedImage placeholder, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToStorage(com.codename1.ui.EncodedImage placeholder) {
	}

	public com.codename1.ui.Image createImageToStorage(com.codename1.ui.EncodedImage placeholder, String storageFile) {
	}

	public com.codename1.ui.Image createImageToStorage(com.codename1.ui.EncodedImage placeholder, String storageFile, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToFile(com.codename1.ui.EncodedImage placeholder, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToFile(com.codename1.ui.EncodedImage placeholder) {
	}

	public com.codename1.ui.Image createImageToFile(com.codename1.ui.EncodedImage placeholder, String file) {
	}

	public com.codename1.ui.Image createImageToFile(com.codename1.ui.EncodedImage placeholder, String file, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public Property getLeafProperty() {
	}

	public Entity getLeafEntity() {
	}
}
