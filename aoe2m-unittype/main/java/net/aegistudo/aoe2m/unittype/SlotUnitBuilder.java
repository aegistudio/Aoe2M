package net.aegistudo.aoe2m.unittype;

public class SlotUnitBuilder<E extends Exception> implements UnitBuilder<E> {
	public interface Exceptable<F extends Exception> {
		public void call() throws F;
	}
	
	public Exceptable<E> flagSpeed;
	public void flagSpeed() throws E {
		if(flagSpeed != null) flagSpeed.call();
	}

	public Exceptable<E> walking;
	public void walking() throws E {
		if(walking != null) walking.call();
	}

	public Exceptable<E> discover;
	public void discover() throws E {
		if(discover != null) discover.call();
	}

	public Exceptable<E> combat;
	public void combat() throws E {
		if(combat != null) combat.call();
	}

	public Exceptable<E> projectile;
	public void projectile() throws E {
		if(projectile != null) projectile.call();
	}

	public Exceptable<E> production;
	public void production() throws E {
		if(production != null) production.call();
	}

	public Exceptable<E> building;
	public void building() throws E {
		if(building != null) building.call();
	}
}
