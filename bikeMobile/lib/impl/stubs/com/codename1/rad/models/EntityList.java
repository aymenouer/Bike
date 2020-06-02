/**
 *  This package contains classes for CodeRAD models, such as {@link Entity}, {@link Property}, and {@link Tag}.
 */
package com.codename1.rad.models;


/**
 *  Encapsulates a list of entities. This list is observable, as it will fire {@link EntityListEvent} events when items are added 
 *  and removed from this list.
 *  @author shannah
 */
public class EntityList extends Entity implements Iterable {

	public EntityList(int maxLen) {
	}

	public EntityList(EntityType rowType, int maxLen) {
	}

	public EntityList(EntityType rowType, int maxLen, java.util.List internalList) {
	}

	public EntityList() {
	}

	/**
	 *  @return the rowType
	 */
	public EntityType getRowType() {
	}

	/**
	 *  @param rowType the rowType to set
	 */
	public void setRowType(EntityType rowType) {
	}

	/**
	 *  Can be overridden by subclasses to create an alternate collection type
	 *  for the entity list.  Default implementation uses an ArrayList.
	 *  @return 
	 */
	protected java.util.List createInternalList() {
	}

	@java.lang.Override
	public java.util.Iterator iterator() {
	}

	public void add(Entity link) {
	}

	protected Entity beforeAdd(Entity link) {
	}

	protected Entity beforeRemove(Entity link) {
	}

	public boolean remove(Entity link) {
	}

	public void clear() {
	}

	public int size() {
	}

	public Entity get(int index) {
	}

	protected void fireEntityAdded(Entity e, int index) {
	}

	protected void fireEntityRemoved(Entity e, int index) {
	}

	public void addActionListener(com.codename1.ui.events.ActionListener l) {
	}

	public void removeActionListener(com.codename1.ui.events.ActionListener l) {
	}

	public class EntityListEvent {


		public EntityListEvent() {
		}
	}

	public class EntityEvent {


		public EntityEvent(Entity entity, int index) {
		}

		public Entity getEntity() {
		}

		public int getIndex() {
		}
	}

	public class EntityAddedEvent {


		public EntityAddedEvent(Entity entity, int index) {
		}
	}

	public class EntityRemovedEvent {


		public EntityRemovedEvent(Entity entity, int index) {
		}
	}
}
