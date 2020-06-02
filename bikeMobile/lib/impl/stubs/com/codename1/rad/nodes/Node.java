/**
 *  This package contains {@link Node} classes for use inside UI descriptors.
 *  
 *  = Models
 *  :toc:
 *  
 *  == Introduction
 *  
 *  === Entities
 *  
 *  === EntityTypes
 *  
 *  
 *  === Properties
 *  
 *  
 *  === Attributes
 *  
 *  
 *  === Tags
 *  
 *  
 *  
 */
package com.codename1.rad.nodes;


/**
 *  A special type of attribute that can contain child nodes.  This is the basis of the UI descriptor hierarchy. 
 *  
 *  @author shannah
 */
public abstract class Node extends com.codename1.rad.models.Attribute {

	/**
	 *  Node attributes.
	 */
	protected final com.codename1.rad.models.AttributeSet attributes;

	protected final com.codename1.rad.ui.NodeList childNodes;

	/**
	 *  View parameters which can be used for setting properties in views.
	 */
	protected final java.util.Map viewParameters;

	protected final java.util.Map actions;

	/**
	 *  Create a new node with given value and provided attributes.
	 *  @param value Node value, may be null.
	 *  @param atts Atrributes to set in the node.
	 */
	public Node(Object value, com.codename1.rad.models.Attribute[] atts) {
	}

	/**
	 *  Create a proxy for this node.  The node class must implement {@link Proxyable}
	 *  @param parent The parent node of the proxy node.
	 *  @return The proxy node.
	 */
	public Node proxy(Node parent) {
	}

	/**
	 *  Returns true if this node can be proxied.  Default return value is false.  Subclasses
	 *  that allow proxying should implement the Proxyable interface, and return true
	 *  for this method.
	 *  @return 
	 */
	public boolean canProxy() {
	}

	/**
	 *  Converts this node into a proxy node.
	 *  @param originalNode 
	 */
	public void setProxying(Node originalNode) {
	}

	/**
	 *  Gets the node that this node proxies.
	 *  @return 
	 */
	public Node getProxying() {
	}

	/**
	 *  Returns the original node in a proxy chain.  If this node is not proxying any
	 *  nodes, then it will return itself.
	 *  @return 
	 */
	public Node getCanonicalNode() {
	}

	/**
	 *  Gets an iterator for iterating up the proxy chain.  If this is not a proxy node
	 *  then this will return an empty iterator.
	 *  @return 
	 */
	public java.util.Iterator proxyingIterator() {
	}

	/**
	 *  Sets the parent node.  You cannot re-assign a non-null parent on a node that
	 *  already has a non-null parent.  A workaround is to first set parent to null, then
	 *  set the parent again to the new parent.  Some node types, such as Actions, need
	 *  to appear in multiple places in the UI tree.  In such cases, a proxy node is created
	 *  for the action in the various positions of the tree.
	 *  @param parent 
	 */
	public void setParent(Node parent) {
	}

	/**
	 *  Returns an attribute of the given type for this node.  If this is a proxy node, 
	 *  it will first check its own attributes.  If none is found, it will check the
	 *  node that it is proxying for the attribute.
	 *  @param <V> The type of attribute to return.
	 *  @param type The attribute type.
	 *  @return The attribute, or null, if this node doesn't have an attribute of this type.
	 */
	public com.codename1.rad.models.Attribute findAttribute(Class type) {
	}

	/**
	 *  Find an attribute in this node, or a parent node.  This first checks the current 
	 *  node for the given attribute. If none is found, it will check the parent node.
	 *  It will walk up the UI tree to the root until it finds an attribute of this type.
	 *  If none is found, it will check the proxy node, and walk up the tree from there.
	 *  @param <V> The attribute type to retrieve.
	 *  @param type The attribute type.
	 *  @return An attribute of the given type, or null if none found.
	 */
	public com.codename1.rad.models.Attribute findInheritedAttribute(Class type) {
	}

	/**
	 *  Gets a view parameter for this node.  This will walk up the tree until it finds 
	 *  a parameter for the given view property.  View properties are defined generally inside
	 *  the View that consumes them.  You can then set values or bindings on these properties
	 *  in the UI tree using the {@link UI#param()} method.
	 *  @param <V> The view parameter type
	 *  @param prop The property to retrieve.
	 *  @return The property parameter, or null if none found.
	 */
	public com.codename1.rad.ui.ViewPropertyParameter getViewParameter(com.codename1.rad.ui.ViewProperty prop) {
	}

	public Object getViewParameterValue(com.codename1.rad.ui.ViewProperty prop) {
	}

	public Object getViewParameterValue(com.codename1.rad.ui.ViewProperty prop, Object defaultValue) {
	}

	public boolean hasViewParameter(com.codename1.rad.ui.ViewProperty prop) {
	}

	/**
	 *  Gets a view parameter for this node.  This will walk up the tree until it finds 
	 *  a parameter for the given view property.  View properties are defined generally inside
	 *  the View that consumes them.  You can then set values or bindings on these properties
	 *  in the UI tree using the {@link UI#param()} method.
	 *  @param <V> The view parameter type.
	 *  @param prop The property to retrieve.
	 *  @param defaultVal Default value in case no property found.
	 *  @return 
	 */
	public com.codename1.rad.ui.ViewPropertyParameter getViewParameter(com.codename1.rad.ui.ViewProperty prop, com.codename1.rad.ui.ViewPropertyParameter defaultVal) {
	}

	/**
	 *  Gets the parent node of this node.
	 *  @return 
	 */
	public Node getParent() {
	}

	/**
	 *  Gets the first ancestor whose class matches the given type.
	 *  @param <V> 
	 *  @param type The class
	 *  @return The first matching ancestor or null
	 */
	public Node getAncestor(Class type) {
	}

	public Object getValue() {
	}

	public void setAttributesIfNotExists(com.codename1.rad.models.Attribute[] atts) {
	}

	/**
	 *  Sets attributes on this node.
	 *  @param atts The attributes to set.
	 */
	public void setAttributes(com.codename1.rad.models.Attribute[] atts) {
	}

	public com.codename1.rad.ui.NodeList getChildNodes() {
	}

	public com.codename1.rad.ui.NodeList getChildFieldNodes(com.codename1.rad.models.Tags tags) {
	}

	public com.codename1.rad.ui.NodeList getChildNodes(Class type) {
	}

	public com.codename1.rad.ui.Actions getActions(ActionNode.Category category) {
	}

	public ActionNode getAction(ActionNode.Category category) {
	}

	public com.codename1.rad.ui.Actions getInheritedActions(ActionNode.Category category) {
	}

	public ActionNode getInheritedAction(ActionNode.Category category) {
	}

	protected static final com.codename1.rad.models.Attribute[] merge(com.codename1.rad.models.Attribute[][] arrs) {
	}

	protected static final com.codename1.rad.models.Attribute[] mergeRecursive(com.codename1.rad.models.Attribute[][] arrs) {
	}

	public String getUIID(String defaultVal) {
	}

	public com.codename1.rad.attributes.UIID getUIID() {
	}

	public String getUIIDPrefix(String defaultVal) {
	}

	public com.codename1.rad.attributes.UIIDPrefix getUIIDPrefix() {
	}

	public com.codename1.rad.attributes.IconUIID getIconUIID() {
	}

	public com.codename1.rad.models.DateFormatterAttribute getDateFormatter() {
	}

	public com.codename1.rad.models.NumberFormatterAttribute getNumberFormatter() {
	}

	public Node as(Class type) {
	}

	public com.codename1.rad.models.PropertySelector createPropertySelector(com.codename1.rad.models.Entity entity) {
	}
}
