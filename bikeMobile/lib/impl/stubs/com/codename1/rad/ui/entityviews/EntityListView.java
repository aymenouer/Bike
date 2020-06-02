/**
 *  This package contains entity views - view components that are bound to an Entity value model.
 */
package com.codename1.rad.ui.entityviews;


/**
 *  A view that renders an {@link EntityList} visually.  This will bind to the list's events so that rows will animate in and 
 *  out appropriately when they are added to the model.  The list can be customized with a {@link EntityListCellRenderer}.
 *  
 *  @see ListCellRendererAttribute
 *  @see RowTemplateNode
 *  
 *  @author shannah
 */
public class EntityListView extends com.codename1.rad.ui.AbstractEntityView implements ca.weblite.shared.components.CollapsibleHeaderContainer.ScrollableContainer {

	public static final com.codename1.rad.ui.ViewProperty SCROLLABLE_Y;

	public static final com.codename1.rad.ui.ViewProperty SCROLLABLE_X;

	public EntityListView(com.codename1.rad.models.EntityList list, com.codename1.rad.nodes.ListNode node) {
	}

	@java.lang.Override
	protected void initComponent() {
	}

	@java.lang.Override
	protected void deinitialize() {
	}

	public void setListCellRenderer(com.codename1.rad.ui.EntityListCellRenderer renderer) {
	}

	public com.codename1.rad.ui.EntityListCellRenderer getListCellRenderer() {
	}

	@java.lang.Override
	public void update() {
	}

	@java.lang.Override
	public void commit() {
	}

	@java.lang.Override
	public com.codename1.rad.nodes.Node getViewNode() {
	}

	public com.codename1.ui.Container getScrollWrapper() {
	}

	@java.lang.Override
	public com.codename1.ui.Container getVerticalScroller() {
	}

	public void setListLayout(com.codename1.ui.layouts.Layout l) {
	}
}
