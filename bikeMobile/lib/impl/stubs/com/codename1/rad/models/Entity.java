/**
 *  This package contains classes for CodeRAD models, such as {@link Entity}, {@link Property}, and {@link Tag}.
 */
package com.codename1.rad.models;


/**
 *  The base class for *Model* objects in CodeRAD.  The {@link Entity} class contains all of the plumbing
 *  necessary for property binding, change listeners, property lookup, and data conversion.  Subclasses of {@link Entity}
 *  should define a static {@link EntityType} object which serves as a sort of meta-class for the entity class.  The {@link EntityType}
 *  keeps track of the properties in the entity, and provides property lookup via {@link Tag}s, which is necessary for loose-coupling.
 *  
 * ==== Example Entity Class
 * 
 * The following figure shows the definition of a very simple entity class:
 * 
 * [source,java]
 * ----
 * public class UserProfile extends Entity {
 *     public static StringProperty name, description; <1>
 *     public static final EntityType TYPE = new EntityType(){{ <2>
 *         name = string(); <3>
 *         description = string();
 *     }};
 *     {
 *         setEntityType(TYPE); <4>
 *     }
 * }
 * ----
 * <1> We define 2 properties of type {@link com.codename1.rad.models.StringProperty} on the class.  A `StringProperty` is simply a property that contains a {@link java.lang.String}.  These are defined `public static` so that we can access them conveniently from anywhere.
 * <2> We define an {@link com.codename1.rad.models.EntityType} for the class.  This is also `public static` because it is class-level (all objects of this class should share the same entity type).
 * <3> We create `name` and `description` properties on this entity type.  Notice that this code runs in the *instance intializer* of the EntityType (the `{{` and `}}` braces are not a typo).  Running this code inside the instance initializer will ensure that the properties are added to the `EntityType`'s property index.
 * <4> Inside the `UserProfile` instance initializer, we set the entity type to the entity type that we created above.
 * 
 * [NOTE]
 * ====
 * Why can't we just use POJOs for our models?*
 * 
 * The {@link com.codename1.rad.models.Entity} class provides a lot of useful plumbing that is necessary for building reusable components that can bind to each other.  This includes property lookup, property binding, change events, and data conversion.
 * ====
 * 
 * ==== Adding Tags to Properties
 * 
 * In the above entity class, we haven't "tagged" any of the properties so it can't be used as a view model for any view, unless that view has been specifically designed for this class, which would limit its reusability.  This is simple to remedy, though. Let's tag the `name` property with {@link com.codename1.rad.schemas.Thing#name}, and `description` with {@link com.codename1.rad.schemas.Thing#description}:
 * 
 * [source,java]
 * ----
 * name = string(tags(Thing.name));
 * description = string(tags(Thing.description));
 * ----
 * 
 * [TIP]
 * ====
 * Properties can contain multiple tags.  E.g. If we want the name field to also be treated as the "ID" field, we could do:
 * 
 * [source,java]
 * ----
 * name = string(tags(Thing.name, Thing.identifier));
 * ----
 * ====
 * 
 * ==== Accessing Property Values
 * 
 * We can access a property value using its property directly.  E.g.
 * 
 * [source,java]
 * ----
 * String name = model.get(UserProfile.name);
 * ----
 * 
 * Notice here we didn't need to cast the return value to "String" because the `Profile.name` property is declared as a string property.  
 * 
 * We can also access the "name" property using the `Thing.name` tag, which is what allows us to use this as a loosely coupled view model:
 * 
 * [source,java]
 * ----
 * String name = (String)model.get(Thing.name);
 * ----
 * 
 * [WARNING]
 * ====
 * When using tags to access properties, it is best to use one of the `getXXX(Tag)` variants that explicitly converts the content type.  E.g. {@link com.codename1.rad.models.Entity#getText(com.codename1.rad.models.Tag)}.  This is because there is no guarantee that a given entity is storing its `Thing.name` property as a String.  It could use any type of property.  Using `getText()` or `getBoolean()` will automatically handle data-conversion if possible.
 * 
 * See {@link com.codename1.rad.models.ContentType} for more information about data conversion in properties.
 * ====
 * 
 * Using the convenience wrapper `getText()` and `setText()` we can then set the values on the `name` property in a generic way:
 * 
 * [source,java]
 * ----
 * model.setText(Thing.name, "Steve");
 * String name = model.getText(Thing.name); // "Steve"
 * ----
 * 
 * [TIP]
 * ====
 * Technically, you don't need to provide direct property access to your entity properties at all.  In our above `UserProfile` class we retained explicit references to the `name` and `description` properties, but we could have simply omitted this.  I.e. The following is also a perfectly valid entity type definition:
 * 
 * .An entity type that doesn't retain explicit references to its properties.  The properties can still be accessed via their assigned tags.
 * [source,java]
 * ----
 * public class UserProfile extends Entity {
 *     public static final EntityType TYPE = new EntityType(){{
 *         string(tags(Thing.name));
 *         string(tags(Thing.description));
 *     }};
 *     {
 *         setEntityType(TYPE);
 *     }
 * }
 * ----
 * ====
 *  
 *  
 *  @author shannah
 */
public class Entity extends java.util.Observable {

	public Entity() {
	}

	public void addPropertyChangeListener(Property property, com.codename1.ui.events.ActionListener l) {
	}

	public void removePropertyChangeListener(Property property, com.codename1.ui.events.ActionListener l) {
	}

	public void addPropertyChangeListener(com.codename1.ui.events.ActionListener l) {
	}

	public void removePropertyChangeListener(com.codename1.ui.events.ActionListener l) {
	}

	protected void firePropertyChangeEvent(PropertyChangeEvent pce) {
	}

	public com.codename1.ui.Image createImageToStorage(Tag tag, com.codename1.ui.EncodedImage placeholder, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToStorage(Property prop, com.codename1.ui.EncodedImage placeholder, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToStorage(Tag tag, com.codename1.ui.EncodedImage placeholder) {
	}

	public com.codename1.ui.Image createImageToStorage(Property prop, com.codename1.ui.EncodedImage placeholder) {
	}

	public com.codename1.ui.Image createImageToStorage(Tag tag, com.codename1.ui.EncodedImage placeholder, String storageFile) {
	}

	public com.codename1.ui.Image createImageToStorage(Property prop, com.codename1.ui.EncodedImage placeholder, String storageFile) {
	}

	public com.codename1.ui.Image createImageToStorage(Tag tag, com.codename1.ui.EncodedImage placeholder, String storageFile, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToStorage(Property prop, com.codename1.ui.EncodedImage placeholder, String storageFile, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToFile(Tag tag, com.codename1.ui.EncodedImage placeholder, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToFile(Property prop, com.codename1.ui.EncodedImage placeholder, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToFile(Tag tag, com.codename1.ui.EncodedImage placeholder) {
	}

	public com.codename1.ui.Image createImageToFile(Property prop, com.codename1.ui.EncodedImage placeholder) {
	}

	public com.codename1.ui.Image createImageToFile(Tag tag, com.codename1.ui.EncodedImage placeholder, String file) {
	}

	public com.codename1.ui.Image createImageToFile(Property prop, com.codename1.ui.EncodedImage placeholder, String file) {
	}

	public com.codename1.ui.Image createImageToFile(Tag tag, com.codename1.ui.EncodedImage placeholder, String file, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public com.codename1.ui.Image createImageToFile(Property prop, com.codename1.ui.EncodedImage placeholder, String file, com.codename1.ui.URLImage.ImageAdapter adapter) {
	}

	public void setEntityType(EntityType entityType) {
	}

	public EntityType getEntityType() {
	}

	public EntityList getEntityList(Tag[] tag) {
	}

	public EntityList getEntityList(Property prop) {
	}

	public Entity getEntity(Tag[] tag) {
	}

	public Entity getEntity(Property prop) {
	}

	/**
	 *  @return the aggregate
	 */
	public Aggregate getAggregate() {
	}

	@java.lang.Override
	protected synchronized void clearChanged() {
	}

	public Object get(Object key) {
	}

	public void set(Object key, Object value) {
	}

	public Object get(Property prop) {
	}

	public Property findProperty(Tag[] tags) {
	}

	public String getText(Property prop) {
	}

	public String getText(Tag[] tags) {
	}

	public Boolean getBoolean(Property prop) {
	}

	public Boolean getBoolean(Tag[] tags) {
	}

	public Object get(Property prop, ContentType contentType) {
	}

	public void set(Property prop, ContentType inputType, Object val) {
	}

	public boolean set(Tag tag, ContentType inputType, Object val) {
	}

	public boolean set(ContentType inputType, Object val, Tag[] tags) {
	}

	public void setText(Property prop, String text) {
	}

	public boolean setText(Tag tag, String text) {
	}

	public boolean setText(String text, Tag[] tags) {
	}

	public void setDouble(Property prop, double val) {
	}

	public boolean setDouble(Tag tag, double val) {
	}

	public boolean setDouble(double val, Tag[] tags) {
	}

	public void setFloat(Property prop, float val) {
	}

	public boolean setFloat(Tag tag, float val) {
	}

	public boolean setFloat(float val, Tag[] tags) {
	}

	public void setInt(Property prop, int val) {
	}

	public boolean setInt(Tag tag, int val) {
	}

	public boolean setInt(int val, Tag[] tags) {
	}

	public void setBoolean(Property prop, boolean val) {
	}

	public boolean setBoolean(Tag tag, boolean val) {
	}

	public boolean setBoolean(boolean val, Tag[] tags) {
	}

	public java.util.Date getDate(Property prop) {
	}

	public java.util.Date getDate(Tag[] tags) {
	}

	public void setDate(Property prop, java.util.Date date) {
	}

	public boolean setDate(Tag tag, java.util.Date date) {
	}

	public boolean setDate(java.util.Date date, Tag[] tags) {
	}

	public void setEntity(Property prop, Entity e) {
	}

	public boolean setEntity(Tag tag, Entity e) {
	}

	public boolean setEntity(Entity e, Tag[] tags) {
	}

	public boolean isEntity(Property prop) {
	}

	public boolean isEntity(Tag tag) {
	}

	public boolean isEmpty(Property prop) {
	}

	public boolean isEmpty(Tag tag) {
	}

	public boolean isFalsey(Property prop) {
	}

	public boolean isFalsey(Tag tag) {
	}

	public void setChanged(Property prop, boolean firePropertyChange) {
	}
}
