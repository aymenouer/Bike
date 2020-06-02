/**
 *  This package contains classes for CodeRAD models, such as {@link Entity}, {@link Property}, and {@link Tag}.
 */
package com.codename1.rad.models;


/**
 *  Encapsulates an entity "type" for an {@link Entity} class.  This is sort of like a "meta-class" that provides run-time support for data conversion, 
 *  property lookup, and property binding.  An EntityType should create its {@link Property}s in its instance initializer.  Typically, the entity type
 *  is declared as a static final anonymous class inside the {@link Entity} class definition as follows:
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
 *  Technically, you don't need to provide direct property access to your entity properties at all.  In our above `UserProfile` class we retained explicit references to the `name` and `description` properties, but we could have simply omitted this.  I.e. The following is also a perfectly valid entity type definition:
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
 *  
 *  == Property Types
 *  
 *  {@link EntityType} includes convenience methods for creating the standard property types:
 *  
 *  . {@link #string(com.codename1.rad.models.Attribute...) }
 *  . {@link #Integer(com.codename1.rad.models.Attribute...) }
 *  . {@link #Boolean(com.codename1.rad.models.Attribute...) }
 *  . {@link #Double(com.codename1.rad.models.Attribute...) }
 *  . {@link #date(com.codename1.rad.models.Attribute...) }
 *  . {@link #entity(java.lang.Class) } - an entity
 *  . {@link #list(java.lang.Class, com.codename1.rad.models.Attribute...) } - A list of entities.
 *  
 *  These methods will create the corresponding property and add it to the {@link EntityType} using {@link #addProperty(com.codename1.rad.models.Property) }.  You can also create custom property types by subclassing {@link AbstractProperty}
 *  
 *  @author shannah
 */
public class EntityType implements Iterable {

	public EntityType() {
	}

	public static EntityType getEntityType(Class type) {
	}

	public void addProperty(Property property) {
	}

	public void addAllProperties(Property[] properties) {
	}

	public boolean removeProperty(Property property) {
	}

	@java.lang.Override
	public java.util.Iterator iterator() {
	}

	public boolean isDynamic() {
	}

	public boolean contains(Property property) {
	}

	public StringProperty string(Attribute[] atts) {
	}

	public SimpleProperty object(Class type, Attribute[] atts) {
	}

	public DateProperty date(Attribute[] atts) {
	}

	public IntProperty Integer(Attribute[] atts) {
	}

	public DoubleProperty Double(Attribute[] atts) {
	}

	public BooleanProperty Boolean(Attribute[] atts) {
	}

	public ListProperty compose(Class type, Attribute[] atts) {
	}

	public ListProperty list(Class type, Attribute[] atts) {
	}

	public EntityProperty entity(Class type, Attribute[] atts) {
	}

	public static Property.Label label(String label) {
	}

	public static Property.Description description(String description) {
	}

	public static Property.Widget widget(Attribute[] atts) {
	}

	public static Tags tags(Tag[] atts) {
	}

	public Property findProperty(Tag[] tags) {
	}

	public Object getPropertyValue(Property prop, Entity entity, ContentType outputType) {
	}

	public void setPropertyValue(Property prop, Entity entity, ContentType inputType, Object data) {
	}

	public String getText(Property prop, Entity entity) {
	}

	public void setText(Property prop, Entity entity, String text) {
	}

	public Integer getInt(Property prop, Entity entity) {
	}

	public void setInt(Property prop, Entity entity, int value) {
	}

	public Double getDouble(Property prop, Entity entity) {
	}

	public void setDouble(Property prop, Entity entity, double val) {
	}

	public Float getFloat(Property prop, Entity entity) {
	}

	public void setFloat(Property prop, Entity entity, float val) {
	}

	public Boolean getBoolean(Property prop, Entity entity) {
	}

	public void setBoolean(Property prop, Entity entity, boolean val) {
	}

	public Object getPropertyValue(Tag tag, Entity entity, ContentType outputType) {
	}

	public boolean setPropertyValue(Tag tag, Entity entity, ContentType inputType, Object val) {
	}

	public Object getPropertyValue(Entity entity, ContentType outputType, Tag[] tags) {
	}

	public boolean setPropertyValue(Entity entity, ContentType inputType, Object val, Tag[] tags) {
	}

	public Object getPropertyValue(Tag tag, Entity entity, ContentType outputType, Object defaultVal) {
	}

	public String getText(Tag tag, Entity entity) {
	}

	public boolean setText(Tag tag, Entity entity, String text) {
	}

	public String getText(Entity entity, Tag[] tags) {
	}

	public boolean setText(Entity entity, String text, Tag[] tags) {
	}

	public Integer getInt(Tag prop, Entity entity) {
	}

	public boolean setInt(Tag prop, Entity entity, int val) {
	}

	public Integer getInt(Entity entity, Tag[] tags) {
	}

	public boolean setInt(Entity entity, int val, Tag[] tags) {
	}

	public Double getDouble(Entity entity, Tag[] tags) {
	}

	public boolean setDouble(Entity entity, double val, Tag[] tags) {
	}

	public Double getDouble(Tag prop, Entity entity) {
	}

	public boolean setDouble(Tag prop, Entity entity, double val) {
	}

	public Float getFloat(Tag prop, Entity entity) {
	}

	public boolean setFloat(Tag prop, Entity entity, float val) {
	}

	public Float getFloat(Entity entity, Tag[] tags) {
	}

	public boolean setFloat(Entity entity, float val, Tag[] tags) {
	}

	public Boolean getBoolean(Tag prop, Entity entity) {
	}

	public boolean setBoolean(Tag prop, Entity entity, boolean val) {
	}

	public Boolean getBoolean(Entity entity, Tag[] tags) {
	}

	public boolean setBoolean(Entity entity, boolean val, Tag[] tags) {
	}

	public String getText(Tag tag, Entity entity, String defaultVal) {
	}

	public Integer getInt(Tag prop, Entity entity, Integer defaultVal) {
	}

	public Double getDouble(Tag prop, Entity entity, Double defaultVal) {
	}

	public Float getFloat(Tag prop, Entity entity, Float defaultVal) {
	}

	public Boolean getBoolean(Tag prop, Entity entity, Boolean defaultVal) {
	}

	public java.util.Date getDate(Property prop, Entity entity) {
	}

	public java.util.Date getDate(Tag tag, Entity entity) {
	}

	public java.util.Date getDate(Entity entity, Tag[] tags) {
	}

	public void setDate(Property prop, Entity entity, java.util.Date date) {
	}

	public boolean setDate(Tag tag, Entity entity, java.util.Date date) {
	}

	public boolean setDate(Entity entity, java.util.Date date, Tag[] tags) {
	}
}
