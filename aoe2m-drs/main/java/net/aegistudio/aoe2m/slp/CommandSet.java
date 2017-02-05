package net.aegistudio.aoe2m.slp;

public interface CommandSet {
	public Command parse(byte opcode);
}
