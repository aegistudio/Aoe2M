package net.aegistudo.aoe2m.unittype;

public interface UnitBuilder<E extends Exception> {
	public void flagSpeed() throws E;
	
	public void walking() throws E;
	
	public void discover() throws E;
	
	public void combat() throws E;
	
	public void projectile() throws E;
	
	public void production() throws E;
	
	public void building() throws E;
}
